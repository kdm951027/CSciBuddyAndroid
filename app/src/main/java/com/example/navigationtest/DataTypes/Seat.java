package com.example.navigationtest.DataTypes;

public class Seat {

    private String x500;
    private int seat_Number;
    private boolean need_partner;
    private boolean available;

    public Seat(String x500, int seat_Number, boolean need_partner, boolean available) {
        this.x500 = x500;
        this.seat_Number = seat_Number;
        this.need_partner = need_partner;
        this.available = available;
    }


    public String getX500() {
        return x500;
    }

    public void setX500(String x500) {
        this.x500 = x500;
    }

    public int getSeat_Number() {
        return seat_Number;
    }

    public void setSeat_Number(int seat_Number) {
        this.seat_Number = seat_Number;
    }

    public boolean isNeed_partner() {
        return need_partner;
    }

    public void setNeed_partner(boolean need_partner) {
        this.need_partner = need_partner;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
