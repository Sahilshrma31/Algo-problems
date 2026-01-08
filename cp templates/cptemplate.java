import java.io.*;
import java.util.*;

public class Main {

    /* ================= FAST IO ================= */

    static class FastScanner {
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        private final InputStream in = System.in;

        private int readByte() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c, sign = 1, val = 0;
            do {
                c = readByte();
            } while (c <= ' ');

            if (c == '-') {
                sign = -1;
                c = readByte();
            }

            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return val * sign;
        }

        long nextLong() throws IOException {
            int c, sign = 1;
            long val = 0;
            do {
                c = readByte();
            } while (c <= ' ');

            if (c == '-') {
                sign = -1;
                c = readByte();
            }

            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return val * sign;
        }

        String next() throws IOException {
            int c;
            StringBuilder sb = new StringBuilder();
            do {
                c = readByte();
            } while (c <= ' ');

            while (c > ' ') {
                sb.append((char) c);
                c = readByte();
            }
            return sb.toString();
        }
    }

    static class FastWriter {
        private final StringBuilder sb = new StringBuilder();
        void print(Object o) { sb.append(o); }
        void println(Object o) { sb.append(o).append('\n'); }
        void flush() { System.out.print(sb.toString()); }
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

    static void sort(int[] a) {
        Arrays.sort(a);
    }

    static void reverse(int[] a) {
        for (int l = 0, r = a.length - 1; l < r; l++, r--) {
            int t = a[l];
            a[l] = a[r];
            a[r] = t;
        }
    }

    /* ================= SOLVE ================= */

    static final long MOD = 1_000_000_007;

    static void solve(FastScanner fs, FastWriter out) throws Exception {
        int n = fs.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = fs.nextInt();

        sort(a);

        long sum = 0;
        for (int x : a) sum = (sum + x) % MOD;

        out.println(sum);
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        FastWriter out = new FastWriter();

        int t = 1;
        // t = fs.nextInt(); // uncomment if multiple testcases

        while (t-- > 0) {
            solve(fs, out);
        }

        out.flush();
    }
}

