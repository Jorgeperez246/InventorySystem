package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MainFormController {
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


    public void addPart(ActionEvent actionEvent) {
    }

    public void modifyPart(ActionEvent actionEvent) {
    }

    public void deletePart(ActionEvent actionEvent) {
    }

    public void modifyProduct(ActionEvent actionEvent) {
    }

    public void deleteProduct(ActionEvent actionEvent) {
    }

    public void exitApp(ActionEvent actionEvent) {
    }
}
