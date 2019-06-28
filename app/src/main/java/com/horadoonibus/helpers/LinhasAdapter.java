package com.horadoonibus.helpers;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.horadoonibus.R;
import com.horadoonibus.model.Linha;

import java.util.List;

/**
 * Created by Wolszczak on 27/06/2019.
 */

public class LinhasAdapter extends RecyclerView.Adapter<LinhasAdapter.LinhasViewHolder> {
    private List<Linha> mLinhas;
    private OnClickHandler mClickHandler;


    public LinhasAdapter(OnClickHandler mClickHandler) {
        this.mClickHandler = mClickHandler;
    }

    @NonNull
    @Override
    public LinhasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false);

        return new LinhasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LinhasViewHolder holder, int position) {
        holder.linha.setText(mLinhas.get(position).getCodigo());
        holder.nome.setText(mLinhas.get(position).getNome());
        holder.categoria.setText(mLinhas.get(position).getCategoria());
        holder.cor.setText(mLinhas.get(position).getCor());
        holder.view.setOnClickListener(onClick ->
                mClickHandler.OnClickHandler(mLinhas.get(position)));
    }

    @Override
    public int getItemCount() {
        if (mLinhas == null) return 0;
        return mLinhas.size();
    }

    public class LinhasViewHolder extends RecyclerView.ViewHolder {
        private View view ;
        TextView linha;
        TextView nome;
        TextView categoria;
        TextView cor;

        public LinhasViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            linha = view.findViewById(R.id.linha);
            nome = view.findViewById(R.id.nome);
            categoria = view.findViewById(R.id.categoria);
            cor = view.findViewById(R.id.cor);
        }
    }

    public interface OnClickHandler {
        void OnClickHandler(Linha linha);
    }

    public void setLinhas(List<Linha> linhas) {
        mLinhas = linhas;
        notifyDataSetChanged();
    }
}
