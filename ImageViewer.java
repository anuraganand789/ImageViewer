public class ImageViewer extends javafx.application.Application{

    public static javafx.stage.Stage stage;
    @Override
    public void start(final javafx.stage.Stage stage) throws java.io.IOException{
        ImageViewer.stage = stage;
        stage.setTitle("Image Viewer");
	stage.setScene(new javafx.scene.Scene(javafx.fxml.FXMLLoader.load(getClass().getResource("image-viewer.fxml"))));
	stage.show();
    }

    public static void main(final String ... args){
        launch(args);
    }
}
