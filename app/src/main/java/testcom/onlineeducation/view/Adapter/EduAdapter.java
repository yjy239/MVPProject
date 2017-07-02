package testcom.onlineeducation.view.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import testcom.onlineeducation.R;

/**
 * Created by Administrator on 2017/6/28.
 */
public class EduAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> list = new ArrayList<>();
    private LayoutInflater inflater;
    private Context mContext;
    private ItemOnClick onClick;

    public EduAdapter(Context context, List<String> list) {
        this.list = list;
        mContext = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EduHolder(inflater.inflate(R.layout.item_edu, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof EduHolder){
            ((EduHolder) holder).name.setText(list.get(position));
            ((EduHolder) holder).item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClick.onItemClick(v,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class EduHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.item)
        RelativeLayout item;

        public EduHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public void setOnItemClickLisenter(ItemOnClick onClick){
        this.onClick = onClick;
    }

    public interface ItemOnClick{
        void onItemClick(View v,int position);
    }
}
