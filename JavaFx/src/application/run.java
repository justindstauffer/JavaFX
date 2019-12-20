package application;

import java.util.List;

public class run {

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Object[]> test = new fileInput().getQuestions();
		addObjects<Object,Object> the = new addObjects<Object,Object>(1,"String");
		System.out.println(the.add());
		for(int i = 0; i < test.size(); i++) {
			for(int j = 0; j < test.get(i).length; j++) {
				System.out.println(test.get(i)[j]);
				System.out.println(test.get(i)[j].getClass().getName());
				System.out.println("------------");
			
			}
		}
		
	}

}
