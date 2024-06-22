package model;

public class Aresta {
    private Vertice inicio;
    private Vertice fim;
    private int peso;

    public Aresta(Vertice inicio, Vertice fim) {
        this.inicio = inicio;
        this.fim = fim;
    }

    public Vertice getInicio() {
        return inicio;
    }

    public void setInicio(Vertice inicio) {
        this.inicio = inicio;
    }

    public Vertice getFim() {
        return fim;
    }

    public void setFim(Vertice fim) {
        this.fim = fim;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
}
