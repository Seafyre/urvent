/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventplannerappDELETETHISLATER;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Admin
 */
public class EventPlannerApp extends Application 
{
    public static EventPlannerApp app;
    private static Stage MainStage;
    
    @Override
    public void start(Stage stage) throws Exception 
    {
        //Singleton Pattern, never instanciate another App or Mainstage
        app = this;
        MainStage = stage;
        
        Parent root = FXMLLoader.load(getClass().getResource("/view/HomeView.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        switchViewModel("/view/HomeView.fxml");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
    }
    
    public static void switchViewModel(String viewPath)
    {
        app.setScene(viewPath);
    }
    
    private void setScene(String viewPath)
    {
        try 
        {
            Parent sceneRoot = FXMLLoader.load(getClass().getResource(viewPath));
            Scene scene = new Scene(sceneRoot);
            MainStage.setScene(scene);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(EventPlannerApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
