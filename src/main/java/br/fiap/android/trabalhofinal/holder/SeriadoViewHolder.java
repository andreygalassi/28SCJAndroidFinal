package br.fiap.android.trabalhofinal.holder;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import br.fiap.android.trabalhofinal.NovoSeriadoActivity;
import br.fiap.android.trabalhofinal.R;
import br.fiap.android.trabalhofinal.adapter.SeriadoAdapter;
import br.fiap.android.trabalhofinal.dao.SeriadoDAO;
import br.fiap.android.trabalhofinal.model.Seriado;

/**
 * Created by usuario on 15/04/2017.
 */

public class SeriadoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public final TextView nome;
    public final TextView data;
    private int id;
    public final SeriadoAdapter adapter;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public SeriadoViewHolder(final View view, final SeriadoAdapter adapter){

        super(view);
        this.adapter = adapter;
        view.setOnClickListener(this);

        nome = (TextView) view.findViewById(R.id.tvHNome);
        data = (TextView) view.findViewById(R.id.tvHData);
    }

    public void preencher(Seriado seriado) {
        id = seriado.getId();
        nome.setText(seriado.getNome());
        data.setText(sdf.format(seriado.getData()));
    }

    @Override
    public void onClick(View v) {

        PopupMenu popup = new PopupMenu(v.getContext(), v);
        popup.getMenuInflater().inflate(R.menu.menu_item, popup.getMenu());

        final Activity context = (Activity)v.getContext();

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.mEditar:

                        final Intent intent = new Intent(context, NovoSeriadoActivity.class);
                        intent.putExtra("id", id);
                        context.startActivityForResult(intent, 1002);
                        break;

                    case R.id.mDeletar:
                        SeriadoDAO seriadoDAO = new SeriadoDAO(context);
                        seriadoDAO.deletaSeriado(id);
                        deletarSeriado();
                        break;

                }
                return true;
            }
        });

        popup.show();
    }

    public void deletarSeriado(){
        adapter.remove(getAdapterPosition());
    }

}