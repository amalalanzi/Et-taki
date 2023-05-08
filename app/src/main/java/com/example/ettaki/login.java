package com.example.ettaki;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity implements View.OnClickListener{

    Button loginv;
    TextView forgotpasswordv, signupv;
    EditText emailv, passwordv;
    DBHelper DB;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(SharedPrefManager.getInstance(this).isLoggedin()){
            finish();
            startActivity(new Intent(this , MainActivity2.class));
        }

        loginv = (Button) findViewById(R.id.login);
        signupv = (TextView) findViewById(R.id.signupText);
        forgotpasswordv = (TextView) findViewById(R.id.forgetpassword);
        emailv = (EditText) findViewById(R.id.email);
        passwordv = (EditText) findViewById(R.id.password);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("فضلِا انتظر....");




        /* go to signup page */
        signupv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, signup.class);
                startActivity(intent);
            }
        });


        /* go to forget password page page */

        forgotpasswordv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, forgetpasswordActivity.class);
                startActivity(intent);
            }
        });


        loginv.setOnClickListener(this);
//        loginv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String email = emailv.getText().toString();
//                String pass = passwordv.getText().toString();
//
//                if(email.equals("")||pass.equals(""))
//                    Toast.makeText(login.this, " فضلًا أدخل جميع الحقول ", Toast.LENGTH_SHORT).show();
//                else{
//                    Boolean checkuserpass = DB.checkusernamepassword(email, pass);
//                    if(checkuserpass==true){
//                        Intent intent  = new Intent(getApplicationContext(), ettaki.class);
//                        startActivity(intent);
//                    }else{
//                        Toast.makeText(login.this, " كلمة المرور او البريد الإلكتروني غير صحيح ", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//
//
//            }
//        });


    }


    private void userLogin(){

         String email = emailv.getText().toString().trim();
         String password = passwordv.getText().toString().trim();

         progressDialog.show();
        if(!email.equals("") && !password.equals("")) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_LOGIN, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    try {
                        JSONObject obj = new JSONObject(response);
                        if (!obj.getBoolean("error")) {
                            SharedPrefManager.getInstance(getApplicationContext()).userLogin(
                                    obj.getInt("id"),
                                    obj.getString("email"),
                                    obj.getString("firstName"),
                                    obj.getString("lastName"),
                                    obj.getString("type"),
                                    obj.getString("gender")

                            );

                            startActivity(new Intent(getApplicationContext(), MainActivity2.class));

                        } else {
                            Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }


            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(login.this, error.toString().trim(), Toast.LENGTH_LONG).show();


                }
            }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("email", email);
                    params.put("password", password);
                    return params;

                }
            };
            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        }else {
            progressDialog.dismiss();

            Toast.makeText(getApplicationContext(), "جميع الحقول مطلوبة", Toast.LENGTH_LONG).show();


        }

//       progressDialog.show();
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_LOGIN,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        progressDialog.dismiss();
//
//                        try {
//                            JSONObject obj = new JSONObject(response);
//                            if(!obj.getBoolean("error")){
//                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(
//                                        obj.getInt("id"),
//                                        obj.getString("email"),
//                                        obj.getString("firstName"),
//                                        obj.getString("lastName"),
//                                        obj.getString("type"),
//                                        obj.getString("gender")
//
//                                );
//
//                                Toast.makeText(getApplicationContext(), "تم تسجيل الدخول بنجاح ", Toast.LENGTH_LONG).show();
//
//
//                             startActivity(new Intent(getApplicationContext(),MainActivity.class));
//
//                            }else {
//                                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
//
//                            }
//                        }
//
//                        catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                progressDialog.dismiss();
//                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
//
//
//            }
//        }
//        ){
//
//            @Nullable
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("email",email);
//                params.put("password", password);
//                return params;
//
//            }
//        };
//
//        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
//
//
  }


  public void test(String x){

      try {
                            JSONObject obj = new JSONObject(x);
                            if(!obj.getBoolean("error")){

//                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(
//                                        obj.getInt("id"),
//                                        obj.getString("email"),
//                                        obj.getString("firstName"),
//                                        obj.getString("lastName"),
//                                        obj.getString("type"),
//                                        obj.getString("gender")
//
//                                );

                                Toast.makeText(getApplicationContext(), "تم تسجيل الدخول بنجاح ", Toast.LENGTH_LONG).show();


                             startActivity(new Intent(getApplicationContext(),MainActivity2.class));

                            }else {
                                Toast.makeText(getApplicationContext(), x, Toast.LENGTH_LONG).show();

                            }
                        }

      catch (JSONException e) {
          e.printStackTrace();
          Toast.makeText(getApplicationContext(), x, Toast.LENGTH_LONG).show();


      }



  }

    @Override
    public void onClick(View v) {
        if(v == loginv) {
            userLogin();
        }
    }
}








//    private void addDataToFirestore(){
//        FirebaseFirestore database= FirebaseFirestore.getInstance();
//        HashMap<String, Object> data = new HashMap<>();
//        data.put("first name", "amal");
//        data.put("lastname", "alanzi");
//        database.collection("users")
//                .add(data)
//                .addOnSuccessListener(documentReference -> {
//                    Toast.makeText(getApplicationContext(), "Data insert", Toast.LENGTH_SHORT).show();
//                })
//                .addOnFailureListener(exception -> {
//                    Toast.makeText(getApplicationContext(), "No Data insert", Toast.LENGTH_SHORT).show();
//                });
//
//    }
