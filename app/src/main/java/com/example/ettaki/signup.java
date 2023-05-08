package com.example.ettaki;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class signup extends AppCompatActivity {

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    DBHelper DB;

    String[] item = {" مريض ", " دكتور ", " داعم "};
    AutoCompleteTextView autoCompleteTextView;

    ArrayAdapter<String> adapterItems;

    TextView backlogin;
    EditText Fname, Lname, Emailsignup, Passwordsignup, Repasswordsignup;
    Button registerUser;
    RadioButton Female, Male;
    RadioGroup Gender;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

//        firebaseDatabase = FirebaseDatabase.getInstance();
//        databaseReference =  firebaseDatabase.getReferenceFromUrl("https://et-taki-default-rtdb.firebaseio.com/");

        Fname = (EditText) findViewById(R.id.fname);
        Lname = (EditText) findViewById(R.id.lname);
        Emailsignup = (EditText) findViewById(R.id.emailsignup);
        Passwordsignup = (EditText) findViewById(R.id.passwordsignup);
        Repasswordsignup = (EditText) findViewById(R.id.repasswordsignup);
        registerUser = (Button) findViewById(R.id.registerUser);
        Female = (RadioButton) findViewById(R.id.femaleGender);
        Male = (RadioButton) findViewById(R.id.maleGender);
        Gender = (RadioGroup) findViewById(R.id.groupGender);
        backlogin = (TextView) findViewById(R.id.backtologin);

        progressDialog = new ProgressDialog(this);


//        DB = new DBHelper(this);
//
//
//        Fname = (EditText) findViewById(R.id.fname);
//        Lname = (EditText) findViewById(R.id.lname);
//        Emailsignup = (EditText) findViewById(R.id.emailsignup);
//        Passwordsignup = (EditText) findViewById(R.id.passwordsignup);
//        Repasswordsignup = (EditText) findViewById(R.id.repasswordsignup);
//        registerUser = (Button) findViewById(R.id.registerUser);
//        Female = (RadioButton) findViewById(R.id.femaleGender);
//        Male = (RadioButton) findViewById(R.id.maleGender);
//        Gender = (RadioGroup) findViewById(R.id.groupGender);
//        backlogin = (TextView) findViewById(R.id.backtologin);

        String x;
        if(Female.isChecked())
            x= "ذكر" ;
        else
            x= "انثى" ;






        /* for the list type member*/
        autoCompleteTextView = findViewById(R.id.auto_complete_text);
        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item, item);

        autoCompleteTextView.setAdapter(adapterItems);



        /* go to login page */
        backlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signup.this, login.class);
                startActivity(intent);
            }
        });

        registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String firstName = Fname.getText().toString();
                final String lastName = Lname.getText().toString();
                final String email = Emailsignup.getText().toString();
                final String password = Passwordsignup.getText().toString();
                final   String repass = Repasswordsignup.getText().toString();
                final String type = autoCompleteTextView.getText().toString();

                String x;
                if(Female.isChecked())
                    x= "ذكر" ;
                else
                    x= "انثى" ;
                final String gender = x;

                if (firstName.isEmpty()) {
                    Fname.setError("ادخل الاسم الاول ");
                    Fname.requestFocus();
                    return;
                }

                else if (lastName.isEmpty()) {
                    Lname.setError("ادخل الاسم الأخير ");
                    Lname.requestFocus();
                    return;
                }

                else if (email.isEmpty()) {
                    Emailsignup.setError(" ادخل البريد الالكتروني  ");
                    Emailsignup.requestFocus();
                    return;
                }

                else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Emailsignup.setError(" ادخل البريد الالكتروني بشكل صحيح  ");
                    Emailsignup.requestFocus();
                    return;
                }


                else if (password.isEmpty()) {
                    Passwordsignup.setError(" ادخل كلمة المرور  ");
                    Passwordsignup.requestFocus();
                    return;
                }

                else if (password.length() < 6) {
                    Passwordsignup.setError(" كلمة المرور قصيره، يجب ان لا تقل عن ٦ احرف  ");
                    Passwordsignup.requestFocus();
                    return;
                }

                else if (repass.isEmpty()) {
                    Repasswordsignup.setError(" ادخل كلمة المرور مرة اخرى  ");
                    Repasswordsignup.requestFocus();
                    return;
                }

                else if (!repass.equals(password)) {
                    Repasswordsignup.setError(" كلمة المرور ليست مطابقة  ");
                    Repasswordsignup.requestFocus();
                    return;
                }

                else if (type.isEmpty()) {
                    autoCompleteTextView.setError(" اختار نوع العضو  ");
                    autoCompleteTextView.requestFocus();
                    return;
                }else {


                    insertNewUser();
//                    Toast.makeText(signup.this, " nooo", Toast.LENGTH_LONG).show();
//
//
//                    databaseReference.addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            if (snapshot.hasChild(email)) {
//                                Toast.makeText(signup.this, " البريد الإلكتروني مستخدم مسبقاً، فضلاً قم بتسجيل الدخول", Toast.LENGTH_LONG).show();
//
//                            } else {
//                                databaseReference.child("users").child(email).child("firstName").setValue(firstName);
//                                databaseReference.child("users").child(email).child("lastName").setValue(lastName);
//                                databaseReference.child("users").child(email).child("password").setValue(password);
//                                databaseReference.child("users").child(email).child("type").setValue(type);
//                                databaseReference.child("users").child(email).child("gender").setValue(gender);
//
//                                Toast.makeText(signup.this, "  تم انشاء حسابك بنجاح", Toast.LENGTH_LONG).show();
//                                finish();
//
//
//                            }
//
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//                            Toast.makeText(signup.this, " no", Toast.LENGTH_LONG).show();
//
//                        }
//                    });


                }

            }
        });


    }




    //    Add To Users Table
    private void insertNewUser() {
        final String firstName = Fname.getText().toString();
        final String lastName = Lname.getText().toString();
        final String email = Emailsignup.getText().toString();
        final String password = Passwordsignup.getText().toString();
        final   String repass = Repasswordsignup.getText().toString();
        final String type = autoCompleteTextView.getText().toString();

        String x;
        if(Female.isChecked())
            x= "ذكر" ;
        else
            x= "انثى" ;
        final String gender = x;

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("User");
        User user = new User();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setType(type);
        user.setGender(gender);
        user.setPassword(password);

        myRef.push().setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(signup.this, "  تم انشاء حسابك بنجاح", Toast.LENGTH_LONG).show();
                Emailsignup.setText("");
                Fname.setText("");
                Lname.setText("");
                autoCompleteTextView.setText("");
                Female.setText("");
                Male.setText("");
                Passwordsignup.setText("");
              //  pDialog.dismiss();
                startActivity(new Intent(getApplicationContext(), login.class));
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
              //  pDialog.dismiss();
            }
        });
    }

//
//        registerUser.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String fname = Fname.getText().toString();
//                String lname = Lname.getText().toString();
//                String email = Emailsignup.getText().toString();
//                String pass = Passwordsignup.getText().toString();
//                String repass = Repasswordsignup.getText().toString();
//                String list = autoCompleteTextView.getText().toString();
//                String Females = Female.getText().toString();
//                String Males = Male.getText().toString();
//
//
//
//
//
//                if (fname.isEmpty()) {
//                    Fname.setError("ادخل الاسم الاول ");
//                    Fname.requestFocus();
//                    return;
//                }
//
//                if (lname.isEmpty()) {
//                    Lname.setError("ادخل الاسم الأخير ");
//                    Lname.requestFocus();
//                    return;
//                }
//
//                if (email.isEmpty()) {
//                    Emailsignup.setError(" ادخل البريد الالكتروني  ");
//                    Emailsignup.requestFocus();
//                    return;
//                }
//
//                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//                    Emailsignup.setError(" ادخل البريد الالكتروني بشكل صحيح  ");
//                    Emailsignup.requestFocus();
//                    return;
//                }
//
//
//                if (pass.isEmpty()) {
//                    Passwordsignup.setError(" ادخل كلمة المرور  ");
//                    Passwordsignup.requestFocus();
//                    return;
//                }
//
//                if (pass.length() < 6) {
//                    Passwordsignup.setError(" كلمة المرور قصيره، يجب ان لا تقل عن ٦ احرف  ");
//                    Passwordsignup.requestFocus();
//                    return;
//                }
//
//                if (repass.isEmpty()) {
//                    Repasswordsignup.setError(" ادخل كلمة المرور مرة اخرى  ");
//                    Repasswordsignup.requestFocus();
//                    return;
//                }
//
//                if (!repass.equals(pass)) {
//                    Repasswordsignup.setError(" كلمة المرور ليست مطابقة  ");
//                    Repasswordsignup.requestFocus();
//                    return;
//                }
//
//                if (list.isEmpty()) {
//                    autoCompleteTextView.setError(" اختار نوع العضو  ");
//                    autoCompleteTextView.requestFocus();
//                    return;
//                }
//
//
//
//
//                if(pass.equals(repass)){
//                    Boolean checkuser = DB.checkusername(fname);
//                    if(checkuser==false){
//                        Boolean insert = DB.insertData(email,fname,lname, list, x, pass);
//                        if(insert==true){
//                            Toast.makeText(signup.this, "  تم انشاء حسابك بنجاح", Toast.LENGTH_LONG).show();
//
//                        }else{
//                            Toast.makeText(signup.this, "  حدث خطاء! حاول مرة اخرى", Toast.LENGTH_LONG).show();
//                        }
//                    }
//                    else{
//                        Toast.makeText(signup.this, " الحساب مستخدم مسبقًا", Toast.LENGTH_SHORT).show();
//                    }
//                }
//                        }
//        });
//
//
//    }

//
//    private void registerUsers() {
//


//        progressDialog.setMessage("يتم معالجة الطلب....");
//       progressDialog.show();
//       StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_REGISTER,
//               new com.android.volley.Response.Listener<String>() {
//                   @Override
//                   public void onResponse(String response) {
//                       progressDialog.dismiss();
//                       Toast.makeText(signup.this, response, Toast.LENGTH_LONG).show();
//
//
//
//
//                   }
//               }, new Response.ErrorListener() {
//           @Override
//           public void onErrorResponse(VolleyError error) {
//               progressDialog.hide();
//               Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
//
//           }
//       }){
//
//           @Nullable
//           @Override
//           protected Map<String, String> getParams() throws AuthFailureError {
//               Map<String,String> params = new HashMap<>();
//               params.put("email", email);
//               params.put("firstName", firstName);
//               params.put("lastName", lastName);
//               params.put("type", type);
//               params.put("gender", gender);
//               params.put("password", password);
//               return params;
//           }
//
//       };
//
//
//
//        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
//



//
//    }
//
//    @Override
//    public void onClick(View view) {
//        if(view == registerUser)
//            registerUsers();
//
//    }






}








  /*  @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registerUser:
                registeruser();
                break;
        }

    }


  private void registeruser() {
       String Fristname = Fname.getText().toString();
        String Lastaname = Lname.getText().toString();
        String Email = Emailsignup.getText().toString();
        String Password = Passwordsignup.getText().toString();
        String Repassword = Repasswordsignup.getText().toString();
        String list = autoCompleteTextView.getText().toString();
       String Females = Female.getText().toString().trim();
        String Males = Male.getText().toString().trim();

        if (Fristname.isEmpty()) {
            Fname.setError("ادخل الاسم الاول ");
            Fname.requestFocus();
            return;
        }

        if (Lastaname.isEmpty()) {
            Lname.setError("ادخل الاسم الأخير ");
            Lname.requestFocus();
            return;
        }

        if (Email.isEmpty()) {
            Emailsignup.setError(" ادخل البريد الالكتروني  ");
            Emailsignup.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            Emailsignup.setError(" ادخل البريد الالكتروني بشكل صحيح  ");
            Emailsignup.requestFocus();
            return;
        }


        if (Password.isEmpty()) {
            Passwordsignup.setError(" ادخل كلمة المرور  ");
            Passwordsignup.requestFocus();
            return;
        }

        if (Password.length() < 6) {
            Passwordsignup.setError(" كلمة المرور قصيره، يجب ان لا تقل عن ٦ احرف  ");
            Passwordsignup.requestFocus();
            return;
        }

        if (Repassword.isEmpty()) {
            Repasswordsignup.setError(" ادخل كلمة المرور مرة اخرى  ");
            Repasswordsignup.requestFocus();
            return;
        }

        if (!Repassword.equals(Password)) {
            Repasswordsignup.setError(" كلمة المرور ليست مطابقة  ");
            Repasswordsignup.requestFocus();
            return;
        }

        if (list.isEmpty()) {
            autoCompleteTextView.setError(" اختار نوع العضو  ");
            autoCompleteTextView.requestFocus();
            return;
        }






        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Users");
        newUser user = new newUser(Fristname, Lastaname, Email);
        reference.child(Fristname).setValue(user);
        Toast.makeText(signup.this, "  تم انشاء حسابك بنجاح", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(signup.this, login.class);
        startActivity(intent);


if (!Fristname.isEmpty() || !Lastaname.isEmpty()|| !Password.isEmpty() || !Email.isEmpty() || !Repassword.isEmpty()){
            mAuth.createUserWithEmailAndPassword(Email, Password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                newUser user = new newUser(Fristname, Lastaname, Email);


                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                     @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registerUser:
                registeruser();
                break;
        }

    }


  private void registeruser() {
       String Fristname = Fname.getText().toString();
        String Lastaname = Lname.getText().toString();
        String Email = Emailsignup.getText().toString();
        String Password = Passwordsignup.getText().toString();
        String Repassword = Repasswordsignup.getText().toString();
        String list = autoCompleteTextView.getText().toString();
       String Females = Female.getText().toString().trim();
        String Males = Male.getText().toString().trim();

        if (Fristname.isEmpty()) {
            Fname.setError("ادخل الاسم الاول ");
            Fname.requestFocus();
            return;
        }

        if (Lastaname.isEmpty()) {
            Lname.setError("ادخل الاسم الأخير ");
            Lname.requestFocus();
            return;
        }

        if (Email.isEmpty()) {
            Emailsignup.setError(" ادخل البريد الالكتروني  ");
            Emailsignup.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            Emailsignup.setError(" ادخل البريد الالكتروني بشكل صحيح  ");
            Emailsignup.requestFocus();
            return;
        }


        if (Password.isEmpty()) {
            Passwordsignup.setError(" ادخل كلمة المرور  ");
            Passwordsignup.requestFocus();
            return;
        }

        if (Password.length() < 6) {
            Passwordsignup.setError(" كلمة المرور قصيره، يجب ان لا تقل عن ٦ احرف  ");
            Passwordsignup.requestFocus();
            return;
        }

        if (Repassword.isEmpty()) {
            Repasswordsignup.setError(" ادخل كلمة المرور مرة اخرى  ");
            Repasswordsignup.requestFocus();
            return;
        }

        if (!Repassword.equals(Password)) {
            Repasswordsignup.setError(" كلمة المرور ليست مطابقة  ");
            Repasswordsignup.requestFocus();
            return;
        }

        if (list.isEmpty()) {
            autoCompleteTextView.setError(" اختار نوع العضو  ");
            autoCompleteTextView.requestFocus();
            return;
        }






        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Users");
        newUser user = new newUser(Fristname, Lastaname, Email);
        reference.child(Fristname).setValue(user);
        Toast.makeText(signup.this, "  تم انشاء حسابك بنجاح", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(signup.this, login.class);
        startActivity(intent);


if (!Fristname.isEmpty() || !Lastaname.isEmpty()|| !Password.isEmpty() || !Email.isEmpty() || !Repassword.isEmpty()){
            mAuth.createUserWithEmailAndPassword(Email, Password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                newUser user = new newUser(Fristname, Lastaname, Email);


                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(signup.this, "  تم انشاء حسابك بنجاح", Toast.LENGTH_LONG).show();
                                                } else {
                                                    Toast.makeText(signup.this, "  حدث خطاء! حاول مرة اخرى", Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });
                            }else {
                                Toast.makeText(signup.this, "  حدث خطاء! حاول مرة اخرى", Toast.LENGTH_LONG).show();
                            }

                        }
                    });




    }}  Toast.makeText(signup.this, "  تم انشاء حسابك بنجاح", Toast.LENGTH_LONG).show();
                                                } else {
                                                    Toast.makeText(signup.this, "  حدث خطاء! حاول مرة اخرى", Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });
                            }else {
                                Toast.makeText(signup.this, "  حدث خطاء! حاول مرة اخرى", Toast.LENGTH_LONG).show();
                            }

                        }
                    });




    }} */







//                       try {
//                           JSONObject jsonObject = new JSONObject(response);
//                           // on below line we are displaying a success toast message.
//                           Toast.makeText(signup.this, jsonObject.getString("message"), Toast.LENGTH_LONG).show();
//                       } catch (JSONException e) {
//                           e.printStackTrace();
//                       }




//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);

