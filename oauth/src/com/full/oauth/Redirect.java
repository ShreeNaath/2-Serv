package com.full.oauth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Redirect extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		String outputString = "", line, outputString1 = "";
		String code = req.getParameter("code");
		URL url = new URL("https://www.googleapis.com/oauth2/v1/token?"
				+ "client_id=129729090266-6j4bgphigv0r3a3dm788lsu9nk9irnv1.apps.googleusercontent.com"
				+ "&client_secret=vs5OiWxEVXGKmpLbzJ--kwIj&"
				+ "redirect_uri=http://4-dot-automatic-vent-130705.appspot.com/redirect&"
				+ "grant_type=authorization_code&" + "code=" + code);
		URLConnection urlConn = url.openConnection();
		urlConn.setDoOutput(true);
		BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
		while ((line = reader.readLine()) != null) {
			outputString += line;
		}
		reader.close();
		JSONParser parser = new JSONParser();
		JSONObject json_access_token = null;

		try {
			json_access_token = (JSONObject) parser.parse(outputString);

		} catch (ParseException e) {

		}
		String access_token = (String) json_access_token.get("access_token");
		URL obj1 = new URL("https://www.googleapis.com/oauth2/v1/userinfo?access_token=" + access_token);
		URLConnection urlConn1 = obj1.openConnection();
		BufferedReader reader1 = new BufferedReader(new InputStreamReader(urlConn1.getInputStream()));
		while ((line = reader1.readLine()) != null) {
			outputString1 += line;
		}
		reader1.close();
		JSONObject json_user_details = null;
		try {
			json_user_details = (JSONObject) parser.parse(outputString1);
		} catch (ParseException e) {
		}
		String email = (String) json_user_details.get("email");
		String name=(String) json_user_details.get("name");
		String id=(String) json_user_details.get("id");
		resp.getWriter().write(email+"\n"+name+"\n"+id);
	}
}