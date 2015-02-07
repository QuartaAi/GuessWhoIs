/*
 * BinaryTree: ADT che implementa Alberi binari.
 */
package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Sandro
 * @param <T>
 */
public class BinaryTree<T> {

    private int counter;
    private BinaryNode<T> current;

    /* constructors */
    public BinaryTree() {
        counter = 0;
        current = null;
    }
    public BinaryTree(T rootValue) {
        BinaryNode<T> rootNode = new BinaryNode<>(counter=1, rootValue);
        current = rootNode;
    }

    /* queries */
    public boolean isEmpty() { return counter==0; }
    public boolean isLeaf() { return ( current==null || ( current.getSx()==null && current.getDx()==null ) ); }
    public BinaryNode<T> getRoot() {
        BinaryNode<T> root = current;
        while (root!=null && root.getParent()!=null) root = root.getParent();
        return root;
    }

    /* getter operations on current node */
    public BinaryNode<T> getCurrent() { return current; }
    public T getCurrentValue() { return ( current==null ? null : current.getValue() ); }
    public BinaryNode<T> getCurrentParent() { return ( current==null ? null : current.getParent() ); }
    public BinaryNode<T> getCurrentSx() { return ( current==null ? null : current.getSx() ); }
    public BinaryNode<T> getCurrentDx() { return ( current==null ? null : current.getDx() ); }

    @Override
    public String toString() {
        return BinaryTree.visitaNodo(getRoot(), ModoVisita.SIMMETRICO);
    }

    /* building children operations */
    public BinaryNode<T> make(T v) {
        BinaryNode<T> n = new BinaryNode<>(++counter, v);
        if (current==null) current=n;
        return n;
    }
    public BinaryNode<T> makeSx(T v) {
        if (this.getCurrentSx()!=null) return null;
        BinaryNode<T> n = new BinaryNode<>(++counter, v, current);
        current.setSx(n);
        return n;
    }
    public BinaryNode<T> makeDx(T v) {
        if (this.getCurrentDx()!=null) return null;
        BinaryNode<T> n = new BinaryNode<>(++counter, v, current);
        current.setDx(n);
        return n;
    }

    /* navigation */
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
    
    private void writeTextFile(String msg, String filename) {
        try {
            BufferedWriter scrittore = new BufferedWriter( new FileWriter(filename) );
            scrittore.write(msg);
            scrittore.close();
        } catch (IOException ex) {
            System.out.println("ERRORE in scrittura del file: " + filename + "\n" + ex.getMessage());
        }
    }
    
    public void writeToXmlFile(String filename) {
        String s = getRoot().toXML("");
        writeTextFile(s, filename);
    }
    
    public void writeToFile(String filename) {
        String s = BinaryTree.visitaNodo(getRoot(), ModoVisita.ANTICIPATO);
        writeTextFile(s, filename);
    }
    
    public void readFromFile(String filename) {
        /* formato CSV atteso: id,value,parent,sx,dx */
        if (counter!=0) { System.out.println("ATTENZIONE: caricamento da file " + filename + " su un albero NON vuoto!"); System.exit(-1); }
        counter = 0;
        ArrayList<String> lines = new ArrayList<>();
        ArrayList<BinaryNode<T>> nodes = new ArrayList<>();
        HashMap<Integer,Integer> map = new HashMap<>();
        try {
            File f = new File(filename);
            Scanner lettore = new Scanner(f);
            while (lettore.hasNext()) {
                /* PRIMO PASSAGGIO: per ogni riga nel file crea un nodo con id e value */
                counter++;
                String s = lettore.nextLine();
                String[] csv = s.split(",");
                if (csv.length!=5) { System.out.println("ERRORE: numero scorretto di valori nella riga #" + counter + " ["+ s +"] del file " + filename); System.exit(-1); }
                int id = Integer.parseInt(csv[0]);
                T value = (T)csv[1];
                lines.add( s );
                nodes.add( new BinaryNode<>( id, value ) );
                map.put(id, counter-1);
            }
            lettore.close();
        } catch (Exception ex) {
            System.out.println("ERRORE [" + ex.getMessage() + "] in elaborazione file: " + filename);
        }
        /* SECONDO PASSAGGIO: si collegano i nodi usando gli id degli archi */
        for (int i = 0; i < lines.size(); i++) {
            String[] csv = lines.get(i).split(",");
            BinaryNode<T> n = nodes.get(i);
            int id = Integer.parseInt(csv[0]);
            int parent = Integer.parseInt(csv[2]);
            int sx = Integer.parseInt(csv[3]);
            int dx = Integer.parseInt(csv[4]);
            if ( parent>counter ) { System.out.println("ATTENZIONE: nodo #" + (i+1) + " [" + lines.get(i) + "] riferisce un PARENT NON esistente!"); System.exit(-1); }
            if ( sx>counter ) { System.out.println("ATTENZIONE: nodo #" + (i+1) + " [" + lines.get(i) + "] riferisce un nodo SX NON esistente!"); System.exit(-1); }
            if ( dx>counter ) { System.out.println("ATTENZIONE: nodo #" + (i+1) + " [" + lines.get(i) + "] riferisce un nodo DX NON esistente!"); System.exit(-1); }
            if (parent>0) n.setParent( nodes.get(map.get(parent)) );
            if (sx>0) n.setSx(nodes.get(map.get(sx)));
            if (dx>0) n.setDx(nodes.get(map.get(dx)));
        }
        current = nodes.get(0);
    }
    
    /*
     * attributi e metodi statici di utilità generale per gli Alberi
    */
    
    private static final String SEPARATOR = "\n";
    
    public static String visitaNodo(BinaryNode p, ModoVisita modo) {
        if (p==null) return "";
        String s = "";
        if (modo==ModoVisita.ANTICIPATO) s += p.toString() + SEPARATOR;
        s += visitaNodo( p.getSx(), modo );
        if (modo==ModoVisita.SIMMETRICO) s += p.toString() + SEPARATOR;
        s += visitaNodo( p.getDx(), modo );
        if (modo==ModoVisita.POSTICIPATO) s += p.toString() + SEPARATOR;
        return s;
    }

}
