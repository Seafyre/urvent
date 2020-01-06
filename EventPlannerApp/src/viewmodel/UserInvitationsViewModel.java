/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewmodel;

import EventHandlers.SwitchViewModelHandler;
import eventplannerappDELETETHISLATER.EventPlannerApp;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import model.Invitation;
import org.json.simple.JSONObject;
import tools.APICommand;
import tools.HTTP;

/**
 * FXML Controller class
 *
 * @author User
 */
public class UserInvitationsViewModel extends ViewModel implements Initializable 
{
    @FXML
    private Button homeBtn;
    
    ArrayList<Invitation> invitations = new ArrayList<Invitation>();
    
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
        ArrayList<JSONObject> obj = HTTP.getArray(APICommand.getInvitationByUserID(EventPlannerApp.app.getActiveUser().getID()));
        for(JSONObject n : obj)
        {
            Invitation inv = new Invitation(n);
            invitations.add(inv);
        }
    }

    @Override
    protected void createHandlers() 
    {
        homeBtn.setOnAction(new SwitchViewModelHandler("/view/HomeView.fxml",this));
    }
    
}
