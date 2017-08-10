package pdi_2017_p1;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Matriz {

	public static double[][] proucto(double[][] ma, double[][] mb) {
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

	public static double[][] getMatrixFromText(String string) throws IllegalArgumentException{
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
		for (int row = 0; row < m.length; row++) {
			res += "[";
			for (int column = 0; column < m[row].length; column++) {
				if (column > 0)
					res += ",";
				res += m[row][column];
			}
			res += "]";
		}
		return res;
	}
}
