package com.fredika.ppm_fredika.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fredika.ppm_fredika.MainActivity;
import com.fredika.ppm_fredika.R;
import com.fredika.ppm_fredika.database.AppDatabase;
import com.fredika.ppm_fredika.database.dao.UserDao;
import com.fredika.ppm_fredika.database.entity.UserEntity;
import com.fredika.ppm_fredika.databinding.ActivityLoginBinding;
import com.fredika.ppm_fredika.session.Preferences;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    private AppDatabase database;
    private List<UserEntity> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        binding.getLifecycleOwner();
        database = AppDatabase.getInstance(this);

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.edtEmail.getText().toString().trim();
                String password = binding.edtPassword.getText().toString().trim();

                if (!email.isEmpty() && !password.isEmpty()){
                    final UserDao userDao = database.userDao();
                    UserEntity userEntity =  userDao.login(email,password);
                    if (userEntity==null){
                        Snackbar.make(v, "Login Gagal ", Snackbar.LENGTH_LONG).show();
                    }else {
                        Preferences.setIsLogin(getBaseContext(),true);
                        Preferences.setIsNama(getBaseContext(),userEntity.getEmail());
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }else {
                    Snackbar.make(v, "Isi semua kolom ", Snackbar.LENGTH_LONG).show();
                }
            }
        });
        binding.btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Preferences.getIsLogin(getBaseContext())){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}