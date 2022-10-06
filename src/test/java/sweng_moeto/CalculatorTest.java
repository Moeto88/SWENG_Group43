package sweng_moeto;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class CalculatorTest {

	@Test
	public void testInfixToPostFix() {
		String equation = "14*3-7*35+22-56+10";
		String[] expectedResult = {"14", "3", "*", "7", "35", "*", "-", "22", "+", "56", "-", "100", "+"};
		assertEquals(Arrays.toString(expectedResult), Calculator.infixToPostFix(equation).toString());
	}
	
	@Test
	public void testEvaluatePostfix() {
		String equation = "14*3-7*35+22-56+100";
		int expectedResult = -137; 
		assertEquals(expectedResult, Calculator.evaluatePostfix(Calculator.infixToPostFix(equation)));
	}
	

}
