package javanet;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.jupiter.api.Test;

public class IPDemo {
	@Test
	public void ip() throws UnknownHostException {
	InetAddress ip =InetAddress.getLocalHost();
	
	ip = InetAddress.getByName("www.baidu.com");
	System.out.println(ip);
	ip.getHostAddress();
	
	
	
	}
}
