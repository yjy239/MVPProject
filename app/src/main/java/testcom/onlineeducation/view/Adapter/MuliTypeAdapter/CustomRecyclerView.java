package testcom.onlineeducation.view.Adapter.MuliTypeAdapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import testcom.onlineeducation.R;
import testcom.onlineeducation.i.IBottom;
import testcom.onlineeducation.utils.ToastUtil;

/**
 * Created by Administrator on 2017/6/16 0016.
 */
public class CustomRecyclerView extends FrameLayout implements IBottom {
    private static final String TAG = "TRecyclerView";
    private SwipeRefreshLayout swipeRefresh;
    private RecyclerView rv;
    private LinearLayoutManager mLinearLayoutManager;
    private GridLayoutManager mGridLayoutManager;
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    private int layoutMangerType;
    private List<Visitable> list = new ArrayList<>();
    private int currentPage = 1;
    private int totalPage = 1;
    private boolean isEmpty = false;
    private MultiTypeAdapter adapter;
    private boolean isShowHasNotMoreData = true;
    private RelativeLayout view;
    private Context context;
    private boolean isBottom;
    private boolean isWarpContent;

    public CustomRecyclerView(Context context) {
        super(context);
        this.context = context;
        init(context, null);
    }

    public CustomRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(context, attrs);
    }

    public CustomRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(context, attrs);
    }


    public void setShowHasNotMoreData(boolean showHasNotMoreData) {
        isShowHasNotMoreData = showHasNotMoreData;
    }

    public SwipeRefreshLayout getSwipeRefresh() {
        return swipeRefresh;
    }

    public Boolean isRefreshing() {
        if (swipeRefresh == null){
            return false;
        }else {
            return swipeRefresh.isRefreshing();
        }
    }


    public MultiTypeAdapter getAdapter() {
        return adapter;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void init(Context context, AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.TRecyclerView);
        int itemType = ta.getResourceId(R.styleable.TRecyclerView_itemType, 0);
        boolean isRefreshable = ta.getBoolean(R.styleable.TRecyclerView_isRefreshable, true);
        layoutMangerType = ta.getInteger(R.styleable.TRecyclerView_layoutMangerType, 0);
        int layoutMangerCount = ta.getInteger(R.styleable.TRecyclerView_layoutMangerCount, 1);
        int layoutMangerOrientation = ta.getInteger(R.styleable.TRecyclerView_layoutMangerOrientation, 0);
        boolean hasContext = ta.getBoolean(R.styleable.TRecyclerView_hasContext, false);
        isWarpContent = ta.getBoolean(R.styleable.TRecyclerView_isWrapContent, false);
        ta.recycle();

        if (isWarpContent){
            View layout = inflate(context, R.layout.layout_custom_recycle_view_wrap, this);
            rv = (RecyclerView) layout.findViewById(R.id.recycler_view);

            rv.setHasFixedSize(true);
            switch (layoutMangerType) {
                case 0:
                    mLinearLayoutManager = new LinearLayoutManager(context);
                    mLinearLayoutManager.setOrientation(layoutMangerOrientation == 1 ? OrientationHelper.HORIZONTAL : OrientationHelper.VERTICAL);
                    rv.setLayoutManager(mLinearLayoutManager);
                    rv.setItemAnimator(new DefaultItemAnimator());
                    break;
                case 1:
                    mGridLayoutManager = new GridLayoutManager(context, layoutMangerCount);
                    mGridLayoutManager.setOrientation(layoutMangerOrientation == 1 ? OrientationHelper.HORIZONTAL : OrientationHelper.VERTICAL);
                    rv.setLayoutManager(mGridLayoutManager);
                    rv.setItemAnimator(new DefaultItemAnimator());
                    break;
                case 2:
                    mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(layoutMangerCount, layoutMangerOrientation == 1 ? OrientationHelper.HORIZONTAL : OrientationHelper.VERTICAL);
                    rv.setLayoutManager(mStaggeredGridLayoutManager);
                    rv.setItemAnimator(new DefaultItemAnimator());
                    break;
                default:
                    mLinearLayoutManager = new LinearLayoutManager(context);
                    rv.setLayoutManager(mLinearLayoutManager);
                    rv.setItemAnimator(new DefaultItemAnimator());
                    break;
            }

            if (hasContext) {
                adapter = new MultiTypeAdapter(list, context);
            } else {
                adapter = new MultiTypeAdapter(list);
            }
            rv.setAdapter(adapter);

        }else {
            View layout = inflate(context, R.layout.layout_custom_recycle_view, this);
            swipeRefresh = (SwipeRefreshLayout) layout.findViewById(R.id.swipe_refresh);
            rv = (RecyclerView) layout.findViewById(R.id.recycler_view);

            rv.addOnScrollListener(new RecyclerView.OnScrollListener() {

                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if (rv.getAdapter() != null
                            && newState == RecyclerView.SCROLL_STATE_IDLE
                            && isBottom) {
                        if (currentPage < totalPage) {
                            currentPage++;
                            actionLoadData();
                        } else {
                            if (isShowHasNotMoreData)
                                ToastUtil.showToast(CustomRecyclerView.this.context, "暂无更多数据");
                        }
                    }
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int arg0, int arg1) {
                    super.onScrolled(recyclerView, arg0, arg1);
                }
            });

            swipeRefresh.setColorSchemeResources(android.R.color.holo_blue_bright);
            swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    currentPage = 1;
                    swipeRefresh.setRefreshing(true);
                    actionLoadData();
                }
            });
            rv.setHasFixedSize(true);
            switch (layoutMangerType) {
                case 0:
                    mLinearLayoutManager = new LinearLayoutManager(context);
                    mLinearLayoutManager.setOrientation(layoutMangerOrientation == 1 ? OrientationHelper.HORIZONTAL : OrientationHelper.VERTICAL);
                    rv.setLayoutManager(mLinearLayoutManager);
                    rv.setItemAnimator(new DefaultItemAnimator());
                    break;
                case 1:
                    mGridLayoutManager = new GridLayoutManager(context, layoutMangerCount);
                    mGridLayoutManager.setOrientation(layoutMangerOrientation == 1 ? OrientationHelper.HORIZONTAL : OrientationHelper.VERTICAL);
                    rv.setLayoutManager(mGridLayoutManager);
                    rv.setItemAnimator(new DefaultItemAnimator());
                    break;
                case 2:
                    mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(layoutMangerCount, layoutMangerOrientation == 1 ? OrientationHelper.HORIZONTAL : OrientationHelper.VERTICAL);
                    rv.setLayoutManager(mStaggeredGridLayoutManager);
                    rv.setItemAnimator(new DefaultItemAnimator());
                    break;
                default:
                    mLinearLayoutManager = new LinearLayoutManager(context);
                    rv.setLayoutManager(mLinearLayoutManager);
                    rv.setItemAnimator(new DefaultItemAnimator());
                    break;
            }

            if (hasContext) {
                adapter = new MultiTypeAdapter(list, context, CustomRecyclerView.this);
            } else {
                adapter = new MultiTypeAdapter(list, CustomRecyclerView.this);
            }
            rv.setAdapter(adapter);

            swipeRefresh.setEnabled(isRefreshable);
        }
    }


    public void stopSwipeRefresh() {
        if (swipeRefresh!=null)
            swipeRefresh.setRefreshing(false);
    }


    @Override
    public void isBottom(boolean bottom) {
        isBottom = bottom;
    }

    public void showEmptyView() {
//        if (isWarpContent){
//            ToastUtil.showToast(CustomRecyclerView.this.context, "列表为空");
//        }else {
            if (view == null) {
                LayoutInflater inflater = LayoutInflater.from(context);
                view = (RelativeLayout) inflater.inflate(R.layout.layout_custom_recycle_view_empty, null);
                view.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        actionLoadData();
                    }
                });
                this.addView(view);
            } else {
                view.setVisibility(View.VISIBLE);
            }
//        }
    }

    public void hideEmptyView() {
        if (view != null) {
            view.setVisibility(View.GONE);
        }
    }

    private void actionLoadData(){
        //通知model层刷新数据
//        EventBus.getDefault().post(new LoadDataEvent(IEventTag.LOAD_DATA, currentPage));
        Log.e(TAG, "actionLoadData: 通知model层刷新数据");
        if (swipeRefresh!=null)
        swipeRefresh.setRefreshing(false);
    }
}