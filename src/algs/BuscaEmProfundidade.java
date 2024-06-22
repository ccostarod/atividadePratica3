package algs;

import questao4.model.Graph;
import questao4.model.Vertice;

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
        for (Vertice v : vertice.getAdjacentes()) {
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
