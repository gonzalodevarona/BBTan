/*
* ˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜
* ID
* UNIVERSIDAD ICESI (CALI-COLOMBIA)
* DEPARTAMENTO TIC - ALGORTIMOS Y PROGRAMACIÓN II
* LAB VI
* @AUTHOR: GONZALO DE VARONA <gonzalo.de1@correo.icesi.edu.co>
* @LAST UPDATE DATE: 11 NOVEMBER 2019
* ˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜
*/

package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Game {
	
	public final static String SCORES = "resources/scores.priv";
	
	private int level;
	private int bounces;
	
	private ArrayList<Ball> balls;
	private Score[] scores;

	public Game(int level) throws IOException, ClassNotFoundException {
		super();
		this.level = level;
		scores = new Score[10];
		balls = new ArrayList<Ball>();
		//loadScores();
		
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public ArrayList<Ball> getBalls() {
		return balls;
	}

	public void setBalls(ArrayList<Ball> balls) {
		this.balls = balls;
	}
	
	public void addBall(Ball newBall) {
		balls.add(newBall);
	}

	public Score[] getScores() {
		return scores;
	}

	public void setScores(Score[] scores) {
		this.scores = scores;
	}
	
	
	public int getBounces() {
		return bounces;
	}
	
	public void setBounces(int bounces) {
		this.bounces = bounces;
	}
	
	public void addBounce() {
		++bounces;
	}

	public void saveScores() throws FileNotFoundException, IOException {
		File file = new File(SCORES);
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
		oos.writeObject(scores);
		oos.close();
			
		
	}
	
	public boolean loadScores() throws FileNotFoundException, IOException, ClassNotFoundException {
		boolean done = false;
		
		File file = new File(SCORES);
		
		if (file.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			this.scores = (Score[]) ois.readObject();
			ois.close();
			done = true;
		}
		return done;
	}
	
	public void sortScores(){
		Score[] sorted = getScores();
		for (int i = 1; i < sorted.length; i++) {
			for (int j = i; j > 0; j--) {
				
				if (sorted[j].compareTo(sorted[j-1]) < 0) {
					Score temp = sorted[j];
					sorted[j] = sorted[j-1];
					sorted[j-1]= temp;
				} 
			}
		}
		
		this.scores = sorted;
		

	}
	
	public void countBounces() {
		int all = 0;
		
		for (int i = 0; i < balls.size(); i++) {
			all += balls.get(i).getBounce();
		}
		
		setBounces(all);
	}
	
	
	public String dataSaving() {
		String all = "";
		
		for (int i = 0; i < balls.size(); i++) {
			all += balls.get(i).toString()+"\n";
		}
		
		return all;
	}
	
	public Score getAScore(int i) {
		return scores[i];
	}
	

	
	
	
	
	
	

} //end of class
