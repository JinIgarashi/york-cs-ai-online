public class Tables  {
    
    public void generateTable()  
    {
      //Put the code for your times-table here
    	for (int row = 1; row <=12; row++) {
    		for (int col = 1; col <= 12; col++) {
    		
    			int value = col * row;
    			String charValue =  String.format(" %d", value);

    			int length = String.valueOf(value).length();
    			if (length == 1) {
    				charValue = String.format("   %d", value);
    			} else if (length == 2) {
    				charValue = String.format("  %d", value);
    			} 

    			if (col == 12) {
        			System.out.println(charValue);
    			} else {
        			System.out.print(charValue);
    			}
    		}
    	}
    
    }
    
    public static void main (String args[]) {
		
		Tables t=new Tables();
		t.generateTable();
	}
}
