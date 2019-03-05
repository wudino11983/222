package test11;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;

public class MatrixInverseTest {

	@Test
	public void Test() {
		double[][] matrix = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };
		//double[][] matrix2=MatrixInverse.inverse(matrix);
		double[][] matrix2={ { 1.0, 0.0, 0.0 }, {0.0, 1.0, 0.0 }, {0.0, 0.0, 1.0} };
		System.out.println(matrix2);
		assertEquals(matrix2, MatrixInverse.inverse(matrix));
		
	}
}
