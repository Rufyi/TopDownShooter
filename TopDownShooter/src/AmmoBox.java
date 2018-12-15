import java.awt.Graphics2D;
import java.awt.Image;		//for setting up images
import java.awt.Rectangle;	//for making collision-detecting boxes
import javax.swing.ImageIcon;
import java.util.ArrayList;

public class AmmoBox extends EntityClass{

	public AmmoBox(int x,int y)
	{
		super(x, y);
	}
	public void update()
	{
		
	}
	public void draw(Graphics2D g2d)
	{
		g2d.drawImage(getUfoImg(),x,y,null);
	}
	public Image getUfoImg()
	{
		ImageIcon ic=new ImageIcon("ammoBox.png");
		return ic.getImage();
	
	}
	public Rectangle getBounds()
	{
		return new Rectangle (x,y,getUfoImg().getWidth(null),getUfoImg().getHeight(null));
	}

}
