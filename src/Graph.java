import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Graphe orienté bi-coloré et symétrique complet
 */
public class Graph {
    private ArrayList<Vertex> vertexs;

    public Graph(){
        vertexs = new ArrayList<>();
    }

    /**
     * Method allows to generate graph with arguments given
     * - The graph after generated will be a "Graphe orienté bi-coloré et symétrique complet"
     * @param p : red probability of a vertex
     * @param q : blue probabilty of an arc
     * @param n : number of vertex
     */
    public void generate(double p, double q, int n){
        /* generate vertex */
        for(int i = 0; i < n; i++){
            Vertex u = new Vertex(String.valueOf(i), generate_vertex_color(p));
            if(!is_exist_vertex(u)){
                vertexs.add(u);
            }
        }
        /* generate arc */
        for(int i = 0; i < n-1; i++){
            vertexs.get(i).add_in_vertex(vertexs.get(i+1), generate_arc_color(q));
            vertexs.get(i).add_out_vertex(vertexs.get(i+1), generate_arc_color(q));
            if(!is_coherent(vertexs.get(i), vertexs.get(i+1))){
                System.out.println("Something wrong between" + i + " and " + i+1 + "\n");
                break;
            }
        }
    }

    /**
     * Generate random color vertex
     * @param p probability for color of red, 1-p for color of blue
     * @return RED or BLUE as color vertex generated
     */
    public Color generate_vertex_color(double p){
        // Vertex generated
        if( new Random().nextDouble() <= p ) {
            return Color.RED;
        }else {
            return Color.BLUE;
        }
    }

    /**
     * Generate random color arc
     * @param q probability for color of blue, 1-q for color of red
     * @return RED or BLUE as color arc generated
     */
    public Color generate_arc_color(double q){
        // Arc generated
        if( new Random().nextDouble() <= q ) {
            return Color.BLUE;
        }else {
            return Color.RED;
        }
    }

    /**
     * Check if this graph contains such vertex
     * @param ve vertex given to check
     * @return true if it does exist, false otherwise
     */
    public boolean is_exist_vertex(Vertex ve){
        for(Vertex v: vertexs){
            if(v.getName().equals(ve.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Calculate number of vertex current in graph
     * @return : number of vertex current in graph
     */
    public int get_nb_vertex(){
        return vertexs.size();
    }

    /**
     * Calculate number of arc current in graph
     * @return : number of arc current in graph
     */
    public int get_nb_arc(){
        int nb_arc = 0;
        for(Vertex v: vertexs){
            nb_arc+=v.get_nb_arc();
        }
        return nb_arc;
    }

    /**
     * Get all of red vertex current in graph
     * @return : list of red vertex current in graph
     */
    public ArrayList<Vertex> get_red_vertex(){
        ArrayList<Vertex> red_ones = new ArrayList<>();
        for(Vertex v: vertexs){
            if(v.is_red()) red_ones.add(v);
        }
        return red_ones;
    }

    /**
     * Get all of blue vertex current in graph
     * @return : list of blue vertex current in graph
     */
    public ArrayList<Vertex> get_blue_vertex(){
        ArrayList<Vertex> blue_ones = new ArrayList<>();
        for(Vertex v: vertexs){
            if(v.is_blue()) blue_ones.add(v);
        }
        return blue_ones;
    }

    /**
     * Calculate number of red vertex current in graph
     * @return : number of red vertex current in graph
     */
    public int get_nb_red_vertex(){
        return get_red_vertex().size();
    }

    /**
     * Calculate number of blue vertex current in graph
     * @return : number of blue vertex current in graph
     */
    public int get_nb_blue_vertex(){
        return get_blue_vertex().size();
    }

    /**
     * Calculate number of red arc current in graph
     * @return : number of red arc current in graph
     */
    public int get_nb_red_arc(){
        //TODO
        return 0;
    }

    /**
     * Calculate number of blue arc current in graph
     * @return : number of blue arc current in graph
     */
    public int get_nb_blue_arc(){
        //TODO
        return 0;
    }


    /**
     * Check if arcs between v1 and v2 is coherent
     * - entrans of v1 on v2 is outgoings of v2 on v1
     * - outgoings of v1 on v2 is entrans of v2 on v1
     * @param v1 vertex 1
     * @param v2 vertex 2
     * @return true if its coherent false if not
     */
    public boolean is_coherent(Vertex v1, Vertex v2){
        boolean check = false;
        /* - entrans of v1 on v2 is outgoings of v2 on v1 */
        for (HashMap.Entry<Vertex, Color> entry1 : v1.getIns().entrySet()) {
            Vertex vertex_in = entry1.getKey();
            Color color_arc_in = entry1.getValue();
            // if v2 is in entrants list of v1
            if(vertex_in == v2){
                for (HashMap.Entry<Vertex, Color> entry2 : v2.getOuts().entrySet()) {
                    Vertex vertex_out = entry2.getKey();
                    Color color_arc_out = entry2.getValue();
                    // if v1 is in outgoing list of v2
                    if(vertex_out == v1) {
                        check = true;
                        break;
                    }else check = false;
                }
            }
        }

        /*- outgoings of v1 on v2 is entrans of v2 on v1*/
        for (HashMap.Entry<Vertex, Color> entry1 : v1.getOuts().entrySet()) {
            Vertex vertex_out = entry1.getKey();
            Color color_arc_out = entry1.getValue();
            // if v2 is in outgoing list of v1
            if(vertex_out == v2){
                for (HashMap.Entry<Vertex, Color> entry2 : v2.getIns().entrySet()) {
                    Vertex vertex_in = entry2.getKey();
                    Color color_arc_in = entry2.getValue();
                    // if v1 is in entrants list of v2
                    if(vertex_in == v1) {
                        check = true;
                        break;
                    }else check = false;
                }
            }
        }

        return check;
    }

    /**
     * Display graph generated
     */
    public void print_graph(){
        for(int i = 0; i < vertexs.size(); i++){
            System.out.println(vertexs.get(i).toString());
        }
    }

    /* ********************** */
    /* ********************** */
    /* * QUESTION 6 METHODS * */
    /* ********************** */
    /* ********************** */
    /**
     * Method allows to resolve the problem "red blue game" by using heuristic algorithm v1
     * @return : number of arc current in graph
     */
    public int resolve_heuristic_v1(){
        return 0;
        //TODO
    }

    /**
     * Method allows to resolve the problem "red blue game" by using heuristic algorithm v2
     * @return : number of arc current in graph
     */
    public int resolve_heuristic_v2(){
        return 0;
        //TODO
    }

}
