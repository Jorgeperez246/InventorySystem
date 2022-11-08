package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {

    @FXML
    private TableView PartTableView;
    @FXML
    private TableColumn PartIdColumn;
    @FXML
    private TableColumn PartNameColumn;
    @FXML
    private TableColumn PartInventoryColumn;
    @FXML
    private TableColumn PartPriceColumn;
    @FXML
    private Button AddPartButton;
    @FXML
    private Button ModifyPartButton;
    @FXML
    private Button DeletePartButton;
    @FXML
    private TextField PartSearch;
    @FXML
    private TableView ProductTableView;
    @FXML
    private TableColumn ProductIdColumn;
    @FXML
    private TableColumn ProductNameColumn;
    @FXML
    private TableColumn ProductInventoryColumn;
    @FXML
    private TableColumn ProductPriceColumn;
    @FXML
    private Button AddProductButton;
    @FXML
    private Button ModifyProductButton;
    @FXML
    private Button DeleteProductButton;
    @FXML
    private TextField ProductSearch;
    @FXML
    private Button ExitAppButton;

    //Opens AddPartForm fxml file
    public void addPart(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/view/AddPartForm.fxml"));
        stage.setTitle("Add Part");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void modifyPart(ActionEvent actionEvent) {
    }

    public void deletePart(ActionEvent actionEvent) {
    }

    public void modifyProduct(ActionEvent actionEvent) {
    }

    public void deleteProduct(ActionEvent actionEvent) {
    }
    // Exits the application
    public void exitApp(ActionEvent actionEvent) {
        System.exit(0);
    }
    public void addProduct(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/view/AddProductForm.fxml"));
        stage.setTitle("Add Product");
        stage.setScene(new Scene(scene));
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
