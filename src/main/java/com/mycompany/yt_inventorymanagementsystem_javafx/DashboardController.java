/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.yt_inventorymanagementsystem_javafx;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.xml.transform.Result;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class DashboardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button addProduct_add_btn;

    @FXML
    private TextField addProduct_brand_txt;

    @FXML
    private FontAwesomeIconView addProduct_btn;

    @FXML
    private Button addProduct_delete_btn;

    @FXML
    private ImageView addProduct_imgView;

    @FXML
    private Button addProduct_imort_btn;

    @FXML
    private TextField addProduct_price_txt;

    @FXML
    private TextField addProduct_productId_txt;

    @FXML
    private TextField addProduct_productName_txt;

    @FXML
    private ComboBox<?> addProduct_productType_combo;

    @FXML
    private Button addProduct_reset_btn;

    @FXML
    private TextField addProduct_search_txt;

    @FXML
    private ComboBox<?> addProduct_status_combo;

    @FXML
    private TableView<ProductsModel> addProduct_tableView;

    @FXML
    private TableColumn<ProductsModel, String> addProduct_tbvCol_brand;

    @FXML
    private TableColumn<ProductsModel, String> addProduct_tbvCol_price;

    @FXML
    private TableColumn<ProductsModel, String> addProduct_tbvCol_productID;

    @FXML
    private TableColumn<ProductsModel, String> addProduct_tbvCol_productName;

    @FXML
    private TableColumn<ProductsModel, String> addProduct_tbvCol_status;

    @FXML
    private TableColumn<ProductsModel, String> addProduct_tbvCol_type;

    @FXML
    private Button addProduct_update_btn;

    @FXML
    private Button addProducts_btn;

    @FXML
    private AnchorPane addProducts_form;

    @FXML
    private Button close_btn;

    @FXML
    private Label home_availableProducts_lbl;

    @FXML
    private Button home_btn;

    @FXML
    private AnchorPane home_form;

    @FXML
    private AreaChart<?, ?> home_incomeChart;

    @FXML
    private Label home_numberOfOrders_lbl;

    @FXML
    private BarChart<?, ?> home_orderChart;

    @FXML
    private Label home_totalIncome_lbl;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane main_form;

    @FXML
    private FontAwesomeIconView minize_btn;

    @FXML
    private Button orders_add_btn;

    @FXML
    private TextField orders_amount_txt;

    @FXML
    private Label orders_balance_lbl;

    @FXML
    private ComboBox<?> orders_brandName_combo;

    @FXML
    private Button orders_btn;

    @FXML
    private TableColumn<CustomerData, String> orders_col_brand;

    @FXML
    private TableColumn<CustomerData, String> orders_col_price;

    @FXML
    private TableColumn<CustomerData, String> orders_col_productName;

    @FXML
    private TableColumn<CustomerData, String> orders_col_quantity;

    @FXML
    private TableColumn<CustomerData, String> orders_col_type;

    @FXML
    private AnchorPane orders_form;

    @FXML
    private Button orders_pay_btn;

    @FXML
    private ComboBox<?> orders_productName_combo;

    @FXML
    private ComboBox<?> orders_productType_combo;

    @FXML
    private Spinner<Integer> orders_quantity_spinner;

    @FXML
    private Button orders_receipt_btn;

    @FXML
    private Button orders_reset_btn;

    @FXML
    private TableView<CustomerData> orders_tableView;

    @FXML
    private Label orders_total_lbl;

    @FXML
    private Label username_lbl;

    // DATABASE TOOLS
    private Connection conn;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet resultSet;
    
    
    public void homeDisplayTotalOrders(){
//        
//        Date date = new Date();
//                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//                    prepare.setString(8, String.valueOf(sqlDate));


        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

//        String sql = "SELECT COUNT(id) AS count_of_id FROM customer_receipt";
        String sql = "SELECT COUNT(id) FROM customer_receipt WHERE date = '"+sqlDate+"' ";
        
        conn = databaseHandler.connectDb();
        
        int countOrders = 0;
        
        try {
            
            prepare = conn.prepareStatement(sql);
            resultSet = prepare.executeQuery();
            
            if(resultSet.next()){
//                countOrders = resultSet.getInt("count_of_id");
                countOrders = resultSet.getInt("COUNT(id)");
            }
            
            home_numberOfOrders_lbl.setText(String.valueOf(countOrders));
            
        } catch (Exception e) {e.printStackTrace();}
        
    }
    
    public void homeTotalIncome(){
        
        String sql = "SELECT SUM(total) AS total_income FROM customer_receipt";
        
        conn = databaseHandler.connectDb();
        
        double totalIncome = 0;
        
        try {
            
            prepare = conn.prepareStatement(sql);
            resultSet = prepare.executeQuery();
            
            if(resultSet.next()){
                totalIncome = resultSet.getDouble("total_income");
            }
            
            home_totalIncome_lbl.setText(String.valueOf("LKR " + totalIncome));
            
        } catch (Exception e) {e.printStackTrace();}
        
    }

    public void homeAvailableProducts(){
        
        String sql = "SELECT COUNT(id) AS ava_products FROM product WHERE status = 'Available'";
        
        conn = databaseHandler.connectDb();
        
        int availableProducts = 0;
        
        try {
            
            prepare = conn.prepareStatement(sql);
            resultSet = prepare.executeQuery();
            
            if(resultSet.next()){
                availableProducts = resultSet.getInt("ava_products");
            }
            
            home_availableProducts_lbl.setText(String.valueOf(availableProducts));
            
        } catch (Exception e) {e.printStackTrace();}
        
    }
    
    public void homeIncomeChart(){
        
        home_incomeChart.getData().clear();
        
//        String sql = "SELECT date, SUM(total) FROM customer_receipt GROUP BY date GROUP BY TIMESTAMP(date) ASC LIMIT 6";
        
        String sql = "SELECT date, SUM(total) FROM customer_receipt GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 6;";

        conn = databaseHandler.connectDb();
        
        try {
            
            XYChart.Series chart = new XYChart.Series();
            
            prepare = conn.prepareStatement(sql);
            resultSet = prepare.executeQuery();
            
            while(resultSet.next()){
                chart.getData().add(new XYChart.Data(resultSet.getString(1), resultSet.getInt(2)));
            }
            
            home_incomeChart.getData().add(chart);
            
        } catch (Exception e) {e.printStackTrace();}
        
    }
    
    public void homeOrdersChart(){
        
        home_orderChart.getData().clear();
        
        String sql = "SELECT date, COUNT(id) FROM customer_receipt GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 5";
        
        /*
        SELECT date, SUM(total): We ask for two things: the date and the sum of income for each date. The SUM(total) adds up all income values for each date, so we get the total income on that day.
        FROM customer_receipt: This tells the database to look in the customer_receipt table.
        GROUP BY date: It groups the data by date, so it only shows one total amount per date.
        ORDER BY TIMESTAMP(date) ASC: Orders the data by date from oldest to newest.
        LIMIT 6: Only shows the last 6 days (or dates) of data.
        */
        
        conn = databaseHandler.connectDb();
        
        try {
            
            XYChart.Series chart = new XYChart.Series<>(); // This creates an empty series, which is like a container that will hold all the data points for the chart.
            
            prepare = conn.prepareStatement(sql);
            resultSet = prepare.executeQuery();
            
            while(resultSet.next()){
                chart.getData().add(new XYChart.Data(resultSet.getString(1), resultSet.getInt(2)));
                
                /*
                resultSet.next(): Moves to the next row of data.
                resultSet.getString(1): Gets the date from the first column.
                resultSet.getInt(2): Gets the total income for that date from the second column.
                */
                
            }
            
            home_orderChart.getData().add(chart);
            
        } catch (Exception e) {e.printStackTrace();}
        
    }
    
    public void addProductsAdd() {

        String sql = "INSERT INTO product (product_id, type, brand, product_name, price, status, image, date) "
                + "VALUES(?,?,?,?,?,?,?,?)";

        conn = databaseHandler.connectDb();

        try {

            Alert alert;

            if (addProduct_productId_txt.getText().isEmpty()
                    || addProduct_productType_combo.getSelectionModel().getSelectedItem() == null
                    || addProduct_brand_txt.getText().isEmpty()
                    || addProduct_productName_txt.getText().isEmpty()
                    || addProduct_price_txt.getText().isEmpty()
                    || addProduct_status_combo.getSelectionModel().getSelectedItem() == null
                    || GetData.path == "") {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();

            } else {

                // CHECK IF THE PRODUCT ID IS ALREADY EXIST
                String checkData = "SELECT product_id FROM product WHERE product_id = '"
                        + addProduct_productId_txt.getText() + "'";

                statement = conn.createStatement();
                resultSet = statement.executeQuery(checkData);

                if (resultSet.next()) {

                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Product ID: " + addProduct_productId_txt.getText() + " was already exist!");
                    alert.showAndWait();
                } else {

                    prepare = conn.prepareStatement(sql);
                    prepare.setString(1, addProduct_productId_txt.getText());
                    prepare.setString(2, (String) addProduct_productType_combo.getSelectionModel().getSelectedItem());
                    prepare.setString(3, addProduct_brand_txt.getText());
                    prepare.setString(4, addProduct_productName_txt.getText());
                    prepare.setString(5, addProduct_price_txt.getText());
                    prepare.setString(6, (String) addProduct_status_combo.getSelectionModel().getSelectedItem());

                    String uri = GetData.path;
                    uri = uri.replace("\\", "\\\\");
                    prepare.setString(7, uri);

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare.setString(8, String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    /*
                    Difference Between executeUpdate() and executeQuery():
                    executeUpdate():

                    This method is used for modifying the database. It is typically used with SQL statements such as INSERT, UPDATE, DELETE, and CREATE. These types of statements alter the contents or structure of the database but do not return data in the form of a result set.
                    It returns an integer representing the number of rows affected by the operation.
                    executeQuery():

                    This method is used for retrieving data from the database. It is typically used with SELECT statements, where the query returns a result set (data retrieved from the database).
                    It returns a ResultSet object, which contains the data retrieved by the query.
                     */
                    // TO BECOME UPDATED YOUR TABLEVIEW
                    addProductsShowListData();

                    // CLEAR THE FIELDS
                    addProductsReset();

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String[] listType = {"Snacks", "Drinks", "Dessert", "Gadgets", "Personal Product", "Others"};

    public void addProductsListTypes() {

        List<String> listT = new ArrayList<>();

        for (String data : listType) {
            listT.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listT);
        addProduct_productType_combo.setItems(listData);

    }

    private String[] listStatus = {"Available", "Not Available"};

    public void addProductsListStatus() {
        List<String> listS = new ArrayList<>();

        for (String data : listStatus) {
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        addProduct_status_combo.setItems(listData);
    }

    public void addproductsSelectedItemsToInputFields() {

        ProductsModel productsData = addProduct_tableView.getSelectionModel().getSelectedItem(); // TableView<ProductsModel> addProduct_tableView; nisa return wenneth ProductsModel object ekak
        int num = addProduct_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        } // This condition checks if the selected index (num) is invalid. If no row is selected (i.e., num is -1), the method returns and exits without doing anything.The num - 1 < -1 check prevents trying to access an invalid row.

        addProduct_productId_txt.setText(String.valueOf(productsData.getProductId())); // addProductsShowListData() Read this method to understand how the variable of the ProductsModel.java class matched to the column of the table
//        addProduct_productType_combo.getSelectionModel().clearSelection();
        addProduct_brand_txt.setText(productsData.getBrand());
        addProduct_productName_txt.setText(productsData.getProductName());
        addProduct_price_txt.setText(String.valueOf(productsData.getPrice()));
//        addProduct_status_combo.getSelectionModel().clearSelection();

        String uri = "file:" + productsData.getImage();

        image = new Image(uri, 115, 128, false, true);
        addProduct_imgView.setImage(image);

        GetData.path = productsData.getImage(); // After clicking the add button and entering the data into the database, the path of GetData.java is cleared due to the execution of addproductReset(). Since the path is empty, by clicking a row in the table, the path of the image can be set to the path of GetData.java.
    }

    public void addProductsUpdate() {

        String uri = GetData.path;
        uri = uri.replace("\\", "\\\\");

        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String sql = "UPDATE product SET type = '"
                + addProduct_productType_combo.getSelectionModel().getSelectedItem()
                + "', brand = '" + addProduct_brand_txt.getText()
                + "', product_name = '" + addProduct_productName_txt.getText()
                + "', price = '" + addProduct_price_txt.getText()
                + "', status = '" + addProduct_status_combo.getSelectionModel().getSelectedItem()
                + "', image = '" + uri
                + "', date = '" + sqlDate
                + "' WHERE product_id = '" + addProduct_productId_txt.getText() + "'";

        conn = databaseHandler.connectDb();

        try {

            Alert alert;

            if (addProduct_productId_txt.getText().isEmpty()
                    || addProduct_productType_combo.getSelectionModel().getSelectedItem() == null
                    || addProduct_brand_txt.getText().isEmpty()
                    || addProduct_productName_txt.getText().isEmpty()
                    || addProduct_price_txt.getText().isEmpty()
                    || addProduct_status_combo.getSelectionModel().getSelectedItem() == null
                    || GetData.path == "") {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();

            } else {

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Product ID: " + addProduct_productId_txt.getText() + " ?");

                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {

                    statement = conn.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated");
                    alert.showAndWait();

                    // TO BECOME UPDATED YOUR TABLEVIEW
                    addProductsShowListData();

                    // CLEAR THE FIELDS
                    addProductsReset();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addProductsDelete() {

        String sql = "DELETE FROM product WHERE product_id = '" + addProduct_productId_txt.getText() + "'";
        conn = databaseHandler.connectDb();

        try {

            Alert alert;

            if (addProduct_productId_txt.getText().isEmpty()
                    || addProduct_productType_combo.getSelectionModel().getSelectedItem() == null
                    || addProduct_brand_txt.getText().isEmpty()
                    || addProduct_productName_txt.getText().isEmpty()
                    || addProduct_price_txt.getText().isEmpty()
                    || addProduct_status_combo.getSelectionModel().getSelectedItem() == null
                    || GetData.path == "") {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();

            } else {

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Product ID: " + addProduct_productId_txt.getText() + " ?");

                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {

                    statement = conn.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted");
                    alert.showAndWait();

                    // TO BECOME UPDATED YOUR TABLEVIEW
                    addProductsShowListData();

                    // CLEAR THE FIELDS
                    addProductsReset();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addProductsReset() {

        addProduct_productId_txt.setText("");
        addProduct_productType_combo.getSelectionModel().clearSelection();
        addProduct_brand_txt.setText("");
        addProduct_productName_txt.setText("");
        addProduct_price_txt.setText("");
        addProduct_status_combo.getSelectionModel().clearSelection();
        addProduct_imgView.setImage(null);
        GetData.path = "";

    }

    private Image image;

    public void addProductsImportImage() {

        FileChooser open = new FileChooser(); // This creates a new FileChooser object, which is a JavaFX component that opens a dialog window to let the user browse and choose files from their computer.
        open.setTitle("Open Image File");
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*jpg", "*png")); // This line adds a filter to the FileChooser, so only image files with the extensions .jpg and .png are shown in the file browser. The user will only be able to select these types of image files.

        File file = open.showOpenDialog(main_form.getScene().getWindow()); // This is the method that opens the file dialog window, allowing the user to browse and select an image file.

        /*
        --What is a Modal Window?--
        A modal window is a type of window that temporarily blocks the interaction with the parent window (the main application window) until the modal window is closed. This means:

        While the modal window (in this case, the file dialog) is open, the user cannot interact with the main window (e.g., click buttons, type in text fields) until they either select a file or close the dialog.
        This is helpful to ensure that the user completes the current action (choosing an image) before interacting with the rest of the application.
        
        ---Why Specify the Parent Window?--
        The argument main_form.getScene().getWindow() is used to indicate the parent window of the file dialog, which ties the file dialog to the main application window. Here’s why this is important:

        Blocking interaction with the main window:

        When the file dialog is modal, it prevents the user from interacting with the parent window until they complete the task in the dialog (choosing a file or closing the dialog). This ensures the user focuses on selecting an image before doing anything else in the app.
        Dialog behavior and position:

        By specifying the parent window, the file dialog will appear centered relative to the main window. It also ensures that when you minimize or move the main window, the file dialog will minimize or move along with it.
        Maintaining application flow:

        If the user could interact with the main window while the file dialog was open, they might trigger unintended behaviors (such as clicking other buttons or opening additional dialogs), which could lead to confusion or errors in the application.
      
         */
        if (file != null) {

            GetData.path = file.getAbsolutePath(); // On Windows, an absolute path might look like this: C:\Users\John\Pictures\image.jpg

            image = new Image(file.toURI().toString(), 115, 128, false, true);
            addProduct_imgView.setImage(image);

        }
    }

    public void addProductsSearch() {

        FilteredList<ProductsModel> filter = new FilteredList<>(addProductList, e -> true);

        addProduct_search_txt.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateProductData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateProductData.getProductId().toString().contains(searchKey)) {
                    return true;
                } else if (predicateProductData.getType().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateProductData.getBrand().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateProductData.getProductName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateProductData.getPrice().toString().contains(searchKey)) {
                    return true;
                } else if (predicateProductData.getStatus().toLowerCase().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<ProductsModel> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(addProduct_tableView.comparatorProperty());
        addProduct_tableView.setItems(sortList);

    }

    public ObservableList<ProductsModel> addProductListDataGet() {

        ObservableList<ProductsModel> productList = FXCollections.observableArrayList();

        String sql = "SELECT * FROM product";
        conn = databaseHandler.connectDb();

        try {

            prepare = conn.prepareStatement(sql);
            resultSet = prepare.executeQuery();
            ProductsModel proData;

            while (resultSet.next()) {

                proData = new ProductsModel(
                        resultSet.getInt("product_id"),
                        resultSet.getString("type"),
                        resultSet.getString("brand"),
                        resultSet.getString("product_name"),
                        resultSet.getDouble("price"),
                        resultSet.getString("status"),
                        resultSet.getString("image"),
                        resultSet.getDate("date")
                );

                productList.add(proData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return productList;

    }

    private ObservableList<ProductsModel> addProductList;
//    public void addProductsShowListData(){
//        
//        addProductList = addProductListDataGet();
//        
//        addProduct_tbvCol_productID.setCellValueFactory(new PropertyValueFactory<>("product_id")); // table colomns name of database
//        addProduct_tbvCol_type.setCellValueFactory(new PropertyValueFactory<>("type"));
//        addProduct_tbvCol_brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
//        addProduct_tbvCol_productName.setCellValueFactory(new PropertyValueFactory<>("product_name"));
//        addProduct_tbvCol_price.setCellValueFactory(new PropertyValueFactory<>("price"));
//        addProduct_tbvCol_status.setCellValueFactory(new PropertyValueFactory<>("status"));
//                
//        addProduct_tableView.setItems(addProductList);
//    }

    public void addProductsShowListData() { // show on table

        addProductList = addProductListDataGet();

        addProduct_tbvCol_productID.setCellValueFactory(new PropertyValueFactory<>("productId"));  // productId is the variable name of the ProductsModel class
        addProduct_tbvCol_type.setCellValueFactory(new PropertyValueFactory<>("type")); // this does not immediately "put all the data related to the product ID at once." Instead, what it does is link the productId field from each product in your list to the Product ID column of the TableView
        addProduct_tbvCol_brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        addProduct_tbvCol_productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        addProduct_tbvCol_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        addProduct_tbvCol_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        addProduct_tableView.setItems(addProductList);
    }

    public void ordersAdd() {

        customerId();
        String sql = "INSERT INTO customer (customer_id, type, brand, productName, quantity, price, date) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?)";

        conn = databaseHandler.connectDb();

        try {

            String checkData = "SELECT * FROM product WHERE product_name = '"
                    + orders_productName_combo.getSelectionModel().getSelectedItem() + "'";

            double priceData = 0;

            statement = conn.createStatement();
            resultSet = statement.executeQuery(checkData);

            if (resultSet.next()) {
                priceData = resultSet.getDouble("price"); // if there are more prices more than 1 then what happen?????
//              ---------------
// ------------------
            }

            double totalPdata = (priceData * qty);

            Alert alert;
            if (orders_productType_combo.getSelectionModel().getSelectedItem() == null
                    || orders_brandName_combo.getSelectionModel().getSelectedItem() == null
                    || orders_productName_combo.getSelectionModel().getSelectedItem() == null
                    || totalPdata == 0) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please choose product first");
                alert.showAndWait();
            } else {

                prepare = conn.prepareStatement(sql);
                prepare.setInt(1, customer_Id);
                prepare.setString(2, (String) orders_productType_combo.getSelectionModel().getSelectedItem());
                prepare.setString(3, (String) orders_brandName_combo.getSelectionModel().getSelectedItem());
                prepare.setString(4, (String) orders_productName_combo.getSelectionModel().getSelectedItem());
                prepare.setString(5, String.valueOf(qty));

                prepare.setString(6, String.valueOf(totalPdata));

                Date date = new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                prepare.setString(7, String.valueOf(sqlDate));

                prepare.executeUpdate();

                ordersShowListData();
//            ordersDisplayTotal();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void ordersPay() {

        customerId();
        String sql = "INSERT INTO customer_receipt (customer_id, total, amount, balance, date) "
                + "VALUES(?, ?, ?, ?, ?)"; // WHERE customer_id = '"+ customer_Id +"'  // change this I

        conn = databaseHandler.connectDb();

        try {

            Alert alert;
            if (totalP > 0) { // || orders_amount_txt.getText().isEmpty() || amount == 0

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure?");
                Optional<ButtonType> optional = alert.showAndWait();

                if (optional.get().equals(ButtonType.OK)) {

                    prepare = conn.prepareStatement(sql);

                    prepare.setInt(1, customer_Id); // I change this
                    prepare.setString(2, String.valueOf(totalP));
                    prepare.setString(3, String.valueOf(amount));
                    prepare.setString(4, String.valueOf(balance));

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare.setString(5, String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Payed!");
                    alert.showAndWait();

//                    ordersShowListData();

//                    totalP = 0;
                    balance = 0;
                    amount = 0;

                    orders_balance_lbl.setText("LKR 0.0");
                    orders_total_lbl.setText("LKR 0.0");
                    orders_amount_txt.setText("");

                } else {
                    return;
                }

            } else {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Invalid : 3");
                alert.showAndWait();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void ordersReceipt() throws JRException{
        
        try {

//            customerId();
            
            HashMap hash = new HashMap();
            hash.put("inventoryP", customer_Id);
            
            if(totalP == 0){
                
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Invalid :3"); // kalin order eka pay karala iwara nam den totalP = 0 ema nisa receipt ekak print kranna denne aye order ekak daddi
                alert.showAndWait();
                
            }else{
                
                JasperDesign jDesign = JRXmlLoader.load("H:\\Java new\\yt java projects\\YT_InventoryManagementSystem_JavaFx\\src\\main\\resources\\reports\\posReport.jrxml");
                JasperReport jReport = JasperCompileManager.compileReport(jDesign);
                JasperPrint jPrint = JasperFillManager.fillReport(jReport, hash, conn);
                
                JasperViewer.viewReport(jPrint, false);
                
                
            }
            
        } catch (Exception e) {e.printStackTrace();}
        
    }
    
    public void ordersReset(){
        
        customerId();
        
        String sql = "DELETE FROM customer WHERE customer_id = '"+ customer_Id +"'";
        
        conn = databaseHandler.connectDb();
        
        try {
            if(!orders_tableView.getItems().isEmpty()){
                
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to reset?");
                Optional<ButtonType> option = alert.showAndWait();
                
                if(option.get().equals(ButtonType.OK)){
                    
                    statement = conn.createStatement();
                    statement.executeUpdate(sql);
                    
                    ordersShowListData();

                    totalP = 0;
                    balance = 0;
                    amount = 0;

                    orders_balance_lbl.setText("LKR 0.0");
                    orders_total_lbl.setText("LKR 0.0");
                    orders_amount_txt.setText("");
                }
                
            }
        } catch (Exception e) {e.printStackTrace();}
        
    }
    
    private double amount;
    private double balance;

    public void ordersAmount() {

        Alert alert;

        if (!orders_amount_txt.getText().isEmpty()) {

            amount = Double.parseDouble(orders_amount_txt.getText());

            if (totalP > 0) {
                if (amount >= totalP) {
                    balance = (amount - totalP);
                    orders_balance_lbl.setText("LKR " + String.valueOf(balance));
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Enter Enough Amount");
                    alert.showAndWait();

                    orders_amount_txt.setText("");
                }
            } else {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Invalid :3");
                alert.showAndWait();

            }
        } else {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Amount Field is Empty");
                alert.showAndWait();

            }

    }

    private double totalP;

    public void ordersDisplayTotal() {

        customerId();

        String sql = "SELECT SUM(price) FROM customer WHERE customer_id = '" + customer_Id + "' ";   // mehidee cus id of customer table is 1, and cus id of customer_receipt is 0, customerId() ekedi customer table eke customer id eka select krala gannwa, eka thamay methana thiyenne '"+customer_Id+"' lesa.

        conn = databaseHandler.connectDb();

        try {

            prepare = conn.prepareStatement(sql);
            resultSet = prepare.executeQuery();

            while (resultSet.next()) {
                totalP = resultSet.getDouble("SUM(price)");
            }

            orders_total_lbl.setText("LKR " + String.valueOf(totalP));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String[] OrderslistType = {"Snacks", "Drinks", "Dessert", "Gadgets", "Personal Product", "Others"};

    public void OrdersListTypes() {

//        orders_productType_combo.getSelectionModel().clearSelection();
        List<String> listT = new ArrayList<>();

        for (String data : OrderslistType) {
            listT.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listT);
        orders_productType_combo.setItems(listData);

        ordersListBrands();
    }

    public void ordersListBrands() { // this method call after type selected, but when selected brand name combo we want call again this mehtod, because we want to show product name combo list

        // Clear the product name combo box when a new type is selected
        orders_productName_combo.getSelectionModel().clearSelection();
        orders_productName_combo.getItems().clear();

        // Check if brand selection exists before executing the query
        if (orders_productType_combo.getSelectionModel().getSelectedItem() == null) {
            return; // Exit the method if no brand is selected
        }

        String sql = "SELECT * FROM product WHERE type = '"
                + orders_productType_combo.getSelectionModel().getSelectedItem()
                + "' and status = 'Available' GROUP BY brand";

        conn = databaseHandler.connectDb();

        try {

            prepare = conn.prepareStatement(sql);
            resultSet = prepare.executeQuery();

            ObservableList listData = FXCollections.observableArrayList();

            while (resultSet.next()) {
                listData.add(resultSet.getString("brand"));
            }

            orders_brandName_combo.setItems(listData);

            ordersListProductName();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void ordersListProductName() {

        // Check if brand selection exists before executing the query
        if (orders_brandName_combo.getSelectionModel().getSelectedItem() == null) {
            return; // Exit the method if no brand is selected
        }

        String sql = "SELECT * FROM product WHERE brand = '"
                + orders_brandName_combo.getSelectionModel().getSelectedItem() + "'";

        conn = databaseHandler.connectDb();

        try {

            prepare = conn.prepareStatement(sql);
            resultSet = prepare.executeQuery();

            ObservableList listData = FXCollections.observableArrayList();

            while (resultSet.next()) {
                listData.add(resultSet.getString("product_name"));
            }

            orders_productName_combo.setItems(listData);

//            while(resultSet.next()){
//                customerData = new CustomerData(
//                        resultSet.getInt("customer_id"), 
//                        resultSet.getString("type"), 
//                        resultSet.getString("brand"), 
//                        resultSet.getString("productName"), 
//                        resultSet.getInt("quantity"), 
//                        resultSet.getDouble("price"), 
//                        resultSet.getDate("date"));
//                
//                listData.add(customerData);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private SpinnerValueFactory<Integer> spinner;

    public void ordersSpinner() {

        spinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 0);
        orders_quantity_spinner.setValueFactory(spinner);

        /*
        
        --- ordersSpinner() Method ---
        
        This method is used to set up a Spinner for ordering quantities, allowing the user to select a number between 0 and 20. Here’s a detailed explanation of each part of this method:

        1. Spinner Initialization:

        
        spinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 0);
        
        SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 0) creates an integer-based spinner with a minimum value of 0, a maximum value of 20, and an initial/default value of 0.
        The SpinnerValueFactory helps manage the range and value of the spinner for quantity selection.
        
        
        2. Assigning the Spinner to the orders_quantity_spinner:

        orders_quantity_spinner.setValueFactory(spinner);
        
        Here, orders_quantity_spinner is a spinner UI element (likely created in the FXML file or elsewhere in your JavaFX UI code).
        This line assigns the spinner factory as the value factory for orders_quantity_spinner. This means that the spinner’s value range (0 to 20) and starting value (0) are now applied to orders_quantity_spinner.
        
        
        3. Placement in Initialization or Button Click:

        ordersSpinner() is called in the initialize() method (often used in JavaFX controllers to initialize UI elements when the scene is loaded) or on a button click.
        When called in initialize(), it sets up the spinner with the specified range and default value as soon as the interface is loaded, making it ready for user interaction.
        
         */
    }

    private int qty;

    public void ordersShowSpinnerValue() {
        qty = orders_quantity_spinner.getValue();
    }

    public ObservableList<CustomerData> ordersListData() {
        customerId(); // pay button eke explain ekata meka yodaganna
        ObservableList<CustomerData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM customer WHERE customer_id = '" + customer_Id + "' ";

        conn = databaseHandler.connectDb();

        try {

            CustomerData customerData;
            prepare = conn.prepareStatement(sql);
            resultSet = prepare.executeQuery();

            while (resultSet.next()) {
                customerData = new CustomerData(
                        resultSet.getInt("customer_id"),
                        resultSet.getString("type"),
                        resultSet.getString("brand"),
                        resultSet.getString("productName"),
                        resultSet.getInt("quantity"),
                        resultSet.getDouble("price"),
                        resultSet.getDate("date"));

                listData.add(customerData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listData;
    }

    private ObservableList<CustomerData> orderList;

    public void ordersShowListData() {

        orderList = ordersListData();

        orders_col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        orders_col_brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        orders_col_productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        orders_col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        orders_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));

        orders_tableView.setItems(orderList);

        ordersDisplayTotal();
    }

    private int customer_Id;

    public void customerId() {

        String customerId = "SELECT * FROM customer";
        conn = databaseHandler.connectDb();

        try {

            prepare = conn.prepareStatement(customerId);
            resultSet = prepare.executeQuery();

            int checkId = 0;

            while (resultSet.next()) {

                // GET LAST CUSTOMER ID
                customer_Id = resultSet.getInt("customer_id");

            }

            String checkData = "SELECT * FROM customer_receipt";

            statement = conn.createStatement();
            resultSet = statement.executeQuery(checkData);

            while (resultSet.next()) {
                checkId = resultSet.getInt("customer_id");
            }

            if (customer_Id == 0) {
                customer_Id += 1;
            } else if (checkId == customer_Id) {
                customer_Id += 1;
            }

            /*
            
            above block checks if a unique customer_Id should be assigned:

            if(customer_Id == 0) checks if there were no customer_id records in the customer table and increments customer_Id by 1.
            else if(checkId == customer_Id) checks if the last customer_id from customer_receipt matches customer_Id. If they are the same, it increments customer_Id by 1 to avoid duplication.
            
             */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchForm(ActionEvent event) {

        if (event.getSource() == home_btn) {

            home_form.setVisible(true);
            addProducts_form.setVisible(false);
            orders_form.setVisible(false);

            home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #269e70, #969635);");
            addProducts_btn.setStyle("-fx-background-color:transparent;");
            orders_btn.setStyle("-fx-background-color:transparent;");
            
            homeDisplayTotalOrders();
            homeTotalIncome();
            homeAvailableProducts();
            homeIncomeChart();
            homeOrdersChart();

        } else if (event.getSource() == addProducts_btn) {

            home_form.setVisible(false);
            addProducts_form.setVisible(true);
            orders_form.setVisible(false);

            home_btn.setStyle("-fx-background-color:transparent;");
            addProducts_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #269e70, #969635);");
            orders_btn.setStyle("-fx-background-color:transparent;");

            addProductsShowListData();
            addProductsListTypes();
            addProductsListStatus();
            addProductsSearch();

        } else if (event.getSource() == orders_btn) {

            home_form.setVisible(false);
            addProducts_form.setVisible(false);
            orders_form.setVisible(true);

            home_btn.setStyle("-fx-background-color:transparent;");
            addProducts_btn.setStyle("-fx-background-color:transparent;");
            orders_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #269e70, #969635);");

            orders_productType_combo.getSelectionModel().clearSelection();
            orders_brandName_combo.getSelectionModel().clearSelection();
            orders_productName_combo.getSelectionModel().clearSelection();

            ordersShowListData();
            OrdersListTypes();
            ordersListBrands();
            ordersListProductName();
            ordersSpinner();

        }
    }

    public void defaultNav(){
        home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #269e70, #969635);"); // software eka run karala home page eka penne, ema nisa home_btn eka click unama watena pata watila tyena one muladima
    }
    
    private double x = 0;
    private double y = 0;

    public void logout() {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure want to logout?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {

                main_form.getScene().getWindow().hide();

                // LINK YOUR LOGIN FORM
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(0.8);
                });

                root.setOnMouseReleased((MouseEvent event) -> {
                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();
            } else {
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayUsername(){
        username_lbl.setText(GetData.username);
    }
    
    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    public void close() {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        displayUsername();
        defaultNav();
        
        homeDisplayTotalOrders();
        homeTotalIncome();
        homeAvailableProducts();
        homeIncomeChart();
        homeOrdersChart();
        
        // TO SHOW THE DATA ON TABLEVIEW
        addProductsShowListData();
        addProductsListTypes();
        addProductsListStatus();

        ordersShowListData();
        OrdersListTypes();
        ordersListBrands();
        ordersListProductName();
        ordersSpinner();
    }

}
