package com.minicup.materialdesign.BottomSheet;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.minicup.materialdesign.R;
import com.minicup.materialdesign.RecyclerView.MarginItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class BottomSheetDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_dialog);
    }

    public void showBottomSheetDialog(View view) {
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        View sheet = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_dialog, null);
        setSheetListener(sheet);
        dialog.setContentView(sheet);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                Toast.makeText(BottomSheetDialogActivity.this, "onDismiss", Toast.LENGTH_SHORT).show();
                //手动滑动到底部时
            }
        });

        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Toast.makeText(BottomSheetDialogActivity.this, "onCancel", Toast.LENGTH_SHORT).show();
                //setCanceledOnTouchOutside(true)时，touch Dialog以外。
            }
        });

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Toast.makeText(BottomSheetDialogActivity.this, "onShow", Toast.LENGTH_SHORT).show();
                //不会被调用
            }
        });
    }

    private void setSheetListener(View sheet) {
        LinearLayout linearLayout = (LinearLayout) sheet;
        int childCount = linearLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final int finalI = i;
            linearLayout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(BottomSheetDialogActivity.this, "点击位置:" + finalI, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void showRecyclerBottomSheetDialog(View view) {
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        RecyclerView recyclerView = (RecyclerView) LayoutInflater.from(this).inflate(R.layout.recyclerview, null);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new MarginItemDecoration(1));
        LocalAdapter adapter = new LocalAdapter(50);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(BottomSheetDialogActivity.this, "position:"+position, Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setContentView(recyclerView);
        dialog.setCanceledOnTouchOutside(true);
        //设置Dialog沉浸状态栏
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        dialog.show();

    }

    public void showCustomDialog(View view) {
        BottomSheetDialog dialog = new BottomSheetDialog(this){
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, 1000);
            }
        };
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        RecyclerView recyclerView = (RecyclerView) LayoutInflater.from(this).inflate(R.layout.recyclerview, null);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new MarginItemDecoration(1));
        LocalAdapter adapter = new LocalAdapter(50);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(BottomSheetDialogActivity.this, "position:"+position, Toast.LENGTH_SHORT).show();
            }
        });

        dialog.setContentView(recyclerView);
        View behaviorView = dialog.getWindow().findViewById(android.support.design.R.id.design_bottom_sheet);
        BottomSheetBehavior.from(behaviorView).setPeekHeight(600);

        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    private class LocalAdapter extends RecyclerView.Adapter {
        private int amount;

        private List<String> mData = new ArrayList<>();
        private Context context = BottomSheetDialogActivity.this;
        private OnItemClickListener onItemClickListener;

        public LocalAdapter(int amount) {
            this.amount = amount;
            for (int i = 0; i < amount; i++) {
                mData.add("index" + i);
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView textView = new TextView(context);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.height = 150;
            textView.setGravity(Gravity.CENTER);
            textView.setBackgroundColor(Color.WHITE);
            textView.setLayoutParams(params);
            return new LocalHolder(textView);
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
            LocalHolder holder1 = (LocalHolder) holder;
            holder1.textView.setText(mData.get(position));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(v, holder.getAdapterPosition());
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }



        private class LocalHolder extends RecyclerView.ViewHolder {
            private TextView textView;

            public LocalHolder(TextView textView) {
                super(textView);
                this.textView = textView;
            }
        }
    }

    interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
}
