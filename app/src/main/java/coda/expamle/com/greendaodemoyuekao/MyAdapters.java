package coda.expamle.com.greendaodemoyuekao;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ASUS-PC on 2017/7/4.
 */

public class MyAdapters extends BaseAdapter {
    private Context context;
    private List<Bean> list;
    private ViewHolder holder;

    public MyAdapters(Context context, List<Bean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item, null);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.banshe = (TextView) convertView.findViewById(R.id.price);
             convertView.setTag(holder);

        }else{
       holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(list.get(position).getName());
        holder.banshe.setText(list.get(position).getBanshe());
        return convertView;
    }
    static  class  ViewHolder{
        TextView name;
        TextView banshe;


    }
}
