package com.myapp.smartagricultureplus.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.myapp.smartagricultureplus.Object.Operation;
import com.myapp.smartagricultureplus.R;

import java.util.ArrayList;
import java.util.List;

public class OperationAdapter extends RecyclerView.Adapter<OperationAdapter.ViewHolder> {
    private List<Operation> mOperationList;

    public OperationAdapter(Context context,ArrayList<Operation> operationList) {

        this.mOperationList=operationList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView1,textView2,textView3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.iv_record_image);
            textView1=itemView.findViewById(R.id.tv_recod_name);
            textView2=itemView.findViewById(R.id.tv_record_date);
            textView3=itemView.findViewById(R.id.tv_record_specific);
        }
    }




    @NonNull
    @Override
    public OperationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_operationrecord_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
       return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull OperationAdapter.ViewHolder holder, int position) {
        Operation operation=mOperationList.get(position);
        holder.imageView.setImageResource(operation.getOperationimage());
        holder.textView1.setText(operation.getOperationname());
        holder.textView2.setText(operation.getOperationdate());
        holder.textView3.setText(operation.getOperationoperating());
     }


    @Override
    public int getItemCount() {
        return mOperationList.size();
    }


}
