package testcom.onlineeducation.view.Adapter;

import android.content.Context;
import android.content.Intent;
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
import testcom.onlineeducation.ui.Activity.Detailed.PayActivity;

/**
 * Created by Administrator on 2017/6/19.
 */
public class AppointAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> mList;
    private Context mContext;
    private LayoutInflater inflater;

    public AppointAdapter(Context mContext, List<String> mList) {
        this.mContext = mContext;
        this.mList = mList;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AppointViewHolder(inflater.inflate(R.layout.appoint_item_layout, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof AppointViewHolder){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(mContext, PayActivity.class);
                    mContext.startActivity(i);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class AppointViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.head)
        ImageView head;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.sort)
        TextView sort;
        @BindView(R.id.appoint)
        TextView appoint;
        @BindView(R.id.price)
        TextView price;

        public AppointViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
