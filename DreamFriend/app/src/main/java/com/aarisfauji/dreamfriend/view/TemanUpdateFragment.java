package com.aarisfauji.dreamfriend.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aarisfauji.dreamfriend.R;
import com.aarisfauji.dreamfriend.presenter.IPresenter;
import com.aarisfauji.dreamfriend.presenter.TemanPresenter;
import com.aarisfauji.dreamfriend.realm.RealmHelper;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * A simple {@link Fragment} subclass.
 * NIM                : 10116563
 * Nama               : A Aris Fauji
 * Kelas              : AKB-13 (IF-13)
 * Tanggal Pengerjaan : 13 Agustus 2019
 */
public class TemanUpdateFragment extends Fragment implements IView.ITemanUpdate, View.OnClickListener{

    View myView;
    Button btnUbah, btnKembali;
    EditText edtNim, edtNama,edtKelas,edtEmail,edtTlp,edtIg;
    int id;
    String nim,nama,kelas,email,tlp,ig;
    IPresenter.ITeman temanPresenter;

    public TemanUpdateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_teman_update, container, false);
        // Inisialisasi
        temanPresenter = new TemanPresenter(this,getActivity());
        btnUbah= (Button)  myView.findViewById(R.id.btnUpdate);
        btnKembali = (Button) myView.findViewById(R.id.btnKembali);
        edtNim = (EditText) myView.findViewById(R.id.edtNim);
        edtNama =(EditText) myView.findViewById(R.id.edtNama);
        edtKelas = (EditText) myView.findViewById(R.id.edtKelas);
        edtEmail = (EditText)myView.findViewById(R.id.edtEmail);
        edtTlp = (EditText)myView.findViewById(R.id.edtTlp);
        edtIg = (EditText)myView.findViewById(R.id.edtIg);
//        btnUbah.setOnClickListener(this);
//        btnKembali.setOnClickListener(this);

        id = Integer.parseInt(getArguments().getString("id"));
        nim = getArguments().getString("nim");
        nama = getArguments().getString("nama");
        kelas = getArguments().getString("kelas");
        tlp = getArguments().getString("tlp");
        email = getArguments().getString("email");
        ig = getArguments().getString("ig");
        edtNim.setText(nim);
        edtNama.setText(nama);
        edtKelas.setText(kelas);
        edtEmail.setText(email);
        edtTlp.setText(tlp);
        edtIg.setText(ig);

        btnKembali.setOnClickListener(this);
        btnUbah.setOnClickListener(this);
        return myView;
    }

    @Override
    public void onClick(View v) {
        if (v == btnUbah){
            int error1 =0;
            nim = edtNim.getText().toString().trim();
            nama = edtNama.getText().toString().trim();
            kelas = edtKelas.getText().toString().trim();
            email = edtEmail.getText().toString().trim();
            tlp = edtTlp.getText().toString().trim();
            ig = edtIg.getText().toString().trim();
            if( TextUtils.isEmpty(nim)){
                edtNim.setError("Kolom NIM harus diisi");
                error1++;
            }
            if(TextUtils.isEmpty(nama)){
                edtNama.setError("Kolom nama harus diisi");
                error1++;
            }
            if(TextUtils.isEmpty(kelas)){
                edtKelas.setError("Kolom kelas harus diisi");
                error1++;
            }
            if(TextUtils.isEmpty(email)){
                edtEmail.setError("Kolom email harus diisi");
                error1++;
            }
            if(TextUtils.isEmpty(tlp)){
                edtTlp.setError("Kolom nomor telepon harus diisi");
                error1++;
            }
            if(TextUtils.isEmpty(ig)){
                edtIg.setError("Kolom instagram harus diisi");
                error1++;
            }
            if(error1 ==0){
                Bundle bundle = new Bundle();
                bundle.putString("nim",nim);
                bundle.putString("nama",nama);
                bundle.putString("kelas",kelas);
                bundle.putString("email",email);
                bundle.putString("tlp",tlp);
                bundle.putString("ig",ig);
                try {
                    temanPresenter.updateDataTeman(id,bundle);
                    Toast.makeText(getActivity(), "Data teman berhasil Diubah", Toast.LENGTH_SHORT).show();
                    closeKeyword();
                }catch (Exception e){
                    Toast.makeText(getActivity(), "Data teman gagal Diubah", Toast.LENGTH_SHORT).show();
                }
            }
        }else if (v == btnKembali) {
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, new TemanFragment());
            fragmentTransaction.commit();
        }
    }

    void closeKeyword(){
        View view = getActivity().getCurrentFocus();
        if(view != null){
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }
}
