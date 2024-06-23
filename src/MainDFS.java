import algs.BuscaEmProfundidade;
import model.Graph;
import model.Vertice;

import java.util.List;

public class MainDFS {
    public static void main(String[] args) {
        // Criando o grafo
        Graph grafo = new Graph();

        // Criando os vértices
        Vertice a = new Vertice("A");
        Vertice b = new Vertice("B");
        Vertice c = new Vertice("C");
        Vertice d = new Vertice("D");
        Vertice e = new Vertice("E");
        Vertice f = new Vertice("F");
        Vertice g = new Vertice("G");
        Vertice h = new Vertice("H");
        Vertice i = new Vertice("I");
        Vertice j = new Vertice("J");
        Vertice k = new Vertice("K");
        Vertice l = new Vertice("L");
        Vertice m = new Vertice("M");

        // Adicionando os vértices ao grafo
        grafo.addVertice(a);
        grafo.addVertice(b);
        grafo.addVertice(c);
        grafo.addVertice(d);
        grafo.addVertice(e);
        grafo.addVertice(f);
        grafo.addVertice(g);
        grafo.addVertice(h);
        grafo.addVertice(i);
        grafo.addVertice(j);
        grafo.addVertice(k);
        grafo.addVertice(l);
        grafo.addVertice(m);

        // Adicionando as arestas direcionadas conforme o grafo fornecido
        grafo.addArestaDirecionada(a, g);
        grafo.addArestaDirecionada(a, j);
        grafo.addArestaDirecionada(b, i);
        grafo.addArestaDirecionada(c, e);
        grafo.addArestaDirecionada(c, j);
        grafo.addArestaDirecionada(d, c);
        grafo.addArestaDirecionada(e, b);
        grafo.addArestaDirecionada(g, f);
        grafo.addArestaDirecionada(h, m);
        grafo.addArestaDirecionada(i, k);
        grafo.addArestaDirecionada(j, a);
        grafo.addArestaDirecionada(k, h);
        grafo.addArestaDirecionada(k, m);
        grafo.addArestaDirecionada(g, a);
        grafo.addArestaDirecionada(f, l);
        grafo.addArestaDirecionada(l, f);

        // Executando a busca em profundidade
        BuscaEmProfundidade dfs = new BuscaEmProfundidade();
        //dfs.dfs(grafo);

        // Imprimindo os resultados da busca em profundidade
//        int numArestas = dfs.numeroArestas(grafo, k, m);
//        System.out.println(grafo.toStringDFS());
//        System.out.println();
//        System.out.println(numArestas);

//        List<Vertice> caminho = dfs.caminhoEntreVertices(grafo, b, m);
//        for(Vertice v: caminho){
//            System.out.print(v + " -> ");
//        }

        dfs.imprimirArestasTipoRetorno(grafo);
    }
}
