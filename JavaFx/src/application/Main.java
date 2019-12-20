package application;
	
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Main extends Application {
	public String userName;
	public int userScore = 0;
	public boolean gameOver = false;
	
	public long start;
	public long end;
	public long timeTaken;
	
	
	
	public List<String[]> stack = new ArrayList<String[]>();
	
	
	public List<Object[]> questions = new fileInput().getQuestions();
	public DatabaseFunctions db = new DatabaseFunctions();
	public boolean correctAnswer = false;
	
	public Object ques1;
	public Object ques2;
	
	public Object ans1;
	public Object ans2;
	public Object ans3;
	public Object ans4;
	
	int qNum = 0;
	
	public boolean timerDone = false;
	
	private Stage thePrimaryStage;
	
	
	@Override
	public void start(Stage primaryStage) {
		this.thePrimaryStage = primaryStage;
		try {
			GridPane root = startPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			thePrimaryStage.setScene(scene);
			thePrimaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private GridPane GameOver() {
		
		GridPane root = new GridPane();
		root.setAlignment(Pos.TOP_CENTER);
		root.setHgap(10);
		root.setVgap(10);
		root.setPadding(new Insets(25,25,25,25));
		
		Text scenetitle = new Text("Game Over");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		root.add(scenetitle, 0, 0, 2, 1);
		
		Text text1 = new Text("Question#: "+stack.get(0)[0]+", UserName: "+stack.get(0)[1]+", Score: "+stack.get(0)[2]+", Time: "+stack.get(0)[3]);
		root.add(text1, 1, 2);
		Text text2 = new Text("Question#: "+stack.get(1)[0]+", UserName: "+stack.get(1)[1]+", Score: "+stack.get(1)[2]+", Time: "+stack.get(1)[3]);   
		root.add(text2, 1, 3);
		Text text3 = new Text("Question#: "+stack.get(2)[0]+", UserName: "+stack.get(2)[1]+", Score: "+stack.get(2)[2]+", Time: "+stack.get(2)[3]);   
		root.add(text3, 1, 4);
		Text text4 = new Text("Question#: "+stack.get(3)[0]+", UserName: "+stack.get(3)[1]+", Score: "+stack.get(3)[2]+", Time: "+stack.get(3)[3]);   
		root.add(text4, 1, 5);
		Text text5 = new Text("Question#: "+stack.get(4)[0]+", UserName: "+stack.get(4)[1]+", Score: "+stack.get(4)[2]+", Time: "+stack.get(4)[3]);   
		root.add(text5, 1, 6);
		Text text6 = new Text("Question#: "+stack.get(5)[0]+", UserName: "+stack.get(5)[1]+", Score: "+stack.get(5)[2]+", Time: "+stack.get(5)[3]);   
		root.add(text6, 1, 7);
		Text text7 = new Text("Question#: "+stack.get(6)[0]+", UserName: "+stack.get(6)[1]+", Score: "+stack.get(6)[2]+", Time: "+stack.get(6)[3]);   
		root.add(text7, 1, 8);
		Text text8 = new Text("Question#: "+stack.get(7)[0]+", UserName: "+stack.get(7)[1]+", Score: "+stack.get(7)[2]+", Time: "+stack.get(7)[3]);   
		root.add(text8, 1, 9);
		Text text9 = new Text("Question#: "+stack.get(8)[0]+", UserName: "+stack.get(8)[1]+", Score: "+stack.get(8)[2]+", Time: "+stack.get(8)[3]);   
		root.add(text9, 1, 10);
		Text text10 = new Text("Question#: "+stack.get(9)[0]+", UserName: "+stack.get(9)[1]+", Score: "+stack.get(9)[2]+", Time: "+stack.get(9)[3]);   
		root.add(text10, 1, 11);
		
		Button btn = new Button("Start New Game");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		root.add(hbBtn, 1, 12);
		
		for(int i = 0; i < stack.size(); i++) {
			System.out.println(stack.get(i)[1]);
		}
		
		final Text actiontarget = new Text();
		root.add(actiontarget, 1, 6);
		
		btn.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		        changeScene(startPane());
		        
		    }
		});
		
		return root;
	}
		
	private BorderPane Game() {
		
		
		start = System.currentTimeMillis();
        
		ques1 = questions.get(qNum)[0];
		ques2 = questions.get(qNum)[1];
		ans1 = questions.get(qNum)[2];
		ans2 = questions.get(qNum)[3];
		ans3 = questions.get(qNum)[4];
		ans4 = questions.get(qNum)[5];
		
		BorderPane border = new BorderPane();
		
		HBox topBox = addTopBox();
		border.setTop(topBox);
		
		GridPane leftBox = addLeftBox();
		border.setLeft(leftBox);
		
		VBox rightBox = addRightBox();
		border.setRight(rightBox);
		
		GridPane bottomBox = addBottomBox();
		border.setBottom(bottomBox);
	
		System.out.println(ans1.getClass().getName());
		System.out.println(ans2.getClass().getName());
		System.out.println(ans3.getClass().getName());
		System.out.println(ans4.getClass().getName());
		System.out.println("------------------------");
		
		return border;
		
	}
	
	
	
	private GridPane addBottomBox() {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_CENTER);
		grid.setStyle("-fx-background-color: #336699;");
	    grid.setHgap(10);
	    grid.setVgap(10);
	    
	    
	    return grid;
	}
	
	private GridPane addLeftBox() {
		
	    
		
	    GridPane grid = new GridPane();
	    grid.setAlignment(Pos.TOP_CENTER);
	    grid.setHgap(10);
	    grid.setVgap(10);
	    
	    grid.setPadding(new Insets(25, 25, 25, 25));
	    
	    Text object1 = new Text(ques1.toString());
	    grid.add(object1, 0, 4);
	    GridPane.setHalignment(object1, HPos.CENTER);
	    
	    Text plusSign = new Text("+");
	    grid.add(plusSign, 1, 4);
	    GridPane.setHalignment(plusSign, HPos.CENTER);
	    
	    Text object2 = new Text(ques2.toString());
	    grid.add(object2, 2, 4);
	    GridPane.setHalignment(object2, HPos.CENTER);
	    
	    
	    Button ans1 = new Button(this.ans1.toString());
	    grid.add(ans1, 0, 8);
	    Button ans2 = new Button(this.ans2.toString());
	    grid.add(ans2, 1, 8);
	    Button ans3 = new Button(this.ans3.toString());
	    grid.add(ans3, 2, 8);
	    Button ans4 = new Button(this.ans4.toString());
	    grid.add(ans4, 3, 8);
	    
	    ans1.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
		    	if(qNum < 9) {
		    		nextQuestion(Main.this.ans1);
		    	} else {
		    		endGame(Main.this.ans1);
		    	}
		    }
		});
	    ans2.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
		    	if(qNum < 9) {
		    		nextQuestion(Main.this.ans2);
		    	} else {
		    		endGame(Main.this.ans2);
		    	}
		    }
		});
	    ans3.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
		    	if(qNum < 9) {
		    		nextQuestion(Main.this.ans3);
		    	} else {
		    		endGame(Main.this.ans3);
		    	}
		    }
		});
	    ans4.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
		    	if(qNum < 9) {
		    		nextQuestion(Main.this.ans4);
		    	} else {
		    		endGame(Main.this.ans4);
		    	}
		    }
		});
	    

	    return grid;
	}
	
	private VBox addRightBox() {
		VBox vbox = new VBox();
	    vbox.setPadding(new Insets(15, 12, 15, 12));
	    vbox.setSpacing(75);
	    vbox.setStyle("-fx-background-color: #ccc;");
	    vbox.setAlignment(Pos.CENTER);
	    

	    return vbox;
	}

	public HBox addTopBox() {
	    HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(75);
	    hbox.setStyle("-fx-background-color: #336699;");
	    
	    
	    
	    Text userNameText = new Text(userName);
	    
	    Text userPoints = new Text(Integer.toString(userScore));
	    
	    Text countDown = new Text("Count Down");
	    
	    
	    hbox.getChildren().addAll(userNameText,userPoints, countDown);

	    return hbox;
	}
	
	public GridPane startPane() {
		List<Person> topTen = DatabaseFunctions.findTopTen();
		GridPane root = new GridPane();
		root.setAlignment(Pos.CENTER);
		root.setHgap(10);
		root.setVgap(10);
		root.setPadding(new Insets(25,25,25,25));
		
		Text scenetitle = new Text("Welcome");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		root.add(scenetitle, 0, 0, 2, 1);

		Label userName = new Label("Enter User Name:");
		root.add(userName, 0, 1);

		TextField userTextField = new TextField();
		root.add(userTextField, 1, 1);
		
		Button btn = new Button("Start");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		root.add(hbBtn, 1, 2);
		
		Button btn2 = new Button("Report Top Ten");
		HBox hbBtn2 = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_LEFT);
		hbBtn.getChildren().add(btn2);
		root.add(hbBtn2, 2, 2);
		
		final Text actiontarget = new Text();
		root.add(actiontarget, 1, 6);
		
		btn2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				try {
					PrintWriter writer = new PrintWriter("C:/testfile/results.txt", "UTF-8");
					for(int i = 0; i < topTen.size(); i++) {
						writer.println("User Name: "+topTen.get(i).userName+" - Score: "+topTen.get(i).userScore);
					}
					writer.close();
					actiontarget.setText("Successfully created report at C:/testfile/results.txt");
				} catch (FileNotFoundException | UnsupportedEncodingException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
		
		btn.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		        if(userTextField.getText().length() == 0) {
		        	actiontarget.setText("Please enter a name.");
		        } else {
		        	Main.this.userName = userTextField.getText();
		        	changeScene(Game());
		        }
		        
		    }
		});
		
		return root;
	}

	protected void changeScene(Pane scene) {
		Parent pane = scene;
		thePrimaryStage.getScene().setRoot(pane);
	}

	public static void main(String[] args) {
		launch(args);
	}

	public Object getAnswer() {
		addObjects<Object,Object> adder = new addObjects<Object,Object>(ques1,ques2);
		return adder.add();
	}
	
	public boolean checkAnswer(Object a) {
		Object answer = getAnswer();
		if(a.equals(answer)) {
			return true;
		}
		else {
			return false;
		}
	}

	public void endGame(Object o) {
		stackFunction(o, qNum);
		qNum = 0;
		gameOver = true;
		Person person = new Person(userName, userScore, 0);
		db.insertPerson(person);
		userScore = 0;
		changeScene(GameOver());
	}

	public void nextQuestion(Object o) {
		
		
		stackFunction(o, qNum);
		qNum++;
		System.out.println(qNum);
		changeScene(Game());
	}

	public void stackFunction(Object o, int qNum) {
		System.out.println(qNum);
		String[] stackUser = new String[4];
		int quesScore = 0;
		if(checkAnswer(o)) {
			quesScore = 1;
			userScore++;
		}
		end = System.currentTimeMillis();
		timeTaken = end - start;
		stackUser[0] = Integer.toString(qNum+1);
		stackUser[1] = userName;
		stackUser[2] = Integer.toString(quesScore);
		stackUser[3] = Long.toString(timeTaken);
		stack.add(stackUser);
	}
}