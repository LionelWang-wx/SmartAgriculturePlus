package com.myapp.smartagricultureplus.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myapp.smartagricultureplus.Object.Device;
import com.myapp.smartagricultureplus.R;
import com.myapp.smartagricultureplus.tool.DatabaseHelper;

import java.util.ArrayList;

public class ControrAdapter extends RecyclerView.Adapter<ControrAdapter.ControrViewHolder> {
    Context context;
    ArrayList<Device> devices;
    DatabaseHelper dbHelper;
    public ControrAdapter(Context context, ArrayList<Device> devices) {
        this.context=context;
        this.devices=devices;
    }

    public class ControrViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView contror_img;
        private TextView  contror_text,tv_contror_delete;
        public ControrViewHolder(@NonNull View itemView) {
            super(itemView);
           contror_img = itemView.findViewById(R.id.iv_contror_img);
           contror_text = itemView.findViewById(R.id.te_contror_text);
           tv_contror_delete= itemView.findViewById(R.id.tv_contror_delete);
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
        controrViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        controrViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(controrViewHolder.tv_contror_delete.getVisibility()==View.VISIBLE){
                    controrViewHolder.tv_contror_delete.setVisibility(View.INVISIBLE);
                }else {
                    controrViewHolder.tv_contror_delete.setVisibility(View.VISIBLE);
                    //判断用户是否移除设备
                    controrViewHolder.tv_contror_delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int position=controrViewHolder.getLayoutPosition();
                            Device device=devices.get(position);
                            AlertDialog.Builder dialog=new AlertDialog.Builder(context);
                            dialog.setTitle("请确定是否删除该"+device.getDeviceName()+"设备?");
                            dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dbHelper=new DatabaseHelper(context,"Devices",null,1);
                                    SQLiteDatabase db=dbHelper.getWritableDatabase();
//                            + "userId text, "
//                                    + "deviceIcon integer, "
//                                    + "deviceName text)";
                                    String[] Values = new String[]{device.getUserId(),device.getDeviceName()};
                                    int isOK=db.delete("ControlDevices","userId=? and deviceName=?",Values);
                                    if (isOK>0){
                                        //更新界面删除效果

                                    }
                                }
                            });
                            dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                            dialog.show();
                        }
                    });
                }
                return true;
            }
        });
        return controrViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ControrViewHolder holder, int position){
           Device device=devices.get(position);
           holder.contror_img.setImageResource(device.getDeviceIcon());
           holder.contror_text.setText(device.getDeviceName());
           holder.tv_contror_delete.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return devices.size();
    }
}
