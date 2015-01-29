/*
 * GuessWhoIs
 */
package control;

import java.util.Scanner;
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

        String answer, differenza, giusto, sbagliato;
        Scanner lettore = new Scanner(System.in);
        Albero<String> t = new Albero<>();
        Nodo<String> tmp;
        t.makeSx("il cane");
      

        while (true) {
            System.out.println("\nPensa un animale ...");
            while ( ! t.isLeaf() ) {
                System.out.print(t.getCurrentValue() + "? ");
                answer = lettore.nextLine();
                if (answer!=null && answer.equalsIgnoreCase("si")) t.goSx(); else t.goDx();
            }
            System.out.print("Ci sono! E' " + t.getCurrentValue() + "! Ho indovinato? ");
            answer = lettore.nextLine();
            if (answer!=null && answer.equalsIgnoreCase("si")) {
                System.out.println("EVVIVA! Sono troppo forte!!!");
                break;
            }
            System.out.print("NON HO INDOVINATO?\nE va bene, pazienza! Scusa, ma cos'era? ");
            giusto = lettore.nextLine();
            sbagliato = t.getCurrentValue();
            System.out.print("E che differenza c'è tra " + giusto + " e " + sbagliato + "? ");
            differenza = lettore.nextLine();
            // sostituisce al nodo sbagliato un sottoalbero fatto da [giusto/differenza\sbagliato]
            tmp = t.getCurrent();
            tmp.setValue(differenza);
            tmp.setSx(new Nodo<String>(giusto,tmp));
            tmp.setDx(new Nodo<String>(sbagliato,tmp));
            t.goHome();
        System.out.println( t.toString() );
            System.out.println("Ok. Grazie.\n\nRiproviamo?");
        }
        System.out.println( t.toString() );
    }

}
