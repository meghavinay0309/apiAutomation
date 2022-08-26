package javaSubfolder;

public class booksPOJO {
	
	private String userID;
	private String it;
	private String title;
	private String body;
	/**
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}
	/**
	 * @param object the userID to set
	 */
	public void setUserID(Object object) {
		this.userID = (String) object;
	}
	/**
	 * @return the it
	 */
	public String getIt() {
		return it;
	}
	/**
	 * @param Integers the it to set
	 */
	public void setIt(Object object) {
		
		this.it = (String) object;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}
	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}
	

}
