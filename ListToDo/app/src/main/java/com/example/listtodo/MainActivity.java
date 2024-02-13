package com.example.listtodo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.example.listtodo.Adpter.TodoAdp;
import com.example.listtodo.Model.ToDoM;
import com.example.listtodo.Utils.DbHelp;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnDialogCloseL {
    private RecyclerView mRecyclerview;
    private FloatingActionButton fab;
    private DbHelp myDB;
    private List<ToDoM> mList;
    private TodoAdp adp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerview = findViewById(R.id.recycleview);
        fab = findViewById(R.id.fab);
        myDB = new DbHelp(MainActivity.this);
        mList = new ArrayList<>();
        adp = new TodoAdp(myDB ,MainActivity.this);

        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setAdapter(adp);

        mList = myDB.getAllTask();
        Collections.reverse(mList);
        adp.setTask(mList);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Addnewtask.newInstance().show(getSupportFragmentManager() ,Addnewtask.TAG);
            }
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecycleVTH(adp));
        itemTouchHelper.attachToRecyclerView(mRecyclerview);
    }

    @Override
    public void onDialogClose(DialogInterface dialogInterface) {
        mList = myDB.getAllTask();
        Collections.reverse(mList);
        adp.setTask(mList);
        adp.notifyDataSetChanged();
    }
}