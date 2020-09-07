package com.shawinfosolutions.paintvisualizer.Activity;

import android.app.Activity;
import android.os.Bundle;

import com.shawinfosolutions.paintvisualizer.R;

import androidx.annotation.Nullable;

public class FeedbackActvity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_layout);
    }
}
