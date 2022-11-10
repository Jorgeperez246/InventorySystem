package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.InHouse;
import model.Outsourced;
import model.Part;

import java.io.IOException;

public class ModifyPartFormController {

    private int index = 0;
    @FXML
    private RadioButton PartInHouseRadio;
    @FXML
    private RadioButton PartOutsourcedRadio;
    @FXML
    private Label MachineId;
    @FXML
    private TextField ModifyPartID;
    @FXML
    private TextField PartName;
    @FXML
    private TextField PartInventory;
    @FXML
    private TextField PartPrice;
    @FXML
    private TextField PartMax;
    @FXML
    private TextField MachineOrCompany;
    @FXML
    private TextField PartMin;
    @FXML
    private ToggleGroup AddPartToggle;
    @FXML
    private Button SavePartButton;
    @FXML
    private Button CancelPartButton;

    public void savePart(ActionEvent actionEvent) {
    }
    /**
     * returns to mainForm
     * @param actionEvent
     * */
    public void cancelPart(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setTitle("Inventory Management System");
        stage.setScene(new Scene((Parent) scene));
        stage.show();
    }
    /**
     * takes information on part from mainForm
     * @param selectedIndex
     * @param selectedItem
     * */
    public void sendPartToModify(int selectedIndex, Part selectedItem) {
        index = selectedIndex;
        if(selectedItem instanceof InHouse){
            PartInHouseRadio.setSelected(true);
            MachineOrCompany.setText(String.valueOf(((InHouse) selectedItem).getMachineId()));
        }
        else{
            PartOutsourcedRadio.setSelected(true);
            MachineOrCompany.setText(((Outsourced) selectedItem).getCompanyName());
        }
        ModifyPartID.setText(String.valueOf(selectedItem.getId()));
        PartName.setText(String.valueOf(selectedItem.getName()));
        PartInventory.setText(String.valueOf(selectedItem.getStock()));
        PartPrice.setText(String.valueOf(selectedItem.getPrice()));
        PartMax.setText(String.valueOf(selectedItem.getMax()));
        PartMin.setText(String.valueOf(selectedItem.getMin()));
    }
}
