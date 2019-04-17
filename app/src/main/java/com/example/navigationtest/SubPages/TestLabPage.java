package com.example.navigationtest.SubPages;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


import com.example.navigationtest.DataTypes.Lab;
import com.example.navigationtest.DataTypes.Seat;
import com.example.navigationtest.DataTypes.Student;
import com.example.navigationtest.Databases.Keller1200SeatDatabase;
import com.example.navigationtest.Databases.StudentDatabase;
import com.example.navigationtest.R;
import com.yarolegovich.lovelydialog.LovelyInfoDialog;


import java.util.ArrayList;

import static java.lang.Math.random;

public class TestLabPage extends Fragment {
    private Lab lab;
    final Student currentStudent = StudentDatabase.getStudent(Student.currentX500);
    ArrayList<Seat> seatList;
    int labSize;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        seatList = Keller1200SeatDatabase.getCurSeats();
        labSize = Keller1200SeatDatabase.getLab_size();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_keller1200, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set the activity bar title.
        getActivity().setTitle("Keller 1-200");


        // Iterate all the child views under the relative layout
        for (final Seat curSeat : seatList){
            int cur_seat_number = curSeat.getSeat_Number();
            String button_id_string = "seat" + cur_seat_number;
            int button_id = getResources().getIdentifier(button_id_string, "id",
                    "com.example.navigationtest");
            final ImageButton cur_Button = (ImageButton)view.findViewById(button_id);

            if (curSeat.getX500() != null && curSeat.isNeed_partner()){
                final Student student = StudentDatabase.getStudent(curSeat.getX500());
//                student.setSeated(true);
                if (student.isSeated()){
                    cur_Button.setImageResource(R.drawable.potential_partner);
                    cur_Button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
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
                else{
                    cur_Button.setImageResource(R.drawable.empty_seat);
                }
            }
            else if (curSeat.getX500() != null && !(curSeat.isNeed_partner())){
                cur_Button.setImageResource(R.drawable.unavailable_seat);
            }
            else{
                cur_Button.setImageResource(R.drawable.empty_seat);

                cur_Button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!currentStudent.isSeated()){
                            cur_Button.setImageResource(R.drawable.potential_partner);
                            StudentDatabase.getStudent(Student.currentX500).setSeated(true);
//                            curSeat.setX500(currentStudent.getX500());
//                            curSeat.setNeed_partner(true);
                        }
                    }
                });

//                @Override
//                public void onClick(View v) {
//                    new LovelyInfoDialog(getActivity())
//                            .setTopColorRes(R.color.darkBlueGrey)
//                            .setTitle(R.string.taking_seat)
//                            .show();
//                    cur_Button.setImageResource(R.drawable.potential_partner);
//                    currentStudent.setSeated(true);
//                }
            }
        }//end of iterating seat list
    }// end of onViewCreated




}
