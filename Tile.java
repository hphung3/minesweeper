import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * This class will make a JButton of a tile and give it a row/col index and holds the number of mines
 * surronding the tile OR hold the text of a mine.
 * 
 * @author - Harrison Phung
 * @version - Novemebr 30, 2012
 */

public class Tile extends JButton implements MineTile{
    private int row, col;
    private String number = null;
    private boolean isMine;
    
    /**
     * Constructor that takes in 2 ints that represent the row and column of that tile
     * 
     * @param - integer of the row and column that will be specific to the tile
     */
    public Tile(int row, int col){
        this.row = row;
        this.col = col;
        isMine = false;
    }
    
    /**
     * Get the row the tile is in.
     * 
     * @return - integer of the row.
     */
    
    public int getRow(){
        return row;
       }

    /**
     * Get the column the tile is in.
     * 
     * @return - integer of the column.
     */
    public int getCol(){
       return col;
       }
       
    /**
     * Get number of mines surrounding the tile
     * 
     * @return - integer of surrounding mines
     */
    public int getAdjacent(){
        int i = 0;
        ArrayList<Tile> tilesToOpen = MinesweeperUtils.getAdjacent(row,col, MinePanel.getBoard());
        for(int arr = 0; arr < tilesToOpen.size() ;arr++){
                         if(tilesToOpen.get(arr).isMine() == true){
                                i++;
                            }
        
        }
        return i;
    }
    
    
    /**
     * Sets the character of the tile to the appropriate text( number of mine)
     * 
     * @param - String of the text the label should appear
     */
    public void setChar(String text){
       number = text ; 
    }
    
    /**
     * Sets the character of the tile to the appropriate text( number of mine)
     * 
     * @return - String of the specific text(number or mine) of the tile 
     */
    public String getChar(){
       return number ; 
    }
       
    /**
     * Set a mine in the tile
     * 
     * @param - boolean that if true, makes the tile a mine tile
     */
    
    public void setMine(boolean a){
        isMine = a;
        number = "*";
    }
    
    /**
     * Checks if a mine is in the tile
     * 
     * @return - boolean if the tile is a mine
     */
    public boolean isMine(){
        return isMine;
    }
}
