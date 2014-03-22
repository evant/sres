package me.tatarka.sres;

/**
 * Created by evan on 3/22/14.
 */
public class ThreadHandlerProvider {
    private static ThreadHandler defaultThreadHandler;

    public static void setDefault(ThreadHandler threadHandler) {
        defaultThreadHandler = threadHandler;
    }

    public static ThreadHandler getDefault() {
        if (defaultThreadHandler == null) {
            Class<ThreadHandler> handlerClass;
            try {
                handlerClass = (Class<ThreadHandler>) Class.forName("me.tatarka.sres.ThreadHandlerImpl");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            try {
                setDefault(handlerClass.newInstance());
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return defaultThreadHandler;
    }
}
