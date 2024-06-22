package questao4.model;

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

    public void addAresta(Vertice inicio, Vertice fim) {
        if (!vertices.contains(inicio) || !vertices.contains(fim)) {
            throw new IllegalArgumentException("Ambos os vértices devem estar no grafo.");
        }
        inicio.getAdjacentes().add(fim);
    }

    public List<Vertice> getVertices() {
        return vertices;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Vertice vertice : vertices) {
            sb.append("Vertice: ").append(vertice.getNome())
                    .append(", Cor: ").append(vertice.getCor())
                    .append(", Distancia: ").append(vertice.getDistancia())
                    .append("\n");
        }
        return sb.toString();
    }
}
