package questao4.utils;

import questao4.model.Vertice;

public class ParOrdenado {
    String vertice1;
    String vertice2;

    public ParOrdenado(Vertice v1, Vertice v2) {
        vertice1 = v1.getNome();
        vertice2 = v2.getNome();
    }

    @Override
    public String toString() {
        return "(" + vertice1 + ", " + vertice2 + ")";
    }
}