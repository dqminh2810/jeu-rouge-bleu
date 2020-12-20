import java.io.Serializable;
import java.util.HashMap;

/**
 * Vertex in oriented graph bi-coloré
 */
public class Vertex implements Serializable {
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

    public Vertex(String name){
        this.name = name;
        //this.color = color;
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
        if(!ins.containsKey(vi)){
            ins.put(vi, arc_color);
        }
        if(!vi.outs.containsKey(this)){
            vi.add_out_vertex(this, arc_color);
        }
    }

    /**
     * Add outgoing to this vertex
     * @param vo : outgoing
     * @param arc_color : arc color between this vertex and outgoing
     */
    public void add_out_vertex(Vertex vo, Color arc_color){
        if(!outs.containsKey(vo)) {
            outs.put(vo, arc_color);
        }
        if(!vo.ins.containsKey(this)) {
            vo.ins.put(this, arc_color);
        }
    }

    /**
     * Remove entrants arc between this vertex and another vertex given
     * @param v given vertex
     */
    public void remove_in_arc(Vertex v){
        this.getIns().remove(v);
    }

    /**
     * Remove outputs arc between this vertex and another vertex given
     * @param v given vertex
     */
    public void remove_out_arc(Vertex v){
        this.getOuts().remove(v);
    }

    /**
     * Remove both entrants and outputs arc between this vertex and another vertex given
     * @param v given vertex
     */
    public void remove_arc(Vertex v){
        remove_in_arc(v);
        remove_out_arc(v);
    }
    /**
     * Check if vertex color is red
     * @return result of red color checking
     */
    public boolean is_red(){
        return color == Color.RED;
    }

    /**
     * Check if vertex color is blue
     * @return result of blue color checking
     */
    public boolean is_blue(){
        return color == Color.BLUE;
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
            if(in_vertex.getName().equals(v.getName())){
                arc_colors.put(color_arc, true);
            }
        }
        // Check outs list and add result
        for (HashMap.Entry<Vertex, Color> entry : outs.entrySet()) {
            Vertex out_vertex = entry.getKey();
            Color color_arc = entry.getValue();
            if(out_vertex.getName().equals(v.getName())){
                arc_colors.put(color_arc, false);
            }
        }
        return arc_colors;
    }

    /**
     * return a score for a red vertex according to the color of its vertices and outgoing arcs
     * If the arc is red and the end vertex is blue: +1 point
     * If the arc is blue and the end vertex is red: -1 point
     * @return the score
     */
    public int getScore(){
        int score = 0;
        for (HashMap.Entry<Vertex,Color> entry : outs.entrySet()){                          //We look at the outgoing arcs
            if(entry.getValue() == Color.RED && entry.getKey().is_blue()) score =+ 1;       //if the arc is red and the end vertex is blue: +1 point
            else if(entry.getValue() == Color.BLUE && entry.getKey().is_red()) score =- 1;  //if the arc is blue and the end vertex is red: -1 point
        }                                                                                   //0 points for other cases
    return score;
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

    /**
     * Get vertex informations
     * @return vertex info as String
     */
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Vertex name: ").append(this.getName()).append("\n");
        res.append("Vertex color: ").append(this.getColor()).append("\n");

        res.append("Ins: " + "\n");
        for (HashMap.Entry<Vertex, Color> entry : ins.entrySet()) {
            Vertex in_vertex = entry.getKey();
            Color in_arc_color = entry.getValue();
            res.append("- in vertex ").append(in_vertex.getName()).append(" ").append(in_vertex.getColor()).append("\n");
            res.append("- in arc color ").append(in_arc_color.toString()).append("\n");
        }


        res.append("Outs: " + "\n");
        for (HashMap.Entry<Vertex, Color> entry : outs.entrySet()) {
            Vertex out_vertex = entry.getKey();
            Color out_arc_color = entry.getValue();
            res.append("- out vertex ").append(out_vertex.getName()).append(" ").append(out_vertex.getColor()).append("\n");
            res.append("- out arc color ").append(out_arc_color.toString()).append("\n");
        }


        return res.toString();
    }
}
