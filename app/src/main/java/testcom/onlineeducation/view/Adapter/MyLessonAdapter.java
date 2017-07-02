package testcom.onlineeducation.view.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import testcom.onlineeducation.R;

/**
 * Created by Administrator on 2017/6/19.
 */
public class MyLessonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context mContext;
    private List<String> mList;
    private int type;
    private LayoutInflater inflater;

    public MyLessonAdapter(Context mContext, List<String> mList, int type) {
        this.mContext = mContext;
        this.mList = mList;
        this.type = type;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyLessonViewHolder(inflater.inflate(R.layout.mylesson_layout, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyLessonViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.backimg)
        ImageView backimg;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.price)
        TextView price;

        public MyLessonViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
