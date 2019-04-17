package com.example.navigationtest.Pages;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.navigationtest.Adapters.GroupAdapter;
import com.example.navigationtest.DataTypes.Group;
import com.example.navigationtest.DataTypes.Student;
import com.example.navigationtest.Databases.StudentDatabase;
import com.example.navigationtest.R;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends Fragment implements GroupAdapter.OnGroupListener {
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

//        //getting the recyclerview from xml
//        recyclerView = (RecyclerView) view.findViewById(R.id.home_recycler);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//
//        //creating recyclerview adapter
//        List<HomeBox> list = new ArrayList<HomeBox>();
//        list.add(new HomeBox("Keller Hall", 0.5f, "4 people studying CSCI 5115"));
//        list.add(new HomeBox("Keller Hall", 0.5f, "2 people studying CSCI 4611"));
//        list.add(new HomeBox("Mechanical Engineering", 0.56f, "3 people studying CSCI 5611"));
//
//        list.add(new HomeBox("Rapson Hall", 0.7f, "3 people studying CSCI 4611"));
//        list.add(new HomeBox("Blegen Hall", 0.8f, "2 people studying CSCI 5609"));
//        list.add(new HomeBox("Koltoff Hall", 0.4f, "7 people studying CSCI 0000"));
//        HomeAdapter adapter = new HomeAdapter(getContext(), list);
//
//        //setting adapter to recyclerview
//        recyclerView.setAdapter(adapter);
//

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView groupRecycler = (RecyclerView)view.findViewById(R.id.home_group_recycler);
        groupRecycler.setHasFixedSize(true);
        groupRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        GroupAdapter groupAdapter = new GroupAdapter(getContext(), StudentDatabase.getStudent(Student.currentX500).getCurrentGroups(), this);
        groupRecycler.setAdapter(groupAdapter);

        getActivity().setTitle("Home");
    }

    @Override
    public void onGroupClick(int position) {
        // get the fragment from the group at position and switch to it
        Group group = StudentDatabase.getStudent(Student.currentX500).getCurrentGroups().get(position);

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
