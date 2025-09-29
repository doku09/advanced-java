package was.httpserver;

import was.v4.HttpRequestHandlerV4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.nio.charset.StandardCharsets.UTF_8;
import static util.MyLogger.log;

public class HttpServer {

	private final ExecutorService es = Executors.newFixedThreadPool(10);
	private final int port;
	private final ServletManager servletManager;

	public HttpServer(int port, ServletManager servletManager) {
		this.port = port;
		this.servletManager = servletManager;
	}

	public void start() throws IOException {
		ServerSocket serverSocket = new ServerSocket(port);
		log("서버시작 port: " + port);

		while(true) {
			Socket socket = serverSocket.accept();
			es.submit(new HttpRequestHandler(socket, servletManager));
		}
	}
}
