package br.fiap.android.trabalhofinal.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.fiap.android.trabalhofinal.R;
import br.fiap.android.trabalhofinal.holder.SeriadoViewHolder;
import br.fiap.android.trabalhofinal.model.Seriado;

/**
 * Created by usuario on 15/04/2017.
 */

public class SeriadoAdapter extends RecyclerView.Adapter  {

    private List<Seriado> seriados;
    private Context context;

    public SeriadoAdapter(List<Seriado> seriados, Context context) {
        this.seriados = seriados;
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lista, parent, false);
        SeriadoViewHolder holder = new SeriadoViewHolder(view, this);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SeriadoViewHolder viewHolder = (SeriadoViewHolder) holder;
        Seriado seriado  = seriados.get(position);
        ((SeriadoViewHolder) holder).preencher(seriado);
    }

    @Override
    public int getItemCount() {
        return seriados.size();
    }

    public void remove(int position) {
        seriados.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
    }
}