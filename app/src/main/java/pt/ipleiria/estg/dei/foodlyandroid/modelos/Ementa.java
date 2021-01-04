package pt.ipleiria.estg.dei.foodlyandroid.modelos;

import java.util.List;

public class Ementa {
    private int id;
    private String nome;
    private double preco;
    private List<Ingrediente> ingredientes;

    public Ementa(int id, String nome, double preco, List<Ingrediente> ingredientes) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.ingredientes = ingredientes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }
}
