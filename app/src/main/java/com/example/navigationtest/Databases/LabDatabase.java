package com.example.navigationtest.Databases;

import android.os.Bundle;

import com.example.navigationtest.DataTypes.Lab;
import com.example.navigationtest.R;
import com.example.navigationtest.SubPages.LabPage;
import com.example.navigationtest.SubPages.TestLabPage;

import java.util.ArrayList;
import java.util.List;

public class LabDatabase {
    // The list of courses.
    private static List<Lab> labs = new ArrayList<Lab>() {
        {
            add(new Lab(
                    "Keller 1-200",
                    18,
                    6,
                    R.drawable.lab_keller_1_200
            ));

            add(new Lab(
                    "Keller 1-250",
                    42,
                    15,
                    R.drawable.lab_keller_1_250
            ));

            add(new Lab(
                    "Keller 1-254",
                    19,
                    8,
                    R.drawable.lab_keller_1_254
            ));

            add(new Lab(
                    "Keller 4-240",
                    10,
                    10,
                    R.drawable.lab_keller_4_240
            ));

            add(new Lab(
                    "Keller 4-250",
                    50,
                    17,
                    R.drawable.lab_keller_4_250
            ));

            add(new Lab(
                    "Lind 150",
                    50,
                    35,
                    R.drawable.lab_lind_150
            ));

            add(new Lab(
                    "Lind 40",
                    43,
                    18,
                    R.drawable.lab_lind_40
            ));
        }
    };

    public LabDatabase() {
        for (Lab lab : labs) {
//            lab.fragment = new LabPage();
            lab.fragment = new TestLabPage();

            Bundle b = new Bundle();
            b.putParcelable("lab", lab);
            lab.fragment.setArguments(b);
        }
    }

    public static List<Lab> getAllLabs() {
        return labs;
    }
}
