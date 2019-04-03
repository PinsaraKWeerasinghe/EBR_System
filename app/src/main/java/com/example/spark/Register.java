package com.example.spark;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Register.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Register#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Register extends Fragment {

    private CardView cardView2;
    private CardView cardView;
    private EditText userName;
    private EditText firstName;
    private EditText lastName;
    private EditText accNo;
    private EditText mobileNo;
    private EditText emailadd;
    private EditText pward;
    private EditText copword;
    private EditText noadd;
    private EditText streetadd;
    private EditText cityadd;
    private EditText zipadd;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Register() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Register.
     */
    // TODO: Rename and change types and number of parameters
    public static Register newInstance(String param1, String param2) {
        Register fragment = new Register();
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
        final View view= inflater.inflate(R.layout.fragment_register, container, false);

        cardView2=(CardView) view.findViewById(R.id.btncancel);
        cardView = (CardView) view.findViewById(R.id.btnReg);
        userName=(EditText)view.findViewById(R.id.userName);
        firstName=(EditText)view.findViewById(R.id.firstName);
        lastName=(EditText)view.findViewById(R.id.lastName);
        accNo=(EditText)view.findViewById(R.id.accNo);
        mobileNo=(EditText)view.findViewById(R.id.mobNo);
        emailadd=(EditText)view.findViewById(R.id.email);
        pward=(EditText)view.findViewById(R.id.pword);
        copword=(EditText)view.findViewById(R.id.conPword);
        noadd=(EditText)view.findViewById(R.id.No);
        streetadd=(EditText)view.findViewById(R.id.street);
        cityadd=(EditText)view.findViewById(R.id.city);
        zipadd=(EditText)view.findViewById(R.id.zipno);


        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registration(view);

            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelReg(view);
            }
        });

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

    public void registration (View view){

//        String username=userName.getText().toString();
//        String password=passWord.getText().toString();
        String username=userName.getText().toString();
        String firstname=firstName.getText().toString();
        String lastname=lastName.getText().toString();
        String accno=accNo.getText().toString();
        String mobno=mobileNo.getText().toString();
        String email=emailadd.getText().toString();
        String pword=pward.getText().toString();
        String cpword=copword.getText().toString();
        String no=noadd.getText().toString();
        String street=streetadd.getText().toString();
        String city=cityadd.getText().toString();
        String zip=zipadd.getText().toString();

        if((username.length()>0)&(firstname.length()>0)&(accno.length()>0)&(pward.length()>0)&(copword.length()>0)){
            Log.d("www", String.valueOf((username!=null)));
            if (pword.equals(cpword)){
                String method="register";
                BackgroundTask backgroundTask=new BackgroundTask(getContext());
                backgroundTask.execute(method,username,firstname,lastname,accno,mobno,email,pword,cpword,no,street,city,zip);
            }
            else{
                Toast.makeText(getContext(), "Enter Password Correctly!", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(getContext(), "Do not Keep Fields Empty!", Toast.LENGTH_LONG).show();
        }



    }

    public void cancelReg(View view){
        Intent intent=new Intent(getContext(),MainActivity.class);
        getContext().startActivity(intent);
    }
}
