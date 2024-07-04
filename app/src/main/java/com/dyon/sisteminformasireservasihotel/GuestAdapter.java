package com.dyon.sisteminformasireservasihotel;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class GuestAdapter extends BaseAdapter {
    Activity activity;
    List<Guest> items;
    private LayoutInflater inflater;

    public GuestAdapter(Activity activity, List<Guest> items) {
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

        if (convertView == null) convertView = inflater.inflate(R.layout.list_guest, null);

        TextView nama = convertView.findViewById(R.id.namaRoom);
        TextView alamat = convertView.findViewById(R.id.alamatRoom);
        TextView no_telp = convertView.findViewById(R.id.hargaRoom);

        Guest data = items.get(position);

        nama.setText(data.getNama());
        alamat.setText(data.getAlamat());
        no_telp.setText(data.getNohp());

        return convertView;
    }
}
