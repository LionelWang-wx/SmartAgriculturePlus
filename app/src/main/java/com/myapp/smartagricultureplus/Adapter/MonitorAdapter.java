package com.myapp.smartagricultureplus.Adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import com.myapp.smartagricultureplus.Object.Device;
import com.myapp.smartagricultureplus.R;
import com.myapp.smartagricultureplus.tool.DatabaseHelper;
import java.util.ArrayList;
import java.util.HashMap;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;
import static android.content.Context.MODE_PRIVATE;

public class MonitorAdapter extends RecyclerView.Adapter<MonitorAdapter.MonitorViewHolder> {

Context context;
ArrayList<Device> devices;
    public SharedPreferences sp;
    public SharedPreferences.Editor edit;
    public DatabaseHelper dbHelper;
    public MonitorAdapter(Context context, ArrayList<Device> devices) {
        this.context = context;
        this.devices = devices;
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
        monitorViewHolder.tv_deviceIsFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position=monitorViewHolder.getLayoutPosition();
                Device device=devices.get(position);
                sp = context.getSharedPreferences("user", MODE_PRIVATE);
                String userId = sp.getString("userId","");
                if(userId!=""){
                    //用户已登录
                    AlertDialog.Builder dialog=new AlertDialog.Builder(context);
                    dialog.setTitle("请确定是否添加"+device.getDeviceName()+"?");
                    dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ProgressDialog progressDialog=new ProgressDialog(context);
                            progressDialog.setTitle("正在连接"+device.getDeviceName());
                            progressDialog.setMessage("loading...");
                            progressDialog.setCancelable(true);
                            progressDialog.show();
                            Thread t0=new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    ((Activity)context).runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            dbHelper=new DatabaseHelper(context,"Devices",null,1);
                                            SQLiteDatabase db=dbHelper.getWritableDatabase();
                                            ContentValues contentValues=new ContentValues();
                                            contentValues.put("userId",userId);
                                            contentValues.put("deviceIcon",device.getDeviceIcon());
                                            contentValues.put("deviceName",device.getDeviceName());
                                            contentValues.put("devicesLayout",device.getDevicesLayout());
                                            db.insert("ControlDevices",null,contentValues);
                                            db.close();
                                        }
                                    });
                                }
                            });
                            t0.start();
                            Thread t1=new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(3000);
                                        progressDialog.dismiss();
                                        ((Activity)context).runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(context,"添加成功",Toast.LENGTH_SHORT).show();
                                                monitorViewHolder.iv_deviceIsFinishIcon.setImageResource(R.mipmap.finished);
                                                monitorViewHolder.tv_deviceIsFinish.setVisibility(View.INVISIBLE);
                                            }
                                        });
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            t1.start();
                        }
                    });
                    dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(context,"取消添加",Toast.LENGTH_SHORT).show();
                        }
                    });
                    dialog.show();
                }else{
                        //未登录，提示用户登录
                    AlertDialog.Builder dialog=new AlertDialog.Builder(context);
                    dialog.setTitle("请登录使用？");
                    dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            sp = context.getSharedPreferences("user", MODE_PRIVATE);
                            String userId = sp.getString("userId","");
                            if (userId==""){
                                sendCode(context);
                            }
                        }
                    });
                    dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(context,"取消登录",Toast.LENGTH_SHORT).show();
                        }
                    });
                    dialog.show();
                }
            }
        });
        return monitorViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MonitorViewHolder holder, int position) {
        Device device=devices.get(position);
        int s=device.getDevicesLayout();
        holder.iv_deviceIcon.setImageResource(device.getDeviceIcon());
        holder.tv_deviceName.setText(device.getDeviceName());
        holder.rl_deviceBackground.setBackgroundResource(device.getDeviceBackground());
    }

    @Override
    public int getItemCount() {
        return devices.size();
    }

    class MonitorViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_deviceIsFinishIcon;
        ImageView iv_deviceIcon;
        TextView tv_deviceName;
        TextView tv_deviceIsFinish;
        RelativeLayout rl_deviceBackground;
        public MonitorViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_deviceIsFinishIcon=itemView.findViewById(R.id.iv_deviceIsFinishIcon);
            iv_deviceIcon=itemView.findViewById(R.id.iv_deviceIcon);
            tv_deviceName=itemView.findViewById(R.id.tv_deviceName);
            tv_deviceIsFinish=itemView.findViewById(R.id.tv_deviceIsFinish);
            rl_deviceBackground=itemView.findViewById(R.id.rl_deviceBackground);
        }
    }
    public void sendCode(Context context) {
        RegisterPage page = new RegisterPage();
        //如果使用我们的ui，没有申请模板编号的情况下需传null
        page.setTempCode(null);
        page.setRegisterCallback(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE){
                    // 处理成功的结果
                    HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
                    // 国家代码，如“86”
                    String country = (String) phoneMap.get("country");
                    // 手机号码，如“13800138000”
                     String phone = (String) phoneMap.get("phone");
                    // TODO 利用国家代码和手机号码进行后续的操作
                    edit = sp.edit();
                    edit.putString("userId",phone);
                    edit.commit();
                } else{
                    // TODO 处理错误的结果
                }
            }
        });
        page.show(context);
    }

}
