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
                int res = 0;
                for(int i=0; i<100; i++){
                    Graph graph = new Graph();
                    graph.generate_asymmetric(p, q, 0.5, 100);
                    res+=graph.resolve_heuristic_v1().size();
                }
                res/=100;
                System.out.format("f( %.1f ; %.1f) = %s \n", p, q, res);
            }
        }
    }
}
