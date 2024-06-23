package model;

import java.util.ArrayList;
import java.util.List;

public class Vertice implements Comparable<Vertice> {
    private String nome;
    private String cor;
    private Vertice pai;
    private List<Aresta> adjacentes;
    // Atributo necessario para BFS e dijkstra
    private int distancia;
    // Atributos necessarios para DFS
    private int tempoInicial;
    private int tempoFinal;

    public Vertice(String nome) {
        this.nome = nome;
        this.distancia = Integer.MAX_VALUE;
        this.cor = "branco";
        this.pai = null;
        this.adjacentes = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getCor() {
        return cor;
    }

    public int getDistancia() {
        return distancia;
    }

    public Vertice getPai() {
        return pai;
    }

    public List<Aresta> getAdjacentes() {
        return adjacentes;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public void setPai(Vertice pai) {
        this.pai = pai;
    }

    public int getTempoInicial() {
        return tempoInicial;
    }

    public int getTempoFinal() {
        return tempoFinal;
    }

    public void setTempoInicial(int tempo) {
        this.tempoInicial = tempo;
    }

    public void setTempoFinal(int tempoFinal) {
        this.tempoFinal = tempoFinal;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Vertice{");
        sb.append("nome='").append(nome).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(Vertice outro) {
        return this.nome.compareTo(outro.getNome());
    }
}
