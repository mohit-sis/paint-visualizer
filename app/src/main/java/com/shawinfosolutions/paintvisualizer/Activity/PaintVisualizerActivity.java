package com.shawinfosolutions.paintvisualizer.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.shawinfosolutions.paintvisualizer.R;

import androidx.annotation.Nullable;

public class PaintVisualizerActivity extends Activity {
    private LinearLayout selectColorLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visualizer_select_wall);
        selectColorLayout=findViewById(R.id.selectColorLayout);
        selectColorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PaintVisualizerActivity.this, SelectColorFromPalletActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will

        switch (item.getItemId()) {
            case android.R.id.home:

                Intent intent = new Intent(PaintVisualizerActivity.this, VisualizerActivity.class);
                startActivity(intent);
                finish();

                //  finish();
                break;

        }
        return (super.onOptionsItemSelected(item));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(PaintVisualizerActivity.this, VisualizerActivity.class);
        startActivity(intent);
        finish();
    }
}
