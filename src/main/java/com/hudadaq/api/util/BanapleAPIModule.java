package com.hudadaq.api.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class BanapleAPIModule {

	private static final String apiURL = "http://api.logisoft.co.kr/api/getQuickData";

	public static String getData(String postParams, Map<String, Object> map) {
		String builder = new String();
		try {
			// String postParams =
			// "method=findDongId&admCd=1165010200&rnMgtSn=116504163088&buldMnnm=9&buldSlno=0";
			
			
			System.out.println("postParams => " + postParams);
			System.out.println("mapParams => " + map.get("content-type").toString() + "; charset=UTF-8");
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			if(map.containsKey("content-type")) {
				//con.setRequestProperty("Content-Type", map.get("content-type").toString() + "; charset=utf-8");
			}
			
			if(map.containsKey("x-banaple-client-id")) {
				System.out.println("x-banaple-client-id" + map.get("x-banaple-client-id").toString());
				con.setRequestProperty("X-Banaple-Client-Id", map.get("x-banaple-client-id").toString());
			}else {
				con.setRequestProperty("X-Banaple-Client-Id", "yoqNjRPTkc9e5QDDtCQW7ouyZL6MggwXdvyRhaKq");
			}
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(wr, "UTF-8"));
			writer.write(postParams);
			writer.close();
			wr.close();
			int responseCode = con.getResponseCode();
			InputStreamReader isr = new InputStreamReader(con.getInputStream(), "UTF-8");
			BufferedReader br;
			if (responseCode == 200) {
				br = new BufferedReader(isr);
			} else {
				br = new BufferedReader(isr);
			}
			String inputLine;
			while ((inputLine = br.readLine()) != null) {
				builder += inputLine;
				System.out.println("while : " + inputLine);
			}

			br.close();

		} catch (Exception e) {

			System.out.println(e);

		}
		return builder;

	}
}
