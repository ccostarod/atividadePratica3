package algs.mst;

import model.Vertice;

import java.util.Collections;
import java.util.List;

public class Heap {
    public void buildMinHeap(List<Vertice> vertices) {
        for (int i = vertices.size() / 2 - 1; i >= 0; i--) {
            minHeapify(vertices, i, vertices.size());
        }
    }

    public Vertice extrairMinimo(List<Vertice> vertices) {
        Vertice min = vertices.get(0);
        vertices.set(0, vertices.get(vertices.size() - 1)); // Substitui a raiz pelo último elemento
        vertices.remove(vertices.size() - 1); // Remove o último elemento já que agora tá duplicado
        minHeapify(vertices, 0, vertices.size()); // Restaurando propriedade do heap
        return min;
    }

    public void minHeapify(List<Vertice> vertices, int i, int tamanho) {
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
