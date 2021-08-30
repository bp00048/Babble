/**
 * 
 */
package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Blair Pattison
 *
 */
public class TestTileRackAppend {
	TileRack testTileRack;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
		this.testTileRack = new TileRack();

	}

	@Test
	public void shouldNotAppendToFullRack() throws TileRackFullException, EmptyTileBagException {
		for (int i = 0; i < 7; i++) {
			Tile testTile = new Tile('A');
			this.testTileRack.append(testTile);
		}
		assertEquals(this.testTileRack.tiles().size(), 7);
		assertEquals(this.testTileRack.getHand(), "AAAAAAA");
		assertEquals(this.testTileRack.getNumberOfTilesNeeded(), 0);

		assertThrows(TileRackFullException.class, () -> {
			Tile testTileTwo = new Tile('T');
			this.testTileRack.append(testTileTwo);
		});
		assertEquals(this.testTileRack.tiles().size(), 7);
		assertEquals(this.testTileRack.getHand(), "AAAAAAA");
	}

}
