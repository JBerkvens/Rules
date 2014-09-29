package com.moridrin.rulesofjeroen.sql;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

/**
 * Author: jeroen
 * Date:   7/13/14
 */
public class Login extends AsyncTask<String, Void, Boolean> {

    protected Boolean doInBackground(String... args) {
        System.out.println("Main");
        boolean returner = false;
        try {
            String params = "?Email=" + args[0] + "&Password=" + args[1];
            String link = "http://moridrin.com/android/WeightPlotter/login.php" + params;
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI(link));
            HttpResponse response = client.execute(request);
            BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String result;
            if ((result = in.readLine()) != null) {
                returner = result.equals("true");
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returner;
    }
}
