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
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Game;

public class ControlGame implements Initializable{
	
	public final static String ROOTEASY = "/resources/Levels/level0.txt";
	public final static String ROOTINTERMEDIUM = "/resources/Levels/level1.txt";
	public final static String ROOTHARD = "/resources/Levels/level2.txt";
	
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
	private MenuItem easy;
	
	@FXML
	private MenuItem intermedium;
	
	@FXML
	private MenuItem hard;
	
	
	private Game game;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
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
	
	public void loadGame (ActionEvent e) throws IOException {
		File file = null;
		
		MenuItem difficult = (MenuItem)(e.getSource());
		
		switch (difficult.getId()) {
		case "easy":
			file = new File(ROOTEASY);
			break;
			
		case "intermedium":
			file = new File(ROOTINTERMEDIUM);
			break;
					
		case "hard":
			file = new File(ROOTHARD);
			break;

		default:
			break;
		}
		
		FileReader reader = null;
		reader = new FileReader(file);
		
		BufferedReader bufferR = new BufferedReader(reader);
		
		String line = bufferR.readLine();
		int level = Integer.parseInt(line);
		
		game = new Game(level);
		
		
		bufferR.close();
		
	}
	
	
	
	
	

} //end of class
