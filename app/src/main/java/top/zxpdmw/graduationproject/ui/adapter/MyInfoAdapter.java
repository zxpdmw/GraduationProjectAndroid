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
import top.zxpdmw.graduationproject.bean.Module;

public class MyInfoAdapter extends RecyclerView.Adapter<MyInfoAdapter.ViewHolder> {
    List<Module> list;
    public MyInfoAdapter(List<Module> modules){
        this.list=modules;
    }


    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view=View.inflate(parent.getContext(),R.layout.item_my,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Module module=list.get(position);
        holder.icon.setBackgroundResource(module.getImg_icon());
        holder.text.setText(module.getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView icon;
        TextView text;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            icon=itemView.findViewById(R.id.icon);
            text=itemView.findViewById(R.id.text);
        }
    }
}
