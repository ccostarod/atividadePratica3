package algs;

import model.Aresta;
import model.Graph;
import model.Vertice;
import utils.ParOrdenado;

import java.util.*;

public class Dijkstra {

    // Método principal do algoritmo de Dijkstra
    public void dijkstra(Graph grafo, Vertice s) {
        // Inicializa as distâncias dos vértices
        initialize(grafo, s);

        // Cria uma fila de prioridade para os vértices
        PriorityQueue<Vertice> Q = new PriorityQueue<>(new VerticeComparator());
        Q.addAll(grafo.getVertices());

        // Enquanto a fila não estiver vazia
        while (!Q.isEmpty()) {
            // Extrai o vértice com a menor distância
            Vertice u = extractMin(Q);

            // Para cada aresta adjacente ao vértice
            for (Aresta aresta : u.getAdjacentes()) {
                Vertice v = aresta.getFim();
                // Se a distância do vértice adjacente for maior que a distância do vértice atual mais o peso da aresta
                if (relax(u, v, aresta.getPeso())) {
                    // Atualiza a distância do vértice adjacente e reordena a fila
                    Q.remove(v);
                    Q.add(v);
                }
            }
        }
    }

    // Método para inicializar as distâncias dos vértices
    private void initialize(Graph grafo, Vertice s) {
        for (Vertice v : grafo.getVertices()) {
            v.setDistancia(Integer.MAX_VALUE);
            v.setPai(null);
        }
        s.setDistancia(0);
    }

    // Método para extrair o vértice com a menor distância da fila
    private Vertice extractMin(PriorityQueue<Vertice> Q) {
        return Q.poll();
    }

    // Método para relaxar a aresta, ou seja, verificar se a distância do vértice adjacente é maior que a distância do vértice atual mais o peso da aresta
    private boolean relax(Vertice u, Vertice v, int peso) {
        if (v.getDistancia() > u.getDistancia() + peso) {
            v.setDistancia(u.getDistancia() + peso);
            v.setPai(u);
            return true;
        }
        return false;
    }

    // Método para obter o caminho mínimo até o vértice de destino
    public List<ParOrdenado> getCaminhoMinimo(Vertice destino) {
        List<ParOrdenado> caminho = new ArrayList<>();
        Set<Vertice> visitados = new HashSet<>();
        for (Vertice v = destino; v != null; v = v.getPai()) {
            if (visitados.contains(v))
                break;
            if (v.getPai() != null) {
                caminho.add(new ParOrdenado(v.getPai(), v));
                visitados.add(v);
            }
        }
        Collections.reverse(caminho);
        return caminho;
    }

    // Método para obter o segundo caminho mínimo até o vértice de destino
    public List<ParOrdenado> getSegundoCaminhoMinimo(Graph grafo, Vertice inicial, Vertice destino) {
        dijkstra(grafo, inicial);
        List<ParOrdenado> caminhoMinimo = new ArrayList<>(getCaminhoMinimo(destino));
        int segundoCaminhoMinimoDist = Integer.MAX_VALUE;
        List<ParOrdenado> segundoCaminhoMinimo = null;

        for (ParOrdenado aresta : caminhoMinimo) {
            Vertice inicio = grafo.getVertices().stream().filter(v -> v.getNome().equals(aresta.getVertice1())).findFirst().get();
            Vertice fim = grafo.getVertices().stream().filter(v -> v.getNome().equals(aresta.getVertice2())).findFirst().get();
            Aresta arestaRemovida = inicio.getAdjacentes().stream().filter(a -> a.getFim().getNome().equals(fim.getNome())).findFirst().get();

            inicio.getAdjacentes().remove(arestaRemovida);

            // Reseta as distâncias dos vértices
            initialize(grafo, inicial);
            dijkstra(grafo, inicial);
            List<ParOrdenado> novoCaminho = getCaminhoMinimo(destino);
            int novaDistancia = destino.getDistancia();

            if (novaDistancia < segundoCaminhoMinimoDist && novaDistancia != Integer.MAX_VALUE) {
                segundoCaminhoMinimoDist = novaDistancia;
                segundoCaminhoMinimo = novoCaminho;
            }

            // Restaura a aresta removida
            inicio.getAdjacentes().add(arestaRemovida);
        }

        return segundoCaminhoMinimo;
    }

    // Comparador para ordenar os vértices na fila de prioridade
    private class VerticeComparator implements Comparator<Vertice> {
        @Override
        public int compare(Vertice v1, Vertice v2) {
            return Integer.compare(v1.getDistancia(), v2.getDistancia());
        }
    }
}