package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

import org.junit.Test;

public class ChatRoom {
	@Test
	public void  chatRoomTest() {
		Thread send = new Thread(new Send());
		Thread receive = new Thread(new Receive());
		send.start();
		receive.start();
	}
}

class Send implements Runnable{
	DatagramSocket ds;
	Scanner sc = new Scanner(System.in);
	public void run() {
			//创建udp服务
			System.out.println("UDP发送端启动");
			try {
				while(true) {
				ds = new DatagramSocket();
				//2.明确数据
				String str = sc.next();
				//3.发送数据，将数据封装到数据包中
				byte[] buf = str.getBytes();
				DatagramPacket dp = new DatagramPacket(buf, buf.length,
						InetAddress.getByName("192.168.1.50"), 10000);
				//4. 从此套接字发送数据报包。
				ds.send(dp);}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if(!ds.isClosed())
				ds.close();	
			}
	}
}

class Receive implements Runnable{
	DatagramSocket ds;
	public void run() {
		boolean isContinue = true;
		while(isContinue) {
		System.out.println("UDP接收端启动");
		try {
			byte[] buf = new byte[1024];
			DatagramPacket dp = new DatagramPacket(buf, buf.length);
			ds.receive(dp);
			String ip = dp.getAddress().getHostAddress();
			int	   port = dp.getPort();
			byte[] data =dp.getData();
			String str = new String(data,0, dp.getLength()) ;
			System.out.println(ip+":"+port);
			System.out.println(str);
			ds = new DatagramSocket(10000);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
		}
	}
}