package com.pandoapps.utils;

import android.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

@SuppressLint("NewApi")
public class MessageManager {
	/**
	 * Error popUp message
	 * 
	 * @param act
	 *            Activity Screen
	 * @param title
	 *            Error title
	 * @param message
	 *            Error message
	 */

	public static boolean showErrorMessage(Activity act, String title,
			String message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(act);
		builder.setMessage(message).setTitle(title).setCancelable(true)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.dismiss();
					}
				});
		builder.show();
		return true;
	}

	public static void showToastMessage(Activity act, String message) {
		Toast toast = Toast.makeText(act, message, Toast.LENGTH_SHORT);
		toast.show();
	}

	/**
	 * popUp for not online users
	 * 
	 * @param act
	 *            Activity Screen
	 */
	public static void notOnlineUser(Activity act) {
		showErrorMessage(act, "Usuário Offline",
				"Você precisa estar online para acessar essa página");
	}

	public static void sendNotification(Context context, int id, String title,
			String message) {

		PendingIntent notifyPIntent = PendingIntent.getActivity(context, 0,
				new Intent(), 0);

		Notification.Builder builder = new Notification.Builder(context);
		builder.setContentTitle(title);
		builder.setContentText(message);
		builder.setSmallIcon(R.drawable.ic_notification_overlay);
		builder.setAutoCancel(true);
		builder.setContentIntent(notifyPIntent);

		NotificationManager notiManager = (NotificationManager) context
				.getSystemService(android.app.Activity.NOTIFICATION_SERVICE);
		notiManager.notify(10001, builder.build());
	}
}
