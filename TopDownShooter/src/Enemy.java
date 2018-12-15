import java.awt.Graphics2D;
import java.awt.Image;		//for setting up images
import java.awt.Rectangle;	//for making collision-detecting boxes
import javax.swing.ImageIcon;
import java.util.ArrayList;

public class Enemy extends EntityClass{

	Player p;
	ArrayList<Enemy> enemy = GameFrame.getEnemyList();
	int speedX;
	boolean eMove=true;
	int speedY;
	int speed=1;
	boolean finalWave=false;
	public Enemy(int x,int y,Player p1)
	{
		super(x, y);
		p=p1;
	}
	public void update()
	{
		checkCollisions();
		move();
	}
	public void draw(Graphics2D g2d)
	{
		g2d.drawImage(getUfoImg(),x,y,null);
	}
	public Image getUfoImg()
	{
		ImageIcon ic=new ImageIcon("Enemy.png");
		return ic.getImage();
	
	}
	public Rectangle getBounds()
	{
		return new Rectangle (x,y,getUfoImg().getWidth(null),getUfoImg().getHeight(null));
	}
	public void checkCollisions()
	{
		for(int i=0;i<enemy.size();i++)
		{	
			Enemy tempEnemy1 = enemy.get(i);
			for(int j=i+1;j<enemy.size();j++)
			{
				Enemy tempEnemy2 = enemy.get(j);
				if(tempEnemy1.getBounds().intersects(tempEnemy2.getBounds()))
				{
					if(tempEnemy1.x > tempEnemy2.x){
						tempEnemy1.x+=1;
                    }
                    if(tempEnemy1.x < tempEnemy2.x){
                    	tempEnemy1.x-=1;;
                    }
                    if(tempEnemy2.x > tempEnemy1.x){
                    	tempEnemy2.x+=1;
                    }
                    if(tempEnemy2.x < tempEnemy1.x){
                    	tempEnemy2.x-=1;
                    }
                    if(tempEnemy1.y > tempEnemy2.y){
                    	tempEnemy1.y+=1;
                    }
                    if(tempEnemy1.y < tempEnemy2.y){
                    	tempEnemy1.y-=1;
                    }
                    if(tempEnemy2.y > tempEnemy1.y){
                    	tempEnemy2.y+=1;
                    }
                    if(tempEnemy2.y < tempEnemy1.y){
                    	tempEnemy2.y-=1;
                    }
				}
			}
		}
	}
	public void move()
	{
		
		
		speedX=(p.getX()-x)/100;
		speedY=(p.getY()-y)/100;		
		x+=speedX*2;
		y+=speedY;
		
	}

}
