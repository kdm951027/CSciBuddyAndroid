package com.example.navigationtest.SubPages;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.navigationtest.Adapters.StudentAdapter;
import com.example.navigationtest.DataTypes.Group;
import com.example.navigationtest.DataTypes.Student;
import com.example.navigationtest.Databases.StudentDatabase;
import com.example.navigationtest.R;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;

import java.util.List;


public class GroupPage extends Fragment implements StudentAdapter.OnStudentListener {
    private Group group;
    private List<Student> members;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the group for this fragment from the bundle.
        if (savedInstanceState == null) {
            Bundle b = getArguments();
            this.group = b.getParcelable("group");
            members = group.getMembers();
        }
        else {
            this.group = savedInstanceState.getParcelable("group");
            members = group.getMembers();
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_group, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView classNumber = (TextView)view.findViewById(R.id.group_classNumber);
         classNumber.setText(group.getCourse().getDepartment() + " " + group.getCourse().getNumber());

        TextView className = (TextView)view.findViewById(R.id.group_className);
        className.setText(group.getCourse().getTitle());

        TextView groupDescription = (TextView)view.findViewById(R.id.group_description);
        groupDescription.setText(group.getDescription());

        RecyclerView groupMembersRecycler = (RecyclerView)view.findViewById(R.id.group_member_recycler);
        groupMembersRecycler.setHasFixedSize(true);
        groupMembersRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        StudentAdapter studentAdapter = new StudentAdapter(getContext(), members, this);
        groupMembersRecycler.setAdapter(studentAdapter);

        TextView groupTimeAndDate = (TextView)view.findViewById(R.id.group_timeAndDate);
        groupTimeAndDate.setText("" + group.getTime() + " " + group.getDate() + " " + group.getPlace());

        final Button joinButton = (Button)view.findViewById(R.id.group_joinButton);

        final Student currentStudent = StudentDatabase.getStudent(Student.currentX500);

        if (group.isInGroup(currentStudent)) {
            joinButton.setText(R.string.group_leave);
        }
        else {
            joinButton.setText(R.string.group_join);
        }

        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (group.isInGroup(currentStudent)) {

                    new LovelyStandardDialog(getActivity(), LovelyStandardDialog.ButtonLayout.VERTICAL)
                            .setTopColorRes(R.color.indigo)
                            .setButtonsColorRes(R.color.darkDeepOrange)
                            .setIcon(R.drawable.ic_info_outline_white_36dp)
                            .setTitle(R.string.group_leave_confirm)
                            .setPositiveButton(R.string.group_confirm, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(getActivity(), R.string.group_left, Toast.LENGTH_SHORT).show();
                                    group.removeFromGroup(currentStudent);
                                    currentStudent.leaveGroup(group);
                                    joinButton.setText(R.string.group_join);
                                }
                            })
                            .setNegativeButton(R.string.group_cancel, null)
                            .show();
                }
                else {
                    new LovelyStandardDialog(getActivity(), LovelyStandardDialog.ButtonLayout.VERTICAL)
                            .setTopColorRes(R.color.indigo)
                            .setButtonsColorRes(R.color.darkDeepOrange)
                            .setIcon(R.drawable.ic_info_outline_white_36dp)
                            .setTitle(R.string.group_join_confirm)
                            .setPositiveButton(R.string.group_confirm, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(getActivity(), R.string.group_joined, Toast.LENGTH_SHORT).show();
                                    group.addToGroup(currentStudent);
                                    currentStudent.joinGroup(group);
                                    joinButton.setText(R.string.group_leave);
                                }
                            })
                            .setNegativeButton(R.string.group_cancel, null)
                            .show();
                }
            }
        });

        // Set the activity bar title.
        getActivity().setTitle("Group Info");
    }

    @Override
    public void onStudentClick(int position) {
        // get the fragment from the group at position and switch to it
        Student student = members.get(position);

        if (student != null && student.fragment != null) {
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
    }
}
