package com.example.stone.multichoicepicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.example.stone.R;

import java.util.List;

/**
 * @author Stone
 * @version V1.0
 * @description:
 * @date 2017/6/14 0014 17:52
 * @email redsstone@163.com
 */


public class Adapter extends BaseAdapter {

    private List<String> items;
    private List<Boolean> clecteItems;
    private Context context;
    private ListView mListView;
    private float size = 18;

    public Adapter(List<String> items, List<Boolean> clecteItems, Context context, ListView mListView) {

        if (items.size() != clecteItems.size()) {
            throw  new RuntimeException("items的长度和clecteItems的长度应该一致");
        }
        this.items = items;
        this.clecteItems = clecteItems;
        this.context = context;
        this.mListView = mListView;
    }

    public void setTextSize(float size) {
        this.size = size;
    }
    public void setList(List<Boolean> clecteItems) {
        this.clecteItems = clecteItems;
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public String getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_multichoice, null);
            viewHolder.items = (TextView) convertView.findViewById(R.id.item_message);
            viewHolder.imgClecte = (ImageView) convertView.findViewById(R.id.img_select);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.items.setText(items.get(position));
        viewHolder.items.setTextSize(size);
        if (clecteItems.get(position) == true) {
            //viewHolder.imgClecte.setImageResource(R.drawable.selected);
            mListView.setItemChecked(position, true);
        } else {
            //viewHolder.imgClecte.setImageResource(R.drawable.unselect);
            mListView.setItemChecked(position, false);
        }
        updateBackground(position, viewHolder.imgClecte);
        return convertView;
    }


    public void updateBackground(int position, View view) {
        int backgroundId;
        if (mListView.isItemChecked(position)) {
            backgroundId = R.drawable.selected;
        } else {
            backgroundId = R.drawable.unselect;
        }

       view.setBackgroundResource(backgroundId);
    }
    class ViewHolder {
        TextView items;
        ImageView imgClecte;
    }
}
