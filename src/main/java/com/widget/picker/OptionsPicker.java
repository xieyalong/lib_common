package com.widget.picker;

import android.view.View;
import android.widget.TextView;

import com.bean.KVBean;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.util.CUActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 自动以内容选择器
 */
public class OptionsPicker {
    OptionsPickerView pvOptions;
    List<KVBean> options1Items;
    public OptionsPicker() {
        options1Items = new ArrayList<>();
//        initOptionPicker(activity,null);
//        options1Items.add(new KVBean("广东1","其他数据"));
//        options1Items.add(new KVBean("广东2","其他数据"));
//        options1Items.add(new KVBean("广东3","其他数据"));
    }
    OptionsSelectClick click;
    public  void show(View v,  List<KVBean> options1Items,OptionsSelectClick click){
        this.click=click;
        initOptionPicker(click);
        this.options1Items=options1Items;
        pvOptions.setPicker(options1Items);
        pvOptions.show(v);
    }
    public  void show(View v,  List<KVBean> options1Items,int index,OptionsSelectClick click){
        this.click=click;
        initOptionPicker(click);
        this.options1Items=options1Items;
        pvOptions.setPicker(options1Items);
        pvOptions.setSelectOptions(index);
        pvOptions.show(v);
    }

    private void initOptionPicker(final  OptionsSelectClick click) {//条件选择器初始化

        /**
         * 注意 ：如果是三级联动的数据(省市区等)，请参照 JsonDataActivity 类里面的写法。
         */
        pvOptions = new OptionsPickerBuilder(CUActivity.getIntanc().getActivity(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText();
                ((TextView)v).setText(tx);
                ((TextView)v).setTag(options1Items.get(options1));
                if (null!=click){
                    click.onOptionsSelect(v);
                }
            }
        })
//                .setTitleText("医院选择")
//                .setContentTextSize(20)//设置滚轮文字大小
//                .setDividerColor(Color.LTGRAY)//设置分割线的颜色
                .setSelectOptions(0)//默认选中项
//                .setBgColor(Color.BLACK)
//                .setTitleBgColor(Color.DKGRAY)
//                .setTitleColor(Color.LTGRAY)
//                .setCancelColor(Color.YELLOW)
//                .setSubmitColor(Color.YELLOW)
//                .setTextColorCenter(Color.LTGRAY)
//                .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
//                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
//                .setLabels("省", "市", "区")
//                .setBackgroundId(0x00000000) //设置外部遮罩颜色
                .setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() {
                    @Override
                    public void onOptionsSelectChanged(int options1, int options2, int options3) {
                        String str = "options1: " + options1 + "\noptions2: " + options2 + "\noptions3: " + options3;
//                        Toast.makeText(activity, str, Toast.LENGTH_SHORT).show();
                        if (null!=click){
                            click.onOptionsSelectChanged(options1,options2,options3);
                        }
                    }
                })
                .build();

//        pvOptions.setSelectOptions(1,1);
//        pvOptions.setPicker(options1Items);//一级选择器*/
//        pvOptions.setPicker(options1Items, options2Items);//二级选择器
        /*pvOptions.setPicker(options1Items, options2Items,options3Items);//三级选择器*/
    }

    //    //选项1
//        options1Items.add(new ProvinceBean(0, "广东", "描述部分", "其他数据"));
//        options1Items.add(new ProvinceBean(1, "湖南", "描述部分", "其他数据"));
//        options1Items.add(new ProvinceBean(2, "广西", "描述部分", "其他数据"));
//
//    //选项2
//    ArrayList<String> options2Items_01 = new ArrayList<>();
//        options2Items_01.add("广州");
//        options2Items_01.add("佛山");
//        options2Items_01.add("东莞");
//        options2Items_01.add("珠海");
//    ArrayList<String> options2Items_02 = new ArrayList<>();
//        options2Items_02.add("长沙");
//        options2Items_02.add("岳阳");
//        options2Items_02.add("株洲");
//        options2Items_02.add("衡阳");
//    ArrayList<String> options2Items_03 = new ArrayList<>();
//        options2Items_03.add("桂林");
//        options2Items_03.add("玉林");
//        options2Items.add(options2Items_01);
//        options2Items.add(options2Items_02);
//        options2Items.add(options2Items_03);
    public interface  OptionsSelectClick{
        void onOptionsSelect(View v);
        void onOptionsSelectChanged(int options1, int options2, int options3);
    }
}
