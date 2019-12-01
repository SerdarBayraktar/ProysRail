package net.proys.proysrail;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Proys Yazılım on 24.07.2019.
 */

public class MySingleton {
    private static Context ctx;
    private static MySingleton mInstance;
    private RequestQueue requestQueue;
    private MySingleton(Context context){
        ctx = context;
        requestQueue = getRequestQueue();
    }
    public static synchronized MySingleton getInstance(Context context){
        return new MySingleton(context);
    }
    private RequestQueue getRequestQueue(){return Volley.newRequestQueue(ctx);}
    public <T> void addToRequestQue(Request<T> request){
        requestQueue.add(request);
    }

}
