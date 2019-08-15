package com.aarisfauji.dreamfriend.presenter;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.aarisfauji.dreamfriend.adapter.TemanAdapter;
import com.aarisfauji.dreamfriend.model.TemanModel;
import com.aarisfauji.dreamfriend.realm.RealmHelper;
import com.aarisfauji.dreamfriend.view.IView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * NIM                : 10116563
 * Nama               : A Aris Fauji
 * Kelas              : AKB-13 (IF-13)
 * Tanggal Pengerjaan : 13 Agustus 2019
 */
public class TemanPresenter implements IPresenter.ITeman {

    private IView.ITemanUpdate temanViewUpdate;
    private IView.ITeman temanView;
    private IView.ITemanAdd temanViewAdd;
    private Context context;
    private Realm realm;
    private RealmHelper realmHelper;
    private TemanAdapter temanAdapter;
    private List<TemanModel> temanModels;

    public TemanPresenter(IView.ITeman temanView, Context context) {
        this.temanView = temanView;
        this.context = context;
        // Setup Realm
        inisialisasi();
    }
    public TemanPresenter(IView.ITemanAdd temanView, Context context) {
        this.temanViewAdd = temanView;
        this.context = context;
        // Setup Realm
        inisialisasi();
    }
    public TemanPresenter(IView.ITemanUpdate temanViewUpdate, Context context) {
        this.temanViewUpdate = temanViewUpdate;
        this.context = context;
        // Setup Realm
        inisialisasi();
    }

    @Override
    public void loadDataTeman() {
        temanAdapter = new TemanAdapter(this,context, temanModels);
        temanView.showTeman(temanAdapter);
    }

    @Override
    public void temanAdd(Bundle bundle) {
        TemanModel temanModel = new TemanModel();
        temanModel.setNim(bundle.getString("nim"));
        temanModel.setNama(bundle.getString("nama"));
        temanModel.setKelas(bundle.getString("kelas"));
        temanModel.setEmail(bundle.getString("email"));
        temanModel.setTelepon(bundle.getString("tlp"));
        temanModel.setInstagram(bundle.getString("ig"));
        realmHelper.save(temanModel);
    }

    @Override
    public void editDataTeman(Bundle bundle) {
        temanView.updateTeman(bundle);
    }

    @Override
    public void deleteDataTeman(int id) {
        realmHelper.delete(id);
        Toast.makeText(context, "Data teman berhasil dihapus", Toast.LENGTH_SHORT).show();
        inisialisasi();
        loadDataTeman();
    }
     @Override
     public void updateDataTeman(int id, Bundle bundle){
         TemanModel temanModel = new TemanModel();
         temanModel.setNim(bundle.getString("nim"));
         temanModel.setNama(bundle.getString("nama"));
         temanModel.setKelas(bundle.getString("kelas"));
         temanModel.setEmail(bundle.getString("email"));
         temanModel.setTelepon(bundle.getString("tlp"));
         temanModel.setInstagram(bundle.getString("ig"));
         realmHelper.update(id, temanModel);
         //refresh();
         //inisialisasi();
         //loadDataTeman();
    }

    private void refresh() {
        Realm.init(context);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);
    }

    private void inisialisasi(){
        Realm.init(context);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);
        temanModels = new ArrayList<>();
        temanModels = realmHelper.getAllTeman();
    }
}
