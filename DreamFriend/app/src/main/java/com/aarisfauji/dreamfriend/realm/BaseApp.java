package com.aarisfauji.dreamfriend.realm;

import android.app.Application;


import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * NIM                : 10116563
 * Nama               : A Aris Fauji
 * Kelas              : AKB-13 (IF-13)
 * Tanggal Pengerjaan : 13 Agustus 2019
 */
public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("dreamfriend.db")
                .schemaVersion(0)
                .build();
        Realm.setDefaultConfiguration(configuration);
    }

}
