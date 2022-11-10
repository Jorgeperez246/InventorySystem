package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyProductFormController implements Initializable {

    private ObservableList<Part> associatedPartList = FXCollections.observableArrayList();
    private int index = 0;
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

    public void cancelProduct(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setTitle("Inventory Management System");
        stage.setScene(new Scene((Parent) scene));
        stage.show();
    }

    /**
     * sends product info from mainform to modifyproductform
     * @param selectedIndex
     * @param selectedItem
     * */
    public void sendProductToModify(int selectedIndex, Product selectedItem) {
        index = selectedIndex;
        ProductId.setText(String.valueOf(selectedItem.getId()));
        ProductName.setText(String.valueOf(selectedItem.getName()));
        ProductInventory.setText(String.valueOf(selectedItem.getStock()));
        ProductPrice.setText(String.valueOf(selectedItem.getPrice()));
        ProductMax.setText(String.valueOf(selectedItem.getMax()));
        ProductMin.setText(String.valueOf(selectedItem.getMin()));

        for (Part part: selectedItem.getAllAssociatedParts()){
            associatedPartList.add(part);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
