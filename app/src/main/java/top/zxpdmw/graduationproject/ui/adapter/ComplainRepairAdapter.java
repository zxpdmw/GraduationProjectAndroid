package top.zxpdmw.graduationproject.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import top.zxpdmw.graduationproject.R;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import top.zxpdmw.graduationproject.bean.ComplainRepair;
import top.zxpdmw.graduationproject.bean.Notice;


@AllArgsConstructor
public class ComplainRepairAdapter extends RecyclerView.Adapter<ComplainRepairAdapter.ViewHolder> {
    private List<ComplainRepair> complainRepairs;
    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(),R.layout.item_list_complainrepair,null);
        //实例化ViewHolder
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        ComplainRepair vo = complainRepairs.get(position);
        //设置
        holder.type.setText(vo.getCr_type());
        holder.icon.setImageResource(R.drawable.touxiang);
        holder.message.setText(vo.getMessage());
    }

    @Override
    public int getItemCount() {
        return complainRepairs.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView icon;
        TextView type;
        TextView message;
        public ViewHolder(View view){
            super(view);
            icon=view.findViewById(R.id.cr_icon);
            type=view.findViewById(R.id.cr_type);
            message=view.findViewById(R.id.cr_message);
        }
    }

    public void add(ComplainRepair complainRepair){
        complainRepairs.add(0,complainRepair);
        notifyItemChanged(0);
    }

    public void delete(ComplainRepair position){
        complainRepairs.remove(position);
        notifyDataSetChanged();
    }
}
