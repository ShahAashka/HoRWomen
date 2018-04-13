package com.horwomen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
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
import java.util.Collections;

import static android.content.Context.MODE_PRIVATE;

public class BackgroundTask extends AsyncTask<String,Void,String> {

    private static final String MYPREF = "myRef";
    Context ctx;

    Session session;



    SharedPreferences pref;
    SharedPreferences.Editor editor ;
    StringBuffer response = null;

    public BackgroundTask(Context ctx) {
        this.ctx = ctx;

        pref = ctx.getSharedPreferences(LoginActivity.MYPREF,MODE_PRIVATE);

        session = new Session(ctx);
    }

    @Override
    protected void onPreExecute() {


    }

    @Override
    protected void onPostExecute(String result) {

         Toast.makeText(ctx, result, Toast.LENGTH_SHORT).show();
     }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);

    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected String doInBackground(String... strings) {

        // Session Manager

        // Toast.makeText(ctx, "Enter to doInBackground", Toast.LENGTH_SHORT).show();

        String reg_url,login_url;

        reg_url = "http://bombaybs.com/register.php";
        login_url = "http://bombaybs.com/login.php";

        String str = strings[0];
        String reg_id,user_id,user_pass;

        editor = pref.edit();

        if(str.equals("register"))
        {

            try {

                reg_id = strings[1];
                user_id = strings[2];
                user_pass = strings[3];
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String data = URLEncoder.encode("reg_id","UTF-8") + "=" + URLEncoder.encode(reg_id,"UTF-8")+"&"+
                        URLEncoder.encode("user_id","UTF-8") + "=" + URLEncoder.encode(user_id,"UTF-8")+"&"+
                        URLEncoder.encode("user_pass","UTF-8") + "=" + URLEncoder.encode(user_pass,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream is = httpURLConnection.getInputStream();
                is.close();



                return "Successfully registered";


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else if(str.equals("login"))
        {
            try {


                user_id = strings[1];
                user_pass = strings[2];


                URL url = new URL(login_url);

                JSONObject json_object = new JSONObject();
                 json_object.put("user_id",user_id);
                json_object.put("password",user_pass);

                String message = json_object.toString();

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                OutputStream out = new BufferedOutputStream(httpURLConnection.getOutputStream());
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
                writer.write(json_object.toString());
                writer.close();
                out.close();



                BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String inputLine;
                response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                pref.edit().putString("reply",response.toString()).apply();


                if(pref.getString("reply","sry").equals("UserExists"))
                {
                    session.createLoginSession(user_id);
                    pref.edit().putString("result", "Exist").apply();
                    pref.edit().apply();
                }
                else
                {
                    pref.edit().putString("result", "Not Exist").apply();
                    pref.edit().apply();
                    pref.edit().commit();

                }

                in.close();
                return response.toString();

                /*httpURLConnection.setFixedLengthStreamingMode(message.getBytes().length);

                //make some HTTP header nicety
                httpURLConnection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
                httpURLConnection.setRequestProperty("X-Requested-With", "XMLHttpRequest");


                httpURLConnection.connect();

                OutputStream os;//= httpURLConnection.getOutputStream();
                os = new BufferedOutputStream(httpURLConnection.getOutputStream());
                os.write(message.getBytes());
                os.flush();
*/
                //BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));

                /*String data = URLEncoder.encode("user_id","UTF-8") + "=" + URLEncoder.encode(user_id,"UTF-8")+"&"+
                        URLEncoder.encode("user_pass","UTF-8") + "=" + URLEncoder.encode(user_pass,"UTF-8");*/

            /*    bufferedWriter.write(message);
                bufferedWriter.flush();
                bufferedWriter.close();*/
  //              os.close();

                /*InputStream is = httpURLConnection.getInputStream();
                BufferedReader bufferredReader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"));
                String response ="";
                String line;

                while((line = bufferredReader.readLine()) != null)
                {
                    response+= line;
                }
                bufferredReader.close();
                is.close();
                httpURLConnection.disconnect();
                return response;

*/

            }
                catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }


        }

        return null;
    }
}
