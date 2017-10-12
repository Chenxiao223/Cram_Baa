package com.example.cram_baa.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cram_baa.Entity.Setting;


public class DBOperation {
    public DBOpenHelper dbOpenHelper;
    public SQLiteDatabase db;

    public DBOperation(Context context) {
        dbOpenHelper = new DBOpenHelper(context, "goss.db", null, 2);
        db = dbOpenHelper.getWritableDatabase();
    }

    public Setting querySet() {
        Cursor cursor = db.query("Setting", null, null,null, null, null, null);
        Setting set1 = new Setting();
        if (!cursor.moveToFirst()) {
            cursor.close();
            return set1;
        }
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            Integer id = cursor.getInt(cursor.getColumnIndex("id"));
            String Name = cursor.getString(cursor.getColumnIndex("Name"));
            String Sex = cursor.getString(cursor.getColumnIndex("Sex"));
            String Region = cursor.getString(cursor.getColumnIndex("Region"));
            String Age = cursor.getString(cursor.getColumnIndex("Age"));
            String School = cursor.getString(cursor.getColumnIndex("School"));
            String Grade = cursor.getString(cursor.getColumnIndex("Grade"));
            String Clbum = cursor.getString(cursor.getColumnIndex("Clbum"));

            set1.setID(id);
            set1.setName(Name);
            set1.setSex(Sex);
            set1.setRegion(Region);
            set1.setAge(Age);
            set1.setSchool(School);
            set1.setGrade(Grade);
            set1.setClbum(Clbum);
        }
        cursor.close();
        return set1;
    }

    public void insertSet(Setting set) {
        ContentValues values = new ContentValues();
        values.put("Name", set.getName());
        values.put("Sex", set.getSex());
        values.put("Region", set.getRegion());
        values.put("Age", set.getAge());
        values.put("School", set.getSchool());
        values.put("Grade", set.getGrade());
        values.put("Clbum", set.getClbum());
        db.insert("Setting", null, values);
        values.clear();
    }

    public void deleteSet() {
        db.delete("Setting", null, null);
        dbOpenHelper.onUpgrade(db, 0, 0);
    }

}
