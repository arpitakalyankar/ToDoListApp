package com.example.listtodo.Adpter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listtodo.Addnewtask;
import com.example.listtodo.MainActivity;
import com.example.listtodo.Model.ToDoM;
import com.example.listtodo.R;
import com.example.listtodo.Utils.DbHelp;

import java.util.List;

public class TodoAdp extends RecyclerView.Adapter<TodoAdp.MyViewHolder> {

    private List<ToDoM> mList;
    private MainActivity activity;
    private DbHelp mydB;
    private int getTask;

    public TodoAdp(DbHelp mydB ,MainActivity activity) {
        this.activity = activity;
        this.mydB = mydB;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tasklayout,parent,false);
      return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final ToDoM item = mList.get(position);
        holder.mcheckBox.setText(item.getTask);
        holder.mcheckBox.setText(item.getStatus());
        holder.mcheckBox.setChecked(toBoolean(item.getStatus()));
        holder.mcheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean isChecked = false;
                if (isChecked){
                  mydB.updatestatus(item.getId(),0);
             }else {
                    mydB.updatestatus(item.getId(),0);

                }

            }

        });
    }
    private int getId() {
        return 0;
    }
    private int getStatus() {
        return 0;
    }
    public boolean toBoolean(int num){
        return num!=0;
}
public Context getContext(){
        return activity;
}
public void setTask(List<ToDoM>mList){
        this.mList = mList;
        notifyDataSetChanged();
}
public void deletTask(int position){
        ToDoM item=mList.get(position);
        mydB.deleteTask(item.getId());
        mList.remove(position);
        notifyItemRemoved(position);
    }
    public void editItem(int position) {
        ToDoM item = mList.get(position);

        Bundle bundle = new Bundle();
        bundle.putInt("id", item.getId());
        bundle.putString("task", item.getTask());

        Addnewtask task = new Addnewtask();
        task.setArguments(bundle);
        task.show(activity.getSupportFragmentManager(), task.getTag());
    }
    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

      CheckBox mcheckBox;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mcheckBox=itemView.findViewById(androidx.appcompat.R.id.checkbox);
        }
    }

}






