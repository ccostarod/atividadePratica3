package questao4;

import questao4.model.Graph;
import questao4.model.Vertice;
import questao4.utils.ParOrdenado;

import java.util.*;

public class BuscaEmLargura {
    private Graph grafo;
    private Vertice inicial;

    public BuscaEmLargura(Graph grafo, Vertice inicial) {
        this.grafo = grafo;
        this.inicial = inicial;
    }
    Queue<Vertice> fila =  new LinkedList<>();
    public void bfs() {
        for (Vertice vertice : this.grafo.getVertices()) {
            if (vertice.getNome().equals(this.inicial.getNome())) {
                vertice.setCor("cinza");
                vertice.setDistancia(0);
                vertice.setPai(null);
                break;
            }
        }
        fila.add(this.inicial);
        while (!fila.isEmpty()) {
            Vertice vert = fila.poll();
            for (Vertice adj : vert.getAdjacentes()) {
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

    public int contarArestasEntreVertices(Vertice destino) {
        bfs();
        return destino.getDistancia() - this.inicial.getDistancia();
    }

    public List<Vertice> obterVerticesNaDistancia(int d) {
        List<Vertice> verticesRetornados = new ArrayList<>();
        bfs();
        for (Vertice v : grafo.getVertices()) {
            if (v.getDistancia() == d) {
                verticesRetornados.add(v);
            }
        }
        return verticesRetornados;
    }

    // melhorar esse method
    public List<ParOrdenado> caminhoEntreVertices(Vertice destino) {
        bfs();
        List<ParOrdenado> lista= new ArrayList<>();
        Vertice aux = destino;
        while (aux != this.inicial) {
            ParOrdenado par = new ParOrdenado( aux.getPai(), aux);
            aux = aux.getPai();
            lista.add(par);
        }
        Collections.reverse(lista);
        return lista;
    }
}
