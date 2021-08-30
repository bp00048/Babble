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
class TestTileRackAppend {
	TileRack testTileRack;
	TileBag testTileBag;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		this.testTileRack = new TileRack();
		this.testTileBag = new TileBag();
	}

	@Test
	public void shouldNotAppendToFullRack() throws TileRackFullException, EmptyTileBagException {
		for (int i = 0; i < 7; i++) {
			this.testTileRack.append(this.testTileBag.drawTile());
		}

		assertThrows(TileRackFullException.class, () -> {
			this.testTileRack.append(this.testTileBag.drawTile());
		});
	}
}
