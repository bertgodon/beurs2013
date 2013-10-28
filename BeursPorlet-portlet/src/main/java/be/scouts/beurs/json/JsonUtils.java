package be.scouts.beurs.json;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

public final class JsonUtils {
	
private static ObjectMapper objectMapper = new ObjectMapper();
	
	private JsonUtils() {}
	
	public static String toJson(Object object) throws IOException{
		StringWriter writer = new StringWriter();
		
		toJson(writer, object);
		
		return writer.toString();
	}
	
	public static void toJson(ResourceResponse response, Object object) throws IOException{
		objectMapper.writeValue(response.getWriter(), object);
		response.setContentType("application/json");
	}
	
	public static void toJson(HttpServletResponse response, Object object) throws IOException{
		objectMapper.writeValue(response.getWriter(), object);
		response.setContentType("application/json");
	}
	
	public static void toJson(Writer writer, Object object) throws IOException{
		objectMapper.writeValue(writer, object);
	}
	
	public static <T> T fromJson(String input, Class<T> t) throws IOException {
		return objectMapper.readValue(input, t);
	}
}
