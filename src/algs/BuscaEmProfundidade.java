package algs;

import model.Aresta;
import model.Graph;
import model.Vertice;

public class BuscaEmProfundidade {
    private int tempo;
    public void dfs(Graph grafo) {
        for (Vertice vertice : grafo.getVertices()) {
            vertice.setCor("branco");
            vertice.setPai(null);
        }
        this.tempo = 0;
        for (Vertice vertice : grafo.getVertices()) {
            if (vertice.getCor().equals("branco")) {
                dfsVisit(grafo, vertice);
            }
        }
    }

    public void dfsVisit(Graph grafo, Vertice vertice) {
        this.tempo++;
        vertice.setTempoInicial(tempo);
        vertice.setCor("cinza");
        for (Aresta a : vertice.getAdjacentes()) {
            Vertice v = a.getFim();
            if (v.getCor().equals("branco")) {
                v.setPai(vertice);
                dfsVisit(grafo, v);
            }
        }
        vertice.setCor("preto");
        this.tempo++;
        vertice.setTempoFinal(tempo);
    }
}
