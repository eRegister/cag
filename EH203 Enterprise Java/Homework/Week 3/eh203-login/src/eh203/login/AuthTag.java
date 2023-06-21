package eh203.login;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class AuthTag extends SimpleTagSupport {
	protected String redirectURL;
	protected String loginURL;
	
	@Override
	public void doTag() throws JspException, IOException {
		User authUser = (User)getJspContext().findAttribute("authenticatedUser");
		
		if (authUser == null) {
			PageContext ctx = (PageContext)getJspContext();
			HttpServletResponse response = (HttpServletResponse)ctx.getResponse();
			response.sendRedirect(loginURL + "?redirect=" + redirectURL);
		}
	}

	/**
	 * @return the redirect
	 */
	public String getRedirectURL() {
		return redirectURL;
	}

	/**
	 * @param redirect the redirect to set
	 */
	public void setRedirectURL(String redirectURL) {
		this.redirectURL = redirectURL;
	}

	/**
	 * @return the loginURL
	 */
	public String getLoginURL() {
		return loginURL;
	}

	/**
	 * @param loginURL the loginURL to set
	 */
	public void setLoginURL(String loginURL) {
		this.loginURL = loginURL;
	}
}
