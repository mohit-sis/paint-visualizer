package com.shawinfosolutions.paintvisualizer.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shawinfosolutions.paintvisualizer.Activity.OurProduct.OurProductInDetailsActivity;
import com.shawinfosolutions.paintvisualizer.Constants;
import com.shawinfosolutions.paintvisualizer.Model.ProductList;
import com.shawinfosolutions.paintvisualizer.R;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

public class DecorativeAdapter extends BaseAdapter {
    private Context mContext;
  private ArrayList<ProductList> productLists;
  private String[] imageName;
  String val;

    // Constructor
    public DecorativeAdapter(Context mContext, ArrayList<ProductList> productLists,String val) {
        this.mContext = mContext;
        this.productLists=productLists;
        this.val=val;
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
                    inflate(R.layout.decorative_items, parent, false);
        }

        // get current item to be displayed
      //  Item currentItem = (Item) getItem(position);

        // get the TextView for item name and item description
        TextView ColorNameTxt = (TextView)
                convertView.findViewById(R.id.ColorNameTxt);
        ImageView ItemImg = (ImageView)
                convertView.findViewById(R.id.ItemImg);

        //sets the text for item name and item description from the current item object
        ColorNameTxt.setText(productLists.get(position).getName());
      //  ItemImg.setText(mThumbIds.getItemDescription());
//        Glide.with(mContext)
//                .load(productLists.get(position).getImageLink())
//                .into(ItemImg);
//

        Picasso.with(mContext)
                .load(Constants.ImageURL +productLists.get(position).getImageLink())
                .placeholder(R.drawable.duco)
                .into(ItemImg);
        final LinearLayout productLayout=(LinearLayout) convertView.findViewById(R.id.productLayout);

        productLayout.setTag(R.string.tag1, productLists.get(position).getName());
        productLayout.setTag(R.string.tagimageLink, productLists.get(position).getImageLink());
        productLayout.setTag(R.string.tagdescription, productLists.get(position).getDescription());
        productLayout.setTag(R.string.taguses, productLists.get(position).getUses());
        productLayout.setTag(R.string.tagtype, productLists.get(position).getType());
        productLayout.setTag(R.string.tagcolor, productLists.get(position).getColor());
        productLayout.setTag(R.string.tagfinish, productLists.get(position).getFinish());
        productLayout.setTag(R.string.tagrecommended, productLists.get(position).getRecommended());
        productLayout.setTag(R.string.tagmixingRatio, productLists.get(position).getMixingRatio());

        productLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ProductName = view.getTag(R.string.tag1).toString();
                String ImageLink = view.getTag(R.string.tagimageLink).toString();
                String description = view.getTag(R.string.tagdescription).toString();
                String uses = view.getTag(R.string.taguses).toString();
                String type = view.getTag(R.string.tagtype).toString();
                String color = view.getTag(R.string.tagcolor).toString();
                String finish = view.getTag(R.string.tagfinish).toString();
                String recommended = view.getTag(R.string.tagrecommended).toString();
                String mixingRatio = view.getTag(R.string.tagmixingRatio).toString();

                ProductList productList=new ProductList();
                productList.setName(ProductName);
                productList.setImageLink(ImageLink);
                productList.setDescription(description);
                productList.setUses(uses);
                productList.setType(type);
                productList.setColor(color);
                productList.setFinish(finish);
                productList.setRecommended(recommended);
                productList.setMixingRatio(mixingRatio);
                productList.setIntentVal(val);

                Intent intent=new Intent(mContext, OurProductInDetailsActivity.class);
                intent.putExtra("productList", (Serializable) productList);
                mContext.startActivity(intent);


            }
        });
        // returns the view for the current row
        return convertView;
    }

}