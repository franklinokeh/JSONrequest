
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.System.in;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.io.IOUtils;


import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author user
 */
public class Post_JSON {

	 public static void main(String[] args) throws JSONException {
			Post_JSON.Post_JSON();
	 }

	 public static void Post_JSON() throws JSONException {
			String query_url = " // URL HERE ";
			
			
			String json = "JSON to be Posted";

			try {
				 URL url = new URL(query_url);

				 HttpURLConnection conn = (HttpURLConnection) url.openConnection();

				 conn.setConnectTimeout(5000);
				 conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
				 conn.setDoOutput(true);
				 conn.setDoInput(true);
				 conn.setRequestMethod("POST");
				                                  
				 try (OutputStream os = conn.getOutputStream()) {
						os.write(json.getBytes("UTF-8"));
				 }

				 try (InputStream in = new BufferedInputStream(conn.getInputStream())) {
						
						String result = IOUtils.toString(in, "UTF-8");
						System.out.println("JSON Response = " + result);						
				 } finally {
						IOUtils.closeQuietly(in);
				 }
				 conn.disconnect();
			} catch (IOException e) {
				 System.out.println(e);
			}
	 }
}
