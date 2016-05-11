package com.full.oauth;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class OAuthServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.sendRedirect(
				"https://accounts.google.com/o/oauth2/auth?redirect_uri=http://4-dot-automatic-vent-130705.appspot.com/redirect&response_type=code&client_id=129729090266-6j4bgphigv0r3a3dm788lsu9nk9irnv1.apps.googleusercontent.com&approval_prompt=force&scope=email&access_type=online");
	}
}
