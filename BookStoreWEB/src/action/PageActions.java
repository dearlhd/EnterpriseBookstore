package action;

import utils.locale.LocaleUtil;

public class PageActions {
	private String actions;
	private String language;
	private String ja;
	
	public String getActions() {
		return actions;
	}
	public void setActions(String actions) {
		this.actions = actions;
	}
	
	public String getJa() {
		return ja;
	}
	public void setJa(String ja) {
		this.ja = ja;
	}
	
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String execute() throws Exception{
		System.out.println("action "+ actions);
		System.out.println("language "+ language);
		if (actions.equals("login" )){
			if (language != null && !language.equals("")) {
				System.out.println("select language");
				LocaleUtil lu = new LocaleUtil(language);
				ja = lu.getInfos();
			}
		}
		return "success";
	}
}