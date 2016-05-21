package utils.aop;

import org.jboss.logging.Logger;
import org.jboss.logmanager.Level;

import java.util.Date;
import java.text.SimpleDateFormat;

public aspect LoginAOP {
	pointcut login () :
		execution (* action.UserActions.login(..));
	
	before() : login () {
		Logger log = Logger.getLogger("LoginAOP");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String tt = df.format(new Date());
		log.info("AOP: Before login\t"+tt);
	}
	
	after() returning : login() {
		Logger log = Logger.getLogger("LoginAOP");
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String tt = df.format(new Date());
		log.info("AOP: after login\t" + tt);
	}
}
