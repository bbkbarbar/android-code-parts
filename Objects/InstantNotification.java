

/*
 *
 * 	Version: 1.0
 * 	2011.09.17.
 * 
 * 	by.: Boór András
 */

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class InstantNotification {
	
	private static final int MY_NOTIFICATION_ID=1;
	
	private NotificationManager notificationManager;
	private Notification myNotification;
	public Activity myParent;
	int iconRes;
	
	
	/*
	 * 	Parameters:
	 * 		1.:	the activity what call that..
	 * 		2.: the icon of notification
	 * 		3.: Title
	 * 		4.: Text
	 * 		5.: Intent what to do when click the notification
	 */
	public NotificationProvider(Activity myParentActivity,
								int iconRes,
								String firstNotifString,
								//long TimeWhenInMillis
								String notificationTitle,
								String notificationText,
								Intent IntentToDo
								){
		
		myParent = myParentActivity;
		this.iconRes = iconRes;
		
		notificationManager =
				   (NotificationManager) myParent.getSystemService(
						   Context.NOTIFICATION_SERVICE
				   );
		
		myNotification = new Notification(
				   	iconRes,
				   	firstNotifString,
				    System.currentTimeMillis()
					);
		
		Context context = myParent.getApplicationContext();
		
		PendingIntent pendingIntent
	     = PendingIntent.getActivity(myParent,
	       0, IntentToDo,
	       Intent.FLAG_ACTIVITY_NEW_TASK);
		
		myNotification.defaults |= Notification.DEFAULT_SOUND;
		myNotification.flags |= Notification.FLAG_AUTO_CANCEL;
		myNotification.setLatestEventInfo(
				   context,
				   notificationTitle,
				   notificationText,
				   pendingIntent
		   );
		
		notificationManager.notify(MY_NOTIFICATION_ID, myNotification);
	}
	
}
