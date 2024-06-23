package model;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private List<Vertice> vertices;

    public Graph() {
        this.vertices = new ArrayList<>();
    }

    public void addVertice(Vertice vertice) {
        if (vertice == null) {
            throw new IllegalArgumentException("Vertice não pode ser nulo");
        }
        this.vertices.add(vertice);
    }

    public void addArestaDirecionada(Vertice inicio, Vertice fim) {
        addArestaDirecionada(inicio, fim, 0);
    }

    public void addArestaNaoDirecionada(Vertice inicio, Vertice fim) {
        addArestaNaoDirecionada(inicio, fim, 0);
    }

    public void addArestaDirecionada(Vertice inicio, Vertice fim, int peso) {
        if (!vertices.contains(inicio) || !vertices.contains(fim)) {
            throw new IllegalArgumentException("Ambos os vértices devem estar no grafo.");
        }
        Aresta aresta = new Aresta(inicio, fim);
        aresta.setPeso(peso);
        inicio.getAdjacentes().add(aresta);
    }

    public void addArestaNaoDirecionada(Vertice inicio, Vertice fim, int peso) {
        if (!vertices.contains(inicio) || !vertices.contains(fim)) {
            throw new IllegalArgumentException("Ambos os vértices devem estar no grafo.");
        }
        Aresta aresta1 = new Aresta(inicio, fim);
        aresta1.setPeso(peso);
        inicio.getAdjacentes().add(aresta1);

        Aresta aresta2 = new Aresta(fim, inicio);
        aresta2.setPeso(peso);
        fim.getAdjacentes().add(aresta2);
    }

    public List<Vertice> getVertices() {
        return vertices;
    }


    public void restaurar() {
        for (Vertice vertice : vertices) {
            vertice.setDistancia(Integer.MAX_VALUE);
            vertice.setCor("branco");
            vertice.setPai(null);
            vertice.setTempoInicial(0);
            vertice.setTempoFinal(0);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Vertice vertice : vertices) {
            sb.append(vertice.getNome()).append(" -> ");
            for (Aresta aresta : vertice.getAdjacentes()) {
                sb.append(aresta.getFim().getNome()).append(" (").append(aresta.getPeso()).append("), ");
            }
            if (!vertice.getAdjacentes().isEmpty()) {
                sb.setLength(sb.length() - 2);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public String toStringBFS() {
        StringBuilder sb = new StringBuilder();
        for (Vertice vertice : vertices) {
            sb.append("Vertice: ").append(vertice.getNome())
                    .append(", Cor: ").append(vertice.getCor())
                    .append(", Distancia: ").append(vertice.getDistancia())
                    .append("\n");
        }
        return sb.toString();
    }

    public String toStringDFS() {
        StringBuilder sb = new StringBuilder();
        for (Vertice vertice : vertices) {
            sb.append("Vertice: ").append(vertice.getNome())
                    .append(", Cor: ").append(vertice.getCor())
                    .append(", Tempo inicial e final: ").append(vertice.getTempoInicial()).append("/").append(vertice.getTempoFinal())
                    .append("\n");
        }
        return sb.toString();
    }
}
