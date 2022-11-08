package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ModifyProductFormController {
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

    public void cancelProduct(ActionEvent actionEvent) {
    }
}
