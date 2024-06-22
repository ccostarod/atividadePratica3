package questao4;


import questao4.model.Graph;
import questao4.utils.ParOrdenado;
import questao4.model.Vertice;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Graph grafo = new Graph();

        //Criar vertices

        Vertice v1 = new Vertice("v1");
        Vertice v2 = new Vertice("v2");
        Vertice v3 = new Vertice("v3");
        Vertice v4 = new Vertice("v4");

        // adiciona os vertices ao grafo
        grafo.addVertice(v1);
        grafo.addVertice(v2);
        grafo.addVertice(v3);
        grafo.addVertice(v4);

        // Cria algumas arestas
        grafo.addAresta(v1, v2);
        grafo.addAresta(v2, v3);
        grafo.addAresta(v2, v4);
        System.out.println(grafo);
        BuscaEmLargura buscaEmLargura = new BuscaEmLargura(grafo, v1);
        buscaEmLargura.bfs();
        List<Vertice> buscaNaDistancia = new ArrayList<>();

        buscaNaDistancia = buscaEmLargura.obterVerticesNaDistancia(1);
        System.out.println(grafo);
        System.out.print("Arestas entre v1 e v4:");
        System.out.println(buscaEmLargura.contarArestasEntreVertices(v4));
        for (Vertice v : buscaNaDistancia) {
            System.out.println(v.getNome());
        }

        List<ParOrdenado> caminho = new ArrayList<>();
        caminho = buscaEmLargura.caminhoEntreVertices(v4);
        System.out.println(caminho);
    }
}