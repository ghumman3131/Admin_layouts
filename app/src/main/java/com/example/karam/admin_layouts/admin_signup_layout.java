package com.example.karam.admin_layouts;

import android.content.Intent;
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

public class admin_signup_layout extends AppCompatActivity {

    EditText Contact_no ;
    EditText username_id, Email_id ,password_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_signup_layout);

        Contact_no = (EditText) findViewById(R.id.Contact_no);

        username_id=(EditText) findViewById(R.id.username_id);
        Email_id=(EditText) findViewById(R.id.Email_id);
        password_id=(EditText) findViewById(R.id.password_id);


    }

    public void register(View v) {
        String username = username_id.getText().toString();

        String email= Email_id.getText().toString();

        String password = password_id.getText().toString();

        String contact = Contact_no.getText().toString();


        if (username.equals("")) {
            Toast.makeText(admin_signup_layout.this, "please enter username", Toast.LENGTH_SHORT).show();

            return;
        }

        if (email.equals("")) {
            Toast.makeText(admin_signup_layout.this, "please enter email", Toast.LENGTH_SHORT).show();

            return;
        }
        if (password.equals("")) {
            Toast.makeText(admin_signup_layout.this, "please enter password", Toast.LENGTH_SHORT).show();

            return;
        }
        if (contact.equals("")) {
            Toast.makeText(admin_signup_layout.this, "please enter contact_no", Toast.LENGTH_SHORT).show();

            return;
        }

        JSONObject job = new JSONObject();

        try {
            job.put("name_key" , username);
            job.put("mobile_key" , contact);
            job.put("pass_key", password);
            job.put("email_key" ,email);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println(job);

        JsonObjectRequest jobreq = new JsonObjectRequest("http://192.168.0.65/admin_signup.php", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    if(response.getString("key").equals("1"))
                    {
                        Toast.makeText(admin_signup_layout.this , "logged in" ,Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(admin_signup_layout.this , MainActivity.class);
                        startActivity(i);
                        finish();
                    }

                    else {

                        Toast.makeText(admin_signup_layout.this , "error" ,Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        jobreq.setRetryPolicy(new DefaultRetryPolicy(20000 , 2 , 2));

        AppController app = new AppController(admin_signup_layout.this);
        app.addToRequestQueue(jobreq);

    }

}