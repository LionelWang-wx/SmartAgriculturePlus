package com.myapp.smartagricultureplus.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myapp.smartagricultureplus.Object.Device;
import com.myapp.smartagricultureplus.R;

import java.util.ArrayList;

public class ControrAdapter extends RecyclerView.Adapter<ControrAdapter.ControrViewHolder> {
    Context context;
    ArrayList<Device> devices;
    public ControrAdapter(Context context, ArrayList<Device> devices) {
        this.context=context;
        this.devices=devices;
    }

    public class ControrViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView contror_img;
        private TextView  contror_text;
        public ControrViewHolder(@NonNull View itemView) {
            super(itemView);
           contror_img = itemView.findViewById(R.id.iv_contror_img);
           contror_text = itemView.findViewById(R.id.te_contror_text);
        }
    }

    @NonNull
    @Override
    public ControrViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=null;
        ControrViewHolder controrViewHolder;
        if (view==null){
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_contror,parent,false);
            controrViewHolder= new ControrViewHolder(view);
            view.setTag(controrViewHolder);
        }else {
            controrViewHolder= (ControrViewHolder) view.getTag();
        }
        return controrViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ControrViewHolder holder, int position){
           Device device=devices.get(position);
           holder.contror_img.setImageResource(device.getDeviceIcon());
           holder.contror_text.setText(device.getDeviceName());
    }

    @Override
    public int getItemCount() {
        return devices.size();
    }
}
