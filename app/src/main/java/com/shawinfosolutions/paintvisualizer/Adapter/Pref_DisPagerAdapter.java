package com.shawinfosolutions.paintvisualizer.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shawinfosolutions.paintvisualizer.Constants;
import com.shawinfosolutions.paintvisualizer.Model.ColorData;
import com.shawinfosolutions.paintvisualizer.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.viewpager.widget.PagerAdapter;

public class Pref_DisPagerAdapter extends PagerAdapter {

    private final LayoutInflater layoutInflater;
    private Context context;
    private ArrayList<String> imagePathlist;
    private ArrayList<ColorData> colorDataList;

    public Pref_DisPagerAdapter(Context context, ArrayList<String> imagePathlist, ArrayList<ColorData> colorDataList) {
        this.context=context;
        this.imagePathlist=imagePathlist;
        this.colorDataList=colorDataList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() {
        return imagePathlist.size();
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
        LinearLayout basecolor=itemView.findViewById(R.id.basecolor);

        container.addView(itemView);
       // Glide.with(context).load(Constants.ImageURL + imagePathlist.get(position)).into(image1);
        Picasso.with(context)
                .load(Constants.ImageURL + imagePathlist.get(position))
                .placeholder(R.drawable.image)
                .into(image1);
        basecolor.removeAllViews();
        for(int i=0;i<imagePathlist.size();i++){
            LinearLayout    childProd = (LinearLayout) layoutInflater.inflate(R.layout.color_child, null);
            LinearLayout colorLayoutVal = (LinearLayout) childProd.findViewById(R.id.colorLayoutVal);
            TextView colorNameTxt = (TextView) childProd.findViewById(R.id.colorNameTxt);
            colorLayoutVal.setBackgroundColor(Color.parseColor(colorDataList.get(i).getHexcodeVal()));
            colorNameTxt.setText(colorDataList.get(i).getColorName());

            basecolor.addView(childProd);
        }

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
