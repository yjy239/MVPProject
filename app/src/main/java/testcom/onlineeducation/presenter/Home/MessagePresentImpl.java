package testcom.onlineeducation.presenter.Home;

import java.util.ArrayList;
import java.util.List;

import testcom.onlineeducation.BasePresenter;
import testcom.onlineeducation.Contract.MessageContract;
import testcom.onlineeducation.bean.MessageListBean;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.Visitable;

/**
 * Created by Administrator on 2017/6/22 0022.
 */
public class MessagePresentImpl extends BasePresenter<MessageContract.MessageView> implements MessageContract.MessagePresent {
    private MessageContract.MessageView mView;
    private List<Visitable> list = new ArrayList<>();

    //构造函数设置present
    public MessagePresentImpl(MessageContract.MessageView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }


    public void start() {
        loadData();
    }

    //初始化数据
    public void loadData() {
        for (int i = 0; i < 12; i++) {
            MessageListBean bean = new MessageListBean();
            bean.setTitle("您预约的的一门课程即将开始");
            int j = i+1;
            bean.setContent("英语老周第"+j+"讲");
            bean.setTime("4月25/9:00");
            list.add(bean);
        }
        mView.initData(list);

    }
}
