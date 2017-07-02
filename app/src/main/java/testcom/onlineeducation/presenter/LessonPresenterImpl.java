package testcom.onlineeducation.presenter;

import java.util.ArrayList;
import java.util.List;

import testcom.onlineeducation.BasePresenter;
import testcom.onlineeducation.Contract.LessonContract;

/**
 * Created by Administrator on 2017/6/16.
 */
public class LessonPresenterImpl extends BasePresenter<LessonContract.LessonView> implements LessonContract.LessonPresent{

    public LessonContract.LessonView mView;
    private List<String> list = new ArrayList<>();

    public LessonPresenterImpl(LessonContract.LessonView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        loadData();
    }

    public void loadData(){
        for(int i = 0;i< 5;i++){
            list.add("1");
        }
        mView.initData(list);
    }


}
