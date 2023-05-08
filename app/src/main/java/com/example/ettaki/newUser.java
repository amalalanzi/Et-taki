package com.example.ettaki;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class newUser extends SQLiteOpenHelper {
    public static final String DBNAME = "Users.db";
    public newUser(Context context) {
        super(context, "Users.db", null, 1);
    }


     String Fname , Lname, Email ;


    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table Userdetails(username TEXT primary key, fullname TEXT, email TEXT, password TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists Userdetails");

    }

    public Boolean insertData(String username, String fullname, String email, String password){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("fullname", fullname);
        contentValues.put("email", email);
        contentValues.put("password", password);

        long result = DB.insert("Userdetails", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean updateData(String username, String fullname, String email, String password) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("fullname", fullname);
        contentValues.put("email", email);
        contentValues.put("password", password);
        Cursor cursor = DB.rawQuery("Select * from Userdetails where username = ?", new String[]{username});
        if (cursor.getCount() > 0) {
            long result = DB.update("Userdetails", contentValues, "username=?", new String[]{username});
            if (result == -1){ return false;}
            else{
                return true;}
        }else {
            return false;
        }
    }



    public Boolean daleteData(String username) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where username = ?", new String[]{username});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Userdetails", "username=?", new String[]{username});
            if (result == -1){ return false;}
            else{
                return true;}
        }else {
            return false;
        }
    }


    public Cursor getdata (){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails", null);
        return cursor;
    }


    public Boolean checkusername(String username) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where username = ? and password = ?", new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

}
