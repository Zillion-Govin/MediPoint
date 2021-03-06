package com.android.notification;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsManager;

import java.util.ArrayList;

/**
 * Created by Z480 on 3/30/2015.
 */
public class SMS {
    private static final String SMS_SENT="Message Sent";
    private static final int MAX_SMS_MESSAGE_LENGTH =160;
    private static final short SMS_PORT=8901;
    private static final String SMS_DELIVERED ="Message Delivered";
    // just initiate this if want to call
        /*sendSms("+6582342891","testing",false);*/

    public void sendSms(Context context,String phonenumber,String message, boolean isBinary)
     {
     SmsManager manager = SmsManager.getDefault();

     PendingIntent piSend = PendingIntent.getBroadcast(context, 0, new Intent(SMS_SENT), 0);
     PendingIntent piDelivered = PendingIntent.getBroadcast(context, 0, new Intent(SMS_DELIVERED), 0);

     if(isBinary)
     {
        byte[] data = new byte[message.length()];

        for(int index=0; index<message.length() && index < MAX_SMS_MESSAGE_LENGTH; ++index)
        {
            data[index] = (byte)message.charAt(index);
        }

         manager.sendDataMessage(phonenumber, null, (short) SMS_PORT, data,piSend, piDelivered);
     }
     else
     {
        int length = message.length();

        if(length > MAX_SMS_MESSAGE_LENGTH)
         {

            ArrayList<String> messagelist = manager.divideMessage(message);

            manager.sendMultipartTextMessage(phonenumber, null, messagelist, null, null);
         }
         else
        {
            manager.sendTextMessage(phonenumber, null, message, piSend, piDelivered);
        }
     }
     }

    /*private AlarmManagerBroadcastReceiver receiver = new AlarmManagerBroadcastReceiver() {

    @Override
    public void onReceive(Context context, Intent intent) {
        String message = null;

        switch (getResultCode()) {
            case Activity.RESULT_OK:
                message = "Message sent!";
                break;
            case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                message = "Error. Message not sent.";
                break;
            case SmsManager.RESULT_ERROR_NO_SERVICE:
                message = "Error: No service.";
                break;
            case SmsManager.RESULT_ERROR_NULL_PDU:
                message = "Error: Null PDU.";
                break;
            case SmsManager.RESULT_ERROR_RADIO_OFF:
                message = "Error: Radio off.";
            break;
        }
    }
    };

    protected void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }*/

}
