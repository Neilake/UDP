package udp;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

//总结：Junit原生不支持多线程
public class ChatRoom {
	
	public static void  main(String[] args) throws SocketException {
		DatagramSocket dssend = new DatagramSocket();
		DatagramSocket dsreceive = new DatagramSocket(10000);
		new Thread(new Send(dssend)).start();
		new Thread(new Receive(dsreceive)).start();
	}
}

class Send implements Runnable{
	private DatagramSocket ds;
	public Send(DatagramSocket ds) {
		this.ds = ds;
	}
	
	public void run() {
			//创建udp服务
			System.out.println("UDP发送端启动");
			BufferedReader str = new BufferedReader(new InputStreamReader(System.in));
				try {
					while(true) {
				//2.明确数据
					byte[] buf = str.readLine().getBytes();
				//3.发送数据，将数据封装到数据包中  .255是广播地址
				DatagramPacket dp = new DatagramPacket(buf, buf.length,
						InetAddress.getByName("192.168.1.50"), 10000);
				//4. 从此套接字发送数据报包。
				ds.send(dp);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} 
				if(!ds.isClosed())
				ds.close();	
			
	}
}

class Receive implements Runnable{
	private DatagramSocket ds;
	public Receive(DatagramSocket ds) {
		this.ds = ds;
	}


	public void run() {
		boolean isContinue = true;
		while(isContinue) {
		System.out.println("UDP接收端启动");
			byte[] buf = new byte[1024];
			//接受消息构造 手册可查
			DatagramPacket dp = new DatagramPacket(buf, buf.length);
			try {
			//3.将收到的数据存储到数据包中 这个是同步阻塞操作
			ds.receive(dp);
			String ip = dp.getAddress().getHostAddress();
			int	   port = dp.getPort();
			byte[] data =dp.getData();
			String str = new String(data,0, dp.getLength());
			System.out.println(ip+":"+port);
			System.out.println(str);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		}
	}
}