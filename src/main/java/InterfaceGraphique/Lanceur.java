package InterfaceGraphique;

/**
*
* @author utilisateur
*/
public class Lanceur {
   public Lanceur(){
	   GeneralView vue = new GeneralView();
       vue.pack();
       vue.setVisible(true);
   }
   
   public static void main(String[] args) {
       javax.swing.SwingUtilities.invokeLater(new Runnable() {
           public void run() {
               new Lanceur();
           }
       });
   }
}