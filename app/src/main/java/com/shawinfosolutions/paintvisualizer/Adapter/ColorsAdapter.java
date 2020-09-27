package com.shawinfosolutions.paintvisualizer.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shawinfosolutions.paintvisualizer.Model.ColorData;
import com.shawinfosolutions.paintvisualizer.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ColorsAdapter extends RecyclerView.Adapter<ColorsAdapter.ViewHolder> {

    private List<ColorData> mData;
    private LayoutInflater mInflater;
    private Context mContext;

    // data is passed into the constructor
    public ColorsAdapter(Context context, List<ColorData> data) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.color_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //  String animal = mData.get(position);
        //   holder.child_layout.setBackgroundResource(R.drawable.bg_button_one); // From android.graphics.Color
        holder.colorLayout.setBackgroundColor(Color.parseColor(mData.get(position).getHex())); // From android.graphics.Color
        holder.colorNameTxt.setText(mData.get(position).getColorName());
//        holder.myTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(mContext, "item clicked at " + position, Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    // total number of items
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout colorLayout;
        TextView colorNameTxt;

        ViewHolder(View itemView) {
            super(itemView);
            colorLayout = itemView.findViewById(R.id.colorLayout);
            colorNameTxt=itemView.findViewById(R.id.colorNameTxt);
        }

    }

}
