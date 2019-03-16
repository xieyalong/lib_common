package com.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.lib_common.R;
import com.util.CUMagicTextView;

/**
 *
 */
public class MagicTextViewActivity extends AppCompatActivity {

    private static final String TAG = "-------------";
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magic_text_view);
        mTextView = findViewById(R.id.tvTest);
        showText();
        selector();
    }

    /**
     * 选择器
     */
    public  void selector(){
        NumberPicker numberPicker = findViewById(R.id.numberPicker);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(2);
        numberPicker.setDisplayedValues(new String[]{"aaa", "bbb", "ccc"});
        numberPicker.getValue();
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                System.out.println(">] onValueChange: " + newVal);//禁止更ui toast也不行
            }
        });
    }

    private void showText() {
        CUMagicTextView.getInstance(mTextView)
                //默认
                .append("这是测试")
                //设置图片在"这是测试"右边
                .append(R.mipmap.ic_launcher)
                //设置"点击这里"颜色 显示下划线
                .append("下划线-点击这里", getResources().getColor(R.color.colorPrimaryDark), true, new CUMagicTextView.OnTextClickListener() {
                    @Override
                    public void onClick(String text) {
                        Toast.makeText(MagicTextViewActivity.this, "点击成功", Toast.LENGTH_SHORT).show();
                    }
                })
//                设置"红色"颜色
                .append("红色", Color.RED)
                //设置"下划线"有下划线
                .append("下划线", true)
                .append("下划线颜色", getResources().getColor(R.color.colorPrimaryDark),true)
                .show();
    }
}