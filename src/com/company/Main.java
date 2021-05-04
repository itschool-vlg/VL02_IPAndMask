package com.company;

public class Main {

    public static IP getSubnetAddress(String ipStr, String maskStr) {
        IP ip = new IP(ipStr);
        IP mask = new IP(maskStr);

        ip.applyMask(mask);

        return ip;
    }

    public static int countPossibleIPs(String maskStr) {
        IP mask = new IP(maskStr);
        // subtract
        // broadcast address: 192.168.0.255
        // gateway address: 192.168.0.0
        return (int) Math.pow(2, mask.zeroBitsCount()) - 2;
    }
    
    public static long ipNumber(String ipStr, String maskStr) {
        IP ip = new IP(ipStr);
        IP mask = new IP(maskStr);

        mask.inv();
        ip.applyMask(mask);

        return ip.sum();
    }

    public static void main(String[] args) {
    }
}
