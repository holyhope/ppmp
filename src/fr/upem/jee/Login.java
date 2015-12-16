package fr.upem.jee;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);

		String email = request.getParameter("email");

		if (Boolean.TRUE.equals(session.getAttribute("connected"))) {
			getAlreadyConnected(response);
			return;
		}

		if (!userEmailExists(email)) {
			getBadUser(response);
			return;
		}

		String password = request.getParameter("password");
		byte[] hash;
		try {
			hash = hashPassword(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace(System.err);
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}

		if (!authenticate(email, hash)) {
			getBadPassword(response);
			return;
		}

		session.setAttribute("connected", true);
		getConnected(response);

		doGet(request, response);
	}

	private void getConnected(HttpServletResponse response) throws IOException {
		// TODO
	}

	private void getAlreadyConnected(HttpServletResponse response) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		PrintWriter writerPage = response.getWriter();
		objectMapper.writer().writeValue(writerPage, new Object() {
			public final int	code	= 1;
			public final String	message	= "Already connected";
		});
		System.out.println("already authenticate");
	}

	private void getBadUser(HttpServletResponse response) throws IOException {
		// TODO
	}

	private void getBadPassword(HttpServletResponse response) throws IOException {
		// TODO
	}

	private boolean authenticate(String email, byte[] hash) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean userEmailExists(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	private byte[] hashPassword(String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		byte[] bytesOfMessage = password.getBytes("UTF-8");
		MessageDigest md = MessageDigest.getInstance("MD5");
		return md.digest(bytesOfMessage);
	}

}
