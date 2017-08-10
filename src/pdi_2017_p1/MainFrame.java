package pdi_2017_p1;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1589158397755057732L;

	double[][] ma;
	double[][] mb;
	double[][] mc;

	Matriz matriz;

	public MainFrame() {
		ma = new double[2][2];
		mb = new double[2][2];
		mc = new double[2][2];
		mc[0][0] = 0;
		mc[0][1] = 0;
		mc[1][0] = 0;
		mc[1][1] = 0;

		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new MainFrame().setVisible(true);
	}
}
