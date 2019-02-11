package com.example.rpanaquecavana.gitandroid;

import com.example.rpanaquecavana.gitandroid.DetalleModelo.Detalle;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailBody {
    @SerializedName("data")
    private List<Detalle> detalles;

    public List<Detalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<Detalle> detalles) {
        this.detalles = detalles;
    }
}
