package algs;

import model.Aresta;
import model.Graph;
import model.Vertice;
import utils.ParOrdenado;

import java.util.*;

public class BuscaEmLargura {
    private Graph grafo;


    public BuscaEmLargura(Graph grafo) {
        this.grafo = grafo;
    }
    Queue<Vertice> fila =  new LinkedList<>();
    public void bfs(Vertice inicial) {
        for (Vertice vertice : this.grafo.getVertices()) {
            vertice.setCor("branco");
            vertice.setDistancia(Integer.MAX_VALUE);
            vertice.setPai(null);
        }
        inicial.setCor("cinza");
        inicial.setDistancia(0);

        fila.add(inicial);
        while (!fila.isEmpty()) {
            Vertice vert = fila.poll();
            for (Aresta aresta : vert.getAdjacentes()) {
                Vertice adj = aresta.getFim();
                if (adj.getCor().equals("branco")) {
                    adj.setCor("cinza");
                    adj.setDistancia(vert.getDistancia() + 1);
                    adj.setPai(vert);
                    fila.add(adj);
                }
            }
            vert.setCor("preto");
        }
    }

    public int contarArestasEntreVertices(Vertice inicial, Vertice destino) {
        bfs(inicial);
        return destino.getDistancia() - inicial.getDistancia();
    }

    public List<Vertice> obterVerticesNaDistancia(Vertice inicial, int d) {
        List<Vertice> verticesRetornados = new ArrayList<>();
        bfs(inicial);
        for (Vertice v : grafo.getVertices()) {
            if (v.getDistancia() == d) {
                verticesRetornados.add(v);
            }
        }
        return verticesRetornados;
    }

    public List<ParOrdenado> caminhoEntreVertices(Vertice inicial,Vertice destino) {
        bfs(inicial);
        List<ParOrdenado> lista= new ArrayList<>();
        Vertice aux = destino;
        while (aux != inicial) {
            ParOrdenado par = new ParOrdenado( aux.getPai(), aux);
            aux = aux.getPai();
            lista.add(par);
        }
        if (aux == null) {
            System.out.println("Não existe caminho entre os vértices especificados.");
            return new ArrayList<>();
        }
        Collections.reverse(lista);
        return lista;
    }
}
