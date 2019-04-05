package com.example.spark;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundTask extends AsyncTask<String,Void,String> {

    Context context;
    UserSessionManager session;
    String uname;
    String pword;


    BackgroundTask(Context context){
        this.context=context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... voids) {

        String method=voids[0];
        if(method.equals("register")){
            String reg_url="http://10.0.2.2:8080/spark/mobileapp/userreg.php";
            String user_name=voids[1];
            String first_name=voids[2];
            String last_name=voids[3];
            String acc_no=voids[4];
            String mob_no=voids[5];
            String email=voids[6];
            String pass_word=voids[7];
            String con_pass_word=voids[8];
            String no=voids[9];
            String street=voids[10];
            String city=voids[11];
            String zip=voids[12];

            try {
                URL url=new URL(reg_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data = URLEncoder.encode("userName","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"+
                        URLEncoder.encode("firstName","UTF-8")+"="+URLEncoder.encode(first_name,"UTF-8")+"&"+
                        URLEncoder.encode("lastName","UTF-8")+"="+URLEncoder.encode(last_name,"UTF-8")+"&"+
                        URLEncoder.encode("accNo","UTF-8")+"="+URLEncoder.encode(acc_no,"UTF-8")+"&"+
                        URLEncoder.encode("mobNo","UTF-8")+"="+URLEncoder.encode(mob_no,"UTF-8")+"&"+
                        URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"+
                        URLEncoder.encode("pWord","UTF-8")+"="+URLEncoder.encode(pass_word,"UTF-8")+"&"+
                        URLEncoder.encode("cpWord","UTF-8")+"="+URLEncoder.encode(con_pass_word,"UTF-8")+"&"+
                        URLEncoder.encode("no","UTF-8")+"="+URLEncoder.encode(no,"UTF-8")+"&"+
                        URLEncoder.encode("street","UTF-8")+"="+URLEncoder.encode(street,"UTF-8")+"&"+
                        URLEncoder.encode("city","UTF-8")+"="+URLEncoder.encode(city,"UTF-8")+"&"+
                        URLEncoder.encode("zip","UTF-8")+"="+URLEncoder.encode(zip,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream=httpURLConnection.getInputStream();
                inputStream.close();
                Intent intent=new Intent(context,MainActivity.class);
                context.startActivity(intent);

                return "Registration Successfully";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(method.equals("submitReport")){
            String reg_url="http://10.0.2.2:8080/spark/mobileapp/reportsubmit.php";
            String location=voids[1];
            String methodofReport=voids[2];
            String comment=voids[3];
            String userID=voids[4];
            String lat=voids[5];
            String lng=voids[6];
            String image=voids[7];

            try {
                URL url=new URL(reg_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data = URLEncoder.encode("location","UTF-8")+"="+URLEncoder.encode(location,"UTF-8")+"&"+
                        URLEncoder.encode("methodofReport","UTF-8")+"="+URLEncoder.encode(methodofReport,"UTF-8")+"&"+
                        URLEncoder.encode("userID","UTF-8")+"="+URLEncoder.encode(userID,"UTF-8")+"&"+
                        URLEncoder.encode("comment","UTF-8")+"="+URLEncoder.encode(comment,"UTF-8")+"&"+
                        URLEncoder.encode("lat","UTF-8")+"="+URLEncoder.encode(lat,"UTF-8")+"&"+
                        URLEncoder.encode("lng","UTF-8")+"="+URLEncoder.encode(lng,"UTF-8")+"&"+
                        URLEncoder.encode("encoded_string","UTF-8")+"="+URLEncoder.encode(image,"UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream=httpURLConnection.getInputStream();
                inputStream.close();
//                Intent intent=new Intent(context,MainActivity.class);
//                context.startActivity(intent);
                //Toast.makeText(context, "Report Submit Successfully", Toast.LENGTH_SHORT).show();
                return "Report Submit Success fully...";



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Intent intent=new Intent(context,MainActivity.class);
            context.startActivity(intent);
        }else if(method.equals("loginCheck")){
            String reg_url="http://10.0.2.2:8080/spark/mobileapp/login.php";
            uname=voids[1];
            pword=voids[2];


            try {
                URL url=new URL(reg_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data = URLEncoder.encode("uname","UTF-8")+"="+URLEncoder.encode(uname,"UTF-8")+"&"+
                        URLEncoder.encode("pword","UTF-8")+"="+URLEncoder.encode(pword,"UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer buffer=new StringBuffer();

                String line="";
                while((line=reader.readLine())!=null){
                    buffer.append(line);
                }

                String key = null;

                try {
                    JSONObject jsonObject=new JSONObject(buffer.toString());
                    key=jsonObject.getString("key");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if ((key).equals("true")){
                    creates();
                }


                reader.close();
                inputStream.close();
//                Intent intent=new Intent(context,MainActivity.class);
//                context.startActivity(intent);
                return "";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(method.equals("history")){
            String reg_url="http://10.0.2.2:8080/spark/mobileapp/History.php";
            uname=voids[1];

            try {
                URL url=new URL(reg_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data = URLEncoder.encode("uname","UTF-8")+"="+URLEncoder.encode(uname,"UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer buffer=new StringBuffer();

                String line="";
                while((line=reader.readLine())!=null){
                    buffer.append(line);
                }


                try {
                    JSONArray jsonArray=new JSONArray(buffer.toString());
                    Log.d("dd","dddddddddddddddddddddddddddddddddddd");
                    History.historyOb=jsonArray;
                } catch (JSONException e) {
                    e.printStackTrace();
                }


//                String key = null;
//                try {
//                    JSONArray jsonArray=new JSONArray(buffer.toString());
//                    JSONObject jsonObject=jsonArray.getJSONObject(0);
//                    key=jsonObject.getString("location");
//                    Log.d("ddddd",key);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }

                //history(key);


                reader.close();
                inputStream.close();
//                Intent intent=new Intent(context,MainActivity.class);
//                context.startActivity(intent);
                return "";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        if(result!=null){
            //Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
        }

    }
    void creates(){
        session=new UserSessionManager(context);
        session.createUserLoginSession("Pinsara","pinsara@gmail.com");
    }

//    void history(String key){
//        Log.d("fff","aaaa");
//
////        try {
////            JSONArray jsonArray=new JSONArray(key);
////            Log.d("fff",jsonArray.getString(0));
////            //JSONObject jsonObject=new JSONObject(buffer.toString());
////
////        } catch (JSONException e) {
////            e.printStackTrace();
////        }
//    }



}
