public class DecimalToBinary {
    // function to convert decimal number to binary string
    public static String toBinary(int n) {
        if (n == 0) return "0";  // special case

        StringBuilder binary = new StringBuilder();

        while (n > 0) {
            int bit = n % 2;            // remainder = current bit
            binary.append(bit);         // add bit (in reverse order)
            n = n / 2;                  // divide by 2 for next step
        }

        return binary.reverse().toString();  // reverse to get correct order
    }

    public static void main(String[] args) {
        int num = 13;
        System.out.println("Binary of " + num + " = " + toBinary(num));
    }
}

// Complexity

// Time: O(log n)

// Space: O(1) (if we build string directly like above, no extra reversal needed)