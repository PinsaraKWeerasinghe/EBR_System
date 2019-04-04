package com.example.spark;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,SubmitReport.OnFragmentInteractionListener,History.OnFragmentInteractionListener,Help.OnFragmentInteractionListener, Register.OnFragmentInteractionListener, Login.OnFragmentInteractionListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    UserSessionManager session;
    Button btnLogout;
    static String userID;
    static String emailID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session=new UserSessionManager(getApplicationContext());

        //btnLogout=(Button)findViewById(R.id.)
        if(session.checkLogin()){
            finish();
        }



        HashMap<String,String> user=session.getUserDetails();
        String uname=user.get(UserSessionManager.KEY_NAME);
        String uemail=user.get(UserSessionManager.KEY_EMAIL);

        userID=uname;
        emailID=uemail;

        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();



        if (savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new SubmitReport()).commit();
        }


    }



    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_submit:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new SubmitReport()).commit();
                break;
            case R.id.nav_history:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new History()).commit();
                break;
            case R.id.nav_help:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Help()).commit();
                break;
            case R.id.nav_logout:
                session.logoutUser();
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
