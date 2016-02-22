package fr.onlineCV.beans;

import java.util.Objects;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class SessionBean {

	/**
	 * Get session of the current visitor.
	 * 
	 * @return Session of the visitor.
	 * @throws NullPointerException
	 *             if visitor is not connected.
	 */
	public static HttpSession getSession() throws NullPointerException {
		return (HttpSession) Objects
				.requireNonNull(FacesContext.getCurrentInstance().getExternalContext().getSession(false));
	}

	/**
	 * Create and get a session for the current visitor. If you only wants to
	 * get the current session, use SessionBean.getSession().
	 * 
	 * @see SessionBean.getSession
	 * @return new session for the visitor.
	 */
	public static HttpSession createSession() {
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	}

	/**
	 * Check if the current visitor is connected.
	 * 
	 * @return True if the visitor is connected.
	 */
	public static boolean isConnected() {
		try {
			
			Objects.requireNonNull(getSession().getAttribute(LoginBean.USER));
			return true;
		} catch (NullPointerException e) {
			return false;
		}
	}
}
