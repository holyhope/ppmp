package fr.onlineCV.beans;

import java.util.Objects;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class SessionBean {
	public static HttpSession getSession() {
		return (HttpSession) Objects
				.requireNonNull(FacesContext.getCurrentInstance().getExternalContext().getSession(false));
	}

	public static HttpSession createSession() {
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	}
}
