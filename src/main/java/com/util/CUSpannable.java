package com.util;

import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

/**
 *
 */
public class CUSpannable {
    private CUSpannable(){}
    private static CUSpannable instance;
    public  static CUSpannable getInstance(){
        if (null==instance){
            instance=new CUSpannable();
        }
        return  instance;
    }
    /**
     * 改变字符串中某一段文字的字号
     * setTextSize("",24,0,2) = null;
     * setTextSize(null,24,0,2) = null;
     * setTextSize("abc",-2,0,2) = null;
     * setTextSize("abc",24,0,4) = null;
     * setTextSize("abc",24,-2,2) = null;
     * setTextSize("abc",24,0,2) = normal string
     * */
    public  SpannableString setTextSize( String content, int startIndex, int endIndex, int fontSize ){
        if( TextUtils.isEmpty( content ) || fontSize <= 0 || startIndex >= endIndex || startIndex < 0 || endIndex >= content.length( ) ){
            return null;
        }

        SpannableString spannableString = new SpannableString( content );
        spannableString.setSpan( new AbsoluteSizeSpan( fontSize ), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );

        return spannableString;
    }

    public  SpannableString setTextSub( String content, int startIndex, int endIndex ){
        if( TextUtils.isEmpty( content ) || startIndex < 0 || endIndex >= content.length( ) || startIndex >= endIndex ){
            return null;
        }

        SpannableString spannableString = new SpannableString( content );
        spannableString.setSpan( new SubscriptSpan( ), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );

        return spannableString;
    }

    public  SpannableString setTextSuper( String content, int startIndex, int endIndex ){
        if( TextUtils.isEmpty( content ) || startIndex < 0 || endIndex >= content.length( ) || startIndex >= endIndex ){
            return null;
        }

        SpannableString spannableString = new SpannableString( content );
        spannableString.setSpan( new SuperscriptSpan( ), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );

        return spannableString;
    }

    public  SpannableString setTextStrikethrough( String content, int startIndex, int endIndex ){
        if( TextUtils.isEmpty( content ) || startIndex < 0 || endIndex >= content.length( ) || startIndex >= endIndex ){
            return null;
        }

        SpannableString spannableString = new SpannableString( content );
        spannableString.setSpan(new StrikethroughSpan(), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableString;
    }

    /**
     * 下划线
     * @param content
     * @param startIndex
     * @param endIndex
     * @return
     */
    public  SpannableString setTextUnderline( String content, int startIndex, int endIndex ){
        if( TextUtils.isEmpty( content ) || startIndex < 0 || endIndex >= content.length( ) || startIndex >= endIndex ){
            return null;
        }

        SpannableString spannableString = new SpannableString( content );
        spannableString.setSpan(new UnderlineSpan(), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableString;
    }

    /**
     * 粗体
     * @param content
     * @param startIndex
     * @param endIndex
     * @return
     */
    public  SpannableString setTextBold( String content, int startIndex, int endIndex ){
        if( TextUtils.isEmpty( content ) || startIndex < 0 || endIndex >= content.length( ) || startIndex >= endIndex ){
            return null;
        }

        SpannableString spannableString = new SpannableString( content );
        spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableString;
    }

    /**
     * 斜体
     * @param content
     * @param startIndex
     * @param endIndex
     * @return
     */
    public  SpannableString setTextItalic( String content, int startIndex, int endIndex ){
        if( TextUtils.isEmpty( content ) || startIndex < 0 || endIndex >= content.length( ) || startIndex >= endIndex ){
            return null;
        }

        SpannableString spannableString = new SpannableString( content );
        spannableString.setSpan(new StyleSpan(android.graphics.Typeface.ITALIC), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableString;
    }

    public  SpannableString setTextBoldItalic( String content, int startIndex, int endIndex ){
        if( TextUtils.isEmpty( content ) || startIndex < 0 || endIndex >= content.length( ) || startIndex >= endIndex ){
            return null;
        }

        SpannableString spannableString = new SpannableString( content );
        spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD_ITALIC), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableString;
    }

    public  SpannableString setTextForeground( String content, int startIndex, int endIndex, int foregroundColor ){
        if( TextUtils.isEmpty( content ) || startIndex < 0 || endIndex >= content.length( ) || startIndex >= endIndex ){
            return null;
        }

        SpannableString spannableString = new SpannableString( content );
        spannableString.setSpan(new ForegroundColorSpan( foregroundColor ), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableString;
    }

    public  SpannableString setTextBackground( String content, int startIndex, int endIndex, int backgroundColor ){
        if( TextUtils.isEmpty( content ) || startIndex < 0 || endIndex >= content.length( ) || startIndex >= endIndex ){
            return null;
        }

        SpannableString spannableString = new SpannableString( content );
        spannableString.setSpan(new BackgroundColorSpan( backgroundColor ), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableString;
    }

    /**
     * 设置文本的超链接
     * @param content 需要处理的文本
     * @param startIndex
     * @param endIndex 被处理文本中需要处理字串的开始和结束索引
     * @param url 文本对应的链接地址，需要注意格式：
     * （1）电话以"tel:"打头，比如"tel:02355692427"
     * （2）邮件以"mailto:"打头，比如"mailto:zmywly8866@gmail.com"
     * （3）短信以"sms:"打头，比如"sms:02355692427"
     * （4）彩信以"mms:"打头，比如"mms:02355692427"
     * （5）地图以"geo:"打头，比如"geo:68.426537,68.123456"
     * （6）网络以"http://"打头，比如"http://www.google.com"
     * */
    public  SpannableString setTextURL( String content, int startIndex, int endIndex, String url ){
        if( TextUtils.isEmpty( content ) || startIndex < 0 || endIndex >= content.length( ) || startIndex >= endIndex ){
            return null;
        }

        SpannableString spannableString = new SpannableString( content );
        spannableString.setSpan(new URLSpan( url ), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableString;
    }

    public  SpannableString setTextImg( String content, int startIndex, int endIndex, Drawable drawable ){
        if( TextUtils.isEmpty( content ) || startIndex < 0 || endIndex >= content.length( ) || startIndex >= endIndex ){
            return null;
        }

        SpannableString spannableString = new SpannableString( content );
        spannableString.setSpan(new ImageSpan(drawable), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableString;
    }
    public  void centreLine(TextView tv){
        tv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//中划线
        tv.getPaint().setAntiAlias(true); //去掉锯齿
    }
    public  void  belowLine(TextView tv){
        tv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        tv.getPaint().setAntiAlias(true); //去掉锯齿
    }

}
