package top.zxpdmw.graduationproject.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.bean.Notice;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder> {
    List<Notice> notices;
    ItemClickListener mItemClickListener;

    public void setOnItemClickListener(ItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    public NoticeAdapter(List<Notice> list){
        this.notices=list;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(),R.layout.item_list_notice,null);
        //实例化ViewHolder
        NoticeAdapter.ViewHolder holder = new NoticeAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Notice vo = notices.get(position);
        //设置
        holder.notice_publish_time.setText(vo.getPublish_time());
        holder.notice_icon.setImageResource(R.drawable.touxiang);
        holder.notice_title.setText(vo.getTitle());
        if(mItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.OnItemClickListener(holder.getAdapterPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return notices.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView notice_icon;
        TextView notice_title;
        TextView notice_publish_time;
        public ViewHolder(View view){
            super(view);
            notice_icon = view.findViewById(R.id.notice_icon);
            notice_title = view.findViewById(R.id.notice_title);
            notice_publish_time = view.findViewById(R.id.notice_publish_time);
        }
    }
}
