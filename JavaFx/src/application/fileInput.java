package application;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class fileInput {
	List<Object[]> questions;
	
	public fileInput() {
		this.questions = getQuestions();
	}
	
	
	public List<Object[]> getQuestions() {
		List<Object> objectList = new ArrayList<Object>();
		List<Object[]> questionList = new ArrayList<>();
		Object[] test = new Object[6];
	    try {
	      File myObj = new File("C:\\testfile\\testfile.txt");
	      Scanner myReader = new Scanner(myObj);
	      while (true) {
	    	  	
	    	  	if (myReader.hasNextInt()) {
	                int int1 = myReader.nextInt();
	                objectList.add(int1);
	            }
	            else if (myReader.hasNextDouble()) {
	                double double1 = myReader.nextDouble();
	                objectList.add(double1);
	            }
	            else if (myReader.hasNextFloat()) {
	            	float float1 = myReader.nextFloat();
	            	objectList.add(float1);
	            }
	            else if (myReader.hasNext()) {
	                String name = myReader.next();
	                objectList.add(name);
	                
	            } else {
	            	break;
	            }
	        }
	      myReader.close();
	      
	      
	      int skipBy = 6;
	      for(int i = 0; i < objectList.size(); i+=skipBy) {
	    	  test = objectList.subList(i, i+skipBy).toArray();
	    	  questionList.add(test);
	      }
	      
	      return questionList;
	      
	    } catch (FileNotFoundException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	      return null;
	    }
	  }
}
