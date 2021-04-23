package com.crudsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditStudent extends AppCompatActivity {
    Intent i;
    StudentDao studentDao;
    EditText sid;
    EditText sName;
    EditText sMobile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);
        studentDao = new StudentDao(this);
        sid = findViewById(R.id.editId);
        sName = findViewById(R.id.editName);
        sMobile = findViewById(R.id.editMobile);
        i = getIntent();
        int id = i.getExtras().getInt("id");
        Student student = studentDao.getStudentById(id);
        if(student != null){
            sid.setText(String.valueOf(student.getId()));
            sid.setEnabled(false);
            sName.setText(student.getName());
            sMobile.setText(student.getMobile());
        }
        System.out.println(id+"========================");
    }

    public void resetData(View view){
        sName.setText("");
        sMobile.setText("");
    }

    public void updateData(View view){
        String id = sid.getText().toString();
        String name = sName.getText().toString();
        String mobile = sMobile.getText().toString();

        Student student = new Student();
        student.setId(Integer.parseInt(id));
        student.setName(name);
        student.setMobile(mobile);
        int rs = studentDao.updateStudent(student);
        if(rs > 0){

            Toast.makeText(getApplicationContext(),"Data update successfully",Toast.LENGTH_SHORT).show();
            i = new Intent(this, MainActivity.class);
            startActivity(i);
        }else{
            Toast.makeText(getApplicationContext(),"Data update successfully",Toast.LENGTH_SHORT).show();
        }

    }
}