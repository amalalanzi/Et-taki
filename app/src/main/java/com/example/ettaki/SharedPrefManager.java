package com.example.ettaki;




import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class SharedPrefManager {

    private static SharedPrefManager mInstance;
    private static Context mCtx;
    private static final String SHARED_PREF_NAME = "mysharedpref12";
    private static  final  String KEY_USER_EMAIL= "useremail";
    private static  final  String KEY_USER_ID= "userid";
    private static  final  String KEY_USER_FIRSTNAME= "userfirstname";
    private static  final  String KEY_USER_LASTNAME= "userlastname";
    private static  final  String KEY_USER_TYPE= "usertype";
    private static  final  String KEY_USER_GENDER= "usergender";



    private SharedPrefManager(Context context) {
        mCtx = context;



    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public boolean userLogin(int id , String email, String firstName, String lastName , String type , String gender){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(KEY_USER_ID, id);
        editor.putString(KEY_USER_EMAIL, email);
        editor.putString(KEY_USER_FIRSTNAME, firstName);
        editor.putString(KEY_USER_LASTNAME, lastName);
        editor.putString(KEY_USER_TYPE, type);
        editor.putString(KEY_USER_GENDER, gender);

        editor.apply();

        return true;

    }

    public boolean isLoggedin(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        if(sharedPreferences.getString(KEY_USER_EMAIL, null) != null){
            return  true;
        }
        return  false;
    }


    public boolean logout(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return  true;
    }

    public String getFullName(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
         String x = sharedPreferences.getString(KEY_USER_FIRSTNAME, null);
         String y = sharedPreferences.getString(KEY_USER_LASTNAME, null);
         String z = x+" " +y;
         return z;
    }

    public String getUserEmail(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_EMAIL, null);

    }

    public String getUserType(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_TYPE, null);

    }


}
