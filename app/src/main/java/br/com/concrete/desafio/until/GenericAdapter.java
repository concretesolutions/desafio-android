package br.com.concrete.desafio.until;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class GenericAdapter<T> extends RecyclerView.Adapter<GenericAdapter.GenericViewHolder> {
    ArrayList<T> dataSet;

    GenericAdapter(T[] dataSet) {
        this.dataSet = new ArrayList<>();

        if (dataSet != null) this.dataSet.addAll(Arrays.asList(dataSet));
    }

    public void addData(T data) {
        dataSet.add(data);
    }

    public void addData(T[] data) {
        dataSet.addAll(Arrays.asList(data));
    }

    public ArrayList<T> getDataSet() {
        return dataSet;
    }

    public void setDataSet(ArrayList<T> dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public int getItemCount() {
        if (dataSet == null) return 0;
        else return dataSet.size();
    }

    static class GenericViewHolder extends RecyclerView.ViewHolder {
        final ConstraintLayout constraintLayout;

        GenericViewHolder(@NonNull View itemView) {
            super(itemView);

            constraintLayout = (ConstraintLayout) itemView;
        }
    }
}