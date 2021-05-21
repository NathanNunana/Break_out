/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram implements MouseMotionListener{

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;

/** Runs the Breakout program. */
	public void run() {
		/* You fill this in, along with any subsidiary methods */
		setSize(WIDTH, HEIGHT);
		setUpBricks();
		createPaddle();
		createBall();
		waitForClick();
		moveBall();
		addMouseMotionListener(this);
	}
	
	private void setUpBricks() {
		for(int i =0; i < NBRICK_ROWS;i++) {
			for(int j = 0; j<NBRICKS_PER_ROW;j++) {
				GRect rect = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
//				int center = (APPLICATION_WIDTH - ((BRICK_WIDTH) * NBRICKS_PER_ROW))/2;
				int x = BRICK_SEP + (BRICK_WIDTH +  BRICK_SEP) * j;
				int y = BRICK_Y_OFFSET + (BRICK_HEIGHT +  BRICK_SEP)*i;
				rect.setLocation(x, y);
				rect.setFilled(true);
				setRainbowColors(i, rect);
				add(rect);
			}
		}
	}
	
	private void setRainbowColors(int index, GRect rect) {
		if (index == 0 || index == 1) {
			rect.setColor(Color.RED);
			rect.setColor(Color.RED);
		}else if (index == 2 || index == 3) {
			rect.setColor(Color.ORANGE);
			rect.setColor(Color.ORANGE);
		}else if (index == 4 || index == 5) {
			rect.setColor(Color.YELLOW);
			rect.setColor(Color.YELLOW);
		}else if (index == 6 || index == 7) {
			rect.setColor(Color.GREEN);
			rect.setColor(Color.GREEN);
		}else if (index == 8 || index == 9) {
			rect.setColor(Color.CYAN);
			rect.setColor(Color.CYAN);
		}
		
	}
	
	private void createPaddle() {
		rect = new GRect(PADDLE_WIDTH,PADDLE_HEIGHT);
		int bottom = HEIGHT - PADDLE_Y_OFFSET;
		int center = (WIDTH - PADDLE_WIDTH)/2;
		rect.setLocation(center, bottom);
		rect.setFilled(true);
		add(rect);
	}
	
	/*This part is where I am struggling with*/
	public void mouseMoved(MouseEvent e) {
//		println(e.getX());
		rect.setX(e.getX());
	}
	
	private void createBall() {
		ball = new GOval(diameter, diameter);
		ball.setFilled(true);
		ball.setLocation((WIDTH - diameter)/2, (HEIGHT - diameter)/2);
		add(ball);
	}
	
	private void moveBall() {
		while(true) { 
			vx += rgen.nextDouble(1.0, 3.0);
//			if(rgen.nextBoolean(.5)) vx = -vx;
			vy += 3.0;
			checkCollision();
			ball.move(vx, vy);
			pause(100);
		}
	}
	private void checkCollision(){
		if(ball.getX() == ball.getY()) {
			vx = -vx;
			vy = -vy;
		}else if(ball.getBottomY() >= (double)APPLICATION_HEIGHT || ball.getY() <= 0.0 ) {
			vy = -vy;
			println("vy = " + vy);
		}else if(ball.getRightX() >= (double)APPLICATION_WIDTH || ball.getX() <= 0.0) {
			vx = -vx;
			println("vx =" + vx);
		}
	}
	
	
	private int diameter = BALL_RADIUS * 2;
	private double vx, vy;
	private GOval ball;
	private GRect rect; 
	RandomGenerator rgen = RandomGenerator.getInstance();
}
