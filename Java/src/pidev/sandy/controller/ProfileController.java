package pidev.sandy.controller;
 
 
 
 
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import pidev.sandy.controller.LoginController;
import pidev.sandy.entites.User;
import pidev.sandy.services.MyServices;
 

/**
 * Java FX FXML Controller.
 * @author  Tarun Tyagi
 */
public class ProfileController implements Initializable {

    @FXML
    private Button update;
    @FXML
    private Label message;
    @FXML
    private TextField user;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private Button Button;
    @FXML
    private Label success;
 
  private  static MyServices myServices=new MyServices();
    @Override public void initialize(URL location, ResourceBundle resources) {
           
                 User loggedUser = LoginController.getInstance().getLoggedUser();
           
 User UserConneter=myServices.chercherUtilisateurByid(loggedUser.getId());
        
  
        
        user.setText(String.valueOf(UserConneter.getId()));
        if(UserConneter.getEmail() != null) {
            email.setText(UserConneter.getEmail());
        }
        if(UserConneter.getPhone() != null) {
            phone.setText(UserConneter.getPhone());
        }
    
        
    }

    @FXML protected void processLogout() {
        
        LoginController.getInstance().userLogout();
        
    }

    @FXML protected void processUpdate() {
      
        
        
        animateMessage();
    }

    private void animateMessage() {
        FadeTransition ft = new FadeTransition(new Duration(3000), success);
        ft.setFromValue(0.0);
        ft.setToValue(1);
        ft.play();
    }    
}