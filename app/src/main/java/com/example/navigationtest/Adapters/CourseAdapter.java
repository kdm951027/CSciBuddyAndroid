package com.example.navigationtest.Adapters;

/**
 * Created by karanjaswani on 1/12/18.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.navigationtest.DataTypes.Course;
import com.example.navigationtest.DataTypes.Group;
import com.example.navigationtest.R;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    // this context we will use to inflate the layout
    private Context mCtx;

    // we are storing all the labs in a list
    private List<Course> courseList;

    private OnCourseListener onCourseListener;

    // getting the context and lab list with constructor
    public CourseAdapter(Context mCtx, List<Course> courseList, OnCourseListener onCourseListener) {
        this.mCtx = mCtx;
        this.courseList = courseList;
        this.onCourseListener = new OnCourseListener() {
            @Override
            public void onCourseClick(int position) {
                // do nothing.
            }
        };
    }

    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_course, null);
        return new CourseViewHolder(view, onCourseListener);
    }

    @Override
    public void onBindViewHolder(CourseViewHolder holder, int position) {
        // getting the product of the specified position
        Course course = courseList.get(position);

        // binding the data with the viewholder views
        holder.courseDepartmentAndNumber.setText(course.getDepartment() + " " + course.getNumber());
        holder.courseTitle.setText(course.getTitle());

    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }


    class CourseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView courseDepartmentAndNumber, courseTitle;
        OnCourseListener onCourseListener;

        public CourseViewHolder(View itemView, OnCourseListener onCourseListener) {
            super(itemView);

            courseDepartmentAndNumber = itemView.findViewById(R.id.layout_course_departmentAndNumber);
            courseTitle = itemView.findViewById(R.id.layout_course_title);

            this.onCourseListener = onCourseListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
            onCourseListener.onCourseClick(getAdapterPosition());
        }

    }

    public interface OnCourseListener {
        void onCourseClick(int position);
    }
}
