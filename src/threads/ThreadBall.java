/*
* ˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜
* ID
* UNIVERSIDAD ICESI (CALI-COLOMBIA)
* DEPARTAMENTO TIC - ALGORTIMOS Y PROGRAMACIÓN II
* LAB VI
* @AUTHOR: GONZALO DE VARONA <gonzalo.de1@correo.icesi.edu.co>
* @LAST UPDATE DATE: 14 NOVEMBER 2019
* ˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜˜
*/

package threads;

import java.io.File;

import control.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.*;

public class ThreadBall extends Thread{
	
	
	private Ball ball;
	private GraphicsContext gc;
	private Stage stage;
	
	
	public ThreadBall(Ball ball, GraphicsContext gc, Stage stage) {
		super();
		this.ball = ball;
		this.gc = gc;
		this.stage = stage;
	}


	public Ball getBall() {
		return ball;
	}


	public void setBall(Ball ball) {
		this.ball = ball;
	}

	public GraphicsContext getGc() {
		return gc;
	}

	public void setGc(GraphicsContext gc) {
		this.gc = gc;
		
	}
	

	public Stage getStage() {
		return stage;
	}


	public void setStage(Stage stage) {
		this.stage = stage;
	}


	public void stopThread(boolean needClose) {
	
		Thread.currentThread().interrupt();
		ball.setStopped(true);
		
	}


	@Override
	public void run() {
		int dir = 10;
		
		for (int pos = 10; !(getBall().isStopped()); pos+=dir) {
			
			try {
				
				if (getBall().getDirection().equalsIgnoreCase(Ball.LEFT)  || getBall().getDirection().equalsIgnoreCase(Ball.RIGHT)) {
					gc.fillOval(pos, getBall().getPosY(), getBall().getRadius(), getBall().getRadius());
				} else {
					gc.fillOval(getBall().getPosX(), pos, getBall().getRadius(), getBall().getRadius());
				}
				
				
				Thread.sleep(getBall().getWaitTime());
				
				if ( pos > getStage().getWidth() ) {
					dir = 10;
				}
				
				if ( pos < 10 ) {
					dir = -10;
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		
	}
	
	
} //end of class
