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

public class Ball {
	
	public final static String UP = "UP";
	public final static String DOWN = "DOWN";
	public final static String LEFT = "LEFT";
	public final static String RIGHT = "RIGHT";
	
	private int radius;
	private int posX;
	private int posY;
	private int waitTime;
	private String direction;
	private int bounce;
	private boolean stopped;
	
	
	public Ball(int radius, int posX, int posY, int waitTime, String direction, int bounce, boolean stopped) {
		super();
		this.radius = radius;
		this.posX = posX;
		this.posY = posY;
		this.waitTime = waitTime;
		this.direction = direction;
		this.bounce = bounce;
		this.stopped = stopped;
	}


	public int getRadius() {
		return radius;
	}


	public void setRadius(int radius) {
		this.radius = radius;
	}


	public int getPosX() {
		return posX;
	}


	public void setPosX(int posX) {
		this.posX = posX;
	}


	public int getPosY() {
		return posY;
	}


	public void setPosY(int posY) {
		this.posY = posY;
	}


	public int getWaitTime() {
		return waitTime;
	}


	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}


	public String getDirection() {
		return direction;
	}


	public void setDirection(String direction) {
		this.direction = direction;
	}


	public int getBounce() {
		return bounce;
	}


	public void setBounce(int bounce) {
		this.bounce = bounce;
	}


	public boolean isStopped() {
		return stopped;
	}


	public void setStopped(boolean stopped) {
		this.stopped = stopped;
	}
	
	public String toString() {
		
		return ""+getRadius()+" "+getPosX()+" "+getPosY()+" "+getWaitTime()+" "+getDirection()+" "+getBounce()+" "+isStopped();
	}
	
	
	
	
} //end of class;
