package questao4.model;

import java.util.ArrayList;
import java.util.List;

public class Vertice {
    private String nome;
    private String cor;
    private int distancia;
    private Vertice pai;
    private List<Vertice> adjacentes;

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

    public List<Vertice> getAdjacentes() {
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

    public void setAdjacentes(ArrayList<Vertice> adjacentes) {
        this.adjacentes = adjacentes;
    }



    public void setTempoInicial(int tempo) {
        this.tempoInicial = tempo;
    }

    public void setTempoFinal(int tempoFinal) {
        this.tempoFinal = tempoFinal;
    }
}
