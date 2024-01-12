package GUI;


//import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import entities.service;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
//import static com.sun.deploy.security.BlockedDialog.show;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.io.File;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class Controller implements Initializable {

    @FXML
    private TextField tfid;

    @FXML
    private TextField tfNom;

    @FXML
    private TextField tfType;

    @FXML
    private TableView<service> tvser;

    @FXML
    private TableColumn<service, Integer> colID;

    @FXML
    private TableColumn<service, String> colNom;

    @FXML
    private TableColumn<service, String> colType;

  @FXML
    private TableColumn<service, String> colC;

    @FXML
    private Button btninsert;

    @FXML
    private Button btnupdate;

    @FXML
    private Button btndelete;
    @FXML
    private ComboBox<String> id_categorie;
    int index = -1;
    @FXML
    private TextField tfprix;
    @FXML
    private TableColumn<service, Integer> colPrix;
    @FXML
    private Button PDF1;
  

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
    public ObservableList<service> getserList(){
        ObservableList<service> serviceList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM service";
        Statement st;
        ResultSet rs;
        try {
            st= conn.createStatement();
            rs =st.executeQuery(query);
            service service;
            while (rs.next()){
                service = new service(rs.getInt("id"), rs.getString("nom"),rs.getString("type"),rs.getString("nomcat"),rs.getInt("prix")  );
                serviceList.add(service);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return serviceList;
    }
    public void showser(){
        ObservableList<service> list =getserList();
        colID.setCellValueFactory(new PropertyValueFactory<service,Integer>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<service,String>("nom"));
         
       
        colType.setCellValueFactory(new PropertyValueFactory<service,String>("type"));
        colC.setCellValueFactory(new PropertyValueFactory<service,String>("nomcat"));
        colPrix.setCellValueFactory(new PropertyValueFactory<service,Integer>("prix"));
        

        tvser.setItems(list);
    }
    
    
         void get (MouseEvent event){
    index = tvser.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    tfid.setText(colID.getCellData(index).toString());
    tfNom.setText(colNom.getCellData(index).toString());
    tfType.setText(colType.getCellData(index).toString());
    
    
    }
     

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showser();
        idcategorie();
    }




    private void insertRecord()
    
    
    {
        String id = tfid.getText();
        String nom = tfNom.getText();
        String type = tfType.getText();
        String prix =tfprix.getText();
        
        if (id.isEmpty() || nom.isEmpty() || type.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();
            return;
        }
        
        
        
       if (!(tfNom.getText().isEmpty())) 
                {
                    String masque = "^[a-zA-Z]+$";
                    Pattern pattern = Pattern.compile(masque);
                    Matcher controler = pattern.matcher(tfNom.getText());
                    if (!(controler.matches())) {
                       Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("nom de service invalide");
            alert.showAndWait();
            return;
                    }
                    

                    if ((tfprix.getText().length() < 1)) {
                          Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("prix invalide");
            alert.showAndWait();
                        return;
                    } 
                else {
    
             
        
        
        
        
            
               String query ="INSERT INTO service VALUES("+ tfid.getText()+",'"+ tfNom.getText()  +"','"+tfType.getText() +"','"+id_categorie.getSelectionModel().getSelectedItem() +"','"+tfprix.getText()+"')";
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
                        tfprix.setText(null);

}




    private void update(){
        String query = "UPDATE service SET nom='"+tfNom.getText()+"',type='"+tfType.getText()+"' ,nomcat='"+id_categorie.getSelectionModel().getSelectedItem()+"' ,prix='"+tfprix.getText()
               +"' WHERE id = "+tfid.getText()+"";
        executeQuery(query);
        showser();
        cleardata();
        notificationup();
    }
    private void delete(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation dialog");
            alert.setHeaderText(null);
            alert.setContentText("are you sure to delete");
           Optional<ButtonType> action = alert.showAndWait();
           if (action.get() == ButtonType.OK){
        String query ="DELETE FROM service WHERE id ="+tfid.getText()+"";
        executeQuery(query);
        cleardata();
        showser();
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
     public void Categoriefxml(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
         Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("javafxapplication2/CategorieInterfacesample.fxml")));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
    }

    private void idcategorie() {
         Connection conn = getConnection();
        Statement st;
        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            //st =conn.createStatement();
            //st.executeUpdate(query);
            
            ResultSet rs = conn.createStatement().executeQuery("select nom from categorie");
        while(rs.next()){
            
          list.add(rs.getString("nom")); 
          //id_categorie.add(rs.getInt("id"));
        }
        
        }catch (Exception ex){
            ex.printStackTrace();
        }
        
       id_categorie.setItems(null);
       id_categorie.setItems(list);
        
      
    }
    	private volatile TrayNotification tray;

    public void initializeTray() {
		Platform.runLater(() -> tray = new TrayNotification());
	}
    private void notification(){
      String title = "Congratulations sir";
		String message = "You've successfully created new Service";
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
		String message = "You've successfully delate a Service";
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
		String message = "You've successfully created uppdate a Service";
		TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;
                          tray.setAnimationType(type);
		tray.setTitle(title);
		tray.setMessage(message);
		tray.setNotificationType(NotificationType.INFORMATION);
		tray.showAndDismiss(Duration.millis(3000));
		}
    @FXML
        void PDF1(ActionEvent event) throws DocumentException {
            Document doc = new Document() ;
            try {
                PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\MSI\\Desktop\\test.pdf"));
                doc.open();

                Paragraph p = new Paragraph(" les donné de ce Service  " );
                
                p.setAlignment(Element.ALIGN_CENTER);
                doc.add(p);


                Paragraph contenu = new Paragraph(preparerMsg());
                doc.add(contenu) ;
                doc.close();


                Desktop.getDesktop().open(new File("C:\\Users\\MSI\\Desktop\\test.pdf"));
            }catch (FileNotFoundException ex) {
                Logger.getLogger(service.class.getName()).log(Level.SEVERE, null, ex);
            }catch (BadElementException ex) {
                Logger.getLogger(service.class.getName()).log(Level.SEVERE, null, ex);
            }catch (IOException ex) {
                Logger.getLogger(service.class.getName()).log(Level.SEVERE, null, ex);
            }

        }


        private String preparerMsg() {
            service f = tvser.getSelectionModel().getSelectedItem();
            String ch = " \n \n"
                    + "1/Nom service : " + f.getNom()+ "\n \n" 
                    + "2/type de service: " + f.getType()+  "\n \n"
                       + "3/ce service et sous la categorie : " + f.getNomcat()+ "\n \n" 
                        +"4/le prix du service : " + f.getPrix()+ "\n \n";
                    
                  


            return ch;
        }
        private Stage stage;
 private Scene scene;
 private Parent root;


    private void toservice(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("sample.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    }

    private void tointerfaceadmin(ActionEvent event) throws IOException {
         root = FXMLLoader.load(getClass().getResource("interfaceadmin.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
    }

    private void tocategorie(ActionEvent event) throws IOException {
         root = FXMLLoader.load(getClass().getResource("CategorieInterfacesample.fxml"));
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
    

    
       

    