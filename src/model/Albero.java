/*
 * Albero.
 */
package model;

/**
 *
 * @author Sandro
 */
public class Albero {

  public static String visitaNodo(Nodo p, ModoVisita modo) {
    if (p==null) return "";
    String s = null;
    if (modo==ModoVisita.ANTICIPATO) s += (s==null ? "" : ",") + p.getValue().toString();
    s += visitaNodo( p.getSx(), modo );
    if (modo==ModoVisita.SIMMETRICO) s += (s==null ? "" : ",") + p.getValue().toString();
    s += visitaNodo( p.getDx(), modo );
    if (modo==ModoVisita.POSTICIPATO) s += (s==null ? "" : ",") + p.getValue().toString();
    return s;
  }
            
}
