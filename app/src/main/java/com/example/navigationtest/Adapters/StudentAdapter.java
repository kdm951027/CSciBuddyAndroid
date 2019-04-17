package com.example.navigationtest.Adapters;

/**
 * Created by karanjaswani on 1/12/18.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.navigationtest.DataTypes.Group;
import com.example.navigationtest.DataTypes.Student;
import com.example.navigationtest.R;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    // this context we will use to inflate the layout
    private Context mCtx;

    // we are storing all the students in a list
    private List<Student> studentList;

    private OnStudentListener onStudentListener;

    // getting the context and lab list with constructor
    public StudentAdapter(Context mCtx, List<Student> studentList, OnStudentListener onStudentListener) {
        this.mCtx = mCtx;
        this.studentList = studentList;
        this.onStudentListener = onStudentListener;
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_student, null);
        return new StudentViewHolder(view, onStudentListener);
    }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, int position) {
        // getting the product of the specified position
        Student student = studentList.get(position);

        // binding the data with the viewholder views
        holder.studentPhoto.setBackgroundResource(student.getPhotoDrawableId());
        holder.studentName.setText(student.getName());
        holder.studentEmail.setText(student.getX500() + "@umn.edu");
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }


    class StudentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView studentPhoto;
        TextView studentName, studentEmail;
        OnStudentListener onStudentListener;

        public StudentViewHolder(View itemView, OnStudentListener onStudentListener) {
            super(itemView);

            studentPhoto = itemView.findViewById(R.id.layout_student_profile_photo);
            studentName = itemView.findViewById(R.id.layout_student_name);
            studentEmail = itemView.findViewById(R.id.layout_student_email);

            this.onStudentListener = onStudentListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
            onStudentListener.onStudentClick(getAdapterPosition());
        }

    }

    public interface OnStudentListener {
        void onStudentClick(int position);
    }
}
