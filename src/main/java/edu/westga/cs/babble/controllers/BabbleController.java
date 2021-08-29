/**
 * 
 */
package edu.westga.cs.babble.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import edu.westga.cs.babble.model.EmptyTileBagException;
import edu.westga.cs.babble.model.PlayedWord;
import edu.westga.cs.babble.model.Tile;
import edu.westga.cs.babble.model.TileBag;
import edu.westga.cs.babble.model.TileNotInGroupException;
import edu.westga.cs.babble.model.TileRack;
import edu.westga.cs.babble.model.TileRackFullException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.util.converter.NumberStringConverter;

/**
 * @author Blair Pattison
 * @version 08/29/2021
 * 
 *          Controller for the Babble GUI.
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

	/**
	 * Initializes the components on the GUI and their respective listeners.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.buildYourTilesDisplayed();
		this.buildGameTilesDisplayed();

		this.tilesDisplayed.setOnMousePressed(new GameTileSelectedListener());
		this.yourWordDisplayed.setOnMousePressed(new YourTileSelectedListener());
		this.resetButton.setOnMousePressed(new ResetListener());
		this.playWordButton.setOnMousePressed(new PlayWordListener());
	}

	/**
	 * Generates a list cell for the cell factory of the list view.
	 * 
	 * @author Blair Pattison
	 *
	 */
	public class TileListCell extends ListCell<Tile> {
		public TileListCell() {
		}

		@Override
		protected void updateItem(Tile t, boolean bln) {
			super.updateItem(t, bln);
			if (t == null) {
				setText(null);
			}
			if (t != null) {
				setText(String.valueOf(t.getLetter()));
				setAccessibleText(String.valueOf(t.getLetter()));
			}
		}
	}

	/**
	 * Listener for when the user selects a tile from the tile rack.
	 * 
	 * @author Blair Pattison
	 *
	 */
	private class GameTileSelectedListener implements EventHandler<MouseEvent> {
		public void handle(MouseEvent me) {
			playedTiles.append(tilesDisplayed.getSelectionModel().selectedItemProperty().get());
			try {
				tiles.remove(tilesDisplayed.getSelectionModel().selectedItemProperty().get());
			} catch (TileNotInGroupException e) {
				return;
			}
			tilesDisplayed.getSelectionModel().clearSelection();
		}
	}

	/**
	 * Listener for when the user selects a tile from their own played word list
	 * view.
	 * 
	 * @author Blair Pattison
	 *
	 */
	private class YourTileSelectedListener implements EventHandler<MouseEvent> {
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
	}

	/**
	 * Listener for when the user pushes the reset button.
	 * 
	 * @author Blair Pattison
	 *
	 */
	private class ResetListener implements EventHandler<MouseEvent> {
		public void handle(MouseEvent me) {
			for (Tile current : playedTiles.tiles()) {
				tiles.append(current);
			}
			tilesDisplayed.setItems(tiles.tiles());
			playedTiles.clear();
		}
	}

	/**
	 * Listener for when the user attempts to play the word they have generated in
	 * their list view.
	 * 
	 * @author Blair Pattison
	 *
	 */
	private class PlayWordListener implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent me) {
			if (wordChecker.isValidWord(playedTiles.getHand())) {
				runningScore += playedTiles.getScore();
				playedTiles.clear();

				IntegerProperty integerProperty = new SimpleIntegerProperty(runningScore);
				scoreField.textProperty().bindBidirectional(integerProperty, new NumberStringConverter());

				try {
					BabbleController.this.fillTiles();
				} catch (TileRackFullException | EmptyTileBagException e) {
					return;
				}

			} else {
				Alert a = new Alert(AlertType.INFORMATION);
				a.setHeaderText("Error");
				a.setContentText("Invalid Word");
				a.show();

			}
		}
	}

	/**
	 * Helper method that refills the tile rack to the amount of tiles needed for
	 * each game.
	 * 
	 * @throws TileRackFullException
	 * @throws EmptyTileBagException
	 */
	private void fillTiles() throws TileRackFullException, EmptyTileBagException {
		int tilesNeeded = tiles.getNumberOfTilesNeeded();
		do {
			tiles.append(tileBag.drawTile());
			tilesNeeded--;
		} while (tilesNeeded != 0);
		tilesDisplayed.setItems(tiles.tiles());

	}

	/**
	 * Generates the cell factory for the your tiles list view and sets the tiles
	 * list.
	 */
	private void buildYourTilesDisplayed() {
		yourWordDisplayed.setItems(playedTiles.tiles());
		this.yourWordDisplayed.setCellFactory(new Callback<ListView<Tile>, ListCell<Tile>>() {
			@Override
			public ListCell<Tile> call(ListView<Tile> arg0) {
				return new TileListCell();
			}
		});

	}

	/**
	 * Generates the cell factory for the game tiles and sets the tiles list.
	 */
	private void buildGameTilesDisplayed() {
		this.tilesDisplayed.setItems(tiles.tiles());
		this.tilesDisplayed.setCellFactory(new Callback<ListView<Tile>, ListCell<Tile>>() {
			@Override
			public ListCell<Tile> call(ListView<Tile> arg0) {
				return new TileListCell();
			}
		});
	}

}
