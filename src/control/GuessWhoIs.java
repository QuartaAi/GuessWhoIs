/*
 * GuessWhoIs
 */
package control;

import model.Nodo;

/**
 *
 * @author sandro.gallo
 */
public class GuessWhoIs {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Nodo root = new Nodo("Gatto");
        root.setDx( new Nodo("Cane") );
        Nodo n = new Nodo("Mucca");
        n.setDx( new Nodo("Cavallo"));
        n.setSx( new Nodo("Pecora"));
        root.setSx( n );
        
        stampaSottoAlbero(n);
        /* Ouput atteso:
        Mucca
        -- Cavallo
        -- Pecora
        */
        stampaSottoAlbero(root);
        /* Ouput atteso:
        Mucca
        -- Cavallo
        -- Pecora
        Gatto
        -- Cane
        -- Mucca
           -- Cavallo
           -- Pecora
        */

    }
    
    public static void stampaSottoAlbero(Nodo radice) {
        /*
        ... da fare da soli ....
        */
    }

    
    
    
}
