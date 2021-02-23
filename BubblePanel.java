import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BubblePanel extends JPanel {
	private ArrayList<Bubble> bubbleList;
	private int size = 30;
	private Timer timer;
	private final int DELAY = 33;
	public BubblePanel() {
		bubbleList = new ArrayList<Bubble>();
		
		addMouseListener(new BubbleListener());
		addMouseMotionListener(new BubbleListener());
		addMouseWheelListener(new BubbleListener());
		
		timer = new Timer(DELAY, new BubbleListener());
		
		
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(600, 400));
		
		timer.start();
	}
	public void paintComponent(Graphics page) {
		super.paintComponent(page);
		//draw all the Bubbles from bubbleList
		for (Bubble bubble:bubbleList) {
			page.setColor(bubble.color);
			page.fillOval(bubble.x - bubble.size/2, bubble.y - bubble.size/2, bubble.size, bubble.size);
		}
		//write the number of bubbles on screen
		page.setColor(Color.GREEN);
		page.drawString("Count: " + bubbleList.size(), 5, 15);
	}
	private class BubbleListener implements MouseListener, MouseMotionListener, MouseWheelListener, ActionListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			timer.stop(); //stop the animation
			// add to the bubble list my mouse location
			bubbleList.add(new Bubble(arg0.getX(), arg0.getY(), size));
			repaint();
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			timer.start();

		}
		@Override
		public void mouseDragged(MouseEvent arg0) {
			// add to the bubble list my mouse location
			bubbleList.add(new Bubble(arg0.getX(), arg0.getY(), size));
			repaint();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent arg0) {
			// change the bubble size whenever the mouse wheel is moved
			size += arg0.getWheelRotation();			
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			for (Bubble bubble:bubbleList) {
				bubble.update();
			}
			repaint();
		}		
	}
	
	private class Bubble {
		// A bubble needs an x, y location and a size 
		public int x;
		public int y;
		public int size;
		public Color color;
		public int xspeed;
		public int yspeed;
		private final int MAX_SPEED = 5;
		
		public Bubble(int newX, int newY, int newSize) {
			x = newX;
			y = newY;
			size = newSize;
			color = new Color((float)Math.random(), (float)Math.random(),(float)Math.random());
			xspeed = (int)(Math.random() * MAX_SPEED * 2 - MAX_SPEED);
			yspeed = (int)(Math.random() * MAX_SPEED * 2 - MAX_SPEED);
			if (xspeed == 0 && yspeed == 0) {
				xspeed = 1;
				yspeed = 1;
			}
		}
		public void update() {
			//y -= 5; // float each bubble up pixels per frame;
			x += xspeed;
			y += yspeed;
			//collision detection with the edges of the panel
			if (x < 0 || x > getWidth() )
				xspeed = -1 * xspeed;
			if (y < 0 || y > getHeight() )
				yspeed = -yspeed;
			
		}
	}
}
