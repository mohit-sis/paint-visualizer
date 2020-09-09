package com.shawinfosolutions.paintvisualizer.Activity.OurProduct;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shawinfosolutions.paintvisualizer.Constants;
import com.shawinfosolutions.paintvisualizer.Model.ProductList;
import com.shawinfosolutions.paintvisualizer.R;
import com.squareup.picasso.Picasso;

import androidx.annotation.Nullable;

public class OurProductInDetailsActivity extends Activity {
private TextView productNameTxt,productDescTxt,TypicalUsesTxt,TypeTxt,ColorTxt,FinishTxt,RECOMMENDED_Txt,MixingRatioTxt;
private LinearLayout saveProjLayout;

    private ImageView productImg, backBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.our_product_in_detail);

        productImg = findViewById(R.id.productImg);
        productNameTxt=findViewById(R.id.productNameTxt);
        productDescTxt=findViewById(R.id.productDescTxt);
        saveProjLayout=findViewById(R.id.saveProjLayout);
        TypicalUsesTxt=findViewById(R.id.TypicalUsesTxt);
        TypeTxt=findViewById(R.id.TypeTxt);
        ColorTxt=findViewById(R.id.ColorTxt);
        FinishTxt=findViewById(R.id.FinishTxt);
        RECOMMENDED_Txt=findViewById(R.id.RECOMMENDED_Txt);
        MixingRatioTxt=findViewById(R.id.MixingRatioTxt);
        if(getIntent().getExtras() != null) {
            ProductList user = (ProductList) getIntent().getSerializableExtra("productList");
            productNameTxt.setText(user.getName());
            TypicalUsesTxt.setText(user.getUses());
            productDescTxt.setText(user.getDescription());
            TypeTxt.setText(user.getType());
            ColorTxt.setText(user.getColor());
            FinishTxt.setText(user.getFinish());
            RECOMMENDED_Txt.setText(user.getRecommended());
            MixingRatioTxt.setText(user.getMixingRatio());


            Picasso.with(OurProductInDetailsActivity.this)
                    .load(Constants.ImageURL +user.getImageLink())
                    .placeholder(R.drawable.duco)
                    .into(productImg);

            String intentVal=user.getIntentVal();
            if(intentVal.equalsIgnoreCase("Decor")){
                saveProjLayout.setVisibility(View.VISIBLE);
            }else if(intentVal.equalsIgnoreCase("myProj")){
                saveProjLayout.setVisibility(View.GONE);

            }

           // TypicalUsesTxt.setText(user);
        }

        backBtn=findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(OurProductInDetailsActivity.this,OurProductActivity.class);
                startActivity(intent);
            }
        });
    }
}
