package pt.ipleiria.estg.dei.foodlyandroid.modelos;

import java.util.ArrayList;
import java.util.Date;

public class Reviews {
    private int id, classificacao;
    private String comentario, nomeUtilizador;

    public Reviews(int id, int classificacao, String comentario, String nomeUtilizador) {
        this.id = id;
        this.classificacao = classificacao;
        this.comentario = comentario;
        this.nomeUtilizador = nomeUtilizador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getNomeUtilizador() {
        return nomeUtilizador;
    }

    public void setNomeUtilizador(String nomeUtilizador) {
        this.nomeUtilizador = nomeUtilizador;
    }
}