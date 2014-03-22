package me.tatarka.sres;

/**
 * Created by evan on 3/22/14.
 */
public interface ThreadHandler {
    public boolean isOnMainThread();
    public void postToMainThread(Runnable runnable);
}
