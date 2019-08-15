package com.aarisfauji.dreamfriend.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.aarisfauji.dreamfriend.R;
import com.aarisfauji.dreamfriend.model.TemanModel;
import com.aarisfauji.dreamfriend.presenter.IPresenter;


import java.util.List;

/**
 * NIM                : 10116563
 * Nama               : A Aris Fauji
 * Kelas              : AKB-13 (IF-13)
 * Tanggal Pengerjaan : 13 Agustus 2019
 */

public class TemanAdapter extends RecyclerView.Adapter<TemanAdapter.MyViewHolder> {
    private List<TemanModel> temanModels;
    Context context;
    IPresenter.ITeman temanPresenter;

    public TemanAdapter(IPresenter.ITeman temanPresenter, Context context, List<TemanModel> temanModel){
        this.temanPresenter = temanPresenter;
        this.context = context;
        this.temanModels = temanModel;
    }

    @Override
    public TemanAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout._teman_item, parent, false);
        return new MyViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(TemanAdapter.MyViewHolder holder, int position) {
        final TemanModel model = temanModels.get(position);
        holder.nim.setText("NIM: "+model.getNim());
        holder.nama.setText(model.getNama());
        holder.kelas.setText("Kelas: "+ model.getKelas());
        holder.email.setText("Email: "+model.getEmail());
        holder.tlp.setText("Tlp: "+model.getTelepon());
        holder.ig.setText("Ig: "+model.getInstagram());

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temanPresenter.deleteDataTeman(model.getId());
            }
        });
        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("id",String.valueOf(model.getId()));
                bundle.putString("nim",model.getNim());
                bundle.putString("nama",model.getNama());
                bundle.putString("kelas",model.getKelas());
                bundle.putString("email",model.getEmail());
                bundle.putString("tlp",model.getTelepon());
                bundle.putString("ig",model.getInstagram());
                temanPresenter.editDataTeman(bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return temanModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nim, nama,kelas,email,tlp,ig;
        Button btnUpdate,btnDelete;

        public MyViewHolder(View itemView){
            super(itemView);
            nim = itemView.findViewById(R.id.tvNim);
            nama = itemView.findViewById(R.id.tvNama);
            kelas = itemView.findViewById(R.id.tvKelas);
            email = itemView.findViewById(R.id.tvEmail);
            tlp = itemView.findViewById(R.id.tvTlp);
            ig = itemView.findViewById(R.id.tvIg);
            btnUpdate = itemView.findViewById(R.id.btnUpdateTeman);
            btnDelete = itemView.findViewById(R.id.btnDeleteTeman);
        }
    }
}
