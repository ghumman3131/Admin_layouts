package com.example.karam.admin_layouts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class splash_admin_layout extends AppCompatActivity {

    private EditText mobile_no_id, password_id;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_admin_layout);
        Button login = (Button) findViewById(R.id.login_button);

        mobile_no_id = (EditText) findViewById(R.id.mobile_no_id);
        password_id = (EditText) findViewById(R.id.password_id);

        final View.OnClickListener onbtn_click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.login_button) {

                    login();


                }


            }
        };

        login.setOnClickListener(onbtn_click);
    }




    public void login()
    {
        String mobile = mobile_no_id.getText().toString();
        String password = password_id.getText().toString();

        JSONObject job = new JSONObject();

        try {
            job.put("mobile_key", mobile);
            job.put("password_key" , password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jobreq = new JsonObjectRequest("http://192.168.0.17/splash_admin.php", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    if(response.getString("key").equals("done"))
                    {
                        Toast.makeText(splash_admin_layout.this, " done ", Toast.LENGTH_SHORT).show();

                        SharedPreferences.Editor sp = getSharedPreferences("admin_info",MODE_PRIVATE).edit();

                        sp.putString("admin_id",response.getString("admin_id"));

                        sp.commit();

                        Intent i = new Intent(splash_admin_layout.this ,MainActivity.class);

                        startActivity(i);
                        finish();
                    }

                    else {
                        Toast.makeText(splash_admin_layout.this, "error", Toast.LENGTH_SHORT).show();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                System.out.println(error);

            }
        });

        jobreq.setRetryPolicy(new DefaultRetryPolicy(20000, 2, 2));

        AppController app = new AppController(splash_admin_layout.this);

        app.addToRequestQueue(jobreq);
    }
}


