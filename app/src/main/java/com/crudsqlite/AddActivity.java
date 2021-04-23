package com.crudsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    //EditText id;
    EditText name;
    EditText mobile;

    StudentDao studentDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        studentDao = new StudentDao(this);
        //id = findViewById(R.id.editId);
        name = findViewById(R.id.editName);
        mobile = findViewById(R.id.editMobile);

    }

    public void addData(View view){

        String inName = name.getText().toString();
        String inMobile = mobile.getText().toString();

        Student student = new Student(inName, inMobile);
        long rs = studentDao.addStudent(student);

        if(rs > 0){
            Toast.makeText(getApplicationContext(),"Data saved successfully",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            resetData();
        }else{
            Toast.makeText(getApplicationContext(),"Data saved failed",Toast.LENGTH_SHORT).show();
        }

        System.out.println("Name: "+ inName+" Mobile: "+inMobile);

    }
    public void resetData(){

        name.setText("");
        mobile.setText("");
    }
}