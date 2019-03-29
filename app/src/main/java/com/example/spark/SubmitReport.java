package com.example.spark;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SubmitReport.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SubmitReport#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SubmitReport extends Fragment implements AdapterView.OnItemSelectedListener {

//    GoogleMap googleMap;
//    MapView mapView;


    String locationRe="Testing location";
    View view;
    String methodOfReport;
    CardView submit;
    EditText commit;
    String userID="12";


    CardView button;
    int PLACE_PICKER_REQUEST=1;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SubmitReport() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SubmitReport.
     */
    // TODO: Rename and change types and number of parameters
    public static SubmitReport newInstance(String param1, String param2) {
        SubmitReport fragment = new SubmitReport();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_submit_report, container, false);

        Spinner spinner=view.findViewById(R.id.incidentMe);
        ArrayAdapter<CharSequence> charSequenceArrayAdapter=ArrayAdapter.createFromResource(getContext(),R.array.reportType,android.R.layout.simple_spinner_item);
        charSequenceArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(charSequenceArrayAdapter);
        spinner.setOnItemSelectedListener(this);


        button=(CardView) view.findViewById(R.id.location);
        submit=(CardView) view.findViewById(R.id.submitR);
        commit=(EditText) view.findViewById(R.id.cmnt);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder=new PlacePicker.IntentBuilder();

                Intent intent;
                try {
                    intent=builder.build((Activity) getContext());
                    startActivityForResult(intent,PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitReport();
            }
        });

        return  view;
    }

    private void submitReport() {
        String location=locationRe;
        String methodof=methodOfReport;
        String comment=commit.getText().toString();
        String userid=userID;

        String method="submitReport";
        BackgroundTask backgroundTask=new BackgroundTask(getContext());
        backgroundTask.execute(method,location,methodof,comment,userid);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode==PLACE_PICKER_REQUEST){
            if(resultCode== Activity.RESULT_OK){
                Place place=PlacePicker.getPlace(data,getContext());
                String address=String.format("Place : %s",place.getAddress());
                Log.d("hi",address);
            }
        }
    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        mapView=(MapView) view.findViewById(R.id.map);
//
//        if(mapView != null){
//            mapView.onCreate(null);
//            mapView.onResume();
//            mapView.getMapAsync(this);
//        }
//    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String method=parent.getItemAtPosition(position).toString();
        if(method.equals("Power Breakedown")){
            methodOfReport="Power Breakedown";

        }else if(method.equals("Maintainence")){
            methodOfReport="Maintainence";
        }else if(method.equals("Request new Connection")){
            methodOfReport="Request new Connection";
        }else if(method.equals("Request exist Connection")){
            methodOfReport="Request exist Connection";
        }else if(method.equals("Complains")){
            methodOfReport="Complains";
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        MapsInitializer.initialize(getContext());
//
//        this.googleMap=googleMap;
//        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
