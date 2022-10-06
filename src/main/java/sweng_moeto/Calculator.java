package sweng_moeto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Calculator {

	static int precedence(String s){
		switch (s)
		{
		case "+":
		case "-":
			return 1;
		case "*":
			return 2;
		}
		return -1;
	}

	static ArrayList<String> infixToPostFix(String expression){
		ArrayList<String> list = new ArrayList<String>();
		String tmpStr = expression;
		boolean exit = false;

		while(!exit)
		{
			int[] arr = new int[3];
			int add = tmpStr.indexOf('+');
			arr[0] = add;
			int sub = tmpStr.indexOf('-');
			arr[1] = sub;
			int mul = tmpStr.indexOf('*');
			arr[2] = mul;
			Arrays.sort(arr);
			int pos = arr[0];

			if(pos == -1)
			{
				pos = arr[1];
				if(pos == -1)
				{
					pos = arr[2];
				}
			}

			if(pos == 0)
			{
				String str = tmpStr.substring(0, 1);
				list.add(str);
				tmpStr = tmpStr.substring(1);
			}
			else if(pos == -1)
			{
				exit = true;
				list.add(tmpStr);
			}
			else
			{
				String str = tmpStr.substring(0, pos);
				list.add(str);
				tmpStr = tmpStr.substring(pos);
			}
		}



		ArrayList<String> result = new ArrayList<String>();
		Stack<String> stack = new Stack<>();
		for (int i = 0; i < list.size() ; i++) 
		{
			String s = list.get(i);

			//check if char is operator
			if(precedence(s)>0)
			{
				while(stack.isEmpty()==false && precedence(stack.peek())>=precedence(s))
				{
					result.add(stack.pop()) ;
				}
				stack.push(s);
			}else if(s.equals(")"))
			{
				String x = stack.pop();
				while(!x.equals("("))
				{
					result.add(x);
					x = stack.pop();
				}
			}
			else if(s.equals("("))
			{
				stack.push(s);
			}
			else
			{
				//character is neither operator nor ( 
				result.add(s);
			}
		}
		for (int i = 0; i <=stack.size() ; i++) 
		{
			result.add(stack.pop());
		}
		return result;
	}    

	static boolean isOperator(String s)
	{
		switch (s){
		case("+"):
		case("-"):
		case("*"):
			return true;
		}
		return false;
	}

	static int evaluatePostfix(ArrayList<String> postExpression)
	{
		Stack<Integer> postFix = new Stack<>();    // Create postfix stack
		int n = postExpression.size();

		for(int i = 0; i < n; i++)
		{
			if(isOperator(postExpression.get(i)))
			{
				// pop top 2 operands.
				int op1 = postFix.pop();
				int op2 = postFix.pop();

				// evaluate in reverse order i.e. op2 operator op1.
				switch(postExpression.get(i))
				{
				case "+": postFix.push(op2 + op1);
				break;

				case "-": postFix.push(op2 - op1);
				break;

				case "*": postFix.push(op2 * op1);
				break;
				}

			}
			// Current Char is Operand simple push into stack
			else
			{
				// convert to integer
				int operand =  Integer.valueOf(postExpression.get(i));
				postFix.push(operand);
			}
		}

		// Stack at End will contain result.
		return postFix.pop();
	}

	public static void main(String[] args) {
		System.out.println("Please enter an equation you want to solve");
		Scanner input = new Scanner(System.in);
		String equation = input.nextLine();
		ArrayList<String> postExpression = infixToPostFix(equation);
		System.out.println("The answer is " + evaluatePostfix(postExpression));
	}

}
