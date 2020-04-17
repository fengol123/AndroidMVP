package com.fdb.mvpdemo.widget.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;


import com.fdb.baselibrary.base.BaseApplication;

import java.util.ArrayList;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static com.fdb.mvpdemo.widget.base.MoreHolder.HAS_LOADALL_STATE;
import static com.fdb.mvpdemo.widget.base.MoreHolder.NETWORK_ERROR_STATE;
import static com.fdb.mvpdemo.widget.base.MoreHolder.NORMAL_STATE;


/**
 * Created by xieguofeng on 2018/7/16
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter {
    protected List<T> mDatas = new ArrayList<>();
    protected LinearLayout mHeaderLayout; //头部容器
    protected LinearLayout mFooterLayout; //尾部容器

    public int mPageSize = 10; //每页的条数

    public static final int TYPE_HEADER = 100; //头部
    public static final int TYPE_FOOTER = 101; //尾部
    public static final int TYPE_NORMAL = 102; //单类型时使用的类型
    public static final int TYPE_LOAD_MORE = 103; //加载更多
    public static final int TYPE_EMPTY = 104; //空页面(正在加载, 加载失败, 没有数据)

    private MoreHolder mMoreHolder;     //加载更多的holder
    private boolean mEnableEmpty = false; //空页面是否显示的总开关
    private boolean mIsShowEmpty = true; //是否显示空页面
    private int mEmptyHolderState = EmptyHolder.LOADING_STATE; //空页面的状态
    private int mMoreHolderState = NORMAL_STATE;
    private EmptyHolder mEmptyHolder;   //空页面的holder

    private BaseHolder.OnItemClickListener<T> mOnItemClickListener;
    private MoreHolder.OnLoadMoreListener mOnLoadMoreListener;

    /**
     * 首次/重新设置数据(重要)
     *
     * @param data
     */
    public void setData(List<T> data) {
        mDatas = data == null ? new ArrayList<T>() : data;
        if (mDatas.size() == 0) {
            //显示空页面(暂无数据)
            mIsShowEmpty = true;
            mEmptyHolderState = EmptyHolder.NO_DATA_STATE;
        } else {
            mIsShowEmpty = false;
        }
        setMoreHolderState(NORMAL_STATE);
        notifyDataSetChanged();
    }

    /**
     * 加载更多数据后,添加数据(重要)
     *
     * @param listBeen
     */
    public void addData(List<T> listBeen) {
        if (listBeen == null) {
            //如果为空,则显示加载失败
            setMoreHolderState(NETWORK_ERROR_STATE);
            return;
        }

        if (listBeen.size() < mPageSize) {
            //如果数据条数少于一页,显示已加载全部
            setMoreHolderState(HAS_LOADALL_STATE);
        } else {
            //设置为等待状态
            setMoreHolderState(NORMAL_STATE);
        }

        //把数据添加到集合中
        mDatas.addAll(listBeen);
        notifyDataSetChanged();
    }

    /**
     * 添加头部布局(重要)
     *
     * @param header 头部的布局
     */
    public void addHeaderView(View header) {
        addHeaderView(header, -1);
    }

    /**
     * 添加头部布局(重要)
     *
     * @param header 头部的布局
     * @param index  添加到第几个头部的位置
     */
    public void addHeaderView(View header, int index) {
        if (mHeaderLayout == null) {
            mHeaderLayout = new LinearLayout(header.getContext());
            mHeaderLayout.setOrientation(LinearLayout.VERTICAL);
            mHeaderLayout.setLayoutParams(new RecyclerView.LayoutParams(MATCH_PARENT, WRAP_CONTENT));
        }

        final int childCount = mHeaderLayout.getChildCount();
        if (index < 0 || index > childCount) {
            index = childCount;
        }
        mHeaderLayout.addView(header, index);
        notifyDataSetChanged();
    }

    /**
     * 去除头部的布局(重要)
     *
     * @param header
     */
    public void removeHeadView(View header) {
        if (mHeaderLayout == null) {
            return;
        }

        mHeaderLayout.removeView(header);
    }

    /**
     * 添加尾部布局(重要)
     *
     * @param footer 尾部的布局
     */
    public void addFootererView(View footer) {
        addFootererView(footer, -1);
    }

    /**
     * 添加尾部布局(重要)
     *
     * @param footer 尾部的布局
     * @param index  添加到第几个尾部的位置
     */
    public void addFootererView(View footer, int index) {
        if (mFooterLayout == null) {
            mFooterLayout = new LinearLayout(footer.getContext());
            mFooterLayout.setOrientation(LinearLayout.VERTICAL);
            mFooterLayout.setLayoutParams(new RecyclerView.LayoutParams(MATCH_PARENT, WRAP_CONTENT));
        }

        final int childCount = mFooterLayout.getChildCount();
        if (index < 0 || index > childCount) {
            index = childCount;
        }
        mFooterLayout.addView(footer, index);
        notifyDataSetChanged();
    }

    /**
     * 去除尾部的布局(重要)
     *
     * @param footer
     */
    public void removeFootView(View footer) {
        if (mFooterLayout == null) {
            return;
        }

        mFooterLayout.removeView(footer);
    }

    /**
     * 根绝viewType,获取holder(重要)
     *
     * @param frameLayout
     * @param viewType
     * @return
     */
    public abstract BaseHolder<T> getHolder(FrameLayout frameLayout, int viewType);

    /**
     * 获取多类型的holder, 如果使用多条目类型, 必须重写该方法(重要)
     *
     * @param position
     * @return
     */
    protected int getExtItemType(int position) {
        return TYPE_NORMAL;
    }

    /**
     * 让加载更多条目显示"加载失败"(重要)
     */
    public void loadMoreError() {
        setMoreHolderState(NETWORK_ERROR_STATE);
    }

    /**
     * 空页面显示"加载失败"(重要)
     */
    public void loadDataError() {
        mIsShowEmpty = true;
        mEmptyHolderState = EmptyHolder.LOAD_ERROR_STATE;
        notifyDataSetChanged();
    }

    /**
     * 空页面显示"正在加载"(重要)
     */
    public void loading() {
        mIsShowEmpty = true;
        mEmptyHolderState = EmptyHolder.LOADING_STATE;
        notifyDataSetChanged();
    }

    /**
     * 控制空页面是否显示(重要)
     *
     * @param enableEmpty false:该adapter不会显示空页面;true:显示
     */
    public void setEnableEmpty(boolean enableEmpty) {
        mEnableEmpty = enableEmpty;
    }

    /**
     * 设置条目点击监听(重要)
     *
     * @param listener
     */
    public void setOnItemClickListener(BaseHolder.OnItemClickListener<T> listener) {
        mOnItemClickListener = listener;
    }

    /**
     * 设置加载更多监听(重要)
     *
     * @param listener
     */
    public void setOnLoadMoreListener(MoreHolder.OnLoadMoreListener listener) {
        mOnLoadMoreListener = listener;
    }

    /**
     * 判断是否显示空页面
     */
    private boolean getIsShowEmpty() {
        if (mIsShowEmpty && mEnableEmpty) {
            return true;
        }
        return false;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) { //头部
            return new ViewHolder(mHeaderLayout) {
            };
        } else if (viewType == TYPE_FOOTER) { //尾部
            return new ViewHolder(mFooterLayout) {
            };
        }

        if (viewType == TYPE_EMPTY) { //空页面
            FrameLayout frameLayout = newFrameLayout(ViewGroup.LayoutParams.MATCH_PARENT);
            mEmptyHolder = new EmptyHolder(frameLayout);
            return mEmptyHolder;
        }

        FrameLayout frameLayout = newFrameLayout(ViewGroup.LayoutParams.WRAP_CONTENT);

        if (viewType == TYPE_LOAD_MORE) { //加载更多
            mMoreHolder = new MoreHolder(frameLayout, mMoreHolderState, mOnLoadMoreListener);
            return mMoreHolder;
        }

        //回调获取条目的holder
        return getHolder(frameLayout, viewType);
    }

    /**
     * 实例化一个FrameLayout布局
     *
     * @param wrapContent 包裹内容/匹配父控件
     * @return
     */
    @NonNull
    private FrameLayout newFrameLayout(int wrapContent) {
        FrameLayout frameLayout = new FrameLayout(BaseApplication.getApp());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                wrapContent);
        frameLayout.setLayoutParams(layoutParams);
        return frameLayout;
    }

    /**
     * 更新holder的数据
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        int dataPosition = position;
        if (getIsShowHeader()) {
            //防止数据越界
            dataPosition--;
        }

        if (getItemViewType(position) == TYPE_HEADER) {

        } else if (getItemViewType(position) == TYPE_FOOTER) {

        } else if (getItemViewType(position) == TYPE_LOAD_MORE) {
            MoreHolder moreHolder = (MoreHolder) holder;
            moreHolder.loadMore();
        } else if (getItemViewType(position) == TYPE_EMPTY) {
            EmptyHolder emptyHolder = (EmptyHolder) holder;
            emptyHolder.setState(mEmptyHolderState);
        } else {
            BaseHolder<T> baseHolder = (BaseHolder<T>) holder;
            baseHolder.setData(mDatas.get(dataPosition), dataPosition);
            baseHolder.setOnItemClickListener(mOnItemClickListener);
        }
    }

    /**
     * 获取条目总数
     *
     * @return
     */
    @Override
    public int getItemCount() {
        int count = mDatas.size();

        if (getIsShowEmpty()) {
            count = 1;

            if (getIsShowHeader()) {
                count++;
            }

            if (getIsShowFooter()) {
                count++;
            }

            return count;
        }

        if (getIsShowHeader()) {
            count++;
        }

        if (getIsShowFooter()) {
            count++;
        }

        if (getIsLoadMore()) {
            count++;
        }

        return count;
    }

    /**
     * 设置加载更多的holder的状态
     *
     * @param state
     */
    private void setMoreHolderState(int state) {
        mMoreHolderState = state;
        if (mMoreHolder == null) {
            return;
        }
        mMoreHolder.setState(state);
    }

    /**
     * 判断是否显示头部
     *
     * @return
     */
    private boolean getIsShowHeader() {
        if (mHeaderLayout != null && mHeaderLayout.getChildCount() != 0) {
            return true;
        }

        return false;
    }

    /**
     * 判断是否显示加载更多
     *
     * @return
     */
    private boolean getIsLoadMore() {
        if (mOnLoadMoreListener == null) {
            return false;
        }

        if (mDatas.size() < mPageSize) {
            return false;
        }

        return true;
    }

    /**
     * 判断是否显示尾部
     *
     * @return
     */
    private boolean getIsShowFooter() {
        if (mFooterLayout != null && mFooterLayout.getChildCount() != 0) {
            return true;
        }

        return false;
    }

    /**
     * 设置每页的数量
     *
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        mPageSize = pageSize;
    }

    private int getHeaderCount() {
        if (mHeaderLayout == null) {
            return 0;
        }

        return mHeaderLayout.getChildCount();
    }

    /**
     * 获取条目的类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            if (getIsShowHeader()) {
                return TYPE_HEADER;
            } else if (getIsShowEmpty()) {
                return TYPE_EMPTY;
            }
        }

        if (position == 1) {
            if (getIsShowHeader() && getIsShowEmpty()) {
                return TYPE_EMPTY;
            }
        }

        if (position == getItemCount() - 1) {
            if (getIsShowFooter()) {
                return TYPE_FOOTER;
            } else if (getIsLoadMore()) {
                return TYPE_LOAD_MORE;
            }
        }

        if (position == getItemCount() - 2) {
            if (getIsShowFooter() && getIsLoadMore()) {
                return TYPE_LOAD_MORE;
            }
        }

        int dataIndex = position;
        if (getIsShowHeader()) { //防止数组越界
            dataIndex--;
        }

        return getExtItemType(dataIndex);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    if (mSpanSizeLookup == null)
                        return (type == TYPE_EMPTY || type == TYPE_HEADER || type == TYPE_FOOTER || type ==
                                TYPE_LOAD_MORE) ? gridManager.getSpanCount() : 1;
                    else
                        return (type == TYPE_EMPTY || type == TYPE_HEADER || type == TYPE_FOOTER || type ==
                                TYPE_LOAD_MORE) ? gridManager.getSpanCount() : mSpanSizeLookup.getSpanSize(gridManager,
                                position - getHeaderCount());
                }
            });
        }
    }

    private SpanSizeLookup mSpanSizeLookup;

    public interface SpanSizeLookup {
        int getSpanSize(GridLayoutManager gridLayoutManager, int position);
    }

    /**
     * @param spanSizeLookup instance to be used to query number of spans occupied by each item
     */
    public void setSpanSizeLookup(SpanSizeLookup spanSizeLookup) {
        this.mSpanSizeLookup = spanSizeLookup;
    }

    /**
     * 加了头部以后,原来的notifyItemChanged方法的position计算需要加上头部数量,所以更新单个条目调用此方法
     *
     * @param position
     */
    public void notifyItemDataChanged(int position) {
        position = position + getHeaderCount();
        notifyItemChanged(position);
    }

    public List<T> getDatas() {
        return mDatas;
    }

}














