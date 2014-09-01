package slidenerd.vivz.experiments;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class TestNotification extends ActionBarActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_notification);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.test_im, menu);
		return true;
	}

	@SuppressLint("NewApi")
	public void sendBasicNotification(View view) {
		Notification notification = new Notification.Builder(this)
				.setContentTitle("Basic Notification")
				.setContentText("Basic Notification, used earlier")
				.setSmallIcon(R.drawable.ic_launcher).build();
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		NotificationManager notificationManager = getNotificationManager();
		notificationManager.notify(0, notification);
	}

	@SuppressLint("NewApi")
	public void sendBigTextStyleNotification(View view) {
		String msgText = "Jeally Bean Notification example!! "
				+ "where you will see three different kind of notification. "
				+ "you can even put the very long string here.";

		NotificationManager notificationManager = getNotificationManager();
		PendingIntent pi = getPendingIntent();
		Builder builder = new Notification.Builder(this);
		builder.setContentTitle("Big text Notofication")
				.setContentText("Big text Notification")
				.setSmallIcon(R.drawable.ic_launcher).setAutoCancel(true)
				.setPriority(Notification.PRIORITY_HIGH)
				.addAction(R.drawable.ic_launcher, "show activity", pi);
		Notification notification = new Notification.BigTextStyle(builder)
				.bigText(msgText).build();

		notificationManager.notify(0, notification);
	}

	@SuppressLint("NewApi")
	public void sendBigPictureStyleNotification(View view) {
		PendingIntent pi = getPendingIntent();
		Builder builder = new Notification.Builder(this);
		builder.setContentTitle("BP notification")
				// Notification title
				.setContentText("BigPicutre notification")
				// you can put subject line.
				.setSmallIcon(R.drawable.ic_launcher)
				// Set your notification icon here.
				.addAction(R.drawable.ic_launcher, "show activity", pi)
				.addAction(
						R.drawable.ic_launcher,
						"Share",
						PendingIntent.getActivity(getApplicationContext(), 0,
								getIntent(), 0, null));

		// Now create the Big picture notification.
		Notification notification = new Notification.BigPictureStyle(builder)
				.bigPicture(
						BitmapFactory.decodeResource(getResources(),
								R.drawable.ic_launcher)).build();
		// Put the auto cancel notification flag
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		NotificationManager notificationManager = getNotificationManager();
		notificationManager.notify(0, notification);
	}

	@SuppressLint("NewApi")
	public void sendInboxStyleNotification(View view) {
		PendingIntent pi = getPendingIntent();
		Builder builder = new Notification.Builder(this)
				.setContentTitle("IS Notification")
				.setContentText("Inbox Style notification!!")
				.setSmallIcon(R.drawable.ic_launcher)
				.addAction(R.drawable.ic_launcher, "show activity", pi);

		Notification notification = new Notification.InboxStyle(builder)
				.addLine("First message").addLine("Second message")
				.addLine("Thrid message").addLine("Fourth Message")
				.setSummaryText("+2 more").build();
		// Put the auto cancel notification flag
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		NotificationManager notificationManager = getNotificationManager();
		notificationManager.notify(0, notification);
	}

	public PendingIntent getPendingIntent() {
		return PendingIntent.getActivity(this, 0, new Intent(this,
				HandleNotificationActivity.class), 0);
	}

	public NotificationManager getNotificationManager() {
		return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	}
}
