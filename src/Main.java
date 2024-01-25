import java.util.LinkedList;

public class Main
{
    // Return the array [gcd(a, b), x, y], where ax + by = gcd(a, b)
    public static int[] extEuclidean(int a, int b)
    {
        // Store the answer here
        int[] answer = new int[3];

        // Remainder initializations
        int r = Integer.MAX_VALUE;
        int rNext;

        // Store iterations in this list
        LinkedList<Integer> l = new LinkedList<>();

        // Euclidean Algorithm, storing information from successive steps appropriately
        while (true)
        {
            // Calculate the quotient (quotient added directly to list for use in bezout section)
            l.add(a / b);
            rNext = a % b;

            // Check if we hit zero to return gcd(a,b)
            if (rNext != 0) {r = rNext;}
            else {answer[0] = r; break;}

            // Shift values for next iteration
            a = b;
            b = r;
        }

        // Backward pass to find Bezout coefficients x and y
        int x = 1;
        int y = 0;

        for (int i = l.size() - 1; i >= 0; i--)
        {
            // Store x
            int tempX = x;

            // Shift x for next iteration (or this is the final x if done iterating)
            x = y;

            // Sub in y with appropriate expression
            y = tempX - l.get(i) * y;
        }

        // Assign x and y results to answer array
        answer[1] = x;
        answer[2] = y;

        return answer;
    }

    public static void main(String[] args)
    {
        int a = 34976, b = 24598;
        int[] result = extEuclidean(a, b);
        System.out.printf("%d = %d(%d) + %d(%d)", result[0], a, result[1], b, result[2]);
    }
}