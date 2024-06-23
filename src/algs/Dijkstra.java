//package algs;
//
//import model.Aresta;
//import model.Graph;
//import model.Vertice;
//import utils.ParOrdenado;
//
//import java.util.*;
//
//public class Dijkstra {
//    public void dijkstra(Graph grafo, Vertice inicial) {
//        for (Vertice v : grafo.getVertices()) {
//            if (v.getNome().equals(inicial.getNome())) {
//                v.setDistancia(0);
//            }
//        }
//
//        PriorityQueue<Vertice> fila = new PriorityQueue<>(new VerticeComparator());
//        fila.add(inicial);
//
//        while (!fila.isEmpty()) {
//            Vertice vert = fila.poll();
//
//            for (Aresta aresta : vert.getAdjacentes()) {
//                Vertice adj = aresta.getFim();
//                int peso = aresta.getPeso();
//                int distancia = vert.getDistancia() + peso;
//                if (distancia < adj.getDistancia()) {
//                    fila.remove(adj);
//                    adj.setDistancia(distancia);
//                    adj.setPai(vert);
//                    fila.add(adj);
//                }
//            }
//        }
//    }
//
//    public List<ParOrdenado> getCaminhoMinimo(Vertice destino) {
//        List<ParOrdenado> caminho = new ArrayList<>();
//        for (Vertice v = destino; v != null; v = v.getPai()) {
//            if (v.getPai() != null)
//                caminho.add(new ParOrdenado(v.getPai(), v));
//        }
//        Collections.reverse(caminho);
//        return caminho;
//    }
//
//    public List<ParOrdenado> getSegundoCaminhoMinimo(Graph grafo, Vertice inicial, Vertice destino) {
//        dijkstra(grafo, inicial);
//        List<ParOrdenado> caminhoMinimo = new ArrayList<>(getCaminhoMinimo(destino));
//        int segundoCaminhoMinimoDist = Integer.MAX_VALUE;
//        List<ParOrdenado> segundoCaminhoMinimo = null;
//
//        for (ParOrdenado aresta : caminhoMinimo) {
//            Vertice inicio = grafo.getVertices().stream().filter(v -> v.getNome().equals(aresta.getVertice1())).findFirst().get();
//            Vertice fim = grafo.getVertices().stream().filter(v -> v.getNome().equals(aresta.getVertice2())).findFirst().get();
//            Aresta arestaRemovida = inicio.getAdjacentes().stream().filter(a -> a.getFim().getNome().equals(fim.getNome())).findFirst().get();
//            inicio.getAdjacentes().remove(arestaRemovida);
//            fim.getAdjacentes().remove(arestaRemovida);
//
//            grafo.restaurar();
//            dijkstra(grafo, inicial);
//            List<ParOrdenado> novoCaminho = getCaminhoMinimo(destino);
//            int novaDistancia = destino.getDistancia();
//
//            if (novaDistancia < segundoCaminhoMinimoDist && novaDistancia != Integer.MAX_VALUE) {
//                segundoCaminhoMinimoDist = novaDistancia;
//                segundoCaminhoMinimo = novoCaminho;
//            }
//
//            inicio.getAdjacentes().add(arestaRemovida);
//            fim.getAdjacentes().add(arestaRemovida);
//        }
//
//        return segundoCaminhoMinimo;
//    }
//
//
//
//    private class VerticeComparator implements Comparator<Vertice> {
//        @Override
//        public int compare(Vertice v1, Vertice v2) {
//            return Integer.compare(v1.getDistancia(), v2.getDistancia());
//        }
//    }
//}

package algs;

import model.Aresta;
import model.Graph;
import model.Vertice;
import utils.ParOrdenado;

import java.util.*;

public class Dijkstra {

    public void dijkstra(Graph grafo, Vertice s) {
        initialize(grafo, s);

        PriorityQueue<Vertice> Q = new PriorityQueue<>(new VerticeComparator());
        Q.addAll(grafo.getVertices());

        while (!Q.isEmpty()) {
            Vertice u = extractMin(Q);

            for (Aresta aresta : u.getAdjacentes()) {
                Vertice v = aresta.getFim();
                if (relax(u, v, aresta.getPeso())) {
                    // Se relaxar atualizou a distância de v, precisamos reordenar a fila
                    Q.remove(v);
                    Q.add(v);
                }
            }
        }
    }

    private void initialize(Graph grafo, Vertice s) {
        for (Vertice v : grafo.getVertices()) {
            v.setDistancia(Integer.MAX_VALUE);
            v.setPai(null);
        }
        s.setDistancia(0);
    }

    private Vertice extractMin(PriorityQueue<Vertice> Q) {
        return Q.poll();
    }

    private boolean relax(Vertice u, Vertice v, int peso) {
        if (v.getDistancia() > u.getDistancia() + peso) {
            v.setDistancia(u.getDistancia() + peso);
            v.setPai(u);
            return true;
        }
        return false;
    }

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

            // Reset the distances of the vertices
            initialize(grafo, inicial);
            dijkstra(grafo, inicial);
            List<ParOrdenado> novoCaminho = getCaminhoMinimo(destino);
            int novaDistancia = destino.getDistancia();

            if (novaDistancia < segundoCaminhoMinimoDist && novaDistancia != Integer.MAX_VALUE) {
                segundoCaminhoMinimoDist = novaDistancia;
                segundoCaminhoMinimo = novoCaminho;
            }

            // Restaurar a aresta removida
            inicio.getAdjacentes().add(arestaRemovida);
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


