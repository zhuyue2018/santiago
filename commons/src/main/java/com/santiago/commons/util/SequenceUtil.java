package com.santiago.commons.util;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Calendar;
import java.util.Enumeration;

/**
 * 
    * @ClassName: SequenceUtil  
    * @Description: 序列工具类  
    * @author jjiang  
    * @date 2018年7月26日  
    *
 */
public final class SequenceUtil
{
    private static AtomicIntegerBound sequence = new AtomicIntegerBound(0,9999);
    private static int ipSuffix;

    static {
	String localHost = getLoginIp();
	ipSuffix = Integer.valueOf(localHost.substring(localHost.lastIndexOf(".") + 1));
    }

    private static String getLoginIp()
    {
	try {
	    for (Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces(); e.hasMoreElements();) {
		NetworkInterface item = e.nextElement();

		for (InterfaceAddress address : item.getInterfaceAddresses()) {
		    if (address.getAddress() instanceof Inet4Address) {
			Inet4Address inet4Address = (Inet4Address) address.getAddress();
			if (!inet4Address.isLinkLocalAddress() && !inet4Address.isLoopbackAddress() && !inet4Address.isMCGlobal() && !inet4Address.isMulticastAddress()) {
			    return inet4Address.getHostAddress();
			}
		    }
		}
	    }

	} catch ( IOException ex ) {
	    ex.printStackTrace();
	}
	return "";
    }

    public static int getIpSuffix()
    {
	return ipSuffix;
    }
    
    public final static Long getTableId()
    {
	return Long.valueOf(String.format("%1$013d%2$03d%3$04d", System.currentTimeMillis(), ipSuffix, sequence.incrementAndGetWithBound()).substring(1));
    }

    public final static String generateTrxSerialNo(String envFlag,int sequence)
    {
	return String.format("%1$tY%1$tm%1$td%2$016d%3$1s%4$6s",Calendar.getInstance().getTime(), sequence,envFlag,"000000" );
    }
    public static void main(String[] args) {
    	/*for(int i =0;i<100;i++){
        	Long tableId = getTableId();
        	System.out.println(tableId);
    	}*/
    	for(int i =0;i<100;i++){
        	String generateTrxSerialNo = generateTrxSerialNo("1",1);
        	System.out.println(generateTrxSerialNo);
    	}
	}
    
}