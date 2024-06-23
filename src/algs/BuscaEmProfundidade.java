package algs;

import model.Aresta;
import model.Graph;
import model.Vertice;
import utils.ParOrdenado;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class    BuscaEmProfundidade {
    private int tempo;
    private List<Aresta> arestasTipoRetorno;

    public BuscaEmProfundidade(){
        this.arestasTipoRetorno = new ArrayList<>();
    }

    public void dfs(Graph grafo) {
        for (Vertice vertice : grafo.getVertices()) {
            vertice.setCor("branco");
            vertice.setPai(null);
        }
        this.tempo = 0;
        for (Vertice vertice : grafo.getVertices()) {
            if (vertice.getCor().equals("branco")) {
                dfsVisit(grafo, vertice);
            }
        }
    }

    public void dfsVisit(Graph grafo, Vertice vertice) {
        this.tempo++;
        vertice.setTempoInicial(tempo);
        vertice.setCor("cinza");
        for (Aresta a : vertice.getAdjacentes()) {
            Vertice v = a.getFim();
            if (v.getCor().equals("branco")) {
                v.setPai(vertice);
                dfsVisit(grafo, v);
            }
            else if(v.getCor().equals("cinza")){
                arestasTipoRetorno.add(a);
            }
        }
        vertice.setCor("preto");
        this.tempo++;
        vertice.setTempoFinal(tempo);
    }

    public Integer numeroArestas(Graph graph, Vertice origem, Vertice destino){
        dfs(graph);
        Integer contagem = 0;
        Vertice atual = destino;

        if (!graph.getVertices().contains(origem) || !graph.getVertices().contains(destino)) {
            throw new IllegalArgumentException("Ambos os vértices devem estar no grafo.");
        }
        if (destino.getPai() == null) {
            return -1; // o destino não é alcançável a partir da origem
        }

        while (atual != null && !atual.equals(origem)) {
            atual = atual.getPai(); //buscando o vértice pai até chegar na origem ou em null
            contagem++;
        }

        if (atual == null) {
            return -1; //não encontrou o vértice origem na árvore de profundidade
        }
        return contagem;
    }

    public List<ParOrdenado> caminhoEntreVertices(Graph graph, Vertice origem, Vertice destino){
        dfs(graph);
        Vertice atual = destino;
        List<Vertice> verticesInvertido = new ArrayList<>();
        List<ParOrdenado> caminho = new ArrayList<>();

        if (!graph.getVertices().contains(origem) || !graph.getVertices().contains(destino)) {
            throw new IllegalArgumentException("Ambos os vértices devem estar no grafo.");
        }
        if (destino.getPai() == null) {
            return null; // o destino não é alcançável a partir da origem
        }

        while (atual != null && !atual.equals(origem)) {
            verticesInvertido.add(atual); //adicionando cada vertice a lista começando do vertice destino
            atual = atual.getPai();
        }
        verticesInvertido.add(atual);

        for (int i = verticesInvertido.size() - 1; i > 0; i--) {
            Vertice v1 = verticesInvertido.get(i);
            Vertice v2 = verticesInvertido.get(i - 1);
            caminho.add(new ParOrdenado(v1, v2));
        }

        if (atual == null) {
            return null; //não encontrou o vértice origem na árvore de profundidade
        }
        return caminho;
    }

    public void imprimirArestasTipoRetorno(Graph grafo) {
        dfs(grafo);
        System.out.println("Arestas de retorno:");
        for (Aresta aresta : arestasTipoRetorno) {
            ParOrdenado par = new ParOrdenado(aresta);
            System.out.println(par);
        }
    }
}





