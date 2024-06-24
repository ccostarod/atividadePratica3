import algs.BuscaEmLargura;
import algs.BuscaEmProfundidade;
import algs.Dijkstra;
import algs.mst.NovoMST;
import algs.mst.Prim;
import model.Aresta;
import model.Graph;
import model.Vertice;
import utils.ParOrdenado;

import java.util.List;
import java.util.Scanner;

import static java.util.Locale.filter;

public class Main {
    public static void main(String[] args) {
        Graph grafo = new Graph();
        BuscaEmLargura bfs = new BuscaEmLargura(grafo);
        BuscaEmProfundidade dfs = new BuscaEmProfundidade();
        Dijkstra dijkstra = new Dijkstra();
        NovoMST novoMST = new NovoMST();
        Prim prim = new Prim();

        Scanner scanner = new Scanner(System.in);
        int opcao = 0;


        while (opcao != 7) {
            System.out.println("Menu:");
            System.out.println("1 - Criar grafo pré-definido");
            System.out.println("2 - Mostrar grafo");
            System.out.println("3 - DFS - Questão 1");
            System.out.println("4 - MST - Questão 2");
            System.out.println("5 - Dijkstra - Questão 3");
            System.out.println("6 - BFS - Questão 4");
            System.out.println("7 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();


            switch (opcao) {
                case 1:
                    System.out.println("Criando grafo pré-definido...");
                    grafo = criarGrafoPreDefinido();
                    bfs = new BuscaEmLargura(grafo);
                    System.out.println("Grafo pré-definido criado.");
                    break;
                case 2:
                    System.out.println(grafo);
                    break;
                case 3:
                    System.out.println("Questão 1 - DFS");
                    int opcaoDFS = 0;
                    Vertice origemDFS = null;

                    while (opcaoDFS != 5) {
                        System.out.println("Menu DFS:");
                        System.out.println("1 - Retornar número de arestas entre origem e destino");
                        System.out.println("2 - Retornar caminho entre vértice de origem e destino");
                        System.out.println("3 - Imprimir todas as arestas de tipo retorno");
                        System.out.println("4 - Imprimir grafo com DFS");
                        System.out.println("5 - Sair");
                        System.out.print("Escolha uma opção: ");
                        opcaoDFS = scanner.nextInt();

                        switch (opcaoDFS) {
                            case 1:
                                System.out.print("Escolha o vértice de origem: ");
                                String origemNome = scanner.next();
                                origemDFS = grafo.getVertices().stream()
                                        .filter(v -> v.getNome().equals(origemNome))
                                        .findFirst()
                                        .orElse(null);

                                if (origemDFS == null) {
                                    System.out.println("Vértice de origem não encontrado. Tente novamente.");
                                }
                                System.out.print("Escolha o vértice de destino: ");
                                String destinoNomeDFS = scanner.next();
                                Vertice destinoDFS = grafo.getVertices().stream()
                                        .filter(v -> v.getNome().equals(destinoNomeDFS))
                                        .findFirst()
                                        .orElse(null);

                                if (destinoDFS == null) {
                                    System.out.println("Vértice de destino não encontrado. Tente novamente.");
                                    break;
                                }

                                System.out.println("Número de arestas até o destino: " + dfs.numeroArestas(grafo, origemDFS, destinoDFS));
                                break;
                            case 2:
                                System.out.print("Escolha o vértice de origem: ");
                                origemNome = scanner.next();
                                origemDFS = grafo.getVertices().stream()
                                        .filter(v -> v.getNome().equals(origemNome))
                                        .findFirst()
                                        .orElse(null);

                                if (origemDFS == null) {
                                    System.out.println("Vértice de origem não encontrado. Tente novamente.");
                                }
                                System.out.print("Escolha o vértice de destino: ");
                                String destinoCaminhoNome = scanner.next();
                                Vertice destinoCaminho = grafo.getVertices().stream()
                                        .filter(v -> v.getNome().equals(destinoCaminhoNome))
                                        .findFirst()
                                        .orElse(null);

                                if (destinoCaminho == null) {
                                    System.out.println("Vértice de destino não encontrado. Tente novamente.");
                                    break;
                                }

                                System.out.println("Caminho até o destino: " + dfs.caminhoEntreVertices(grafo, origemDFS, destinoCaminho));
                                break;
                            case 3:
                                dfs.imprimirArestasTipoRetorno(grafo);
                                break;
                            case 4:
                                System.out.println(grafo.toStringDFS());
                                break;
                            case 6:
                                grafo.restaurar();
                                System.out.println("Saindo do menu DFS...");
                                break;
                            default:
                                System.out.println("Opção inválida. Tente novamente.");
                                break;
                        }
                    }
                    break;
                case 4:
                    int opcaoMST = 0;
                    while (opcaoMST != 3) {
                        System.out.println("Menu MST:");
                        System.out.println("1 - Gerar novo MST");
                        System.out.println("2 - Gerar PRIM");
                        System.out.println("3 - Sair");
                        System.out.print("Escolha uma opção: ");
                        opcaoMST = scanner.nextInt();
                        switch (opcaoMST) {
                            case 1:
                                long inicio = System.currentTimeMillis();
                                List<Aresta> mstArestas = novoMST.mst(grafo);
                                long fim = System.currentTimeMillis();

                                long tempo = fim - inicio;

                                for (Aresta aresta : mstArestas) {
                                    System.out.println("Aresta: " + aresta.getInicio().getNome() + " - " + aresta.getFim().getNome() + " | Peso: " + aresta.getPeso());
                                }
                                System.out.println("Tempo de execução: " + tempo + "ms");
                                break;
                            case 2:
                                System.out.println("Por qual vertice deseja começar: ");
                                String verticeNome = scanner.next();
                                Vertice vertice = grafo.getVertices().stream()
                                        .filter(v -> v.getNome().equals(verticeNome))
                                        .findFirst()
                                        .orElse(null);
                                inicio = System.currentTimeMillis();
                                mstArestas = prim.primMST(grafo, vertice);
                                fim = System.currentTimeMillis();

                                tempo = fim - inicio;

                                for (Aresta aresta : mstArestas) {
                                    System.out.println("Aresta: " + aresta.getInicio().getNome() + " - " + aresta.getFim().getNome() + " | Peso: " + aresta.getPeso());
                                }
                                System.out.println("Tempo de execução: " + tempo + "ms");
                                break;
                            case 3:
                                grafo.restaurar();
                                System.out.println("Saindo do menu MST...");
                                break;
                            default:
                                System.out.println("Opção inválida. Tente novamente.");
                                break;
                        }
                    }
                    break;
                case 5:
                    System.out.println("Questão 3 - Dijkstra modificado");
                    System.out.print("Escolha o vértice inicial: ");
                    String inicialNomeVertice = scanner.next();
                    Vertice inicial = grafo.getVertices().stream()
                            .filter(v -> v.getNome().equals(inicialNomeVertice))
                            .findFirst()
                            .orElse(null);

                    if (inicial == null) {
                        System.out.println("Vértice inicial não encontrado. Tente novamente.");
                        break;
                    }

                    System.out.print("Escolha o vértice de destino: ");
                    String destinoNome = scanner.next();
                    Vertice destino = grafo.getVertices().stream()
                            .filter(v -> v.getNome().equals(destinoNome))
                            .findFirst()
                            .orElse(null);

                    if (destino == null) {
                        System.out.println("Vértice de destino não encontrado. Tente novamente.");
                        break;
                    }

                    dijkstra.dijkstra(grafo, inicial);
                    List<ParOrdenado> caminhoMinimo = dijkstra.getCaminhoMinimo(destino);
                    System.out.println("Caminho mínimo: " + caminhoMinimo);

                    List<ParOrdenado> segundoCaminhoMinimo = dijkstra.getSegundoCaminhoMinimo(grafo, inicial, destino);
                    if (segundoCaminhoMinimo == null) {
                        System.out.println("Não existe um segundo caminho mínimo.");
                    } else {
                        System.out.println("Segundo caminho mínimo: " + segundoCaminhoMinimo);
                    }
                    grafo.restaurar();
                    break;
                case 6:
                    System.out.println("Questão 4 - BFS");
                    int opcaoBFS = 0;
                    Vertice verticeInicial = null;


                    while (opcaoBFS != 6) {
                        System.out.println("Menu BFS:");
                        System.out.println("1 - Iniciar BFS");
                        System.out.println("2 - Número de arestas até um destino");
                        System.out.println("3 - Caminho até um destino");
                        System.out.println("4 - Vértices a uma dada distância");
                        System.out.println("5 - Imprimir grafo com BFS");
                        System.out.println("6 - Sair");
                        System.out.print("Escolha uma opção: ");
                        opcaoBFS = scanner.nextInt();

                        switch (opcaoBFS) {
                            case 1:
                                System.out.print("Digite o vertice inicial: ");
                                String inicialNome = scanner.next();
                                verticeInicial = grafo.getVertices().stream()
                                        .filter(v -> v.getNome().equals(inicialNome))
                                        .findFirst()
                                        .orElse(null);

                                if (verticeInicial == null) {
                                    System.out.println("Vértice inicial não encontrado. Tente novamente.");
                                } else {
                                    bfs.bfs(verticeInicial);
                                }
                                break;
                            case 2:
                                if (verticeInicial == null) {
                                    System.out.println("Por favor, escolha primeiro o vértice inicial.");
                                    break;
                                }
                                System.out.print("Digite o vértice de destino: ");
                                String destinoIndex = scanner.next();
                                Vertice destinoVert = grafo.getVertices().stream()
                                        .filter(v -> v.getNome().equals(destinoIndex))
                                        .findFirst()
                                        .orElse(null);
                                if (destinoVert == null) {
                                    System.out.println("Vértice de destino não encontrado. Tente novamente.");
                                    break;
                                }
                                else {
                                    System.out.println("Número de arestas até o destino: " + bfs.contarArestasEntreVertices(verticeInicial, destinoVert));
                                }
                                break;
                            case 3:
                                if (verticeInicial == null) {
                                    System.out.println("Por favor, escolha primeiro o vértice inicial.");
                                    break;
                                }
                                System.out.print("Digite o vértice de destino: ");
                                String index = scanner.next();
                                Vertice destinoCaminho = grafo.getVertices().stream()
                                        .filter(v -> v.getNome().equals(index))
                                        .findFirst()
                                        .orElse(null);
                                if (destinoCaminho == null) {
                                    System.out.println("Vértice de destino não encontrado. Tente novamente.");
                                    break;
                                }
                                System.out.println("Caminho até o destino: " + bfs.caminhoEntreVertices(verticeInicial, destinoCaminho));
                                break;
                            case 4:
                                if (verticeInicial == null) {
                                    System.out.println("Por favor, escolha primeiro o vértice inicial.");
                                    break;
                                }
                                System.out.print("Digite a distância: ");
                                int distancia = scanner.nextInt();
                                System.out.println("Vértices a uma dada distância: " + bfs.obterVerticesNaDistancia(verticeInicial, distancia));
                                break;
                            case 5:
                                System.out.println(grafo.toStringBFS());
                                break;
                            case 6:
                                System.out.println("Saindo do menu BFS...");
                                break;
                            default:
                                System.out.println("Opção inválida. Tente novamente.");
                                break;
                        }
                    }
                    break;
                case 7:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }

        scanner.close();
    }
    // Teste de grafo para questão 4 - BFS
    private static Graph criarGrafoPreDefinido() {
        Graph grafo = new Graph();

        // Criação dos vértices
        Vertice v0 = new Vertice("A");
        Vertice v1 = new Vertice("B");
        Vertice v2 = new Vertice("C");
        Vertice v3 = new Vertice("D");
        Vertice v4 = new Vertice("E");
        Vertice v5 = new Vertice("F");
        Vertice v6 = new Vertice("G");

        // Adição dos vértices ao grafo

        grafo.addVertice(v0);
        grafo.addVertice(v1);
        grafo.addVertice(v2);
        grafo.addVertice(v3);
        grafo.addVertice(v4);
        grafo.addVertice(v5);
        grafo.addVertice(v6);

        // Adição das arestas não direcionadas com os pesos
        grafo.addArestaNaoDirecionada(v0, v1, 2);
        grafo.addArestaNaoDirecionada(v0, v2, 6);
        grafo.addArestaNaoDirecionada(v1, v3, 5);
        grafo.addArestaNaoDirecionada(v2, v3, 8);
        grafo.addArestaNaoDirecionada(v3, v4, 10);
        grafo.addArestaNaoDirecionada(v3, v5, 15);
        grafo.addArestaNaoDirecionada(v4, v5, 6);
        grafo.addArestaNaoDirecionada(v4, v6, 2);
        grafo.addArestaNaoDirecionada(v5, v6, 6);


        return grafo;
    }

}
