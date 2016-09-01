package com.cn21.FrequencyControl.socket;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 
 * desc:系统启动后 启动socket线程
 * 
 * @author chenxiaofeng(2016年9月1日)
 */
@Component
public class SpringPostInitiationProcessor implements  ApplicationListener<ContextRefreshedEvent>
{
	@Autowired
	private ServerThread serverThread; 
	private static final Logger logger=Logger.getLogger(SpringPostInitiationProcessor.class);

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		 if (event.getApplicationContext().getParent() == null) {
			 logger.info("启动socket线程!");
			 serverThread.start();
	        }
		
	}

}
