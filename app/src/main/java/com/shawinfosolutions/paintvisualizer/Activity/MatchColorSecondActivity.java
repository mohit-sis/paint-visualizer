package com.shawinfosolutions.paintvisualizer.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.shawinfosolutions.paintvisualizer.R;

import androidx.annotation.Nullable;

public class MatchColorSecondActivity extends Activity {
    private LinearLayout openOptionLayout;
    private LinearLayout selectedColorLayout;
    private ImageView backBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_color_layout);
        openOptionLayout=findViewById(R.id.openOptionLayout);
        selectedColorLayout=findViewById(R.id.selectedColorLayout);
        openOptionLayout.setVisibility(View.GONE);
        backBtn=findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MatchColorSecondActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        selectedColorLayout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openOptionLayout.setVisibility(View.VISIBLE);

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will

        switch (item.getItemId()) {
            case android.R.id.home:

                Intent intent = new Intent(MatchColorSecondActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

                //  finish();
                break;

        }
        return (super.onOptionsItemSelected(item));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MatchColorSecondActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
