package tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.junit.Test;

public class ServerSockt {
	@Test
	public void serverSocket() throws IOException {
		//1.创建socket服务端服务，服务端为了让客服可以连接上，必须提供端口
		ServerSocket socketServer =  new ServerSocket(10006);
		//2.获取客户端对象
		Socket s = socketServer.accept();
		while(true) {
		//3.从客户端获取socket 输入流
		InputStream in = s.getInputStream();
		
		byte[] buf = new byte[1024];
		int len = in.read(buf);
		String str = new String(buf,0,len);
		System.out.println(str);
		
		OutputStream out = s.getOutputStream();
		BufferedReader str1 = new BufferedReader(new InputStreamReader(System.in));
		out.write(str1.readLine().getBytes());
		}
	}
}
