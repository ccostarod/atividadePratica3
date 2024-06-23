import algs.BuscaEmLargura;
import algs.BuscaEmProfundidade;
import algs.Dijkstra;
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

        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        while (opcao != 8) {
            System.out.println("Menu:");
            System.out.println("1 - Criar vertices");
            System.out.println("2 - Criar arestas entre vertices");
            System.out.println("3 - Mostrar grafo");
            System.out.println("4 - BFS - Questão 4");
            System.out.println("5 - DFS - Questão 1");
            System.out.println("6 - Dijkstra - Questão 3");
            System.out.println("7 - Criar grafo pré-definido");
            System.out.println("8 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    String nomeVertice;
                    System.out.println("Digite o nome do vértice ou -1 para parar:");
                    while (!(nomeVertice = scanner.next()).equals("-1")) {
                        boolean existe = false;
                        for (Vertice v : grafo.getVertices()) {
                            if (v.getNome().equals(nomeVertice)) {
                                existe = true;
                                break;
                            }
                        }
                        if (existe) {
                            System.out.println("Já existe um vértice com esse nome. Por favor, insira um nome diferente.");
                        } else {
                            Vertice novoVertice = new Vertice(nomeVertice);
                            grafo.addVertice(novoVertice);
                            System.out.println("Vertice " + nomeVertice + " adicionado. Digite o nome do próximo vértice ou -1 para parar:");
                        }
                    }
                    break;
                case 2:
                    if (grafo.getVertices().isEmpty()) {
                        System.out.println("Não há vértices no grafo. Adicione alguns vértices primeiro.");
                        break;
                    }

                    System.out.println("Vértices existentes:");
                    for (int i = 0; i < grafo.getVertices().size(); i++) {
                        System.out.println((i + 1) + " - " + grafo.getVertices().get(i).getNome());
                    }
                    String continuar = "s";
                    while (continuar.equalsIgnoreCase("s")) {
                        System.out.print("Escolha o nome do vértice de início: ");
                        String inicioNome = scanner.next();
                        Vertice inicio = grafo.getVertices().stream()
                            .filter(v -> v.getNome().equals(inicioNome))
                            .findFirst()
                            .orElse(null);

                        if (inicio == null) {
                            System.out.println("Vértice de início não encontrado. Tente novamente.");
                            continue;
                        }

                        System.out.print("Escolha o nome do vértice de fim: ");
                        String fimNome = scanner.next();
                        Vertice fim = grafo.getVertices().stream()
                            .filter(v -> v.getNome().equals(fimNome))
                            .findFirst()
                            .orElse(null);

                        if (fim == null) {
                            System.out.println("Vértice de fim não encontrado. Tente novamente.");
                            continue;
                        }

                        System.out.print("Digite o peso da aresta (0 caso não tenha): ");
                        int peso = scanner.nextInt();

                        System.out.println("Escolha o tipo de aresta:");
                        System.out.println("1 - Direcionada");
                        System.out.println("2 - Não direcionada");
                        int tipoAresta = scanner.nextInt();

                        if (tipoAresta == 1) {
                            grafo.addArestaDirecionada(inicio, fim, peso);
                            System.out.println("Aresta direcionada adicionada.");
                        } else if (tipoAresta == 2) {
                            grafo.addArestaNaoDirecionada(inicio, fim, peso);
                            System.out.println("Aresta não direcionada adicionada.");
                        } else {
                            System.out.println("Opção inválida. Tente novamente.");
                        }

                        System.out.print("Deseja adicionar outra aresta? (s/n): ");
                        continuar = scanner.next();
                    }
                    break;
                case 3:
                    System.out.println(grafo);
                    break;
                case 4:
                    int opcaoBFS = 0;
                    Vertice verticeInicial = null;

                    System.out.println("Vértices existentes:");
                    for (int i = 0; i < grafo.getVertices().size(); i++) {
                        System.out.println((i + 1) + " - " + grafo.getVertices().get(i).getNome());
                    }

                    while (opcaoBFS != 7) {
                        System.out.println("Menu BFS:");
                        System.out.println("1 - Escolher vértice inicial");
                        System.out.println("2 - Número de arestas até um destino");
                        System.out.println("3 - Caminho até um destino");
                        System.out.println("4 - Vértices a uma dada distância");
                        System.out.println("5 - Alterar vértice inicial");
                        System.out.println("6 - Sair");
                        System.out.print("Escolha uma opção: ");
                        opcaoBFS = scanner.nextInt();

                        switch (opcaoBFS) {
                            case 1:
                                System.out.print("Escolha o nome do vértice inicial: ");
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
                                System.out.print("Escolha o nome do vértice de destino: ");
                                String destinoIndex = scanner.next();
                                Vertice destino = grafo.getVertices().stream()
                                        .filter(v -> v.getNome().equals(destinoIndex))
                                        .findFirst()
                                        .orElse(null);
                                if (destino == null) {
                                    System.out.println("Vértice de destino não encontrado. Tente novamente.");
                                    break;
                                }
                                else {
                                    System.out.println("Número de arestas até o destino: " + bfs.contarArestasEntreVertices(verticeInicial, destino));
                                }
                                break;
                            case 3:
                                if (verticeInicial == null) {
                                    System.out.println("Por favor, escolha primeiro o vértice inicial.");
                                    break;
                                }
                                System.out.print("Escolha o nome do vértice de destino: ");
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
                                grafo.restaurar();
                                System.out.print("Escolha o nome do vértice inicial: ");
                                String inicial = scanner.next();
                                verticeInicial = grafo.getVertices().stream()
                                        .filter(v -> v.getNome().equals(inicial))
                                        .findFirst()
                                        .orElse(null);

                                if (verticeInicial == null) {
                                    System.out.println("Vértice inicial não encontrado. Tente novamente.");
                                    break;
                                }
                                bfs.bfs(verticeInicial);
                                break;
                            case 6:
                                grafo.restaurar();
                                System.out.println("Saindo do menu BFS...");
                                break;
                            default:
                                System.out.println("Opção inválida. Tente novamente.");
                                break;
                        }
                    }
                    break;
                case 5:
                    // Código para executar DFS
                    break;
                case 6:
                    System.out.print("Escolha o nome do vértice inicial: ");
                    String inicialNome = scanner.next();
                    Vertice inicial = grafo.getVertices().stream()
                            .filter(v -> v.getNome().equals(inicialNome))
                            .findFirst()
                            .orElse(null);

                    if (inicial == null) {
                        System.out.println("Vértice inicial não encontrado. Tente novamente.");
                        break;
                    }

                    System.out.print("Escolha o nome do vértice de destino: ");
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
                case 7:
                    System.out.println("Criando grafo pré-definido...");
                    grafo = criarGrafoPreDefinido();
                    bfs = new BuscaEmLargura(grafo);
                    System.out.println("Grafo pré-definido criado.");
                    break;
                case 8:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }

        scanner.close();
    }

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