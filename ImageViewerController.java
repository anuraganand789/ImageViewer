import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javafx.fxml.FXML;

import javafx.scene.control.ListView;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;

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

}

