package com.shawinfosolutions.paintvisualizer.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.shawinfosolutions.paintvisualizer.R;

public class ContactUsActivity extends Activity {

    private ImageView backBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_us);

        backBtn=findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ContactUsActivity.this,MoreOptionActivity.class);
                startActivity(intent);
            }
        });

    }
}
