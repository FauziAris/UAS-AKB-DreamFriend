package com.aarisfauji.dreamfriend.realm;

import android.util.Log;

import com.aarisfauji.dreamfriend.model.TemanModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * NIM                : 10116563
 * Nama               : A Aris Fauji
 * Kelas              : AKB-13 (IF-13)
 * Tanggal Pengerjaan : 13 Agustus 2019
 */
public class RealmHelper {

    Realm realm;

    public  RealmHelper(Realm realm){
        this.realm = realm;
    }

    // untuk menyimpan data
    public void save(final TemanModel temanModel){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm != null){
                    Log.e("Created", "Database was created");
                    Number currentIdNum = realm.where(TemanModel.class).max("id");
                    int nextId;
                    if (currentIdNum == null){
                        nextId = 1;
                    }else {
                        nextId = currentIdNum.intValue() + 1;
                    }
                    temanModel.setId(nextId);
                    TemanModel model = realm.copyToRealm(temanModel);
                }else{
                    Log.e("ppppp", "execute: Database not Exist");
                }
            }
        });
    }

    // untuk memanggil semua data
    public List<TemanModel> getAllTeman(){
        RealmResults<TemanModel> results = realm.where(TemanModel.class).findAll();
        return results;
    }

    // untuk meng-update data
    public void update(final Integer id, final TemanModel temanModel){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                TemanModel model = realm.where(TemanModel.class)
                        .equalTo("id", id)
                        .findFirst();
                model.setNim(temanModel.getNim());
                model.setNama(temanModel.getNama());
                model.setKelas(temanModel.getKelas());
                model.setTelepon(temanModel.getTelepon());
                model.setEmail(temanModel.getEmail());
                model.setInstagram(temanModel.getInstagram());
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.e("pppp", "onSuccess: Update Successfully");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                error.printStackTrace();
            }
        });
    }

    // untuk menghapus data
    public void delete(Integer id){
        final RealmResults<TemanModel> model = realm.where(TemanModel.class).equalTo("id", id).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                model.deleteFromRealm(0);
            }
        });
    }

}
