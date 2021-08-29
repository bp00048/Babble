/**
 * 
 */
package edu.westga.cs.babble.controllers;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import edu.westga.cs.babble.model.EmptyTileBagException;
import edu.westga.cs.babble.model.PlayedWord;
import edu.westga.cs.babble.model.Tile;
import edu.westga.cs.babble.model.TileBag;
import edu.westga.cs.babble.model.TileNotInGroupException;
import edu.westga.cs.babble.model.TileRack;
import edu.westga.cs.babble.model.TileRackFullException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.util.converter.NumberStringConverter;

/**
 * @author windy
 *
 */
public class BabbleController implements Initializable {
	@FXML
	private ListView<Tile> tilesDisplayed;
	@FXML
	private ListView<Tile> yourWordDisplayed;
	@FXML
	private Label testLabel;
	@FXML
	private Button resetButton;
	@FXML
	private Button playWordButton;
	@FXML
	private TextField scoreField;
	private TileRack tiles;
	private TileBag tileBag;
	private PlayedWord playedTiles;
	private WordDictionary wordChecker;
	private int runningScore;

	/**
	 * @throws EmptyTileBagException
	 * @throws TileRackFullException
	 * 
	 */
	public BabbleController() throws TileRackFullException, EmptyTileBagException {
		this.tilesDisplayed = new ListView<>();
		this.yourWordDisplayed = new ListView<>();
		this.scoreField = new TextField();
		this.tileBag = new TileBag();
		this.tiles = new TileRack();
		this.playedTiles = new PlayedWord();
		this.resetButton = new Button();
		this.playWordButton = new Button();
		this.wordChecker = new WordDictionary();

		this.fillTiles();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		ObservableList<Tile> data = FXCollections.observableList(tiles.tiles());
		this.tilesDisplayed.setItems(data);

		this.tilesDisplayed.setCellFactory(new Callback<ListView<Tile>, ListCell<Tile>>() {

			@Override
			public ListCell<Tile> call(ListView<Tile> arg0) {

				ListCell<Tile> cell = new ListCell<Tile>() {

					@Override
					protected void updateItem(Tile t, boolean bln) {
						super.updateItem(t, bln);
						if (t == null) {
							setText(null);
						}
						if (t != null) {
							setText(String.valueOf(t.getLetter()));
						}
					}

				};

				return cell;
			}
		});

		yourWordDisplayed.setItems(playedTiles.tiles());

		this.tilesDisplayed.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				playedTiles.append(tilesDisplayed.getSelectionModel().selectedItemProperty().get());
				try {
					tiles.remove(tilesDisplayed.getSelectionModel().selectedItemProperty().get());
				} catch (TileNotInGroupException e) {
					return;
				}
				tilesDisplayed.getSelectionModel().clearSelection();
			}
		});

		this.yourWordDisplayed.setCellFactory(new Callback<ListView<Tile>, ListCell<Tile>>() {

			@Override
			public ListCell<Tile> call(ListView<Tile> arg0) {

				ListCell<Tile> cell = new ListCell<Tile>() {

					@Override
					protected void updateItem(Tile t, boolean bln) {
						super.updateItem(t, bln);
						if (t == null) {
							setText(null);
						}
						if (t != null) {
							setText(String.valueOf(t.getLetter()));
						}
					}

				};

				return cell;
			}
		});

		this.yourWordDisplayed.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				tiles.append(yourWordDisplayed.getSelectionModel().selectedItemProperty().get());
				try {
					playedTiles.remove(yourWordDisplayed.getSelectionModel().selectedItemProperty().get());
				} catch (TileNotInGroupException e) {
					return;
				}
				yourWordDisplayed.getSelectionModel().clearSelection();
				tilesDisplayed.setItems(tiles.tiles());
			}
		});

		this.resetButton.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				for (Tile current : playedTiles.tiles()) {
					tiles.append(current);
				}
				tilesDisplayed.setItems(tiles.tiles());
				playedTiles.clear();
			}
		});

		this.playWordButton.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				String currentHand = playedTiles.getHand();
				if (wordChecker.isValidWord(currentHand)) {
					runningScore += playedTiles.getScore();
					playedTiles.clear();
					IntegerProperty integerProperty = new SimpleIntegerProperty(runningScore);
					scoreField.textProperty().bindBidirectional(integerProperty, new NumberStringConverter());
					int tilesNeeded = tiles.getNumberOfTilesNeeded();
					do {
						try {
							tiles.append(tileBag.drawTile());
						} catch (TileRackFullException | EmptyTileBagException e) {
							e.printStackTrace();
						}
						tilesNeeded--;
					} while (tilesNeeded != 0);
					tilesDisplayed.setItems(tiles.tiles());

				} else {
					Alert a = new Alert(AlertType.INFORMATION);

					a.setHeaderText("Invalid word");

					a.show();

				}
			}
		});

	}


	public void fillTiles() throws TileRackFullException, EmptyTileBagException {
		int tilesNeeded = tiles.getNumberOfTilesNeeded();
		do {
			tiles.append(tileBag.drawTile());
			tilesNeeded--;
		} while (tilesNeeded != 0);
		tilesDisplayed.setItems(tiles.tiles());

	}
}
