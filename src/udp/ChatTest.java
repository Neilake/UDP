package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import org.junit.Test;

public class ChatTest  {
	@Test
	public void udpSend() throws IOException {
		//创建udp服务
		System.out.println("UDP发送端启动");
		DatagramSocket ds = new DatagramSocket();
		//2.明确数据
		String str = "吃饱了撑的学计算机";
		//3.发送数据，将数据封装到数据包中
		byte[] buf = str.getBytes();
		DatagramPacket dp = new DatagramPacket(buf, buf.length,
				InetAddress.getByName("192.168.1.50"), 10000);
		//4. 从此套接字发送数据报包。
		ds.send(dp);
		ds.close();
	}
	@Test
	public void udpReceive() throws IOException {
		boolean isContinue = true;
		while(isContinue) {
		System.out.println("UDP接收端启动");
		DatagramSocket ds = new DatagramSocket(10000);
		byte[] buf = new byte[1024];
		DatagramPacket dp = new DatagramPacket(buf, buf.length);
		ds.receive(dp);
		String ip = dp.getAddress().getHostAddress();
		int	   port = dp.getPort();
		byte[] data =dp.getData();
		String str = new String(data,0, dp.getLength()) ;
		System.out.println(ip+":"+port);
		System.out.println(str);
		ds.close();}
		
	}
	
}
