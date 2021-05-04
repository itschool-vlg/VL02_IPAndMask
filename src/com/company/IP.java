package com.company;

import java.util.Objects;

public class IP {
    int a, b, c, d;

    // pass ip in format '192.168.0.1'
    public IP(String ipStr) {
        String[] parts = ipStr.split("\\.");
        if(parts.length != 4)
            throw new RuntimeException("wrong ip scheme: '" + ipStr + "'");

        a = Integer.parseInt(parts[0]);
        b = Integer.parseInt(parts[1]);
        c = Integer.parseInt(parts[2]);
        d = Integer.parseInt(parts[3]);
    }

    // count - one bits count, e.g. 24 bits equals to
    // 11111111.11111111.11111111.00000000
    public IP(int count) {
        if(count > 32 || count < 0) {
            throw new RuntimeException("incorrect bit count for ip: " + count);
        }

        int[] v = new int[4];

        for (int i = 0; i < 4; i++) {
            if(count >= 8) {
                v[i] = 255;
                count -= 8;
            } else if(count > 0) {
                for (int j = 7; j > 7 - count; j--) {
                    v[i] += (int)(Math.pow(2, j));
                }
                break;
            }
        }

        a = v[0];
        b = v[1];
        c = v[2];
        d = v[3];
    }

    public void applyMask(IP mask) {
        a &= mask.a;
        b &= mask.b;
        c &= mask.c;
        d &= mask.d;
    }

    public int oneBitsCount() {
        return Integer.bitCount(a) +
                Integer.bitCount(b) +
                Integer.bitCount(c) +
                Integer.bitCount(d);
    }

    public int zeroBitsCount() {
        return 8*4 - oneBitsCount();
    }

    public String bitString() {
        return String.format("%s.%s.%s.%s",
                binaryStringLeftPad(a),
                binaryStringLeftPad(b),
                binaryStringLeftPad(c),
                binaryStringLeftPad(d));
    }

    public String binaryStringLeftPad(int v) {
        String b = Integer.toBinaryString(v);
        return ("00000000" + b).substring(b.length());
    }

    public void inv() {
        a = 255 - a;
        b = 255 - b;
        c = 255 - c;
        d = 255 - d;
    }

    public long sum() {
        return (long)16777216 * a + (long)65536 * b + (long)256 * c + d;
    }

    @Override
    public String toString() { // d - decimal integer
        return String.format("%d.%d.%d.%d", a, b, c, d);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IP ip = (IP) o;
        return a == ip.a && b == ip.b && c == ip.c && d == ip.d;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c, d);
    }
}
