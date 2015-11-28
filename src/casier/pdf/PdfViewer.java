package casier.pdf;

import casier.entities.Peine;
import casier.entities.Personne;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Callback;

public class PdfViewer extends Application {
    
        private Pagination pagination ;
	private Label currentZoomLabel ;
	
	private FileChooser fileChooser ;
	private ObjectProperty<PDFFile> currentFile ;
	private ObjectProperty<ImageView> currentImage ;
        private ScrollPane scroller ;
	private DoubleProperty zoom ;
	private PageDimensions currentPageDimensions ;
	private ExecutorService imageLoadService ;
	
	private static final double ZOOM_DELTA = 1.05 ;
        
        private Personne personne;
        private List<Peine> peines;
        
    /**
     *
     * @param stage 
     * @param personne la personne pour laquelle on génère le casier judiciaire
     * @param peines la liste des peines de la personne concernée
     * @throws IOException
     */
    public void run(Stage stage, Personne personne, List<Peine> peines) throws IOException{ // fonction à utiliser comme evenement d'un bouton pour générer un casier judiciaire
            this.personne = personne;
            this.peines = peines;
            
            start(stage);
        }
	
    
	@Override
	public void start(Stage primaryStage) throws IOException {
		
            BorderPane grid = new BorderPane();
            scroller = new ScrollPane();
            scroller.setPannable(true);
            grid.setPadding(new Insets(5, 5, 5, 5));
            
            HBox hb = new HBox(5);
            hb.setAlignment(Pos.TOP_LEFT);
            hb.setPadding(new Insets(5, 5, 5, 5));
            
            Label zoomB = new Label("Zoom:");
            
            Button zoomOut = new Button("-");
            Button zoomIn = new Button("+");
            Button zoomFit = new Button("zoom to fit");
            Button zoomWidth = new Button("zoom to width");
            currentZoomLabel = new Label();
            
            hb.getChildren().add(zoomB);
            hb.getChildren().add(zoomOut);
            hb.getChildren().add(zoomIn);
            hb.getChildren().add(zoomFit);
            hb.getChildren().add(zoomWidth);
            hb.getChildren().add(currentZoomLabel);
            
            pagination = new Pagination();
            
            grid.setTop(hb);
            grid.setCenter(pagination);
            grid.setRight(scroller);
            
            zoomOut.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    zoomOut();
                }
            });
            
            zoomIn.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    zoomIn();
                }
            });
            
            zoomFit.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    zoomFit();
                }
            });
            
            zoomWidth.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    zoomWidth();
                }
            });
               
                try {
                    // Chargement du fichier à l'ouverture de la fenêtre
                loadFile();
            } catch (Exception e) {
                System.out.println("Erreur lors de l'ouverture du visualiseur");
                e.printStackTrace();
            }
                
                primaryStage.setScene(new Scene(grid,600, 800));
                
                primaryStage.setTitle("Visualiseur de casier judiciaire");
                
		primaryStage.show();
	}
        
	public static void main(String[] args) {
		launch(args);
	}
        
        
        public void initialize() {
		
		createAndConfigureImageLoadService();
		//createAndConfigureFileChooser();
		
		currentFile = new SimpleObjectProperty<>();
		updateWindowTitleWhenFileChanges();
		
		currentImage = new SimpleObjectProperty<>();
		scroller.contentProperty().bind(currentImage);
		
		zoom = new SimpleDoubleProperty(1);
		// To implement zooming, we just get a new image from the PDFFile each time.
		// This seems to perform well in some basic tests but may need to be improved
		// E.g. load a larger image and scale in the ImageView, loading a new image only
		// when required.
		zoom.addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				updateImage(pagination.getCurrentPageIndex());
			}
		});
		
		currentZoomLabel.textProperty().bind(Bindings.format("%.0f %%", zoom.multiply(100)));
		
		bindPaginationToCurrentFile();
		createPaginationPageFactory();
	}
        
        private void createAndConfigureImageLoadService() {
		imageLoadService = Executors.newSingleThreadExecutor(new ThreadFactory() {
			@Override
			public Thread newThread(Runnable r) {
				Thread thread = new Thread(r);
				thread.setDaemon(true);
				return thread;
			}
		});
	}
        
//        private void createAndConfigureFileChooser() {
//		fileChooser = new FileChooser();
//		fileChooser.setInitialDirectory(Paths.get(System.getProperty("user.home")).toFile());
//		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf", "*.PDF"));
//	}
        
        
	private void updateWindowTitleWhenFileChanges() {
		currentFile.addListener(new ChangeListener<PDFFile>() {
			@Override
			public void changed(ObservableValue<? extends PDFFile> observable, PDFFile oldFile, PDFFile newFile) {
				try {
					String title = newFile == null ? "Visualiseur de casier" : newFile.getStringMetadata("Title") ;
					Window window = pagination.getScene().getWindow();
					if (window instanceof Stage) {
						((Stage)window).setTitle(title);
					}
				} catch (IOException e) {
					showErrorMessage("Impossible d'obtenir le nom de ce fichier", e);
				}
			}
			
		});
	}
        
        private void bindPaginationToCurrentFile() {
		currentFile.addListener(new ChangeListener<PDFFile>() {
			@Override
			public void changed(ObservableValue<? extends PDFFile> observable, PDFFile oldFile, PDFFile newFile) {
				if (newFile != null) {
					pagination.setCurrentPageIndex(0);
				} 
			}
		});
		pagination.pageCountProperty().bind(new IntegerBinding() {
			{
				super.bind(currentFile);
			}
			@Override
			protected int computeValue() {
				return currentFile.get()==null ? 0 : currentFile.get().getNumPages() ;
			}
		});
		pagination.disableProperty().bind(Bindings.isNull(currentFile));
	}
        
        private void createPaginationPageFactory() {
		pagination.setPageFactory(new Callback<Integer, Node>() {
			@Override
			public Node call(Integer pageNumber) {
				if (currentFile.get() == null) {
					return null ;
				} else {
					if (pageNumber >= currentFile.get().getNumPages() || pageNumber < 0) {
						return null ;
					} else {
						updateImage(pageNumber);
						return scroller ;
					}
				}
			}
		});
	}
        
        // Chargement du casier judiciaire
        
        public void loadFile() {
              initialize();
		final File file = personne == null ? PdfTemplate.getCasierLikePdf("test.pdf") : PdfTemplate.getCasierLikePdf(personne, peines);
//fileChooser.showOpenDialog(pagination.getScene().getWindow());
		if (file != null) {
			final Task<PDFFile> loadFileTask = new Task<PDFFile>() {
				@Override
				protected PDFFile call() throws Exception {
					try ( 
							RandomAccessFile raf = new RandomAccessFile(file, "r");
							FileChannel channel = raf.getChannel() 
						) {
						ByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
						return new PDFFile(buffer);
					}
				}
			};
			loadFileTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
				@Override
				public void handle(WorkerStateEvent event) {
					pagination.getScene().getRoot().setDisable(false);
					final PDFFile pdfFile = loadFileTask.getValue();
					currentFile.set(pdfFile);
				}
			});
			loadFileTask.setOnFailed(new EventHandler<WorkerStateEvent>() {
				@Override
				public void handle(WorkerStateEvent event) {
					pagination.getScene().getRoot().setDisable(false);
					showErrorMessage("Could not load file "+file.getName(), loadFileTask.getException());
				}
			});
//			pagination.getScene().getRoot().setDisable(true);
			imageLoadService.submit(loadFileTask);
		}
	}
        
        // Fonction d'évenement
        
        private void zoomIn() {
		zoom.set(zoom.get()*ZOOM_DELTA);
	}
        
        private void zoomOut() {
		zoom.set(zoom.get()/ZOOM_DELTA);
	}
        
        private void zoomFit() {
		// TODO: the -20 is a kludge to account for the width of the scrollbars, if showing.
		double horizZoom = (scroller.getWidth()-20) / currentPageDimensions.width ;
		double verticalZoom = (scroller.getHeight()-20) / currentPageDimensions.height ;
		zoom.set(Math.min(horizZoom, verticalZoom));
	}
        
        private void zoomWidth() {
		zoom.set((scroller.getWidth()-20) / currentPageDimensions.width) ;
	}
        
        // Chargement image
        
        private void updateImage(final int pageNumber) {
		final Task<ImageView> updateImageTask = new Task<ImageView>() {
			@Override
			protected ImageView call() throws Exception {
				PDFPage page = currentFile.get().getPage(pageNumber+1);
				Rectangle2D bbox = page.getBBox();
				final double actualPageWidth = bbox.getWidth();
				final double actualPageHeight = bbox.getHeight();
				// record page dimensions for zoomToFit and zoomToWidth:
				currentPageDimensions = new PageDimensions(actualPageWidth, actualPageHeight);
				// width and height of image:
				final int width = (int) (actualPageWidth * zoom.get());
				final int height = (int) (actualPageHeight * zoom.get());
				// retrieve image for page:
				// width, height, clip, imageObserver, paintBackground, waitUntilLoaded:
				java.awt.Image awtImage = page.getImage(width, height, bbox, null, true, true); 
				// draw image to buffered image:
				BufferedImage buffImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
				buffImage.createGraphics().drawImage(awtImage, 0, 0, null);
				// convert to JavaFX image:
				Image image = SwingFXUtils.toFXImage(buffImage, null);
				// wrap in image view and return:
				ImageView imageView = new ImageView(image);
				imageView.setPreserveRatio(true);
				return imageView ;
			}
		};
                
                updateImageTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				pagination.getScene().getRoot().setDisable(false);
				currentImage.set(updateImageTask.getValue());
			}
		});
		
		updateImageTask.setOnFailed(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				pagination.getScene().getRoot().setDisable(false);
				updateImageTask.getException().printStackTrace();
			}
			
		});
		
		pagination.getScene().getRoot().setDisable(true);
		imageLoadService.submit(updateImageTask);
	}
        
        	private void showErrorMessage(String message, Throwable exception) {
		
		// TODO: move to fxml (or better, use ControlsFX)
		
		final Stage dialog = new Stage();
		dialog.initOwner(pagination.getScene().getWindow());
		dialog.initStyle(StageStyle.UNDECORATED);
		final VBox root = new VBox(10);
		root.setPadding(new Insets(10));
		StringWriter errorMessage = new StringWriter();
		exception.printStackTrace(new PrintWriter(errorMessage));
		final Label detailsLabel = new Label(errorMessage.toString());
		TitledPane details = new TitledPane();
		details.setText("Details:");
		Label briefMessageLabel = new Label(message);
		final HBox detailsLabelHolder =new HBox();
		
		Button closeButton = new Button("OK");
		closeButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				dialog.hide();
			}
		});
		HBox closeButtonHolder = new HBox();
		closeButtonHolder.getChildren().add(closeButton);
		closeButtonHolder.setAlignment(Pos.CENTER);
		closeButtonHolder.setPadding(new Insets(5));
		root.getChildren().addAll(briefMessageLabel, details, detailsLabelHolder, closeButtonHolder);
		details.setExpanded(false);
		details.setAnimated(false);

		details.expandedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				if (newValue) {
					detailsLabelHolder.getChildren().add(detailsLabel);
				} else {
					detailsLabelHolder.getChildren().remove(detailsLabel);
				}
				dialog.sizeToScene();
			}
			
		});
		final Scene scene = new Scene(root);

		dialog.setScene(scene);
		dialog.show();
	}
                
                private class PageDimensions {
		private double width ;
		private double height ;
		PageDimensions(double width, double height) {
			this.width = width ;
			this.height = height ;
		}
		@Override
		public String toString() {
			return String.format("[%.1f, %.1f]", width, height);
		}
	}

}
