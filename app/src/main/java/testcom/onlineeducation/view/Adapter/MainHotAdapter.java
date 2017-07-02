package testcom.onlineeducation.view.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import testcom.onlineeducation.R;
import testcom.onlineeducation.model.HotBean;

/**
 * Created by Administrator on 2017/6/14.
 */
public class MainHotAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<HotBean> list;
    private Context context;
    private LayoutInflater inflater;

    public MainHotAdapter(Context context, List<HotBean> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HotViewHolder(inflater.inflate(R.layout.item_hot_layout, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HotViewHolder) {
            ((HotViewHolder) holder).name.setText(list.get(position).getName());
            ((HotViewHolder) holder).price.setText(list.get(position).getPrice());
            ((HotViewHolder) holder).inText.setText(list.get(position).getTitle());
            ((HotViewHolder) holder).item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HotViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.goods_pic)
        ImageView goodsPic;
        @BindView(R.id.in_text)
        TextView inText;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.item)
        RelativeLayout item;


        public HotViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
