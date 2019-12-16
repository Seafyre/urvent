/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewmodel;

import EventHandlers.CreateEventConfirmButtonEventHandler;
import EventHandlers.SwitchViewModelHandler;
import eventplannerappDELETETHISLATER.EventPlannerApp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;
import tools.APICommand;
import tools.HTTP;

/**
 * FXML Controller class
 *
 * @author User
 */
public class CreateEventViewModel extends ViewModel  implements Initializable 
{
    @FXML
    private Button homeBtn;
    
    @FXML
    private Button submitBtn;  

    @FXML
    private Button cancelBtn;
    
    @FXML
    private TextField nameTxtFld;
    
    @FXML
    private TextField descrTxtFld;
            
    @FXML
    private TextField dateTxtFld;
            
    @FXML
    private TextField locationTxtFld;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        EventPlannerApp.app.setActiveVM(this);
        createHandlers();
    }    

    @Override
    protected void loadData() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void createHandlers() 
    {
        homeBtn.setOnAction(new SwitchViewModelHandler("/view/HomeView.fxml",this));
        submitBtn.setOnAction(new CreateEventConfirmButtonEventHandler(this));
        cancelBtn.setOnAction(new SwitchViewModelHandler("/view/UserOwnedEventsView.fxml",this));   
    }
    
    public void createNewEvent()
    {
       JSONObject reply = HTTP.get(APICommand.insertNewEvent(EventPlannerApp.app.getActiveUser().getID() ,nameTxtFld.getText(), descrTxtFld.getText(), Integer.valueOf(locationTxtFld.getText()))); //TODO choose location
       Boolean success = (Boolean) reply.get("succ");
       if(success == true)
       {
           System.out.println("created event!");
           EventPlannerApp.switchViewModel("/view/UserOwnedEventsView.fxml");
       }
       else
       {
           Alert a = new Alert(Alert.AlertType.ERROR);
           a.setContentText("Create Event Failed");
           a.showAndWait();
       }
    }

}
