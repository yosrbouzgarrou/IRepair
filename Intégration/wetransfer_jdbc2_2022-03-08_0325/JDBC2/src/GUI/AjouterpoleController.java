/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Pole;
import entities.Stock;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import services.servicePole;
import services.serviceStock;
import javafx.collections.transformation.FilteredList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author nader
 */
public class AjouterpoleController implements Initializable {

    
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfLieu;
    @FXML
    private Button tfajouter;
    @FXML
    private Button mfmodifier;
    @FXML
    private Button mfsupprimer;
    @FXML
    private Button btnGoStock;
    @FXML
    private TableColumn<Pole, String> aanom;
    @FXML
    private TableColumn<Pole, String> aalieu;
    @FXML
    private TableView<Pole> tableview;

    ObservableList list;
    @FXML
    private TextField recherche;
    @FXML
    private TextField email;
    @FXML
    private TableColumn<Pole, Integer> id;

    @FXML
    private TextField aaid;
    int index = -1;
   
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
     servicePole p = new servicePole();
        List pole =p.afficher();
        list = FXCollections.observableArrayList(pole);
        tableview.setItems(list);
          id.setCellValueFactory(new PropertyValueFactory<>("id_pole"));
        aanom.setCellValueFactory(new PropertyValueFactory<>("nom_pole"));
        aalieu.setCellValueFactory(new PropertyValueFactory<>("lieu_pole"));
        
       // date_devis.setCellValueFactory(new PropertyValueFactory<>("date_devis"));
       FilteredList<Pole> filteredData = new FilteredList<>(list, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		recherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Pole -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (String.valueOf(Pole.getId_pole()).indexOf(lowerCaseFilter)!=-1) {
					return true; // Filter matches first name.
				} else if (String.valueOf(Pole.getLieu_pole()).indexOf(lowerCaseFilter)!=-1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(Pole.getNom_pole()).indexOf(lowerCaseFilter)!=-1){
				     return true;
                                } 
                                
                                
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Pole> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tableview.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tableview.setItems(sortedData);
    }    

    @FXML
    private void savepole(ActionEvent event) {
          if(  "".equals(tfNom.getText())&& "".equals(tfLieu.getText())){
            
            JOptionPane.showMessageDialog(null, "le champ est vide ");
        }  
     
        String nom = tfNom.getText();  
        String lieu =tfLieu.getText();
        Pole p = new Pole(nom,lieu);
        servicePole pc = new servicePole();
        pc.ajout(p);
        Pole m=pc.afficher().get(pc.afficher().size()-1);
        list.add(m);
        
        
         String to = email.getText(); 
         String myaccountEmail="mezraninader@gmail.com" ; 
         String password ="gfaziheb0m" ; 
          Properties properties = new Properties();    
         properties.put("mail.smtp.auth", "true");
         properties.put("mail.smtp.starttls.enable", "true");
         properties.put("mail.smtp.host", "smtp.gmail.com");
         properties.put("mail.smtp.port", "587");
       
         Session session = Session.getInstance(properties,new javax.mail.Authenticator() {
             
             
    @Override
    protected javax.mail.PasswordAuthentication getPasswordAuthentication(){
        return new javax.mail.PasswordAuthentication(myaccountEmail, password);
    }
});
         
           try {
            javax.mail.Message message = new MimeMessage(session) ;
                       
            message.setFrom(new InternetAddress(myaccountEmail));
     
           
            message.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to) );
            
            message.setSubject("concernant votre pole");
            
            
            
            String htmlCode = "<h2> Votre pole est ajouté avec succ </h2>" + m.getNom_pole(); 
            message.setContent(htmlCode,"text/html");
            
            Transport.send(message);
            
               System.out.println("succes");}
          catch (MessagingException ex) {
            Logger.getLogger(ex.getMessage());
        
        }

        
       
    }

    @FXML
    private void modifierpole(ActionEvent event) {
        if(   "".equals(tfNom.getText())&& "".equals(tfLieu.getText())){
             JOptionPane.showMessageDialog(null, "le champ est vide ");
        }
         int id =Integer.parseInt(aaid.getText());
         String nom = tfNom.getText();  
        String lieu =tfLieu.getText();
        Pole p = new Pole(id,nom,lieu);
        servicePole pc = new servicePole();
        pc.modifier(p);
         list.remove(p);
         list.add(p);
          tfLieu.clear();
        aaid.clear();
       tfNom.clear();
       
    }

    @FXML
    private void supprimerpole(ActionEvent event) {
        if( "".equals(tfNom.getText())&& "".equals(tfLieu.getText())){
             JOptionPane.showMessageDialog(null, "le champ est vide ");
        }
          int id =Integer.parseInt(aaid.getText());
         String nom = tfNom.getText();  
        String lieu =tfLieu.getText();
        Pole p = new Pole(id,nom,lieu);
        servicePole pc = new servicePole();
        pc.supprimer(id); 
         list.remove(p);
        aaid.clear();
        tfLieu.clear();
        tfNom.clear();
       
    }
     FXMLLoader loader = new FXMLLoader(getClass().getResource("interfaceadmin.fxml"));
   
     
     @FXML
    public void goStock(ActionEvent event) {
         

        try {
                Parent root = loader.load();
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
              
                tfNom.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println("Error" + ex.getMessage());
           // Logger.getLogger(AjouterpoleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void selectRow(MouseEvent event) {
        index = tableview.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
   aaid.setText(id.getCellData(index).toString());
   tfLieu.setText(aalieu.getCellData(index).toString());
   tfNom.setText(aanom.getCellData(index).toString());
    
    }
    @FXML
   private void interfaceadmin2(ActionEvent event) throws IOException {
               System.out.println("You clicked me!");
           Parent modifierCours = FXMLLoader.load(getClass().getResource("interfaceadmin2.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show();
    }
    @FXML
    private void tordv(ActionEvent event) throws IOException {
               System.out.println("You clicked me!");
           Parent modifierCours = FXMLLoader.load(getClass().getResource("interfaceadmin.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show();
    }
      @FXML
    private void tocat(ActionEvent event) throws IOException {
               System.out.println("You clicked me!");
           Parent modifierCours = FXMLLoader.load(getClass().getResource("cat.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show();
    }
    
   
    
  

    
}

