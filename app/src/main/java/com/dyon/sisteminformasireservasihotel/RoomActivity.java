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
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class RoomActivity extends AppCompatActivity {
    ListView listViewRoom;
    DatabaseHelper dbcenter;
    RoomAdapter adapter;
    FloatingActionButton fabRoom;
    Cursor cursor;
    LayoutInflater inflater;
    Spinner spinnerRoom;
    List<Room> itemListRoom = new ArrayList<>();
    EditText tid, tnama, tkode, tharga, taction;
    String vid, vnama, vkode, vjenis, vharga, vaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        listViewRoom = findViewById(R.id.listViewRoom);
        fabRoom = findViewById(R.id.fabRoom);
        dbcenter = new DatabaseHelper(this);
        listViewRoom.setAdapter(new RoomAdapter(this, itemListRoom));
        listViewRoom.setOnItemClickListener((adapterView, view, position, l) -> {
            final String idx = itemListRoom.get(position).getId();
            final CharSequence[] pilihanAksi = {"Hapus", "Ubah"};
            android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(RoomActivity.this);
            dialog.setItems(pilihanAksi, (dialog1, which) -> {
                switch (which){
                    case 0:
                        //jika dipilih hapus
                        hapusData(idx);
                        break;
                    case 1:
                        //jika dipilih ubah
                        ubahData(idx);
                        break;
                }
            });
            dialog.create();
            dialog.show();
        });
        fabRoom.setOnClickListener(view -> {
            dialogForm("", "", "", "", "", "Tambah");
        });

        refreshData();
    }

    public void dialogForm(String id, String namax, String kode, String jenis, String harga, String action){
        AlertDialog.Builder dialogForm = new AlertDialog.Builder(RoomActivity.this);
        inflater = getLayoutInflater();
        View viewDialog = inflater.inflate(R.layout.form_room, null);
        dialogForm.setView(viewDialog);
        dialogForm.setCancelable(true);
        dialogForm.setTitle("Form Kamar Hotel");
        spinnerRoom = viewDialog.findViewById(R.id.spinnerRoom);

        tid = viewDialog.findViewById(R.id.inId);
        tnama = viewDialog.findViewById(R.id.inNama);
        tkode = viewDialog.findViewById(R.id.inKode);
//        tjenis = viewDialog.findViewById(R.id.inJenisKamarHotel);
        tharga = viewDialog.findViewById(R.id.inHargaKamar);

        if (id.isEmpty()){
            tid.setVisibility(View.GONE);
            tnama.setText(null);
            tkode.setText(null);
//            tjenis.setText(null);
            tharga.setText(null);
        }else{
            tid.setText(id);
            tnama.setText(namax);
            tkode.setText(kode);
//            spinnerRoom.setSelection(Integer.parseInt(jenis) - 1);
//            tjenis.setText(jenis);
            tharga.setText(harga);
        }

        dialogForm.setPositiveButton(action, (dialog, which) -> {
            vid = tid.getText().toString();
            vnama = tnama.getText().toString();
            vkode = tkode.getText().toString();
            vjenis = spinnerRoom.getSelectedItem().toString();
//            vjenis = tjenis.getText().toString();
            vharga = tharga.getText().toString();
            vaction = action.toLowerCase();

            simpan();
            dialog.dismiss();
        });
        dialogForm.setNegativeButton("Batal", (dialog, which) -> {
            dialog.dismiss();
            tid.setText(null);
            tnama.setText(null);
            tkode.setText(null);
//            tjenis.setText(null);
            tharga.setText(null);
        });
        dialogForm.show();
    }

    private void ubahData(String idx){
        SQLiteDatabase db = dbcenter.getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM tb_room WHERE id = '"+idx+"'", null);
        cursor.moveToFirst();
        if (cursor.getCount()>0){
            cursor.moveToPosition(0);
            dialogForm(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), "Ubah");
        }
    }

    private void hapusData(String idx){
        SQLiteDatabase db = dbcenter.getWritableDatabase();
        db.execSQL("DELETE FROM tb_room WHERE id = '"+idx+"'");
        Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), RoomActivity.class);
        startActivity(intent);
//        refreshData();
    }

    public void simpan(){
        SQLiteDatabase db = dbcenter.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nama", vnama);
        values.put("kode_kamar", vkode);
        values.put("jenis_kamar", vjenis);
        values.put("harga", vharga);
        if (vaction.equals("tambah")){
            db.insert("tb_room", null, values);
        }else {
            db.update("tb_room", values, "id = '"+vid+"'", null);
        }
        Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), RoomActivity.class);
        startActivity(intent);
//        refreshData();
    }

    private void refreshData() {
        itemListRoom.clear();
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM tb_room", null);
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            itemListRoom.add(new Room(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4)));
        }
    }

    @Override
    public void onBackPressed() {
        // do what you want to do when the "back" button is pressed.
        startActivity(new Intent(RoomActivity.this, MainActivity.class));
        finish();
    }
}
