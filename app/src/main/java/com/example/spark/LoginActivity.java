package com.example.spark;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements Login.OnFragmentInteractionListener, Register.OnFragmentInteractionListener{


    Button btnLogin;
    EditText txtUsername, txtPassword;



    UserSessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerLogin,new Login()).commit();

        session=new UserSessionManager(getApplicationContext());


        Toast.makeText(getApplicationContext(), "Hey, Login first...", Toast.LENGTH_LONG).show();



    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void login (View view){
        txtUsername=(EditText) findViewById(R.id.uname);
        txtPassword=(EditText) findViewById(R.id.pword);

        String username=txtUsername.getText().toString();
        String password=txtPassword.getText().toString();

        if(username.trim().length()>0 &&password.trim().length()>0){
            session.createUserLoginSession("Pinsara","pinsara@gmail.com");
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(getApplicationContext(), "Please Enter Username and Password", Toast.LENGTH_LONG).show();
        }
    }

    public void register (View view){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerLogin,new Register()).commit();
    }


}
