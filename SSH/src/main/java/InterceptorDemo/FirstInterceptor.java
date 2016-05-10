package InterceptorDemo;

import java.util.Date;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class FirstInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("===================================================================");
		long start=System.currentTimeMillis();
		System.out.println("====进入Interceptor==========:"+new Date()+System.currentTimeMillis());
		String result=invocation.invoke();
		long end =System.currentTimeMillis();
		System.out.println("====执行完Interceptor=========:"+new Date()+System.currentTimeMillis());
		System.out.println("====执行Action时间=============:"+(end-start)+"ms");
		return result;
	}
	
}
