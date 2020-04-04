/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceGraphique;

/**
 *
 * @author utilisateur
 */
public class LanceurOrdiTravail {
    public LanceurOrdiTravail(){
        OrdinateurTravailView vue = new OrdinateurTravailView();
        vue.pack();
        vue.setVisible(true);
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LanceurOrdiTravail();
            }
        });
    }
}
