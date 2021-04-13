package top.zxpdmw.graduationproject.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import lombok.AllArgsConstructor;
import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.bean.ComplainRepair;
import top.zxpdmw.graduationproject.bean.HouseKeeping;

@AllArgsConstructor
public class HouseKeepingAdapter extends RecyclerView.Adapter<HouseKeepingAdapter.ViewHolder> {
    List<HouseKeeping> list;

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_list_house_keeping, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        HouseKeeping houseKeeping=list.get(position);
        holder.icon.setBackgroundResource(R.drawable.touxiang);
        holder.type.setText(houseKeeping.getHk_type());
        holder.message.setText(houseKeeping.getHk_type());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView type;
        TextView message;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            icon=itemView.findViewById(R.id.hk_icon);
            type=itemView.findViewById(R.id.hk_type);
            message=itemView.findViewById(R.id.hk_message);
        }
    }

    public void add(HouseKeeping complainRepair){
        list.add(0,complainRepair);
        notifyItemChanged(0);
    }

    public void delete(HouseKeeping position){
        list.remove(position);
        notifyDataSetChanged();
    }
}
