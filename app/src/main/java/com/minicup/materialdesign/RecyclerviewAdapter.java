package com.minicup.materialdesign;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Austin on 2017-05-17.
 */
public class RecyclerviewAdapter extends RecyclerView.Adapter {
   public Context context;
   public ActivityInfo[] mData;
   public LayoutInflater layoutInflater;
    private OnItemClickListener onItemClickListener;

    public RecyclerviewAdapter(Context context, ActivityInfo[] mData) {
        this.context = context;
        this.mData = mData;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_list, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VH holder1 = (VH) holder;
        holder1.title.setText(mData[position].title);
        if(mData[position].clz==null){
            holder1.title.setTextColor(Color.RED);
        }else{
            holder1.title.setTextColor(context.getResources().getColor(android.R.color.primary_text_light));
        }
        holder1.desc.setText(mData[position].desc);

    }

    @Override
    public int getItemCount() {
        return mData.length;
    }

    class VH extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView title;
        public TextView desc;
        private View rootView;

        public VH(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            desc = (TextView) view.findViewById(R.id.desc);
            rootView = view;
            rootView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(onItemClickListener!=null){
                onItemClickListener.onItemClick(rootView, getAdapterPosition());
            }

            Class clz = mData[getAdapterPosition()].clz;
            if(clz!=null){
                context.startActivity(new Intent(context, clz));
            }
        }
    }


    public void setOnItemClickListener(OnItemClickListener listener){
        onItemClickListener = listener;
    }


    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

}
