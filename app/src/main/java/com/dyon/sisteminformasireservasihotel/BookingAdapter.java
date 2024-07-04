package com.dyon.sisteminformasireservasihotel;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class BookingAdapter extends BaseAdapter {
    Activity activity;
    List<Booking> items;
    private LayoutInflater inflater;

    public BookingAdapter(Activity activity, List<Booking> items) {
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

        if (convertView == null) convertView = inflater.inflate(R.layout.list_booking, null);

        TextView nama = convertView.findViewById(R.id.namaRoom);
        TextView durasi = convertView.findViewById(R.id.durasi);
        TextView jenis_kamar = convertView.findViewById(R.id.jenis_kamar);

        Booking data = items.get(position);


        nama.setText(data.getNama());
        durasi.setText(data.getDurasi());
        jenis_kamar.setText(data.getJenis_kamar());

        return convertView;
    }
}
