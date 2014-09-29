package com.moridrin.rulesofjeroen.sql;

import android.os.AsyncTask;

import com.moridrin.rulesofjeroen.components.Rule;
import com.moridrin.rulesofjeroen.components.RuleList;

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
public class GetWeights extends AsyncTask<String, Void, RuleList> {

    protected RuleList doInBackground(String... args) {
        RuleList returner = new RuleList.Array();
        try {
            String params = "?User=" + args[0];
            String link = "http://moridrin.com/android/RulesOfJeroen/getRules.php" + params;
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI(link));
            HttpResponse response = client.execute(request);
            BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String result;
            if ((result = in.readLine()) != null) {
                String[] lines = result.split("</br>");
                for (String line : lines) {
                    String[] component = line.split(";");
                    returner.add(new Rule(component[0], component[1], Integer.parseInt(component[2])));
                }
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returner;
    }
}
