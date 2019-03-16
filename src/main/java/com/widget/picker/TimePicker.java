package com.widget.picker;

import android.app.Dialog;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.util.CUActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimePicker {
    TimePickerBuilder timePickerBuilder;
    TimePickerView pvTime;
    TimePickerClick click;
    Dialog mDialog;
    boolean[] booleans;
    Calendar startDate,endDate,defultDate;
    public TimePicker() {
    }

    /**
     *
     * @param v
     */
    public  void show(View v){
        // pvTime.setDate(Calendar.getInstance());
//        pvTime.show(); //show timePicker*/
        pvTime.show(v);//弹出时间选择器，传递参数过去，回调的时候则可以绑定此view
    }

    /**
     * 设置年月日十几分秒 在中间弹出dialog
     * @param v
     * @param booleans
     */
    public  void show(View v, boolean[] booleans){
        this.booleans=booleans;
        initTimePicker();
        setData();
        // pvTime.setDate(Calendar.getInstance());//设置默认时间
//        pvTime.show(); //show timePicker*/
        pvTime.show(v);//弹出时间选择器，传递参数过去，回调的时候则可以绑定此view
    }

    /**
     *
     * @param v
     * @param booleans
     * @param startDate
     * @param endDate
     * @param defultDate
     *        Calendar startDate = Calendar.getInstance();
    startDate.setTime(new Date());
    startDate.add(Calendar.YEAR, -1);

    Calendar endDate = Calendar.getInstance();
    endDate.setTime(new Date());
    endDate.add(Calendar.YEAR, +1);
     */
    public  void show(View v, boolean[] booleans,Calendar startDate, Calendar endDate,Calendar defultDate){
        this.booleans=booleans;
        this.startDate=startDate;
        this.endDate=endDate;
        this.defultDate=defultDate;
        setData();
        // pvTime.setDate(Calendar.getInstance());//设置默认时间
//        pvTime.show(); //show timePicker*/
        pvTime.show(v);//弹出时间选择器，传递参数过去，回调的时候则可以绑定此view
    }
    public  void show(View v, boolean[] booleans, Calendar startDate,Calendar endDate,Calendar defultDate,TimePickerClick click){
        this.booleans=booleans;
        this.defultDate=defultDate;
        this.endDate=endDate;
        this.startDate=startDate;
        this.click=click;
        initTimePicker();
        setData();
        // Calendar startDate = Calendar.getInstance();
//        startDate.set(2018,11,12);
//        Calendar endDate = Calendar.getInstance();
//        endDate.set(2018,12,12);
//        timePickerBuilder.setRangDate(startDate, endDate);
        // pvTime.setDate(Calendar.getInstance());//设置默认时间
//        pvTime.show(); //show timePicker*/
        pvTime.show(v);//弹出时间选择器，传递参数过去，回调的时候则可以绑定此view
    }



    public  void show(TimePickerClick click){
        this.click=click;
        pvTime.show();//弹出时间选择器，传递参数过去，回调的时候则可以绑定此view
    }
    public  void show( boolean[] booleans,Calendar startDate, Calendar endDate,Calendar defultDate,TimePickerClick click){
        this.booleans=booleans;
        this.startDate=startDate;
        this.endDate=endDate;
        this.defultDate=defultDate;
        this.click=click;
        setData();
        pvTime.show();//弹出时间选择器，传递参数过去，回调的时候则可以绑定此view
    }
    private void initTimePicker() {//Dialog 模式下，在底部弹出
        if (null==timePickerBuilder){
            timePickerBuilder = new TimePickerBuilder(CUActivity.getIntanc().getActivity(), new OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {
                    if (null!=v){
                        ((TextView)v).setText(getTime(date));
                    }
                    if (null!=click){
                        click.onTimeSelect(getTime(date));
                    }
                }
            }) .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                @Override
                public void onTimeSelectChanged(Date date) {
                    if (null!=click){
                        click.onTimeSelectChanged(date);
                    }
                }
            });
        }
        setData();
    }
    private   void setData(){
        try {
            if (null==booleans){
                booleans= new boolean[]{true, true, true, false, false, false};
            }
            timePickerBuilder.setType(booleans)//setType(new boolean[]{true, true, true, false, false, false})
                    .isDialog(true); //默认设置false ，内部实现将DecorView 作为它的父控件。
            //设置开发时间
            if (startDate!=null&&endDate!=null){
                timePickerBuilder.setRangDate(startDate, endDate);
                if (null==defultDate){
                    timePickerBuilder.setDate(Calendar.getInstance());
                }
            }
            //设置默认值
            if (null!=defultDate){
                timePickerBuilder.setDate(defultDate);
            }
            pvTime=timePickerBuilder.build();

            mDialog = pvTime.getDialog();
            if (mDialog != null) {
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        Gravity.BOTTOM);
                params.leftMargin = 0;
                params.rightMargin = 0;
                pvTime.getDialogContainerLayout().setLayoutParams(params);

                Window dialogWindow = mDialog.getWindow();
                if (dialogWindow != null) {
                    dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                    dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = null;
        try {
            Log.d("getTime()", "choice date millis: " + date.getTime());
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String mat="";
            int int_true=0;
            for (int i = 0; i <booleans.length ; i++) {
                if (booleans[i]){
                    int_true++;
                }
            }
            if (3==int_true){
                mat="yyyy-MM-dd";
            }else if (5==int_true){
                mat="yyyy-MM-dd HH:mm";
            }
            format = new SimpleDateFormat(mat);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return format.format(date);
    }
    public  interface    TimePickerClick{
        void onTimeSelect(String time);
        void onTimeSelectChanged(Date date);
    }
}
