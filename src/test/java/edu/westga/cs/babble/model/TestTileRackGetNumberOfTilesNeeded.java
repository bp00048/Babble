/**
 * 
 */
package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author windy
 *
 */
public class TestTileRackGetNumberOfTilesNeeded {
	TileRack testTileRack;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
		this.testTileRack = new TileRack();
	}

	@Test
	public void emptyTileRackShouldNeedMaxSizeNumberOfTiles() {
		assertTrue(this.testTileRack.tiles().isEmpty());
		assertEquals(this.testTileRack.getNumberOfTilesNeeded(), 7);
	}

	@Test
	public void tileRackWithOneTileShouldNeedMaxSizeMinusOneTiles() {
		Tile testTile = new Tile('T');
		this.testTileRack.append(testTile);
		assertEquals(this.testTileRack.getNumberOfTilesNeeded(), 6);
	}

	@Test
	public void tileRackWithSeveralTilesShouldNeedSomeTiles() {
		Tile testTileOne = new Tile('T');
		Tile testTileTwo = new Tile('E');
		Tile testTileThree = new Tile('S');
		Tile testTileFour = new Tile('T');
		this.testTileRack.append(testTileOne);
		this.testTileRack.append(testTileTwo);
		this.testTileRack.append(testTileThree);
		this.testTileRack.append(testTileFour);

		assertEquals(this.testTileRack.getNumberOfTilesNeeded(), 3);
	}
	
	@Test
	public void fullRackNeedsZeroTiles() throws TileRackFullException, EmptyTileBagException {
		TileBag testTileBag = new TileBag();
		for (int i = 0; i < 7; i++) {
			this.testTileRack.append(testTileBag.drawTile());
		
	}
		assertEquals(this.testTileRack.getNumberOfTilesNeeded(), 0);
	}

}
