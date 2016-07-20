package Quartz;

import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/** 
 * 
 * @author zhang
 * @Date  2016年6月20日 下午9:45:30
 * @doing 
 */

public class JobTest implements Job{
	
	@Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("In SimpleQuartzJob - executing its JOB at " 
                + new Date() + " by " + context.getTrigger()+"\n"+context.getJobDetail());
    }
	
}
