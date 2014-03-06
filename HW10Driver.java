import javax.swing.*;

/**
 * Driver class that will make the Frame to hold the panel and display the Minesweeper to user
 * 
 * @author - Harrison Phung
 * @version - Novemebr 30, 2012
 */

public class HW10Driver{
/**
 * Main method that creates a frame and makes the minepanel
 * 
 * @param - String array 
 */
    public static void main(String[]args){
        JFrame frame = new JFrame("MineSweeper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new MinePanel());
        frame.pack();
        frame.setVisible(true);
        
    }
}
