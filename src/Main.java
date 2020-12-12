public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.generate(0.25, 0.5, 10);
        graph.print_graph();
        System.out.println("- Number arc in this graph: "+graph.get_nb_arc()+"\n");
        System.out.println("- Number arc in this graph after resolve heuristic v1: "+graph.resolve_heuristic_v1()+"\n");
    }
}
