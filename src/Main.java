public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.generate(0.25, 0.5, 10);
        //graph.print_graph();
        System.out.println("*** Before ****\n");
        //graph.print_graph();
        System.out.println("- Number vertices in this graph: " + graph.get_nb_vertex()+"\n");

        System.out.println("*** After resolve heuristic v1 ****\n");
        System.out.println("- Number red vertices deleted: " + graph.resolve_heuristic_v1()+"\n");
        //graph.print_graph();
        System.out.println("- Number vertices has left: " + graph.get_nb_vertex()+"\n");

    }
}
