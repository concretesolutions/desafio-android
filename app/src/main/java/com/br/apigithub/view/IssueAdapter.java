package com.br.apigithub.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.br.apigithub.R;
import com.br.apigithub.beans.Issue;

import java.util.List;

/**
 * Created by rlima on 04/10/18.
 */

public class IssueAdapter extends RecyclerView.Adapter<IssueViewHolder> {
    private List<Issue> list;
    private Context context;

    public IssueAdapter(List<Issue> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public IssueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.adapter_issue, parent, false);
        return new IssueViewHolder(v);
    }

    @Override
    public void onBindViewHolder(IssueViewHolder holder, int position) {
        Issue i = list.get(position);
        holder.getTitulo().setText(i.getTitle());
        holder.getNumero().setText(i.getNumber().toString());
    }

    @Override
    public int getItemCount() {
        return list != null && !list.isEmpty() ? list.size() : 0;
    }
}

class IssueViewHolder extends RecyclerView.ViewHolder {
    private TextView numero;
    private TextView titulo;
    private TextView descricao;

    public IssueViewHolder(View itemView) {
        super(itemView);
        setTitulo((TextView) itemView.findViewById(R.id.titulo_pull));
        setNumero((TextView) itemView.findViewById(R.id.numero_issue));
    }

    public TextView getNumero() {
        return numero;
    }

    public void setNumero(TextView numero) {
        this.numero = numero;
    }

    public TextView getTitulo() {
        return titulo;
    }

    public void setTitulo(TextView titulo) {
        this.titulo = titulo;
    }

    public TextView getDescricao() {
        return descricao;
    }

    public void setDescricao(TextView descricao) {
        this.descricao = descricao;
    }
}
