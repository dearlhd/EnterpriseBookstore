package webService.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

@ApplicationPath("/rest")
public class MyApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(UserUtil.class);
		s.add(JacksonJsonProvider.class);
		return s;
	}
}