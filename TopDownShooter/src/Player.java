import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
public class Player extends EntityClass{

	private boolean left,right,up,down;
	boolean shoot=false;
	private int speed=3;
	private int bulletSpeedX;
	private int bulletSpeedY;
	Rectangle bullet;
	int score=0;
	int tempLives=20;
	int lives=3;
	int mx,my;
	int ammo=50;
	int enemyHealth=0;
	boolean gOver=false;
	ArrayList<Enemy> enemy = GameFrame.getEnemyList();
	ArrayList<AmmoBox> ammoBox = GameFrame.getAmmoList();
	FinalBoss boss;
	public Player(int x,int y)
	{
		super(x,y);
	
	}
	public void update()
	{
		if(left&&!right)
		{
			x-=speed;
			if(x<0)
				left=false;
		}
		if(!left&&right)
		{
			x+=speed;
			if(x>1360)
				right=false;
		}
		if(up&&!down)
		{
			y-=speed;
			if(y<50)
				up=false;
		}
		if(!up&&down)
		{
			y+=speed;
			if(y>680)
				down=false;
		}
		move();
		checkCollisions();
		
	}
	
	public void noAmmoSound() 
	{
		  try {
		   File file = new File("noAmmo.wav");
		   Clip clip = AudioSystem.getClip();
		   clip.open(AudioSystem.getAudioInputStream(file));
		   clip.start();
		  } catch (Exception e) {
		   System.err.println(e.getMessage());
		  }
	}
	public void playerHit() 
	{
		  try {
		   File file = new File("playerHit.wav");
		   Clip clip = AudioSystem.getClip();
		   clip.open(AudioSystem.getAudioInputStream(file));
		   clip.start();
		  } catch (Exception e) {
		   System.err.println(e.getMessage());
		  }
	}
	public void shootSound() 
	{
		  try {
		   File file = new File("playerShoot.wav");
		   Clip clip = AudioSystem.getClip();
		   clip.open(AudioSystem.getAudioInputStream(file));
		   clip.start();
		  } catch (Exception e) {
		   System.err.println(e.getMessage());
		  }
	}
	public void gameOverShound() 
	{
		  try {
		   File file = new File("playerShoot.wav");
		   Clip clip = AudioSystem.getClip();
		   clip.open(AudioSystem.getAudioInputStream(file));
		   clip.start();
		  } catch (Exception e) {
		   System.err.println(e.getMessage());
		  }
	}
	public void killSound() 
	{
		  try {
		   File file = new File("monsterKilled.wav");
		   Clip clip = AudioSystem.getClip();
		   clip.open(AudioSystem.getAudioInputStream(file));
		   clip.start();
		  } catch (Exception e) {
		   System.err.println(e.getMessage());
		  }
	}
	public void pickUp() 
	{
		  try {
		   File file = new File("pickUp.wav");
		   Clip clip = AudioSystem.getClip();
		   clip.open(AudioSystem.getAudioInputStream(file));
		   clip.start();
		  } catch (Exception e) {
		   System.err.println(e.getMessage());
		  }
	}
	public void draw(Graphics2D g2d)
	{
		g2d.drawImage(getShipImg(),x,y,null);
		if(shoot)
		{
			g2d.setColor(Color.YELLOW);
			g2d.fillOval(bullet.x, bullet.y, bullet.width, bullet.height);
		}
		
		
	}
	
	public Image getShipImg()
	{
		ImageIcon ic=new ImageIcon("Player.png");
		return ic.getImage();
	}
	public Rectangle getBounds()
	{
		return new Rectangle (x,y,getShipImg().getWidth(null),getShipImg().getHeight(null));
	}
	public void checkCollisions()
	{
		for(int i=0;i<enemy.size();i++)
		{
			if(shoot)
			{
				if(bullet.getBounds().intersects(enemy.get(i).getBounds()))
				{
					bullet = new Rectangle(x,y,0,0);
					enemyHealth++;
					if(enemyHealth==3)
					{
						GameFrame.removeEnemy(enemy.get(i));
						
						killSound();
						enemyHealth=0;
					}
				}

			}
		
		}	
		for(int i=0;i<enemy.size();i++)
		{
			
			if(enemy.get(i).getBounds().intersects(getBounds()))
			{
				tempLives--;
				if(tempLives==0)
				{
					tempLives=20;
				if(lives>0)
				{
					lives--;
					playerHit();
				}
					if(lives==0)
					{
						gOver=true;
					}
				}
			}
		
		}
		for(int i=0;i<ammoBox.size();i++)
		{
			
			if(getBounds().intersects(ammoBox.get(i).getBounds()))
			{
				ammo+=30;
				pickUp();
				GameFrame.removeEnemy(ammoBox.get(i));
			}
		
		}	
	}
	public void keyPressed(KeyEvent e)
	{
		int key=e.getKeyCode();
		
		if(key==KeyEvent.VK_LEFT)
		{
			left=true;
			if(x<0)
				left=false;
		
			
		}
		else if(key==KeyEvent.VK_RIGHT)
		{
			right=true;
			if(x>1360)
				right=false;
		}
		else if(key==KeyEvent.VK_UP)
		{
			up=true;
			if(y<50)
				up=false;;
			
		}
		else if(key==KeyEvent.VK_DOWN)
		{
			down=true;
			if(y>680)
				down=false;
		}
	}
	public void keyReleased(KeyEvent e)
	{
		int key=e.getKeyCode();
		
		if(key==KeyEvent.VK_LEFT)
		{
			left=false;
			if(x<0)
				left=false;
		
		
		}
		else if(key==KeyEvent.VK_RIGHT)
		{
			right=false;
			if(x>1360)
				right=false;
		}
		else if(key==KeyEvent.VK_UP)
		{
			up=false;
			if(y<50)
				up=false;;
			
		}
		else if(key==KeyEvent.VK_DOWN)
		{
			down=false;
			if(y>680)
				down=false;
		}
	}
	public void MousePressed(MouseEvent e)
	{
		mx=e.getX()+GameFrame.getCamera().getX();
		my=e.getY()+GameFrame.getCamera().getY();;
		bullet = new Rectangle(x,y,5,5);
		if(ammo>0)
		{
			ammo--;
			shootSound();
			shoot=true;
		}
		if(ammo==0)
		{
			bullet = new Rectangle(x,y,5,5);
			noAmmoSound();
			shoot=false;
		}
		
	}
	public void move()
	{
		bulletSpeedX=(mx-x)/10;
		bulletSpeedY=(my-y)/10;
		if(shoot)
		{
			bullet.y+=bulletSpeedY;
			bullet.x+=bulletSpeedX;
		}
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public int getLives()
	{
		return lives;
	}
	public int getAmmo()
	{
		return ammo;
	}



	
	

}
