package com.crudsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class StudentDao extends DatabaseHelper {
    private static DatabaseHelper dbhelper;
    private String tableName = "students";
    SQLiteDatabase database;

    public StudentDao(Context context) {
        super(context);
        dbhelper = getHelper(context);
        if(dbhelper == null)
            dbhelper = getHelper(context);
        database = dbhelper.getWritableDatabase();

    }

    public long addStudent(Student student){
        ContentValues values = new ContentValues();
        values.put("name", student.getName());
        values.put("mobile", student.getMobile());
        long rs = database.insert(tableName, null, values);
        return  rs;
    }

    public int updateStudent(Student student){
        ContentValues values = new ContentValues();
        values.put("id", student.getId());
        values.put("name", student.getName());
        values.put("mobile", student.getMobile());
        int rs = database.update(tableName, values, "id=?", new String[]{String.valueOf(student.getId())});
        return  rs;
    }

    public List<Student> getStudentList(){
        database = dbhelper.getReadableDatabase();
        Cursor cr = database.rawQuery("select * from students", null);
        List<Student> studentList = new ArrayList<>();
        while(cr.moveToNext()){

            String id = cr.getString(cr.getColumnIndex("id"));
            String name = cr.getString(cr.getColumnIndex("name"));
            String mobile = cr.getString(cr.getColumnIndex("mobile"));
            Student student = new Student(Integer.parseInt(id), name, mobile);
            studentList.add(student);

        }
        return studentList;
    }

    public Student getStudentById(int id){
        database = dbhelper.getReadableDatabase();
        Cursor cr = database.rawQuery("select * from students where id=?", new String[]{String.valueOf(id)});
        Student student = new Student();
        while(cr.moveToNext()){
            String id1 = cr.getString(cr.getColumnIndex("id"));
            String name = cr.getString(cr.getColumnIndex("name"));
            String mobile = cr.getString(cr.getColumnIndex("mobile"));
            student = new Student(Integer.parseInt(id1), name, mobile);

        }
        return student;
    }

    public boolean deleteStudent(int id){
        int rs = database.delete(tableName, "id=?", new String[]{String.valueOf(id)});
        return  rs>0? true: false;
    }
}
