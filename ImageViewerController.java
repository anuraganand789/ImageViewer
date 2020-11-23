import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.geometry.Pos;

import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;
import javafx.scene.layout.VBox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ImageViewerController {

    @FXML
    private BorderPane appBorderPane;

    @FXML
    private ListView<Photo> listView;

    @FXML
    private ImageView imageView;

    @FXML
    private Pane      imageViewPane;
    
    private ObservableList<Photo> listOfPhotos; 
    private Map<String, Image>    mapOfImages; 

    public void initialize(){

        listOfPhotos = FXCollections.observableArrayList();
	listOfPhotos.add(new Photo("images/ganesh-bhagwan.jpeg",         "Ganesh Bhagwan"           ));
	listOfPhotos.add(new Photo("images/hanumanji-paanch-sir.jpg",    "Panchmukhi Hanuman Jee"   ));
	listOfPhotos.add(new Photo("images/ramjee-hanumanjee.jpg",       "Ramjee Hanumar Jee"       ));  
	listOfPhotos.add(new Photo("images/shivjee-with-family_2.jpg",   "Shivjee With Family 1"    ));
	listOfPhotos.add(new Photo("images/shivjee-with-family.jpg",     "ShivJee With Family 2"    )); 
	listOfPhotos.add(new Photo("images/vishnuroop.jpeg",             "Brihad Vishnuroop"        ));
        
	listView.setItems(listOfPhotos);

        mapOfImages  = new HashMap<>(listOfPhotos.size());

	imageView.fitWidthProperty().bind(imageViewPane.widthProperty());
	imageView.fitHeightProperty().bind(imageViewPane.heightProperty());

	listView.setCellFactory((imageView) -> new ImageTextCell());

	listView.getSelectionModel()
	        .selectedItemProperty()
	        .addListener((obValue, oldPhoto, newPhoto) 
	                      -> { 
			           final Image image = Optional.<Image>ofNullable(mapOfImages.get(newPhoto.title()))
			                                       .orElse(new Image(newPhoto.location()));
			           mapOfImages.putIfAbsent(newPhoto.title(), image);
				   ImageViewer.stage.setTitle(newPhoto.title());
	                           imageView.setImage(image);
			        }
	                    );
    }
    
    private class ImageTextCell extends ListCell<Photo>{
        private VBox vbox            = new VBox(8.0);
	private Label label          = new Label();
	private ImageView thumbImageView = new ImageView();
        
	{
	    thumbImageView.setFitHeight(100.0);
	    thumbImageView.setPreserveRatio(true);

            label.setWrapText(true);
	    label.setTextAlignment(TextAlignment.CENTER);
	    label.setLabelFor(thumbImageView);
	    label.underlineProperty().setValue(true);

	    vbox.setAlignment(Pos.CENTER);
	    vbox.getChildren().addAll(thumbImageView, label);
	    vbox.cursorProperty().setValue(Cursor.HAND);

	}

        

	@Override
	protected void updateItem(final Photo photo, final boolean empty){
            super.updateItem(photo, empty);
	    
	    if(empty || photo == null) { 
	        setGraphic(null); 
		return;
	    }

	    label.setText(photo.title());
	    thumbImageView.setImage(new Image(photo.location())); 
	    
	    setGraphic(vbox);

	}
    }

}

