import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class ExitConfirmation {

    static boolean answer;

    public static boolean display(String title, String message){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);
        Label label = new Label();
        label.setText(message);

        // Create two buttons
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });

        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.TOP_CENTER);

        Scene scene = new Scene(layout, 100, 100);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }
}
