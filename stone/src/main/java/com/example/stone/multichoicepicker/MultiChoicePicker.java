package com.example.stone.multichoicepicker;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.lib.WheelView;
import com.bigkoo.pickerview.view.BasePickerView;
import com.example.stone.R;


import java.util.ArrayList;
import java.util.List;

/**
 * @author Stone
 * @version V1.0
 * @description:
 * @date 2017/6/14 0014 16:51
 * @email redsstone@163.com
 */


public class MultiChoicePicker extends BasePickerView implements View.OnClickListener {


    private ListView mListView;
    private int layoutRes;
    private Button btnSubmit, btnCancel; //确定、取消按钮
    private TextView tvTitle;
    private RelativeLayout rv_top_bar;

    private static final String TAG_SUBMIT = "submit";
    private static final String TAG_CANCEL = "cancel";

    private OnMultiChoiceClickListener onMultiChoiceClickListener;

    private String Str_Submit;//确定按钮文字
    private String Str_Cancel;//取消按钮文字
    private String Str_Title;//标题文字

    private int Color_Submit;//确定按钮颜色
    private int Color_Cancel;//取消按钮颜色
    private int Color_Title;//标题颜色

    private int Color_Background_Wheel;//滚轮背景颜色
    private int Color_Background_Title;//标题背景颜色

    private int Size_Submit_Cancel;//确定取消按钮大小
    private int Size_Title;//标题文字大小
    private int Size_Content;//内容文字大小

    private int textColorOut; //分割线以外的文字颜色
    private int textColorCenter; //listview内容文字颜色
    private int dividerColor; //分割线的颜色
    private int backgroundId; //显示时的外部背景色颜色,默认是灰色
    // 条目间距倍数 默认1.6
    private float lineSpacingMultiplier = 1.6F;
    private boolean isDialog;//是否是对话框模式

    private boolean cancelable;//是否能取消
    private boolean linkage;//是否联动

    private boolean isCenterLabel ;//是否只显示中间的label

    private String label1;//单位
    private String label2;
    private String label3;

    private boolean cyclic1;//是否循环
    private boolean cyclic2;
    private boolean cyclic3;

    private Typeface font;//字体样式


    private WheelView.DividerType dividerType;//分隔线类型
    private List<String> items;
    private List<Boolean> checkedItems;
    private List<Boolean> savaCheckedItems = new ArrayList<>();


    private MultiChoicePicker(Builder builder) {
        super(builder.context);
        this.onMultiChoiceClickListener = builder.onMultiChoiceClickListener;
        this.Str_Submit = builder.Str_Submit;
        this.Str_Cancel = builder.Str_Cancel;
        this.Str_Title = builder.Str_Title;

        this.Color_Submit = builder.Color_Submit;
        this.Color_Cancel = builder.Color_Cancel;
        this.Color_Title = builder.Color_Title;
        this.Color_Background_Wheel = builder.Color_Background_Wheel;
        this.Color_Background_Title = builder.Color_Background_Title;

        this.Size_Submit_Cancel = builder.Size_Submit_Cancel;
        this.Size_Title = builder.Size_Title;
        this.Size_Content = builder.Size_Content;

        this.cyclic1 = builder.cyclic1;
        this.cyclic2 = builder.cyclic2;
        this.cyclic3 = builder.cyclic3;

        this.cancelable = builder.cancelable;
        this.linkage = builder.linkage;
        this.isCenterLabel = builder.isCenterLabel;

        this.label1 = builder.label1;
        this.label2 = builder.label2;
        this.label3 = builder.label3;

        this.font = builder.font;

        this.textColorCenter = builder.textColorCenter;
        this.textColorOut = builder.textColorOut;
        this.dividerColor = builder.dividerColor;
        this.lineSpacingMultiplier = builder.lineSpacingMultiplier;
        this.layoutRes = builder.layoutRes;
        this.isDialog = builder.isDialog;
        this.dividerType = builder.dividerType;
        this.backgroundId = builder.backgroundId;
        this.decorView = builder.decorView;
        this.items = builder.items;
        this.checkedItems = builder.checkedItems;

        for (int i = 0; i < builder.checkedItems.size(); i++) {
            savaCheckedItems.add(builder.checkedItems.get(i));
        }
        initView(builder.context);
    }

    //建造器
    public static class Builder {
        private int layoutRes = R.layout.pickerview_multichoice;
        private Context context;
        private OnMultiChoiceClickListener onMultiChoiceClickListener;

        private String Str_Submit;//确定按钮文字
        private String Str_Cancel;//取消按钮文字
        private String Str_Title;//标题文字

        private int Color_Submit;//确定按钮颜色
        private int Color_Cancel;//取消按钮颜色
        private int Color_Title;//标题颜色

        private int Color_Background_Wheel;//滚轮背景颜色
        private int Color_Background_Title;//标题背景颜色

        private int Size_Submit_Cancel = 17;//确定取消按钮大小
        private int Size_Title = 18;//标题文字大小
        private int Size_Content = 18;//内容文字大小

        private boolean cancelable = true;//是否能取消
        private boolean linkage = true;//是否联动
        private boolean isCenterLabel = true;//是否只显示中间的label

        private int textColorOut; //分割线以外的文字颜色
        private int textColorCenter; //分割线之间的文字颜色
        private int dividerColor; //分割线的颜色
        private int backgroundId; //显示时的外部背景色颜色,默认是灰色
        public ViewGroup decorView ;//显示pickerview的根View,默认是activity的根view
        // 条目间距倍数 默认1.6
        private float lineSpacingMultiplier = 1.6F;
        private boolean isDialog;//是否是对话框模式

        private String label1;
        private String label2;
        private String label3;

        private boolean cyclic1 = false;//是否循环，默认否
        private boolean cyclic2 = false;
        private boolean cyclic3 = false;

        private Typeface font;

        private int option1;//默认选中项
        private int option2;
        private int option3;

        private List<String> items;
        private List<Boolean> checkedItems;

        private WheelView.DividerType dividerType;//分隔线类型

        //Required
        public Builder(Context context) {
            this.context = context;
        }

        //Option

        public Builder setSubmitText(String Str_Cancel) {
            this.Str_Submit = Str_Cancel;
            return this;
        }

        public Builder setCancelText(String Str_Cancel) {
            this.Str_Cancel = Str_Cancel;
            return this;
        }

        public Builder setTitleText(String Str_Title) {
            this.Str_Title = Str_Title;
            return this;
        }

        public Builder isDialog(boolean isDialog) {
            this.isDialog = isDialog;
            return this;
        }

        public Builder setSubmitColor(int Color_Submit) {
            this.Color_Submit = Color_Submit;
            return this;
        }

        public Builder setCancelColor(int Color_Cancel) {
            this.Color_Cancel = Color_Cancel;
            return this;
        }

        /**
         * 显示时的外部背景色颜色,默认是灰色
         * @param backgroundId
         * @return
         */
        public Builder setBackgroundId(int backgroundId) {
            this.backgroundId = backgroundId;
            return this;
        }
        /**
         * 必须是viewgroup
         * 设置要将pickerview显示到的容器
         * @param decorView
         * @return
         */
        public Builder setDecorView(ViewGroup decorView) {
            this.decorView = decorView;
            return this;
        }

        public Builder setBgColor(int Color_Background_Wheel) {
            this.Color_Background_Wheel = Color_Background_Wheel;
            return this;
        }

        public Builder setTitleBgColor(int Color_Background_Title) {
            this.Color_Background_Title = Color_Background_Title;
            return this;
        }

        public Builder setTitleColor(int Color_Title) {
            this.Color_Title = Color_Title;
            return this;
        }

        public Builder setSubCalSize(int Size_Submit_Cancel) {
            this.Size_Submit_Cancel = Size_Submit_Cancel;
            return this;
        }

        public Builder setTitleSize(int Size_Title) {
            this.Size_Title = Size_Title;
            return this;
        }

        public Builder setContentTextSize(int Size_Content) {
            this.Size_Content = Size_Content;
            return this;
        }


        public Builder setOutSideCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        /**
         * 此方法已废弃
         * 不联动的情况下，请调用 setNPicker 方法。
         * */
        @Deprecated
        public Builder setLinkage(boolean linkage) {
            this.linkage = linkage;
            return this;
        }

        public Builder setLabels(String label1, String label2, String label3) {
            this.label1 = label1;
            this.label2 = label2;
            this.label3 = label3;
            return this;
        }

        /**
         * 设置间距倍数,但是只能在1.2-2.0f之间
         *
         * @param lineSpacingMultiplier
         */
        public Builder setLineSpacingMultiplier(float lineSpacingMultiplier) {
            this.lineSpacingMultiplier = lineSpacingMultiplier;
            return this;
        }

        /**
         * 设置分割线的颜色
         *
         * @param dividerColor
         */
        public Builder setDividerColor(int dividerColor) {
            this.dividerColor = dividerColor;
            return this;
        }

        /**
         * 设置分割线的类型
         *
         * @param dividerType
         */
       /* public Builder setDividerType(WheelView.DividerType dividerType) {
            this.dividerType = dividerType;
            return this;
        }*/

        /**
         * listview内容文字的颜色
         *
         * @param textColorCenter
         */
        public Builder setTextColorCenter(int textColorCenter) {
            this.textColorCenter = textColorCenter;
            return this;
        }

        /**
         * 设置分割线以外文字的颜色
         *
         * @param textColorOut
         */
        public Builder setTextColorOut(int textColorOut) {
            this.textColorOut = textColorOut;
            return this;
        }

        public Builder setTypeface(Typeface font) {
            this.font = font;
            return this;
        }

        public Builder setCyclic(boolean cyclic1, boolean cyclic2, boolean cyclic3) {
            this.cyclic1 = cyclic1;
            this.cyclic2 = cyclic2;
            this.cyclic3 = cyclic3;
            return this;
        }

        public Builder isCenterLabel(boolean isCenterLabel) {
            this.isCenterLabel = isCenterLabel;
            return this;
        }

        public Builder setMultiChoiceItems(List<String> items, List<Boolean> checkedItems,
                                           final OnMultiChoiceClickListener listener) {
            this.items = items;
            this.checkedItems = checkedItems;
            this.onMultiChoiceClickListener = listener;
            return this;
        }
        public MultiChoicePicker build() {
            return new MultiChoicePicker(this);
        }
    }

    public interface OnMultiChoiceClickListener {
        void onClick(List<Boolean> clecteItems);
    }

    @Override
    public void onClick(View v) {
        String tag = (String) v.getTag();
        if (tag.equals(TAG_SUBMIT)) {
            returnData();
        }
        dismiss();

    }

    private void returnData() {
        if (onMultiChoiceClickListener != null) {
            onMultiChoiceClickListener.onClick(savaCheckedItems);
        }
    }

    private void initView(Context context) {
        setDialogOutSideCancelable(cancelable);
        initViews(backgroundId);
        init();
        initEvents();


        LayoutInflater.from(context).inflate(layoutRes, contentContainer);

        //顶部标题
        tvTitle = (TextView) findViewById(com.bigkoo.pickerview.R.id.tvTitle);
        rv_top_bar = (RelativeLayout)findViewById(com.bigkoo.pickerview.R.id.rv_topbar);

        //确定和取消按钮
        btnSubmit = (Button) findViewById(com.bigkoo.pickerview.R.id.btnSubmit);
        btnCancel = (Button) findViewById(com.bigkoo.pickerview.R.id.btnCancel);

        btnSubmit.setTag(TAG_SUBMIT);
        btnCancel.setTag(TAG_CANCEL);
        btnSubmit.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        //设置文字
        btnSubmit.setText(TextUtils.isEmpty(Str_Submit) ? context.getResources().getString(com.bigkoo.pickerview.R.string.pickerview_submit) : Str_Submit);
        btnCancel.setText(TextUtils.isEmpty(Str_Cancel) ? context.getResources().getString(com.bigkoo.pickerview.R.string.pickerview_cancel) : Str_Cancel);
        tvTitle.setText(TextUtils.isEmpty(Str_Title) ? "" : Str_Title);//默认为空

        //设置color
        btnSubmit.setTextColor(Color_Submit == 0 ? pickerview_timebtn_nor : Color_Submit);
        btnCancel.setTextColor(Color_Cancel == 0 ? pickerview_timebtn_nor : Color_Cancel);
        tvTitle.setTextColor(Color_Title == 0 ? pickerview_topbar_title : Color_Title);
        rv_top_bar.setBackgroundColor(Color_Background_Title == 0 ? pickerview_bg_topbar : Color_Background_Title);

        //设置文字大小
        btnSubmit.setTextSize(Size_Submit_Cancel);
        btnCancel.setTextSize(Size_Submit_Cancel);
        tvTitle.setTextSize(Size_Title);
        tvTitle.setText(Str_Title);

        // ----ListView布局
        final ListView listView = (ListView) findViewById(R.id.list_view);
        mListView = listView;
        //listView的多选模式
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        final Adapter adapter = new Adapter(items, checkedItems,context, listView);
        adapter.setTextSize(Size_Content);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                savaCheckedItems.set(position,listView.isItemChecked(position));
                adapter.setList(savaCheckedItems);
                adapter.notifyDataSetChanged();
            }
        });

        setOutSideCancelable(cancelable);
        if (tvTitle!= null){
            tvTitle.setText(Str_Title);
        }


    }
}
