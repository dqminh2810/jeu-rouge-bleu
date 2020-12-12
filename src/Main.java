public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.generate(0.25, 0.5, 10);
        graph.print_graph();
    }
}
