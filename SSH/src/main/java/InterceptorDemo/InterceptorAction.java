package InterceptorDemo;

import com.opensymphony.xwork2.ActionSupport;

public class InterceptorAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public String execute() throws Exception {
		long b=0;
		for (int i = 1; i < 100000000; i++) {
			b=b+i;
		}
		System.out.println("======Action执行=============:"+b);
		// TODO Auto-generated method stub
		return super.execute();
	}
	
}
