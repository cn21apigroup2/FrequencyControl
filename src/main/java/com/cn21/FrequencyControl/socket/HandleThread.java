package com.cn21.FrequencyControl.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.Semaphore;

/**
 * 
 * @author Administrator
 *
 */
public class HandleThread extends Thread{
	private boolean needUpdate;
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private boolean running=true;
	private int message;
	private HeartThread heartThread;
	private UpdateThread updateThread;
	//private Object object=new Object();
	
	public HandleThread(Socket socket) throws IOException{
		this.socket=socket;
		in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out=new PrintWriter(socket.getOutputStream());
		heartThread=new HeartThread();
	}
	
	@Override
	public void run(){
		heartThread.start();
		updateThread.start();
		while(running){
			try {
				synchronized(this){
					wait();
					out.println(message);
					out.flush();
				}				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
			
		}
		System.out.println("server handle run end");
	}
	
	public void notifySendMessage(int message){
		synchronized(this){
			this.message=message;
			notify();
		}		
	}
	
	/**
	 * @return the needUpdate
	 */
	public synchronized boolean isNeedUpdate() {
		return needUpdate;
	}

	/**
	 * @param needUpdate the needUpdate to set
	 */
	public synchronized void setNeedUpdate(boolean needUpdate) {
		this.needUpdate = needUpdate;
	}

	/**
	 * 处理客户端发来的信息
	 * @param message
	 */
	protected void handleMessage(int message){
		
	}
	
	public void stopRunning(){
		running=false;
		this.interrupt();
		heartThread.interrupt();
		heartThread.checkTimeThread.interrupt();
		updateThread.interrupt();
		try {
			join();
			heartThread.checkTimeThread.join();
			updateThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close(){
		if(running) stopRunning();
		try {		
			in.close();
			out.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isClosed(){
		return !running;
	}
	
	private class HeartThread extends Thread{
		private boolean isTimeout=true;
		public long timeout;
		
		private Thread checkTimeThread=new Thread(){
			@Override
			public void run() {
				while(running){
					synchronized(this){
						try {
							wait(timeout);
							if(isTimeout){
								alarmTimeout();
								break;
							}
							isTimeout=true;
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							break;
						}
					}
				}
				
			}
		};
		
		public HeartThread(){
			this(3600000);   //1 hour
			//this(10000);  //test 10s
		}

		public HeartThread(long timeout){
			if(timeout<=0) throw new IllegalArgumentException("timeout cannot be -");
			this.timeout=timeout;
			checkTimeThread.start();
		}

		public void run() {
			while(running){
				try {
					String line;
					line=in.readLine();		
					int code=Integer.valueOf(line);
					if(code==MessageRule.HEART){
						System.out.println("server:recv heart");
						synchronized(checkTimeThread){
							isTimeout=false;
							checkTimeThread.notify();
						}						
						HandleThread.this.notifySendMessage(MessageRule.HEARTACK);
					}else
						handleMessage(code);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					break;
				}catch(NumberFormatException e){
					continue;
				}
				//out.println(MessageRule.HEART);
				//out.flush();
				//System.out.println("heart"+MessageRule.HEART);
			}
		}
		
		protected void alarmTimeout() {
			System.out.println("client timeout , socket that with the client close");
			close();
		}
		
	}
	private class UpdateThread extends Thread{
		private int interval=600000;
		
		public void run() {
			while(running){
				try {
					sleep(interval);
					synchronized (HandleThread.this) {
						if(needUpdate){
							notifySendMessage(MessageRule.APILIMITED_UPDATE);
							setNeedUpdate(false);
						}
					}
				}
				//out.println(MessageRule.HEART);
				//out.flush();
				//System.out.println("heart"+MessageRule.HEART);
					catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					break;
				}
			}
		}
		
	}
}
