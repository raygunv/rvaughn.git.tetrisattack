import java.util.Random;

import javax.swing.JPanel;


public class blockHolder 
{
	Random rn = new Random();
	public enum blockType {
	    RED, BLUE, CYAN, PURPLE,
	    YELLOW, GREEN,  
	}
	Block arrayOfBlocks[][]=new Block[6][12];
	

    
    public void arrayFiller() {
    	blockType type;
    	int random;
    	
       	for(int i=0; i<6; i++) {		
    	
			 for( int j=0; j<12; j++) {
				 
				 random=rn.nextInt(5)+1;
				 //System.out.println(random);
				 
				 switch (random) {
				  	case 1:
				  		redBlock red=new redBlock(i,j);
				  		arrayOfBlocks[i][j]=red;
				  		break;
                    
				  	case 2:
				  		blueBlock blue=new blueBlock(i,j);
				  		arrayOfBlocks[i][j]=blue;
				  		break;
                         
				  	case 3: 
				  		cyanBlock cyan=new cyanBlock(i,j);
				  		arrayOfBlocks[i][j]=cyan;
				  		break;
				  	case 4:
				  		purpleBlock purple=new purpleBlock(i,j);
				  		arrayOfBlocks[i][j]=purple;
				  		break;
				  		
				  	case 5:
				  		yellowBlock yellow=new yellowBlock(i,j);
				  		arrayOfBlocks[i][j]=yellow;
				  		break;
				  		
				  	case 6:
				  		greenBlock green=new greenBlock(i,j);
				  		arrayOfBlocks[i][j]=green;
				  		break;
            
				  }
			 }
		 }
    	
    }
    public void paneFiller(JPanel pane){
		int x=1;
    	for(int i=0; i<6; i++) {	
    		x+=10;
	    	
			 for( int j=0; j<12; j++) {
				
				pane.add(arrayOfBlocks[i][j]);
				arrayOfBlocks[i][j].setLocation(i*60+x,j*60);
				
			 }
		}
    }

	
}
