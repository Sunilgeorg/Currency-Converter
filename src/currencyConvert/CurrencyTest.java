package currencyConvert;

import java.io.File;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
		
	public class CurrencyTest {
		public static void main(String[] args) 
	      throws BiffException, IOException, WriteException
	   { 
        //Read Sterling test data from Sterling.xls 
		  Workbook workbookSterling = Workbook.getWorkbook(new File("Sterling.xls"));
	      Sheet sheetSterling = workbookSterling.getSheet(0);
	      
	      //Add Sterling values into an array 
	     double [][] arySterling = new double[5][5];
	     
	     for(int i=1; i<5; i++){
	    	 for(int j=1;j<5;j++)	{
	    		 //If any cell is empty add the value 0.0 into it.
	    		 if((sheetSterling.getCell(i, j).getContents())== ""){
	    			 arySterling[i][j]= 0;
	    			 
	    			 }
	    		 else{
	    		 arySterling[i][j]= Double.parseDouble(sheetSterling.getCell(i, j).getContents());
	    		 
	    		 }}
	    	 }
	    //Close Sterling.xls
	   workbookSterling.close();
	  	    
	   //Read Euro test data from Euro.xls 
	      Workbook workbookEuro = Workbook.getWorkbook(new File("Euro.xls"));
	      Sheet sheetEuro = workbookEuro.getSheet(0);
	    
	      // Add Euro values into an array 
	      double [][] aryEuro = new double[5][5];
	      // Loop through all the elements of aryEuro to check values in the Euro table
	      for(int i=1; i<5; i++){
		    	 for(int j=1;j<5;j++)	{
		    		//If any cell is empty add the value 0.0 into it.
		    		 if((sheetEuro.getCell(i, j).getContents())== ""){
		    			 aryEuro[i][j]= 0;
		    			 }
		    		 else{
		    		 aryEuro[i][j]= Double.parseDouble(sheetEuro.getCell(i, j).getContents());
		    		 }
		    	 }}
	       
	    //Testing for accuracy 
	   int rows = 5;
	   int columns = 5;
	// Iterate through each element in the 2 arrays
	  for(int i=1;i< rows;i++)
		  {
		  for(int j=1;j<columns;j++)
		  {
		/*if they have empty cells(array value 0.0)in the same position,
        we exclude them from conversion test. if there happened to be any value, these cells will 
        be included in the test and hence end up in the 'else' part and thus fail 
        */
		  if ((arySterling[i][j]== 0.0) && (aryEuro[i][j]==0.0)){
			   
			  
			  System.out.println("Test passed for empty cells for " + sheetEuro.getCell(i,0).getContents() + " of " + sheetEuro.getCell(0,j).getContents());
		
		  }
		  // Checking if Euro is 1.5 times Sterling
	   else if (aryEuro[i][j] ==  arySterling[i][j]* 1.5)
			  
	   {
	
	  System.out.println("Tests for Conversion passed for " + sheetEuro.getCell(i,0).getContents() + " of " + sheetEuro.getCell(0,j).getContents());
	
	   }
		// Test Fail conditions ; either the cells are not empty where they are required to 
    	// or the commission rates tests fail
	   else   {
		   
		   System.out.println("tests for Conversion Failed for " + sheetEuro.getCell(0,j).getContents() + " of " + sheetEuro.getCell(i,0).getContents());
		   System.out.println("Conversion of  " + arySterling[i][j] + " Sterling to " + aryEuro[i][j] + " Euros  does not match the rates ");
		
	   }
	   }
	   }
	
	  workbookEuro.close();
			   }
	    
	      
	   }
	