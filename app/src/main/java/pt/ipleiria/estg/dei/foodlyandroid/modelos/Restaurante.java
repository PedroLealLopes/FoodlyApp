package pt.ipleiria.estg.dei.foodlyandroid.modelos;

public class Restaurante {
    private int id, maxPessoas, currPessoas;
    private String nome, descricao, localizacao, capa;
    private String horaAbertura, horaFecho, wifi;
    private boolean animais, vegetariano;

    public Restaurante(int id, int maxPessoas, int currPessoas, String capa, String descricao, String localizacao, String horaAbertura, String horaFecho, String wifi, boolean animais, boolean vegetariano) {
        this.id = id;
        this.maxPessoas = maxPessoas;
        this.currPessoas = currPessoas;
        this.capa = capa;
        this.nome = nome;
        this.descricao = descricao;
        this.localizacao = localizacao;
        this.horaAbertura = horaAbertura;
        this.horaFecho = horaFecho;
        this.wifi = wifi;
        this.animais = animais;
        this.vegetariano = vegetariano;
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

    public String getCapa() {
        return capa;
    }

    public void setCapa(String capa) {
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

    public String getHoraAbertura() {
        return horaAbertura;
    }

    public void setHoraAbertura(String horaAbertura) {
        this.horaAbertura = horaAbertura;
    }

    public String getHoraFecho() {
        return horaFecho;
    }

    public void setHoraFecho(String horaFecho) {
        this.horaFecho = horaFecho;
    }

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    public boolean isAnimais() {
        return animais;
    }

    public void setAnimais(boolean animais) {
        this.animais = animais;
    }

    public boolean isVegetariano() {
        return vegetariano;
    }

    public void setVegetariano(boolean vegetariano) {
        this.vegetariano = vegetariano;
    }
}