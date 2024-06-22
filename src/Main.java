import algs.BuscaEmLargura;
import algs.BuscaEmProfundidade;
import algs.Dijkstra;
import model.Aresta;
import model.Graph;
import utils.ParOrdenado;
import model.Vertice;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Graph grafo = new Graph();

        Vertice v0 = new Vertice("v0");
        Vertice v1 = new Vertice("v1");
        Vertice v2 = new Vertice("v2");
        Vertice v3 = new Vertice("v3");
        Vertice v4 = new Vertice("v4");
        Vertice v5 = new Vertice("v5");
        Vertice v6 = new Vertice("v6");
        Vertice v7 = new Vertice("v7");
        Vertice v8 = new Vertice("v8");
        Vertice v9 = new Vertice("v9");

// Adiciona os vertices ao grafo
        grafo.addVertice(v0);
        grafo.addVertice(v1);
        grafo.addVertice(v2);
        grafo.addVertice(v3);
        grafo.addVertice(v4);
        grafo.addVertice(v5);
        grafo.addVertice(v6);
        grafo.addVertice(v7);
        grafo.addVertice(v8);
        grafo.addVertice(v9);

// Cria as arestas
        grafo.addArestaNaoDirecionada(v0, v1);
        grafo.addArestaNaoDirecionada(v1, v2, 1);
        grafo.addArestaNaoDirecionada(v1, v3, 1);
        grafo.addArestaNaoDirecionada(v2, v4, 1);
        grafo.addArestaNaoDirecionada(v2, v6, 1);
        grafo.addArestaNaoDirecionada(v3, v4, 1);
        grafo.addArestaDirecionada(v5, v6, 1);
        grafo.addArestaNaoDirecionada(v5, v7, 1);
        grafo.addArestaNaoDirecionada(v6, v7, 1);
        grafo.addArestaNaoDirecionada(v7, v8, 1);
        grafo.addArestaNaoDirecionada(v8, v9, 1);

//        for (Vertice vertice : grafo.getVertices()) {
//            for (Aresta aresta : vertice.getAdjacentes()) {
//                System.out.println(aresta);
//            }
//        }
//        BuscaEmLargura buscaEmLargura = new BuscaEmLargura(grafo, v0);
//        buscaEmLargura.bfs();
//        List<Vertice> buscaNaDistancia = new ArrayList<>();
//
//        buscaNaDistancia = buscaEmLargura.obterVerticesNaDistancia(3);
//        System.out.println(grafo.toStringBFS());
//        System.out.print("Arestas entre v1 e v8:");
//        System.out.println(buscaEmLargura.contarArestasEntreVertices(v5));
//        for (Vertice v : buscaNaDistancia) {
//            System.out.println(v.getNome());
//        }
//
//        List<ParOrdenado> caminho = new ArrayList<>();
//        caminho = buscaEmLargura.caminhoEntreVertices(v5);
//        System.out.println(caminho);

        //TESTE DFS
//        System.out.println(grafo.toStringDFS());
//        BuscaEmProfundidade buscaEmProfundidade = new BuscaEmProfundidade();
//        buscaEmProfundidade.dfs(grafo);
//        System.out.println(grafo.toStringDFS());
//
        Dijkstra dijkstra = new Dijkstra();
        dijkstra.dijkstra(grafo, v1);
        List<ParOrdenado> caminho = dijkstra.getCaminhoMinimo(v5);
        System.out.println(caminho);
    }
}