package com.crudsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.crudsqlite.adapter.StudentAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    StudentDao studentDao;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        studentDao = new StudentDao(this);
        List<Student> stList = studentDao.getStudentList();
        listView = findViewById(R.id.studentList);
        StudentAdapter adapter = new StudentAdapter(this, stList);
        listView.setAdapter(adapter);
    }

    public void Add(View view) {
        intent = new Intent(this, AddActivity.class);
        startActivity(intent);

    }
}