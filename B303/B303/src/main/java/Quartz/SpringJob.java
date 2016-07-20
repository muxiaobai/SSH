package Quartz;

import java.util.Date;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import Service.UserService;

/** 
 * 
 * @author zhang
 * @Date  2016年6月20日 下午9:45:30
 * @doing 
 */

public class SpringJob extends QuartzJobBean{

		// 判断作业是否执行的旗标
		private boolean isRunning = false;
		// 该作业类所依赖的业务逻辑组件
		private UserService userService;
		public void setUserService(UserService userService) {
			this.userService = userService;
		}
		// 定义任务执行体
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
			if (!isRunning){
				//System.out.println("开始调度自动生成用户列表");
				isRunning = true;
				// 调用业务逻辑方法
				System.out.println("saddddddddd");
				//empMgr.autoPay();
				isRunning = false;
			}
	}
}
