package com.djzass.medipoint;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.djzass.medipoint.entity.Appointment;
import com.djzass.medipoint.logic_manager.Container;

/**
 * Created by Zillion Govin on 4/4/2015.
 */

public class ViewAppointmentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointment);

        Bundle b = getIntent().getExtras();
        Appointment app = b.getParcelable("appObj");

        TextView specialtyName = (TextView) findViewById(R.id.viewSpecialty);
        specialtyName.setText(Container.getSpecialtyManager().getSpecialtyNameByID(app.getId(), this));
        TextView serviceName = (TextView) findViewById(R.id.viewService);
        serviceName.setText(app.getStatus());
        TextView appointmentStatus = (TextView) findViewById(R.id.viewCountry);
        appointmentStatus.setText(app.getCountry());
        TextView appointmentDate = (TextView) findViewById(R.id.viewDate);
        appointmentDate.setText(app.getDateString());
        TextView appointmentTime = (TextView) findViewById(R.id.viewTime);
        appointmentTime.setText(app.getTimeString());
        TextView appointmentLocation = (TextView) findViewById(R.id.viewLocation);
        appointmentStatus.setText(app.getClinic());
        TextView doctorName = (TextView) findViewById(R.id.viewLocation);
        doctorName.setText(app.getClinic());
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


    public void cancelAppointment(){
        Appointment appointment = new Appointment();
        AlarmSetter malarm = new AlarmSetter();
        malarm.cancelAlarm(this,appointment);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_call:
                Intent editIntent= new Intent(getApplicationContext(),EditAppointmentActivity.class);
                startActivity(editIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
