package algs.mst;
import model.Vertice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConjuntoDisjunto {
    private Map<Vertice, Vertice> pai;
    private Map<Vertice, Integer> altura; //usado para ajudar a manter as árvores formadas durante as uniões o mais balanceadas possível.

    public ConjuntoDisjunto(List<Vertice> vertices) {
        pai = new HashMap<>(); //mapeia cada vértice ao seu "pai"
        altura = new HashMap<>(); //mapeia cada vértice a um inteiro representando a "altura" da árvore de subconjuntos na estrutura de conjuntos disjuntos
        for (Vertice vertice : vertices) {
            pai.put(vertice, vertice); //Define o vértice como seu próprio pai no início
            altura.put(vertice, 0); //todos comecam com 0
        }
    }

    public Vertice find(Vertice v) {
        //Se v não é seu próprio pai, o método recursivamente encontra o pai de v e aplica compressão de caminho (atualizando o pai de v para ser a raiz diretamente).
        if (!pai.get(v).equals(v)) {
            pai.put(v, find(pai.get(v)));
        }
        return pai.get(v);
    }

    public void union(Vertice v1, Vertice v2) {
        Vertice raiz1 = find(v1);
        Vertice raiz2 = find(v2);

        if (!raiz1.equals(raiz2)) { //Verificar se v1 e v2 já estão no mesmo conjunto
            if (altura.get(raiz1) > altura.get(raiz2)) { //árvore com root2 como raiz se torna uma subárvore da árvore com root1 como raiz.
                pai.put(raiz2, raiz1);
            } else if (altura.get(raiz1) < altura.get(raiz2)) { //a árvore com root1 como raiz se torna uma subárvore da árvore com root2 como raiz.
                pai.put(raiz1, raiz2);
            } else { // uma das árvores é arbitrariamente escolhida para ser a raiz
                pai.put(raiz2, raiz1);
                altura.put(raiz1, altura.get(raiz1) + 1); //Neste caso, root2 é unido sob root1 e o rank de root1 é incrementado.
            }
        }
    }
}
