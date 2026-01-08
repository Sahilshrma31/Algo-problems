import java.io.*;
import java.util.*;

public class Main implements Runnable {

    static FastInput in = new FastInput(System.in);
    static FastOutput out = new FastOutput(System.out);

    public static void main(String[] args) {
        // Large stack for recursion-heavy problems
        new Thread(null, new Main(), "Main", 1 << 29).start();
    }

    @Override
    public void run() {
        int t = 1;
        // t = in.nextInt(); // uncomment if multiple test cases

        while (t-- > 0) {
            solve();
        }
        out.flush();
    }

    /* ================= SOLVE ================= */

    static void solve() {
        // Write problem logic here
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);

        long sum = 0;
        for (int x : a) sum += x;

        out.println(sum);
    }

    /* ================= UTILITIES ================= */

    static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    static long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }

    static long modPow(long a, long b, long mod) {
        long res = 1;
        a %= mod;
        while (b > 0) {
            if ((b & 1) == 1) res = (res * a) % mod;
            a = (a * a) % mod;
            b >>= 1;
        }
        return res;
    }

    static class Pair<F, S> {
        F first;
        S second;

        Pair(F f, S s) {
            first = f;
            second = s;
        }
    }

    /* ================= FAST OUTPUT ================= */

    static class FastOutput {
        private final PrintWriter writer;

        FastOutput(OutputStream os) {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(os)));
        }

        void print(Object o) {
            writer.print(o);
        }

        void println(Object o) {
            writer.println(o);
        }

        void println() {
            writer.println();
        }

        void flush() {
            writer.flush();
        }
    }

    /* ================= FAST INPUT ================= */

    static class FastInput {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastInput(InputStream in) {
            this.in = in;
        }

        private int read() {
            if (ptr >= len) {
                try {
                    len = in.read(buffer);
                    ptr = 0;
                } catch (IOException e) {
                    return -1;
                }
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() {
            int c, sign = 1, val = 0;
            do {
                c = read();
            } while (c <= ' ');

            if (c == '-') {
                sign = -1;
                c = read();
            }

            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }

        long nextLong() {
            int c, sign = 1;
            long val = 0;
            do {
                c = read();
            } while (c <= ' ');

            if (c == '-') {
                sign = -1;
                c = read();
            }

            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }

        int[] nextIntArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = nextInt();
            return a;
        }

        long[] nextLongArray(int n) {
            long[] a = new long[n];
            for (int i = 0; i < n; i++) a[i] = nextLong();
            return a;
        }

        String next() {
            int c;
            StringBuilder sb = new StringBuilder();
            do {
                c = read();
            } while (c <= ' ');

            while (c > ' ') {
                sb.append((char) c);
                c = read();
            }
            return sb.toString();
        }
    }
}
