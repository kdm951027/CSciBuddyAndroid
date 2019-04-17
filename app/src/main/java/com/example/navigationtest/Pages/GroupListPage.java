package com.example.navigationtest.Pages;

/**
 * Created by karanjaswani on 2/7/18.
 */

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.navigationtest.Adapters.LabAdapter;
import com.example.navigationtest.DataTypes.Group;
import com.example.navigationtest.Databases.GroupDatabase;
import com.example.navigationtest.Databases.LabDatabase;
import com.example.navigationtest.R;
import com.example.navigationtest.Adapters.GroupAdapter;
import com.example.navigationtest.SubPages.CreateGroupPage;
import com.example.navigationtest.SubPages.GroupPage;

import java.util.List;

public class GroupListPage extends Fragment implements GroupAdapter.OnGroupListener {

    //the recyclerview
    RecyclerView recyclerView;

    //Lab list
    List<Group> groupList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        groupList = GroupDatabase.getAllGroups();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the view.
        return inflater.inflate(R.layout.fragment_group_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get the groupList RecyclerView.
        RecyclerView groupListRecycler = (RecyclerView)view.findViewById(R.id.groupList_recycler);
        groupListRecycler.setHasFixedSize(true);
        groupListRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Create the GroupAdapter and set it to the recycler.
        GroupAdapter groupAdapter = new GroupAdapter(getContext(), groupList, this);
        groupListRecycler.setAdapter(groupAdapter);

        // Get the new group button
        FloatingActionButton newGroupButton = view.findViewById(R.id.groupList_newGroupButton);
        newGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment createGroup = new CreateGroupPage();

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.setCustomAnimations(
                        R.anim.fab_slide_in_from_left,
                        R.anim.fab_slide_out_to_right,
                        R.anim.fab_slide_in_from_right,
                        R.anim.fab_slide_out_to_left);
                ft.replace(R.id.content_frame, createGroup);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        // Set the activity bar title.
        getActivity().setTitle("Study Groups");
    }

    @Override
    public void onGroupClick(int position) {
        // get the fragment from the group at position and switch to it
        Group group = groupList.get(position);

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
