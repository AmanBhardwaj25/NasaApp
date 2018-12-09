package com.example.amanbhardwaj.nasaapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomeListView extends ArrayAdapter<String> {


    private String[] id;
    private String[] main1;
    private String[] desc;
    private Integer[] imgID;
    private Activity context;

    public CustomeListView(Activity context, String[] id, String[] main1, String[] desc, Integer[] imgID) {


        super(context, R.layout.listview_layout, id);


        this.context = context;

        this.id = id;

        this.main1 = main1;

        this.desc = desc;

        this.imgID = imgID;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View r = convertView;
        ViewHolder viewHolder;
        if(r==null)
        {
            LayoutInflater layoutInflater = context.getLayoutInflater();

            r = layoutInflater.inflate(R.layout.listview_layout,null,true);

            viewHolder = new ViewHolder(r);

            r.setTag(viewHolder);
        }

        else
        {
            viewHolder = (ViewHolder) r.getTag();
        }

        viewHolder.img.setImageResource(imgID[position]);
        viewHolder.name.setText(id[position] + "\n\n" + main1[position] + "\n\n" + desc[position]);
        // viewHolder.danger.setText(main1[position]);
        // viewHolder.url.setText(desc[position]);

        return r;
    }

    class ViewHolder
    {
        TextView name;
        TextView danger;
        TextView url;
        ImageView img;

        ViewHolder(View v)
        {
            name = (TextView) v.findViewById(R.id.textView1);
            danger = (TextView) v.findViewById(R.id.textView2);
            url = (TextView) v.findViewById(R.id.textView3);

            img = (ImageView) v.findViewById(R.id.imgvw);
        }
    }
}
