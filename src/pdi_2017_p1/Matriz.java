package pdi_2017_p1;

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
}
