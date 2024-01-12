/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author aadhayma
 */
public class MenuController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Categoriefxml(ActionEvent event) {
         Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource("CategorieInterfacesample.fxml"));
            
        }catch(IOException ex){
            System.out.println("javafxapplication2.MenuController.goservice()");
            
        }
        bp.setCenter(root);
    }
         
        
    

    @FXML
    private void sampl(ActionEvent event) {
        Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            
        }catch(IOException ex){
            System.out.println("javafxapplication2.MenuController.goservice()");
            
        }
         bp.setCenter(root);
       
    }

    @FXML
    private void web(ActionEvent event) {
          Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource("menudevis.fxml"));
            
        }catch(IOException ex){
            System.out.println("javafxapplication2.MenuController.goservice()");
            
        }
         bp.setCenter(root);
        
    }

   
    
}
