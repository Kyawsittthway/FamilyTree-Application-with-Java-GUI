import javafx.stage.*;

import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

/**
 * This class to created to display the warning dialog when requried in GUI class
 * @author Kyaw Sitt Tway
 *
 */

public class Alert {
	
/**
 * This method is created to display the warning with a passed title and message when required
 * @param title
 * @param message
 */
public static void display(String title,String message)
{
	Stage window = new Stage();
	window.initModality(Modality.APPLICATION_MODAL);
	window.setTitle(title);
	window.setMinWidth(250);
	
	Label label = new Label();
	label.setText(message);
	Button closeButton = new Button("Close");
	closeButton.setOnAction(e->window.close());
	
	VBox layout = new VBox(10);
	layout.getChildren().addAll(label,closeButton);
	layout.setAlignment(Pos.CENTER);
	
	Scene alert_scene = new Scene(layout,550,100);
	window.setScene(alert_scene);
	window.showAndWait();
}

/**
 * This method is created to display the warning with yes or no button
 * @param title
 * @param message
 */
public static void yesNo(String title,String message)
{
	Stage window = new Stage();
	window.initModality(Modality.APPLICATION_MODAL);
	window.setTitle(title);
	window.setMinWidth(250);
	
	Label label = new Label();
	label.setText(message);
	Button yes = new Button("Yes");
	yes.setOnAction(e->window.close());
	Button no = new Button("No");
	no.setOnAction(e->window.close());
	
	VBox layout = new VBox(10);
	layout.getChildren().addAll(label,yes,no);
	layout.setAlignment(Pos.CENTER);
	
	Scene alert_scene = new Scene(layout,550,100);
	window.setScene(alert_scene);
	window.showAndWait();
}

}
