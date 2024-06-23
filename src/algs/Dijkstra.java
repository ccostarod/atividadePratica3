package algs;

import model.Aresta;
import model.Graph;
import model.Vertice;
import utils.ParOrdenado;

import java.util.*;

public class Dijkstra {
    public void dijkstra(Graph grafo, Vertice inicial) {
        for (Vertice v : grafo.getVertices()) {
            if (v.getNome().equals(inicial.getNome())) {
                v.setDistancia(0);
            }
        }

        PriorityQueue<Vertice> fila = new PriorityQueue<>(new VerticeComparator());
        fila.add(inicial);

        while (!fila.isEmpty()) {
            Vertice vert = fila.poll();

            for (Aresta aresta : vert.getAdjacentes()) {
                Vertice adj = aresta.getFim();
                int peso = aresta.getPeso();
                int distancia = vert.getDistancia() + peso;
                if (distancia < adj.getDistancia()) {
                    fila.remove(adj);
                    adj.setDistancia(distancia);
                    adj.setPai(vert);
                    fila.add(adj);
                }
            }
        }
    }

    public List<ParOrdenado> getCaminhoMinimo(Vertice destino) {
        List<ParOrdenado> caminho = new ArrayList<>();
        for (Vertice v = destino; v != null; v = v.getPai()) {
            if (v.getPai() != null)
                caminho.add(new ParOrdenado(v.getPai(), v));
        }
        Collections.reverse(caminho); // inverte a lista para começar pelo vértice de origem
        return caminho;
    }

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
            fim.getAdjacentes().remove(arestaRemovida);

            grafo.restaurar();
            dijkstra(grafo, inicial);
            List<ParOrdenado> novoCaminho = getCaminhoMinimo(destino);
            int novaDistancia = destino.getDistancia();

            if (novaDistancia < segundoCaminhoMinimoDist && novaDistancia != Integer.MAX_VALUE) {
                segundoCaminhoMinimoDist = novaDistancia;
                segundoCaminhoMinimo = novoCaminho;
            }

            inicio.getAdjacentes().add(arestaRemovida);
            fim.getAdjacentes().add(arestaRemovida);
        }

        return segundoCaminhoMinimo;
    }



    private class VerticeComparator implements Comparator<Vertice> {
        @Override
        public int compare(Vertice v1, Vertice v2) {
            return Integer.compare(v1.getDistancia(), v2.getDistancia());
        }
    }
}
