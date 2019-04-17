package com.example.navigationtest.SubPages;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.navigationtest.R;

public class Keller4125 extends Fragment {

    TextView text_view_desk_1;
    TextView text_view_desk_2;
    TextView text_view_desk_3;
    TextView text_view_desk_4;
    TextView text_view_desk_5;
    TextView text_view_desk_6;
    TextView text_view_desk_7;
    TextView text_view_desk_8;
    TextView text_view_desk_9;



    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view =  inflater.inflate(R.layout.keller4125_seat_layout, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Keller 4-125");

        text_view_desk_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                // ft.replace(R.id.content_frame, new Profile1());
                ft.addToBackStack(null);
                ft.commit();
            }
        });
    }
}
