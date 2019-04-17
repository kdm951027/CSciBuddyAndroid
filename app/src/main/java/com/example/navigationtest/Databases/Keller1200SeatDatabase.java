package com.example.navigationtest.Databases;

import com.example.navigationtest.DataTypes.Student;

import java.util.ArrayList;
import java.util.Random;
import com.example.navigationtest.DataTypes.Seat;


public class Keller1200SeatDatabase {

    private static ArrayList<Seat> curSeats;
    private static int lab_size = 18;
    private static int cur_seat_count = 1;

    public Keller1200SeatDatabase() {
        curSeats = generateRandomSeats(lab_size);
    }

    static Seat generateRandomSeat() {
        Random r = new Random();
        Seat newSeat;

        boolean seated = r.nextBoolean();
        if (seated){
            Student random_student = StudentDatabase.getRandomStudent();
            random_student.setSeated(true);
            newSeat = new Seat(
                    random_student.getX500(),
                    cur_seat_count,
                    r.nextBoolean(),
                    false
            );
        }
        else{
            newSeat = new Seat(
                    null,
                    cur_seat_count,
                    false,
                    true
            );
        }
        cur_seat_count++;
        return newSeat;
    }

    static ArrayList<Seat> generateRandomSeats(int howMany) {
        ArrayList<Seat> subset = new ArrayList<Seat>();
        for (int i = 0; i < howMany; i++) {
            subset.add(generateRandomSeat());
        }
        return subset;
    }

    public static ArrayList<Seat> getCurSeats() {
        return curSeats;
    }

    public static void setCurSeats(ArrayList<Seat> curSeats) {
        Keller1200SeatDatabase.curSeats = curSeats;
    }

    public static int getLab_size() {
        return lab_size;
    }

    public static void setLab_size(int lab_size) {
        Keller1200SeatDatabase.lab_size = lab_size;
    }
}
