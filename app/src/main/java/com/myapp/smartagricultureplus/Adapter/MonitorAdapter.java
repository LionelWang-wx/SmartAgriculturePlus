package com.myapp.smartagricultureplus.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.myapp.smartagricultureplus.Object.Monitor;
import com.myapp.smartagricultureplus.R;
import java.util.ArrayList;

public class MonitorAdapter extends RecyclerView.Adapter<MonitorAdapter.MonitorViewHolder> {

Context context;
ArrayList<Monitor> monitors;


    public MonitorAdapter(Context context, ArrayList<Monitor> monitors) {
        this.context = context;
        this.monitors = monitors;
    }

    @NonNull
    @Override
    public MonitorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=null;
        MonitorViewHolder monitorViewHolder;
        if (view==null){
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.monitor_item,parent,false);
            monitorViewHolder = new MonitorViewHolder(view);
            view.setTag(monitorViewHolder);
        }else {
            monitorViewHolder= (MonitorViewHolder) view.getTag();
        }
        return monitorViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MonitorViewHolder holder, int position) {
        Monitor monitor=monitors.get(position);
        holder.iv_monitorIcon.setImageResource(monitor.getMonitorIcon());
        holder.tv_monitorName.setText(monitor.getMonitorName());
    }

    @Override
    public int getItemCount() {
        return monitors.size();
    }

    class MonitorViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_monitorIcon;
        TextView tv_monitorPoint;
        TextView tv_monitorName;
        public MonitorViewHolder(@NonNull View itemView) {
            super(itemView);
             iv_monitorIcon=itemView.findViewById(R.id.iv_monitorIcon);
//             tv_monitorPoint=itemView.findViewById(R.id.tv_monitorPoint);
             tv_monitorName=itemView.findViewById(R.id.tv_monitorName);
        }
    }
}
