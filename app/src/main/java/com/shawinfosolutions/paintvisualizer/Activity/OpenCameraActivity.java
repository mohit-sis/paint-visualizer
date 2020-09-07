package com.shawinfosolutions.paintvisualizer.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraX;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageAnalysisConfig;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureConfig;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.core.PreviewConfig;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.util.Rational;
import android.util.Size;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.shawinfosolutions.paintvisualizer.R;
import com.shawinfosolutions.paintvisualizer.TouchPoints;

import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Range;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class OpenCameraActivity extends AppCompatActivity implements View.OnClickListener{

    private static String TAG = "MainActivity";
    private int REQUEST_CODE_PERMISSIONS = 101;
    private final String[] REQUIRED_PERMISSIONS = new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"};
    TextureView textureView;
    ImageView ivBitmap;
    LinearLayout llBottom;
    private  Mat mRgba = new Mat();
    private  boolean isTouched = false;
    int currentImageType = Imgproc.COLOR_RGB2GRAY;
    private ActionBar actionbar;

    ImageCapture imageCapture;
    ImageAnalysis imageAnalysis;
    Preview preview;

    FloatingActionButton btnCapture, btnOk, btnCancel;

    static {
        if (!OpenCVLoader.initDebug())
            Log.d("ERROR", "Unable to load OpenCV");
        else
            Log.d("SUCCESS", "OpenCV loaded");
    }

    List<TouchPoints> touchPoints = Collections.synchronizedList(new ArrayList<TouchPoints>());
    Random rnd  = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_layout);
        actionbar = getSupportActionBar();
        actionbar.setTitle("Paint Visualizer");

        btnCapture = findViewById(R.id.btnCapture);
        btnOk = findViewById(R.id.btnAccept);
        btnCancel = findViewById(R.id.btnReject);

        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        llBottom = findViewById(R.id.llBottom);
        textureView = findViewById(R.id.textureView);
        ivBitmap = findViewById(R.id.ivBitmap);
        ivBitmap.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if( mRgba != null ) {
                    isTouched = true;
                    double cols = mRgba.cols();// mRgba is your image frame
                    double rows = mRgba.rows();

                    double xOffset = (textureView.getWidth() - cols) / 2;
                    double yOffset = (textureView.getHeight() - rows) / 2;

                    double onClickX = (float) (event).getX();
                    double onClickY = (float) (event).getY() ;

                    onClickX = (float) (event).getX() - xOffset;
                    onClickY = (float) (event).getY() - yOffset;

                    double [] colors = new double[3];
                    colors[0]=  180; //rnd.nextInt(255);
                    colors[1] = 10; //rnd.nextInt(255);
                    colors[2] =  rnd.nextInt(180);

                    touchPoints.add(new TouchPoints(onClickX,onClickY,colors));
                }

                return false;
            }
        });

        if (allPermissionsGranted()) {
            startCamera();
        } else {
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);
        }
    }

    private void startCamera() {

        CameraX.unbindAll();
        preview = setPreview();
        imageCapture = setImageCapture();
        imageAnalysis = setImageAnalysis();

        //bind to lifecycle:
        CameraX.bindToLifecycle(this, preview, imageCapture, imageAnalysis);
    }


    private Preview setPreview() {

        Rational aspectRatio = new Rational(textureView.getWidth(), textureView.getHeight());
        Size screen = new Size(textureView.getWidth(), textureView.getHeight()); //size of the screen


        PreviewConfig pConfig = new PreviewConfig.Builder().setTargetAspectRatio(aspectRatio).setTargetResolution(screen).build();
        Preview preview = new Preview(pConfig);

        preview.setOnPreviewOutputUpdateListener(
                new Preview.OnPreviewOutputUpdateListener() {
                    @Override
                    public void onUpdated(Preview.PreviewOutput output) {
                        ViewGroup parent = (ViewGroup) textureView.getParent();
                        parent.removeView(textureView);
                        parent.addView(textureView, 0);

                        textureView.setSurfaceTexture(output.getSurfaceTexture());
                        updateTransform();
                    }
                });

        return preview;
    }


    private ImageCapture setImageCapture() {
        ImageCaptureConfig imageCaptureConfig = new ImageCaptureConfig.Builder().setCaptureMode(ImageCapture.CaptureMode.MIN_LATENCY)
                .setTargetRotation(getWindowManager().getDefaultDisplay().getRotation()).build();
        final ImageCapture imgCapture = new ImageCapture(imageCaptureConfig);


        btnCapture.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                imgCapture.takePicture(new ImageCapture.OnImageCapturedListener() {
                    @Override
                    public void onCaptureSuccess(ImageProxy image, int rotationDegrees) {
                        Bitmap bitmap = textureView.getBitmap();
                        showAcceptedRejectedButton(true);
                        ivBitmap.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onError(ImageCapture.UseCaseError useCaseError, String message, @Nullable Throwable cause) {
                        super.onError(useCaseError, message, cause);
                    }
                });


                /*File file = new File(
                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "" + System.currentTimeMillis() + "_JDCameraX.jpg");
                imgCapture.takePicture(file, new ImageCapture.OnImageSavedListener() {
                    @Override
                    public void onImageSaved(@NonNull File file) {
                        Bitmap bitmap = textureView.getBitmap();
                        showAcceptedRejectedButton(true);
                        ivBitmap.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onError(@NonNull ImageCapture.UseCaseError useCaseError, @NonNull String message, @Nullable Throwable cause) {

                    }
                });*/
            }
        });

        return imgCapture;
    }


    private ImageAnalysis setImageAnalysis() {

        // Setup image analysis pipeline that computes average pixel luminance
        HandlerThread analyzerThread = new HandlerThread("OpenCVAnalysis");
        analyzerThread.start();


        ImageAnalysisConfig imageAnalysisConfig = new ImageAnalysisConfig.Builder()
                .setImageReaderMode(ImageAnalysis.ImageReaderMode.ACQUIRE_LATEST_IMAGE)
                .setCallbackHandler(new Handler(analyzerThread.getLooper()))
                .setImageQueueDepth(1).build();

        ImageAnalysis imageAnalysis = new ImageAnalysis(imageAnalysisConfig);

        imageAnalysis.setAnalyzer(
                new ImageAnalysis.Analyzer() {
                    @Override
                    public void analyze(ImageProxy image, int rotationDegrees) {
                        //Analyzing live camera feed begins.

                        final Bitmap bitmap = textureView.getBitmap();

                        if(bitmap==null)
                            return;


                        Mat mRgb = new Mat();
                        Utils.bitmapToMat(bitmap, mRgba);

                        List<Mat> mats = new ArrayList<Mat>();
                        //Segmentation start
                        Core.split(mRgba,mats);
                        //remove alpha
                        mats.remove(3);
                        //create rgb
                        Core.merge(mats,mRgb);

                        Mat canny = edgeDectection(mats.get(0));
                        Mat cannyMaskborder = canny.clone();
                        Core.copyMakeBorder(cannyMaskborder, cannyMaskborder, 1, 1, 1, 1, 0, Scalar.all(255));

                        TouchPoints [] touchPointArr = new TouchPoints [touchPoints.size()];
                        touchPoints.toArray(touchPointArr);
                        Mat segmentedMat = createSegmentation(touchPointArr,cannyMaskborder, mRgb,canny);
                        //  mRgb.release();
                        canny.release();
                        mats.clear();
                        if(isTouched) {
                            mRgb = colorImage(touchPointArr, mRgb, segmentedMat);
                            Utils.matToBitmap(mRgb, bitmap);
                        }else{
                            Utils.matToBitmap(mRgba, bitmap);
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ivBitmap.setImageBitmap(bitmap);
                            }
                        });

                    }
                });


        return imageAnalysis;

    }

    private Mat edgeDectection(Mat mat){

        Mat mGray =  new Mat();
        Mat mCanny = new Mat();
        Imgproc.GaussianBlur(mat, mGray, new org.opencv.core.Size(3, 3), 0);
        Imgproc.Canny(mGray, mCanny, 25.0, 65.0d);
        Imgproc.dilate(mCanny, mCanny, Imgproc.getStructuringElement(0, new org.opencv.core.Size(5.0d, 5.0d), new Point(-1, -1)), new Point(-1, -1), 1);
        Imgproc.erode(mCanny, mCanny, Imgproc.getStructuringElement(0, new org.opencv.core.Size(5.0d, 5.0d), new Point(-1, -1)), new Point(-1, -1), 1);

        return  mCanny;
    }

    private Mat createSegmentation(TouchPoints [] touchPointArr, Mat cannyMaskborder, Mat mRgb, Mat mCanny){

        Mat mR = new Mat();
        Mat mB = new Mat();

        for(int i =0;i<touchPointArr.length;i++){

            TouchPoints touchPoint = touchPointArr[i];

            if ( !(touchPoint.getX() == 0.0d && touchPoint.getY() != 0.0d) ) {

                double[] fillColors = cannyMaskborder.get((int) touchPoint.getY(), (int) touchPoint.getX());

                if(fillColors != null) {

                    double fillColor = fillColors[0];
                    if (fillColor == 0.0d || fillColor == 255.0d) {
                        // Log.e(TAG, "Creating MASK Y: " +  touchPoint.getY() +  " X: " + touchPoint.getX() );
                        Imgproc.floodFill(mRgb, cannyMaskborder, new Point(touchPoint.getX(), touchPoint.getY()), new Scalar(255), Imgproc.boundingRect(cannyMaskborder), Scalar.all(5), Scalar.all(5), ((int)fillColor << 8) | 4 | 131072);
                    } else {

                        // Log.e(TAG, "Fill Color out of range" + fillColor);
                        org.opencv.core.Size _size = cannyMaskborder.size();
                        mR = Mat.zeros(_size,cannyMaskborder.type());

                        // Core.compare
                        Scalar _s = new Scalar(fillColor, 0, 0, 0);
                        Core.compare(cannyMaskborder, _s, mR, Core.CMP_EQ);
                        Scalar _s1 = new Scalar(i + 1, 0, 0, 0);
                        cannyMaskborder.setTo(_s1, mR);

                    }

                }/*else{

                    Log.e(TAG,"fillColors is  null " );
                    Log.e(TAG, "fillColors is NULL for Y: " +  touchPoint.getY() +  " X: " + touchPoint.getX() );
                }*/

            }

            //Imgproc.rectangle(mRgb, new Point(touchPoint.getX(), touchPoint.getY()), new Point(touchPoint.getX() + 30, touchPoint.getY() + 30), new Scalar(255, 255, 255), 5);
        }


        Mat mRange = new Mat(cannyMaskborder,new Range(1,cannyMaskborder.rows() -1), new Range(1,cannyMaskborder.cols() - 1));
        Core.subtract(mRange,mCanny,mB);
        mRange.release();

        Imgproc.morphologyEx(mB,mB,3, Imgproc.getStructuringElement(0, new org.opencv.core.Size(5.0d, 5.0d), new Point(-1, -1)), new Point(-1,-1),3);
        Imgproc.dilate(mB, mB,Imgproc.getStructuringElement(0, new org.opencv.core.Size(5.0d, 5.0d), new Point(-1, -1)), new Point(-1,-1),1);

        mR.release();
        cannyMaskborder.release();

        return mB;
    }

    private Mat colorImage(TouchPoints [] touchPointArr, Mat mRgb,Mat mB){

        int channels = mRgb.channels();
        //Coloring -
        int[] iArr = new int[(mB.cols() * mB.rows())];
        int[] iArr2 = new int[(mRgb.cols() * mRgb.rows() * channels)];
        mB.convertTo(mB,CvType.CV_32S);
        mB.get(0,0,iArr);
        mRgb.convertTo(mRgb,CvType.CV_32S);
        mRgb.get(0,0,iArr2);
        int l = touchPointArr.length;
        for (int i = 0; i < iArr.length; i++) {
            int i2 = iArr[i];
            if (i2 != 0) {
                if(i2 <= l) {
                    TouchPoints points = touchPointArr[i2 - 1];
                    double[] colors = points.getSelectedColor();
                    int i5 = i * channels;
                    int i6 = iArr2[i5];
                    iArr2[i5] = (int) colors[0];
                    iArr2[i5 + 1] = (int) colors[1];
                    iArr2[i5 + 2] = (int) colors[2];
                }
            }
        }

        mRgb.put(0,0,iArr2);
        mRgb.convertTo(mRgb,CvType.CV_8U);

        return  mRgb;

    }


    private void showAcceptedRejectedButton(boolean acceptedRejected) {
        if (acceptedRejected) {
            CameraX.unbind(preview, imageAnalysis);
            llBottom.setVisibility(View.VISIBLE);
            btnCapture.hide();
            textureView.setVisibility(View.GONE);
        } else {
            btnCapture.show();
            llBottom.setVisibility(View.GONE);
            textureView.setVisibility(View.VISIBLE);
            textureView.post(new Runnable() {
                @Override
                public void run() {
                    startCamera();
                }
            });
        }
    }


    private void updateTransform() {
        Matrix mx = new Matrix();
        float w = textureView.getMeasuredWidth();
        float h = textureView.getMeasuredHeight();

        float cX = w / 2f;
        float cY = h / 2f;

        int rotationDgr;
        int rotation = (int) textureView.getRotation();

        switch (rotation) {
            case Surface.ROTATION_0:
                rotationDgr = 0;
                break;
            case Surface.ROTATION_90:
                rotationDgr = 90;
                break;
            case Surface.ROTATION_180:
                rotationDgr = 180;
                break;
            case Surface.ROTATION_270:
                rotationDgr = 270;
                break;
            default:
                return;
        }

        mx.postRotate((float) rotationDgr, cX, cY);
        textureView.setTransform(mx);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                startCamera();
            } else {
                Toast.makeText(this, "Permissions not granted by the user.", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private boolean allPermissionsGranted() {

        for (String permission : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_camera, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.black_white:
                currentImageType = Imgproc.COLOR_RGB2GRAY;
                startCamera();
                return true;

            case R.id.hsv:
                currentImageType = Imgproc.COLOR_RGB2HSV;
                startCamera();
                return true;

            case R.id.lab:
                currentImageType = Imgproc.COLOR_RGB2Lab;
                startCamera();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnReject:
                showAcceptedRejectedButton(false);
                break;

            case R.id.btnAccept:
                File file = new File(
                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "" + System.currentTimeMillis() + "_JDCameraX.jpg");
                imageCapture.takePicture(file, new ImageCapture.OnImageSavedListener() {
                    @Override
                    public void onImageSaved(@NonNull File file) {
                        showAcceptedRejectedButton(false);

                        Toast.makeText(getApplicationContext(), "Image saved successfully in Pictures Folder", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(@NonNull ImageCapture.UseCaseError useCaseError, @NonNull String message, @Nullable Throwable cause) {

                    }
                });
                break;
        }
    }


}
