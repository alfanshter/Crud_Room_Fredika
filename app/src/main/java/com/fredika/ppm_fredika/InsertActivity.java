package com.fredika.ppm_fredika;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.fredika.ppm_fredika.database.AppDatabase;
import com.fredika.ppm_fredika.database.entity.LaptopEntity;
import com.fredika.ppm_fredika.databinding.ActivityInsertBinding;
import com.fredika.ppm_fredika.session.Preferences;
import com.google.android.material.snackbar.Snackbar;

public class InsertActivity extends AppCompatActivity {
    private String id_laptop;
    private Boolean isedit = false;
    ActivityInsertBinding binding;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_insert);
        binding.getLifecycleOwner();
        database = AppDatabase.getInstance(this);
        Intent intent = getIntent();
        id_laptop = intent.getStringExtra("id_laptop");

        if (id_laptop != null) {
            if (Integer.parseInt(id_laptop) > 0) {
                isedit = true;
                LaptopEntity laptopEntity = database.laptopDao().get(Integer.parseInt(id_laptop));
                binding.edtNamalaptop.setText(laptopEntity.getNama_barang());
                binding.edtMereklaptop.setText(laptopEntity.getMerek_barang());
                binding.edtHarga.setText(laptopEntity.getHarga_barang().toString());
                binding.edtJumlah.setText(laptopEntity.getJumlah_barang().toString());
            } else {
                isedit = false;
            }

        }


        binding.btnTambahlaptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namalaptop = binding.edtNamalaptop.getText().toString().trim();
                String merklaptop = binding.edtMereklaptop.getText().toString().trim();
                Integer harga = Integer.parseInt(binding.edtHarga.getText().toString().trim());
                Integer jumlah = Integer.parseInt(binding.edtJumlah.getText().toString().trim());
                String email = Preferences.getIsNama(getBaseContext());
                if (!namalaptop.isEmpty() && !merklaptop.isEmpty() && !harga.toString().isEmpty() &&
                        !jumlah.toString().isEmpty()) {

                    if (isedit) {
                        database.laptopDao().update(Integer.parseInt(id_laptop),namalaptop,merklaptop,jumlah,harga);
                        finish();
                    }else {
                        database.laptopDao().insertlaptop(namalaptop, merklaptop, harga, jumlah, email);
                        finish();
                    }
                } else {
                    Snackbar.make(v, "Isi semua kolom ", Snackbar.LENGTH_LONG).show();

                }
            }
        });
    }

}