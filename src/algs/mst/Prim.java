package algs.mst;

import model.Aresta;
import model.Graph;
import model.Vertice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Prim {

    public Graph primMST(Graph graph, Vertice vertice) {
        List<Vertice> vertices = new ArrayList<>(graph.getVertices());
        List<Aresta> mstArestas = new ArrayList<>(); // Lista para armazenar as arestas da MST
        Graph grafo = new Graph();

        if (!vertices.contains(vertice)) {
            throw new IllegalArgumentException("O vértice deve estar no grafo.");
        }

        for (Vertice v: vertices) { // Inicializando a chave e o pai de cada vértice
            v.setDistancia(Integer.MAX_VALUE);
            v.setPai(null);
        }

        vertice.setDistancia(0); // Key[r] = 0

        // Constrói o heap mínimo
        buildMinHeap(vertices);

        while (!vertices.isEmpty()) {
            Vertice u = extrairMinimo(vertices);
            grafo.addVertice(u);

            for (Aresta aresta : u.getAdjacentes()) {
                Vertice v = aresta.getFim();
                if (vertices.contains(v) && aresta.getPeso() < v.getDistancia()) { // Se v está em vertices e o peso da aresta (u, v) é menor que a chave de v
                    // Atualiza o pai de v para u e a chave de v para o peso da aresta (u, v)
                    v.setPai(u);
                    v.setDistancia(aresta.getPeso());

                    // Atualiza a posição de v no heap
                    int index = vertices.indexOf(v);
                    decreaseKey(vertices, index);
                }
            }
        }

//        for (Vertice v : graph.getVertices()) {
//            if (v.getPai() != null) {
//                mstArestas.add(new Aresta(v.getPai(), v, v.getDistancia()));
//            }
//        }
//
//        for (Aresta aresta : mstArestas) {
//            System.out.println("Aresta: " + aresta.getInicio().getNome() + " - " + aresta.getFim().getNome() + " | Peso: " + aresta.getPeso());
//        }
        return graph;
    }

    private void decreaseKey(List<Vertice> vertices, int i) {
        while (i > 0 && vertices.get((i - 1) / 2).getDistancia() > vertices.get(i).getDistancia()) {
            Collections.swap(vertices, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    private void buildMinHeap(List<Vertice> vertices) {
        for (int i = vertices.size() / 2 - 1; i >= 0; i--) {
            minHeapify(vertices, i, vertices.size());
        }
    }

    private Vertice extrairMinimo(List<Vertice> vertices) {
        Vertice min = vertices.get(0);
        vertices.set(0, vertices.get(vertices.size() - 1)); // Substitui a raiz pelo último elemento
        vertices.remove(vertices.size() - 1); // Remove o último elemento já que agora tá duplicado
        minHeapify(vertices, 0, vertices.size()); // Restaurando propriedade do heap
        return min;
    }

    private void minHeapify(List<Vertice> vertices, int i, int tamanho) {
        int raiz = i;
        int esquerda = 2 * i + 1;
        int direita = 2 * i + 2;
        if (esquerda < tamanho && vertices.get(esquerda).compareTo(vertices.get(raiz)) < 0) {
            raiz = esquerda;
        }
        if (direita < tamanho && vertices.get(direita).compareTo(vertices.get(raiz)) < 0) {
            raiz = direita;
        }

        if (raiz != i) {
            Collections.swap(vertices, i, raiz);
            minHeapify(vertices, raiz, tamanho);
        }
    }
}
