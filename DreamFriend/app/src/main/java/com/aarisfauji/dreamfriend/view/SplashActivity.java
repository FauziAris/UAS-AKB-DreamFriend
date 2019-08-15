package com.aarisfauji.dreamfriend.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.aarisfauji.dreamfriend.presenter.IPresenter;
import com.aarisfauji.dreamfriend.presenter.SignInPresenter;
/**
 * NIM                : 10116563
 * Nama               : A Aris Fauji
 * Kelas              : AKB-13 (IF-13)
 * Tanggal Pengerjaan : 13 Agustus 2019
 */
public class SplashActivity extends AppCompatActivity {

    IPresenter.ISignInPresenter loginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginPresenter = new SignInPresenter();

        if (loginPresenter.isSignIn(getApplicationContext())){
//       if(1==2){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
//        // langsung pindah ke MainActivity atau activity lain
//        // begitu memasuki splash screen ini
//        Intent intent = new Intent(this, LoginActivity.class);
//        startActivity(intent);
//        finish();
    }
}
