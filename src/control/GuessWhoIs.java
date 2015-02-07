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

        BinaryTree<String> tree = new BinaryTree<>();
        tree.readFromFile("data/tree.txt");
        
        while ( Play.guess(tree) );
        System.out.println(tree.toString());
        System.out.println(tree.getRoot().toXML(""));
        
        tree.writeToFile("data/tree.txt");
        tree.writeToXmlFile("data/tree.xml");
        
    }
    
}
