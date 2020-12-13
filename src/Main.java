import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //Graph graph = new Graph();

        /* SYMMETRIC GRAPH*/
        //graph.generate_full_symmetric(0.25, 0.5, 10);
        //graph.print_graph();

        /* ASYMMETRIC GRAPH*/
        //graph.generate_asymmetric(0.25, 0.5, 0.5, 10);
        //graph.print_graph();

        /* INITIAL GRAPH */
        //System.out.println("*** Before ****\n");
        //graph.print_graph();
        //System.out.println("- Number vertices in this graph: " + graph.get_nb_vertex()+"\n");

        /* AFTER USING HEURISTIC ALGORITHM */
        //System.out.println("*** After resolve heuristic v1 ****\n");
        //System.out.println("- Number red vertices deleted: " + graph.resolve_heuristic_v1().size()+"\n");
        //graph.print_graph();
        //System.out.println("- Number vertices has left: " + graph.get_nb_vertex()+"\n");



        /* *************** */
        /* *************** */
        /* * QUESTION 7  * */
        /* *************** */
        /* *************** */

        for(double p=0; p<1; p+=0.1){
            for(double q=0; q<1; q+=0.1){
                int res_1 = 0;
                //int res_2 = 0;
                for(int i=0; i<100; i++){
                    Graph graph_1 = new Graph();
                    //Graph graph_2 = new Graph();
                    graph_1.generate_asymmetric(p, q, 0.5, 100);
                    //graph_2.setVertices(new ArrayList<>(graph_1.getVertices()));
                    res_1+=graph_1.resolve_heuristic_v1().size();
                    //res_2+=graph_2.resolve_heuristic_v2().size();
                }
                res_1/=100;
                System.out.format("V1 - f( %.1f ; %.1f) = %s \n", p, q, res_1);
                //res_2/=100;
                //System.out.format("V2 - f( %.1f ; %.1f) = %s \n", p, q, res_2);

            }
        }
    }
}
