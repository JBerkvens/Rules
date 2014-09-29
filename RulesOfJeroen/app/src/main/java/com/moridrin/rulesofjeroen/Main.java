package com.moridrin.rulesofjeroen;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.moridrin.rulesofjeroen.components.RuleList;
import com.moridrin.rulesofjeroen.login.LoginActivity;

import java.util.Collections;


public class Main extends Activity {

    private static String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.moridrin.rulesofjeroen", Service.MODE_PRIVATE);
        user = sharedPreferences.getString("username", "");
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
        if (!isLoggedIn) {
            Intent loginIntent = new Intent(this, LoginActivity.class);
            loginIntent.putExtra("Action", R.integer.action_login);
            startActivityForResult(loginIntent, R.integer.action_login);
        }
        RuleList rules = new RuleList.Array();
        Collections.sort(rules, new RuleList.RankComparator());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_logout: {
                Intent loginIntent = new Intent(this, LoginActivity.class);
                loginIntent.putExtra("Action", R.integer.action_logout);
                startActivityForResult(loginIntent, R.integer.action_logout);
                return super.onOptionsItemSelected(item);
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == R.integer.action_login) {
            if (resultCode == RESULT_OK) {
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.moridrin.rulesofjeroen", Service.MODE_PRIVATE);
                user = sharedPreferences.getString("username", "");
            } else {
                finish();
            }
        } else if (requestCode == R.integer.action_logout) {
            if (resultCode == R.integer.result_logout_success) {
                finish();
            }
        }
    }
}
