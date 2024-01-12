package GUI;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

    public class LoginController{

    @FXML
    private AnchorPane AnchorPane;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField emailinput;

       @FXML
    void login(ActionEvent event) throws IOException{
        
    
         
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/interfaceadmin.fxml"));
            Parent root = loader.load();
            anchorPane.getScene().setRoot(root);
   
         

    }
}


 

    

