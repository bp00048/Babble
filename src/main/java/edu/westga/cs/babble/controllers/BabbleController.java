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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;

/**
 * @author windy
 *
 */
public class BabbleController implements Initializable {
	@FXML private ListView<Tile> tilesDisplayed;
	@FXML private ListView<Tile> yourWordDisplayed;
	@FXML private Label testLabel;
	private TileRack tiles;
	private TileBag tileBag;
	private PlayedWord playedTiles;


	/**
	 * @throws EmptyTileBagException 
	 * @throws TileRackFullException 
	 * 
	 */
	public BabbleController() throws TileRackFullException, EmptyTileBagException {
	this.tilesDisplayed = new ListView<>();
	this.yourWordDisplayed = new ListView<>();
	this.testLabel = new Label();
	this.tileBag = new TileBag();
	this.tiles = new TileRack();
	this.playedTiles = new PlayedWord();
	int tilesNeeded = 0;
	do {
		this.tiles.append(this.tileBag.drawTile());
		tilesNeeded++;
	} while (tilesNeeded < 7);
	
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
						if(t == null) {
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
				
					e.printStackTrace();
				}
        	   
    	    }
    	});

		this.yourWordDisplayed.setCellFactory(new Callback<ListView<Tile>, ListCell<Tile>>() {

			@Override
			public ListCell<Tile> call(ListView<Tile> arg0) {

				ListCell<Tile> cell = new ListCell<Tile>() {

					@Override
					protected void updateItem(Tile t, boolean bln) {
						super.updateItem(t, bln);
						if(t == null) {
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
	
	    
	
	}
	
	}


