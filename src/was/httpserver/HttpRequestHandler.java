package was.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static java.nio.charset.StandardCharsets.UTF_8;
import static util.MyLogger.log;

public class HttpRequestHandler implements Runnable{
	private final Socket socket;
	private final ServletManager servletManager;

	public HttpRequestHandler(Socket socket, ServletManager servletManager) {
		this.socket = socket;
		this.servletManager = servletManager;
	}

	@Override
	public void run() {
		try {
			process();
		} catch (Exception e) {
			log(e);
		}
	}

	private void process() throws IOException {
		try (socket;
		     BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), UTF_8));
		     PrintWriter writer = new PrintWriter(socket.getOutputStream(), false, UTF_8);
		) {
			HttpRequest request = new HttpRequest(reader);
			HttpResponse response = new HttpResponse(writer);

			log("HTTP 요청: " + request);
			servletManager.execute(request, response);
			response.flush();
			log("HTTP 응답 완료");
		}
	}

	private void home(HttpResponse response) {
		response.writeBody("<h1>home</h1>");
		response.writeBody("<ul");
		response.writeBody("<li><a href='/site1'>site1</a></li>");
		response.writeBody("<li><a href='/site2'>site2</a></li>");
		response.writeBody("<li><a href='/search?q=hello'>검색</a></li>");
		response.writeBody("</ul>");
	}

	private void site1(HttpResponse response) {
		response.writeBody("<h1>site1</h1>");
	}

	private void site2(HttpResponse response) {
		response.writeBody("<h1>site2</h1>");
	}

	// GET / search?q=hello HTTP/1.1
	private void search(HttpRequest request, HttpResponse response) {
		String query = request.getParameter("q");
		response.writeBody("<h1>Search</h1>");
		response.writeBody("<ul");
		response.writeBody("<li>query: " + query + "</li>");
		response.writeBody("</ul>");
	}

	private void notFound(HttpResponse response) {
		response.setStatusCode(404);
		response.writeBody("<h1>404 페이지를 찾을 수 없습니다.</h1>");
	}

	private static void sleep(int millis) {

		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	private void responseToClient(PrintWriter writer) {
		// 웹브라우저에 전달하는 낸용

		String body = "<h1>Hello World</h1>";
		byte[] length = body.getBytes(UTF_8);

		StringBuilder sb = new StringBuilder();
		sb.append("HTTP/1.1 200 OK\r\n");
		sb.append("Content-type: text/html\r\n");
		sb.append("Content-Length: ").append(length).append("\r\n");
		sb.append("\r\n"); //header, body 구분 라인
		sb.append(body);

		log("HTTP 응답 정보 출력");
		System.out.println(sb);

		writer.println(sb);
		writer.flush();
	}

	private static String requestToString(BufferedReader reader) throws IOException {
		StringBuilder sb = new StringBuilder();
		String line;
		while((line = reader.readLine()) != null) {
			if (line.isEmpty()) {
				break;
			}
			sb.append(line).append("\n");
		}
		return sb.toString();
	}
}
