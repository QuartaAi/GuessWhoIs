/*
 * Nodo: ADT che implementa Nodi di alberi BINARI.
 */
package model;


public class Nodo {
    
    private String info;
    private Nodo dx=null, sx=null;

    public int getNumChildren() {
        int num_children = 0;
        if (dx!=null) num_children++;
        if (sx!=null) num_children++;
        return num_children;
    }
    
    public Nodo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public Nodo getDx() {
        return dx;
    }

    public Nodo getSx() {
        return sx;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setDx(Nodo dx) {
        this.dx = dx;
    }

    public void setSx(Nodo sx) {
        this.sx = sx;
    }

    
}
