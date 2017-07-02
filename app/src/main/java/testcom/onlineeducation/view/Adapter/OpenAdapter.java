package testcom.onlineeducation.view.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import testcom.onlineeducation.R;
import testcom.onlineeducation.ui.Activity.Detailed.PayActivity;

/**
 * Created by Administrator on 2017/6/19.
 */
public class OpenAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<String> mList;
    private LayoutInflater inflater;

    public OpenAdapter(Context context, List<String> mList) {
        this.mContext = context;
        this.mList = mList;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new openViewHolder(inflater.inflate(R.layout.openlesson_layout, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof openViewHolder){
            ((openViewHolder) holder).item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(mContext, PayActivity.class);
                    i.putExtra("type",1);
                    mContext.startActivity(i);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class openViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.goods_pic)
        ImageView goodsPic;
        @BindView(R.id.in_text)
        TextView inText;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.item)
        View item;

        public openViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
