package com.shawinfosolutions.paintvisualizer.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shawinfosolutions.paintvisualizer.Activity.InspirationalTrendsActivity;
import com.shawinfosolutions.paintvisualizer.Activity.SelectedColorToImageActivity;
import com.shawinfosolutions.paintvisualizer.Constants;
import com.shawinfosolutions.paintvisualizer.Model.ColorData;
import com.shawinfosolutions.paintvisualizer.Model.PrefDiscoveryData;
import com.shawinfosolutions.paintvisualizer.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class Pref_DisPagerAdapter extends PagerAdapter {

    private final LayoutInflater layoutInflater;
    private Context context;
    private ArrayList<String> imagePathList;
    private ArrayList<ColorData> colorDataList;
    private PrefDiscoveryData prefData;
    private ViewPager viewPager;
    private LinearLayout colorListLayout, selectedColorLay, openOptionLayout;

    public Pref_DisPagerAdapter(Context context, ArrayList<String> imagePathList, ArrayList<ColorData> colorDataList, PrefDiscoveryData prefData) {
        this.context=context;
        this.imagePathList=imagePathList;
        this.colorDataList=colorDataList;
        this.prefData = prefData;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() {
        return imagePathList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.color_pref_child, container, false);

        // ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        //imageView.setImageResource(images[position]);
        ImageView image1 = itemView.findViewById(R.id.image1);
        //LinearLayout baseColor=itemView.findViewById(R.id.basecolor);

        //m
        TextView descTextView = itemView.findViewById(R.id.DescriptionTxt1);
        ImageView nextBtn = itemView.findViewById(R.id.nxtImgView);
        ImageView preImgView = itemView.findViewById(R.id.preImgView);
        final ImageView like_img = itemView.findViewById(R.id.like_img);
        colorListLayout = itemView.findViewById(R.id.colorListLayout);

        /*
        //To set Theme description from data
        String descriptionText = prefData.getDescription();
        descTextView.setText(descriptionText.substring(0,10));
         */

        container.addView(itemView);
       // Glide.with(context).load(Constants.ImageURL + imagePathlist.get(position)).into(image1);
        Picasso.with(context)
                .load(Constants.ImageURL + imagePathList.get(position))
                .placeholder(R.drawable.image)
                .into(image1);
        //baseColor.removeAllViews();
        //m
        colorListLayout.removeAllViews();
        Log.e("imagePathListSize",String.valueOf(imagePathList.size()));
        Log.e("AdapterColorDataSize",String.valueOf(colorDataList.size()));
        Log.e("prefDataColorSize",String.valueOf(prefData.getColors().size()));

        //for(int i=0;i<prefData.getColors().size();i++)
        for(int i=0;i< colorDataList.size();i++)
        {
            /*
            LinearLayout childProd = (LinearLayout) layoutInflater.inflate(R.layout.color_child, null);
            LinearLayout colorLayoutVal = (LinearLayout) childProd.findViewById(R.id.colorLayoutVal);
            TextView colorNameTxt = (TextView) childProd.findViewById(R.id.colorNameTxt);
            colorLayoutVal.setBackgroundColor(Color.parseColor(colorDataList.get(i).getHexcodeVal()));
            colorNameTxt.setText(colorDataList.get(i).getColorName());
            */
            String colorName = colorDataList.get(i).getColorName();

            LinearLayout childColor = (LinearLayout) layoutInflater.inflate(R.layout.color_child, null);
            LinearLayout colorLayoutVal = (LinearLayout) childColor.findViewById(R.id.colorLayoutVal);
            LinearLayout selectedColorLay = childColor.findViewById(R.id.selectColorLayout);

            TextView colorNameTxt = (TextView) childColor.findViewById(R.id.colorNameTxt);

            Log.e("colorName", colorDataList.get(i).getColorName());
            Log.e("ColorCode",String.valueOf(Color.parseColor(colorDataList.get(i).getHexcodeVal())));

            colorLayoutVal.setBackgroundColor(Color.parseColor(colorDataList.get(i).getHexcodeVal()));
            colorNameTxt.setText(colorName.substring(0, 1).toUpperCase()+ colorName.substring(1).toLowerCase());

            colorListLayout.addView(childColor);
            //baseColor.addView(childProd);
        }

        /*
        //To open optionLayout
        selectedColorLay=itemView.findViewById(R.id.selectedColorLay);

        selectedColorLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("click", "click on childColor");
                openOptionLayout.setVisibility(View.VISIBLE);
            }
        });
        */

        //next and prev pref_discovery
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("pref_dis","nextButtonClicked");
                //Log.e("pageNo:",String.valueOf(viewPager.getCurrentItem()));
                //viewPager.setCurrentItem(getItem(+1), true);
            }
        });

        preImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("pref_dis","prevButtonClicked");
                //Log.e("pageNo:",String.valueOf(viewPager.getCurrentItem()));
                //viewPager.setCurrentItem(getItem(-1), true);
            }
        });

        //when click on like button
        like_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("pref_dis","like_clicked");
                //like_img.setImageResource(R.drawable.like_img_red);
            }
        });


        //listening to image click
       /* imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "you clicked image " + (position + 1), Toast.LENGTH_LONG).show();
            }
        });*/

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

}
