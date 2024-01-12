/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import entities.Pole;
import entities.Stock;
import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
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
public class StockController implements Initializable {

    @FXML
    private TextField aaid;
    @FXML
    private TextField aanom;
    @FXML
    private TextField aaquantite;
    @FXML
    private TextField aaprix;
    @FXML
    private TextField tfid;
    @FXML
    private Button aaajouter;
    @FXML
    private Button aamodifier;
    @FXML
    private Button aasupprimer;  
    @FXML
    private Button BTnGoPole;
     @FXML
    private TableColumn<Stock, String> nom;

    @FXML
    private TableColumn<Stock, Float> prix;

    @FXML
    private TableColumn<Stock, Integer> qtt;

    @FXML
    private TableView<Stock> tableview;
     ObservableList list;
    @FXML
    private TextField barrederecherche;
    @FXML
    private Button stat;
    @FXML
    private TextField email;


        
    @FXML
    private TableColumn<Stock,Integer> id;
    @FXML
    private TableColumn<Stock,Integer> idpole;
    int index = -1;

    /**
     * Initializes the controller class.
     */
    @Override
     public void initialize(URL url, ResourceBundle rb) {

       serviceStock sd = new serviceStock();
        List stock = sd.afficher();
        list = FXCollections.observableArrayList(stock);
        tableview.setItems(list);
       
        id.setCellValueFactory(new PropertyValueFactory<>("id_article"));
        idpole.setCellValueFactory(new PropertyValueFactory<>("id_pole"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom_article"));
        qtt.setCellValueFactory(new PropertyValueFactory<>("quantite_article"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix_article"));
        
        // date_devis.setCellValueFactory(new PropertyValueFactory<>("date_devis"));
        FilteredList<Stock> filteredData = new FilteredList<>(list, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
	barrederecherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Stock -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (String.valueOf(Stock.getId_article()).indexOf(lowerCaseFilter)!=-1) {
					return true; // Filter matches first name.
				} else if (String.valueOf(Stock.getNom_article()).indexOf(lowerCaseFilter)!=-1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(Stock.getPrix_article()).indexOf(lowerCaseFilter)!=-1){
				     return true;
                                } 
                                else if (String.valueOf(Stock.getQuantite_article()).indexOf(lowerCaseFilter)!=-1){
				     return true;
                                } 
                                else if (String.valueOf(Stock.getId_pole()).indexOf(lowerCaseFilter)!=-1){
				     return true;
                                } 
                               
                                
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Stock> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tableview.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tableview.setItems(sortedData);
    }   
     
     
     

     
  
    @FXML
    private void ajouterstock(ActionEvent event) throws MessagingException {
      
        
         if( "".equals(aaquantite.getText()) && "".equals(aaprix.getText())&& "".equals(tfid.getText())){
            
            JOptionPane.showMessageDialog(null, "le champ est vide ");
        }  
         
        String nom = aanom.getText();  
        int quantite = Integer.parseInt(aaquantite.getText());
        float prix =Float.parseFloat(aaprix.getText());
        int idport = Integer.parseInt(tfid.getText());
        Stock s = new Stock (nom,quantite,prix,idport);
        serviceStock pc = new serviceStock();
        pc.ajout(s);
        Stock m=pc.afficher().get(pc.afficher().size()-1);
        
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
            
            message.setSubject("concernant votre stock");
            
            
            
            String htmlCode = "<h2> Votre stock est ajouté avec succ </h2>"+ m.getId_article(); 
            message.setContent(htmlCode,"text/html");
            
            Transport.send(message);
            
               System.out.println("succes");}
          catch (MessagingException ex) {
            Logger.getLogger(ex.getMessage());
        
        }

        
    }
         
    
    @FXML
    private void modifierstock(ActionEvent event) {
          if( "".equals(aaquantite.getText()) && "".equals(aaprix.getText())&& "".equals(tfid.getText())){
            
            JOptionPane.showMessageDialog(null, "le champ est vide ");
        }  
    
        String nom = aanom.getText();
        int id_s =Integer.parseInt(aaid.getText());
        int qte =Integer.parseInt(aaquantite.getText());       
        float prix =Float.parseFloat(aaprix.getText());      
        int id_pole =Integer.parseInt(tfid.getText());
        Stock s = new Stock (id_s,nom,qte,prix,id_pole);
        serviceStock pc = new serviceStock();
        pc.modifier(s);
        
        list.remove(s);
        list.add(s);
        aanom.clear();
        aaid.clear();
        aaquantite.clear();
        aaprix.clear();
        tfid.clear();
        
    }

    @FXML
    private void supprimerstock(ActionEvent event) {
         String nom = aanom.getText();
         int id_s =Integer.parseInt(aaid.getText());
        int qte =Integer.parseInt(aaquantite.getText());       
        float prix =Float.parseFloat(aaprix.getText());      
        int id_pole =Integer.parseInt(tfid.getText());


        
        serviceStock pc = new serviceStock();
        Stock p = new Stock(id_s,nom, id_pole, prix, qte);
         
        pc.supprimer(id_s); 
        list.remove(p);
        aanom.clear();
        aaid.clear();
        aaquantite.clear();
        aaprix.clear();
        tfid.clear();
            
                    
        
        
    }
    
      FXMLLoader loader = new FXMLLoader(getClass().getResource("interfaceadmin.fxml"));
    @FXML
       void GoPole(ActionEvent event) {

        try {
                Parent root = loader.load();
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                aanom.getScene().setRoot(root);
               
                
        } catch (IOException ex) {
            System.out.println("Error" + ex.getMessage());
           // Logger.getLogger(AjouterpoleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void afficherstat(ActionEvent event) throws IOException {
               System.out.println("You clicked me!");
           Parent modifierCours = FXMLLoader.load(getClass().getResource("stat.fxml"));
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
    
    
    

    @FXML
    private void selectRow(MouseEvent event) {
        index = tableview.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    aaid.setText(id.getCellData(index).toString());
    aanom.setText(nom.getCellData(index));
    aaquantite.setText(qtt.getCellData(index).toString());
    aaprix.setText(prix.getCellData(index).toString());
    tfid.setText(idpole.getCellData(index).toString());
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
    
    
}
