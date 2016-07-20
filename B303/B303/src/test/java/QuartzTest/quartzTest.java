package QuartzTest;

import java.text.ParseException;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/** 
 * 
 * @author zhang
 * @Date  2016年6月20日 下午10:03:59
 * @doing 
 */

public class quartzTest {
	 public static void main(String args[]) throws SchedulerException, ParseException{
		 // 创建一个JobDetail，指明name，groupname，以及具体的Job类名，
		   //该Job负责定义需要执行任务
	        JobDetail jobDetail= JobBuilder.newJob(JobTest.class).withIdentity("testJob_1","group_1").build();
	        // 创建一个触发的Trigger，指明什么时候执行，执行几次
	        Trigger trigger= TriggerBuilder.newTrigger()
	        		.withIdentity("trigger_1","group_1")
	        		.startNow()
	        		.withSchedule(SimpleScheduleBuilder.simpleSchedule()//SimpleScheduleBuilder
	                .withIntervalInSeconds(1).repeatForever() //时间间隔
	               // .withRepeatCount(5)        //重复次数(将执行6次)
	                 ).build();
	        SchedulerFactory sf = new StdSchedulerFactory();
	        // 创建一个Scheduler
	        Scheduler sched = sf.getScheduler();
	        // 用scheduler将JobDetail与Trigger关联在一起，开始调度任务
	        sched.scheduleJob(jobDetail,trigger);
	        sched.start();
	    }
}