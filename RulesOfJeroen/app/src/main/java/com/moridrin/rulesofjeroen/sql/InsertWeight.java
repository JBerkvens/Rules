package com.moridrin.rulesofjeroen.sql;

import android.os.AsyncTask;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author: jeroen
 * Date:   7/13/14
 */
public class InsertWeight extends AsyncTask<String, Void, String> {

    protected String doInBackground(String... args) {
        String returner;

        String weight = args[0];
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
        String user = args[1];

        if (Double.parseDouble(weight) > 0.05) {
            HttpClient client = new DefaultHttpClient();
            String params = "?Weight=" + weight + "&Date=" + date + "&Time=" + time + "&User=" + user;
            HttpPost post = new HttpPost("http://moridrin.com/android/WeightPlotter/insertInto.php" + params);

            try {
                client.execute(post);
                returner = "Success!";
            } catch (IOException e) {
                returner = e.toString();
                e.printStackTrace();
            }
        } else {
            returner = "Weight must be > 0.05";
        }
        return returner;
    }
}
