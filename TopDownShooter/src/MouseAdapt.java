import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class MouseAdapt extends MouseAdapter {

	Player p;
	
	public MouseAdapt(Player player)
	{
		p=player;
	}
	public void mousePressed(MouseEvent e)
	{
		p.MousePressed(e);
		
	}


	

}
