import algs.mst.NovoMST;
import algs.mst.Prim;
import model.Aresta;
import model.Graph;
import model.Vertice;

import java.util.List;

public class MainPrim {
    public static void main(String[] args) {
        // Cria o grafo
        Graph graph = new Graph();

        // Cria os vértices
        Vertice a = new Vertice("A");
        Vertice b = new Vertice("B");
        Vertice c = new Vertice("C");
        Vertice d = new Vertice("D");
        Vertice e = new Vertice("E");

        // Adiciona os vértices ao grafo
        graph.addVertice(a);
        graph.addVertice(b);
        graph.addVertice(c);
        graph.addVertice(d);
        graph.addVertice(e);

        // Adiciona as arestas
        graph.addArestaNaoDirecionada(a, b, 3);
        graph.addArestaNaoDirecionada(a, c, 5);
        graph.addArestaNaoDirecionada(a, d, 8);
        graph.addArestaNaoDirecionada(a, e, 9);
        graph.addArestaNaoDirecionada(b, d, 2);
        graph.addArestaNaoDirecionada(c, d, 8);
        graph.addArestaNaoDirecionada(c, e, 2);
        graph.addArestaNaoDirecionada(d, e, 1);

        Prim prim = new Prim();
        long inicio = System.currentTimeMillis();
        List<Aresta> mstArestas = prim.primMST(graph, a);
        long fim = System.currentTimeMillis();

        long tempo = fim - inicio;
        System.out.println(tempo);
        System.out.println();

        for (Aresta aresta : mstArestas) {
            System.out.println("Aresta: " + aresta.getInicio().getNome() + " - " + aresta.getFim().getNome() + " | Peso: " + aresta.getPeso());
        }
    }
}
