import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.Timer;

import javax.swing.*;

public class Snakegame extends JPanel implements ActionListener,KeyListener{
 

     private class Tile {
      int x;
      int y;

      Tile (int x, int y) { 
        this.x = x;
        this.y = y;
        }
        
     }
        int borderwidth;
        int borderheight;
        int tilesize=25;

        Tile snakehead;
        boolean gameover=false;

        ArrayList<Tile>snakebody;
        Tile food;
        Random random;
       // game logic
        Timer gameLoop;
        int velocityX;
        int velocityY;

        Snakegame(int borderwidth,int borderheight){
            this.borderwidth = borderwidth;
            this.borderheight = borderheight;
            setPreferredSize(new Dimension(this.borderwidth,this.borderheight));
            setBackground(Color.BLACK);
            addKeyListener(this);
            setFocusable(true);

            snakehead=new Tile(5,5);
            snakebody=new ArrayList<Tile>();
            food =new Tile(10, 10);
            random= new Random();
            placefood();

            velocityX=0;
            velocityY=0;
            gameLoop =new Timer(100, this); 
            gameLoop.start();
        }
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            draw(g);
        }
        public void draw(Graphics g){

            // for(int i=0;i<borderwidth/tilesize;i++){
            //     g.drawLine(i*tilesize, 0, i*tilesize, borderheight);
            //     g.drawLine(0, i*tilesize, borderwidth, i*tilesize);

            // }
            //food
            g.setColor(Color.red);
            g.fill3DRect(food.x*tilesize,food.y*tilesize ,tilesize,tilesize,true );
            //snakehead
            g.setColor(Color.green);
            g.fill3DRect(snakehead.x*tilesize,snakehead.y*tilesize , tilesize, tilesize,true);

            //Snakebody
            for(int i=0 ;i<snakebody.size();i++){
                Tile snakepart =snakebody.get(i);
                g.fill3DRect(snakepart.x*tilesize, snakepart.y*tilesize, tilesize, tilesize,true);
            }
            //score
            g.setFont(new Font("Arial",Font.BOLD,16));
            if(gameover){
                g.setColor(Color.red);
                g.drawString("Game  Over:" +String.valueOf(snakebody.size()),tilesize-16,tilesize);
            }else{
                g.drawString("SCore: "+String.valueOf(snakebody.size()),tilesize-16,tilesize);
            }
        }
        public void placefood(){
    food.x=random.nextInt(borderwidth/tilesize);
    food.y=random.nextInt(borderheight/tilesize);
        }
        public boolean collision(Tile a, Tile b) {
            return a.x==b.x && a.y==b.y;
         }
        public void move(){
            //eat food
            if(collision(snakehead, food)){
                snakebody.add(new Tile(food.x,food.y));
                placefood();
            }

            for(int i=snakebody.size()-1;i>=0;i--){
                Tile snakepart=snakebody.get(i);
                if(i==0){
                    snakepart.x=snakehead.x;
                    snakepart.y=snakehead.y;
                }else{
                    Tile prevsnakepart =snakebody.get(i-1);
                    snakepart.x=prevsnakepart.x;
                    snakepart.y=prevsnakepart.y;
                }
            }

            snakehead.x +=velocityX;
            snakehead.y +=velocityY;
            // game over 
            for(int i=0;i<snakebody.size();i++){
                Tile snakepart=snakebody.get(i);
                if(collision(snakehead, snakepart)){
                    gameover=true;
                }
            }
            if(snakehead.x*tilesize<0 || snakehead.x*tilesize>borderwidth || snakehead.y*tilesize<0 ||snakehead.y>borderheight){
                gameover=true;
            }
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            move();
            repaint();
            if(gameover){
                gameLoop.stop();
            }
        }
        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_UP && velocityY !=1){
                velocityX =0;
                velocityY =-1;
            }
           else if(e.getKeyCode() == KeyEvent.VK_DOWN  &&velocityY !=-1){
                velocityX =0;
                velocityY =1;
            }
            else if(e.getKeyCode() == KeyEvent.VK_LEFT && velocityX !=1){
                velocityX =-1;
                velocityY =-0;
            }
             else if(e.getKeyCode() == KeyEvent.VK_RIGHT && velocityX !=1){
                velocityX =1;
                velocityY =0;
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {    
        }
        @Override
        public void keyReleased(KeyEvent e) {
        }
      
 }

