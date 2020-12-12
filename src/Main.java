import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
//        Graph graph = new Graph();
//        graph.generate(0.25, 0.5, 10);
//        graph.print_graph();
//        System.out.println("- Number arc in this graph: "+graph.get_nb_arc()+"\n");
//        System.out.println("- Number arc in this graph after resolve heuristic v1: "+graph.resolve_heuristic_v1()+"\n");

        Vertex v1 = new Vertex("1", Color.RED);
        Vertex v2 = new Vertex("2", Color.BLUE);
        Vertex v3 = new Vertex("3", Color.BLUE);
        Vertex v4 = new Vertex("3", Color.RED);

        v1.add_out_vertex(v2, Color.RED);
        v1.add_in_vertex(v3, Color.BLUE);

        v2.add_out_vertex(v3, Color.RED);
        v2.add_out_vertex(v4, Color.BLUE);
        v2.add_in_vertex(v1, Color.RED);
        v2.add_in_vertex(v4, Color.BLUE);

        v3.add_out_vertex(v1, Color.BLUE);
        v3.add_in_vertex(v2, Color.RED);

        v4.add_out_vertex(v2, Color.BLUE);
        v4.add_in_vertex(v2, Color.BLUE);

        ArrayList<Vertex> vertices  = new ArrayList<>();
        vertices.add(v1);
        vertices.add(v2);
        vertices.add(v3);
        vertices.add(v4);

        Graph graph = new Graph(vertices);
        graph.print_graph();
        System.out.println("- Number red vertex in this graph: "+graph.get_nb_red_vertex()+ " of "+ graph.get_nb_vertex()+ " in total" + "\n");
        int k = graph.resolve_heuristic_v2();
        System.out.println("- Number red vertex in this graph: "+graph.get_nb_red_vertex()+ " of "+ graph.get_nb_vertex()+ " in total" + "\n");
        System.out.println("Number of red vertex removed: " + k +"\n");

//        System.out.println("Score of v1:" + v1.getScore());
//        System.out.println("Score of v4:" + v4.getScore());

    }
}
