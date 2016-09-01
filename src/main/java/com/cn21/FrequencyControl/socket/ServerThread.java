package com.cn21.FrequencyControl.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cn21.FrequencyControl.module.Application;
import com.cn21.FrequencyControl.service.ApplicationService;

/**
 * 
 * @author Administrator
 *
 */
@Component
public class ServerThread extends Thread{
	public static int PORT=8800;
	private Map<String,HandleThread> sockets;
	private ServerSocket serverSocket;
	private boolean running=true;
	@Autowired
	private ApplicationService applicationService;
	
	public ServerThread() throws IOException{
		serverSocket=new ServerSocket(PORT);
		sockets=new HashMap<String,HandleThread>();
	}
	
	public void notifyPullApiLimited(String appKey){
		if(sockets.containsKey(appKey)){
			HandleThread handler=sockets.get(appKey);
			if(handler!=null){
				if(!handler.isClosed())
					handler.setNeedUpdate(true);
				else sockets.remove(appKey);
			}
		}
	}
	
	/**
	 * 判断app是否存在
	 * @param appKey
	 * @return
	 */
	public boolean isValidApp(String appKey,String appSecret){
		if(appKey==null||appSecret==null)  return false;
		Application application = applicationService.getApplicationByAppKey(appKey);
		if(application==null) return false;
		if(!application.getSecret().equals(appSecret)) return false;
		return true;
	}
	
	@Override
	public void run() {
		while(running){
			Socket socket=null;
			try {
				socket = serverSocket.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("accept exception");
				e.printStackTrace();
				break;
			}
			try{
				BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
				//获取appkey
				String appKey=in.readLine();
				String appSecret=in.readLine();
				// appkey  invalid
				if(!isValidApp(appKey,appSecret))  continue;
				//使用handleThread 管理连接
				if(appKey!=null&&appSecret!=null){
					System.out.println("server get appkey "+appKey);
					HandleThread handler=new HandleThread(socket);
					sockets.put(appKey, handler);
					handler.start();
				}else{
					System.out.println("server get appkey null");
					socket.close();
				}
			}catch(IOException e){
				System.out.println("accept exception");
				e.printStackTrace();
				break;
			}
		}
		System.out.println("server run end");
	}
	
	public void stopRunning(){
		running=false;
		this.interrupt();
		for(String key:sockets.keySet()){
			HandleThread item=sockets.get(key);
			item.stopRunning();
		}
		/*try {
			join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	public void close(){
		if(running) stopRunning();
		try {
			serverSocket.close();
			for(String key:sockets.keySet()){
				HandleThread item=sockets.get(key);
				item.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
