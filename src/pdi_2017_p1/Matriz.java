package pdi_2017_p1;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Matriz {
	public static final String ADDITION = "+";
	public static final String SUBSTRACTION = "-";
	public static final String MULTIPLICATION = "x";
	public static final String DIVISION = "/";
	
	public static double[][] addition(double[][] ma, double[][] mb) {
		double [][] res = ma;
		res[0][0] += mb[0][0];
		res[0][1] += mb[0][1];
		res[1][0] += mb[1][0];
		res[1][1] += mb[1][1];
		return res;
	}
	
	public static double[][] substraction(double[][] ma, double[][] mb) {
		double [][] res = ma;
		res[0][0] -= mb[0][0];
		res[0][1] -= mb[0][1];
		res[1][0] -= mb[1][0];
		res[1][1] -= mb[1][1];
		return res;
	}

	public static double[][] multiplication(double[][] ma, double[][] mb) {
		int aRows = ma.length;
		int aColumns = ma[0].length;
		int bRows = mb.length;
		int bColumns = mb[0].length;

		if (aColumns != bRows)
			throw new IllegalArgumentException("A:Rows: " + aColumns + " did not match B:Columns " + bRows + ".");

		double[][] mc = new double[aRows][bColumns];
		for (int i = 0; i < aRows; i++)
			for (int j = 0; j < bColumns; j++)
				mc[i][j] = 0;

		for (int i = 0; i < aRows; i++)
			for (int j = 0; j < bColumns; j++)
				for (int k = 0; k < aColumns; k++)
					mc[i][j] += ma[i][k] * mb[k][j];
		return mc;
	}
	
	public static double[][] multiplication(double[][] ma, double d) {
		double[][] res = ma;
		res[0][0] *= -d;
		res[0][1] *= -d;
		res[1][0] *= -d;
		res[1][1] *= -d;
		return res;
	}
	
	public static double[][] division(double[][] ma, double[][] mb) {
		return multiplication(ma, inverse(mb));
	}

	public static double[][] inverse(double[][] m) {
		double[][] res = m;
		if (res.length != 2 || res[0].length != 2)
			throw new IllegalArgumentException("Inverse is only supported for 2x2 matrices.");

		double temp;
		double div = 1 / (res[0][0] * res[1][1] - res[0][1] * res[1][0]);
		temp = res[0][0];
		res[0][0] = div * res[1][1];
		res[1][1] = div * temp;
		res[0][1] *= -div;
		res[1][0] *= -div;
		return res;
	}

	public static double[][] getMatrixFromText(String string) throws IllegalArgumentException {
		Pattern pattern = Pattern.compile("\\[(\\d+(.\\d+)?(,\\d+(.\\d+)?)*)\\]");
		Matcher matcher = pattern.matcher(string);
		List<String[]> matches = new ArrayList<>();
		String[] temp;
		String fullMatch = "";
		int columns = 0;
		while (matcher.find()) {
			fullMatch += matcher.group();
			temp = matcher.group(1).split(",");
			matches.add(temp);
			if (columns == 0)
				columns = temp.length;
			else if (columns != temp.length)
				throw new IllegalArgumentException("The text can not be formatted due to incorrect form of matrix.");

		}

		if (matches.size() == 0 || !fullMatch.equals(string))
			throw new IllegalArgumentException("The text can not be formatted.");

		double[][] res = new double[columns][matches.size()];
		for (int row = 0; row < matches.size(); row++)
			for (int column = 0; column < columns; column++)
				res[row][column] = Double.valueOf(matches.get(row)[column]);

		return res;
	}

	public static String getStringFromMatrix(double[][] m) {
		String res = "";
		boolean comma;
		for(double[] row : m) {
			res += "[";
			comma = false;
			for(double val : row) {
				if(comma)
					res += ",";
				res += val;
				comma = true;
			}
			res += "]";
		}
		return res;
	}
}
