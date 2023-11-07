package com.example.aminandroid.Classes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.lang.UProperty;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aminandroid.R;

public class ListAdapter extends BaseAdapter {
    Context context;
    Game[] gamelist;
    LayoutInflater layoutInflater;
    public ListAdapter(Context context,Game[] gamelist){
        this.context = context;
        this.gamelist = gamelist;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return gamelist.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.activity_list_view,null);
        TextView textView = view.findViewById(R.id.productTV);
        ImageView image = view.findViewById(R.id.imImage);
        textView.setText(gamelist[i].toString());
        byte[] im = gamelist[i].getImageByte();
        Bitmap bm = BitmapFactory.decodeByteArray(im, 0 ,im.length);
        image.setImageBitmap(bm);
        return view;
    }
}
