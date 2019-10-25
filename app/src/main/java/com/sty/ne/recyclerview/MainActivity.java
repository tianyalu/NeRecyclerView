package com.sty.ne.recyclerview;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private FeedAdapter mFeedAdapter;
    private RelativeLayout mSuspensionBar;
    private TextView mSuspensionTv;
    private ImageView mSuspensionIv;
    private FloatingActionButton fbBackTop;


    private int mSuspensionHeight;
    private int mCurrentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSuspensionBar = findViewById(R.id.suspension_bar);
        mSuspensionTv = findViewById(R.id.tv_nickname);
        mSuspensionIv = findViewById(R.id.iv_avatar);
        fbBackTop = findViewById(R.id.fb_back_top);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = findViewById(R.id.recycler_view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mFeedAdapter = new FeedAdapter();
        mRecyclerView.setAdapter(mFeedAdapter);
        //确定Item的改变不会影响RecyclerView的宽高的时候可以设置setHasFixedSize(true)，
        //并通过Adapter的增删改插方法去刷新RecyclerView，而不是通过notifyDataSetChanged()。
        //（其实可以直接设置为true，当需要改变宽高的时候就用notifyDataSetChanged()去整体刷新一下
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //获取悬浮条高度
                mSuspensionHeight = mSuspensionBar.getHeight();
                Log.i("sty", "onScrollStateChanged: mSuspensionHeight ->" + mSuspensionHeight );
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //对悬浮条的位置进行调整
                //找到下一个itemView
                View view = layoutManager.findViewByPosition(mCurrentPosition + 1);
                if(view != null) {
                    Log.i("sty", "onScrollStateChanged: view.getTop() ->" + view.getTop() + " (currentPosition: " + mCurrentPosition + ")");
                    if(view.getTop() <= mSuspensionHeight) {
                        //需要对悬浮条进行移动
                        mSuspensionBar.setY(-(mSuspensionHeight - view.getTop()));
                    }else {
                        //保持在原来位置
                        mSuspensionBar.setY(0);
                    }
                }

                if(mCurrentPosition != layoutManager.findFirstVisibleItemPosition()) {
                    mCurrentPosition = layoutManager.findFirstVisibleItemPosition();
                    updateSuspensionBar();
                }
            }
        });

        updateSuspensionBar();

        fbBackTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mRecyclerView != null) {
//                    mRecyclerView.scrollToPosition(0);
                    mRecyclerView.smoothScrollToPosition(0);
                }
            }
        });
    }

    private void updateSuspensionBar() {
        Picasso.with(this)
                .load(getAvatarResId(mCurrentPosition))
                .centerInside()
                .fit()
                .into(mSuspensionIv);
        mSuspensionTv.setText("Float Title " + mCurrentPosition);
    }

    private int getAvatarResId(int position) {
        switch (position % 4) {
            case 0:
                return R.drawable.avatar1;
            case 1:
                return R.drawable.avatar2;
            case 2:
                return R.drawable.avatar3;
            case 3:
                return R.drawable.avatar4;

        }
        return 0;
    }
}
