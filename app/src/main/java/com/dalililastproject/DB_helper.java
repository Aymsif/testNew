package com.dalililastproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DB_helper extends SQLiteOpenHelper {
    public final static String DB_NAME = "dalili_2.db";
    public final static String DB_LOCATION = Environment.getDataDirectory() + "/data/com.dalililastproject/databases/";
    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    public DB_helper(@Nullable Context context) {

        super(context, DB_NAME, null, 1);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void Open() {
        String DB_Path = context.getDatabasePath(DB_NAME).getPath();
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
            return;
        } else {
            sqLiteDatabase = SQLiteDatabase.openDatabase(DB_Path, null, SQLiteDatabase.OPEN_READWRITE);
        }

    }

    public void close() {
        if (sqLiteDatabase != null) {
            sqLiteDatabase.close();
        }
    }

    public ArrayList<DataModel> get_universities_data(int type) {

        ArrayList<DataModel> arrayList = new ArrayList<DataModel>();


        Open();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * From universities where type = " + type, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            DataModel dataModel = new DataModel();
            int id = cursor.getInt(cursor.getColumnIndex("univer_id"));
            String name = cursor.getString(cursor.getColumnIndex("uinever_name"));
            String icon = cursor.getString(cursor.getColumnIndex("univer_icon"));
            dataModel.setName(name);
            dataModel.setIcon(icon);
            dataModel.setId(id);
            dataModel.setViewType(1);
            arrayList.add(dataModel);
            cursor.moveToNext();

        }

        cursor.close();
        close();
        return arrayList;


    }

    public ArrayList<DataModel> get_universities_info(int id) {

        ArrayList<DataModel> arrayList = new ArrayList<DataModel>();


        Open();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * From universities where univer_id = " + id, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            DataModel dataModel = new DataModel();

            String name = cursor.getString(cursor.getColumnIndex("uinever_name"));
            String info = cursor.getString(cursor.getColumnIndex("univer_info"));
            String image = cursor.getString(cursor.getColumnIndex("univer_image"));
            dataModel.setName(name);
            dataModel.setImage(image);
            dataModel.setInfo(info);
            dataModel.setViewType(1);
            arrayList.add(dataModel);
            cursor.moveToNext();
        }
        cursor.close();
        close();
        return arrayList;


    }

    public ArrayList<DataModel> get_faculties_data(int type) {

        ArrayList<DataModel> arrayList = new ArrayList<DataModel>();


        Open();
        Cursor cursor = sqLiteDatabase.rawQuery("Select universities.univer_id,universities.univer_image,universities.uinever_name,faculties.* From faculties Left join universities on (faculties.univer_id = universities.univer_id) where faculties.univer_id = " + type, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            DataModel dataModel = new DataModel();
            int id = cursor.getInt(cursor.getColumnIndex("facu_id"));
            String name = cursor.getString(cursor.getColumnIndex("facu_name"));
            String image = cursor.getString(cursor.getColumnIndex("univer_image"));
            String title = cursor.getString(cursor.getColumnIndex("uinever_name"));
            dataModel.setName(name);
            dataModel.setImage(image);
            dataModel.setId(id);
            dataModel.setTitle(title);
            dataModel.setViewType(1);
            arrayList.add(dataModel);
            cursor.moveToNext();
        }
        cursor.close();
        close();
        return arrayList;


    }

    public String get_dept_info(int id) {


        Open();
        int cont = 0;
        String name = "";
        Cursor cursor = sqLiteDatabase.rawQuery("Select * From departments where facu_id = " + id, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            DataModel dataModel = new DataModel();
            cont++;
            int department_id = cursor.getInt(cursor.getColumnIndex("dept_id"));
            name += cont + " - " + cursor.getString(cursor.getColumnIndex("dept_name")) + "\n";

            if (department_id != 0) {
                Cursor cursor2 = sqLiteDatabase.rawQuery("Select * From sub_dept where dept_id = " + department_id, null);

                cursor2.moveToFirst();
                while (!cursor2.isAfterLast()) {
                    String sub_name = cursor2.getString(cursor2.getColumnIndex("sub_name"));
                    name += "\t\t\t\t\t - " + sub_name + "\n";
                    cursor2.moveToNext();
                }

            }


            cursor.moveToNext();
        }
        cursor.close();
        close();
        return name;


    }
}
