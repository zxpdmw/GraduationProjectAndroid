package top.zxpdmw.graduationproject.ui.adapter;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import top.zxpdmw.graduationproject.R;
import top.zxpdmw.graduationproject.bean.HouseRentSale;


public class HouseRentSaleAdapter extends RecyclerView.Adapter<HouseRentSaleAdapter.ViewHolder> {
     List<HouseRentSale> houseRentSales;

    public HouseRentSaleAdapter(List<HouseRentSale> list) {
        this.houseRentSales = list;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view=View.inflate(parent.getContext(),R.layout.item_list_house,null);
        return new ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        HouseRentSale houseRentSale = houseRentSales.get(position);
        holder.imageView.setBackgroundResource(R.drawable.touxiang);
        holder.message.setText(houseRentSale.getMessage());
        if (houseRentSale.getT().equals("sale")){
            holder.price.setText(houseRentSale.getPrice() + "元/平");
        }else {
            holder.price.setText(houseRentSale.getPrice() + "元/月");
        }
        holder.price.setTextColor(R.color.red);
    }

    @Override
    public int getItemCount() {
        return houseRentSales.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView message;
        TextView price;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.house_image);
            message=itemView.findViewById(R.id.house_message);
            price=itemView.findViewById(R.id.house_price);
        }
    }

    public void add(HouseRentSale houseRentSale){
        houseRentSales.add(houseRentSale);
        notifyItemChanged(0);
    }

    public void delete(HouseRentSale houseRentSale){
        houseRentSales.remove(houseRentSale);
        notifyDataSetChanged();
    }

}
