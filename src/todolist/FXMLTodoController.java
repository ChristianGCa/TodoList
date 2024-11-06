/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package todolist;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author christian
 */
public class FXMLTodoController implements Initializable {

    private String url = "http://localhost:3000/tarefas/";

    @FXML
    private Button btAdicionarTarefa;

    @FXML
    private ComboBox<String> cbPrioridade;

    @FXML
    private TextField tfTarefa;

    @FXML
    private VBox vboxTarefas;

    @FXML
    void adicionarTarefa(ActionEvent event) {

        String descricaoTarefa = tfTarefa.getText();
        String prioridadeSelecionada = cbPrioridade.getValue();

        criarTarefa(descricaoTarefa, prioridadeSelecionada);

        tfTarefa.clear();
        cbPrioridade.getSelectionModel().clearSelection();
    }

    void carregarPrioridades() {
        ObservableList<String> prioridades = FXCollections.observableArrayList("Alta", "Média", "Baixa");
        cbPrioridade.setItems(prioridades);
    }

    public void abrirAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR); // WARNING, CONFIRMATION, INFORMATION, NONE
        alert.setTitle("Aviso");
        alert.setContentText("Certifique-se de preencher o campo de Tarefa e selecionar uma prioridade");
        alert.show();
    }

    public void carregarTarefas() {
        RequestsHTTP request = new RequestsHTTP();
        String jsonResponse = request.getRequest(url); // Chama o método que faz a requisição e devolve o JSON

        JSONArray jsonArray = new JSONArray(jsonResponse);
        
         for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject item = jsonArray.getJSONObject(i);
            String tarefa = item.getString("tarefa");
            String prioridade = item.getString("prioridade");
            
             criarTarefa(tarefa, prioridade);
         }
    }
    
    public void criarTarefa(String tarefa, String prioridade) {
        
        if (tarefa == null || tarefa.trim().isEmpty() || prioridade == null || prioridade.trim().isEmpty()) {
            
                abrirAlert(); 
                
        } else {
             Label novaTarefa = new Label("--> : " + tarefa + " - Prioridade: " + prioridade);
                novaTarefa.setStyle("-fx-font-size: 16px;");
                vboxTarefas.getChildren().add(novaTarefa);

                Button btnExcluir = new Button("Excluir");
                btnExcluir.setOnAction(e -> vboxTarefas.getChildren().remove(novaTarefa.getParent())); // Remove o HBox da tarefa

                HBox tarefaBox = new HBox(10, novaTarefa, btnExcluir); // 10 é o espaçamento entre o label e o botão
                vboxTarefas.getChildren().add(tarefaBox); // Adiciona o HBox ao VBox
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarPrioridades();
        carregarTarefas();
    }

}
