package com.example.ettaki;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ettaki.chat.models.UsersModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;


public class signup extends AppCompatActivity {


    String[] item = {" مريض ", " دكتور ", " داعم "};
    AutoCompleteTextView autoCompleteTextView;

    ArrayAdapter<String> adapterItems;

    TextView backlogin;
    EditText Fname, Lname, Emailsignup, Passwordsignup, Repasswordsignup;
    Button registerUser;
    RadioButton Female, Male;
    RadioGroup Gender;
    ProgressDialog progressDialog;

    //for User Image
    ImageView imgUser;
    FirebaseStorage storage;
    StorageReference storageReference;
    Uri filePath, urlImage, uriPath;
    UsersModel usersModel;

    //auth
    private FirebaseAuth mAuth;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == 200 && resultCode == RESULT_OK && data != null) {
                filePath = data.getData();
                imgUser.setImageURI(filePath);
                uriPath = filePath;

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void uploadImage() {//رفع الصورة من التلفون الى قاعدة البيانات
        if (uriPath != null) {
            Calendar calendar = Calendar.getInstance();
            StorageReference imagePath = storageReference.child("Users Images").child("profile" + calendar.getTimeInMillis());
            imagePath.putFile(uriPath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    imagePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            urlImage = uri;
                            //coursesModel.setImageCourse(String.valueOf(urlImage));
                            usersModel.setImage(String.valueOf(urlImage));
                            insertNewUser();
                            // Toast.makeText(getApplicationContext(), "Upload Image Success", Toast.LENGTH_LONG).show();

                        }//لارجاع رابط الصورة

                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), "Failed Upload Image  ", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

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
        imgUser = findViewById(R.id.imgUser);
        progressDialog = new ProgressDialog(this);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        storageReference = FirebaseStorage.getInstance().getReference();
        usersModel = new UsersModel();
        mAuth = FirebaseAuth.getInstance();

        //when choose image
        imgUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Galary = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(Galary, 200);
            }
        });

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
                final String repass = Repasswordsignup.getText().toString();
                final String type = autoCompleteTextView.getText().toString();

                if (uriPath == null){
                    Toast.makeText(signup.this, "اختار صورة البروفايل", Toast.LENGTH_LONG).show();
                    return;

                }
                if (firstName.isEmpty()) {
                    Fname.setError("ادخل الاسم الاول ");
                    Fname.requestFocus();
                    return;
                } else if (lastName.isEmpty()) {
                    Lname.setError("ادخل الاسم الأخير ");
                    Lname.requestFocus();
                    return;
                } else if (email.isEmpty()) {
                    Emailsignup.setError(" ادخل البريد الالكتروني  ");
                    Emailsignup.requestFocus();
                    return;
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Emailsignup.setError(" ادخل البريد الالكتروني بشكل صحيح  ");
                    Emailsignup.requestFocus();
                    return;
                } else if (password.isEmpty()) {
                    Passwordsignup.setError(" ادخل كلمة المرور  ");
                    Passwordsignup.requestFocus();
                    return;
                } else if (password.length() < 6) {
                    Passwordsignup.setError(" كلمة المرور قصيره، يجب ان لا تقل عن ٦ احرف  ");
                    Passwordsignup.requestFocus();
                    return;
                } else if (repass.isEmpty()) {
                    Repasswordsignup.setError(" ادخل كلمة المرور مرة اخرى  ");
                    Repasswordsignup.requestFocus();
                    return;
                } else if (!repass.equals(password)) {
                    Repasswordsignup.setError(" كلمة المرور ليست مطابقة  ");
                    Repasswordsignup.requestFocus();
                    return;
                } else if (type.isEmpty()) {
                    autoCompleteTextView.setError(" اختار نوع العضو  ");
                    autoCompleteTextView.requestFocus();
                    return;
                } else {
                    progressDialog.setMessage("جاري رفع البيانات...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    String gender;
                    if (Female.isChecked())
                        gender = "ذكر";
                    else
                        gender = "انثى";
                    usersModel.setFirstName(firstName);
                    usersModel.setLastName(lastName);
                    usersModel.setEmail(email);
                    usersModel.setPassword(password);
                    usersModel.setType(type);
                    usersModel.setGender(gender);
                    authUser();
                }
            }
        });


    }


    //    Add To Users Table
    private void insertNewUser() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("User");

        myRef.child(usersModel.getUserId()).setValue(usersModel).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(signup.this, "  تم انشاء حسابك بنجاح", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
                startActivity(new Intent(getApplicationContext(), login.class));
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
                //  pDialog.dismiss();
            }
        });
    }

    //add user to  firebase authentication
    private void authUser() {
        mAuth.createUserWithEmailAndPassword(usersModel.getEmail(), usersModel.getPassword())
                .addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "createUserWithEmail:success");

                            FirebaseUser user = mAuth.getCurrentUser();
                            usersModel.setUserId(user.getUid());
                            uploadImage();

                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "createUserWithEmail:failure", task.getException());

                            Toast.makeText(signup.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                        }
                    }
                })
                .addOnFailureListener(signup.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        Passwordsignup.requestFocus();
                        Passwordsignup.setError(e.getMessage());
                        progressDialog.dismiss();

                    }
                });

    }
}

