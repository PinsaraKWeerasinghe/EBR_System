package com.example.spark;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link History.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link History#newInstance} factory method to
 * create an instance of this fragment.
 */
public class History extends Fragment implements AdapterView.OnItemSelectedListener {
    String state;
    UserSessionManager session;
    static JSONArray historyOb;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public History() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment History.
     */
    // TODO: Rename and change types and number of parameters
    public static History newInstance(String param1, String param2) {
        History fragment = new History();
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
        View view= inflater.inflate(R.layout.fragment_history, container, false);

//        Spinner spinner=view.findViewById(R.id.state);
//        ArrayAdapter<CharSequence> charSequenceArrayAdapter=ArrayAdapter.createFromResource(getContext(),R.array.statetypes,android.R.layout.simple_spinner_item);
//        charSequenceArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(charSequenceArrayAdapter);
//        spinner.setOnItemSelectedListener(this);

        session=new UserSessionManager(getContext());
        //btnLogout=(Button)findViewById(R.id.)


        HashMap<String,String> user=session.getUserDetails();
        String uname=user.get(UserSessionManager.KEY_NAME);

        String method="history";
        BackgroundTask backgroundTask=new BackgroundTask(getContext());
        backgroundTask.execute(method,uname);
        TableLayout tableLayout=(TableLayout)view.findViewById(R.id.historytable);

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        if(historyOb!=null){
            for(int i=0;i<historyOb.length();i++){
                try {
                    JSONObject jsonObjects=historyOb.getJSONObject(i);

                    TableRow row=new TableRow(getContext());
                    row.setLayoutParams((new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT)));

                    TextView reportno=new TextView(getContext());
                    reportno.setText(jsonObjects.getString("incident_id"));
                    reportno.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,TableRow.LayoutParams.WRAP_CONTENT));
                    row.addView(reportno);

                    TextView location=new TextView(getContext());
                    location.setText(jsonObjects.getString("dates"));
                    location.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,TableRow.LayoutParams.WRAP_CONTENT));
                    row.addView(location);

                    TextView section=new TextView(getContext());
                    section.setText(jsonObjects.getString("insident_type"));
                    section.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,TableRow.LayoutParams.WRAP_CONTENT));
                    row.addView(section);

                    TextView state=new TextView(getContext());
                    state.setText(jsonObjects.getString("state"));
                    state.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,TableRow.LayoutParams.WRAP_CONTENT));
                    row.addView(state);

                    tableLayout.addView(row);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        return view;
    }

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
        if(method.equals("Open")){
            state = "Open";
            Toast.makeText(getContext(),state,Toast.LENGTH_SHORT).show();
        }else if(method.equals("Inprogress")){
            state="Inprogress";
            Toast.makeText(getContext(),state,Toast.LENGTH_SHORT).show();
        }else if(method.equals("Closed")) {
            state = "Closed";
            Toast.makeText(getContext(),state,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

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
