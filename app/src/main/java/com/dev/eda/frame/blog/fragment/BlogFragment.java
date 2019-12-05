package com.dev.eda.frame.blog.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.eda.R;
import com.dev.eda.app.base.BaseFragment;
import com.dev.eda.app.utils.Logger;
import com.dev.eda.frame.blog.adapter.BlogAdapter;
import com.dev.eda.frame.blog.model.Blog;
import com.dev.eda.frame.blog.model.BlogContent;
import com.dev.eda.frame.blog.model.BlogImage;
import com.dev.eda.frame.home.listener.AppBarStateChangeListener;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class BlogFragment extends BaseFragment {

    private static BlogFragment mInstance;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.back_view)
    ImageView backView;
    @BindView(R.id.blog_xiangji)
    ImageView blogXiangji;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.blog_toolbar_layout)
    CollapsingToolbarLayout blogToolbarLayout;
    @BindView(R.id.blog_app_bar)
    AppBarLayout blogAppBar;

    private List<Blog> blogList;

    private BlogFragment() {
    }

    public static BlogFragment getInstance() {
        if (null == mInstance) {
            mInstance = new BlogFragment();
        }
        return mInstance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ImmersionBar.with(this).init();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_blog;
    }

    @Override
    protected void initData() {
        super.initData();
        blogList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Blog blog = new Blog();
            blog.setTitleImageResource(R.drawable.shanghai);
            blog.setName("昵称"+i);

            List<BlogContent> blogContents = new ArrayList<>();

            BlogContent blogContent = new BlogContent(BlogContent.itemType_message);
            blogContent.setContent("我是一段很长很长很长很长很长很长很长很长很长很长很长很长的内容");
            blogContents.add(blogContent);

            BlogContent blogContent1 = new BlogContent(BlogContent.itemType_images);
            List<BlogImage> blogImages = new ArrayList<>();
            for (int j = 0; j < 7; j++) {
                BlogImage image = new BlogImage();
                image.setImageResource(R.drawable.shanghai);
                blogImages.add(image);
            }
            blogContent1.setImages(blogImages);
            blogContents.add(blogContent1);

            BlogContent blogContent2 = new BlogContent(BlogContent.itemType_more);
            blogContent2.setTimeAndAddress(i+"分钟前");
            blogContents.add(blogContent2);

            BlogContent blogContent3 = new BlogContent(BlogContent.itemType_good);
            blogContent3.setGoodText("❤雷佳音,hurricane");
            blogContents.add(blogContent3);

            blog.setBlogContents(blogContents);
            blogList.add(blog);
        }
    }

    @Override
    protected void initView() {
        super.initView();
        blogAppBar.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state == State.EXPANDED) {
                    //展开状态
                    blogToolbarLayout.setContentScrimColor(Color.parseColor("#00000000"));
                } else if (state == State.COLLAPSED) {
                    //折叠状态
                    blogToolbarLayout.setContentScrimColor(Color.parseColor("#99CCFF"));
                } else {
                    //中间状态
                    blogToolbarLayout.setContentScrimColor(Color.parseColor("#00000000"));
                }
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
//        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new BlogAdapter(R.layout.item_card_bolg,getContext(), blogList));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mInstance = null;
    }
}