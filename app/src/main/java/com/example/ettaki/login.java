package com.example.ettaki;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {

    Button loginv;
    TextView forgotpasswordv, signupv;
    EditText emailv, passwordv;
    DBHelper DB;
    ProgressDialog progressDialog;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

//        if (mAuth.getCurrentUser() != null) {
//            finish();
//            startActivity(new Intent(this, MainActivity2.class));
//        }

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


        loginv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = emailv.getText().toString();
                String pass = passwordv.getText().toString();
                if (emailv.getText().equals("")) {
                    emailv.requestFocus();
                    emailv.setError("يرجى ادخال الايميل");
                    return;
                }
                if (passwordv.getText().equals("")) {
                    passwordv.requestFocus();
                    passwordv.setError("يرجى ادخال كلمة المرورو");
                    return;
                }
                if (!isNetworkAvailable()) {
                    Toast.makeText(login.this, "تحقق من اتصال الانترنت", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    userLogin();
                }

            }
        });


    }


    private void userLogin() {

        String email = emailv.getText().toString().trim();
        String password = passwordv.getText().toString().trim();

        progressDialog.show();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            // Log.d(TAG, "signInWithEmail:success");
                            Toast.makeText(login.this, "تم تسجيل الدخول", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            startActivity(new Intent(getApplicationContext(), MainActivity2.class));

                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(login.this, "كلمة السر او الايميل خطا", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();


                        }
                    }
                });
    }

    public boolean isNetworkAvailable() {
        boolean state;
        try {
            ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = null;
            if (manager != null) {
                {
                    state = true;
                    networkInfo = manager.getActiveNetworkInfo();
                }
                return networkInfo != null && networkInfo.isConnected();
            } else {
                state = false;
            }
        } catch (NullPointerException e) {
            state = false;
        }
        return state;
    }

    public void test(String x) {

        try {
            JSONObject obj = new JSONObject(x);
            if (!obj.getBoolean("error")) {

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


                startActivity(new Intent(getApplicationContext(), MainActivity2.class));

            } else {
                Toast.makeText(getApplicationContext(), x, Toast.LENGTH_LONG).show();

            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), x, Toast.LENGTH_LONG).show();


        }


    }


}

