package com.minicup.materialdesign.RecyclerView;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.minicup.materialdesign.OkHttp.MyOkhttp;
import com.minicup.materialdesign.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.minicup.materialdesign.R.id.recyclerView;

public class MeiziActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private int pageIndex=1;
    private MeiziResp meiziResp;
    private MeiziAdapter adapter;
    private SwipeRefreshLayout mSwip;
    private LinearLayoutManager mLayoutManager;
    private int lastVisibleItem;
    private ItemTouchHelper itemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meizi);
        mSwip = (SwipeRefreshLayout) findViewById(R.id.swip);
        mRecyclerView = (RecyclerView) findViewById(recyclerView);
        initRecyclerView();

        requestUrl(1);
        mSwip.setRefreshing(true);

        initListener();
    }

    private void initListener() {
        mSwip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestUrl(1);
                pageIndex = 1;
            }
        });

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Snackbar.make(mRecyclerView, "onItemClick:"+position, Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(int position) {
                Snackbar.make(mRecyclerView, "onItemLongClick:"+position, Snackbar.LENGTH_SHORT).show();


            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
//                0：当前屏幕停止滚动；1时：屏幕在滚动 且 用户仍在触碰或手指还在屏幕上；2时：随用户的操作，屏幕上产生的惯性滑动；
                //               滑动状态停止并且剩余两个item时自动加载
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem +2>=mLayoutManager.getItemCount()) {
                    requestUrl(++pageIndex);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //                获取加载的最后一个可见视图在适配器的位置。
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            }
        });
    }

    private void initRecyclerView() {
        itemTouchHelper =new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int dragFlags=0,swipeFlags=0;
                if(recyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager){
                    dragFlags=ItemTouchHelper.UP|ItemTouchHelper.DOWN|ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;
                }else if(recyclerView.getLayoutManager() instanceof LinearLayoutManager){
                    dragFlags=ItemTouchHelper.UP|ItemTouchHelper.DOWN;
                    //设置侧滑方向为从左到右和从右到左都可以
                    swipeFlags = ItemTouchHelper.START|ItemTouchHelper.END;
                }
                return makeMovementFlags(dragFlags,swipeFlags);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int from=viewHolder.getAdapterPosition();
                int to=target.getAdapterPosition();
                Collections.swap(adapter.getData(),from,to);
                adapter.notifyItemMoved(from,to);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                adapter.removeItem(viewHolder.getAdapterPosition());
            }

            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                super.onSelectedChanged(viewHolder, actionState);
                if(actionState==ItemTouchHelper.ACTION_STATE_DRAG) {
                    //viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
                    ((CardView) viewHolder.itemView).setCardBackgroundColor(Color.LTGRAY);
                }
            }

            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
//                viewHolder.itemView.setBackgroundColor(Color.WHITE);
                ((CardView) viewHolder.itemView).setCardBackgroundColor(Color.WHITE);
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                viewHolder.itemView.setAlpha(1- Math.abs(dX)/getScreenWidth());

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        });


        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new MarginItemDecoration((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics())));
        adapter = new MeiziAdapter();
        mRecyclerView.setAdapter(adapter);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

    }

    private float getScreenWidth() {
        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    private void requestUrl(final int pageIndex) {

        Log.e("MeiziActivity", "requestUrl--------------------");
        new Thread(new Runnable() {
            @Override
            public void run() {
                String s = MyOkhttp.get("http://gank.io/api/data/福利/10/"+pageIndex);
                Gson gson = new Gson();
                final MeiziResp meiziResp = gson.fromJson(s, MeiziResp.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mSwip.setRefreshing(false);
                        if(pageIndex==1){
                            adapter.setDate(meiziResp);
                        }else {
                            adapter.addData(meiziResp);
                        }
                    }
                });
            }
        }).start();
    }


    interface OnItemClickListener{
        void onItemClick(int position);
        void onItemLongClick(int position);
    }

    private class MeiziResp {

        /**
         * error : false
         * results : [{"_id":"5923a0c1421aa92c794632fe","createdAt":"2017-05-23T10:38:57.783Z","desc":"5-23","publishedAt":"2017-05-23T11:14:05.141Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1ffv3gxs37oj20u011i0vk.jpg","used":true,"who":"daimajia "},{"_id":"59223657421aa92c794632f4","createdAt":"2017-05-22T08:52:39.188Z","desc":"5-22","publishedAt":"2017-05-22T11:30:21.8Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fftusiwb8hj20u00zan1j.jpg","used":true,"who":"代码家"},{"_id":"591a4a02421aa92c794632c8","createdAt":"2017-05-16T08:38:26.35Z","desc":"5-16","publishedAt":"2017-05-16T12:10:38.580Z","source":"chrome","type":"福利","url":"http://ww1.sinaimg.cn/large/610dc034ly1ffmwnrkv1hj20ku0q1wfu.jpg","used":true,"who":"daimajai"},{"_id":"59187082421aa91c8da340d1","createdAt":"2017-05-14T22:58:10.836Z","desc":"5-14","publishedAt":"2017-05-15T12:03:44.165Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1ffla9ostxuj20ku0q2abt.jpg","used":true,"who":"带马甲"},{"_id":"59154ae7421aa90c7a8b2b0d","createdAt":"2017-05-12T13:40:55.505Z","desc":"5-13","publishedAt":"2017-05-12T13:44:54.673Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-05-12-18380140_455327614813449_854681840315793408_n.jpg","used":true,"who":"daimajia"},{"_id":"5913d09d421aa90c7fefdd8e","createdAt":"2017-05-11T10:46:53.608Z","desc":"5-11","publishedAt":"2017-05-11T12:03:09.581Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-05-11-18380166_305443499890139_8426655762360565760_n.jpg","used":true,"who":"代码家"},{"_id":"591264ce421aa90c7a8b2aec","createdAt":"2017-05-10T08:54:38.531Z","desc":"5-10","publishedAt":"2017-05-10T11:56:10.18Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-05-10-18382517_1955528334668679_3605707761767153664_n.jpg","used":true,"who":"带马甲"},{"_id":"59110cff421aa90c83a513ff","createdAt":"2017-05-09T08:27:43.31Z","desc":"5-9","publishedAt":"2017-05-09T12:13:25.467Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-05-09-18443931_429618670743803_5734501112254300160_n.jpg","used":true,"who":"daimajia"},{"_id":"590fe059421aa90c83a513f2","createdAt":"2017-05-08T11:04:57.969Z","desc":"5-8","publishedAt":"2017-05-08T11:22:01.540Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-05-08-18252341_289400908178710_9137908350942445568_n.jpg","used":true,"who":"daimajia"},{"_id":"590bce25421aa90c7d49ad3c","createdAt":"2017-05-05T08:58:13.502Z","desc":"5-5","publishedAt":"2017-05-05T11:56:35.629Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-05-05-18251898_1013302395468665_8734429858911748096_n.jpg","used":true,"who":"daimajia"}]
         */

        private boolean error;
        /**
         * _id : 5923a0c1421aa92c794632fe
         * createdAt : 2017-05-23T10:38:57.783Z
         * desc : 5-23
         * publishedAt : 2017-05-23T11:14:05.141Z
         * source : chrome
         * type : 福利
         * url : https://ws1.sinaimg.cn/large/610dc034ly1ffv3gxs37oj20u011i0vk.jpg
         * used : true
         * who : daimajia
         */

        private List<ResultsBean> results;

        public boolean isError() {
            return error;
        }

        public void setError(boolean error) {
            this.error = error;
        }

        public List<ResultsBean> getResults() {
            return results;
        }

        public void setResults(List<ResultsBean> results) {
            this.results = results;
        }

        public class ResultsBean {
            private String _id;
            private String createdAt;
            private String desc;
            private String publishedAt;
            private String source;
            private String type;
            private String url;
            private boolean used;
            private String who;

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getPublishedAt() {
                return publishedAt;
            }

            public void setPublishedAt(String publishedAt) {
                this.publishedAt = publishedAt;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public boolean isUsed() {
                return used;
            }

            public void setUsed(boolean used) {
                this.used = used;
            }

            public String getWho() {
                return who;
            }

            public void setWho(String who) {
                this.who = who;
            }
        }
    }

    private class MeiziAdapter extends RecyclerView.Adapter<MeiziHolder> {


        public List<MeiziResp.ResultsBean> getData(){
            return results;
        }

        private List<MeiziResp.ResultsBean> results = new ArrayList<>();

        private OnItemClickListener listener;

        public void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }

        public MeiziAdapter() {
        }

        @Override
        public MeiziHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.meizi_item, parent, false);
            MeiziHolder holder = new MeiziHolder(item);
            return holder;
        }

        @Override
        public void onBindViewHolder(MeiziHolder holder, final int position) {
            MeiziResp.ResultsBean resultsBean = results.get(position);
            Picasso.with(MeiziActivity.this).load(resultsBean.getUrl()).into(holder.imageView);
            holder.textView.setText(resultsBean.getCreatedAt());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        listener.onItemClick(mRecyclerView.getChildAdapterPosition(v));
                    }
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (listener != null) {
                        listener.onItemLongClick(mRecyclerView.getChildAdapterPosition(v));
                        return true;
                    }
                    return false;
                }
            });
        }

        @Override
        public int getItemCount() {
            return results.size();
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }

        public void addData(MeiziResp meiziResp) {
            int itemCount = adapter.getItemCount();
            results.addAll(meiziResp.getResults());
            adapter.notifyItemRangeInserted(itemCount, meiziResp.getResults().size());
        }

        public void setDate(MeiziResp meiziResp) {
            results.clear();
            results.addAll(meiziResp.getResults());
            adapter.notifyDataSetChanged();

        }

        public void removeItem(int position) {
            results.remove(position);
            adapter.notifyItemRemoved(position);
        }
    }

    private class MeiziHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView textView;
        public MeiziHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}
