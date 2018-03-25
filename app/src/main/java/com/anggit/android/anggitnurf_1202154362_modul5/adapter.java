package com.anggit.android.anggitnurf_1202154362_modul5;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Anggit Nur on 3/25/2018.
 */

public class adapter extends RecyclerView.Adapter<adapter.holder> {
    //declaration variable used in this activity
    private Context cntx;
    private java.util.List<AddData> list;
    int color;

    //constructor
    public adapter(Context cntx, java.util.List<AddData> list, int color){
        this.cntx=cntx;
        this.list=list;
        this.color=color;
    }

    //choosing viewholder to recyclerview
    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //making new view
        View view = LayoutInflater.from(cntx).inflate(R.layout.cardview, parent, false);
        holder hldr = new holder(view);
        return hldr;
    }

    //value setting from viewholder
    @Override
    public void onBindViewHolder(holder holder, int position) {
        AddData data = list.get(position);
        holder.ToDo.setText(data.getTodo());
        holder.Description.setText(data.getDesc());
        holder.Priority.setText(data.getPrior());
        holder.cardv.setCardBackgroundColor(cntx.getResources().getColor(this.color));
    }

    //get list
    @Override
    public int getItemCount() {
        return list.size();
    }

    //get list from adapter
    public AddData getData(int position){
        return list.get(position);
    }

    //delete list from to do list
    public void deleteData(int i){
        //remove selected item
        list.remove(i);
        //deleted item notification
        notifyItemRemoved(i);
        notifyItemRangeChanged(i, list.size());
    }

    class holder extends RecyclerView.ViewHolder{
        //variable declration
        public TextView ToDo, Description, Priority;
        public CardView cardv;
        public holder(View itemView){
            super(itemView);

            //access id textview from layout and cardview
            ToDo = itemView.findViewById(R.id.headline);
            Description = itemView.findViewById(R.id.explanation);
            Priority = itemView.findViewById(R.id.number);
            cardv = itemView.findViewById(R.id.cardlist);
        }
    }
}
