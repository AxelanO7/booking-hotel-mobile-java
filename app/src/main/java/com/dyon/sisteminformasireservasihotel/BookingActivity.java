package com.dyon.sisteminformasireservasihotel;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class BookingActivity extends AppCompatActivity {
    ListView listViewBooking;
    FloatingActionButton fabBooking;
    DatabaseHelper dbcenter;
    BookingAdapter adapter;
    Cursor cursor;
    LayoutInflater inflater;
    List<Booking> itemListBooking = new ArrayList<>();
    Spinner tjenis_kamar;
    EditText tid, tnama, tnomor_ktp, tkode_kamar, ttanggal_masuk, ttanggal_keluar, tdurasi, tharga, tsubtotal;
    String vid, vnama, vjenis_kamar, vnomor_ktp, vkode_kamar, vtanggal_masuk, vtanggal_keluar, vdurasi, vharga, vsubtotal, vaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        listViewBooking = findViewById(R.id.listViewBooking);
        fabBooking = findViewById(R.id.fabBooking);

        dbcenter = new DatabaseHelper(this);

        listViewBooking.setAdapter(new BookingAdapter(this, itemListBooking));

        listViewBooking.setOnItemClickListener((adapterView, view, position, l) -> {
            final String idx = itemListBooking.get(position).getId();
            final CharSequence[] pilihanAksi = {"Hapus", "Ubah"};
            AlertDialog.Builder dialog = new AlertDialog.Builder(BookingActivity.this);
            dialog.setItems(pilihanAksi, (dialog1, which) -> {
                switch (which){
                    case 0:
                        //jika dipilih hapus
                        hapusData(idx);
                        break;

                    case 1:
                        //jika memilih edit/ubah
                        ubahData(idx);
                        break;
                }

            }).show();
        });

        fabBooking.setOnClickListener(view -> {
            dialogForm("","","","", "", "", "", "", "", "", "Tambah");
        });

        refreshData();
    }
    public void dialogForm(String id, String nama, String jenis_kamar, String nomor_ktp, String kode_kamar, String tanggal_masuk, String tanggal_keluar, String durasi, String harga, String subtotal, String button){
        AlertDialog.Builder dialogForm = new AlertDialog.Builder(BookingActivity.this);
        inflater = getLayoutInflater();
        View viewDialog = inflater.inflate(R.layout.form_booking, null);
        dialogForm.setView(viewDialog);
        dialogForm.setCancelable(true);
        dialogForm.setTitle("Form Booking");

        tid = viewDialog.findViewById(R.id.inId);
        tnama = viewDialog.findViewById(R.id.inNama);
        tjenis_kamar = viewDialog.findViewById(R.id.inJenisKamar);
        tnomor_ktp = viewDialog.findViewById(R.id.inKode);
        tkode_kamar = viewDialog.findViewById(R.id.inKodeKamar);
        ttanggal_masuk = viewDialog.findViewById(R.id.inTanggalMasuk);
        ttanggal_keluar = viewDialog.findViewById(R.id.inTanggalKeluar);
        tdurasi = viewDialog.findViewById(R.id.inDurasi);
        tharga = viewDialog.findViewById(R.id.inHarga);
        tsubtotal = viewDialog.findViewById(R.id.inSubtotal);

        if (id.isEmpty()){
            tid.setText(null);
            tnama.setText(null);
//            tjenis_kamar.setText(null);
            tnomor_ktp.setText(null);
            tkode_kamar.setText(null);
            ttanggal_masuk.setText(null);
            ttanggal_keluar.setText(null);
            tdurasi.setText(null);
            tharga.setText(null);
            tsubtotal.setText(null);
        }else{
            tid.setText(id);
            tnama.setText(nama);
//            tjenis_kamar.setText(jenis_kamar);
            tnomor_ktp.setText(nomor_ktp);
            tkode_kamar.setText(kode_kamar);
            ttanggal_masuk.setText(tanggal_masuk);
            ttanggal_keluar.setText(tanggal_keluar);
            tdurasi.setText(durasi);
            tharga.setText(harga);
            tsubtotal.setText(subtotal);
        }

        dialogForm.setPositiveButton(button, (dialog, which) -> {
            vid = tid.getText().toString();
            vnama = tnama.getText().toString();
            vjenis_kamar = tjenis_kamar.getSelectedItem().toString();
            vnomor_ktp = tnomor_ktp.getText().toString();
            vkode_kamar = tkode_kamar.getText().toString();
            vtanggal_masuk = ttanggal_masuk.getText().toString();
            vtanggal_keluar = ttanggal_keluar.getText().toString();
            vdurasi = tdurasi.getText().toString();
            vharga = tharga.getText().toString();
            vsubtotal = tsubtotal.getText().toString();
            vaction = button.toLowerCase();

            simpan();
            dialog.dismiss();
        });
        dialogForm.setNegativeButton("Batal", (dialog, which) -> {
            dialog.dismiss();
            tid.setText(null);
            tnama.setText(null);
//            tjenis_kamar.setText(null);
            tnomor_ktp.setText(null);
            tkode_kamar.setText(null);
            ttanggal_masuk.setText(null);
            ttanggal_keluar.setText(null);
            tdurasi.setText(null);
            tharga.setText(null);
            tsubtotal.setText(null);
        });
        dialogForm.show();
    }

    public void ubahData(String id){
        SQLiteDatabase db = dbcenter.getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM tb_booking WHERE id = '"+id+"'", null);
        cursor.moveToFirst();
        if (cursor.getCount()>0){
            cursor.moveToPosition(0);
            dialogForm(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), "Ubah");
        }
    }
    public void simpan(){
        SQLiteDatabase db = dbcenter.getWritableDatabase();
        ContentValues values = new ContentValues();
//        if (vaction.equals("tambah")){
//            values.put("id", vid);
//        }
//        values.put("id", vid);
        values.put("nama", vnama);
        values.put("jenis_kamar", vjenis_kamar);
        values.put("nomor_ktp", vnomor_ktp);
        values.put("kode_kamar", vkode_kamar);
        values.put("tanggal_masuk", vtanggal_masuk);
        values.put("tanggal_keluar", vtanggal_keluar);
        values.put("durasi", vdurasi);
        values.put("harga", vharga);
        values.put("subtotal", vsubtotal);
        if (vaction.equals("tambah")){
            db.insert("tb_booking", null, values);
        }else {
            db.update("tb_booking", values, "id = '"+vid+"'", null);
        }
        Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), BookingActivity.class);
        startActivity(intent);
//        refreshData();
    }
    public void hapusData(String id){
        SQLiteDatabase db = dbcenter.getWritableDatabase();
        db.delete("tb_booking", "id = '"+id+"'", null);
        Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), BookingActivity.class);
        startActivity(intent);
//        refreshData();
    }

    private void refreshData() {
        itemListBooking.clear();
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM tb_booking", null);
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            itemListBooking.add(new Booking(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),cursor.getString(7), cursor.getString(8), cursor.getString(9)));
        }
    }

    @Override
    public void onBackPressed() {
        // do what you want to do when the "back" button is pressed.
        startActivity(new Intent(BookingActivity.this, MainActivity.class));
        finish();
    }
}
