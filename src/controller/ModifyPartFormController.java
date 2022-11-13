package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
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



    /**
     * FUTURE ENHANCEMENT:Would like to add a feature that promps the user If they are sure
     * that they would like to save their part, similar to the prompt that asks the user if they
     * would like to delete the part.
     *
     * ERRORS:was having trouble with this function because I reused code from savePart function from
     * AddPartForm and realized I wasn't using the updatePart Function in Inventory model class
     * @param actionEvent
     * */
    @FXML
    public void savePart(ActionEvent actionEvent) throws IOException {
        try {
            int PartId = Integer.parseInt(ModifyPartID.getText());
            String Name = PartName.getText();
            int Stock = Integer.parseInt(PartInventory.getText());
            double Price = Double.parseDouble(PartPrice.getText());
            int Max = Integer.parseInt(PartMax.getText());
            int Min = Integer.parseInt(PartMin.getText());

            int machineID;
            String companyName;

            //checks whether min is less than max, if not then displays error
            if (Max < Min) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Max is not greater than Min");
                alert.showAndWait();
                return;
            }
            //checks whether inventory is within min and max range, if not then displays error
            else if (Stock < Min || Max < Stock) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory be in Min to Max Range");
                alert.showAndWait();
                return;
            }


            //checks if part outsourced or inhouse is selected
            if (PartOutsourcedRadio.isSelected()) {
                companyName = MachineOrCompany.getText();
                Outsourced updatePart = new Outsourced(PartId, Name, Price, Stock, Min, Max, companyName);
                Inventory.updatePart(index,updatePart);
            }
            if (PartInHouseRadio.isSelected()) {
                machineID = Integer.parseInt(MachineOrCompany.getText());
                InHouse updatePart = new InHouse(PartId, Name, Price, Stock, Min, Max, machineID);
                Inventory.updatePart(index,updatePart);
            }
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Object scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
            stage.setScene(new Scene((Parent) scene));
            stage.show();
            // catch used to diplay incorrect values entered into the form
        }
        catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setContentText("Incorrect values");
            alert.showAndWait();
    }
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
     * takes information on part from mainForm and sends it to modifypartForm
     * ERRORS: label for machine or company name only displays correctly if
     * clicking onto the opposite radio button and clicking back on to it. will try to check
     * the fxml and see if anything is wrong with that first.
     *
     * @param selectedIndex
     * @param selectedItem
     * */
    public void sendPartToModify(int selectedIndex, Part selectedItem) {
        index = selectedIndex;
        if(selectedItem instanceof InHouse){
            PartInHouseRadio.setSelected(true);
            MachineOrCompany.setText(String.valueOf(((InHouse) selectedItem).getMachineId()));
            MachineId.setText("Machine ID");
        }
        else{

            PartOutsourcedRadio.setSelected(true);
            MachineOrCompany.setText(((Outsourced) selectedItem).getCompanyName());
            MachineId.setText("Company Name");
        }
        ModifyPartID.setText(String.valueOf(selectedItem.getId()));
        PartName.setText(String.valueOf(selectedItem.getName()));
        PartInventory.setText(String.valueOf(selectedItem.getStock()));
        PartPrice.setText(String.valueOf(selectedItem.getPrice()));
        PartMax.setText(String.valueOf(selectedItem.getMax()));
        PartMin.setText(String.valueOf(selectedItem.getMin()));
    }
   /**
     * sets machineId label to machine id
     * @param event
     * */
    @FXML
    public void setMachine(ActionEvent event) {

        MachineId.setText("Machine ID");
    }

    /**
     * sets machineId label to comapany name
     * @param event
     * */
    @FXML
    public void setCompany(ActionEvent event) {
        MachineId.setText("Company Name");
    }

}
