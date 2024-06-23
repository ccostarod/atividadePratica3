package algs.mst;

import model.Aresta;
import model.Graph;
import model.Vertice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Prim {
    Heap heap = new Heap();

    public List<Aresta> primMST(Graph graph, Vertice vertice) {
        List<Vertice> vertices = new ArrayList<>(graph.getVertices());
        List<Aresta> mstArestas = new ArrayList<>();

        if (!vertices.contains(vertice)) {
            throw new IllegalArgumentException("O vértice deve estar no grafo.");
        }

        for (Vertice v: vertices) { // Inicializando a chave e o pai de cada vértice
            v.setDistancia(Integer.MAX_VALUE);
            v.setPai(null);
        }

        vertice.setDistancia(0);

        // Constrói o heap mínimo
        heap.buildMinHeap(vertices);

        while (!vertices.isEmpty()) {
            Vertice u = heap.extrairMinimo(vertices);

            for (Aresta aresta : u.getAdjacentes()) {
                Vertice v = aresta.getFim();
                if (vertices.contains(v) && aresta.getPeso() < v.getDistancia()) { // Se v está em vertices e o peso da aresta (u, v) é menor que a chave de v
                    // Atualiza o pai de v para u e a chave de v para o peso da aresta (u, v)
                    v.setPai(u);
                    v.setDistancia(aresta.getPeso());

                    // Atualiza a posição de v no heap
                    int index = vertices.indexOf(v);
                    diminuirChave(vertices, index);
                }
            }
        }

        for (Vertice v : graph.getVertices()) {
            if (v.getPai() != null) {
                mstArestas.add(new Aresta(v.getPai(), v, v.getDistancia()));
            }
        }

        return mstArestas;
    }

    private void diminuirChave(List<Vertice> vertices, int i) {
        while (i > 0 && vertices.get((i - 1) / 2).getDistancia() > vertices.get(i).getDistancia()) {
            Collections.swap(vertices, i, (i - 1) / 2); //Se a propriedade do heap for violada (o valor do pai é maior que o valor do vértice i), o vértice i é trocado com seu pai.
            i = (i - 1) / 2;
        }
    }
}
