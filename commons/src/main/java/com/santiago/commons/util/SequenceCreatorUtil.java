package com.santiago.commons.util;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Iterator;

public final class SequenceCreatorUtil {
    private static SequenceAtomicNumber sequence = new SequenceAtomicNumber(9999);
    private static int ipSuffix;

    public SequenceCreatorUtil() {
    }

    private static String getLoginIp() {
        try {
            Enumeration e = NetworkInterface.getNetworkInterfaces();

            while(e.hasMoreElements()) {
                NetworkInterface item = (NetworkInterface)e.nextElement();
                Iterator i$ = item.getInterfaceAddresses().iterator();

                while(i$.hasNext()) {
                    InterfaceAddress address = (InterfaceAddress)i$.next();
                    if (address.getAddress() instanceof Inet4Address) {
                        Inet4Address inet4Address = (Inet4Address)address.getAddress();
                        if (!inet4Address.isLinkLocalAddress() && !inet4Address.isLoopbackAddress() && !inet4Address.isMCGlobal() && !inet4Address.isMulticastAddress()) {
                            return inet4Address.getHostAddress();
                        }
                    }
                }
            }
        } catch (IOException var5) {
            var5.printStackTrace();
        }

        return "";
    }

    public static int getIpSuffix() {
        return ipSuffix;
    }

    public static final Long getTableId() {
        return Long.valueOf(String.format("%1$013d%2$03d%3$04d", System.currentTimeMillis(), ipSuffix, sequence.incrementAndGet()).substring(1));
    }

    public static final String generateTrxSerialNo() {
        return String.format("T%1$tY%1$tm%1$td%1$tH%1$tM%1$tS%1$tL0%2$03d%3$04d", Calendar.getInstance().getTime(), ipSuffix, sequence.incrementAndGet());
    }

    public static final String generateBillSerialNo() {
        return String.format("B%1$tY%1$tm%1$td%1$tH%1$tM%1$tS%1$tL0%2$03d%3$04d", Calendar.getInstance().getTime(), ipSuffix, sequence.incrementAndGet());
    }

    static {
        String localHost = getLoginIp();
        ipSuffix = Integer.valueOf(localHost.substring(localHost.lastIndexOf(".") + 1));
    }
}