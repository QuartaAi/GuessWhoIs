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
    
    Nodo<String> p = new Nodo<>("il cane");
    Scanner lettore = new Scanner(System.in);

    while (true) {
      while (p.getSx()!=null) {
        System.out.println(p.getValue() + "? ");
        String answer = lettore.nextLine();
        if (answer!=null && answer.equalsIgnoreCase("si")) {
          p = p.getSx();
        } else p = p.getDx();
      }
      System.out.println("E' " + p.getValue() + "\nHo indovinato? ");
      String answer = lettore.nextLine();
      if (answer!=null && answer.equalsIgnoreCase("si")) {
        System.out.println("Troppo forte!!!");
        break;
      }
      System.out.println("OK!\nScusa, cos'era? ");
      answer = lettore.nextLine();
      System.out.println("E che differenza c'è tra " + answer + " e " + p.getValue() + "?");
      String differenza = lettore.nextLine();
      impara(p, differenza, answer);
      while (p.getParent()!=null) p = p.getParent();
      System.out.println("Ok. Grazie.\nRiproviamo.");
    }
    
  }

  public static Nodo impara(Nodo vecchio, String differenza, String nuovo) {
    Nodo<String> diff = new Nodo<>(differenza);
    diff.setParent(vecchio.getParent());
    vecchio.setParent(diff);
    diff.setSx(new Nodo<>(nuovo, diff));
    diff.setDx(vecchio);
    return diff;
  }
  
}
