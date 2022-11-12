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
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyProductFormController implements Initializable {

    private ObservableList<Part> associatedPartList = FXCollections.observableArrayList();
    @FXML
    private TextField SearchPart;
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
    private TableView<Part> PartTableView;
    @FXML
    private TableColumn<Part,Integer> PartIdColumn;
    @FXML
    private TableColumn<Part,String> PartNameColumn;
    @FXML
    private TableColumn<Part,Inventory> PartInventoryColumn;
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

    /**
     * same function from AddProduct controller recycled
     * allows user to add part to product
     * @param actionEvent
     * */
    public void addPartToProduct(ActionEvent actionEvent) {
        Part part = PartTableView.getSelectionModel().getSelectedItem();

        if(part != null) {
            associatedPartList.add(part);
            AssociatedPartTableView.setItems(associatedPartList);
        }

        else if(!associatedPartList.contains(null)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("part not selected from list");
            alert.showAndWait();
        }
    }
    /**
     * same function from AddProduct controller
     * allows user to remove part from product
     * @param actionEvent
     * */
    public void removePart(ActionEvent actionEvent) {
        Part part = AssociatedPartTableView.getSelectionModel().getSelectedItem();

        if(part != null) {
            associatedPartList.remove(part);
            AssociatedPartTableView.setItems(associatedPartList);
        }

        else if(!associatedPartList.contains(null)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("part not selected from list");
            alert.showAndWait();
        }
    }

    /**
     * ran into 3 hour issue here because my updateProduct function in inventory was blank
     * saves product modification
     * @param actionEvent
     * */
    @FXML
    public void saveProduct(ActionEvent actionEvent) throws IOException {
        try {
            int ProductID = Integer.parseInt(ProductId.getText());
            String Name = ProductName.getText();
            int Stock = Integer.parseInt(ProductInventory.getText());
            double Price = Double.parseDouble(ProductPrice.getText());
            int Max = Integer.parseInt(ProductMax.getText());
            int Min = Integer.parseInt(ProductMin.getText());

            if (Stock > Max || Stock < Min) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory requirements: Inventory must be within min and max.");
                alert.showAndWait();
                return;
            } else if (Min >= Max) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory requirements: maximum must be greater than minimum");
                alert.showAndWait();
                return;
            }

            Product product = new Product(ProductID,Name,Price,Stock,Min,Max);
            if (!product.equals(associatedPartList)) {
                Inventory.updateProduct(index, product);
            }

            for (Part part: associatedPartList) {
                if (part != associatedPartList)
                    product.addAssociatedPart(part);
            }




            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Object scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
            stage.setScene(new Scene((Parent) scene));
            stage.show();

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setContentText("Incorrect value");
            alert.showAndWait();

        }
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

        associatedPartList.addAll(selectedItem.getAllAssociatedParts());

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        PartTableView.setItems(Inventory.getAllParts());
        PartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        AssociatedPartTableView.setItems(associatedPartList);
        AssociatedPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        AssociatedPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        AssociatedPartInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        AssociatedPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    /**
     * used same search function as AddProductForm to search by ID and name
     * @param event
     * */
    public void searchPart(ActionEvent event) {
        String search = SearchPart.getText();
        ObservableList<Part> partFound = Inventory.lookupPart(search);
        try {
            while(partFound.size() == 0){
                int productId = Integer.parseInt(search);
                partFound.add(Inventory.lookupPart(productId));
            }
            PartTableView.setItems(partFound);
        }
        catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nothing Found");
            alert.setContentText("No Part was found in the list");
            alert.showAndWait();
        }
    }
}
