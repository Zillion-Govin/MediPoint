package com.djzass.medipoint.boundary_ui;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.djzass.medipoint.R;
import com.djzass.medipoint.entity.Account;
import com.djzass.medipoint.entity.Appointment;
import com.djzass.medipoint.entity.Clinic;
import com.djzass.medipoint.entity.Doctor;
import com.djzass.medipoint.entity.Service;
import com.djzass.medipoint.entity.Specialty;
import com.djzass.medipoint.entity.Timeframe;
import com.djzass.medipoint.logic_manager.AlarmSetter;
import com.djzass.medipoint.logic_manager.Container;
import com.djzass.medipoint.logic_manager.SessionManager;

import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Zillion Govin on 17/3/2015.
 */
public class EditAppointmentActivity extends onDataPass implements AdapterView.OnItemSelectedListener, SelectionListener {

    //appointment atrribute selections
    /**
     * Clininc Id for the appointment being edited
     */
    int clinicId;

    /**
     * Patient ID is the ID of patient booking the appointment
     */
    int patientId;

    /**
     * ID of the doctor for the appointment being edited
     */
    int doctorId;

    /**
     * Store ID of the referring doctor if appointment is a referral
     */
    int referrerId;

    /**
     * date for the appointment being edited
     */
    Calendar apptDate = Calendar.getInstance();

    /**
     * service ID for the appointment being edited
     */
    int serviceId;

    /**
     * specialty ID for the appointment being edited
     */
    int specialtyId;

    /**
     * Slot duration for the appointment being edited
     */
    int duration;

    /**
     * Pre Appointment actions for the appointment being edited
     */
    String preAppointmentActions;
    /**
     * Timeframe actions for the appointment being edited
     */
    Timeframe timeframe;

    //Spinner
    Spinner specialtySpinnerCreate;
    Spinner countrySpinnerCreate;
    Spinner serviceSpinnerCreate;
    Spinner doctorSpinnerCreate;
    Spinner clinicSpinnerCreate;
    List<Specialty> specialities;
    Button confirmButton;
    Button cancelButton;
    Appointment app;
 
    /**
     * Called when the activity is starting. This is where most initialization is done: calling
     * setContentView(int) to inflate the activity's UI, using findViewById(int) to programmatically
     * interact with widgets in the UI.
     *
     * @param savedInstanceState  If the activity is being re-initialized after previously being shut
     *                           down then this Bundle contains the data it most recently supplied in
     *                           onSaveInstanceState(Bundle). Otherwise it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_appointment);
        Bundle b = getIntent().getExtras();
        app = b.getParcelable("appFromView");
        referrerId = app.getReferrerId();

        Button datepicker = (Button)findViewById(R.id.datepicker);
        apptDate = app.getDate();
        String[] month_str = new DateFormatSymbols().getMonths();
        datepicker.setText(apptDate.get(Calendar.DATE) + " " + month_str[apptDate.get(Calendar.MONTH)] + " " + apptDate.get(Calendar.YEAR));

        specialities = Container.getSpecialtyManager().getSpecialtys(this);
        specialtySpinnerCreate = (Spinner) findViewById(R.id.EditApptSpecialty);
        List<String> specialtyNames = new ArrayList<String>();
        for(Specialty s: specialities){
            specialtyNames.add(s.getName());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,specialtyNames);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.notifyDataSetChanged();
        specialtySpinnerCreate.setAdapter(dataAdapter);
        specialtySpinnerCreate.setSelection(dataAdapter.getPosition(Container.getSpecialtyManager().getSpecialtyNameBySpecialtyId(app.getSpecialtyId(),this)));
        specialtySpinnerCreate.setOnItemSelectedListener(this);

        //country spinner and array adapter
        countrySpinnerCreate = (Spinner) findViewById(R.id.EditApptCountries);
        countrySpinnerCreate.setOnItemSelectedListener(this);
        ArrayAdapter countryAdapter_create = ArrayAdapter.createFromResource(this, R.array.countries, android.R.layout.simple_spinner_dropdown_item);
        countryAdapter_create.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinnerCreate.setAdapter(countryAdapter_create);
        countrySpinnerCreate.setSelection(countryAdapter_create.getPosition(Container.getClinicManager().getCountryByClinicId(app.getClinicId(),this)));


        //service spinner
        serviceSpinnerCreate = (Spinner) findViewById(R.id.EditApptType);
        //doctor spinner
        doctorSpinnerCreate = (Spinner) findViewById(R.id.EditApptDoctors);
        //clinic location spinner
        clinicSpinnerCreate = (Spinner) findViewById(R.id.EditApptLocations);

        confirmButton = (Button)findViewById(R.id.ConfirmEditAppt);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                onConfirmEdit(arg0);
        }});

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

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

        //logout menu item selected
        else if(id==R.id.action_logout){
            SessionManager sessionManager = new SessionManager(this);
            sessionManager.deleteLoginSession();
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Bundle b = getIntent().getExtras();
        Appointment app = b.getParcelable("appFromView");


        /** pop up message
         TextView MyTime = (TextView) view;
         Toast.makeText(this, MyTime.getText() + " is selected", Toast.LENGTH_SHORT).show();
         **/
        switch (parent.getId()) {
            case R.id.EditApptSpecialty:
                String speciality = String.valueOf(specialtySpinnerCreate.getSelectedItem());
                int selection = 1;
                for (Specialty s : specialities) {
                    if (speciality.equals(s.getName())) {
                        selection = s.getId();
                    }
                }
                this.specialtyId = selection;

                List<Service> services = Container.getServiceManager().getServicesBySpecialtyID(selection, this);
                List<String> serviceNames = new ArrayList<String>();
                for (Service s : services) {
                    serviceNames.add(s.getName());
                }
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, serviceNames);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter.notifyDataSetChanged();
                serviceSpinnerCreate.setAdapter(dataAdapter);
                serviceSpinnerCreate.setSelection(dataAdapter.getPosition(Container.getServiceManager().getServiceNameByServiceID(app.getServiceId(), this)));
                serviceSpinnerCreate.setOnItemSelectedListener(this);
                this.serviceId = app.getServiceId();

                List<Doctor> doctors = Container.getDoctorManager().getDoctorsByClinicAndSpecialization(clinicId, specialtyId, this);
                List<String> doctorNames = new ArrayList<String>();
                for (Doctor d : doctors) {
                    doctorNames.add(d.getName());
                }
                ArrayAdapter<String> doctorDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, doctorNames);
                doctorDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                doctorDataAdapter.notifyDataSetChanged();
                doctorSpinnerCreate.setAdapter(doctorDataAdapter);
                doctorSpinnerCreate.setSelection(doctorDataAdapter.getPosition(Container.getDoctorManager().getDoctorNameByDoctorId(app.getDoctorId(), this)));
                doctorSpinnerCreate.setOnItemSelectedListener(this);

                break;

            case R.id.EditApptType:
                services = Container.getServiceManager().getServicesBySpecialtyID(specialtyId,this);
                String service = String.valueOf(serviceSpinnerCreate.getSelectedItem());
                for (Service s : services) {
                    if (service.equals(s.getName())) {
                        this.serviceId = s.getId();
                        this.preAppointmentActions = s.getPreAppointmentActions();
                        this.duration = s.getDuration();
                    }
                }

                break;

            case R.id.EditApptDoctors:
                doctors = Container.getDoctorManager().getDoctorsByClinicAndSpecialization(clinicId,specialtyId,this);
                String doctor = String.valueOf(doctorSpinnerCreate.getSelectedItem());
                for (Doctor d : doctors) {
                    if (doctor.equals(d.getName())) {
                        doctorId = d.getDoctorId();
                    }
                }

                break;


            case R.id.EditApptCountries:
                String country = String.valueOf(countrySpinnerCreate.getSelectedItem());

                List<Clinic> clinics = Container.getClinicManager().getClinicsByCountry(country, this);
                List<String> clinicNames = new ArrayList<String>();
                for (Clinic c : clinics) {
                    clinicNames.add(c.getName());
                }

                ArrayAdapter<String> clinicDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, clinicNames);
                clinicDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                clinicDataAdapter.notifyDataSetChanged();
                clinicSpinnerCreate.setAdapter(clinicDataAdapter);
                clinicSpinnerCreate.setSelection(clinicDataAdapter.getPosition(Container.getClinicManager().getClinicNameByClinicId(app.getClinicId(),this)));
                clinicSpinnerCreate.setOnItemSelectedListener(this);
                String clinic = String.valueOf(clinicSpinnerCreate.getSelectedItem());
                for (Clinic c : clinics) {
                    if (clinic.equals(c.getName())) {
                        clinicId = c.getId();
                    }
                }

                doctors = Container.getDoctorManager().getDoctorsByClinicAndSpecialization(clinicId,specialtyId, this);
                doctorNames = new ArrayList<String>();
                for (Doctor d : doctors) {
                    doctorNames.add(d.getName());
                }
                doctorDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, doctorNames);
                doctorDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                doctorDataAdapter.notifyDataSetChanged();
                doctorSpinnerCreate.setAdapter(doctorDataAdapter);
                doctorSpinnerCreate.setSelection(doctorDataAdapter.getPosition(Container.getDoctorManager().getDoctorNameByDoctorId(app.getDoctorId(), this)));
                doctorSpinnerCreate.setOnItemSelectedListener(this);

                break;


            case R.id.EditApptLocations:

                String clinic2 = String.valueOf(clinicSpinnerCreate.getSelectedItem());
                List<Clinic> clinics2 = Container.getClinicManager().getClinics(this);
                for (Clinic c : clinics2) {
                    if (clinic2.equals(c.getName())) {
                        clinicId = c.getId();
                    }
                }


                doctors = Container.getDoctorManager().getDoctorsByClinicAndSpecialization(clinicId,specialtyId,this);
                doctorNames = new ArrayList<String>();
                for (Doctor d : doctors) {
                    doctorNames.add(d.getName());
                }
                doctorDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, doctorNames);
                doctorDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                doctorDataAdapter.notifyDataSetChanged();
                doctorSpinnerCreate.setAdapter(doctorDataAdapter);
                doctorSpinnerCreate.setSelection(doctorDataAdapter.getPosition(Container.getDoctorManager().getDoctorNameByDoctorId(app.getDoctorId(), this)));
                doctorSpinnerCreate.setOnItemSelectedListener(this);

                break;
        }

        if(specialtyId==app.getSpecialtyId() && doctorId==app.getDoctorId() && clinicId==app.getClinicId()){
            Button timepicker = (Button)findViewById(R.id.timepickeredit);
            timeframe = app.getTimeframe();
            this.apptDate.set(Calendar.HOUR_OF_DAY,(this.timeframe.getStartTime()/2));
            this.apptDate.set(Calendar.MINUTE,30*(this.timeframe.getStartTime()%2));
            timepicker.setText(app.getTimeString());
        }
        else{
            resetTimePicker();
        }

    }

    @Override
    public void selectItem(int position) {
        Button btn = (Button) findViewById(R.id.timepickeredit);
        if(getTimePickerItems().get(position)!="N/A"){
            btn.setText(getTimePickerItems().get(position));
            this.timeframe = new Timeframe(getTimePickerItems().get(position));
        } else {
            resetTimePicker();
        }
    }

    public void resetTimePicker(){
        Button btn = (Button) findViewById(R.id.timepickeredit);
        btn.setText("TAP TO CHOOSE TIME");
        this.timeframe = new Timeframe(-1,-1);
    }



    public void editAppointment(){

        //calendar changed

        //time changed

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    //Button Methods
    public void onCancelEdit(View view){
        Toast.makeText(this, "Appointment edit cancelled", Toast.LENGTH_SHORT).show();
        Intent in = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(in);
    }

    public void onConfirmEdit(View view){
        //add to database
        Calendar currentDate = Calendar.getInstance();
        currentDate.add(Calendar.DATE, 1);


        if (this.apptDate.getTimeInMillis()==0){
            Toast.makeText(this, "Please select a date ", Toast.LENGTH_SHORT).show();
        } else if (this.timeframe.getStartTime()<0){
            Toast.makeText(this, "Please select a time. ", Toast.LENGTH_SHORT).show();
        } else {
            this.apptDate.set(Calendar.HOUR_OF_DAY,(this.timeframe.getStartTime()/2));
            this.apptDate.set(Calendar.MINUTE,30*(this.timeframe.getStartTime()%2));
            if (this.apptDate.compareTo(currentDate)<0){
                Toast.makeText(this, "You must book at least 24 hours in advance. ", Toast.LENGTH_SHORT).show();
            } else {
                //AccountManager accountManager = new AccountManager(this);
                SessionManager sessionMgr = new SessionManager(this);
                try {
                    patientId = (int)sessionMgr.getAccountId();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Appointment appointment = new Appointment(this.patientId, this.clinicId, this.specialtyId, this.serviceId, this.doctorId, referrerId,this.apptDate, this.timeframe, Container.getServiceManager().getServicePreappbyID(this.serviceId, this));
                appointment.setId(app.getId());
                long res = Container.getAppointmentManager().editAppointment(appointment, this);
                if (res == -1) {
                    //Toast.makeText(this,(String) "DoctorId " + doctorId, Toast.LENGTH_SHORT).show();
                    Toast.makeText(this,"Appointment creation failed", Toast.LENGTH_SHORT).show();

                } else {
                    AlarmSetter malarm = new AlarmSetter();
                    Account account = new Account();
                    try {
                        account = Container.getAccountManager().getAccountById(sessionMgr.getAccountId(), this);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    malarm.setAlarm(getApplicationContext(),appointment,account);
                    /*Notification notification = new Notification();
                    notification.buildNotification(this, "Appointment created.",appointment);*/
                    //if successful
                    Toast.makeText(this, "Appointment successfully edited", Toast.LENGTH_SHORT).show();
                    Intent goToMain = new Intent(this, MainActivity.class);
                    startActivity(goToMain);

                }
            }
        }

        //if not successful
        // Toast.makeText(this, "Failed to edit appointment, please try again", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart(){
        super.onStart();
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onStop(){
        super.onStop();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    public void showTimepicker(View v){
        FragmentManager manager = getFragmentManager();
        TimePickerFragment timepicker = new TimePickerFragment();
        timepicker.show(manager, "TimePicker");
    }

    /*
    public void showDatePicker(View v){
        FragmentManager manager = getFragmentManager();
        DatePickerFragment datepicker = new DatePickerFragment();
        datepicker.show(manager, "Datepicker");
    }
    */

    public void onDateButtonSelected(View v){
        int id = v.getId();
        Bundle bundle = new Bundle();
        bundle.putInt("VIEW_ID",id);
        FragmentManager manager = getFragmentManager();
        DatePickerFragment datepicker = new DatePickerFragment();
        datepicker.setArguments(bundle);
        datepicker.show(manager, "Datepicker");
    }

    @Override
    public void DatePickerFragmentToActivity(int date, int month, int year, Button button)
    {
        super.DatePickerFragmentToActivity(date,month,year,button);
        apptDate.set(year,month,date);
    }

    public void onTimeButtonSelected(View v){
        if (this.apptDate.getTimeInMillis()==0){
            Toast.makeText(this, "Please select a date ", Toast.LENGTH_SHORT).show();
            return;
        }
        FragmentManager manager = getFragmentManager();
        TimePickerFragment timepicker = new TimePickerFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(TimePickerFragment.DATA, getTimePickerItems());     // Require ArrayList
        bundle.putInt(TimePickerFragment.SELECTED, 0);
        timepicker.setArguments(bundle);
        timepicker.show(manager, "TimePicker");
    }

    private ArrayList<String> getTimePickerItems() {
        ArrayList<String> availableSlots = new ArrayList<String>();
        //Toast.makeText(this, this.date.getTime().toString(), Toast.LENGTH_SHORT).show();

        this.duration = Container.getServiceManager().getServiceDurationbyID(this.serviceId, this);
        List<Timeframe> temp = Container.getAppointmentManager().getAvailableTimeSlot(this.apptDate, this.patientId, this.doctorId, this.clinicId, 18, 42, duration, this);

        availableSlots.add("N/A");
        for (Timeframe t:temp){
            availableSlots.add(t.getTimeLine());
        }
        return availableSlots;
    }
}

