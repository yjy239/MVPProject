package testcom.onlineeducation.bean;

import java.util.ArrayList;

import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.TypeFactory;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.Visitable;

/**
 * Created by Administrator on 2017/6/23 0023.
 */
public class HomeListBean implements Visitable{
    private String type;
    private ArrayList<Visitable> list;
    private int maxShowCount;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Visitable> getList() {
        return list;
    }

    public void setList(ArrayList<Visitable> list) {
        this.list = list;
    }

    public int getMaxShowCount() {
        return maxShowCount;
    }

    public void setMaxShowCount(int maxShowCount) {
        this.maxShowCount = maxShowCount;
    }

    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }
}
