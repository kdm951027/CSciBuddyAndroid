package com.example.navigationtest.Pages;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.navigationtest.Adapters.CourseAdapter;
import com.example.navigationtest.Adapters.GroupAdapter;
import com.example.navigationtest.Adapters.StudentAdapter;
import com.example.navigationtest.DataTypes.Course;
import com.example.navigationtest.DataTypes.Group;
import com.example.navigationtest.DataTypes.Student;
import com.example.navigationtest.R;
import com.yarolegovich.lovelydialog.LovelyInfoDialog;
import com.yarolegovich.lovelydialog.LovelyTextInputDialog;

import org.w3c.dom.Text;

import java.util.List;


public class ProfilePage extends Fragment implements GroupAdapter.OnGroupListener
{

    Student student;
    List<Group> groups;
    List<Course> courses;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize any required resources here (e.g. lists).

        if (savedInstanceState == null) {
            Bundle b = getArguments();
            student = b.getParcelable("student");
            groups = student.getCurrentGroups();
            courses = student.getEnrolledCourses();
        }
        else {
            student = savedInstanceState.getParcelable("student");
            groups = student.getCurrentGroups();
            courses = student.getEnrolledCourses();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        ImageView studentPhoto = (ImageView)view.findViewById(R.id.profile_photo);
        studentPhoto.setBackgroundResource(student.getPhotoDrawableId());

        ImageView studentBackground = (ImageView)view.findViewById(R.id.profile_background);
        studentBackground.setBackgroundResource(student.getBackgroundDrawableId());

        final TextView studentName = (TextView)view.findViewById(R.id.profile_name);
        studentName.setText(student.getName());

        final TextView studentAbout = (TextView)view.findViewById(R.id.profile_about);
        studentAbout.setText(student.getAbout());

        final TextView studentDescription = (TextView)view.findViewById(R.id.profile_description);
        studentDescription.setText(student.getMajor() + " | " + student.getYear() + " | " + student.getX500());

        final Button studentEmail = (Button)view.findViewById(R.id.profile_emailButton);
        studentEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:" + student.getX500()+"@umn.edu"));
                startActivity(emailIntent);
            }
        });

        // Add the course recycler
        RecyclerView enrolledCoursesRecycler = (RecyclerView)view.findViewById(R.id.profile_enrolledCourses_recycler);
        enrolledCoursesRecycler.setHasFixedSize(true);
        enrolledCoursesRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        CourseAdapter courseAdapter = new CourseAdapter(getContext(), courses, null);
        enrolledCoursesRecycler.setAdapter(courseAdapter);

        // Add the group recycler.
        RecyclerView studentGroupsRecycler = (RecyclerView)view.findViewById(R.id.profile_group_recycler);
        studentGroupsRecycler.setHasFixedSize(true);
        studentGroupsRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        GroupAdapter groupAdapter = new GroupAdapter(getContext(), groups, this);
        studentGroupsRecycler.setAdapter(groupAdapter);

        // Remove the edit button if its not the current student.
        if (!student.getX500().equals(Student.currentX500)) {
            View fab = view.findViewById(R.id.profile_edit_fab);
            View fab2 = view.findViewById(R.id.leave_seat_fab);
            ((ViewGroup) fab.getParent()).removeView(fab);
            ((ViewGroup) fab2.getParent()).removeView(fab2);
        }
        else {
            if (student.isSeated()){
                final View seat_leave_fab = view.findViewById(R.id.leave_seat_fab);
                seat_leave_fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new LovelyInfoDialog(getActivity())
                                .setTopColorRes(R.color.darkBlueGrey)
                                .setTitle(R.string.leave_message)
                                .show();
                        student.setSeated(false);
                        ((ViewGroup) seat_leave_fab.getParent()).removeView(seat_leave_fab);
                    }
                });

            }
            else{
                    View fab2 = view.findViewById(R.id.leave_seat_fab);
                    ((ViewGroup) fab2.getParent()).removeView(fab2);
                }

            // Set the onClicks for the FAB and fields...
            View fab = view.findViewById(R.id.profile_edit_fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new LovelyInfoDialog(getActivity())
                            .setTopColorRes(R.color.darkBlueGrey)
                            .setTitle(R.string.profile_info_message)
                            .show();
                }
            });

            studentName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new LovelyTextInputDialog(getActivity(), R.style.EditTextTintTheme)
                            .setTopColorRes(R.color.colorPrimary)
                            .setMessage(R.string.profile_name)
                            .setInitialInput(student.getName())
                            .setConfirmButton(android.R.string.ok, new LovelyTextInputDialog.OnTextInputConfirmListener() {
                                @Override
                                public void onTextInputConfirmed(String text) {
                                    Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
                                    studentName.setText(text);
                                    student.setName(text);
                                }
                            })
                            .show();
                }
            });

            studentAbout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new LovelyTextInputDialog(getActivity(), R.style.EditTextTintTheme)
                            .setTopColorRes(R.color.colorPrimary)
                            .setMessage(R.string.profile_about)
                            .setInitialInput(student.getAbout())
                            .setConfirmButton(android.R.string.ok, new LovelyTextInputDialog.OnTextInputConfirmListener() {
                                @Override
                                public void onTextInputConfirmed(String text) {
                                    Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
                                    studentAbout.setText(text);
                                    student.setAbout(text);
                                }
                            })
                            .show();
                }
            });
        }

        // Set the app bar title.
        getActivity().setTitle(student.getName());
    }

    @Override
    public void onGroupClick(int position) {
        // get the fragment from the group at position and switch to it
        Group group = groups.get(position);

        if (group != null && group.fragment != null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.setCustomAnimations(
                    R.anim.fab_slide_in_from_left,
                    R.anim.fab_slide_out_to_right,
                    R.anim.fab_slide_in_from_right,
                    R.anim.fab_slide_out_to_left);
            ft.replace(R.id.content_frame, group.fragment);
            ft.addToBackStack(null);
            ft.commit();
        }
    }
}
