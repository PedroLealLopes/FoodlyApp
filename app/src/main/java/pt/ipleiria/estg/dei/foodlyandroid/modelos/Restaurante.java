package pt.ipleiria.estg.dei.foodlyandroid.modelos;

public class Restaurante {
    private int id, maxPessoas, currPessoas;
    private String nome, localizacao;

    public Restaurante(int id, int maxPessoas, int currPessoas, String nome, String localizacao) {
        this.id = id;
        this.maxPessoas = maxPessoas;
        this.currPessoas = currPessoas;
        this.nome = nome;
        this.localizacao = localizacao;
    }

    public int getId() {
        return id;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
}