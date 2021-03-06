package com.djzass.mediapp.apptlistview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Deka on 4/4/2015.
 */
public class ViewAppointmentFragment extends Fragment {

    ArrayList<Appointment> appointments;
    public static ViewAppointmentFragment newInstance() {
        ViewAppointmentFragment fragment = new ViewAppointmentFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        appointments = new ArrayList<Appointment>();
        Calendar[] dateTimes = {new GregorianCalendar(1995, 8, 10, 10, 0), new GregorianCalendar(1995, 10, 9, 3, 2),
                new GregorianCalendar(1994, 10, 9, 11, 33), new GregorianCalendar(1993, 6, 7, 10, 2), new GregorianCalendar(1996, 10, 8, 9, 30),
                new GregorianCalendar(1995, 10, 9, 4, 2), new GregorianCalendar(1995, 10, 9, 2,4), new GregorianCalendar(1995, 10, 9, 4, 2),
                new GregorianCalendar(1995, 10, 9, 4, 5), new GregorianCalendar(1995, 10, 9 ,3 ,5)    };
        String[] apptName = { "General Consultation", "Wisdom Tooth Extraction", "Tooth filling", "Tumor Surgery", "Sore Throat",
                "Hemoteraphy", "Hearing Test", "Sinus Surgery", "Women Health's Consultatiton", "Audio Therapy" };
        String[] name = {"Alice", "Bob", "Cindy", "Daniel", "Ezra", "Farah", "George",
                "Hans", "Iris", "Jack", "Kelly"};
        String[] status = {"In Progress", "Pending", "Ongoing", "Cancelled", "Done"};
        String[] clinics = {"DjZass HealthCare Center", "Zjdass Medical Centre", "DassJz Clinic","JzDass Clinic Centre"};
        String[] country = {"Malaysia", "Singapore", "Thailand"};

        String s, cl, co;
        for (int i=0;i<10;i++){
            s = status[i % status.length].toUpperCase();
            cl = clinics[i % clinics.length];
            co = country[i % country.length];
            appointments.add(new Appointment(i, apptName[i], s, dateTimes[i], cl, co));
        }

        /*for (Appointment a: appointments){
            Toast.makeText(this, a.toString(), Toast.LENGTH_SHORT).show();
        }*/


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        ListView apptList = (ListView)view.findViewById(R.id.customListView);
        CustomAdapter apptAdapter = new CustomAdapter(getActivity(), appointments);
        apptList.setAdapter(apptAdapter);

        apptList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView <?> parent, View view, int position, long id) {
                Appointment app = (Appointment) parent.getAdapter().getItem(position);
                //Toast.makeText(getApplicationContext(), app.toString(), Toast.LENGTH_SHORT).show();
                Intent in = new Intent(getActivity().getApplicationContext(), ViewAppointment.class);
                in.putExtra("appObj", app);
                startActivity(in);
                /*Toast.makeText(getApplicationContext(),
                        "Click ListItem Number " + position, Toast.LENGTH_SHORT)
                        .show();*/
            }
        });

        return view;
    }
}
