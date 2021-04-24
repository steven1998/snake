package be.uantwerpen.fti.ei;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class SnakeScreen extends JPanel implements ActionListener {
    private final int BoardWidth = 30;
    private final int BoardHeight = 30;
    private final int Size = 10;
    private JLabel statusbar;
    private boolean isRunning;
    private boolean isPaused;
    private Timer timer;
    private int score = 0;
    private int curX = 11;  // coordinates of first head
    private int curY = 7;
    private ArrayList<Point> snake = new ArrayList<>();
    private Point food;
    private Point lastFood = new Point(80,80);
    private int dots = 4;
    private boolean dotadded = true;
    private int dx = 0;     // next delta x
    private int dy = 0;     // next delta y

    public SnakeScreen(Snake parent) {
        setFocusable(true);
        timer = new Timer(250, this);
        timer.start();  // start timer (activates actionPerformed)
        addKeyListener(new TAdapter()); // activate key input
        statusbar = parent.getStatusBar();
        statusbar.setText("press arrow key to start");
        isRunning = false;
        isPaused = false;
        for (int i=0; i<5; i++) // make snake
            snake.add(new Point(i+7, 7));
        food = new Point(5,5);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isRunning) {    // goto next position
            curX = (curX + dx + BoardWidth) % BoardWidth;
            curY = (curY + dy + BoardHeight) % BoardHeight;



            if (curX == food.getLocation().x && curY == food.getLocation().y){
                lastFood.setLocation(food.getLocation().x,food.getLocation().y);
                score = score + 1;
                statusbar.setText("Score: " + String.valueOf(score));
                Random rn = new Random();
                food.setLocation(rn.nextInt(30  + 1),rn.nextInt(30  + 1) );
            }
            else{}
            if(snake.get(dots).x == food.getLocation().x && snake.get(dots).y == food.getLocation().y){
                if (dotadded == false) {
                    dots = dots + 1;
                    dotadded = true;
                    snake.add(new Point(curX,curY));  // add new head
                }
            }
            else{
                snake.remove(0);            // remove tail
                snake.add(new Point(curX,curY));  // add new head
            }

        }
        repaint();
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(0,255,0));
        g2d.fillOval(food.getLocation().x*Size,food.getLocation().y*Size, Size ,Size );
        //head
        g2d.setColor(new Color(255,0,0));
        g2d.fillOval(snake.get(4).x*Size,snake.get(4).y*Size,Size,Size);
        // body
        g2d.setColor(new Color(170, 0, 0));
        for (int i=0; i<dots; i++)
            g2d.fillOval(snake.get(i).x*Size, snake.get(i).y*Size, Size, Size);
        Toolkit.getDefaultToolkit().sync();
        //g2d.dispose();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if (!isRunning) {
                isRunning = true;   // start ranning after key pressed
                statusbar.setText("Score: " + String.valueOf(score));
            }
            int keycode = e.getKeyCode();
            switch (keycode) {
                case KeyEvent.VK_LEFT:
                    dx = -1;
                    dy = 0;
                    break;
                case KeyEvent.VK_RIGHT:
                    dx = 1;
                    dy = 0;
                    break;
                case KeyEvent.VK_DOWN:
                    dx = 0;
                    dy = 1;
                    break;
                case KeyEvent.VK_UP:
                    dx = 0;
                    dy = -1;
                    break;
                case KeyEvent.VK_SPACE:
                    isPaused = !isPaused;
                    if (isPaused) {
                        timer.stop();
                        statusbar.setText("Score: " + String.valueOf(score) + "         Paused ");
                    }
                    else {
                        timer.start();
                        statusbar.setText("Score: " + String.valueOf(score));
                    }
                    break;
            }
        }
    }

}

