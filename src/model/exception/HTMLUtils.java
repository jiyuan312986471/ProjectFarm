package model.exception;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

public class HTMLUtils {

	public static String buildHeader(String title) {
		StringBuilder sb = new StringBuilder();
		sb.append("<!DOCTYPE html>");
		sb.append("<html lang=\"en\">");
		sb.append("<head>");
		sb.append("<title>" + title + "</title>");
		sb.append("<meta charset=\"utf-8\">");
		sb.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
		sb.append("<link rel=\"stylesheet\"");
		sb.append("href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css");
		sb.append("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js\"></script>");
		sb.append("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js\"></script>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("<div class=\"container\">");
		//sb.append("<h4>Module " + module + ", Example " + example + "</h4>");
		sb.append("<div class=\"panel panel-primary\">");
		sb.append("<div class=\"panel-heading\">" + title + "</div>");
		sb.append("<div class=\"panel-body\">");

		return sb.toString();
	}

	public static String buildFooter() {
		StringBuilder sb = new StringBuilder();
		sb.append("</div>"); // closing panel body
		sb.append("</div>"); // closing panel default
		sb.append("</div>"); // closing container
		sb.append("</body></html>");
		return sb.toString();
	}

	public static String buildErrorMessages(String messages) {
		
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(buildHeader("Please check the following list of errors:"));
			BufferedReader bre = new BufferedReader(new StringReader(messages));
			
			String line = null;
			while ((line = bre.readLine()) != null) {
				sb.append("<div class=\"alert alert-danger\" role=\"alert\">");
				sb.append("<span class=\"glyphicon glyphicon-exclamation-sign\" aria-hidden=\"true\"></span>");
				sb.append("<span class=\"sr-only\">Error:</span>");
				sb.append(line);
				sb.append("</div>");
			}
			sb.append(buildFooter());
			return sb.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

}
