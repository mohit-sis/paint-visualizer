package com.shawinfosolutions.paintvisualizer.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.shawinfosolutions.paintvisualizer.R;

import androidx.annotation.Nullable;

public class SelectedColorToImageActivity extends Activity {
    private LinearLayout saveProjLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_color_to_image_layout);
        saveProjLayout=findViewById(R.id.saveProjLayout);
        saveProjLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(SelectedColorToImageActivity.this,MatchColorSecondActivity.class);
//                startActivity(intent);
//                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will

        switch (item.getItemId()) {
            case android.R.id.home:

                Intent intent = new Intent(SelectedColorToImageActivity.this, SelectColorFromPalletActivity.class);
                startActivity(intent);
                finish();

                //  finish();
                break;

        }
        return (super.onOptionsItemSelected(item));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SelectedColorToImageActivity.this, SelectColorFromPalletActivity.class);
        startActivity(intent);
        finish();
    }
}
