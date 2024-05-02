//public class RSA {
//}
import java.math.*;
import java.util.*;
class RSA {
    public static void main(String args[])
    {int p, q, n, m, d = 0, e, i;
        int msg = 4;
        double c;
        BigInteger msgback;
        p = 9;
        q = 11;
        n = p * q;
        m = (p - 1) * (q - 1);
        System.out.println("the value of m = " + m);
        for (e = 2; e < m; e++) {
            if (gcd(e, m) == 1) {
                break;
            }}
        System.out.println("the value of e = " + e);
        for (i = 0; i <= 9; i++) {
            int x = 1 + (i * m);
            if (x % e == 0) {
                d = x / e;
                break;
            }}
        System.out.println("the value of d = " + d);
        c = (Math.pow(msg, e)) % n;
        System.out.println("Encrypted message is : " + c);
        BigInteger N = BigInteger.valueOf(n);
        BigInteger C = BigDecimal.valueOf(c).toBigInteger();
        msgback = (C.pow(d)).mod(N);
        System.out.println("Decrypted message is : " + msgback);
    }
    static int gcd(int e, int z)
    {
        if (e == 0)
            return z;
        else
            return gcd(z % e, e);
    }
}