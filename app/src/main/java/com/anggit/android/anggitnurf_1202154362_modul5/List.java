package com.anggit.android.anggitnurf_1202154362_modul5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class List extends AppCompatActivity {
    //declare variable
    database db;
    RecyclerView rv;
    adapter adapter;
    ArrayList<AddData> datalist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        //set title into To Do List
        setTitle("To Do List");

        //access recyclerview from layout
        rv = findViewById(R.id.recview);
        //make new arraylist
        datalist = new ArrayList<>();
        //make new database
        db = new database(this);
        //mcall readdata method
        db.readdata(datalist);

        //initiate shared preference
        SharedPreferences sharedP = this.getApplicationContext().getSharedPreferences("Preferences", 0);
        int color = sharedP.getInt("Colourground", R.color.white);

        //make new adapter
        adapter = new adapter(this,datalist, color);
        //avoid unused size
        rv.setHasFixedSize(true);
        //show layout linear
        rv.setLayoutManager(new LinearLayoutManager(this));
        //iinitiate adapter for recycler view
        rv.setAdapter(adapter);

        //start delete method
        swipeToDelete();
    }

    //making method to delete item
    public void swipeToDelete(){
        //make new touch helper for recycler view
        ItemTouchHelper.SimpleCallback touchcall = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                AddData current = adapter.getData(position);
                //if swipe to left
                if(direction==ItemTouchHelper.LEFT){
                    //remove item choosed
                    if(db.removedata(current.getTodo())){
                        //menghapus data
                        adapter.deleteData(position);
                        //making snackbar for 1 second to notify data has been deleted
                        Snackbar.make(findViewById(R.id.coor), "Data Deleted", 1000).show();
                    }
                }
            }
        };
        //choose itemtouchhelper for recycler view
        ItemTouchHelper touchhelp = new ItemTouchHelper(touchcall);
        touchhelp.attachToRecyclerView(rv);
    }
    //menu from activity made :
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    //method for item selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //get id from item
        int id = item.getItemId();
        //aif item is setting
        if (id==R.id.action_settings){
            //intent from to do list to setting
            Intent intent = new Intent(List.this, Settings.class);
            //start intent
            startActivity(intent);
            //close intent
            finish();
        }
        return true;
    }

    //method start when add button clicked
    public void add(View view) {
        //intent from list to do To add to do
        Intent intent = new Intent(List.this, AddTodo.class);
        //start intent
        startActivity(intent);
    }
}