

package test;
 
import java.awt.Image;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.image.Image;

//import javafx.scene.image.Image;


public class NewFXMain extends Application {
 
 @Override
 public void start(Stage stage) throws Exception {
  try {
   
   Parent root = FXMLLoader.load(getClass().getResource("../GUI/login.fxml"));
   Scene scene = new Scene(root);
   stage.setScene(scene);
   stage.setResizable(false);
 //  Image image = new Image("C:\\Users\\MSI\\Desktop\\Nouveau dossier (4)\\JDBC\\src\\assets\\274632604_1082973332260706_5424613250223252116_n..png");
     //   Image icon = new Image(getClass().getResourceAsStream("icon.png"));
   //stage.getIcons().add(icon);
   stage.setTitle("Irepair");
   //stage.getIcons().add(new Image("C:\\Users\\MSI\\Desktop\\Nouveau dossier (4)\\JDBC\\src\\assets\\274632604_1082973332260706_5424613250223252116_n.png"));
   stage.show();
   
  } catch(Exception e) {
   e.printStackTrace();
  }
 } 

 public static void main(String[] args) {
  launch(args);
 }
}