package com.netsize.recyclerviewtest;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by loxu on 13/06/2017.
 */

//    case 1  :  only textview on RecyclerView
//    case 2 :   2 textviews on RecyclerView
//    case 3 :   cardview

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.DataObjectHolder> {

    private ArrayList<DataObject> mDataset;
    private static MyClickListener myClickListener;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case


        // case 1
        public TextView mTextView;

        public ViewHolder(View v) {
            super(v);

            //case 1
            //mTextView = v;
        }
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        // case 1
        //TextView  mTextView;

        // case 2 3
        TextView label;
        TextView dateTime;

        public DataObjectHolder(View itemView) {
            super(itemView);

            // case 1
            //mTextView = (TextView)itemView;

            // case 2 3
            label = (TextView) itemView.findViewById(R.id.textView);
            dateTime = (TextView) itemView.findViewById(R.id.textView2);
            //Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<DataObject> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        // case  1 2
       View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);
        // set the view's size, margins, paddings and layout parameters

        // card view case 3
       /* View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_row, parent, false);*/

        DataObjectHolder vh = new DataObjectHolder(view);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        // case 1
        //holder.mTextView.setText(mDataset.get(position).getmText1());

        // case 2 3
        holder.label.setText(mDataset.get(position).getmText1());
        holder.dateTime.setText(mDataset.get(position).getmText2());

    }

    public void addItem(DataObject dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}
