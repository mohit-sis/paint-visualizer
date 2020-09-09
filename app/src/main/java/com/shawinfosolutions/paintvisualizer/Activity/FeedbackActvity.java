package com.shawinfosolutions.paintvisualizer.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.shawinfosolutions.paintvisualizer.R;

import androidx.annotation.Nullable;

public class FeedbackActvity extends Activity {
    private ImageView backBtn;
    private Button submitBtn;
    private EditText feebBackText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_layout);

        feebBackText = findViewById(R.id.feebBackText);
        submitBtn = findViewById(R.id.submitBtn);

        backBtn=findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FeedbackActvity.this,MoreOptionActivity.class);
                startActivity(intent);
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (feebBackText.getText().toString() == null && feebBackText.getText().toString().equalsIgnoreCase("")){
                    Log.e("feedbackText",feebBackText.getText().toString());
                    Toast.makeText(getApplicationContext(),"Please write something in feedback",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(),"Your Feedback submitted successfully",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(FeedbackActvity.this,MainActivity.class);
                    startActivity(intent);
                }

            }
        });
    }
}
