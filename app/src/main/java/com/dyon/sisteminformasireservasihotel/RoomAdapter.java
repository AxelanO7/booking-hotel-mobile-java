package com.dyon.sisteminformasireservasihotel;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class RoomAdapter extends BaseAdapter {
    Activity activity;
    List<Room> items;
    private LayoutInflater inflater;

    public RoomAdapter(Activity activity, List<Room> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) convertView = inflater.inflate(R.layout.list_room, null);

        TextView nama = convertView.findViewById(R.id.namaRoom);
        TextView jenis_kamar = convertView.findViewById(R.id.jenisRoom);
        TextView harga = convertView.findViewById(R.id.hargaRoom);

        Room data = items.get(position);
        nama.setText(data.getNama());
        jenis_kamar.setText(data.getJenis());
        harga.setText(data.getHarga());
        return convertView;
    }

}
