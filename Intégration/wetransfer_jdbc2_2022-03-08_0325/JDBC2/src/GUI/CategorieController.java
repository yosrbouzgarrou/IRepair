package GUI;


import entities.Categorie;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import static com.sun.media.jfxmediaimpl.MediaUtils.warning;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.security.acl.Owner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import static java.util.Collections.list;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import static javafx.scene.control.Alert.AlertType.ERROR;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class CategorieController implements Initializable {

   

    @FXML
    private TextField tfid;

    @FXML
    private TextField tfNom;

    @FXML
    private TextField tfType;
    int index = -1;
    

    @FXML
    private TableView<Categorie> tvcat;

    @FXML
    private TableColumn<Categorie, Integer> colID;

    @FXML
    private TableColumn<Categorie, String> colNom;

    @FXML
    private TableColumn<Categorie, String> colType;
   // ObservableList<Categorie>dataList;



    @FXML
    private Button btninsert;

    @FXML
    private Button btnupdate;

    @FXML
    private Button btndelete;
    @FXML
    private ImageView ivcat;
    
    @FXML
    private Button btimage;
    
    File file ;
    private static Stage pStage;
    @FXML
    private TextField filerField;
    @FXML
    private Button PFD1;
    
    
  

    @FXML
    void handleButtonAction( ActionEvent event) {
        if(event.getSource()== btninsert){
            insertRecord();
        } else if (event.getSource()==btnupdate){
            update();
        }
        else if (event.getSource()==btndelete){
            delete();
        }

    }
    public Connection getConnection(){
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/irepair","root","");
            return conn;
        }catch (Exception ex ){
            System.out.println("Error :"+ex.getMessage());
            return null;
        }
    }
     void getSelected (MouseEvent event){
    index = tvcat.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    tfid.setText(colID.getCellData(index).toString());
    tfNom.setText(colNom.getCellData(index).toString());
    tfType.setText(colType.getCellData(index).toString());
    
    
    }
    public ObservableList<Categorie> getserList(){
        ObservableList<Categorie> categorieList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM categorie";
        Statement st;
        ResultSet rs;
        try {
            st= conn.createStatement();
            rs =st.executeQuery(query);
            Categorie categorie;
            while (rs.next()){
                categorie =new Categorie(rs.getInt("id"), rs.getString("nom"),rs.getString("type") );
                categorieList.add(categorie);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return categorieList;
    }
    public void showser(){
        ObservableList<Categorie> list =getserList();
        colID.setCellValueFactory(new PropertyValueFactory<Categorie,Integer>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<Categorie,String>("nom"));
        colType.setCellValueFactory(new PropertyValueFactory<Categorie,String>("type"));
tvcat.setItems(list);
FilteredList<Categorie> filteredData = new FilteredList<>(list, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filerField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Categorie -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Categorie.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (Categorie.getType().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				//else if (String.valueOf(employee.getSalary()).indexOf(lowerCaseFilter)!=-1)
				     //return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Categorie> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tvcat.comparatorProperty());
		
		
               
        
        tvcat.setItems(sortedData);
        
        
    }
   
     
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showser();
        //recherche();
        
    }

   private void  setPrimaryStage(Stage pStage ) {
       CategorieController.pStage = pStage;
    }
    public static Stage getPrimaryStage() {
        return pStage;
        
    }


    private void insertRecord(){
        String id = tfid.getText();
        String nom = tfNom.getText();
        String type = tfType.getText();
        //String email = emailFld.getText();
        if (id.isEmpty() || nom.isEmpty() || type.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();
        }
        if (!(tfNom.getText().isEmpty())) 
                {
                    String masque = "^[a-zA-Z]+$";
                    Pattern pattern = Pattern.compile(masque);
                    Matcher controler = pattern.matcher(tfNom.getText());
                    if (!(controler.matches())) {
                       Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("nom de categorie invalide");
            alert.showAndWait();
            return;
                    }

        
                    else{
        
        String query ="INSERT INTO categorie VALUES("+ tfid.getText()+",'"+ tfNom.getText() +"','"+tfType.getText()+"')";
        executeQuery(query);
        showser();
        cleardata();
        notification();
    }
    
        }
    }
    
    private void cleardata(){
    tfid.setText(null);
            tfNom.setText(null);
                    tfType.setText(null);

}
    public static void showAlert (Alert.AlertType alertType ,  String message , String title) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.initModality(Modality.APPLICATION_MODAL);
        //alert.initOwner(owner);
        alert.showAndWait();
    }




    private void update(){
        String query = "UPDATE categorie SET nom='"+tfNom.getText()+"',type='"+tfType.getText()
               +"' WHERE id = "+tfid.getText()+"";
        executeQuery(query);
         cleardata();
        showser();
        notificationup();
    }
    private void delete(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation dialog");
            alert.setHeaderText(null);
            alert.setContentText("are you sure to delete");
           Optional<ButtonType> action = alert.showAndWait();
           if (action.get() == ButtonType.OK){
        String query ="DELETE FROM categorie WHERE id ="+tfid.getText()+"";
        executeQuery(query);
        showser();
         cleardata();
         notificationsup();
           }
    }
    

    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try {
            st =conn.createStatement();
            st.executeUpdate(query);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleBrowseImage(ActionEvent event) {
        try {
            FileChooser fc  = new FileChooser();
            FileChooser.ExtensionFilter ext1 = new FileChooser.ExtensionFilter("JPG files(*.jpg)","*.JPG");
            FileChooser.ExtensionFilter ext2 = new FileChooser.ExtensionFilter("PNG files(*.png)","*.PNG");
            fc.getExtensionFilters().addAll(ext1 , ext2);
            file = fc.showOpenDialog(CategorieController.getPrimaryStage());
            BufferedImage bf ;
            bf = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bf, null);
            ivcat.setImage(image);
            
            
            
        } catch (Exception ex) { System.out.println(""+ ex.getMessage());
        }
    }
    @FXML
        void PDF1(ActionEvent event) throws DocumentException, FileNotFoundException {
            Document doc = new Document() ;
            try {
                PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\MSI\\Desktop\\test.pdf"));
                doc.open();

                Paragraph p = new Paragraph("categorie ");
                p.setAlignment(Element.ALIGN_CENTER);
                doc.add(p);


                Paragraph contenu = new Paragraph(preparerMsg());
                doc.add(contenu) ;
                doc.close();


                Desktop.getDesktop().open(new File("C:\\Users\\MSI\\Desktop\\test.pdf"));
            }catch (FileNotFoundException ex) {
                Logger.getLogger(Categorie.class.getName()).log(Level.SEVERE, null, ex);
            }catch (BadElementException ex) {
                Logger.getLogger(Categorie.class.getName()).log(Level.SEVERE, null, ex);
            }catch (IOException ex) {
                Logger.getLogger(Categorie.class.getName()).log(Level.SEVERE, null, ex);
            }

        }


        private String preparerMsg() {
            Categorie f = tvcat.getSelectionModel().getSelectedItem();
            String ch = " \n \n"
                    + "Id type: " + f.getType()+  "\n \n"

                    + "Nom service : " + f.getNom()+ "\n \n" ;
                  


            return ch;
        }
        private void notification(){
      String title = "Congratulations sir";
		String message = "You've successfully created new categorie ";
		TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;
                          tray.setAnimationType(type);
		tray.setTitle(title);
		tray.setMessage(message);
		tray.setNotificationType(NotificationType.SUCCESS);
		tray.showAndDismiss(Duration.millis(3000));
		}
    private void notificationsup(){
      String title = "Congratulations sir";
		String message = "You've successfully delate a categorie";
		TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;
                          tray.setAnimationType(type);
		tray.setTitle(title);
		tray.setMessage(message);
		tray.setNotificationType(NotificationType.ERROR);
		tray.showAndDismiss(Duration.millis(3000));
		}
    private void notificationup(){
      String title = "Congratulations sir";
		String message = "You've successfully uppdate a Categorie" ;
		TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;
                          tray.setAnimationType(type);
		tray.setTitle(title);
		tray.setMessage(message);
		tray.setNotificationType(NotificationType.INFORMATION);
		tray.showAndDismiss(Duration.millis(3000));
		
        
    
}
        private Stage stage;
 private Scene scene;
 private Parent root;

    @FXML
    private void toservice(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("sample.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    }

    @FXML
    private void tocategorie(ActionEvent event) {
    }

    @FXML
    private void toadmin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("interfaceadmin.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    }
     @FXML
    private void tocat(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("cat.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    }
     @FXML
    private void tordv(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("interfaceadmin.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    }
     @FXML
    private void tostock(ActionEvent event) throws IOException {
               System.out.println("You clicked me!");
           Parent modifierCours = FXMLLoader.load(getClass().getResource("interfaceadmin2.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show();
    }
}
