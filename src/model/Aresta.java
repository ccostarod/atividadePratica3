package model;

import java.util.Objects;

public class Aresta {
    private Vertice inicio;
    private Vertice fim;
    private int peso;

    public Aresta(Vertice inicio, Vertice fim) {
        this.inicio = inicio;
        this.fim = fim;
    }

    public Aresta(Vertice inicio, Vertice fim, int peso) {
        this.inicio = inicio;
        this.fim = fim;
        this.peso = peso;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aresta aresta = (Aresta) o;
        return peso == aresta.peso &&
                ((Objects.equals(inicio, aresta.inicio) && Objects.equals(fim, aresta.fim)) ||
                        (Objects.equals(inicio, aresta.fim) && Objects.equals(fim, aresta.inicio)));
    }

    @Override
    public String toString() {
        return "Aresta{" +
                "inicio=" + inicio +
                ", fim=" + fim +
                ", peso=" + peso +
                '}';
    }
}
