import java.util.Scanner;
import java.util.Stack;

/**
 * Created with ♥ by georgeplaton on 31.03.18.
 */
public class BalancedParanthesis {

    /**
     * Will check if the expressions have balanced paranthesis.
     * The solution is to use a stack, where we will push the character in case its an open parathensis, and pop the character if its a closed one.
     * In case they are not matching paranthesis (e.g {}  are matching paranthesis), then we will return false.
     *
     * @param exp    - Must not be null
     * @return
     */
    public static boolean isBalanced(final String exp) {
        Stack<Character> stack = new Stack<Character>();

        /**
         * 1. Traverse the given expression to check matching parenthesis
         */
        for(int i = 0; i < exp.length(); i++)
        {
            /**
             * If the exp[i] is a starting parenthesis then push it
             */
            char expChar =  exp.charAt(i);
            if (expChar == '{' || expChar == '(' || expChar == '[')
                stack.push(expChar);

            /**
             * If exp[i] is an ending parenthesis then pop from stack and check if the popped parenthesis is a matching pair
             */
            if (expChar == '}' || expChar == ')' || expChar == ']')
            {
                /**
                 * If we see an ending parenthesis without a pair then return false
                 */
                if (stack.isEmpty()) {
                    return false;
                } else {
                    /**
                     * Pop the top element from stack, if it is not a pair parenthesis of character then there is a mismatch.
                     * This happens for expressions like {(})
                     */
                    if (!isMatchingPair(stack.pop(), expChar) )
                        return false;
                }
            }

        }

        /**
         * If there is something left in expression then there is a starting parenthesis without a closing parenthesis
         */
        if (stack.isEmpty())
            return true;
        else
            return false;
    };

    /**
     * Will check if the given characters are matching
     * @param character1    - Must not be null
     * @param character2    - Must not be null
     * @return
     */
    private static boolean isMatchingPair(char character1, char character2) {
        if (character1 == '(' && character2 == ')')
            return true;
        else if (character1 == '{' && character2 == '}')
            return true;
        else if (character1 == '[' && character2 == ']')
            return true;
        else
            return false;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int a0 = 0; a0 < t; a0++) {
            String expression = in.next();
            System.out.println( (isBalanced(expression)) ? "YES" : "NO" );
        }
    }
};
