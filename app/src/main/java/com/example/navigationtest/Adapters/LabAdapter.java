package com.example.navigationtest.Adapters;

/**
 * Created by karanjaswani on 1/12/18.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.navigationtest.DataTypes.Lab;
import com.example.navigationtest.R;

import java.util.List;

public class LabAdapter extends RecyclerView.Adapter<LabAdapter.LabViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the labs in a list
    private List<Lab> labList;

    private OnLabListner mOnLabListner;


    //getting the context and lab list with constructor
    public LabAdapter(Context mCtx, List<Lab> labList, OnLabListner onLabListner) {
        this.mCtx = mCtx;
        this.labList = labList;
        this.mOnLabListner = onLabListner;
    }

    @Override
    public LabViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_lab, null);
        return new LabViewHolder(view, mOnLabListner);
    }

    @Override
    public void onBindViewHolder(LabViewHolder holder, int position) {
        //getting the product of the specified position
        Lab lab = labList.get(position);

        // binding the data with the viewholder views
        holder.photo.setBackgroundResource(lab.getPhotoDrawableId());
        holder.name.setText(lab.getName());
        holder.seating.setText("" + lab.getAvailableSeats() + " of " + lab.getTotalSeats() + " seats available");
    }


    @Override
    public int getItemCount() {
        return labList.size();
    }


    class LabViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView photo;
        TextView name, seating;

        OnLabListner onLabListner;


        public LabViewHolder(View itemView, OnLabListner onLabListner) {
            super(itemView);

            photo = itemView.findViewById(R.id.layout_lab_photo);
            name = itemView.findViewById(R.id.layout_lab_name);
            seating = itemView.findViewById(R.id.layout_lab_seating);

            this.onLabListner = onLabListner;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
            onLabListner.onLabClick(getAdapterPosition());
        }
    }

    public interface OnLabListner{
        void onLabClick(int position);
    }
}
