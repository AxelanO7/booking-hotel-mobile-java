package com.dyon.sisteminformasireservasihotel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class GuestActivity extends AppCompatActivity {
    ListView listViewGuest;
    DatabaseHelper dbcenter;
    GuestAdapter adapter;
    FloatingActionButton fabGuest;
    Cursor cursor;
    LayoutInflater inflater;
    List<Guest> itemListGuest = new ArrayList<>();
    RadioGroup rgjenis_kelamin;
    RadioButton rblaki, rbperempuan;
    EditText tid, tnama, tnomor_ktp, tnomor_hp, talamat;
    String vid, vnama, vjenis_kelamin, vnomor_ktp, vnomor_hp, valamat, vaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);

        listViewGuest = findViewById(R.id.listViewGuest);
        fabGuest = findViewById(R.id.fabGuest);

        dbcenter = new DatabaseHelper(this);


        listViewGuest.setAdapter(new GuestAdapter(this, itemListGuest));
//        listViewGuest.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemListGuest));

        listViewGuest.setOnItemClickListener((adapterView, view, position, l) -> {
            final String idx = itemListGuest.get(position).getId();
            final CharSequence[] pilihanAksi = {"Hapus", "Ubah"};
            AlertDialog.Builder dialog = new AlertDialog.Builder(GuestActivity.this);
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
            });
            dialog.create();
            dialog.show();
        });

        fabGuest.setOnClickListener(view -> {
            //aksi ketika tombol tambah ditekan
            dialogForm("","","","","","","Tambah");
        });

        refreshData();
    }

    public void dialogForm(String id, String namax, String nomor_ktpx,  String alamatx, String jenis_kelaminx, String nohpx, String actionButton){
        AlertDialog.Builder dialogForm = new AlertDialog.Builder(GuestActivity.this);
        inflater = getLayoutInflater();
        View viewDialog = inflater.inflate(R.layout.form_guest, null);
        dialogForm.setView(viewDialog);
        dialogForm.setCancelable(true);
        dialogForm.setTitle("Form Tamu Hotel");

        tid = viewDialog.findViewById(R.id.inId);
        tnama = viewDialog.findViewById(R.id.inNama);

        rblaki = viewDialog.findViewById(R.id.rbLaki);
        rbperempuan = viewDialog.findViewById(R.id.rbPerempuan);
        tnomor_ktp = viewDialog.findViewById(R.id.inKode);
        tnomor_hp = viewDialog.findViewById(R.id.inHargaKamar);
        talamat = viewDialog.findViewById(R.id.inJenisKamarHotel);

        if (id.isEmpty()){
            tid.setVisibility(View.GONE);
            tnama.setText(namax);
            tnomor_ktp.setText(nomor_ktpx);
            tnomor_hp.setText(nohpx);
            talamat.setText(alamatx);
        }else{
            tid.setText(id);
            tnama.setText(namax);
            if (jenis_kelaminx.equals("Laki-laki"))
                rblaki.setChecked(true);
            else
                rbperempuan.setChecked(true);            tnomor_ktp.setText(nomor_ktpx);
            tnomor_hp.setText(nohpx);
            talamat.setText(alamatx);
        }

        dialogForm.setPositiveButton(actionButton, (dialog, which) -> {
            vid = tid.getText().toString();
            vnama = tnama.getText().toString();
            vjenis_kelamin = rblaki.isChecked() ? "Laki-laki" : "Perempuan";
            vnomor_ktp = tnomor_ktp.getText().toString();
            vnomor_hp = tnomor_hp.getText().toString();
            valamat = talamat.getText().toString();
            vaction = actionButton.toLowerCase();

            simpan();
            dialog.dismiss();
        });
        dialogForm.setNegativeButton("Batal", (dialog, which) -> {
            dialog.dismiss();
            tid.setText(null);
            tnama.setText(null);
            tnomor_ktp.setText(null);
            tnomor_hp.setText(null);
            talamat.setText(null);
        });
        dialogForm.show();
    }

    public void ubahData(String id){
        SQLiteDatabase db = dbcenter.getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM tb_guest WHERE id = '"+id+"'", null);
        cursor.moveToFirst();
        if (cursor.getCount()>0){
            cursor.moveToPosition(0);
            dialogForm(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), "Ubah");
        }
    }
    public void simpan(){
        SQLiteDatabase db = dbcenter.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nama", vnama);
        values.put("jenis_kelamin", vjenis_kelamin);
        values.put("nomor_ktp", vnomor_ktp);
        values.put("no_hp", vnomor_hp);
        values.put("alamat", valamat);
        if (vaction.equals("tambah")){
            db.insert("tb_guest", null, values);
        }else {
            db.update("tb_guest", values, "id = '"+vid+"'", null);
        }
        Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), GuestActivity.class);
        startActivity(intent);
//        refreshData();
    }
    public void hapusData(String id){
        SQLiteDatabase db = dbcenter.getWritableDatabase();
        db.delete("tb_guest", "id = '"+id+"'", null);
        Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), GuestActivity.class);
        startActivity(intent);
//        refreshData();
    }

    private void refreshData() {
        itemListGuest.clear();
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM tb_guest", null);
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            itemListGuest.add(new Guest(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5)));
        }
    }

    @Override
    public void onBackPressed() {
        // do what you want to do when the "back" button is pressed.
        startActivity(new Intent(GuestActivity.this, MainActivity.class));
        finish();
    }
}
