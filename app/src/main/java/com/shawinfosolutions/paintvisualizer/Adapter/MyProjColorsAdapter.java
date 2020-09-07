package com.shawinfosolutions.paintvisualizer.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shawinfosolutions.paintvisualizer.Activity.OurProduct.OurProductInDetailsActivity;
import com.shawinfosolutions.paintvisualizer.Constants;
import com.shawinfosolutions.paintvisualizer.Model.ColorData;
import com.shawinfosolutions.paintvisualizer.Model.ProductList;
import com.shawinfosolutions.paintvisualizer.R;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;


    public class MyProjColorsAdapter extends BaseAdapter {
        private Context mContext;
        private ArrayList<ColorData> productLists;
        private String[] imageName;

        // Constructor
        public MyProjColorsAdapter(Context mContext, ArrayList<ColorData> productLists) {
            this.mContext = mContext;
            this.productLists=productLists;
            // this.imageName=imageName;
        }

        @Override
        public int getCount() {
            return productLists.size(); //returns total of items in the list
        }

        @Override
        public Object getItem(int position) {
            return productLists.size(); //returns list item at the specified position
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // inflate the layout for each list row
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).
                        inflate(R.layout.my_proj_color_items, parent, false);


            }

            // get current item to be displayed
            //  Item currentItem = (Item) getItem(position);

            // get the TextView for item name and item description
//            TextView ColorNameTxt = (TextView)
//                    convertView.findViewById(R.id.ColorNameTxt);
//            ImageView ItemImg = (ImageView)
//                    convertView.findViewById(R.id.ItemImg);
          LinearLayout  colorLayout = convertView.findViewById(R.id.colorLayout);
            TextView colorNameTxt=convertView.findViewById(R.id.colorNameTxt);
            //sets the text for item name and item description from the current item object
            colorNameTxt.setText(productLists.get(position).getColorName());
            colorLayout.setBackgroundColor(Color.parseColor(productLists.get(position).getHexcodeVal())); // From android.graphics.Color

            //  ItemImg.setText(mThumbIds.getItemDescription());
//        Glide.with(mContext)
//                .load(productLists.get(position).getImageLink())
//                .into(ItemImg);
//

//            Picasso.with(mContext)
//                    .load(Constants.ImageURL +productLists.get(position).getImageLink())
//                    .placeholder(R.drawable.duco)
//                    .into(ItemImg);


            return convertView;
        }

    }
