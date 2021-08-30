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
public class TestTileGroupRemove {
	TileGroupClass testTileGroup;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
		this.testTileGroup = new TileGroupClass();
	}

	@Test
	public void shouldNotAllowNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			this.testTileGroup.remove(null);
		});
	}

	@Test
	public void canNotRemoveFromEmptyTileGroup() {
		Tile testTile = new Tile('T');
		assertThrows(TileNotInGroupException.class, () -> {
			this.testTileGroup.remove(testTile);
		});
	}

	@Test
	public void canNotRemoveTileNotInTileGroup() {
		Tile testTile = new Tile('T');
		this.testTileGroup.append(testTile);
		Tile nonTestTile = new Tile('N');
		assertThrows(TileNotInGroupException.class, () -> {
			this.testTileGroup.remove(nonTestTile);
		});
	}

	@Test
	public void canRemoveOnlyTileInTileGroup() throws TileNotInGroupException {
		Tile testTile = new Tile('T');
		this.testTileGroup.append(testTile);
		this.testTileGroup.remove(testTile);
		assertTrue(this.testTileGroup.tiles().isEmpty());
	}

	@Test
	public void canRemoveFirstOfManyTilesFromTileGroup() throws TileNotInGroupException {
		Tile testTileOne = new Tile('T');
		Tile testTileTwo = new Tile('E');
		Tile testTileThree = new Tile('S');
		Tile testTileFour = new Tile('T');

		this.testTileGroup.append(testTileOne);
		this.testTileGroup.append(testTileTwo);
		this.testTileGroup.append(testTileThree);
		this.testTileGroup.append(testTileFour);

		this.testTileGroup.remove(testTileOne);

		assertEquals(this.testTileGroup.getHand(), "EST");
	}

	@Test
	public void canRemoveLastOfManyTilesFromTileGroup() throws TileNotInGroupException {
		Tile testTileOne = new Tile('T');
		Tile testTileTwo = new Tile('E');
		Tile testTileThree = new Tile('S');
		Tile testTileFour = new Tile('T');

		this.testTileGroup.append(testTileOne);
		this.testTileGroup.append(testTileTwo);
		this.testTileGroup.append(testTileThree);
		this.testTileGroup.append(testTileFour);

		this.testTileGroup.remove(testTileFour);

		assertEquals(this.testTileGroup.getHand(), "TES");
	}

	@Test
	public void canRemoveMiddleOfManyTilesFromTileGroup() throws TileNotInGroupException {
		Tile testTileOne = new Tile('T');
		Tile testTileTwo = new Tile('E');
		Tile testTileThree = new Tile('S');
		Tile testTileFour = new Tile('T');

		this.testTileGroup.append(testTileOne);
		this.testTileGroup.append(testTileTwo);
		this.testTileGroup.append(testTileThree);
		this.testTileGroup.append(testTileFour);

		this.testTileGroup.remove(testTileTwo);

		assertEquals(this.testTileGroup.getHand(), "TST");
	}

	@Test
	public void canRemoveMultipleTilesFromTileGroup() throws TileNotInGroupException {
		Tile testTileOne = new Tile('T');
		Tile testTileTwo = new Tile('E');
		Tile testTileThree = new Tile('S');
		Tile testTileFour = new Tile('T');

		this.testTileGroup.append(testTileOne);
		this.testTileGroup.append(testTileTwo);
		this.testTileGroup.append(testTileThree);
		this.testTileGroup.append(testTileFour);

		this.testTileGroup.remove(testTileOne);
		this.testTileGroup.remove(testTileThree);

		assertEquals(this.testTileGroup.getHand(), "ET");
	}

	@Test
	public void doesNotRemoveDuplicateTilesFromTileGroup() throws TileNotInGroupException {
		Tile testTileOne = new Tile('T');
		Tile testTileTwo = new Tile('E');
		Tile testTileThree = new Tile('S');
		Tile testTileFour = new Tile('T');

		this.testTileGroup.append(testTileOne);
		this.testTileGroup.append(testTileTwo);
		this.testTileGroup.append(testTileThree);
		this.testTileGroup.append(testTileFour);

		this.testTileGroup.remove(testTileTwo);
		this.testTileGroup.remove(testTileThree);

		assertEquals(this.testTileGroup.getHand(), "TT");
	}

	private class TileGroupClass extends TileGroup {

	}
}
