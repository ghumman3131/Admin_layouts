package com.example.karam.admin_layouts;

/**
 * Created by karam on 19-04-17.
 */


import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by ghumman on 2/16/2017.
 */

public class AppController  {

    private RequestQueue mRequestQueue;

    private Context c;


    private static AppController mInstance;

    public AppController(Context c)
    {
        mInstance = this;
        this.c = c;

    }

    public static synchronized AppController getInstance()
    {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null)
        {
            mRequestQueue = Volley.newRequestQueue(c);
        }

        return mRequestQueue;
    }



    public  void addToRequestQueue(Request req) {

        getRequestQueue().add(req);
    }


}

