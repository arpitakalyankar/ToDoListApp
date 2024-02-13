package com.example.listtodo;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.listtodo.Model.ToDoM;
import com.example.listtodo.Utils.DbHelp;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


    public class Addnewtask extends BottomSheetDialogFragment {

        public static final String TAG = "Addnewtask";
        //widgets
        private EditText mEditText;
        private Button msaveButton;
        private DbHelp mydb;

        public static Addnewtask newInstance() {
            return new Addnewtask();
        }
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.addnewtask, container, false);
            return v;
        }
        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            mEditText = view.findViewById(R.id.edittext);
            msaveButton = view.findViewById(R.id.button_save);

            mydb = new DbHelp(getActivity());
            boolean isupdate = false;

            Bundle bundle = getArguments();
            if (bundle != null) {
                isupdate = true;
                String task = bundle.getString("task");
                mEditText.setText(task);

                if (task.length() > 0) {
                    msaveButton.setEnabled(false);
                }
            }
            mEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (toString().equals("")) {
                        msaveButton.setEnabled(false);
                        msaveButton.setBackgroundColor(Color.GRAY);
                    } else {
                        msaveButton.setEnabled(true);
                        msaveButton.setBackgroundColor(getResources().getColor(R.color.purple_200));
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            boolean finalIsupdate = isupdate;
            msaveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String text = mEditText.getText().toString();
                    if (finalIsupdate) {
                        mydb.updateTask(bundle.getInt("ID"), text);
                    } else {
                        ToDoM item = new ToDoM();
                        item.setTask(text);
                        item.setStatus(0);
                        mydb.insertTask(item);
                    }
                    dismiss();
                }
            });
        }

        @Override
        public void onDismiss(@NonNull DialogInterface dialog) {
            super.onDismiss(dialog);
            Activity activity = getActivity();
            if (activity instanceof OnDialogCloseL){
                ((OnDialogCloseL)activity).onDialogClose(dialog);
            }
        }
    }



