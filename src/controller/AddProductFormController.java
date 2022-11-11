package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddProductFormController implements Initializable {
    private ObservableList<Part> associatedPart = FXCollections.observableArrayList();
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
    private TableView<Part> PartTableView;
    @FXML
    private TableColumn<Part,Integer> PartIdColumn;
    @FXML
    private TableColumn<Part,String> PartNameColumn;
    @FXML
    private TableColumn<Part,Integer> PartInventoryColumn;
    @FXML
    private TableColumn<Part,Double> PartPriceColumn;
    @FXML
    private TableView<Part> AssociatedPartTableView;
    @FXML
    private TableColumn<Part,Integer> AssociatedPartIdColumn;
    @FXML
    private TableColumn<Part,Double> AssociatedPartPriceColumn;
    @FXML
    private TableColumn<Part,String> AssociatedPartNameColumn;
    @FXML
    private TableColumn<Part,Integer> AssociatedPartInventoryColumn;
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

    /**
     * sends you back to main Form
     * @param event
     * */
    public void cancelProduct(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setTitle("Inventory System");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        PartTableView.setItems(Inventory.getAllParts());
        PartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        AssociatedPartTableView.setItems(associatedPart);
        AssociatedPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        AssociatedPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        AssociatedPartInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        AssociatedPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

    }


}
