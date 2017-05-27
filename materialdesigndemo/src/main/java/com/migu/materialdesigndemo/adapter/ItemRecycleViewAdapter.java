package com.migu.materialdesigndemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.migu.materialdesigndemo.R;

import java.util.List;

/**
 * Created by DY on 2017/5/24.
 */

public class ItemRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> datas;
    private Context context;
    private static final int TYPE_NORMAL=1;
    private static final int TYPE_FOOTER=2;
    public OnItemClickListener onItemClickListener;
    private boolean isHintFooter=true;

    public boolean isHintFooter() {
        return isHintFooter;
    }

    public void setHintFooter(boolean hintFooter) {
        isHintFooter = hintFooter;
    }

    public ItemRecycleViewAdapter(List<String> datas, Context context) {
        this.context=context;
        this.datas=datas;
    }
    public interface OnItemClickListener{
        public void OnItemClick(View view,int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }



    @Override
    public int getItemViewType(int position) {
        if ((position+1)==getItemCount()&&!isHintFooter){
            return TYPE_FOOTER;
        }else{
            return TYPE_NORMAL;
        }
//        return (position+1)==getItemCount()?TYPE_FOOTER:TYPE_NORMAL;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view=View.inflate(context, R.layout.recycleview_item,null);
        Log.e("123","getItemCount===>"+getItemCount());
        Log.e("123","viewType===>"+viewType);
        if (viewType==TYPE_NORMAL) {
            View view = LayoutInflater.from(context).inflate(R.layout.recycleview_item, parent, false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }else if (viewType==TYPE_FOOTER){
            View view = LayoutInflater.from(context).inflate(R.layout.recycleview_item_footer, parent, false);
            FootViewHolder footViewHolder = new FootViewHolder(view);
            return footViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyViewHolder){
            MyViewHolder itemHolder= (MyViewHolder) holder;
            itemHolder.tv.setText(datas.get(position));
            itemHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.OnItemClick(holder.itemView,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (isHintFooter){
            return datas.size();
        }else{
            return datas.size()==0?0:datas.size()+1;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv= (TextView) itemView.findViewById(R.id.tv_content);
        }

    }
    class FootViewHolder extends RecyclerView.ViewHolder{

        public FootViewHolder(View itemView) {
            super(itemView);
        }
    }

}
