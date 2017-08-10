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
		initializeComponents();

		ma = new double[2][2];
		mb = new double[2][2];
		mc = new double[2][2];
		mc[0][0] = 0;
		mc[0][1] = 0;
		mc[1][0] = 0;
		mc[1][1] = 0;
	}

	private void initializeComponents() {
		setTitle("Procesamiento de imagenes - Practica 1");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	public static void main(String[] args) {
		//new MainFrame().setVisible(true);
		double[][] m = Matriz.getMatrixFromText("[1.0,2.2,3.3][4,5,6][7,8,9.0]");
		System.out.println(Matriz.getStringFromMatrix(m));
	}
}
