package was.v6;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;

import java.io.IOException;

public class SearchControllerV6 {

	public void search(HttpRequest request, HttpResponse response) throws IOException {
		String query = request.getParameter("q");
		response.writeBody("<h1>Search</h1>");
		response.writeBody("<ul");
		response.writeBody("<li>query: " + query + "</li>");
		response.writeBody("</ul>");
	}
}
