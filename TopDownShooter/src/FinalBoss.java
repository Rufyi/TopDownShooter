import java.awt.Graphics2D;
import java.awt.Image;		//for setting up images
import java.awt.Rectangle;	//for making collision-detecting boxes
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import java.util.ArrayList;

public class FinalBoss extends EntityClass{
	Player p;
	int speedX,speedY;
	boolean bremove=false;
	int bossHealth=0;
	public FinalBoss(int x,int y,Player p1)
	{
		super(x, y);
		p=p1;
	}
	public void update()
	{
		move();
		checkCollision();
	}
	public void draw(Graphics2D g2d)
	{
		g2d.drawImage(getUfoImg(),x,y,null);
	}
	public Image getUfoImg()
	{
		ImageIcon ic=new ImageIcon("FinalBoss.png");
		return ic.getImage();
	
	}
	public Rectangle getBounds()
	{
		return new Rectangle (x,y,getUfoImg().getWidth(null),getUfoImg().getHeight(null));
	}
	public void checkCollision()
	{
		if(p.getBounds().intersects(getBounds()))
		{
			p.tempLives--;
			if(p.tempLives==0)
			{
				p.tempLives=20;
				p.lives--;
				if(p.lives==0)
				{
					p.gOver=true;
				}
			}
		}
	if(p.shoot)
	{
		if(p.bullet.getBounds().intersects(getBounds()))
		{
			p.bullet = new Rectangle(x,y,0,0);
			bossHealth++;
			if(bossHealth==50)
			{
				bremove=true;
			}
		}
	}
	}
	public boolean getRemove()
	{
		return bremove;
	}
	public void move()
	{
		
		
		speedX=(p.getX()-x)/100;
		speedY=(p.getY()-y)/100;		
		x+=speedX*2;
		y+=speedY;
		
	}
	

}
