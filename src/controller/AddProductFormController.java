package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class AddProductFormController {
    @FXML
    private TextField ProductId;
    @FXML
    private TextField ProductName;
    @FXML
    private TextField ProductInventory;
    @FXML
    private TextField ProductPrice;
    @FXML
    private TextField ProductMax;
    @FXML
    private TextField ProductMin;
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
    private TableView AssociatedPartTableView;
    @FXML
    private TableColumn AssociatedPartIdColumn;
    @FXML
    private TableColumn AssociatedPartPriceColumn;
    @FXML
    private TableColumn AssociatedPartNameColumn;
    @FXML
    private TableColumn AssociatedPartInventoryColumn;
    @FXML
    private Button RemovePartButton;
    @FXML
    private Button SaveProductButton;
    @FXML
    private Button CancelProductButton;

    public void addPartToProduct(ActionEvent actionEvent) {
    }

    public void removePart(ActionEvent actionEvent) {
    }

    public void saveProduct(ActionEvent actionEvent) {
    }

    public void cancelProduct(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setTitle("Inventory System");
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
