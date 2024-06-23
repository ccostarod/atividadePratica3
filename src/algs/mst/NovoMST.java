package algs.mst;

import model.Aresta;
import model.Graph;
import model.Vertice;

import java.util.ArrayList;
import java.util.List;

public class NovoMST {

    public void mst(Graph graph) {
        List<Aresta> todasArestas = graph.getArestas();
        List<Aresta> arestasRemoviveis = new ArrayList<>();
        List<Aresta> todasArestasRemoviveis = new ArrayList<>();

        // Ordenar as arestas em ordem decrescente
        todasArestas.sort((a1, a2) -> Integer.compare(a2.getPeso(), a1.getPeso()));

        ConjuntoDisjunto ds = new ConjuntoDisjunto(graph.getVertices());

        for (Aresta aresta : todasArestas) {
            Vertice inicio = aresta.getInicio();
            Vertice fim = aresta.getFim();

            // Verificar se a remoção da aresta desconecta o grafo
            if (ds.find(inicio) != ds.find(fim)) {
                arestasRemoviveis.add(aresta);
                ds.union(inicio, fim);
            }

            for(Aresta a: arestasRemoviveis){ //para cada aresta busca a sua inversa para adicionar a uma nova lista que tem todas
                if(aresta.getInicio().equals(a.getFim()) && aresta.getFim().equals(a.getInicio()) && a.getPeso() == aresta.getPeso()){
                    todasArestasRemoviveis.add(aresta);
                }
            }
        }

        todasArestasRemoviveis.addAll(arestasRemoviveis); //adicionando os restos das arestas

        List<Aresta> mstArestas = new ArrayList<>();
        for (Aresta aresta : todasArestas) {
            if (!todasArestasRemoviveis.contains(aresta)) { //se nas arestas removiveis não contem certa aresta, quer dizer que essa aresta é desconecta o grafo
                mstArestas.add(aresta);
            }
        }

        // Imprimir as arestas da MST
        for (Aresta aresta : mstArestas) {
            System.out.println("Aresta: " + aresta.getInicio().getNome() + " - " + aresta.getFim().getNome() + " | Peso: " + aresta.getPeso());
        }
    }
}
