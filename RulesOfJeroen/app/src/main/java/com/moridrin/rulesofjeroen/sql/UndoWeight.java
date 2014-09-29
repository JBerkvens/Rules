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
public class UndoWeight extends AsyncTask<String, Void, String> {

    protected String doInBackground(String... args) {
        String returner;

        Date currentDate = new Date();
        Date getFromDate = new Date(currentDate.getTime() - (1000 * 60 * 5));
        String date = new SimpleDateFormat("yyyy-MM-dd").format(getFromDate);
        String time = new SimpleDateFormat("HH:mm:ss").format(getFromDate);
        String user = args[0];

        HttpClient client = new DefaultHttpClient();
        String params = "?Date=" + date + "&Time=" + time + "&User=" + user;
        String url = "http://moridrin.com/android/WeightPlotter/removeLastFrom.php/" + params;
        HttpPost post = new HttpPost(url);

        try {
            client.execute(post);
            returner = "Success!";
        } catch (IOException e) {
            returner = e.toString();
            e.printStackTrace();
        }
        return returner;
    }
}
