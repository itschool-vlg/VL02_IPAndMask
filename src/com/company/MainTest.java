package com.company;

import org.junit.jupiter.api.Test;

import static com.company.Main.*;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testGetSubnetAddress() {
        Object[][] cases = new Object[][]{
                new Object[]{"217.170.113.105", "255.255.224.0", "217.170.96.0"},
                new Object[]{"192.168.0.1", "255.255.255.0", "192.168.0.0"}
        };

        for(Object[] c : cases) {
            String ipStr = (String) c[0];
            String maskStr = (String) c[1];
            String wantStr = (String) c[2];

            assertEquals(new IP(wantStr), getSubnetAddress(ipStr, maskStr));
        }
    }

    @Test
    void testGetSubnetAddressCountMask() {
        Object[][] cases = new Object[][]{
                new Object[]{"192.168.0.1", "24", "192.168.0.0"},
        };

        for(Object[] c : cases) {
            String ipStr = (String) c[0];
            String maskStr = (String) c[1];
            String wantStr = (String) c[2];

            assertEquals(new IP(wantStr), getSubnetAddress(ipStr, maskStr));
        }
    }

    @Test
    void testMaskFromBitsCount() {
        Object[][] cases = new Object[][]{
                new Object[]{32, "255.255.255.255"},
                new Object[]{31, "255.255.255.254"},
                new Object[]{30, "255.255.255.252"},
                new Object[]{29, "255.255.255.248"},
                new Object[]{28, "255.255.255.240"},
                new Object[]{27, "255.255.255.224"},
                new Object[]{26, "255.255.255.192"},
                new Object[]{25, "255.255.255.128"},
                new Object[]{24, "255.255.255.0"},
                new Object[]{23, "255.255.254.0"},
                new Object[]{22, "255.255.252.0"},
                new Object[]{21, "255.255.248.0"},
                new Object[]{20, "255.255.240.0"},
                new Object[]{19, "255.255.224.0"},
                new Object[]{18, "255.255.192.0"},
                new Object[]{17, "255.255.128.0"},
                new Object[]{16, "255.255.0.0"},
                new Object[]{15, "255.254.0.0"},
                new Object[]{14, "255.252.0.0"},
                new Object[]{13, "255.248.0.0"},
                new Object[]{12, "255.240.0.0"},
                new Object[]{11, "255.224.0.0"},
                new Object[]{10, "255.192.0.0"},
                new Object[]{9, "255.128.0.0"},
                new Object[]{8, "255.0.0.0"},
                new Object[]{7, "254.0.0.0"},
                new Object[]{6, "252.0.0.0"},
                new Object[]{5, "248.0.0.0"},
                new Object[]{4, "240.0.0.0"},
                new Object[]{3, "224.0.0.0"},
                new Object[]{2, "192.0.0.0"},
                new Object[]{1, "128.0.0.0"},
                new Object[]{0, "0.0.0.0"},
        };

        for(Object[] c : cases) {
            int bits = (Integer) c[0];
            String wantStr = (String) c[1];

            assertEquals(new IP(wantStr), new IP(bits));
            System.out.println("passed " + bits + " = " + wantStr);
        }
    }

    @Test
    void testCountPossibleIPs() {
        Object[][] cases = new Object[][]{
                new Object[]{"255.255.254.0", 510},
                new Object[]{"255.255.255.0", 254},
                new Object[]{"255.255.168.0", 8190},
        };

        for(Object[] c : cases) {
            String mask = (String) c[0];
            int want = (Integer) c[1];

            assertEquals(want, countPossibleIPs(mask));
        }
    }

    @Test
    void testIpNumber() {
        Object[][] cases = new Object[][]{
                new Object[]{"192.168.105.123", "255.255.254.0", 379},
                new Object[]{"192.168.105.123", "255.255.255.0", 123},
        };

        for(Object[] c : cases) {
            String ip = (String) c[0];
            String mask = (String) c[1];
            int want = (Integer) c[2];

            assertEquals(want, ipNumber(ip, mask));
        }
    }

}