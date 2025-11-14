package com.codingkiddo.api.examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AuthHttpServer {

	// Hard-coded demo credentials
	private static final String USERNAME = "admin";
	private static final String PASSWORD = "secret";

	public static void main(String[] args) {
		int port = 8080;
		System.out.println("Auth HTTP server running on http://localhost:" + port);

		try (ServerSocket serverSocket = new ServerSocket(port)) {
			while (true) {
				// Accept one connection (single-threaded for simplicity)
				try (Socket client = serverSocket.accept()) {
					handleClient(client);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			System.err.println("Failed to bind to port " + port);
			e.printStackTrace();
		}
	}

	private static void handleClient(Socket client) throws IOException {
		try (BufferedReader in = new BufferedReader(
				new InputStreamReader(client.getInputStream(), StandardCharsets.UTF_8));
				OutputStream out = client.getOutputStream()) {
			// --- 1. Read request line and headers (very naive HTTP parsing) ---
			String requestLine = in.readLine();
			if (requestLine == null || requestLine.isEmpty()) {
				return;
			}

			// Example: "GET / HTTP/1.1"
			System.out.println("Request: " + requestLine);

			String authorizationHeader = null;
			String line;
			while ((line = in.readLine()) != null && !line.isEmpty()) {
				// Uncomment to debug:
				// System.out.println("Header: " + line);

				if (line.toLowerCase().startsWith("authorization:")) {
					authorizationHeader = line.substring("authorization:".length()).trim();
				}
			}

			// --- 2. Check auth ---
			if (!isAuthorized(authorizationHeader)) {
				sendUnauthorized(out);
				return;
			}

			// --- 3. If authorized, send a normal 200 OK response ---
			String responseBody = "Hello, " + USERNAME + "! You are authenticated.\n";

			byte[] bodyBytes = responseBody.getBytes(StandardCharsets.UTF_8);
			String response = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/plain; charset=utf-8\r\n" + "Content-Length: "
					+ bodyBytes.length + "\r\n" + "Connection: close\r\n" + "\r\n";

			out.write(response.getBytes(StandardCharsets.UTF_8));
			out.write(bodyBytes);
			out.flush();
		}
	}

	private static boolean isAuthorized(String authorizationHeader) {
		if (authorizationHeader == null) {
			return false;
		}

		// Expecting: "Basic base64(username:password)"
		if (!authorizationHeader.regionMatches(true, 0, "Basic ", 0, 6)) {
			return false;
		}

		String base64Credentials = authorizationHeader.substring(6).trim();
		String decoded;
		try {
			decoded = new String(Base64.getDecoder().decode(base64Credentials), StandardCharsets.UTF_8);
		} catch (IllegalArgumentException e) {
			// Invalid Base64
			return false;
		}

		String[] parts = decoded.split(":", 2);
		if (parts.length != 2) {
			return false;
		}

		String user = parts[0];
		String pass = parts[1];

		return USERNAME.equals(user) && PASSWORD.equals(pass);
	}

	private static void sendUnauthorized(OutputStream out) throws IOException {
		// Note the WWW-Authenticate header here
		String response = "HTTP/1.1 401 Unauthorized\r\n" + "WWW-Authenticate: Basic realm=\"MyRawJavaServer\"\r\n"
				+ "Content-Length: 0\r\n" + "Connection: close\r\n" + "\r\n";

		out.write(response.getBytes(StandardCharsets.UTF_8));
		out.flush();
	}
}
