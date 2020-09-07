package com.shawinfosolutions.paintvisualizer;

import com.android.volley.Response;

public interface IObserverAsyncTask {

    void observer(String identifier, Response response);
    void observer(String response);

}
