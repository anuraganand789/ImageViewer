import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ImageViewer extends Application{

    private static  Stage stage;

    @Override
    public void start(final Stage stage) throws IOException{
        stage.setTitle("Image Viewer");
	    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("image-viewer.fxml"))));
	    stage.show();
        //Used in controller
        ImageViewer.stage = stage;
    }

    public static void main(final String ... args){
        launch(args);
    }

    public static void setStageTitle(final String title){
        stage.setTitle(title);
    }
}
