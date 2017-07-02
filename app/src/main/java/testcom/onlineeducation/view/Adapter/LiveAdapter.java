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
 * Created by Administrator on 2017/6/17.
 */
public class LiveAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {



    private List<String> list;
    private Context mContext;
    private LayoutInflater inflater;

    public LiveAdapter(Context context, List<String> list) {
        this.list = list;
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LiveViewHolder(inflater.inflate(R.layout.live_item_layout, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof LiveViewHolder) {
            ((LiveViewHolder) holder).item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(mContext,PayActivity.class);
                    mContext.startActivity(i);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class LiveViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.line)
        View line;
        @BindView(R.id.line2)
        View line2;
        @BindView(R.id.backimg)
        ImageView backimg;
        @BindView(R.id.live_status)
        LinearLayout liveStatus;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.peoplenum)
        TextView peoplenum;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.item)
        RelativeLayout item;

        public LiveViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
