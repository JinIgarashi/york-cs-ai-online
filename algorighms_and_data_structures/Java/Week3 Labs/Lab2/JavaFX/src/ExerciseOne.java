
import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.paint.Color; 
import javafx.scene.shape.Arc; 
import javafx.scene.shape.ArcType; 
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font; 
import javafx.scene.text.Text; 
import javafx.stage.Stage;

public class ExerciseOne extends Application {

	public void start (Stage stage)     
	{ 
		
		Line line1 = new Line();
		line1.setStartX(100.0f);
		line1.setStartY(70.0f);
		line1.setEndX(100.0f);
		line1.setEndY(250.0f);
		line1.setStroke(Color.RED);
		
		Line line2 = new Line();
		line2.setStartX(100.0f);
		line2.setStartY(250.0f);
		line2.setEndX(400.0f);
		line2.setEndY(250.0f);
		line2.setStroke(Color.RED);
		
		Line line3 = new Line();
		line3.setStartX(400.0f);
		line3.setStartY(250.0f);
		line3.setEndX(100.0f);
		line3.setEndY(70.0f);
		line3.setStroke(Color.RED);
	
        // create a group that holds all the features                     
		Group root = new Group(line1, line2, line3); 
		
		// create and configure a new scene         
		Scene scene = new Scene(root, 450, 275, Color.YELLOW); 
	
		// add the scene to the stage, then set the title         
		stage.setScene(scene);         
		stage.setTitle("Smiley Face"); 
		
		// show the stage         
		stage.show();  
	} 

    public static void main(String[] args)     
    {         
    	launch(args);
    }   
}

	
	
	


