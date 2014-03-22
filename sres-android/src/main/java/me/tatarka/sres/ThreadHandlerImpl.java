package me.tatarka.sres;

import android.os.Handler;
import android.os.Looper;


/**
 * Created by evan on 3/22/14.
 */
public class ThreadHandlerImpl implements ThreadHandler {
    private Handler handler;

    @Override
    public boolean isOnMainThread() {
        return Thread.currentThread() == Looper.getMainLooper().getThread();
    }

    @Override
    public void postToMainThread(Runnable runnable) {
        if (handler == null) handler = new Handler(Looper.getMainLooper());
        handler.post(runnable);
    }
}
