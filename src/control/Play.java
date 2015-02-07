/*
 * Play one TUI dialog for guessing an animal from the tree .
 */
package control;

import java.util.Scanner;
import model.*;

/**
 *
 * @author Sandro
 */
public class Play {
    
  /**
   * @param t
   * @return continue
   */
  public static boolean guess(BinaryTree<String> t) {
    String answer, differenza, giusto, sbagliato;
    Scanner lettore = new Scanner(System.in);
    System.out.println("Pensa un animale ...");
    System.out.print(" ... premi INVIO quando sei pronto ... ");
    lettore.nextLine();
    t.goHome();
    while ( ! t.isLeaf() ) {
        System.out.print(t.getCurrentValue() + "? ");
        answer = lettore.nextLine();
        if (answer!=null && answer.equalsIgnoreCase("si")) t.goSx(); else t.goDx();
    }
    System.out.print("Ci sono!\nE' " + t.getCurrentValue() + "!\nHo indovinato? ");
    answer = lettore.nextLine();
    if (answer!=null && answer.equalsIgnoreCase("si")) {
        System.out.println("EVVIVA! Sono troppo forte!!!");
        System.out.print("Vuoi la rivincita? ");
    } else {
        System.out.print("E va bene, ci ho provato ... pazienza!\nScusa, ma ... cos'era? ");
        giusto = lettore.nextLine();
        sbagliato = t.getCurrentValue();
        System.out.print("E che differenza c'è tra " + giusto.toUpperCase() + " e " + sbagliato.toUpperCase() + "? ");
        differenza = lettore.nextLine();
        // sostituisce al nodo sbagliato un sottoalbero fatto da [giusto/differenza\sbagliato]
        // lo fa rimpiazzando il valore di giusto con differenza e creando due nuovi nodi figli
        t.getCurrent().setValue(differenza);
        t.makeSx(giusto);
        t.makeDx(sbagliato);
        System.out.println("Ok, grazie della preziosa informazione.");
        System.out.print("Vuoi darmi la rivincita? ");
    }
    answer = lettore.nextLine();
    System.out.println();
    return answer.equalsIgnoreCase("si");
  }

}
