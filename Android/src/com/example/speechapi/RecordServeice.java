package com.example.speechapi;


import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.IBinder;

public class RecordServeice extends Service {

	private static final int _ID = 1;
	private static final String NAME = "setting";
	private LocalBinder localBinder;
	public static String path;
	private SharedPreferences preferences;
	private static final String KEY_BOOL = "check";
	private boolean isR;
	private Intent newIntent = null;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return localBinder;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		localBinder = new LocalBinder();
		preferences = getSharedPreferences(NAME, MODE_APPEND);
		isR = preferences.getBoolean(KEY_BOOL, false);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		newIntent = new Intent();
		newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);   
		newIntent.setClass(getApplicationContext(),NewActivity.class);  
		
		if (isR) {
			String Audio = intent.getStringExtra("Audio");
			if (Audio.equals("ON")) {
//				sendNotification("����¼��");
				startActivity(newIntent);
//				doRecord();

			}
//			else if (Audio.equals("OFF")) {
//				sendNotification("ֹͣ¼��");
////				doStop();
//			}
		}
		return super.onStartCommand(intent, flags, startId);
	}

	
	/**
	 * ���ͷ�����ǰ̨���е�֪ͨ
	 */
	private void sendNotification(String text) {

		Notification notification = new Notification();
		notification.icon = R.drawable.vanilla;
		notification.when = System.currentTimeMillis();

		Intent intent = new Intent(getApplicationContext(),
				MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		PendingIntent pendingIntent = PendingIntent.getActivity(
				getApplicationContext(), 0, intent,
				PendingIntent.FLAG_UPDATE_CURRENT);

		notification.setLatestEventInfo(getApplicationContext(), "Vanilla¼������",
				text, pendingIntent);

		// ��������Ϊǰ̨״̬�������͡����ڽ��С�֪ͨ
		startForeground(_ID, notification);
	}

	class LocalBinder extends Binder {

		RecordServeice getServeice() {
			return RecordServeice.this;
		}
	}
}
