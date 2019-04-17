package com.example.navigationtest.Adapters;

/**
 * Created by karanjaswani on 1/12/18.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.navigationtest.DataTypes.Group;
import com.example.navigationtest.R;

import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupViewHolder> {

    // this context we will use to inflate the layout
    private Context mCtx;

    // we are storing all the labs in a list
    private List<Group> groupList;

    private OnGroupListener onGroupListener;

    // getting the context and lab list with constructor
    public GroupAdapter(Context mCtx, List<Group> groupList, OnGroupListener onGroupListener) {
        this.mCtx = mCtx;
        this.groupList = groupList;
        this.onGroupListener = onGroupListener;
    }

    @Override
    public GroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_group, null);
        return new GroupViewHolder(view, onGroupListener);
    }

    @Override
    public void onBindViewHolder(GroupViewHolder holder, int position) {
        // getting the product of the specified position
        Group group = groupList.get(position);

        // binding the data with the viewholder views
        holder.className.setText(group.getCourse().getDepartment() + " " + group.getCourse().getNumber());
        holder.description.setText(group.getDescription());
        holder.members.setText(group.getMembers().toString().substring(1, group.getMembers().toString().length() - 1));
        holder.timeAndDate.setText(group.getTime() + " " + group.getDate() + " " + group.getPlace());

    }

    @Override
    public int getItemCount() {
        return groupList.size();
    }


    class GroupViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView className, description, members, timeAndDate;
        OnGroupListener onGroupListener;

        public GroupViewHolder(View itemView, OnGroupListener onGroupListener) {
            super(itemView);

            className = itemView.findViewById(R.id.layout_group_className);
            description = itemView.findViewById(R.id.layout_group_description);
            members = itemView.findViewById(R.id.layout_group_members);
            timeAndDate = itemView.findViewById(R.id.layout_group_timeAndDate);

            this.onGroupListener = onGroupListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
            onGroupListener.onGroupClick(getAdapterPosition());
        }

    }

    public interface OnGroupListener {
        void onGroupClick(int position);
    }
}
