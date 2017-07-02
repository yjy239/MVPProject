package testcom.onlineeducation.bean;

import java.util.ArrayList;

import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.TypeFactory;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.Visitable;

/**
 * Created by Administrator on 2017/6/23 0023.
 */
public class HomeHeaderBean implements Visitable{

    private ArrayList<Visitable> messageList;
    private ArrayList<BannerListBean> bannerList;

    public ArrayList<Visitable> getMessageList() {
        return messageList;
    }

    public void setMessageList(ArrayList<Visitable> messageList) {
        this.messageList = messageList;
    }

    public ArrayList<BannerListBean> getBannerList() {
        return bannerList;
    }

    public void setBannerList(ArrayList<BannerListBean> bannerList) {
        this.bannerList = bannerList;
    }


    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }
}
