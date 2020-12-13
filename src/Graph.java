import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Graphe orienté bi-coloré et symétrique complet
 */
public class Graph {
    private ArrayList<Vertex> vertices;

    public Graph(){
        vertices = new ArrayList<>();
    }

    /**
     * Method allows to generate asymmetric graph with arguments given
     * - The graph after generated will be a "Graphe orienté bi-coloré et symétrique complet"
     * @param p : red probability of a vertex
     * @param q : blue probabilty of an arc
     * @param o : entrant direction probabilty of an arc
     * @param n : number of vertex
     */
    public void generate_asymmetric(double p, double q, double o, int n){
        /* generate vertex */
        for(int i = 0; i < n; i++){
            Vertex u = new Vertex(String.valueOf(i), generate_vertex_color(p));
            if(!is_exist_vertex(u)){
                vertices.add(u);
            }
        }
        /* generate arc */
        Random random = new Random();
        for(int i = 0; i < n-1; i++){
            int nb_arc = random.nextInt(n/2);      // Get random number arc of this vertex
            for(int j = 0; j < nb_arc; j++){
                int v = random.nextInt(n);              // Get random vertex link to this vertex
                boolean direction = generate_arc_direction(o);  // Get random arc direction between these two vertices
                if(v != i){
                    if(direction){  // entrant arc
                        vertices.get(i).add_in_vertex(vertices.get(v), generate_arc_color(q));
                    }else{  // outgoing arc
                        vertices.get(i).add_out_vertex(vertices.get(v), generate_arc_color(q));
                    }
                    if(!is_coherent(vertices.get(i), vertices.get(v))) {    // check coherent
                        System.out.println("Something wrong between" + i + " and " + v + "\n");
                        break;
                    }
                }
            }
        }
    }

    /**
     * Method allows to generate full symmetric graph with arguments given
     * - The graph after generated will be a "Graphe orienté bi-coloré et symétrique complet"
     * @param p : red probability of a vertex
     * @param q : blue probabilty of an arc
     * @param n : number of vertex
     */
    public void generate_full_symmetric(double p, double q, int n){
        /* generate vertex */
        for(int i = 0; i < n; i++){
            Vertex u = new Vertex(String.valueOf(i), generate_vertex_color(p));
            if(!is_exist_vertex(u)){
                vertices.add(u);
            }
        }
        /* generate arc */
        for(int i = 0; i < n-1; i++){
            vertices.get(i).add_in_vertex(vertices.get(i+1), generate_arc_color(q));
            vertices.get(i).add_out_vertex(vertices.get(i+1), generate_arc_color(q));
            if(!is_coherent(vertices.get(i), vertices.get(i+1))){
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
        if( new Random().nextDouble() <= q ) {
            return Color.BLUE;
        }else {
            return Color.RED;
        }
    }

    /**
     * Generate random direction arc
     * @param o probability for direction entrant, 1-q for direction outgoing
     * @return true if entrant or false if outgoing
     */
    public boolean generate_arc_direction(double o){
        if( new Random().nextDouble() <= o ) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * Check if this graph contains such vertex
     * @param ve vertex given to check
     * @return true if it does exist, false otherwise
     */
    public boolean is_exist_vertex(Vertex ve){
        for(Vertex v: vertices){
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
        return vertices.size();
    }

    /**
     * Calculate number of arc current in graph
     * @return : number of arc current in graph
     */
    public int get_nb_arc(){
        int nb_arc = 0;
        for(Vertex v: vertices){
            // avoid duplication by divided to 2
            nb_arc+=v.get_nb_arc()/2;
        }
        return nb_arc;
    }

    /**
     * Get all of red vertex current in graph
     * @return : list of red vertex current in graph
     */
    public ArrayList<Vertex> get_red_vertex(){
        ArrayList<Vertex> red_ones = new ArrayList<>();
        for(Vertex v: vertices){
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
        for(Vertex v: vertices){
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
        for(int i = 0; i < vertices.size(); i++){
            System.out.println(vertices.get(i).toString());
        }
    }

    /* ********************** */
    /* ********************** */
    /* * QUESTION 6 METHODS * */
    /* ********************** */
    /* ********************** */
    /**
     * Method allows to resolve the problem "red blue game" by using heuristic algorithm v1
     *  Phase 1: Find RED vertices and change his neighbours color to RED adapting to the arc color
     * - Find RED vertices in graph (1)
     * - Find RED outgoing arcs of RED vertices found above and turn the neighbours color to RED (2)
     *
     *  Phase 2: Delete all RED vertices found with the arc linking to these vertices
     * - Delete arcs which link between the RED vertices found above and his neighbours (3)
     * - Delete RED vertices found (4)
     *
     * - At the end, the graph has left only the BLUE vertices with arc linking between them (5)
     * @return : number of vertices (color of RED) deleted in graph
     */
    public ArrayList<Vertex> resolve_heuristic_v1(){
        ArrayList<Vertex> red_vertices = new ArrayList<>();

        /* Phase 1 */
        for(int i=0; i<vertices.size(); i++){
            /* Step (1) */
            if(vertices.get(i).getColor()==Color.RED){
                boolean existed_i = false;
                for(Vertex v: red_vertices){
                    if(v.getName().equals(vertices.get(i).getName())){
                        existed_i = true;
                        break;
                    }
                }
                if(!existed_i){
                    red_vertices.add(vertices.get(i));  // Add Red vertex to list to be deleted if it does not added
                    System.out.println("Red vertex initial "+vertices.get(i).getName());
                }

                /* Step (2) */
                for(int j=0; j<vertices.size(); j++) {
                    HashMap<Color, Boolean> arcs = vertices.get(i).get_arc_color(vertices.get(j));
                    for (HashMap.Entry<Color, Boolean> entry : arcs.entrySet()) {
                        Color color_arc = entry.getKey();
                        Boolean orientation_arc = entry.getValue();
                        if(!orientation_arc){   // outgoing arc
                            if(color_arc == Color.RED){ // and it color is RED
                                vertices.get(j).setColor(Color.RED);    // turn the neighbour to RED
                                boolean existed_j = false;
                                for(Vertex v: red_vertices){
                                    if(v.getName().equals(vertices.get(j).getName())){
                                        existed_j = true;
                                        break;
                                    }
                                }
                                if(!existed_j){
                                    red_vertices.add(vertices.get(j));      // add to the red vertices list if it does not added
                                    System.out.println("Add vertex after change color to Red "+vertices.get(j).getName());
                                }
                            }
                        }
                    }
                }
            }
        }

        /* Phase 2 */
        /* Step (3) */
        // Delete arcs linking between this red vertex and his neighbours
        for(int i=0; i<red_vertices.size(); i++){
            for(int j=0; j<vertices.size(); j++){
                red_vertices.get(i).remove_arc(vertices.get(j));
                vertices.get(j).remove_arc(red_vertices.get(i));
            }
        }

        /* Step (4) */
        vertices.removeAll(red_vertices);

        /* Step (5)*/
        return red_vertices;
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
