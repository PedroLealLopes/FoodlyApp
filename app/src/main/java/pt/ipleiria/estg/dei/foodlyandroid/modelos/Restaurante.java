package pt.ipleiria.estg.dei.foodlyandroid.modelos;

import java.util.ArrayList;
import java.util.Date;

public class Restaurante {
    private int id, maxPessoas, currPessoas, capa;
    private String nome, descricao, localizacao, classificacao;
    private Date horaAbertura, horaFecho;
    private boolean wifi, animais;

    public Restaurante(int id, int maxPessoas, int currPessoas, int capa, String nome, String descricao, String localizacao, String classificacao, Date horaAbertura, Date horaFecho, boolean wifi, boolean animais) {
        this.id = id;
        this.maxPessoas = maxPessoas;
        this.currPessoas = currPessoas;
        this.capa = capa;
        this.nome = nome;
        this.descricao = descricao;
        this.localizacao = localizacao;
        this.classificacao = classificacao;
        this.horaAbertura = horaAbertura;
        this.horaFecho = horaFecho;
        this.wifi = wifi;
        this.animais = animais;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaxPessoas() {
        return maxPessoas;
    }

    public void setMaxPessoas(int maxPessoas) {
        this.maxPessoas = maxPessoas;
    }

    public int getCurrPessoas() {
        return currPessoas;
    }

    public void setCurrPessoas(int currPessoas) {
        this.currPessoas = currPessoas;
    }

    public int getCapa() {
        return capa;
    }

    public void setCapa(int capa) {
        this.capa = capa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public Date getHoraAbertura() {
        return horaAbertura;
    }

    public void setHoraAbertura(Date horaAbertura) {
        this.horaAbertura = horaAbertura;
    }

    public Date getHoraFecho() {
        return horaFecho;
    }

    public void setHoraFecho(Date horaFecho) {
        this.horaFecho = horaFecho;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isAnimais() {
        return animais;
    }

    public void setAnimais(boolean animais) {
        this.animais = animais;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }
}