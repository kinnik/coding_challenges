/** http://www.careercup.com/question?id=4911380140392448
  * Input: A string equation that contains numbers, '+' and '*' 
  * Output: Result as int. 
  * For example: 
  * Input: 3*5+8 (as String) 
  * Output: 23 (as int)
  */

/**
  * Assumption: * binds stronger than +
  */

import java.util.*;

public class Calculator {
    private static final String PLUS  = "+";
    private static final String TIMES = "*";

    public static void main(String args[]) {
        LinkedList<String>  ops = new LinkedList<String>();
        LinkedList<Double>  val = new LinkedList<Double>();

        Scanner s = new Scanner(System.in);
        String token;

        while (s.hasNext()) {
            token = s.next();

            if (token.equals(PLUS) || token.equals(TIMES)) {
                ops.addFirst(token);
            } else {
                Double nextOperand = Double.parseDouble(token);
                
                if (!ops.isEmpty() && ops.getFirst().equals(TIMES)) {
                    Double leftOperand = val.removeFirst();
                    String operator    = ops.removeFirst();
                    val.addFirst(leftOperand * nextOperand);
                } else {
                    val.addFirst(nextOperand);
                }
            }
        }

        while (!ops.isEmpty()) {
            Double  leftOperand = val.removeFirst();
            Double rightOperand = val.removeFirst();
            String     operator = ops.removeFirst();

            if (operator.equals(TIMES)) {
                val.addFirst(leftOperand * rightOperand);
            } else if (operator.equals(PLUS)) {
                val.addFirst(leftOperand + rightOperand);
            }
        }

        System.out.println("Result: " + val.removeFirst());
    }
}
