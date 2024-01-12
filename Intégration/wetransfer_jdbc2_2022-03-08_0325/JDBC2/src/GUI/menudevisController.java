package GUI;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import entities.Devis;
import entities.Devis;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import services.ServiceDevis;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.collections.transformation.FilteredList;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.collections.transformation.SortedList;
import javafx.stage.Stage;
import javafx.scene.media.*;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.util.Properties;
import javafx.scene.control.ChoiceBox;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;
public class menudevisController implements Initializable {

 private Stage stage;
 private Scene scene;
 private Parent root;
     Session newSession = null;
	MimeMessage mimeMessage = null;
        String emailBody ="nnn";
 
    @FXML
    private AnchorPane pane;
    @FXML
    private TableView<Devis> tableview;
    @FXML
    private TableColumn<Devis, Integer> id_devis;
    @FXML
    private TableColumn<Devis, Double> prix;
    @FXML
    private TableColumn<Devis, Integer> id_Client;
    @FXML
    private TableColumn<Devis, String> date_devis;
    
    @FXML
    private Button updatebut;
     @FXML
    private ChoiceBox<Integer> choiceBox;
    @FXML
    private Button addbut;
    
    @FXML
    private TextField filterField;
  
  
    @FXML
    private TextField insertprix;
       @FXML
    private Label alerta;
    @FXML
    private TextField insertid_client;
    @FXML
    private DatePicker insertdate_devis;
     ServiceDevis sd = new ServiceDevis();
    int index = -1;
    int modifint = -1;
  
     private final ObservableList<Devis> dataList = FXCollections.observableArrayList();
    
  
     @Override
    public void initialize(URL url, ResourceBundle rb) {

       ServiceDevis sd = new ServiceDevis();
        List devis = sd.afficher();
        ObservableList list = FXCollections.observableArrayList(devis);
        tableview.setItems(list);
        id_devis.setCellValueFactory(new PropertyValueFactory<>("id_devis"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        id_Client.setCellValueFactory(new PropertyValueFactory<>("id_Client"));
        date_devis.setCellValueFactory(new PropertyValueFactory<>("date_devis"));
        
        
         ServiceDevis sp = new ServiceDevis();
         List deviss = sp.afficherid();
           ObservableList lista = FXCollections.observableArrayList(deviss);
        // choiceBox.getItems().add(deviss);
         choiceBox.setItems(lista);

         FilteredList<Devis> filteredData = new FilteredList<>(list, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Devis -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (String.valueOf(Devis.getId_devis()).indexOf(lowerCaseFilter)!=-1) {
					return true; // Filter matches first name.
				} else if (String.valueOf(Devis.getPrix()).indexOf(lowerCaseFilter)!=-1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(Devis.getId_Client()).indexOf(lowerCaseFilter)!=-1){
				     return true;
                                } else if (Devis.getDate_devis().toLowerCase().indexOf(lowerCaseFilter) != -1)
                                    return true;
                                
                                
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Devis> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tableview.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tableview.setItems(sortedData);
		
    }  

    @FXML
    void deletedevis(ActionEvent event)throws AddressException, MessagingException, IOException {
        
  // Saving code here
                 int dialogButton = JOptionPane.YES_NO_OPTION;
                 int dialogResult = JOptionPane.showConfirmDialog(null, "Voulez vous supprimer ?", "Alert !", dialogButton);
                if(dialogResult == 0) {
 


        ServiceDevis sd = new ServiceDevis();
        Devis selectedID = tableview.getSelectionModel().getSelectedItem();
        String s=String.valueOf(selectedID);
        alerta.setText("Supprimé");
       sd.supprimer(selectedID);
       
        emailBody = "Monsieur nous vous informons que le devis numéro " + selectedID.getId_devis() + " créé le " + selectedID.getDate_devis() +" a été supprimé.";
        
        setupServerProperties();
	draftEmail();
	sendEmail();
           String musicFile = "C:\\Users\\PLANETAMIR\\Desktop\\Intégration\\wetransfer_jdbc2_2022-03-08_0325\\JDBC2\\src\\assets\\nosound.mp3";     // For example

          Media sound = new Media(new File(musicFile).toURI().toString());
          MediaPlayer mediaPlayer = new MediaPlayer(sound);
          mediaPlayer.play();
           List devis = sd.afficher();
        ObservableList list = FXCollections.observableArrayList(devis);
        tableview.setItems(list);
        id_devis.setCellValueFactory(new PropertyValueFactory<>("id_devis"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        id_Client.setCellValueFactory(new PropertyValueFactory<>("id_Client"));
        date_devis.setCellValueFactory(new PropertyValueFactory<>("date_devis"));
        insertprix.clear();
        choiceBox.setValue(null);
        insertdate_devis.setValue(null);
          } else {

      //  tableview.getItems().remove(selectedID);
       //refresh
          
        List devis = sd.afficher();
        ObservableList list = FXCollections.observableArrayList(devis);
        tableview.setItems(list);
        id_devis.setCellValueFactory(new PropertyValueFactory<>("id_devis"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        id_Client.setCellValueFactory(new PropertyValueFactory<>("id_Client"));
        date_devis.setCellValueFactory(new PropertyValueFactory<>("date_devis"));
                 insertprix.clear();
        choiceBox.setValue(null);
        insertdate_devis.setValue(null);}
    }
    @FXML
    private void Insertdevis(ActionEvent event) {
        
        //ajout
         if( ( insertprix.getText().isEmpty() == true ) || (String.valueOf(choiceBox.getValue()).isEmpty() == true) || (String.valueOf(insertdate_devis.getValue()).isEmpty() == true)){
            alerta.setText("REMPLISSEZ TOUS LES CHAMPS SVP !!!! ");
          
        }else 
        {
       
        Devis d = new Devis();
        d.setPrix(Double.parseDouble(insertprix.getText()));
     //   d.setId_Client(Integer.parseInt(insertid_client.getText()));
         d.setId_Client(choiceBox.getValue());
        d.setDate_devis(String.valueOf(insertdate_devis.getValue()));
          String musicFile = "C:\\Users\\PLANETAMIR\\Desktop\\Intégration\\wetransfer_jdbc2_2022-03-08_0325\\JDBC2\\src\\assets\\sound.mp3";     // For example

          Media sound = new Media(new File(musicFile).toURI().toString());
          MediaPlayer mediaPlayer = new MediaPlayer(sound);
          mediaPlayer.play();
        
        sd.ajout(d);
        
        insertprix.clear();
        choiceBox.setValue(null);
        insertdate_devis.setValue(null);
        alerta.setText("C'est fait ");
        }
        
        
        //refresh
          ServiceDevis sd = new ServiceDevis();
        List devis = sd.afficher();
        ObservableList list = FXCollections.observableArrayList(devis);
        tableview.setItems(list);
        id_devis.setCellValueFactory(new PropertyValueFactory<>("id_devis"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        id_Client.setCellValueFactory(new PropertyValueFactory<>("id_Client"));
        date_devis.setCellValueFactory(new PropertyValueFactory<>("date_devis"));
    }
  //  @Override
    
 public void tointerfaceadmin(ActionEvent event) throws IOException {
  root = FXMLLoader.load(getClass().getResource("interfaceadmin.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
  
 }
 
  @FXML
    void getSelected (MouseEvent event){
    index = tableview.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    insertprix.setText(prix.getCellData(index).toString());
  //  insertid_client.setText(id_Client.getCellData(index).toString());
     choiceBox.setValue(id_Client.getCellData(index));
    insertdate_devis.setValue(LocalDate.parse(date_devis.getCellData(index)));
 
     modifint = id_devis.getCellData(index);
     // alerta.setText(String.valueOf(modifint));
      updatebut.setVisible(true);
      addbut.setVisible(false);
   
    
    }
    
    @FXML
   private void updateDevis (ActionEvent event){
         if( ( insertprix.getText().isEmpty() == true ) || (String.valueOf(choiceBox.getValue()).isEmpty() == true)  || (String.valueOf(insertdate_devis.getValue()).isEmpty() == true)){
            alerta.setText("REMPLISSEZ TOUS LES CHAMPS SVP !!!! ");
        }else 
        {
        Devis d = new Devis();
        d.setId_devis(modifint);
        d.setPrix(Double.parseDouble(insertprix.getText()));
      //  d.setId_Client(Integer.parseInt(insertid_client.getText()));
        d.setId_Client(choiceBox.getValue());
        d.setDate_devis(String.valueOf(insertdate_devis.getValue()));
          String musicFile = "C:\\Users\\PLANETAMIR\\Desktop\\Intégration\\wetransfer_jdbc2_2022-03-08_0325\\JDBC2\\src\\assets\\sound.mp3";     // For example

          Media sound = new Media(new File(musicFile).toURI().toString());
          MediaPlayer mediaPlayer = new MediaPlayer(sound);
          mediaPlayer.play();
        
        sd.modifier(d);
        
        //refresh
          ServiceDevis sd = new ServiceDevis();
        List devis = sd.afficher();
        ObservableList list = FXCollections.observableArrayList(devis);
        tableview.setItems(list);
        id_devis.setCellValueFactory(new PropertyValueFactory<>("id_devis"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        id_Client.setCellValueFactory(new PropertyValueFactory<>("id_Client"));
        date_devis.setCellValueFactory(new PropertyValueFactory<>("date_devis"));
       insertprix.clear();
        choiceBox.setValue(null);
        insertdate_devis.setValue(null);
        updatebut.setVisible(false);
      addbut.setVisible(true);}
   }
 void sendEmail() throws MessagingException {
		String fromUser = "erzafixed@gmail.com";  //Enter sender email id
		String fromUserPassword = "spacetoon";  //Enter sender gmail password , this will be authenticated by gmail smtp server
		String emailHost = "smtp.gmail.com";
		Transport transport = newSession.getTransport("smtp");
		transport.connect(emailHost, fromUser, fromUserPassword);
		transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
		transport.close();
		System.out.println("Email successfully sent!!!");
	}

	MimeMessage draftEmail() throws AddressException, MessagingException, IOException {
		String[] emailReceipients = {"simbey2000@gmail.com"};  //Enter list of email recepients
		String emailSubject = "Alerte supression d'un devis";
		//String emailBody = "Test Body of my email";
		mimeMessage = new MimeMessage(newSession);
		
		for (int i =0 ;i<emailReceipients.length;i++)
		{
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailReceipients[i]));
		}
		mimeMessage.setSubject(emailSubject);
	   
      // CREATE MIMEMESSAGE 
	    // CREATE MESSAGE BODY PARTS 
	    // CREATE MESSAGE MULTIPART 
	    // ADD MESSAGE BODY PARTS ----> MULTIPART 
	    // FINALLY ADD MULTIPART TO MESSAGECONTENT i.e. mimeMessage object 
	    
	    
		 MimeBodyPart bodyPart = new MimeBodyPart();
		 bodyPart.setContent(emailBody,"text/html;charset=UTF-8");
		 MimeMultipart multiPart = new MimeMultipart();
		 multiPart.addBodyPart(bodyPart);
		 mimeMessage.setContent(multiPart);
		 return mimeMessage;
	}

	void setupServerProperties() {
		Properties properties = System.getProperties();
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		newSession = Session.getDefaultInstance(properties,null);
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
    private void tostock(ActionEvent event) throws IOException {
               System.out.println("You clicked me!");
           Parent modifierCours = FXMLLoader.load(getClass().getResource("interfaceadmin2.fxml"));
        Scene modifierCours_scene = new Scene(modifierCours);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(modifierCours_scene);
        app_stage.show();
    }
	
 
}