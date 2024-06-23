package utils;

import model.Aresta;
import model.Vertice;

public class ParOrdenado {
    String vertice1;
    String vertice2;

    public ParOrdenado(Vertice v1, Vertice v2) {
        vertice1 = v1.getNome();
        vertice2 = v2.getNome();
    }

    public ParOrdenado(Aresta aresta) {
        vertice1 = aresta.getInicio().getNome();
        vertice2 = aresta.getFim().getNome();
    }

    public String getVertice1() {
        return vertice1;
    }

    public String getVertice2() {
        return vertice2;
    }

    @Override
    public String toString() {
        return "(" + vertice1 + ", " + vertice2 + ")";
    }
}