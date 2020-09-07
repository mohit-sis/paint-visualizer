package com.shawinfosolutions.paintvisualizer.Fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.shawinfosolutions.paintvisualizer.Activity.VisualizerActivity;
import com.shawinfosolutions.paintvisualizer.Adapter.Adapter_PhotosFolder;
import com.shawinfosolutions.paintvisualizer.Adapter.DecorativeAdapter;
import com.shawinfosolutions.paintvisualizer.Adapter.GalleryAdapter;
import com.shawinfosolutions.paintvisualizer.Model.ProductList;
import com.shawinfosolutions.paintvisualizer.Model_images;
import com.shawinfosolutions.paintvisualizer.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class GalleryFragment extends Fragment {
    public static ArrayList<Model_images> al_images = new ArrayList<>();
    private boolean boolean_folder;
    private Adapter_PhotosFolder obj_adapter;

    private GridView grid_view;
    Context thiscontext;
    private static final int REQUEST_PERMISSIONS = 100;

    public GalleryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_grid_gallery, container, false);
        thiscontext = container.getContext();
        grid_view = root.findViewById(R.id.grid_view);
        Bundle bundle = this.getArguments();
        String Pictures = bundle.getString("Pictures");
        Log.e("Pictures","==="+Pictures);
        try {
            ArrayList<ProductList> productLists=new ArrayList<>();
            productLists.clear();
            JSONArray jsonArray = new JSONArray(Pictures);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject explrObject = jsonArray.getJSONObject(i);
                ProductList productList=new
                        ProductList();

                productList.setName(explrObject.getString("name"));
                productList.setImageLink(explrObject.getString("imageLink"));
                productLists.add(productList);
            }
            GalleryAdapter adapter = new GalleryAdapter(getActivity(),productLists);
            grid_view.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }



//        if ((ContextCompat.checkSelfPermission(getContext(),
//                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(getContext(),
//                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
//            if ((ActivityCompat.shouldShowRequestPermissionRationale((Activity) thiscontext,
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) && (ActivityCompat.shouldShowRequestPermissionRationale((Activity) thiscontext,
//                    Manifest.permission.READ_EXTERNAL_STORAGE))) {
//
//            } else {
//                ActivityCompat.requestPermissions((Activity) thiscontext,
//                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
//                        REQUEST_PERMISSIONS);
//            }
//        } else {
//            Log.e("Else", "Else");
//            try{
//                fn_imagespath();
//
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
        return root;
    }
//    public ArrayList<Model_images> fn_imagespath() {
//        al_images.clear();
//
//        int int_position = 0;
//        Uri uri;
//        Cursor cursor;
//        int column_index_data, column_index_folder_name;
//
//        String absolutePathOfImage = null;
//        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//
//        String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME};
//
//        final String orderBy = MediaStore.Images.Media.DATE_TAKEN;
//        cursor =getContext().getContentResolver().query(uri, projection, null, null, orderBy + " DESC");
//
//        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
//        column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
//        while (cursor.moveToNext()) {
//            absolutePathOfImage = cursor.getString(column_index_data);
//            Log.e("Column", absolutePathOfImage);
//            Log.e("Folder", cursor.getString(column_index_folder_name));
//
//            for (int i = 0; i < al_images.size(); i++) {
//                if (al_images.get(i).getStr_folder().equals(cursor.getString(column_index_folder_name))) {
//                    boolean_folder = true;
//                    int_position = i;
//                    break;
//                } else {
//                    boolean_folder = false;
//                }
//            }
//
//
//            if (boolean_folder) {
//
//                ArrayList<String> al_path = new ArrayList<>();
//                al_path.addAll(al_images.get(int_position).getAl_imagepath());
//                al_path.add(absolutePathOfImage);
//                al_images.get(int_position).setAl_imagepath(al_path);
//
//            } else {
//                ArrayList<String> al_path = new ArrayList<>();
//                al_path.add(absolutePathOfImage);
//                Model_images obj_model = new Model_images();
//                obj_model.setStr_folder(cursor.getString(column_index_folder_name));
//                obj_model.setAl_imagepath(al_path);
//                try {
//                    al_images.add(obj_model);
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//
//            }
//
//
//        }
//
//
//        for (int i = 0; i < al_images.size(); i++) {
//            Log.e("FOLDER", al_images.get(i).getStr_folder());
//            for (int j = 0; j < al_images.get(i).getAl_imagepath().size(); j++) {
//                Log.e("FILE", al_images.get(i).getAl_imagepath().get(j));
//            }
//        }
//        try{
//            obj_adapter = new Adapter_PhotosFolder(getContext(), al_images);
//            grid_view.setAdapter(obj_adapter);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return al_images;
//
//    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        switch (requestCode) {
//            case REQUEST_PERMISSIONS: {
//                for (int i = 0; i < grantResults.length; i++) {
//                    if (grantResults.length > 0 && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
//                        try{
//                            fn_imagespath();
//                        }catch (Exception e){
//                            e.printStackTrace();
//                        }
//                    } else {
//                        Toast.makeText(getContext(), "The app was not allowed to read or write to your storage. Hence, it cannot function properly. Please consider granting it this permission", Toast.LENGTH_LONG).show();
//                    }
//                }
//            }
//        }
//    }

}


