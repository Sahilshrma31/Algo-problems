public class BinaryToDecimalOptimized {
    public static int binaryToDecimal(String binary) {
        int n = binary.length();
        int ans = 0;
        int power = 1; // 2^0 initially

        for (int i = n - 1; i >= 0; i--) {
            if (binary.charAt(i) == '1') {
                ans += power;  // only add for 1
            }
            power *= 2; // move to next power
        }

        return ans;
    }

    public static void main(String[] args) {
        String bin = "1101"; // 13
        System.out.println("Decimal of " + bin + " = " + binaryToDecimal(bin));
    }
}
