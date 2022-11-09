package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * adds part to allParts Observable list
     * @param newPart
     * */
    public static void addPart(Part newPart){
        allParts.add(newPart);
    }

    /**
     * adds product to allProducts Observable list
     * @param newProduct
     * */
    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }

    /**
     * iterates over all parts and returns part if ID matches
     * @param partId
     * @return part
     * */
    public static Part lookupPart(int partId) {
        for (Part part : Inventory.getAllParts()) {
            while (part.getId() == partId) {
                return part;
            }
        }
        return null;
    }

    /**
     * iterates over all products and returns product if ID matches
     * @param productId
     * @return product
     * */
    public static Product lookupProduct(int productId){
        for(Product product: Inventory.getAllProducts()){
            while (product.getId() == productId)
                return product;
        }
        return null;

    }

    /**
     * creates an observable list if partName is within a list of all parts and returns the part
     * @param partName
     * @return partsFound
     * */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> partsFound = FXCollections.observableArrayList();

        for (Part part: allParts) {
            if (part.getName().toLowerCase().contains(partName.toLowerCase())) {
                partsFound.add(part);
            }
        }
        return partsFound;

    }
    /**
     * creates an observable list if productName is within a list of all products and returns the product
     * @param productName
     * @return productsFound
     * */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> productsFound = FXCollections.observableArrayList();

        for(Product product: allProducts){
            if (product.getName().toLowerCase().contains(productName.toLowerCase())){
                productsFound.add(product);
            }
        }
        return productsFound;

    }

    /**
     * updates the part
     * @param index
     * @param selectedPart
     * */
    public static void updatePart(int index, Part selectedPart){
        allParts.set(index,selectedPart);

    }

    /**
     * updates product
     * @param index
     * @param selectedProduct
     * */
    public static void updateProduct(int index, Product selectedProduct){

    }

    /**
     * deletes part
     * @param selectedPart
     * */
    public static boolean deletePart(Part selectedPart){
    return allParts.remove(selectedPart);
    }

    /**
     * deletes part
     * @param selectedProduct
     * */
    public static boolean deleteProduct(Product selectedProduct){
    return allProducts.remove(selectedProduct);
    }

    /**
     * returns all parts in products observable list
     * @return allParts
     * */
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }
    /**
     * returns all products in products observable list
     * @return allProducts
     * */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }


}