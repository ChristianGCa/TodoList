package todolist;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 *
 * @author christian
 */
public class RequestsHTTP {

    public String getRequest(String url) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());




            return response.body();
            
        } catch (Exception e) {
            System.out.println("Erro: "+e);
        }
        return "[]";
    }
    
    public void postRequest(String url, String tarefa, String prioridade) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            String json = "{Tarefa: " + tarefa + ", " + "Prioridade: " + prioridade + "}";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json)) // Método POST com corpo
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Status Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deleteRequest() {
        
    }
    
}
    
    
    
