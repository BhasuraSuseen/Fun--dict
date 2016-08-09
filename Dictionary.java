import dict.BinarySearchTree;
import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*;
import java.io.*;
import java.util.*;
public class Dictionary{
   /** Declaring varibles for public components of GUI **/
   private JFrame mainFrame;   
   private JPanel headerLabel;   
   private JLabel statusLabel;   
   private JPanel controlPanel1;
   private JPanel controlPanel2;
   private JPanel controlPanel3;
   private JTextField txt;
   private JTextArea area; 
   private String word;
   private String result;
   private String define;
   /** Create an Object of BinarySearchTree class 
   in a way that all methods can access that object. 
   this will be the tree of dictionary word nodes **/
   public BinarySearchTree tree = new BinarySearchTree();  
   public Dictionary(){      
       DictionaryGUI();/** Initiate GUI **/
       BufferedReader bReader = null;
       /** Read data from a file, 
       create new node in the tree, for each line in the file which contain word and its definition,
       Error handling while reading **/
		try {
		    String currentLine;      
	            bReader = new BufferedReader(new FileReader("testing.txt"));
		    while ((currentLine = bReader.readLine()) != null) {	    
                    StringTokenizer stringLine;
 		    stringLine = new StringTokenizer(currentLine,"|");
                    String word1 = stringLine.nextToken();
                    String definition1 = stringLine.nextToken();
                    tree.insert(word1, definition1);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bReader != null)bReader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}   
   }
   /** Main method
   create an Instance of Dictionary class
   calling dictionaryEvent method to add add action components to the GUI **/
   public static void main(String[] args){      
       Dictionary dictionary = new Dictionary();        
       dictionary.dictionaryEvent();
       
	             
   } 
   /** create DictionaryGUI method
   Calling This will creat Windowd GUI and Its main pannels and fields **/        
   private void DictionaryGUI(){      
       mainFrame = new JFrame("2016 Dictionary CS042");     
       mainFrame.setSize(1035,750);
       mainFrame.setLayout(null);
      
       headerLabel = new JPanel();      
       statusLabel = new JLabel("");
       statusLabel.setHorizontalAlignment(JLabel.CENTER);
       statusLabel.setVerticalAlignment(JLabel.TOP);        
       /**Create GUI window**/      
       mainFrame.addWindowListener(new WindowAdapter() {         
           public void windowClosing(WindowEvent windowEvent){         
               System.exit(0);         
           }              
       }); 
       /**Create and Decorate GUI panels**/          
       controlPanel1 = new JPanel();      
       controlPanel1.setLayout(new FlowLayout());
       controlPanel3 = new JPanel();      
       controlPanel3.setLayout(new FlowLayout());
       controlPanel2 = new JPanel();      
       controlPanel2.setLayout(new FlowLayout());
       controlPanel1.setBackground(new Color(107,167,214));
       controlPanel2.setBackground(new Color(107,167,214));
       controlPanel3.setBackground(new Color(107,167,214));
       headerLabel.setOpaque(true);
       statusLabel.setOpaque(true);
       statusLabel.setForeground(Color.white);
       statusLabel.setFont(new Font("Arial", Font.BOLD, 14));
       statusLabel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
       headerLabel.setBackground(new Color(67,142,200));
       statusLabel.setBackground(new Color(67,142,200));
       mainFrame.setBackground(new Color(0,0,0));
       mainFrame.add(headerLabel);      
       mainFrame.add(controlPanel1);
       mainFrame.add(controlPanel3);
       mainFrame.add(controlPanel2);       
       mainFrame.add(statusLabel);
       
       headerLabel.add(new JLabel(new ImageIcon("dic1.jpg"))); 
       headerLabel.setVisible(true);     
       mainFrame.setVisible(true);
       
       Insets insets = mainFrame.getInsets();
       headerLabel.setBounds(insets.left,insets.top, 1000, 200);
       controlPanel1.setBounds(insets.left,200 + insets.top, 1000, 40);
       controlPanel3.setBounds(insets.left, 240 + insets.top, 1000, 90);
       controlPanel2.setBounds(insets.left, 330 + insets.top, 1000, 50);
       statusLabel.setBounds(insets.left, 380 + insets.top, 1000, 280);
            
}
/**Create dictionaryEvent method this method contains action trigger objects
These components will intract with users to trigger events **/ 
   private void dictionaryEvent(){
       /**Create buttons **/ 
      JButton addButton = new JButton("Add new word");      
      JButton searchButton = new JButton("Search word");      
      JButton deleteButton = new JButton("Delete word");
      JButton synonymsButton = new JButton("Synonyms of the word");
      /**Add text fields **/
      area = new JTextArea();
      txt = new JTextField();
      /**Decorate  text fields **/
      txt.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
      area.setFont(new Font("Arial", Font.BOLD, 14));
      area.setForeground(Color.GRAY);
      txt.setPreferredSize(new Dimension(250, 30));
      txt.setFont(new Font("Arial", Font.BOLD, 14));
      txt.setForeground(Color.GRAY);
      area.setLineWrap(true);
      area.setWrapStyleWord(true);
      area.setPreferredSize(new Dimension(800, 80));
      area.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
      controlPanel3.add(area);
      controlPanel1.add(txt);
      /**Decorate buttons **/ 
      addButton.setFont(new Font("Arial", Font.BOLD, 14));
      addButton.setForeground(Color.white);
      addButton.setBackground(new Color(67,142,200));
      searchButton.setFont(new Font("Arial", Font.BOLD, 14));
      searchButton.setForeground(Color.white);
      searchButton.setBackground(new Color(67,142,200));
      deleteButton.setFont(new Font("Arial", Font.BOLD, 14));
      deleteButton.setForeground(Color.white);
      deleteButton.setBackground(new Color(67,142,200));
      synonymsButton.setFont(new Font("Arial", Font.BOLD, 14));
      synonymsButton.setForeground(Color.white);
      synonymsButton.setBackground(new Color(67,142,200));
      addButton.setActionCommand("Add");      
      searchButton.setActionCommand("Search");
      deleteButton.setActionCommand("Delete");
      /**add trigger objects to buttons **/ 
      addButton.addActionListener(new ButtonClickListener());       
      searchButton.addActionListener(new ButtonClickListener());       
      deleteButton.addActionListener(new ButtonClickListener());
      synonymsButton.addActionListener(new ButtonClickListener()); 
      /**add buttons to GUI panels **/ 
      controlPanel2.add(addButton);      
      controlPanel2.add(searchButton);      
      controlPanel2.add(deleteButton);
      controlPanel2.add(synonymsButton);       
      mainFrame.setVisible(true);     
}
/**This class defines what instructions should happen when some trigger triggerd  **/
private class ButtonClickListener implements ActionListener{      
       public void actionPerformed(ActionEvent e) {         
           String actionCommand = e.getActionCommand(); 
           /**Add new word **/               
           if( actionCommand.equals( "Add" ))  {            
               word = txt.getText();
               define = area.getText();
               tree.insert(word, define);
               statusLabel.setText("Word " + word + " is Added to the Dictionary");         
           }/**Search results words definition **/ 
           else if( actionCommand.equals( "Search" ) )  {            
               area.setText("");
               word = txt.getText();
               result = tree.find(word);
               statusLabel.setText(result);
               area.setText(result);          
           }/**Delete word**/ 
           else if(actionCommand.equals( "Delete" ))  {
               area.setText("");
               word = txt.getText();
               if(tree.delete(word) == true){
                   statusLabel.setText("Word " + word + " Deleted");   
               }else{
                   statusLabel.setText("Word " + word + " Not Found");   
               }         
           }/**Find similar meaning words of a word**/ 
           else{
               String synonymList = " Synonyms : ";
               tree.synoCount = 0;
               for(int j = 0; j < 11; j++){
                    tree.syno[j] = " ";
               }
               int i = 0;
               int j = 0;
               area.setText("");
               word = txt.getText();
               result = tree.find(word);            
               statusLabel.setText("Synonyms");
               tree.findSynonyms(tree.rootNode, result); 
               while (tree.syno[i] != " "){
                   if(word.compareTo(tree.syno[i]) != 0){
                   synonymList = synonymList + "  "+ Integer.toString(j + 1) +". " + tree.syno[i];
                   j++;
                   }
                   i++;
               }
               statusLabel.setText(synonymList);        
           }        
       }    
} 
}

