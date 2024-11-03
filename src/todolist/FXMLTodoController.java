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
import javafx.scene.layout.VBox;

/**
 *
 * @author christian
 */
public class FXMLTodoController implements Initializable {
    
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
        
        if (descricaoTarefa != null && prioridadeSelecionada != null) {
            
            Label novaTarefa = new Label("[!]: " + descricaoTarefa + " - Prioridade: " + prioridadeSelecionada);
            novaTarefa.setStyle("-fx-font-size: 16px;");
            vboxTarefas.getChildren().add(novaTarefa);
            
            tfTarefa.clear();
            cbPrioridade.getSelectionModel().clearSelection();
            
        }else {
            
            abrirAlert();
            
        }
    }
    
    void carregarPrioridades() {
        ObservableList<String> prioridades = FXCollections.observableArrayList("Alta", "Média", "Baixa");
        cbPrioridade.setItems(prioridades); 
    }
    
    public void abrirAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING); // WARNING, CONFIRMATION, INFORMATION, NONE
        alert.setTitle("Aviso");
        alert.setContentText("Certifique-se de preencher o campo de Tarefa e selecionar uma prioridade");
        alert.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarPrioridades();
    }    
}
