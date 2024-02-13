package com.example.listtodo;

import android.content.ClipData;
import android.content.DialogInterface;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listtodo.Adpter.TodoAdp;

public class RecycleVTH extends ItemTouchHelper.SimpleCallback {

    private TodoAdp adp;

    public RecycleVTH(TodoAdp adp){
        super(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.adp = adp;
    }
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        final int position = viewHolder.getAdapterPosition();
        if (direction ==ItemTouchHelper.RIGHT){
            AlertDialog.Builder builder = new AlertDialog.Builder(adp.getContext());
            builder.setTitle("Delete task");
            builder.setMessage("Are You sure ?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    adp.deletTask(position);
                }
            });
             builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    adp.notifyItemChanged(position);
                }
            });

        }else {
            adp.editItem(position);


        }
    }
}
