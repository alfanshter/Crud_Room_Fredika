package com.fredika.ppm_fredika;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fredika.ppm_fredika.adapter.BarangAdapter;
import com.fredika.ppm_fredika.auth.LoginActivity;
import com.fredika.ppm_fredika.database.AppDatabase;
import com.fredika.ppm_fredika.database.entity.LaptopEntity;
import com.fredika.ppm_fredika.databinding.ActivityMainBinding;
import com.fredika.ppm_fredika.session.Preferences;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private AppDatabase database;
    private BarangAdapter barangAdapter;
    private List<LaptopEntity> list = new ArrayList<>();
    private AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.getLifecycleOwner();


        database = AppDatabase.getInstance(this);
        list.clear();
        list.addAll(database.laptopDao().getall(Preferences.getIsNama(getBaseContext())));
        Log.d("MainActivity", list.toString());
        barangAdapter = new BarangAdapter(this, list);
        barangAdapter.setDialog(new BarangAdapter.Dialog() {
            @Override
            public void onClick(int position) {
                final CharSequence[] dialogItem = {String.format(getString(R.string.hapus)), "Update"};
                dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @SuppressLint("ShowToast")
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i) {
                            case 0:
                                LaptopEntity laptopEntity = list.get(position);
                                database.laptopDao().delete(laptopEntity);
                                onStart();
                                break;

                            case 1:
                                Intent intent = new Intent(MainActivity.this, InsertActivity.class);
                                intent.putExtra("id_laptop", String.valueOf(list.get(position).getId_laptop()));
                                startActivity(intent);
                                break;
                        }
                    }
                });
                dialog.show();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        binding.rvListitem.setLayoutManager(layoutManager);
        binding.rvListitem.setAdapter(barangAdapter);


        binding.btnAdditem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InsertActivity.class);
                startActivity(intent);
            }
        });

        binding.btnProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfilActivity.class);
                startActivity(intent);
                finish();

            }
        });

        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Preferences.setIsLogin(getBaseContext(),false);
                Preferences.setIsNama(getBaseContext(),"");
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        list.clear();
        list.addAll(database.laptopDao().getall(Preferences.getIsNama(getBaseContext())));
        barangAdapter.notifyDataSetChanged();
    }
}