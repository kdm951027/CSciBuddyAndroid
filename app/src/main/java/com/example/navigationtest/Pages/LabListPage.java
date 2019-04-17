package com.example.navigationtest.Pages;

/**
 * Created by karanjaswani on 2/7/18.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;

import com.example.navigationtest.DataTypes.Lab;
import com.example.navigationtest.Databases.LabDatabase;
import com.example.navigationtest.Adapters.LabAdapter;
import com.example.navigationtest.R;

import java.util.List;

import static android.content.ContentValues.TAG;


public class LabListPage extends Fragment implements LabAdapter.OnLabListner{

    List<Lab> labList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        labList = LabDatabase.getAllLabs();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lab_list, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set the lab list.
        RecyclerView labListRecycler = (RecyclerView)view.findViewById(R.id.labList_recycler);
        labListRecycler.setHasFixedSize(true);
        labListRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        LabAdapter labAdapter = new LabAdapter(getContext(), labList, this);
        labListRecycler.setAdapter(labAdapter);

        // Set the title bar.
        getActivity().setTitle("Labs");
    }

    @Override
    public void onLabClick(int position) {
        Lab lab = LabDatabase.getAllLabs().get(position);

        if (lab.fragment != null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.setCustomAnimations(
                    R.anim.fab_slide_in_from_left,
                    R.anim.fab_slide_out_to_right,
                    R.anim.fab_slide_in_from_right,
                    R.anim.fab_slide_out_to_left);
            ft.replace(R.id.content_frame, lab.fragment);
            ft.addToBackStack(null);
            ft.commit();
        }
    }
}
