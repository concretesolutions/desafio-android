package com.example.rpanaquecavana.gitandroid;

import com.example.rpanaquecavana.gitandroid.DetalleModelo.Detail;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailBody {
    @SerializedName("data")
    //private List<Detail> detalles;

    private Detail[] detalles;

    public Detail[] getDetalles() {
        return detalles;
    }

    public void setDetalles(Detail[] detalles) {
        this.detalles = detalles;
    }
    /*
    public List<Detail> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<Detail> detalles) {
        this.detalles = detalles;
    }*/
}
