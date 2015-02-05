/*
 * GuessWhoIs
 */
package control;

import model.*;

/**
 *
 * @author sandro.gallo
 */
public class GuessWhoIs {

  /**
   * @param args the command line arguments
   */
    public static void main(String[] args) {

        Albero<String> t = new Albero<>("il gatto");
        t.makeSx("il cane");
        t.makeDx("la mucca");
        t.goDx();
        t.makeSx("il capriolo");
        t.makeDx("la pecora");
        System.out.println(t.toString());
        
        
    }
    
}
