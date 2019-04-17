package com.example.navigationtest.SubPages;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.navigationtest.DataTypes.Course;
import com.example.navigationtest.DataTypes.Lab;
import com.example.navigationtest.DataTypes.Student;
import com.example.navigationtest.Databases.CourseDatabase;
import com.example.navigationtest.Databases.StudentDatabase;
import com.example.navigationtest.R;
import com.yarolegovich.lovelydialog.LovelyTextInputDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.random;

public class LabPage extends Fragment {
    private Lab lab;
    private Random randomGenerator;
    final Student currentStudent = StudentDatabase.getStudent(Student.currentX500);
    List<Integer> partnersID = new ArrayList<>();
    List<Integer> emptyID = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the lab for this fragment from the bundle.
        if (savedInstanceState == null) {
            Bundle b = getArguments();
            lab = b.getParcelable("lab");
        }

        int totalSeat = lab.getTotalSeats();
        for (int i = 0; i < totalSeat; i ++)
        {
            double rand = Math.random();
            if (rand >= 0.8)
            {
                partnersID.add(i);
            }
            else if (rand >= 0.6)
            {
                emptyID.add(i);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if ( lab.getName().equals("Keller 4-250") || lab.getName().equals("Keller 4-240") )
            return inflater.inflate(R.layout.fragment_keller4250, container, false);
        else
            return inflater.inflate(R.layout.fragment_keller1200, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set the activity bar title.
        getActivity().setTitle(lab.getName());

        // Iterate all the child views under the relative layout
        RelativeLayout parent = (RelativeLayout)view.findViewById(R.id.parent);
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount/2; i ++)
        {
            final ImageButton child = (ImageButton) parent.getChildAt(i); // the seat
            final TextView child_text = (TextView) parent.getChildAt(i + childCount/2); // text below the seat

            if (partnersID.contains(i)) // potential partners
            {
                child.setImageResource(R.drawable.potential_partner);

                //List<Course> stu_course = student.getEnrolledCourses();
                //int index = randomGenerator.nextInt(stu_course.size()); // this line reports null reference error
                //Course curr_study = stu_course.get(index);
                Course rand_course = CourseDatabase.getRandomCourse();
                child_text.setText(rand_course.toString());

                child.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Student student = StudentDatabase.getRandomStudent();
                        while (student.getX500().equals(Student.currentX500)) // find another student other than current user
                        {
                            student = StudentDatabase.getRandomStudent();
                        }

                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.setCustomAnimations(
                                R.anim.fab_slide_in_from_left,
                                R.anim.fab_slide_out_to_right,
                                R.anim.fab_slide_in_from_right,
                                R.anim.fab_slide_out_to_left);
                        ft.replace(R.id.content_frame, student.fragment);
                        ft.addToBackStack(null);
                        ft.commit();
                    }
                });
            }
            else if (emptyID.contains(i)) // empty seat
            {
                child.setImageResource(R.drawable.empty_seat);
                child.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        new LovelyTextInputDialog(getActivity(), R.style.EditTextTintTheme)
                                .setTopColorRes(R.color.colorPrimary)
                                .setTitle(R.string.seat_checkin)
                                .setMessage(R.string.subject)
                                .setConfirmButton(android.R.string.ok, new LovelyTextInputDialog.OnTextInputConfirmListener()
                                {
                                    @Override
                                    public void onTextInputConfirmed(String text) {
                                        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
                                        child.setImageResource(R.drawable.selected_seat);
                                    }
                                })
                                .show();
                    }
                });
            }

    }
}}
