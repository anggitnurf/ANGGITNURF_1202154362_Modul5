package com.anggit.android.anggitnurf_1202154362_modul5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddTodo extends AppCompatActivity {
    //declare variable
    EditText ToDo, Description, Priority;
    database dtbase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);
        //sset tittle to : "add to do"
        setTitle("Add To Do");

        //access id text from layout access
        ToDo = (EditText) findViewById(R.id.editTodo);
        Description = (EditText) findViewById(R.id.editDesc);
        Priority = (EditText) findViewById(R.id.editPriority);
        dtbase = new database(this);
    }

    @Override
    public void onBackPressed() {
        //intent add to do to list to do
        Intent intent = new Intent(AddTodo.this, List.class);
        //start intent
        startActivity(intent);
        //closing activity after intent
        this.finish();
    }

    //method start when button add clicked
    public void tambah(View view) {
        //get text from insert variable
       String todo = ToDo.getText().toString();
       String desc  = Description.getText().toString();
       String prior = Priority.getText().toString();

       //if data is empty
       if(todo.isEmpty()||desc.isEmpty()||prior.isEmpty()){
           Toast.makeText(AddTodo.this,"Insert Your To Do",Toast.LENGTH_LONG).show();
           //data is inserted
       }else {
           //getting data inserted
           dtbase.inputdata(new AddData(ToDo.getText().toString(), Description.getText().toString(), Priority.getText().toString()));
           //toast if data is added
           Toast.makeText(this, "To Do List added!", Toast.LENGTH_SHORT).show();
           //start the activity
           startActivity(new Intent(AddTodo.this, List.class));
           //closing application
           this.finish();
       }
    }
}
