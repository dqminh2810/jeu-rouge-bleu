import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Vertex in oriented graph bi-coloré
 */
public class Vertex {
    private String name;    // vertex name
    private Color color;  // vertex color which is red or blue
    private HashMap<Vertex, Color> ins;   // entrants of this vertex which key is entrant vertex & value is arc color
    private HashMap<Vertex, Color> outs;  // outgoings of this vertex which key is outgoing vertex & value is arc color

    public Vertex(String name, Color color){
        this.name = name;
        this.color = color;
        ins = new HashMap<>();
        outs = new HashMap<>();
    }

    /**
     * Getting number of entrants arc on this vertex
     * @return number of entrants arc of this vertex
     */
    private int get_nb_arc_in(){
        return ins.size();
    }

    /**
     * Getting number of outgoings arc on this vertex
     * @return number of outgoings arc of this vertex
     */
    private int get_nb_arc_out(){
       return outs.size();
    }

    /**
     * Getting total entrants and outgoing arc of this vertex
     * @return number of total entrants and outgoing arc of this vertex
     */
    public int get_nb_arc(){
        return get_nb_arc_in() + get_nb_arc_out();
    }

    /**
     * Add entrant to this vertex
     * @param vi : entrant
     * @param arc_color : arc color between this vertex and entrant
     */
    public void add_in_vertex(Vertex vi, Color arc_color){
        ins.put(vi, arc_color);
    }

    /**
     * Add outgoing to this vertex
     * @param vo : outgoing
     * @param arc_color : arc color between this vertex and outgoing
     */
    public void add_out_vertex(Vertex vo, Color arc_color){
        outs.put(vo, arc_color);
    }

    /**
     * Check if vertex color is red
     * @return result of red color checking
     */
    public boolean is_red(){
        if(color == Color.RED) return true;
        return false;
    }

    /**
     * Check if vertex color is blue
     * @return result of blue color checking
     */
    public boolean is_blue(){
        if(color == Color.BLUE) return true;
        return false;
    }

    /**
     * Getting arc color between this vertex and given vertex (Could have 2 arcs between 2 vertex which have the opposite orientation)
     * - check if given vertex is in ins list and outs list
     * @param v : given vertex
     * @return  - Hashmap which contain color arc and arc orientation between 2 vertex
     *          - key : arc color between this vertex and given vertex
     *          - value : true if is an entrant arc & false if is an outgoing arc
     */
    public HashMap<Color, Boolean> get_arc_color(Vertex v){
        HashMap<Color, Boolean> arc_colors = new HashMap<>();
        // Check ins list and add result
        for (HashMap.Entry<Vertex, Color> entry : ins.entrySet()) {
            Vertex in_vertex = entry.getKey();
            Color color_arc = entry.getValue();
            if(in_vertex == v){
                arc_colors.put(color_arc, true);
            }
        }
        // Check outs list and add result
        for (HashMap.Entry<Vertex, Color> entry : outs.entrySet()) {
            Vertex out_vertex = entry.getKey();
            Color color_arc = entry.getValue();
            if(out_vertex == v){
                arc_colors.put(color_arc, false);
            }
        }
        return arc_colors;
    }


    /**
     * GETTERS
     * */
    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public HashMap<Vertex, Color> getIns() {
        return ins;
    }

    public HashMap<Vertex, Color> getOuts() {
        return outs;
    }


    /**
     * SETTERS
     * */
    public void setName(String name) {
        this.name = name;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setIns(HashMap<Vertex, Color> ins) {
        this.ins = ins;
    }

    public void setOuts(HashMap<Vertex, Color> outs) {
        this.outs = outs;
    }
}
