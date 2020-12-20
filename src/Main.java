import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        /* *************** */
        /* *************** */
        /* * QUESTION 6  * */
        /* *************** */
        /* *************** */

        /*
        Graph graph = new Graph();
        graph.generate_asymmetric(0.25, 0.5, 0.5, 10);

        Graph graph_copy = (Graph) deepCopy(graph);

        System.out.println("ALGO 1 ********************************************************");

        System.out.println("- Number red vertex in this graph: "+graph.get_nb_red_vertex()+ " of "+ graph.get_nb_vertex()+ " in total" + "\n");
        int k1 = graph.resolve_heuristic_v1().size();
        System.out.println("- Number red vertex in this graph: "+graph.get_nb_red_vertex()+ " of "+ graph.get_nb_vertex()+ " in total" + "\n");
        System.out.println("Number of red vertex removed: " + k1 +"\n");

        System.out.println("ALGO 2 ********************************************************");

        System.out.println("- Number red vertex in this graph: "+graph_copy.get_nb_red_vertex()+ " of "+ graph_copy.get_nb_vertex()+ " in total" + "\n");
        int k2 = graph_copy.resolve_heuristic_v2();
        System.out.println("- Number red vertex in this graph: "+graph_copy.get_nb_red_vertex()+ " of "+ graph_copy.get_nb_vertex()+ " in total" + "\n");
        System.out.println("Number of red vertex removed: " + k2 +"\n");
        */



        /* *************** */
        /* *************** */
        /* * QUESTION 7  * */
        /* *************** */
        /* *************** */


        for(double p=0; p<1; p+=0.1){
            for(double q=0; q<1; q+=0.1){
                int res_1 = 0;
                int res_2 = 0;
                for(int i=0; i<100; i++){
                    Graph graph_1 = new Graph();
                    graph_1.generate_full_symmetric(p, q, 100);
                    //graph_2.setVertices(new ArrayList<>(graph_1.getVertices()));
                    Graph graph_2 = (Graph) deepCopy(graph_1);
                    res_1+=graph_1.resolve_heuristic_v1().size();
                    res_2+=graph_2.resolve_heuristic_v2();
                }
                res_1/=100;
                System.out.format("V1 - f( %.1f ; %.1f) = %s \n", p, q, res_1);
                res_2/=100;
                System.out.format("V2 - f( %.1f ; %.1f) = %s \n", p, q, res_2);
            }
        }

    }

    private static Object deepCopy(Object object) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream outputStrm = new ObjectOutputStream(outputStream);
            outputStrm.writeObject(object);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            ObjectInputStream objInputStream = new ObjectInputStream(inputStream);
            return objInputStream.readObject();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
