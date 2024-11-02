package com.natalia.myapplication;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DadosTempo {
    private String cidade;
    private String tempAgora;
    private String image;
    private ArrayList<Forecast> lista = new ArrayList<>();

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getTempAgora() {
        return tempAgora;
    }

    public void setTempAgora(String tempAgora) {
        this.tempAgora = tempAgora;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<Forecast> getLista() {
        return lista;
    }

    public void setLista(List<Forecast> lista) {
        this.lista.addAll(lista);
    }
}