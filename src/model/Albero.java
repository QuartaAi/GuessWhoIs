/*
 * Albero.
 */
package model;

/**
 *
 * @author Sandro
 * @param <T>
 */
public class Albero<T> {
    
    private Nodo<T> current;

    public void writeToXMLFile(String filename) {
        // da implementare
    }
    
    public Albero(T t) {
        Nodo<T> tmp = new Nodo<>(t);
        current= tmp;
    }

    public Nodo<T> getRoot() {
        Nodo<T> root = current;
        while (root!=null && root.getParent()!=null) root = root.getParent();
        return root;
    }

    public Nodo<T> getCurrent() {
        return current;
    }

    public T getCurrentValue() {
        return ( current==null ? null : current.getValue() );
    }
    
    public Nodo<T> getCurrentParent() {
        return ( current==null ? null : current.getParent() );
    }
    
    public Nodo<T> getCurrentSx() {
        return ( current==null ? null : current.getSx() );
    }
    
    public Nodo<T> getCurrentDx() {
        return ( current==null ? null : current.getDx() );
    }

    public boolean isLeaf() {
        return ( current==null || ( current.getSx()==null && current.getDx()==null ) );
    }
    
    @Override
    public String toString() {
        return Albero.visitaNodo(getRoot(), ModoVisita.SIMMETRICO);
    }

    public Nodo<T> makeSx(T v) {
        Nodo<T> n = new Nodo<>(v, current);
        if (current==null) current=n; else current.setSx(n);
        return n;
    }
    
    public Nodo<T> makeDx(T v) {
        Nodo<T> n = new Nodo<>(v, current);
        if (current==null) current=n; else current.setDx(n);
        return n;
    }
    
    public void goHome() {
        while (current!=null && current.getParent()!=null) current = current.getParent();
    }
    
    public boolean goParent() {
        if (current==null || current.getParent()==null) return false;
        current = current.getParent();
        return true;
    }
    
    public boolean goSx() {
        if (current==null || current.getSx()==null) return false;
        current = current.getSx();
        return true;
    }
    
    public boolean goDx() {
        if (current==null || current.getDx()==null) return false;
        current = current.getDx();
        return true;
    }
    
    /*
     * attributi e metodi statici di utilità generale per gli Alberi
    */
    
    private static final String SEPARATOR = " ";
    
    public static String visitaNodo(Nodo p, ModoVisita modo) {
        if (p==null) return "";
        String s = "";
        if (modo==ModoVisita.ANTICIPATO) s += p.getValue().toString() + SEPARATOR;
        s += visitaNodo( p.getSx(), modo );
        if (modo==ModoVisita.SIMMETRICO) s += p.getValue().toString() + SEPARATOR;
        s += visitaNodo( p.getDx(), modo );
        if (modo==ModoVisita.POSTICIPATO) s += p.getValue().toString() + SEPARATOR;
        return s;
    }

    public static boolean scambiaNodi(Nodo n1, Nodo n2) {
        Nodo tmp;
        // i due nodi devono esistere entrambi
        if (n1==null || n2==null) return false;
        // se n1 ha un padre reimpostiamo il suo valore di figlio (sx o dx) a n2
        if (n1.getParent()!=null) {
            if (n1.getParent().getDx()==n1) n1.getParent().setDx(n2);
            else if (n1.getParent().getSx()==n1) n1.getParent().setSx(n2);
        }
        // stessa  cosa per n2 con n1
        if (n2.getParent()!=null) {
            if (n2.getParent().getDx()==n2) n2.getParent().setDx(n1);
            else if (n2.getParent().getSx()==n2) n2.getParent().setSx(n1);
        }
        // invertiamo i parent di n1 e di n2
        tmp = n1.getParent(); n1.setParent(n2.getParent()); n2.setParent(tmp);
        return true;
    }
        
}
