import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
/**
 * 
 * @author - Harrison Phung
 * @version - Novemebr 30, 2012
 */
public class MinePanel extends JPanel{

    enum State{Before, After};
    
    private State state;
    private static Tile[][] msboard;
    private JButton restart, hint;
    private int numberrows, numbercols, mines,again;
    private Random random; 
    private JPanel panel;
    private MinesweeperUtils utils;
    
    /**
     * Constructor of the MinePanel that will call a method to create a minesweeper board 
     */
    public MinePanel(){
        CreatePanel();
    }
    
    /**
     * Takes in the user input and calls method to make a board 
     */
    public void CreatePanel(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Number of Rows: ");
        numberrows = scan.nextInt();
        System.out.println("Enter Number of Columns: ");
        numbercols = scan.nextInt();
        System.out.println("Enter Number of Mines under " + numberrows*numbercols + ":");
        mines = scan.nextInt();
        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel();
        restart = new JButton("Restart");
        restart.addActionListener(new ButtonListener());
        topPanel.add(restart);
        hint = new JButton("Hint");
        hint.addActionListener(new ButtonListener());
        topPanel.add(hint);
        add(topPanel,BorderLayout.NORTH);
        JPanel panel = Minepanel(numberrows, numbercols,mines);
        add(panel, BorderLayout.CENTER);

    }
    
    /**
     * Makes a board of JButtons in a gridLayout and sets the status of the game to wait for the first 
     * click
     * 
     * @param - int of number of rows columns, and mines wanted
     */
    public JPanel Minepanel(int x, int y, int mines){
        state = State.Before;
        msboard = new Tile[x][y];
        /*
         * Create a JButton relation with the tileBoard
         */
        JPanel buttonpanel= new JPanel();
        buttonpanel.setLayout(new GridLayout(x,y));
        for (int i = 0; i < x; i++)
            for (int j = 0; j < y; j++){
                msboard[i][j] = new Tile(i,j);
                //add Listeners
                msboard[i][j].addActionListener(new ButtonListener());
                msboard[i][j].addMouseListener(new ButtonListener());
                msboard[i][j].setMargin(new Insets(0, 0, 0, 0));
                //msboard[i][j].setText("0");
                buttonpanel.add(msboard[i][j]);
                
            }

        return buttonpanel;
    }
    

    /**
     * This class will handle and do an action when a JButton is clicked
     */
    
    private class ButtonListener implements ActionListener, MouseListener{
        /**
         * Method takes in an ActionEvent and acts accordingly
         * 
         * @param - ActionEvent object
         */
        public void actionPerformed(ActionEvent event){
            
            if(event.getSource() instanceof Tile){
                Tile tile = (Tile)event.getSource();
                int row = tile.getRow();
                //System.out.print(row);
                int col = tile.getCol();
                //System.out.println(col);
                //creates mines that is not the one clicked on
                if (state == State.Before){
                    state = State.After;
                    //create mines
                    createMines(row,col);
                    //checks around the tile clicked on and return the number of mines around
                    int i = findMines(row,col);
                    if(i==0){
                                ArrayList<Tile> tilesOpen = MinesweeperUtils.expandTile(tile, msboard);
                                for(int ar = 0; ar < tilesOpen.size() ;ar++){
                                    msboard[tilesOpen.get(ar).getRow()][tilesOpen.get(ar).getCol()].setChar(""+findMines(tilesOpen.get(ar).getRow(), tilesOpen.get(ar).getCol()));
                                    msboard[tilesOpen.get(ar).getRow()][tilesOpen.get(ar).getCol()].setLabel(msboard[tilesOpen.get(ar).getRow()][tilesOpen.get(ar).getCol()].getChar());
                                }
                            }
                    tile.setChar(""+i);
                    tile.setLabel(tile.getChar());
                }            
                
            
                else{//if the state is after
                    if (tile.isMine() == false){
                        int i = findMines(row,col);
                        tile.setChar(""+i);
                        tile.setLabel(tile.getChar());
                            if(i==0){
                                ArrayList<Tile> tilesOpen = MinesweeperUtils.expandTile(tile, msboard);
                                for(int ar = 0; ar < tilesOpen.size() ;ar++){
                                    msboard[tilesOpen.get(ar).getRow()][tilesOpen.get(ar).getCol()].setChar(""+findMines(tilesOpen.get(ar).getRow(), tilesOpen.get(ar).getCol()));
                                    msboard[tilesOpen.get(ar).getRow()][tilesOpen.get(ar).getCol()].setLabel(msboard[tilesOpen.get(ar).getRow()][tilesOpen.get(ar).getCol()].getChar());
                                }
                            }
                            
                        }
                        else{
                            revealMine();
                        }
                }
            
                tile.setLabel(tile.getChar());
               
            }
            
     
            

            if(event.getSource() == restart){
                //state = state.Before;
                removeAll();
                updateUI();
                JPanel topPanel = new JPanel();
                
                restart = new JButton("Restart");
                restart.addActionListener(new ButtonListener());
                hint = new JButton("Hint");
                hint.addActionListener(new ButtonListener());
                topPanel.add(restart);
                topPanel.add(hint);
                add(topPanel,BorderLayout.NORTH);
                panel = Minepanel(numberrows, numbercols, mines);
                add(panel,BorderLayout.CENTER);//restart the game with new X,Y, mines
                
            }
            
            if(event.getSource() == hint){
                hintMine();
            }
                  
            check();
        }
        /**
         * When a mouse is pressed, if it is a right click, then the label will be set a an "F" for flag
         * 
         * @param - MouseEvent object
         */
        public void mousePressed (MouseEvent event){
                Tile tile = (Tile)event.getSource();
                if (event.getButton() == 3) { // if right click
                    tile.setLabel("F");
                }
        }
        
        /**
         * Empty method of the MouseEvent interface
         * 
         * @param - MouseEvent object
         */
        public void mouseClicked(MouseEvent event) {}
        
        /**
         * Empty method of the MouseEvent interface
         * 
         * @param - MouseEvent object
         */
        public void mouseReleased (MouseEvent event) {}
        
        /**
         * Empty method of the MouseEvent interface
         * 
         * @param - MouseEvent object
         */
        public void mouseEntered (MouseEvent event) {}
        /**
         * Empty method of the MouseEvent interface
         * 
         * @param - MouseEvent object
         */
        public void mouseExited (MouseEvent event) {}
    }
    

     /**
      * Find surrounding tiles and count them and put it in the tile
      * 
      * @param - int row and col of the tile selected
      * @return - integer of the number of surrounding mines
      */
     public int findMines(int row, int col){ 
        int i = 0;
        ArrayList<Tile> tilesToOpen = MinesweeperUtils.getAdjacent(row,col, msboard);
        for(int arr = 0; arr < tilesToOpen.size() ;arr++){
                         if(tilesToOpen.get(arr).isMine() == true){
                                i++;
                         }
        
        }
        return i;
    }
    
    /**
     * create Mines that is not on the first clicked button
     * 
     * @param - integers of the row and column of the button that was clicked on
     */
    
    public void createMines(int x, int y){
    int tempMines = mines;
    random = new Random();
        while (tempMines !=0){
            int randRow = random.nextInt(numberrows);
            int randCol = random.nextInt(numbercols);
            if(randRow != x || randCol != y){
            if(msboard[randRow][randCol].isMine() == false){
                msboard[randRow][randCol].setMine(true);
                tempMines--;
            }
        }
        }
        
    
    }
    
    /**
     * Get the board of the MinePanl
     * 
     * @return - 2D array of Tiles
     */
    public static Tile[][] getBoard(){
        return msboard;
    }
    
    /**
     * Sets a flag on a mine tile in the panel
     */
    public void hintMine(){
        int x = 0;
        //go through board and check if char
        
        for (int i = 0; i < numberrows; i++)
            for (int j = 0; j < numbercols; j++)
                if(msboard[i][j].isMine() == true)
                    if(msboard[i][j].getText()== ""){
                        if (x==0)
                        msboard[i][j].setText("!");
                        x++;
                    }
                       
                    
        //check the JButton getText --> iff null --> set "!"
    }
    
    /**
     * Checks the amount of tiles clicked on and when the # tiles clicked = total tiles - mines, gives the Winning OptionPane
     */
    public void check(){
        int difference = numberrows*numbercols - mines;
        int counter= 0;
        for (int i = 0; i < numberrows; i++)
            for (int j = 0; j < numbercols; j++){
                String s = msboard[i][j].getChar();
                if (s != null)
                if(!s.equals("*"))
                counter++;
                if (counter == difference){    
                   again = JOptionPane.showConfirmDialog (null, "You Won!! You're a genius! Do you want to try again?");
                if(again == JOptionPane.YES_OPTION){
                removeAll();
                updateUI();
                JPanel topPanel = new JPanel();
                restart = new JButton("Restart");
                restart.addActionListener(new ButtonListener());
                hint = new JButton("Hint");
                hint.addActionListener(new ButtonListener());
                topPanel.add(restart);
                topPanel.add(hint);
                add(topPanel,BorderLayout.NORTH);
                panel = Minepanel(numberrows, numbercols, mines);
                add(panel,BorderLayout.CENTER);//restart the game with new X,Y, mines
                }
           }
        }
    }
    
    /**
     * Reveal all mines in panel and makes an OptionPane to allow user to reset the game
     */
    public void revealMine(){
        for (int i = 0; i < numberrows; i++)
            for (int j = 0; j < numbercols; j++)
                if(msboard[i][j].isMine() == true)
                    msboard[i][j].setLabel("*");
        //JOptionPane pane = new JOptionPane("You Win Playagain?")
        again = JOptionPane.showConfirmDialog (null, "You Lose Noob! Do you want to try again?");
        if(again == JOptionPane.YES_OPTION){
                removeAll();
                updateUI();
                JPanel topPanel = new JPanel();
                restart = new JButton("Restart");
                restart.addActionListener(new ButtonListener());
                hint = new JButton("Hint");
                hint.addActionListener(new ButtonListener());
                topPanel.add(restart);
                topPanel.add(hint);
                add(topPanel,BorderLayout.NORTH);
                panel = Minepanel(numberrows, numbercols, mines);
                add(panel,BorderLayout.CENTER);//restart the game with new X,Y, mines
            
        }
    }
}

