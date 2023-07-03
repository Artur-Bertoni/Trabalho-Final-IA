package Problema02;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

class GraphColoring {

    private static int VERTEX_NUMBER;
    private static int COLOR_NUMBER;
    private static List<String> COLOR_LIST;
    private static List<Character> VERTEX_NAME_LIST;
    private static GraphColoring GRAPH;
    private final LinkedList<Integer>[] CONNECTION_LIST;
    private int[] result;

    public static void main(String[] args) {

        LOOP:
        do {
            System.out.print("""
                    Escolha a opção desejada:
                    1 - Usar predefinições do exercício (7 vértices previamente conectadas e 3 cores);
                    2 - Informar quantidade e nome de vértices, quantidade e nome de cores e quais vértices se conectam;
                    3 - Encerrar programa.
                    Opção:\s""");

            int option = new Scanner(System.in).nextInt();

            switch (option) {
                case 1 -> {
                    initializeDefault();

                    System.out.println("\nColoração do Grafo:");
                    GRAPH.greedyColoring();

                    System.out.println("\nGrafo:");
                    GRAPH.printGraph();

                    break LOOP;
                }
                case 2 -> {
                    initializeCustom();

                    System.out.println("\nColoração do Grafo:");
                    GRAPH.greedyColoring();

                    System.out.println("\nGrafo:");
                    GRAPH.printGraph();

                    break LOOP;
                }
                case 3 -> {
                    System.out.println("Programa encerrado.");
                    break LOOP;
                }
                default -> System.out.println("Opção inválida! Insira um valor válido!\n");
            }
        } while (true);
    }

    static void initializeDefault() {
        GRAPH = new GraphColoring(7, 3);
        COLOR_LIST = new ArrayList<>(Arrays.asList("Vermelho", "Azul", "Amarelo"));
        VERTEX_NAME_LIST = new ArrayList<>(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G'));

        GRAPH.addConnection('A', 'B');
        GRAPH.addConnection('B', 'C');
        GRAPH.addConnection('B', 'E');
        GRAPH.addConnection('C', 'D');
        GRAPH.addConnection('E', 'C');
        GRAPH.addConnection('E', 'F');
        GRAPH.addConnection('E', 'G');
        GRAPH.addConnection('F', 'G');
    }

    static void initializeCustom() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nVÉRTICES:\nInforme a quantidade de vértices: ");
        VERTEX_NUMBER = scanner.nextInt();

        VERTEX_NAME_LIST = new ArrayList<>();
        COLOR_LIST = new ArrayList<>();

        System.out.println("\nInforme os nomes (caracteres) dos vértices do Grafo:");
        for (int i = 0; i < VERTEX_NUMBER; i++) {
            System.out.print((i + 1) + "º vértice: ");
            VERTEX_NAME_LIST.add(scanner.next().toUpperCase().charAt(0));
        }

        System.out.print("\nCORES:\nInforme a quantidade de cores: ");
        COLOR_NUMBER = scanner.nextInt();

        System.out.println("Informe a lista de cores:");
        for (int i = 0; i < COLOR_NUMBER; i++) {
            System.out.print((i + 1) + "ª cor: ");
            COLOR_LIST.add(scanner.next());
            COLOR_LIST.set(i, COLOR_LIST.get(i).substring(0, 1).toUpperCase().concat(COLOR_LIST.get(i).substring(1)));
        }

        GRAPH = new GraphColoring(VERTEX_NUMBER, COLOR_NUMBER);

        System.out.println("\nCONEXÕES:\nObs.: Não é necessário informar mais de uma vez a mesma conexão, por exemplo, se A se conecta com B, informe apenas q A se conecta com B,\nnão há necessidade de informar também nas conexões de B que ele se conecta com A.\n");

        for (int i = 0; i < VERTEX_NUMBER; i++) {
            int originVertex = i;
            System.out.print("Informe os vértices com que o vértice " + VERTEX_NAME_LIST.get(i) + " se conecta (separados por \", \"), se não houver conexões, insira \"-\": ");
            String answer = scanner.next();

            if (!answer.equals("-"))
                Arrays.stream(answer.split(", "))
                        .toList()
                        .forEach(connectionName ->
                                GRAPH.addConnection(VERTEX_NAME_LIST.get(originVertex), connectionName.toUpperCase().charAt(0)));

            scanner.nextLine();
        }

        scanner.close();
    }

    GraphColoring(int vertexNumber, int colorNumber) {
        VERTEX_NUMBER = vertexNumber;
        COLOR_NUMBER = colorNumber;

        CONNECTION_LIST = new LinkedList[vertexNumber];
        for (int i = 0; i < vertexNumber; ++i)
            CONNECTION_LIST[i] = new LinkedList<>();
    }

    void addConnection(char vertex1, char vertex2) {
        CONNECTION_LIST[VERTEX_NAME_LIST.indexOf(vertex1)].add(VERTEX_NAME_LIST.indexOf(vertex2));
        CONNECTION_LIST[VERTEX_NAME_LIST.indexOf(vertex2)].add(VERTEX_NAME_LIST.indexOf(vertex1));
    }

    void greedyColoring() {
        result = new int[VERTEX_NUMBER];

        Arrays.fill(result, -1);
        result[0] = 0;

        boolean[] available = new boolean[COLOR_NUMBER];
        Arrays.fill(available, true);

        for (int vertex = 1; vertex < VERTEX_NUMBER; vertex++) {
            for (int connectedVertex : CONNECTION_LIST[vertex])
                if (result[connectedVertex] != -1)
                    available[result[connectedVertex]] = false;

            int color;
            for (color = 0; color < COLOR_NUMBER; color++)
                if (available[color])
                    break;

            result[vertex] = color;

            Arrays.fill(available, true);
        }

        try {
            for (int i = 0; i < VERTEX_NUMBER; i++)
                System.out.println("Vértice " + VERTEX_NAME_LIST.get(i) + " --->  " + COLOR_LIST.get(result[i]));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("É impossível continuar colorindo este grafo de forma que as vértices que se conectam possuam entre si cores diferentes");
        }
    }

    void printGraph() {
        // Cria um objeto Graph
        Graph<Character, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

        // Adiciona os vértices ao grafo
        for (char vertex : VERTEX_NAME_LIST) {
            graph.addVertex(vertex);
        }

        // Adiciona as conexões ao grafo
        for (int i = 0; i < VERTEX_NUMBER; i++) {
            char vertex = VERTEX_NAME_LIST.get(i);
            LinkedList<Integer> connections = CONNECTION_LIST[i];
            for (int connectedVertex : connections) {
                char connectedVertexName = VERTEX_NAME_LIST.get(connectedVertex);
                graph.addEdge(vertex, connectedVertexName);
            }
        }

        // Exporta o grafo como arquivo DOT
        exportGraphToDot(graph, "graph.dot");

        System.out.println("Grafo exportado como graph.dot");
    }

    private void exportGraphToDot(Graph<Character, DefaultEdge> graph, String fileName) {
        try (Writer writer = new FileWriter(fileName)) {
            writer.write("graph G {\n");

            // Escreve as definições de cores
            for (int i = 0; i < VERTEX_NUMBER; i++) {
                char vertex = VERTEX_NAME_LIST.get(i);
                String color = COLOR_LIST.get(result[i]);
                writer.write("    " + vertex + " [color=" + color + "];\n");
            }

            // Escreve as arestas
            for (DefaultEdge edge : graph.edgeSet()) {
                char source = graph.getEdgeSource(edge);
                char target = graph.getEdgeTarget(edge);
                writer.write("    " + source + " -- " + target + ";\n");
            }

            writer.write("}\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}