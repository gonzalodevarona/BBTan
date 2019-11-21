/*
* ˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜
* ID
* UNIVERSIDAD ICESI (CALI-COLOMBIA)
* DEPARTAMENTO TIC - ALGORTIMOS Y PROGRAMACIÓN II
* LAB VI
* @AUTHOR: GONZALO DE VARONA <gonzalo.de1@correo.icesi.edu.co>
* @LAST UPDATE DATE: 10 NOVEMBER 2019
* ˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜
*/

package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.*;
import threads.*;


public class ControlGame implements Initializable{
	
	public final static String LEVELS = "resources/Levels";

	
	@FXML
	private MenuItem aboutGame;
	
	@FXML
	private MenuItem loadGame;
	
	@FXML
	private MenuItem saveGame;
	
	@FXML
	private MenuItem exit;
	
	@FXML
	private MenuItem hiScores;
	
	@FXML
	private VBox mainVBox;
	
	@FXML
	private Label bounce;
	
	@FXML
	private Canvas canvas;
	
	
	
	@FXML
	private MenuItem easy;
	
	@FXML
	private MenuItem intermedium;
	
	@FXML
	private MenuItem hard;
	
	
	private Stage stage;
	
	
	
	private Game game;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	public MenuItem getSaveGame() {
		return saveGame;
	}



	public void setSaveGame(MenuItem saveGame) {
		this.saveGame = saveGame;
	}



	public void showGameInfo(ActionEvent e) {
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("/gui/about.fxml"));

			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.setResizable(false);
			stage.show();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void exit(ActionEvent e) {
		try {
			Window node = ( (Node) mainVBox ).getScene().getWindow();
			Stage stage = (Stage) node;
			stage.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void loadGame (ActionEvent e) throws IOException, ClassNotFoundException {
		canvas.setHeight(stage.getHeight());
		canvas.setWidth(stage.getWidth());
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("File reader for BBTan");
		File selected = fileChooser.showOpenDialog(getStage());
		
		if (selected != null && selected.getCanonicalPath().contains(".txt")) {

			FileReader reader = new FileReader(selected);
			BufferedReader bufferR = new BufferedReader(reader);
			
			String line = bufferR.readLine();
			line = bufferR.readLine();
			
			int level = Integer.parseInt(line);
			
			game = new Game(level);
			String[] words = new String[7];
			line = bufferR.readLine();
			
			ThreadUpdate tu = new ThreadUpdate(getCanvas().getGraphicsContext2D(), getStage());
			
			while (line != null) {
				
						
				if (line.charAt(0) != '#') {
					
					words = line.split(" ");
					
					
					
					int radius = Integer.parseInt(words[0]);
					int posX = Integer.parseInt(words[1]);
					int posY = Integer.parseInt(words[2]);
					int waitTime = Integer.parseInt(words[3]);
					String direction = words[4];
					int bounces = Integer.parseInt(words[5]);
					boolean stopped = false;
					
					if (words[6].charAt(0) == 't' || words[6].charAt(0) == 'T') {
						 stopped = true;
					}
					
					Ball ball = new Ball(radius, posX, posY, waitTime, direction, bounces, stopped);
					
					game.addBall(ball);
					
					ThreadBall tb = new ThreadBall(ball, getCanvas().getGraphicsContext2D(), getStage());
					tu.addBall(ball);
					tb.start();
					stage.show();
				}
				
				
				line = bufferR.readLine();
			}
			
			game.countBounces();
			
			
			tu.start();
			
		
			
			
		
			
			bufferR.close();
			reader.close();
		} else {
			VBox vbox = new VBox();
			Label l = new Label("ERROR: Invalid file, only .txt files are accepted. \n Watch it NEXT TIME");
			vbox.getChildren().add(l);
			Stage mssg = new Stage();
			mssg.setScene(new Scene(vbox));
			mssg.show();
		}


		
	}
	

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}
	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public void updateBounces(ActionEvent e) {
		String x = ""+game.getBounces();
		bounce.setText(x);
	}
	
	public void saveGame(ActionEvent e) throws IOException {
		String all = "#Level\n";
		if (getGame() != null) {
			all += getGame().getLevel()+"\n";
			all += "#radius posX posY waiTime direction bounce stopped\n";
			all += getGame().dataSaving();
			GregorianCalendar gc = new GregorianCalendar();
			String myDate = ""+gc.getTimeInMillis();
			String root = LEVELS+"/"+myDate+".txt";
							 
			FileOutputStream fos = new FileOutputStream(root);
			 
			fos.write(all.getBytes());
			fos.close();
			
			
			exit(e);
		} else {
			VBox vbox = new VBox();
			Label l = new Label("ERROR: No game was saved due to there is not a match going on. \n Watch it NEXT TIME");
			vbox.getChildren().add(l);
			Stage mssg = new Stage();
			mssg.setScene(new Scene(vbox));
			mssg.show();
		}
		
		
			
	}
	
	public void showHighScores(ActionEvent e) {
		try {
			if (game != null) {
				VBox vbox = new VBox();
				GridPane gp = new GridPane();
				
				for (int i = 0; i < 10; i++) {
					for (int j = 0; j < 2; j++) {
						Label l = new Label();
						if (j == 0) {
							l.setText(game.getAScore(i).getName());
						} else {
							l.setText(""+game.getAScore(i).getScore());
						}
						gp.add(l, j, i);
					}
				}
				
				Stage stage = new Stage();
				stage.setScene(new Scene(vbox));
				stage.setResizable(false);
				stage.show();
			}
			
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	

	


} //end of class
