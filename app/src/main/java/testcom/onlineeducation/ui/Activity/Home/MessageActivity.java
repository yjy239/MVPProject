package testcom.onlineeducation.ui.Activity.Home;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import testcom.onlineeducation.Contract.MessageContract;
import testcom.onlineeducation.R;
import testcom.onlineeducation.presenter.Home.MessagePresentImpl;
import testcom.onlineeducation.ui.Activity.BaseActivity;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.CustomRecyclerView;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.MultiTypeAdapter;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.Visitable;

/**
 * Created by Administrator on 2017/6/22 0022.
 */
public class MessageActivity extends BaseActivity implements MessageContract.MessageView{

    @BindView(R.id.v)
    View v;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_head)
    RelativeLayout rlHead;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.custom_rv)
    CustomRecyclerView customRv;

    private MessagePresentImpl mPresenter;

    @Override
    protected int setViewId() {
        return R.layout.activity_message;
    }

    @Override
    protected void findViews() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void init() {
        mPresenter = new MessagePresentImpl(this);
        mPresenter.start();
        mPresenter.attchView(this);
    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }

    @Override
    public void initData(List<Visitable> list) {
        MultiTypeAdapter adapter = customRv.getAdapter();
        if (list.size() == 0) {
            customRv.showEmptyView();
        } else {
            customRv.hideEmptyView();
        }
        adapter.refreshData(list);
        if (customRv.isRefreshing()) {
            showShortToast("刷新成功");
        }
        customRv.stopSwipeRefresh();
    }

    @Override
    public void setPresenter(MessagePresentImpl presenter) {
        this.mPresenter = presenter;
    }
}
