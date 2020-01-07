package vr.qiyi.com.foregroundservice;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

//this demo only demonstrate if one App itselt is forground or backgroud. not
public class MyApplication extends Application {
    private MyActivitiesLife myActivitiesLife = new MyActivitiesLife();
    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(myActivitiesLife);
    }
    private static class MyActivitiesLife implements ActivityLifecycleCallbacks{
        private final String TAG = MyApplication.class.getSimpleName();
        private static int resumed;
        private static int paused;
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        }
        @Override
        public void onActivityStarted(Activity activity) {
        }
        @Override
        public void onActivityResumed(Activity activity) {
            Log.d(TAG, "onActivityResumed [ " + activity.getPackageName() + " ] " + activity.toString());
            ++resumed;
        }
        @Override
        public void onActivityPaused(Activity activity) {
            Log.d(TAG, "onActivityPaused [ " + activity.getPackageName() + " ] " + activity.toString());
            ++paused;
        }
        @Override
        public void onActivityStopped(Activity activity) {
        }
        @Override
        public void onActivityDestroyed(Activity activity) {
        }
        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        }
        /**
         * 判断应用是否在前台
         * @return
         */
        public static boolean isAppForeground() {
            return resumed > paused;
        }
    }
}
