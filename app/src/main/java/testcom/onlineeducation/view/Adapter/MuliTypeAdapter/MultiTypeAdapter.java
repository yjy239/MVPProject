package testcom.onlineeducation.view.Adapter.MuliTypeAdapter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import testcom.onlineeducation.i.IBottom;

/**
 * Created by Administrator on 2017/3/29 0029.
 */
public class MultiTypeAdapter extends RecyclerView.Adapter<BaseViewHolder> implements View.OnClickListener{
    private TypeFactory typeFactory;
    private List<Visitable> models;
    private IBottom iBottom;
    private OnItemClick onItemClick;

    public MultiTypeAdapter(List<Visitable> models) {
        this.models = models;
        this.typeFactory = new TypeFactoryForList();
    }

    public MultiTypeAdapter(List<Visitable> models, Context context) {
        this.models = models;
        this.typeFactory = new TypeFactoryForList(context);
    }

    public MultiTypeAdapter(List<Visitable> models, IBottom iBottom) {
        this.models = models;
        this.typeFactory = new TypeFactoryForList();
        this.iBottom = iBottom;
    }

    public MultiTypeAdapter(List<Visitable> models, Context context, IBottom iBottom) {
        this.models = models;
        this.typeFactory = new TypeFactoryForList(context);
        this.iBottom = iBottom;
    }

    public void addMoreData(List<Visitable> models) {
        this.models.addAll(models);
        notifyDataSetChanged();
    }

    public void refreshData(List<Visitable> models){
        if (this.models != null){
            this.models.clear();
            this.models.addAll(models);
        }else {
            this.models = models;
        }
        notifyDataSetChanged();
    }

    private void specialUpdate() {
        Handler handler = new Handler();
        final Runnable r = new Runnable() {
            public void run() {
                notifyItemChanged(getItemCount() - 1);
            }
        };
        handler.post(r);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        View itemView = View.inflate(context, viewType, null);
        return typeFactory.createViewHolder(viewType, itemView);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.setUpView(models.get(position), position, this);
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(this);
        if (iBottom != null) {
            if (position == getItemCount() - 1) {
                iBottom.isBottom(true);
            } else {
                iBottom.isBottom(false);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (null == models) {
            return 0;
        }
        return models.size();
    }


    @Override
    public int getItemViewType(int position) {
        return models.get(position).type(typeFactory);
    }

    public List<Visitable> getModels() {
        return models;
    }

    @Override
    public void onClick(View v) {
        if (onItemClick != null)
        onItemClick.onItemClick(v,(int)v.getTag());
    }

    public interface OnItemClick{
        void onItemClick(View v,int position);
    }

    public void setOnItemClickListener(OnItemClick onItemClick){
        this.onItemClick = onItemClick;
    }

}
