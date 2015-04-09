package com.djzass.medipoint;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.djzass.medipoint.entity.Appointment;

/**
 * Created by Zillion Govin on 4/4/2015.
 */

public class ViewAppointmentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointment);

        Bundle b = getIntent().getExtras();
        AppointmentDummy app = b.getParcelable("appObj");

        TextView text = (TextView) findViewById(R.id.viewSpecialty);
        text.setText(app.getName());
        TextView text2 = (TextView) findViewById(R.id.viewService);
        text2.setText(app.getStatus());
        TextView text3 = (TextView) findViewById(R.id.viewCountry);
        text3.setText(app.getCountry());
        TextView text4 = (TextView) findViewById(R.id.viewDate);
        text4.setText(app.getDateString());
        TextView text5 = (TextView) findViewById(R.id.viewTime);
        text5.setText(app.getTimeString());
        TextView text6 = (TextView) findViewById(R.id.viewLocation);
        text6.setText(app.getClinic());
    }


    public void ViewApptEdit(View view)
    {
        //Button edit
    }

    public void ViewApptDelete(View view)
    {
        //Button delete
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void cancelAppointment(){
        Appointment appointment = new Appointment();
        AlarmSetter malarm = new AlarmSetter();
        malarm.cancelAlarm(this,appointment);
    }
}
