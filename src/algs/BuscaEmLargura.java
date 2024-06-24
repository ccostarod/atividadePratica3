package algs;

import model.Aresta;
import model.Graph;
import model.Vertice;
import utils.ParOrdenado;

import java.util.*;

public class BuscaEmLargura {
    private Graph grafo; // O grafo em que a busca será realizada

    // Construtor que recebe o grafo
    public BuscaEmLargura(Graph grafo) {
        this.grafo = grafo;
    }

    Queue<Vertice> fila =  new LinkedList<>(); // Fila usada para a busca em largura

    // Método que realiza a busca em largura a partir de um vértice inicial
    public void bfs(Vertice inicial) {
        // Inicializa todos os vértices como não visitados (cor branca) e com distância infinita
        for (Vertice vertice : this.grafo.getVertices()) {
            vertice.setCor("branco");
            vertice.setDistancia(Integer.MAX_VALUE);
            vertice.setPai(null);
        }
        // Marca o vértice inicial como visitado (cor cinza) e com distância 0
        inicial.setCor("cinza");
        inicial.setDistancia(0);

        // Adiciona o vértice inicial na fila
        fila.add(inicial);

        // Enquanto a fila não estiver vazia
        while (!fila.isEmpty()) {
            // Remove o próximo vértice da fila
            Vertice vert = fila.poll();

            // Para cada vértice adjacente ao vértice removido
            for (Aresta aresta : vert.getAdjacentes()) {
                Vertice adj = aresta.getFim();

                // Se o vértice adjacente não foi visitado
                if (adj.getCor().equals("branco")) {
                    // Marca o vértice adjacente como visitado e atualiza sua distância e pai
                    adj.setCor("cinza");
                    adj.setDistancia(vert.getDistancia() + 1);
                    adj.setPai(vert);

                    // Adiciona o vértice adjacente na fila
                    fila.add(adj);
                }
            }
            // Marca o vértice como completamente explorado (cor preto)
            vert.setCor("preto");
        }
    }

    // Método que retorna o número de arestas entre dois vértices
    public int contarArestasEntreVertices(Vertice inicial, Vertice destino) {
        bfs(inicial); // Realiza a busca em largura a partir do vértice inicial
        return destino.getDistancia() - inicial.getDistancia(); // Retorna a diferença de distâncias
    }

    // Método que retorna todos os vértices que estão a uma certa distância de um vértice inicial
    public List<Vertice> obterVerticesNaDistancia(Vertice inicial, int d) {
        List<Vertice> verticesRetornados = new ArrayList<>();
        bfs(inicial); // Realiza a busca em largura a partir do vértice inicial

        // Para cada vértice do grafo
        for (Vertice v : grafo.getVertices()) {
            // Se a distância do vértice é igual à distância d
            if (v.getDistancia() == d) {
                // Adiciona o vértice na lista de vértices retornados
                verticesRetornados.add(v);
            }
        }
        return verticesRetornados; // Retorna a lista de vértices
    }

    // Método que retorna o caminho entre dois vértices
    public List<ParOrdenado> caminhoEntreVertices(Vertice inicial,Vertice destino) {
        bfs(inicial); // Realiza a busca em largura a partir do vértice inicial
        List<ParOrdenado> lista= new ArrayList<>();
        Vertice aux = destino;

        // Enquanto não chegar no vértice inicial
        while (aux != inicial) {
            // Adiciona o par (pai, vértice) na lista
            ParOrdenado par = new ParOrdenado( aux.getPai(), aux);
            aux = aux.getPai();
            lista.add(par);
        }

        // Se não existe caminho entre os vértices especificados
        if (aux == null) {
            System.out.println("Não existe caminho entre os vértices especificados.");
            return new ArrayList<>();
        }

        // Inverte a lista para que o caminho esteja na ordem correta
        Collections.reverse(lista);
        return lista; // Retorna a lista de pares ordenados que representam o caminho
    }
}