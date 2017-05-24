package com.migu.materialdesigndemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.migu.materialdesigndemo.R;

import java.util.List;

/**
 * Created by DY on 2017/5/24.
 */

public class ItemRecycleViewAdapter extends RecyclerView.Adapter<ItemRecycleViewAdapter.MyViewHolder> {
    private List<String> datas;
    private Context context;
    public ItemRecycleViewAdapter(List<String> datas,Context context) {
        this.context=context;
        this.datas=datas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view=View.inflate(context, R.layout.recycleview_item,null);
        View view= LayoutInflater.from(context).inflate(R.layout.recycleview_item,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv= (TextView) itemView.findViewById(R.id.tv_content);
        }

    }
}
