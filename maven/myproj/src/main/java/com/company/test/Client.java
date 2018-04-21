import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.google.protobuf.CodedInputStream;

public class Client {
	public static final String IP_ADDR = "127.0.0.1";    //server ip 
	public static final int PORT = 8000;    //port  

	public static void main(String[] args) throws InterruptedException {  
		System.out.println("client starting...");  

		while (true) {  
			Thread.sleep(1000);
			Socket socket = null;
			try {
				socket = new Socket(IP_ADDR, PORT);  

				InputStream input = socket.getInputStream();
				try{           
					// 反序列化
					Infor.my_message mm = Infor.my_message.parseDelimitedFrom(input);

					System.out.println(); 
					System.out.println(); 
					System.out.println(); 
					System.out.println("服务器端返回过来的是: ");  
					System.out.println("startedTime:" + mm.getStartedTime());
					System.out.println("version:" + mm.getVersion());
					System.out.println("configuredCapacity:" + mm.getConfiguredCapacity());
					System.out.println("dfsUsed:" + mm.getDfsUsed());
					System.out.println("fileNum:" + mm.getFileNum());
					System.out.println("replicatedFilesNum:" + mm.getReplicatedFilesNum());
					System.out.println("blockNum:" + mm.getBlockNum());
					System.out.println("livedNodeNum:" + mm.getLivedNodeNum());
					System.out.println("decommissioningNodeNum:" + mm.getDecommissioningNodeNum());
				} catch(Exception e)
				{
					System.out.println(e.toString());
					System.out.println("catch e");
					break;
				}
				input.close();
			} catch (Exception e) {
				System.out.println("客户端异常:" + e.getMessage()); 
			} finally {
				if (socket != null) {
					try {
						socket.close();
					} catch (IOException e) {
						socket = null; 
						System.out.println("客户端 finally 异常:" + e.getMessage()); 
					}
				}
			}
		}  
	}  
}  
