package com.example.navigationtest.SubPages;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.navigationtest.DataTypes.Course;
import com.example.navigationtest.DataTypes.Group;
import com.example.navigationtest.DataTypes.Student;
import com.example.navigationtest.Databases.CourseDatabase;
import com.example.navigationtest.Databases.GroupDatabase;
import com.example.navigationtest.Databases.StudentDatabase;
import com.example.navigationtest.R;
import com.yarolegovich.lovelydialog.LovelyTextInputDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class CreateGroupPage extends Fragment {

    Course course;
    String description, time, date, place;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat timeSdf = new SimpleDateFormat("hh:mm a");
        SimpleDateFormat dateSdf = new SimpleDateFormat("MMMM dd, YYYY ");

        description = getString(R.string.group_create_description);
        time = timeSdf.format(c.getTime());
        date = dateSdf.format(c.getTime());
        place = getString(R.string.group_create_defaultPlace);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_group_create, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get the button and set on click etc etc...
        // in callback, DO NOT add to back stack --- then this fragment will delete itself!

        // Get the spinner and populate it with all possible classes.
        Spinner classSpinner = (Spinner)view.findViewById(R.id.group_create_classSpinner);

        List<Course> allCourses = CourseDatabase.getAllCourses();
        final String[] courseTitles = new String[allCourses.size()];
        for (int i = 0; i < courseTitles.length; i++) {
            courseTitles[i] = allCourses.get(i).getId();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, courseTitles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classSpinner.setAdapter(adapter);

        classSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                course = CourseDatabase.getCourseById(courseTitles[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                course = null;
            }
        });

        // Set group description
        final TextView groupDescription = (TextView)view.findViewById(R.id.group_create_description);
        groupDescription.setText(description);
        groupDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LovelyTextInputDialog(getActivity(), R.style.EditTextTintTheme)
                        .setTopColorRes(R.color.colorPrimary)
                        .setMessage("Enter a short group description.")
                        .setInitialInput(groupDescription.getText().toString())
                        .setConfirmButton(android.R.string.ok, new LovelyTextInputDialog.OnTextInputConfirmListener() {
                            @Override
                            public void onTextInputConfirmed(String text) {
                                Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
                                groupDescription.setText(text);
                                description = text;
                            }
                        })
                        .show();
            }
        });

        // Set group time
        final TextView groupTime = (TextView)view.findViewById(R.id.group_create_time);
        groupTime.setText(time);
        groupTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LovelyTextInputDialog(getActivity(), R.style.EditTextTintTheme)
                        .setTopColorRes(R.color.colorPrimary)
                        .setMessage("Choose a time to meet.")
                        .setInitialInput(groupTime.getText().toString())
                        .setConfirmButton(android.R.string.ok, new LovelyTextInputDialog.OnTextInputConfirmListener() {
                            @Override
                            public void onTextInputConfirmed(String text) {
                                Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
                                groupTime.setText(text);
                                time = text;
                            }
                        })
                        .show();
            }
        });

        // Set group date
        final TextView groupDate = (TextView)view.findViewById(R.id.group_create_date);
        groupDate.setText(date);
        groupDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LovelyTextInputDialog(getActivity(), R.style.EditTextTintTheme)
                        .setTopColorRes(R.color.colorPrimary)
                        .setMessage("Choose a day to meet.")
                        .setInitialInput(groupDate.getText().toString())
                        .setConfirmButton(android.R.string.ok, new LovelyTextInputDialog.OnTextInputConfirmListener() {
                            @Override
                            public void onTextInputConfirmed(String text) {
                                Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
                                groupDate.setText(text);
                                date = text;
                            }
                        })
                        .show();
            }
        });

        // Set group place
        final TextView groupPlace = (TextView)view.findViewById(R.id.group_create_place);
        groupPlace.setText(place);
        groupPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LovelyTextInputDialog(getActivity(), R.style.EditTextTintTheme)
                        .setTopColorRes(R.color.colorPrimary)
                        .setMessage("Choose a place to meet.")
                        .setInitialInput(groupPlace.getText().toString())
                        .setConfirmButton(android.R.string.ok, new LovelyTextInputDialog.OnTextInputConfirmListener() {
                            @Override
                            public void onTextInputConfirmed(String text) {
                                Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
                                groupPlace.setText(text);
                                place = text;
                            }
                        })
                        .show();
            }
        });



        Button createButton = (Button)view.findViewById(R.id.group_create_button);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Group group = new Group(
                        course,
                        StudentDatabase.getStudents(new String[]{Student.currentX500}),
                        description,
                        time,
                        date,
                        place
                );
                GroupDatabase.addGroup(group);
                getFragmentManager().popBackStackImmediate();
                Toast.makeText(getActivity(), "Group created.", Toast.LENGTH_SHORT).show();
            }
        });

        // Set the activity bar title.
        getActivity().setTitle("Create a Group");
    }

}
