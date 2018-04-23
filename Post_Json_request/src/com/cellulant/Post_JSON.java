
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cellulant;

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
			String query_url = "http://197.159.100.249:9001/hub/services/paymentGateway/JSON/";
			
			
			String json = " {\n"
							+ " 	\"countryCode\": \"ZM\",\n"
							+ " 	\"function\": \"BEEP.pushPayments\",\n"
							+ " 	\"payload\": \"<Json Encoded string of the Payload below>\",\n"
							+ " 	\"credentials\": {\n"
							+ " 		\"username\": \"username\",\n"
							+ " 		\"password\": \"password\"\n"
							+ " 	},\n"
							+ " 	\"packet\": [{\n"
							+ " 		\"serviceCode\": \"ZM-MTN-AIRTIME\",\n"
							+ " 		\"MSISDN\": \"260975469186\",\n"
							+ " 		\"invoiceNumber\": \"\",\n"
							+ " 		\"accountNumber\": \"260975469186\",\n"
							+ " 		\"payerTransactionID\": \"CLIENT_UNIQUE_TRANSACTION_544944115593\",\n"
							+ " 		\"amount\": 100,\n"
							+ " 		\"hubID\": \"\",\n"
							+ " 		\"narration\": \"MTN Airtime topup\",\n"
							+ " 		\"datePaymentReceived\": \"2017-11-04 13:27:21\",\n"
							+ " 		\"extraData\": \"\",\n"
							+ " 		\"currencyCode\": \"ZMK\",\n"
							+ " 		\"customerNames\": \"George Kihara\",\n"
							+ " 		\"paymentMode\": \"Online Payment\"\n"
							+ " 	}]\n"
							+ " }";

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
//						JSONObject myResponse = new JSONObject(result);
//						System.out.println("authStatus " + myResponse.getInt("authStatus.authStatusCode"));
//						System.out.println("authStatusDescription " + myResponse.getString("authStatusDescription"));
				 } finally {
						IOUtils.closeQuietly(in);
				 }
				 conn.disconnect();
			} catch (IOException e) {
				 System.out.println(e);
			}
	 }
}
