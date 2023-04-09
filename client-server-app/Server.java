import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import com.sun.net.httpserver.HttpServer;

public class Server {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(4004), 0);
        server.createContext("/", exchange -> {
            Map<String, String> params = DesktopClient.queryToMap(exchange.getRequestURI().getQuery());
            String text = URLDecoder.decode(params.get("Text"), StandardCharsets.UTF_8);

            String response = "Answer from server: " + text;

            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        });
        server.setExecutor(null);
        server.start();
    }
}
