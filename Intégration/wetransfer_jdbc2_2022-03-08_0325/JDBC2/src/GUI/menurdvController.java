package GUI;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import entities.Rendezvous;

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
import services.ServiceRendezvous;
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
import services.ServiceRendezvous;
import entities.Rendezvous;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.util.Properties;
import javafx.scene.control.ChoiceBox;
import services.ServiceDevis;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
public class menurdvController implements Initializable {

 private Stage stage;
 private Scene scene;
 private Parent root;
  @FXML
    private AnchorPane pane;
    @FXML
    private TableView<Rendezvous> tableview;
    @FXML
    private TableColumn<Rendezvous, Integer> id_rdv;
    @FXML
    private TableColumn<Rendezvous, String> date_rdv;
    @FXML
    private TableColumn<Rendezvous, Integer> id_devis;
    @FXML
    private TableColumn<Rendezvous, Integer> id_Client;
        @FXML
    private ChoiceBox<Integer> choiceBox;
          @FXML
    private ChoiceBox<Integer> choiceBox2;
    
    @FXML
    private Button updatebut;
      @FXML
    private Button testr;
    @FXML
    private Button addbut;
    
    @FXML
    private TextField filterField;
  
  
    @FXML
    private TextField insertid_client;
       @FXML
    private Label alerta;
    @FXML
    private TextField insertid_devis;
    @FXML
    private DatePicker insertdate_rdv;
     ServiceRendezvous sd = new ServiceRendezvous();
    int index = -1;
    int modifint = -1;
     private final ObservableList<Rendezvous> dataList = FXCollections.observableArrayList();
    Session newSession = null;
	MimeMessage mimeMessage = null;
        String emailBody ="nnn";
 
 
    public void initialize(URL url, ResourceBundle rb) {

       ServiceRendezvous sd = new ServiceRendezvous();
        List rendezvous = sd.afficher();
        ObservableList list = FXCollections.observableArrayList(rendezvous);
        tableview.setItems(list);
        id_rdv.setCellValueFactory(new PropertyValueFactory<>("id_rdv"));
        date_rdv.setCellValueFactory(new PropertyValueFactory<>("date_rdv"));
        id_devis.setCellValueFactory(new PropertyValueFactory<>("id_devis"));
        id_Client.setCellValueFactory(new PropertyValueFactory<>("id_client"));
        
         ServiceDevis sp = new ServiceDevis();
         List deviss = sp.afficherid();
           ObservableList lista = FXCollections.observableArrayList(deviss);
        // choiceBox.getItems().add(deviss);
         choiceBox.setItems(lista);
         
         ServiceDevis de = new ServiceDevis();
         List devisss = de.statiddevis();
           ObservableList listaa = FXCollections.observableArrayList(devisss);
        // choiceBox.getItems().add(deviss);
         choiceBox2.setItems(listaa);
        
        
         FilteredList<Rendezvous> filteredData = new FilteredList<>(list, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Rendezvous -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (String.valueOf(Rendezvous.getId_rdv()).indexOf(lowerCaseFilter)!=-1) {
					return true; // Filter matches first name.
				} else if (String.valueOf(Rendezvous.getId_devis()).indexOf(lowerCaseFilter)!=-1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(Rendezvous.getId_client()).indexOf(lowerCaseFilter)!=-1){
				     return true;
                                } else if (Rendezvous.getDate_rdv().toLowerCase().indexOf(lowerCaseFilter) != -1)
                                    return true;
                                
                                
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Rendezvous> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tableview.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tableview.setItems(sortedData);
		
    }  
    
    /*****************************/
 @FXML
void tester(ActionEvent event) throws AddressException, MessagingException, IOException
	{
		//Mail mail = new Mail();
		setupServerProperties();
		draftEmail();
		sendEmail();
	}




/*****************/

    @FXML
    void deleterdv(ActionEvent event)throws AddressException, MessagingException, IOException {
         int dialogButton = JOptionPane.YES_NO_OPTION;
                 int dialogResult = JOptionPane.showConfirmDialog(null, "Voulez vous supprimer ?", "Alert !", dialogButton);
                if(dialogResult == 0) {
        ServiceRendezvous sd = new ServiceRendezvous();
        Rendezvous selectedID = tableview.getSelectionModel().getSelectedItem();
        String s=String.valueOf(selectedID);
        alerta.setText("Supprimé");
        
        emailBody = "Monsieur nous vous informons que le rendez-vous numéro " + selectedID.getId_rdv() + " programé le " + selectedID.getDate_rdv() +" a été supprimé.";
        
        setupServerProperties();
	draftEmail();
	sendEmail();
        
       sd.supprimer(selectedID);
        String musicFile = "C:\\Users\\PLANETAMIR\\Desktop\\Intégration\\wetransfer_jdbc2_2022-03-08_0325\\JDBC2\\src\\assets\\nosound.mp3";     // For example

          Media sound = new Media(new File(musicFile).toURI().toString());
          MediaPlayer mediaPlayer = new MediaPlayer(sound);
          mediaPlayer.play();
      //  tableview.getItems().remove(selectedID);
       //refresh
          
        List Rendezvous = sd.afficher();
        ObservableList list = FXCollections.observableArrayList(Rendezvous);
        tableview.setItems(list);
       id_rdv.setCellValueFactory(new PropertyValueFactory<>("id_rdv"));
        date_rdv.setCellValueFactory(new PropertyValueFactory<>("date_rdv"));
        id_devis.setCellValueFactory(new PropertyValueFactory<>("id_devis"));
        id_Client.setCellValueFactory(new PropertyValueFactory<>("id_client"));
        insertid_devis.clear();
        choiceBox.setValue(null);
        insertdate_rdv.setValue(null);
         } else {
                      List Rendezvous = sd.afficher();
        ObservableList list = FXCollections.observableArrayList(Rendezvous);
        tableview.setItems(list);
       id_rdv.setCellValueFactory(new PropertyValueFactory<>("id_rdv"));
        date_rdv.setCellValueFactory(new PropertyValueFactory<>("date_rdv"));
        id_devis.setCellValueFactory(new PropertyValueFactory<>("id_devis"));
        id_Client.setCellValueFactory(new PropertyValueFactory<>("id_client"));
        insertid_devis.clear();
        choiceBox.setValue(null);
        insertdate_rdv.setValue(null);
                }
    }
    @FXML
    private void insertrdv(ActionEvent event) {
        
        //ajout
         if( (String.valueOf(choiceBox.getValue()).isEmpty() == true) ||(String.valueOf(choiceBox2.getValue()).isEmpty() == true)|| (String.valueOf(insertdate_rdv.getValue()).isEmpty() == true)){
            alerta.setText("REMPLISSEZ TOUS LES CHAMPS SVP !!!! ");
        }else 
        {
       
        Rendezvous d = new Rendezvous();
        d.setId_devis(choiceBox2.getValue());
         d.setId_client(choiceBox.getValue());
       // d.setId_client(Integer.parseInt(choiceBox.getText()));
        d.setDate_rdv(String.valueOf(insertdate_rdv.getValue()));
          String musicFile = "C:\\Users\\PLANETAMIR\\Desktop\\Intégration\\wetransfer_jdbc2_2022-03-08_0325\\JDBC2\\src\\assets\\sound.mp3";     // For example

          Media sound = new Media(new File(musicFile).toURI().toString());
          MediaPlayer mediaPlayer = new MediaPlayer(sound);
          mediaPlayer.play();
        sd.ajout(d);
     //   insertid_devis.clear();
        choiceBox.setValue(null);
        choiceBox2.setValue(null);
        insertdate_rdv.setValue(null);
        alerta.setText("C'est fait ");
        }
        
        
        //refresh
          ServiceRendezvous sd = new ServiceRendezvous();
        List rendezvous = sd.afficher();
        ObservableList list = FXCollections.observableArrayList(rendezvous);
        tableview.setItems(list);
        id_rdv.setCellValueFactory(new PropertyValueFactory<>("id_rdv"));
        date_rdv.setCellValueFactory(new PropertyValueFactory<>("date_rdv"));
        id_devis.setCellValueFactory(new PropertyValueFactory<>("id_devis"));
        id_Client.setCellValueFactory(new PropertyValueFactory<>("id_client"));
    }
  //  @Override
    
 
 
  @FXML
    void getSelected (MouseEvent event){
    index = tableview.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    //insertid_devis.setText(id_devis.getCellData(index).toString());
        choiceBox.setValue(id_Client.getCellData(index));
          choiceBox2.setValue(id_devis.getCellData(index));
    insertdate_rdv.setValue(LocalDate.parse(date_rdv.getCellData(index)));
 
     modifint = id_rdv.getCellData(index);
  //    alerta.setText(String.valueOf(modifint));
      updatebut.setVisible(true);
      addbut.setVisible(false);
   
    
    }
    
    @FXML
   private void updaterdv(ActionEvent event){
         if( (String.valueOf(choiceBox2.getValue()).isEmpty() == true) || (String.valueOf(choiceBox.getValue()).isEmpty() == true) || (String.valueOf(insertdate_rdv.getValue()).isEmpty() == true)){
            alerta.setText("REMPLISSEZ TOUS LES CHAMPS SVP !!!! ");
        }else 
        {
        Rendezvous d = new Rendezvous();
        d.setId_rdv(modifint);
        d.setId_devis(choiceBox2.getValue());
         d.setId_client(choiceBox.getValue());
        //d.setId_client(Integer.parseInt(insertid_client.getText()));
        d.setDate_rdv(String.valueOf(insertdate_rdv.getValue()));
          String musicFile = "C:\\Users\\PLANETAMIR\\Desktop\\Intégration\\wetransfer_jdbc2_2022-03-08_0325\\JDBC2\\src\\assets\\sound.mp3";     // For example

          Media sound = new Media(new File(musicFile).toURI().toString());
          MediaPlayer mediaPlayer = new MediaPlayer(sound);
          mediaPlayer.play();
        sd.modifier(d);
        
        //refresh
          ServiceRendezvous sd = new ServiceRendezvous();
        List devis = sd.afficher();
        ObservableList list = FXCollections.observableArrayList(devis);
        tableview.setItems(list);
     id_rdv.setCellValueFactory(new PropertyValueFactory<>("id_rdv"));
        date_rdv.setCellValueFactory(new PropertyValueFactory<>("date_rdv"));
        id_devis.setCellValueFactory(new PropertyValueFactory<>("id_devis"));
        id_Client.setCellValueFactory(new PropertyValueFactory<>("id_client"));
      //  insertid_devis.clear();
       // insertid_client.clear();
      //  insertdate_rdv.setValue(null);
        insertdate_rdv.setValue(null);
        choiceBox.setValue(null);
        choiceBox2.setValue(null);
        updatebut.setVisible(false);
      addbut.setVisible(true);}
   }
 
 public void tointerfaceadmin(ActionEvent event) throws IOException {
  root = FXMLLoader.load(getClass().getResource("interfaceadmin.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
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
		String emailSubject = "Alerte supression d'un rendez-vous";
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