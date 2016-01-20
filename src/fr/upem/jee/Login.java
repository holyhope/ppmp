package fr.upem.jee;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	protected static final int BAD_USER = 0;
	protected static final int BAD_PASSWORD = 1;
	protected static final int ALREADY_CONNECTED = 2;
	protected static final int CONNECTED = 3;
	
	private static final String URL_BDD = "jdbc:mysql://localhost:8889/online_cv";
	private static final String USER_BDD = "root";
	private static final String PASSWORD_BDD = "root";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		File pagePath = new File(this.getServletContext().getRealPath("/Profile.jsp"));
		 if ( pagePath.exists() ) {
		        request.getRequestDispatcher("/Profile.jsp").forward(request, response);
		    } else {
		        throw new IllegalArgumentException(String.format( "The page %s does not exist", "/Profile.jsp"));
		    }
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);

		String email = request.getParameter("email");

		/*if (Boolean.TRUE.equals(session.getAttribute("connected"))) {
			getAlreadyConnected(response);
			return;
		}*/

		if (!userEmailExists(email)) {
			getBadUser(response);
			return;
		}

		String password = request.getParameter("password");
		String hash;
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
		//getConnected(response);
		
		doGet(request, response);
		return;
	}

	private void getConnected(HttpServletResponse response) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		PrintWriter writerPage = response.getWriter();
		objectMapper.writer().writeValue(writerPage, new Object() {
			public final int code = CONNECTED;
			public final String message = "Connected";
		});
		System.out.println("connected");
	}

	private void getAlreadyConnected(HttpServletResponse response) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		PrintWriter writerPage = response.getWriter();
		objectMapper.writer().writeValue(writerPage, new Object() {
			public final int code = ALREADY_CONNECTED;
			public final String message = "Already connected";
		});
		System.out.println("already authenticate");
	}

	private void getBadUser(HttpServletResponse response) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		PrintWriter writerPage = response.getWriter();
		objectMapper.writer().writeValue(writerPage, new Object() {
			public final int code = BAD_USER;
			public final String message = "Unknown user";
		});
		System.out.println("bad user");
	}

	private void getBadPassword(HttpServletResponse response) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		PrintWriter writerPage = response.getWriter();
		objectMapper.writer().writeValue(writerPage, new Object() {
			public final int code = BAD_PASSWORD;
			public final String message = "Bad password";
		});
		System.out.println("bad password");
	}

	private boolean authenticate(String email, String hash) {
		boolean isAuthenticate = false;
		
		try {
			Driver dm = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
			DriverManager.registerDriver(dm);
			Connection myConn = DriverManager.getConnection(URL_BDD, USER_BDD,
					PASSWORD_BDD);
			
			Statement st = myConn.createStatement();
			
			PreparedStatement authenticateQuery = (PreparedStatement) myConn
					.prepareStatement("SELECT * FROM users WHERE email = (?) AND hash_password = (?)");
			authenticateQuery.setString(1, email);
			authenticateQuery.setString(2, hash);
			
			ResultSet set = authenticateQuery.executeQuery();
			
			if(set.next() && set.getString("email").equals(email) && set.getString("hash_password").equals(hash)){
				isAuthenticate = true;
			}
			
			set.close();
			st.close();
			myConn.close();


		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isAuthenticate;
	}

	private boolean userEmailExists(String email) {
		boolean isMailExists = false;
		try {
			Driver dm = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
			DriverManager.registerDriver(dm);
			Connection myConn = DriverManager.getConnection(URL_BDD, USER_BDD,
					PASSWORD_BDD);
			
			Statement st = myConn.createStatement();
			
			PreparedStatement checkMailQuery = (PreparedStatement) myConn
					.prepareStatement("SELECT email FROM users WHERE email = (?)");
			checkMailQuery.setString(1, email);
			
			ResultSet set = checkMailQuery.executeQuery();
			if(set.next() && set.getString("email").equals(email)){
				isMailExists = true;
			}
			set.close();
			st.close();
			myConn.close();


		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isMailExists;
	}

	private String hashPassword(String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes(), 0, password.length());
		return new BigInteger(1, md.digest()).toString(16);
	}

}
