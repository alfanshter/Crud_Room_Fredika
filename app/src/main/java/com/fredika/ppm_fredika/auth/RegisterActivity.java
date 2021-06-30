package com.fredika.ppm_fredika.auth;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.fredika.ppm_fredika.R;
import com.fredika.ppm_fredika.database.AppDatabase;
import com.fredika.ppm_fredika.database.dao.UserDao;
import com.fredika.ppm_fredika.database.entity.UserEntity;
import com.fredika.ppm_fredika.databinding.ActivityRegisterBinding;
import com.google.android.material.snackbar.Snackbar;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        binding.getLifecycleOwner();
        database = AppDatabase.getInstance(this);
        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = binding.edtNama.getText().toString().trim();
                String email = binding.edtEmail.getText().toString().trim();
                String password = binding.edtPassword.getText().toString().trim();

                if (!nama.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
                    UserEntity userEntity = database.userDao().cekuser(email);
                    if (userEntity==null){
                        database.userDao().registerUser(nama,email,password);
                        finish();
                    }else {
                        Snackbar.make(v, "email sudah terdaftar", Snackbar.LENGTH_LONG).show();
                    }
                } else {
                    Snackbar.make(v, "Isi semua kolom ", Snackbar.LENGTH_LONG).show();
                }
            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}