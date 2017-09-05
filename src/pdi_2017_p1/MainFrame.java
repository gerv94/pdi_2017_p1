package pdi_2017_p1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainFrame extends JFrame implements ActionListener, MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1589158397755057732L;

	private static final String OPEN_FILE = "of";
	private static final String OPEN_FILE_HALF = "of_half";
	private static final String ROTATE_FIRST_QUADRANT = "rotate_first_cuadrant";
	private static final String MIRROR_VERTICAL = "mirror_vertical";
	private static final String MIRROR_HORIZONTAL = "mirror_horizontal";

	private static final String TITLE = "Procesamiento de imagenes - Practica 1";

	private JTextField txtMa00;
	private JTextField txtMa01;
	private JTextField txtMa10;
	private JTextField txtMa11;
	private JTextField txtMb00;
	private JTextField txtMb01;
	private JTextField txtMb10;
	private JTextField txtMb11;
	private JTextField txtMc00;
	private JTextField txtMc01;
	private JTextField txtMc10;
	private JTextField txtMc11;

	private JLabel lblPixel;
	private JLabel lblR;
	private JLabel lblG;
	private JLabel lblB;
	private JLabel lblSatR;
	private JLabel lblSatG;
	private JLabel lblSatB;
	private JLabel pixelColor;

	private ImagePDI guiImg;

	private JFileChooser fileChooser;

	Matriz matriz;

	public MainFrame() {
		initializeComponents();
	}

	private void initializeComponents() {
		setTitle(TITLE);
		setSize(1100, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		int txtColumns = 6;
		txtMa00 = new JTextField();
		txtMa01 = new JTextField();
		txtMa10 = new JTextField();
		txtMa11 = new JTextField();
		txtMa00.setColumns(txtColumns);

		txtMb00 = new JTextField();
		txtMb01 = new JTextField();
		txtMb10 = new JTextField();
		txtMb11 = new JTextField();
		txtMb00.setColumns(txtColumns);

		txtMc00 = new JTextField();
		txtMc01 = new JTextField();
		txtMc10 = new JTextField();
		txtMc11 = new JTextField();
		txtMc00.setEditable(false);
		txtMc01.setEditable(false);
		txtMc10.setEditable(false);
		txtMc11.setEditable(false);
		txtMc00.setColumns(txtColumns);

		// Setting north controls
		JButton btnAddition = new JButton(Matriz.ADDITION);
		JButton btnSubstraction = new JButton(Matriz.SUBSTRACTION);
		JButton btnMultiplication = new JButton(Matriz.MULTIPLICATION);
		JButton btnDivision = new JButton(Matriz.DIVISION);
		btnAddition.addActionListener(this);
		btnSubstraction.addActionListener(this);
		btnMultiplication.addActionListener(this);
		btnDivision.addActionListener(this);
		btnAddition.setActionCommand(Matriz.ADDITION);
		btnSubstraction.setActionCommand(Matriz.SUBSTRACTION);
		btnMultiplication.setActionCommand(Matriz.MULTIPLICATION);
		btnDivision.setActionCommand(Matriz.DIVISION);

		JPanel pnlMa = new JPanel();
		pnlMa.setLayout(new GridLayout(2, 2));
		pnlMa.add(txtMa00);
		pnlMa.add(txtMa01);
		pnlMa.add(txtMa10);
		pnlMa.add(txtMa11);

		JPanel pnlMb = new JPanel();
		pnlMb.setLayout(new GridLayout(2, 2));
		pnlMb.add(txtMb00);
		pnlMb.add(txtMb01);
		pnlMb.add(txtMb10);
		pnlMb.add(txtMb11);

		JPanel pnlMc = new JPanel();
		pnlMc.setLayout(new GridLayout(2, 2));
		pnlMc.add(txtMc00);
		pnlMc.add(txtMc01);
		pnlMc.add(txtMc10);
		pnlMc.add(txtMc11);

		JPanel pnlOper = new JPanel();
		pnlOper.setLayout(new GridLayout(2, 2));
		pnlOper.add(btnAddition);
		pnlOper.add(btnSubstraction);
		pnlOper.add(btnMultiplication);
		pnlOper.add(btnDivision);

		JLabel lblEq = new JLabel("=");

		JPanel pnlNorth = new JPanel();
		pnlNorth.setLayout(new FlowLayout());
		pnlNorth.add(pnlMa);
		pnlNorth.add(pnlOper);
		pnlNorth.add(pnlMb);
		pnlNorth.add(lblEq);
		pnlNorth.add(pnlMc);

		// Setting center controls
		guiImg = new ImagePDI();
		guiImg.setOpaque(true);
		guiImg.setBackground(Color.WHITE);
		guiImg.addMouseListener(this);
		guiImg.addMouseMotionListener(this);

		JScrollPane pnlCenter = new JScrollPane(guiImg);

		// Setting west controls
		pixelColor = new JLabel("                                        ");
		lblPixel = new JLabel("0000,0000");
		lblR = new JLabel("-------");
		lblG = new JLabel("-------");
		lblB = new JLabel("-------");
		JLabel lblBlank = new JLabel(" ");
		JLabel lblSR = new JLabel("Sat R:");
		JLabel lblSG = new JLabel("Sat G:");
		JLabel lblSB = new JLabel("Sat B:");
		lblSatR = new JLabel("-------");
		lblSatG = new JLabel("-------");
		lblSatB = new JLabel("-------");
		pixelColor.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblPixel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblR.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblG.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblB.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblSR.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblSG.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblSB.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblSatR.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblSatG.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblSatB.setAlignmentX(Component.CENTER_ALIGNMENT);
		pixelColor.setOpaque(true);
		pixelColor.setBackground(Color.WHITE);

		JPanel pnlWest = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public Dimension getMaximumSize() {
				return getPreferredSize();
			}
		};
		pnlWest.setLayout(new BoxLayout(pnlWest, BoxLayout.PAGE_AXIS));
		pnlWest.add(pixelColor);
		pnlWest.add(lblPixel);
		pnlWest.add(lblR);
		pnlWest.add(lblG);
		pnlWest.add(lblB);
		pnlWest.add(lblBlank);
		pnlWest.add(lblSR);
		pnlWest.add(lblSatR);
		pnlWest.add(lblSG);
		pnlWest.add(lblSatG);
		pnlWest.add(lblSB);
		pnlWest.add(lblSatB);

		// Setting south controls
		JButton btnOpenFile = new JButton("Change image file");
		btnOpenFile.setActionCommand(OPEN_FILE);
		btnOpenFile.addActionListener(this);

		JButton btnOpenFileToHalf = new JButton("Add image to half");
		btnOpenFileToHalf.setActionCommand(OPEN_FILE_HALF);
		btnOpenFileToHalf.addActionListener(this);

		JButton btnRotateFirstQuadrant = new JButton("Rotate first quadrant");
		btnRotateFirstQuadrant.setActionCommand(ROTATE_FIRST_QUADRANT);
		btnRotateFirstQuadrant.addActionListener(this);

		JButton btnMirrorVertical = new JButton("Mirror vertical");
		btnMirrorVertical.setActionCommand(MIRROR_VERTICAL);
		btnMirrorVertical.addActionListener(this);

		JButton btnMirrorHorizontal = new JButton("Mirror horizontal");
		btnMirrorHorizontal.setActionCommand(MIRROR_HORIZONTAL);
		btnMirrorHorizontal.addActionListener(this);

		JPanel pnlSouth = new JPanel();
		pnlSouth.setLayout(new FlowLayout());
		pnlSouth.add(btnOpenFile);
		pnlSouth.add(btnOpenFileToHalf);
		pnlSouth.add(btnRotateFirstQuadrant);
		pnlSouth.add(btnMirrorVertical);
		pnlSouth.add(btnMirrorHorizontal);

		// Adding panels
		setLayout(new BorderLayout());
		add(pnlNorth, BorderLayout.NORTH);
		add(pnlWest, BorderLayout.WEST);
		add(pnlCenter, BorderLayout.CENTER);
		add(pnlSouth, BorderLayout.SOUTH);

		FileFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
		fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(imageFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);

		displayImage("/home/german/Pictures/dog.jpg");
	}

	private void displayImage(String str) {
		displayImage(new File(str));
	}

	private void displayImage(File f) {
		try {
			guiImg.setImage(ImageIO.read(f));
			double[] res = guiImg.getSaturation();
			lblSatR.setText(String.valueOf(res[0]));
			lblSatG.setText(String.valueOf(res[1]));
			lblSatB.setText(String.valueOf(res[2]));
			setTitle(TITLE + " : " + f.getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addImageToHalf(File f) {
		try {
			guiImg.addImageAtFirstQuadrant(ImageIO.read(f));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private double[][] getMatrixA() {
		double[][] res = new double[2][2];
		res[0][0] = Double.valueOf(txtMa00.getText());
		res[0][1] = Double.valueOf(txtMa01.getText());
		res[1][0] = Double.valueOf(txtMa10.getText());
		res[1][1] = Double.valueOf(txtMa11.getText());
		return res;
	}

	private double[][] getMatrixB() {
		double[][] res = new double[2][2];
		res[0][0] = Double.valueOf(txtMb00.getText());
		res[0][1] = Double.valueOf(txtMb01.getText());
		res[1][0] = Double.valueOf(txtMb10.getText());
		res[1][1] = Double.valueOf(txtMb11.getText());
		return res;
	}

	private void showResult(double[][] res) {
		txtMc00.setText(String.valueOf(res[0][0]));
		txtMc01.setText(String.valueOf(res[0][1]));
		txtMc10.setText(String.valueOf(res[1][0]));
		txtMc11.setText(String.valueOf(res[1][1]));
	}

	private void showPixel(int x, int y) {
		try {
			Color c = new Color(guiImg.getRGB(x, y));
			pixelColor.setBackground(c);
			lblPixel.setText(String.valueOf(x) + "," + String.valueOf(y));
			lblR.setText("R: " + String.valueOf(c.getRed()));
			lblG.setText("G: " + String.valueOf(c.getGreen()));
			lblB.setText("B: " + String.valueOf(c.getBlue()));
		} catch (Exception e) {
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case Matriz.ADDITION:
			showResult(Matriz.addition(getMatrixA(), getMatrixB()));
			break;
		case Matriz.SUBSTRACTION:
			showResult(Matriz.substraction(getMatrixA(), getMatrixB()));
			break;
		case Matriz.MULTIPLICATION:
			showResult(Matriz.multiplication(getMatrixA(), getMatrixB()));
			break;
		case Matriz.DIVISION:
			showResult(Matriz.division(getMatrixA(), getMatrixB()));
			break;
		case OPEN_FILE:
			if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
				displayImage(fileChooser.getSelectedFile());
			break;
		case OPEN_FILE_HALF:
			if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
				addImageToHalf(fileChooser.getSelectedFile());
			break;
		case ROTATE_FIRST_QUADRANT:
			guiImg.rotateFirstQuadrant();
			break;
		case MIRROR_VERTICAL:
			guiImg.mirrorVertical();
			break;
		case MIRROR_HORIZONTAL:
			guiImg.mirrorHorizontal();
			break;
		default:
			break;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		showPixel(e.getX(), e.getY());
	}

	public static void main(String[] args) {
		new MainFrame().setVisible(true);
	}
}
