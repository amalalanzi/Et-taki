package com.example.ettaki;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;
import java.util.Map;

public class forgetpasswordActivity extends AppCompatActivity {

    TextView backlogin ;
    Button change;
    EditText email;
    DBHelper DB;

    String contactemail;
    StringRequest stringRequest;
    String URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);

        backlogin = (TextView)findViewById(R.id.backtologin);
        change = (Button)findViewById(R.id.newpassword);
        email = (EditText)findViewById(R.id.emailforget) ;

       // DB = new DBHelper(this);



        /* go to login page */
        backlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(forgetpasswordActivity.this, login.class);
                startActivity(intent);
            }
        });


//        chngebtn();



        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String EmailAddress = email.getText().toString();

                FirebaseAuth.getInstance().sendPasswordResetEmail(EmailAddress).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                           Toast.makeText(forgetpasswordActivity.this, "send email", Toast.LENGTH_LONG).show();


                        }else {
                            Toast.makeText(forgetpasswordActivity.this, "fial", Toast.LENGTH_LONG).show();

                        }
                    }
                });


            }
        });

//
//        change.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                    String EmailAddress = email.getText().toString();
//
//                   FirebaseAuth.getInstance().sendPasswordResetEmail(EmailAddress).addOnCompleteListener(new OnCompleteListener<Void>() {
//                       @Override
//                       public void onComplete(@NonNull Task<Void> task) {
//                           if
//                       }
//                   });

//                    try {
//                        if(EmailAddress.length() > 0) {


//                            if(DB.getEmailAddress(EmailAddress)) {
//                                Toast.makeText(forgetpasswordActivity.this, "Email Successfully ", Toast.LENGTH_LONG).show();
//                                String to = email.getText().toString();
//                                //String subject = EmailAddress.getText().toString();
//                                // String message = EmailAddress.getText().toString();
//                                Intent email = new Intent(Intent.ACTION_SEND);
//                                email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
//                                email.putExtra(Intent.EXTRA_SUBJECT, "Password Recovery");
//                                email.putExtra(Intent.EXTRA_TEXT, DB.getEmailAddress(EmailAddress));
//                                //need this to prompts email client only
//                                email.setType("message/rfc822");
//                                startActivity(Intent.createChooser(email, "gmail"));
//                            }
//
//                            else {
//                                Toast.makeText(forgetpasswordActivity.this,"Invalid Email",
//                                        Toast.LENGTH_LONG).show();
//                                email.setText("");
//                            }
//
//                        }
//
//                    }
//
//                    catch(Exception e) {
//                        Toast.makeText(forgetpasswordActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
//                    }
//
//
//
//
//            }
//        });

    }

//    private void chngebtn() {
//
//        change.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                contactemail = email.getText().toString().trim();
//
//                if(TextUtils.isEmpty(contactemail)){
//                    Toast.makeText(getApplicationContext(),"enter email", Toast.LENGTH_LONG).show();
//                }else {
//                    if(Patterns.EMAIL_ADDRESS.matcher(contactemail).matches()){
//
//                        sendForgetpassWord(contactemail);
//                    }
//                }


//
//                stringRequest = new StringRequest(Request.Method.POST, Constants.URL_FORGOT, new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {

//                        if(response.equals("yes[]")){
//                            Toast.makeText(getApplicationContext(),"yess", Toast.LENGTH_LONG).show();
//                        }else {
//                            Toast.makeText(getApplicationContext(),"nooo", Toast.LENGTH_LONG).show();
//
//                        }
//
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getApplicationContext(),"plesa chek connection", Toast.LENGTH_LONG).show();
//
//
//                    }
//                }
//                ){
//                    @Nullable
//                    @Override
//                    protected Map<String, String> getParams() throws AuthFailureError {
//                        Map<String, String> params = new HashMap<>();
//                        params.put("email", contactemail);
//                        return params;
//
////                    }
////                };
//            }
//        });
//    }

//
//    public void sendForgetpassWord(final String MyEmail){
//        Toast.makeText(getApplicationContext(),"see your email", Toast.LENGTH_LONG).show();
//
//        final RequestQueue queue= Volley.newRequestQueue(this);
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, )

//    }
}