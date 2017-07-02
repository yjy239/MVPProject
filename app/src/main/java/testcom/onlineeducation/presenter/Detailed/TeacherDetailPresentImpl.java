package testcom.onlineeducation.presenter.Detailed;

import java.util.ArrayList;
import java.util.List;

import testcom.onlineeducation.BasePresenter;
import testcom.onlineeducation.Contract.TeacherDetailContract;

/**
 * Created by Administrator on 2017/6/23.
 */
public class TeacherDetailPresentImpl extends BasePresenter<TeacherDetailContract.View>implements TeacherDetailContract.Presenter{

    private TeacherDetailContract.View mView;
    private List<String> list = new ArrayList<>();

    public TeacherDetailPresentImpl(TeacherDetailContract.View mView){
        this.mView = mView;
        mView.setPresenter(this);

    }

    @Override
    public void start() {
        for(int i = 0;i<5;i++){
            list.add("1");
        }
        mView.initData(list);
    }
}
