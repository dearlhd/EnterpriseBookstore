package utils.locale;

import java.util.Locale;
import java.util.ResourceBundle;

import net.sf.json.JSONObject;

public class LocaleUtil {
	ResourceBundle rb;
	
	public LocaleUtil(String ss) {
		String basename = "utils.locale.resource";
		
		if (ss.equals("zh")) {
			rb = ResourceBundle.getBundle( basename, Locale.CHINA);
		}
		else if (ss.equals("en")) {
			rb = ResourceBundle.getBundle( basename, Locale.US);
		}
	}
	
	public String getInfos () {
		JSONObject jo = new JSONObject();
		
		jo.put("title", rb.getString("login.title"));
		jo.put("username", rb.getString("login.username"));
		jo.put("password", rb.getString("login.password"));
		jo.put("submit", rb.getString("login.submit"));
		jo.put("tip1", rb.getString("login.tip1"));
		jo.put("tip2", rb.getString("login.tip2"));
		jo.put("tipLink1", rb.getString("login.tipLink1"));
		jo.put("tipLink2", rb.getString("login.tipLink2"));

		return jo.toString();
	}
}
