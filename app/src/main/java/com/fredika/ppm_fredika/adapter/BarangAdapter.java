package com.fredika.ppm_fredika.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fredika.ppm_fredika.R;
import com.fredika.ppm_fredika.database.entity.LaptopEntity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.ViewAdapter> {
    private List<LaptopEntity> list;
    private Context context;
    private Dialog dialog;
    private ArrayList<LaptopEntity> LaptopEntityList;
    public interface Dialog{
        void onClick(int position);
    }


    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public BarangAdapter(Context context, List<LaptopEntity> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewAdapter onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewAdapter holder, int position) {
        try {
            String jumlah = list.get(position).getJumlah_barang().toString();
            holder.namalaptop.setText(String.format(context.getString(R.string.namalaptop)) + " : " + list.get(position).getNama_barang());
            holder.mereklaptop.setText(String.format(context.getString(R.string.mereklaptop)) + " : " + list.get(position).getMerek_barang());
            holder.harga.setText(String.format(context.getString(R.string.harga)) + " : " + list.get(position).getHarga_barang());
            holder.jumlah.setText(String.format(context.getString(R.string.jumlah)) + " : " + jumlah);

        }catch (Exception e){
            return;
        }

    }

    public void filterList(ArrayList<LaptopEntity> filteredList) {
        LaptopEntityList = filteredList;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewAdapter extends RecyclerView.ViewHolder {

        TextView namalaptop,mereklaptop,harga,jumlah;

        public ViewAdapter(@NonNull @NotNull View itemView) {
            super(itemView);
            namalaptop = itemView.findViewById(R.id.txt_namalaptop);
            mereklaptop = itemView.findViewById(R.id.txt_mereklaptop);
            harga = itemView.findViewById(R.id.txt_harga);
            jumlah = itemView.findViewById(R.id.txt_jumlah);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dialog!=null){
                        dialog.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }
}
