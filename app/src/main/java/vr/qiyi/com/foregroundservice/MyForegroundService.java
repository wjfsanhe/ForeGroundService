package vr.qiyi.com.foregroundservice;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by ngudream on 2017/2/26.
 * 前台服务优先级比较高，在系统内存不足的情况下，不会被系统杀死
 */
public class MyForegroundService extends Service {
    private final static int FOREGROUND_SERVICE_ID = 100;
    @Override
    public void onCreate() {
        super.onCreate();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyForegroundService", "start Service ....");
        startForeground(FOREGROUND_SERVICE_ID, createNotification());//启动前台服务，在通知栏显示一个消息
        return super.onStartCommand(intent, flags, startId);
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);//停止前台服务并且移除通知栏
    }
    /**
     * 构建通知栏消息
     * @return
     */
    private Notification createNotification() {
        Notification.Builder builder = new Notification.Builder(this.getApplicationContext()); //获取一个Notification构造器
        Intent newIntent = new Intent(this, MainActivity.class);
        builder.setContentIntent(PendingIntent.getActivity(this, 0, newIntent, 0))
                .setContentTitle("前台 service 标题")
                .setContentText("前台 service 内容")
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setSmallIcon(R.mipmap.ic_launcher);
        return builder.build();
    }
}
