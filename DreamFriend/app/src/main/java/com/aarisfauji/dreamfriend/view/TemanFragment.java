package com.aarisfauji.dreamfriend.view;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aarisfauji.dreamfriend.R;
import com.aarisfauji.dreamfriend.adapter.TemanAdapter;
import com.aarisfauji.dreamfriend.model.TemanModel;
import com.aarisfauji.dreamfriend.presenter.IPresenter;
import com.aarisfauji.dreamfriend.presenter.TemanPresenter;

/**
 * A simple {@link Fragment} subclass.
 * NIM                : 10116563
 * Nama               : A Aris Fauji
 * Kelas              : AKB-13 (IF-13)
 * Tanggal Pengerjaan : 13 Agustus 2019
 */
public class TemanFragment extends Fragment implements  IView.ITeman, View.OnClickListener{
    View myView;
    RecyclerView recyclerView;
    IPresenter.ITeman temanPresenter;
    FloatingActionButton fab;

    public TemanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         myView =inflater.inflate(R.layout.fragment_teman, container, false);
        recyclerView = myView.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        temanPresenter = new TemanPresenter(this,getActivity());
        temanPresenter.loadDataTeman();
        fab = myView.findViewById(R.id.fab);
        fab.setOnClickListener(this);
         return myView;
    }

    @Override
    public void showTeman(TemanAdapter temanAdapter) {
        recyclerView.setAdapter(temanAdapter);
    }

    @Override
    public void temanDetail(int id,String nim,String nama) {
        Bundle bundle = new Bundle();
        bundle.putString("id",String.valueOf(id));
        bundle.putString("nim",nim);
        bundle.putString("nama",nama);
        TemanUpdateFragment TDF =new TemanUpdateFragment();
        TDF.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, TDF);
        fragmentTransaction.commit();
    }

    @Override
    public void updateTeman(Bundle bundle) {
        TemanUpdateFragment TDF =new TemanUpdateFragment();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        TDF.setArguments(bundle);
        fragmentTransaction.replace(R.id.content_frame, TDF);
        fragmentTransaction.commit();
    }


    @Override
    public void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.commit();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab :
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, new TemanAddFragment());
                fragmentTransaction.commit();
                break;
        }
    }
}
