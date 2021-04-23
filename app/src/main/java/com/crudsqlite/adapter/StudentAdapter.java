package com.crudsqlite.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.crudsqlite.EditStudent;
import com.crudsqlite.R;
import com.crudsqlite.StudentDao;
import com.crudsqlite.Student;
import java.util.List;

public class StudentAdapter extends ArrayAdapter {
    Activity context;
    List<Student> list;
    StudentDao studentDao;

    public StudentAdapter(Activity context, List<Student> list) {
        super(context, R.layout.my_student_list, list);
        this.list = list;
        this.context = context;
        this.studentDao = new StudentDao(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        Student student = list.get(position);

        View rowView = inflater.inflate(R.layout.my_student_list, null, true);

        TextView name = rowView.findViewById(R.id.studentName);
        TextView mobile = rowView.findViewById(R.id.studentMobile);

        name.setText(student.getName());
        mobile.setText(student.getMobile());


        Button btnEdit = rowView.findViewById(R.id.btnEdit);
        Button btnDelete = rowView.findViewById(R.id.btnDelete);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student1 = list.get(position);
                Intent i = new Intent(context, EditStudent.class);
                i.putExtra("id", student1.getId());
                context.startActivity(i);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student1 = list.get(position);
                boolean rs = studentDao.deleteStudent(student1.getId());
                if(rs){
                    list.remove(position);
                    notifyDataSetChanged();
                    setNotifyOnChange(true);
//                    list.remove(position);
                    Toast.makeText(context, "Delete Successful", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(context, "Delete failed! please try again.", Toast.LENGTH_LONG).show();
                }
            }
        });
        return rowView;

    }


}
