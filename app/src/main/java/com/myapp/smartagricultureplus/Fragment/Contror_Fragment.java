package com.myapp.smartagricultureplus.Fragment;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.myapp.smartagricultureplus.Adapter.ControrAdapter;
import com.myapp.smartagricultureplus.Object.Device;
import com.myapp.smartagricultureplus.R;
import com.myapp.smartagricultureplus.tool.DatabaseHelper;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * 控制
 */
public class Contror_Fragment extends Fragment {
    ControrAdapter controrAdapter;
    DatabaseHelper dbHelper;
    private RecyclerView mRcvContror;
    private ArrayList<Device> devices = new ArrayList<>();;
    Device device;
    public SharedPreferences sp;
    public SharedPreferences.Editor edit;
    ProgressBar pb_loading;
    LinearLayout llt_loading;
    int startX,startY,stopX,stopY,y;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_contror_, container, false);
        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState){
            super.onActivityCreated(savedInstanceState);
            initView();
            initData();
            initAdapter();
            mRcvContror.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    switch (motionEvent.getAction()){
                        case MotionEvent.ACTION_DOWN:
                             startX=(int)motionEvent.getX();
                             startY=(int)motionEvent.getY();
                            break;
                        case MotionEvent.ACTION_MOVE:
                             stopX=(int)motionEvent.getX();
                             stopY=(int)motionEvent.getY();
//                            double z=Math.sqrt(Math.pow(x,2)+Math.pow(y, 2));
//                             xy=(int) Math.sqrt(Math.pow(Math.abs((stopX-startX)),2)-Math.pow(Math.abs((stopY-startY)),2));
                            y=stopY-startY;
                            Log.e("xy","xy="+y);
                            break;
                        case MotionEvent.ACTION_UP:
                            if (y>500){
                                pb_loading.setVisibility(View.VISIBLE);
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            Thread.sleep(1000);
                                            getActivity().runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    devices.clear();
                                                    initData();
                                                    controrAdapter.notifyDataSetChanged();
                                                    Toast.makeText(getActivity(),"更新成功",Toast.LENGTH_SHORT).show();
                                                    pb_loading.setVisibility(View.GONE);
                                                }
                                            });
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }).start();
                                return true;
                            }
                            break;
                    }
                    return false;
                }
            });
        }


    private void initAdapter() {
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mRcvContror.setLayoutManager(linearLayoutManager);
            controrAdapter=new ControrAdapter(getActivity(),devices);
            mRcvContror.setAdapter(controrAdapter);
        }

        private void initData() {
            sp = getActivity().getSharedPreferences("user", MODE_PRIVATE);
            String userId = sp.getString("userId","");
            if (userId!=""){
                dbHelper=new DatabaseHelper(getActivity(),"Devices",null,1);
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                String[] userIdValues = new String[]{userId};
                Cursor cursor=db.query("ControlDevices",null,"userId=?",userIdValues,null,null,null,null);
                if (cursor.getCount()>0){
                    cursor.moveToFirst();
//                + "userId text, "
//                + "deviceIcon integer, "
//                + "deviceName text)";
                    do{
                        String userIdReturn = cursor.getString(cursor.getColumnIndex("userId"));
                        int deviceIconReturn = cursor.getInt(cursor.getColumnIndex("deviceIcon"));
                        String deviceNameReturn = cursor.getString(cursor.getColumnIndex("deviceName"));
                        int devicesLayout = cursor.getInt(cursor.getColumnIndex("devicesLayout"));
                        device=new Device(userIdReturn,deviceIconReturn,deviceNameReturn,devicesLayout);
                        devices.add(device);
                    }while (cursor.moveToNext());
                    cursor.close();
                }
            }

        }

    private void initView() {
            pb_loading=getActivity().findViewById(R.id.pb_loading);
            mRcvContror=getActivity().findViewById(R.id.rcv_contror);
            llt_loading=getActivity().findViewById(R.id.llt_loading);
        }
}