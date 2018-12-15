
public class Camera extends EntityClass{
	int x, y;
	Player player;
	
	public Camera(Player p, int x,int y)
	{
		super(x,y);
		player=p;
	}
	public void update()
	{
		x=player.getX()-1360/2;
		if(x<=0)
			x=0;
		if(x>=180)
			x=180;
		y=player.getY()-680/2;
		if(y<=0)
			y=0;
		if(y>=220)
			y=220;
		
			
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}
