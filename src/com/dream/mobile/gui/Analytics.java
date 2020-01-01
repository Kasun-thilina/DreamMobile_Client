package com.dream.mobile.gui;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import javax.swing.JFrame;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class Analytics extends JFrame {
	// HTTP Client for API Connection
	private final static HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();

	public Analytics() {
		setResizable(false);
		
		JLabel lblNewLabel = new JLabel("New label");
		getContentPane().add(lblNewLabel, BorderLayout.NORTH);
	}

	public static void main(String[] args) throws Exception {
		String imageUrl = "https://quickchart.io/chart/render/9a560ba4-ab71-4d1e-89ea-ce4741e9d232";
		String destinationFile = "image22.jpg";

		chartConnection();
		saveImage(imageUrl, destinationFile);
	}

	public static void saveImage(String imageUrl, String destinationFile) throws IOException {
		URL url = new URL(imageUrl);
		InputStream is = url.openStream();
		OutputStream os = new FileOutputStream(destinationFile);

		byte[] b = new byte[2048];
		int length;

		while ((length = is.read(b)) != -1) {
			os.write(b, 0, length);
		}

		is.close();
		os.close();
	}

	public static String chartConnection() {
		String str = "{\"chart\": {\"type\": \"bar\", \"data\": {\"labels\": [\"Hello\", \"World\"], \"datasets\": [{\"label\": \"Foo\", \"data\": [1, 2]}]}}}";

		HttpRequest request = HttpRequest.newBuilder().POST(BodyPublishers.ofString(str))
				.uri(URI.create("https://quickchart.io/chart/create")).setHeader("User-Agent", "Java 11 HttpClient Bot")																								// header
				.header("Content-Type", "application/json").build();

		HttpResponse<String> response = null;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// print status code
		System.out.println(response.statusCode());

		// print response body
		System.out.println(response.body());
		String url=null;
		try {
			JSONObject jsonObject= (JSONObject) new JSONParser().parse(response.body());
			url=(String) jsonObject.get("url");
			System.out.println(url);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return url;
	}

	

}