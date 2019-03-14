package com.example.assignment4_sqlite;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    DatabaseHelper mDatabaseHelper;
    private Button btnAdd, btnASeeAll, btnUpdate, btnDelete;
    private EditText editText1, editText2, editText3, editText4;
    String name, id, email, tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Context context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);
        btnAdd = findViewById(R.id.btnAdd);
        btnASeeAll = findViewById(R.id.btnSeeAll);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        mDatabaseHelper = new DatabaseHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = editText1.getText().toString();
                email = editText2.getText().toString();
                tv = editText3.getText().toString();
                id = editText4.getText().toString();
                if(name.length() != 0 && email.length()!= 0 &&
                   tv.length() != 0){
                    addData(name, email, tv, id);
                    editText1.setText("");
                    editText2.setText("");
                    editText3.setText("");
                    editText4.setText("");
                }else{
                    toastMessage("Blank not permitted. Put something");
                }
            }
        });

        btnASeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                String str = "";
                Cursor data;
                name = editText1.getText().toString();
                email = editText2.getText().toString();
                tv = editText3.getText().toString();
                id = editText4.getText().toString();
                if (name.length() != 0 && id.length() != 0) {
                    data = mDatabaseHelper.getData(name, id);
                } else {
                    if (name.length() != 0) {
                        data = mDatabaseHelper.getData(name);
                    } else {
                        data = mDatabaseHelper.getData();
                    }
                }
//                ArrayList<String> listData = new ArrayList <>();
                if (data.getCount() > 0) {
                    if (data.moveToFirst()) {
                        do {
                            Log.d(TAG, "Displaying data inside main activity");
                            Log.d(TAG, "MainActivity" + data.getString(0));
                            Log.d(TAG, "MainActivity" + data.getString(1));
                            Log.d(TAG, "MainActivity" + data.getString(2));
                            Log.d(TAG, "MainActivity" + data.getString(3));


                            int i = data.getColumnCount();
                            for (int j = 0; j < i; j++) {

                                switch (j){
                                    case 0:
                                        str = str.concat("ID: ");
                                        break;
                                    case 1:
                                        str = str.concat("Name: ");
                                        break;
                                    case 2:
                                        str = str.concat("Email: ");
                                        break;
                                    case 3:
                                        str = str.concat("Fav. tv show: ");
                                        break;
                                }

                                str = str.concat(data.getString(j));
                                str = str.concat("\n");
                            }
//                            if (data.moveToNext()) {
                                str = str.concat("---------------------------------------------\n");
//                            }
                        } while (data.moveToNext());
                    }

                    alert.setTitle("Result:");
                    alert.setMessage(str);
                    alert.setNeutralButton("Ok", null);
                    alert.show();
//                AlertFragment blankFragment = new AlertFragment();
//                blankFragment.show(getSupportFragmentManager(), "FRAGMENT_TAG");
//                Intent intent = new Intent(MainActivity.this, ListDataActivity.class);
//                startActivity(intent);
                }else
                    toastMessage("Data not found");
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = editText1.getText().toString();
                email = editText2.getText().toString();
                tv = editText3.getText().toString();
                id = editText4.getText().toString();

                if(name.length() != 0 && email.length()!= 0 &&
                        tv.length() != 0 && id.length() != 0){
                    updateData(name, email, tv, id);
                    editText1.setText("");
                    editText2.setText("");
                    editText3.setText("");
                    editText4.setText("");
                }else{
                    toastMessage("Blank not permitted. Put something");
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = editText1.getText().toString();
                email = editText2.getText().toString();
                tv = editText3.getText().toString();
                id = editText4.getText().toString();

                if(name.length() != 0 && id.length() != 0){
                    deleteData(name, email, tv, id);
                    editText1.setText("");
                    editText2.setText("");
                    editText3.setText("");
                    editText4.setText("");
                }else{
                    toastMessage("Blank not permitted for name & id. Put something");
                }
            }
        });
    }

    private void deleteData(String name, String email, String tv, String id) {
        Cursor data = mDatabaseHelper.getData(name,id);
        if (data.getCount()>=1){
            boolean deleteData = mDatabaseHelper.deleteData(name,id);

            if (deleteData)
                toastMessage("Data successfully deleted");
            else
                toastMessage("Data could not be deleted");
        }else
            toastMessage("Data not found");
    }

    private void updateData(String name, String email, String tv, String id) {

        Cursor data = mDatabaseHelper.getData(name);

        Log.d(TAG, Integer.toString(data.getCount()));
        if (data.getCount()>=1) {
            if (data.moveToFirst()) {
                do {
                    Log.d(TAG, "Displaying data inside main activity");
                    Log.d(TAG, "MainActivity" + data.getString(0));
                    Log.d(TAG, "MainActivity" + data.getString(1));
                    Log.d(TAG, "MainActivity" + data.getString(2));
                    Log.d(TAG, "MainActivity" + data.getString(3));
                } while (data.moveToNext());
            }
                Cursor data2 = mDatabaseHelper.getData(name,id);

            if (data2.getCount()>=1) {
                if (data2.moveToFirst()) {
                    do {
                        Log.d(TAG, "Displaying data inside main activity");
                        Log.d(TAG, "MainActivity2" + data2.getString(0));
                        Log.d(TAG, "MainActivity2" + data2.getString(1));
                        Log.d(TAG, "MainActivity2" + data2.getString(2));
                        Log.d(TAG, "MainActivity2" + data2.getString(3));
                    } while (data2.moveToNext());
                }
                boolean updateData = mDatabaseHelper.updateData(name, email, tv, id);

                if (updateData)
                    toastMessage("Data successfully updated");
                else
                    toastMessage("Data could not be updated");
            }else{
                addData(name, email, tv, id);
                toastMessage("Data successfully updated");
            }
        }else {
            toastMessage("Data not found");
        }
    }

    public void addData(String name, String email, String tv, String id){
        boolean insertData = mDatabaseHelper.addData(name, email, tv, id);

        if(insertData)
            toastMessage("Data successfully inserted");
        else
            toastMessage("Duplicate data");
    }

    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
