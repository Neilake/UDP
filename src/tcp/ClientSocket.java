package tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;

public class ClientSocket {
	@Test
	public void clientSocket() throws UnknownHostException, IOException {
		System.out.println("客户端启动");
		//1.创建端口，明确地址和端口
		Socket socket = new Socket("192.168.1.50",10006);
		//2.获取socket流中的输出流，将数据发送给服务端
		while(true) {
		OutputStream out = socket.getOutputStream();
		//3.通过 输出流写数据
		BufferedReader str1 = new BufferedReader(new InputStreamReader(System.in));
		out.write(str1.readLine().getBytes());
		//4.关闭资源
		//socket.close();
		
		//获取socket的读取流，读取服务端发回的数据
		InputStream in = socket.getInputStream();
		byte[] buf = new byte[1024];
		int len = in.read(buf);
		String str = new String(buf,0,len);
		System.out.println(str);
		}
	}
}
