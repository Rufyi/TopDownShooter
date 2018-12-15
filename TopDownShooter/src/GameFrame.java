import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.ActionEvent;      
import java.awt.event.ActionListener;  		
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
public class GameFrame extends JPanel implements ActionListener{

	Timer mainTimer;
	Random rand = new Random();
	Player player;
	static Camera camera;
	static ArrayList<Enemy>enemy=new ArrayList<Enemy>();
	static ArrayList<AmmoBox>ammo=new ArrayList<AmmoBox>();
	FinalBoss boss;
	int waveNumber=1;
	int roundNumber=1;
	boolean drawBoss=false;
	boolean playOnce=true;
	boolean playOnce2=true;
	boolean stopBGM=false;
	BackgroundImage image;
	//1529 865
	public GameFrame()
	{
		setFocusable(true);
		player = new Player(600,300);
		image = new BackgroundImage(0,0);
		addMouseListener(new MouseAdapt(player));
		addKeyListener(new KeyAdapt(player));
		camera = new Camera(player,0,0);
		mainTimer = new Timer(20,this);
		mainTimer.start();
		setEnemy();
	}
	public void setEnemy()
	{
			for(int i=0;i<12;i++)
			{
				enemy.add(new Enemy(rand.nextInt(50000),rand.nextInt(50000),player));
			}
			for(int i=0;i<5;i++)
			{
				ammo.add(new AmmoBox(rand.nextInt(1300),rand.nextInt(600)));
			}
		
	}
	
	public void BGM() 
	{
		  try {
		   File file = new File("BGM.wav");
		   Clip clip = AudioSystem.getClip();
		   clip.open(AudioSystem.getAudioInputStream(file));
		   clip.start();
		   if(stopBGM)
			   clip.stop();
		  } catch (Exception e) {
		   System.err.println(e.getMessage());
		  }
	}
	public void gameOverBGM() 
	{
		  try {
		   File file = new File("gOverBGM.wav");
		   Clip clip = AudioSystem.getClip();
		   clip.open(AudioSystem.getAudioInputStream(file));
		   clip.start();
		  } catch (Exception e) {
		   System.err.println(e.getMessage());
		  }
	}

	public void actionPerformed(ActionEvent arg0)
	{
		player.update();
		if(drawBoss)
		{
			boss.update();
		}
		camera.update();
		for(int i=0;i<enemy.size();i++)
		{
			Enemy tempEnemy = enemy.get(i);
			tempEnemy.update();
		}
		checkEmpty();
		repaint();
		if(playOnce)
		{
			if(!player.gOver)
			{
				BGM();
				playOnce=false;
			}
		}
		if(playOnce2)
		{
			if(player.gOver)
			{
				stopBGM=true;
				gameOverBGM();
				playOnce2=false;
			}
		}
	}
	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g.translate(-camera.getX(),-camera.getY());
		image.draw(g2d);
		for(int i=0;i<ammo.size();i++)
		{
			AmmoBox tempAmmo = ammo.get(i);
			tempAmmo.draw(g2d);
		}
		player.draw(g2d);
		if(drawBoss)
		{
			if(!boss.bremove)
			{
				boss.draw(g2d);
			}
		}
		for(int i=0;i<enemy.size();i++)
		{
			Enemy tempEnemy = enemy.get(i);
			tempEnemy.draw(g2d);
		}
		g.translate(camera.getX(),camera.getY());
		if(!player.gOver)
		{
		g.setColor(Color.black);
		g.fillRect(0, 0, 300, 30);
		g.setColor(Color.green);
		g.fillRect(0, 0, 100*player.getLives(), 30);
		g.setFont(new Font("TRAJAN PRO", Font.PLAIN, 30));
		g.setColor(Color.white);
		g.drawString("Ammo: "+player.getAmmo(), 8, 60);
		g.drawString("WAVE: "+waveNumber, 600, 60);
		}
		if(player.gOver)
		{
			
			g.setFont(new Font("TRAJAN PRO",Font.PLAIN,100));
			g.setColor(Color.black);
			g.fillRect(0,0,1360,680);
			g.setColor(Color.white);
			g.drawString("GAME", 500, 300);
			g.drawString("OVER", 500, 400);
		}
	}
	public void checkEmpty()
	{
		if(enemy.size()==0)
		{
			roundNumber++;
			if(waveNumber==1)
			{
				for(int i=0;i<12;i++)
				{
					enemy.add(new Enemy(rand.nextInt(50000),rand.nextInt(50000),player));
				}
			}
			if(waveNumber==2)
			{
				for(int i=0;i<17;i++)
				{
					enemy.add(new Enemy(rand.nextInt(50000),rand.nextInt(50000),player));
				}
			}
			if(waveNumber==3)
			{
				for(int i=0;i<15;i++)
				{
					enemy.add(new Enemy(rand.nextInt(50000),rand.nextInt(50000),player));
				}
				boss= new FinalBoss(rand.nextInt(50000),rand.nextInt(50000),player);
				drawBoss=true;
	
				
			}
		}
		if(waveNumber==4)
		{
			JOptionPane.showMessageDialog(null,"YOU WIN",null,JOptionPane.WARNING_MESSAGE);
			System.exit(0);

		}
		if(roundNumber==3)
		{
			waveNumber++;
			for(int i=0;i<3;i++)
			{
				ammo.add(new AmmoBox(rand.nextInt(1300),rand.nextInt(600)));
			}
			roundNumber=1;
		}
	}
	public static ArrayList<Enemy> getEnemyList()
	{
        return enemy;	
	}
	public static ArrayList<AmmoBox> getAmmoList()
	{
        return ammo;	
	}
	public static Camera getCamera()
	{
        return camera;	
	}
	public static void removeEnemy(AmmoBox u)
	{
        ammo.remove(u);	
	}
	public static void removeEnemy(Enemy u)
	 {
		 enemy.remove(u);
	 }



   
    

	
		
	
}
