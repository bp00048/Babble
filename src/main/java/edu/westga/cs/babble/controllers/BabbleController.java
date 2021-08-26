/**
 * 
 */
package edu.westga.cs.babble.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import edu.westga.cs.babble.model.EmptyTileBagException;
import edu.westga.cs.babble.model.Tile;
import edu.westga.cs.babble.model.TileBag;
import edu.westga.cs.babble.model.TileRack;
import edu.westga.cs.babble.model.TileRackFullException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;

/**
 * @author windy
 *
 */
public class BabbleController implements Initializable {
	@FXML private ListView<Tile> tilesDisplayed;
	@FXML private Label testLabel;
	private TileRack tiles;
	private TileBag tileBag;


	/**
	 * @throws EmptyTileBagException 
	 * @throws TileRackFullException 
	 * 
	 */
	public BabbleController() throws TileRackFullException, EmptyTileBagException {
	this.tilesDisplayed = new ListView<>();
	this.testLabel = new Label();
	this.tileBag = new TileBag();
	this.tiles = new TileRack();
	int tilesNeeded = 0;
	do {
		this.tiles.append(this.tileBag.drawTile());
		tilesNeeded++;
	} while (this.tiles.getNumberOfTilesNeeded() > tilesNeeded);
	
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.testLabel.setText("test successful");
	
        ObservableList<Tile> data = FXCollections.observableList(tiles.tiles());
        this.tilesDisplayed.setItems(data);

		this.tilesDisplayed.setCellFactory(new Callback<ListView<Tile>, ListCell<Tile>>() {

			@Override
			public ListCell<Tile> call(ListView<Tile> arg0) {

				ListCell<Tile> cell = new ListCell<Tile>() {

					@Override
					protected void updateItem(Tile t, boolean bln) {
						super.updateItem(t, bln);
						if (t != null) {
							setText(String.valueOf(t.getLetter()));
						}
					}

				};

				return cell;
			}
		});

	}
	
	}


