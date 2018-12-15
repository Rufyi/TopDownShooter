import javax.swing.JFrame;
public class TopDownShooter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Game");
		frame.setSize(1360,680);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.add(new GameFrame());
		frame.setVisible(true);
		

	}

}
