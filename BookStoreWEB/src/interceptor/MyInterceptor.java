package interceptor;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import entityBean.User;

public class MyInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// ȡ��������ص�ActionContextʵ��  
        ActionContext ctx = invocation.getInvocationContext();  
        Map session = ctx.getSession();  
        User user = (User) session.get("username");  
  
        // ���û�е�½�����ߵ�½���е��û�������yuewei�����������µ�½  
  
        if (user != null) {  
            System.out.println("test");  
            return invocation.invoke();  
        }  
  
        ctx.put("tip", "�㻹û�е�¼");  
        return Action.LOGIN;  
	}

}
