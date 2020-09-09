package com.shawinfosolutions.paintvisualizer.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shawinfosolutions.paintvisualizer.Activity.SelectedProjectActivity;
import com.shawinfosolutions.paintvisualizer.Constants;
import com.shawinfosolutions.paintvisualizer.Model.ColorData;
import com.shawinfosolutions.paintvisualizer.Model.MyProject;
import com.shawinfosolutions.paintvisualizer.Model.MyProjectItem;
import com.shawinfosolutions.paintvisualizer.R;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class  MyProjectAdapter extends
        RecyclerView.Adapter<MyProjectAdapter.ViewHolder> {
    private final LayoutInflater mInflater;
    private Context mContext;
   //  private Integer[] mThumbIds;
   // private String[] imageName;
    ArrayList<MyProject> myProjects;
    // ... constructor and member variables

    public MyProjectAdapter(Context context, ArrayList<MyProject> myProjects) {
        this.mContext = context;
        this.myProjects=myProjects;
        //this.imageName=imageName;
        this.mInflater = LayoutInflater.from(context);
    }
    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public MyProjectAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.my_project_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(MyProjectAdapter.ViewHolder holder, int position) {
        // Get the data model based on position

        // Set item views based on your views and data model
        holder.ProjectName.setText(myProjects.get(position).getTitle());
        Log.e("ProjectName" ,myProjects.get(position).getTitle());

//        TextView textView = holder.nameTextView;
//        textView.setText(contact.getName());
//        Glide.with(mContext)
//                .load(mThumbIds[position])
//                .into(holder.projectImg);

//
        Picasso.with(mContext)

                .load(Constants.ImageURL +myProjects.get(position).getImageLink())
                .placeholder(R.drawable.image)
                .into(holder.projectImg);

        holder.projectLayout.setTag(R.string.tagPicture, myProjects.get(position).getPictures());
        holder.projectLayout.setTag(R.string.tagProducts, myProjects.get(position).getProducts());
        holder.projectLayout.setTag(R.string.tagColors, myProjects.get(position).getColors());

        final ArrayList <MyProjectItem> myProjectSelectesArrayList= new
                ArrayList<>();
        myProjectSelectesArrayList.clear();

        holder.projectLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Pictures = view.getTag(R.string.tagPicture).toString();
                String Products = view.getTag(R.string.tagProducts).toString();
                String Colors = view.getTag(R.string.tagColors).toString();
                //m
                String projectName = myProjects.get(0).getTitle();
                String projectImgLink = myProjects.get(0).getImageLink();

                MyProjectItem myProjectval=new MyProjectItem();

                myProjectval.setPictures(Pictures);
                myProjectval.setProducts(Products);
                myProjectval.setColors(Colors);
                //m
                myProjectval.setTitle(projectName);
                myProjectval.setProjectImgLink(projectImgLink);
                //myProjectSelectesArrayList.add(myProjectval);
                Intent intent=new Intent(mContext, SelectedProjectActivity.class);
                intent.putExtra("myProject",(Serializable) myProjectval);
                mContext.startActivity(intent);
            }
        });
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return myProjects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView projectImg;
        TextView ProjectName;
        LinearLayout projectLayout;

        ViewHolder(View itemView) {
            super(itemView);
            projectImg = itemView.findViewById(R.id.projectImg);
            ProjectName=itemView.findViewById(R.id.ProjectName);
            projectLayout=itemView.findViewById(R.id.projectLayout);
        }

    }
}
