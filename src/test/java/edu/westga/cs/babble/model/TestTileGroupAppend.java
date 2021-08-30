/**
 * 
 */
package edu.westga.cs.babble.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Blair Pattison
 *
 */
public class TestTileGroupAppend {
	TileGroupClass testTileGroup;
	TileBag testTileBag;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
		this.testTileGroup = new TileGroupClass();
		this.testTileBag = new TileBag();
	}

	@Test
	public void shouldNotAllowNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			this.testTileGroup.append(null);
		});
		assertEquals(this.testTileGroup.tiles().size(), 0);
		assertEquals(this.testTileGroup.getHand(), "");
	}

	@Test
	public void emptyGroupShouldBeEmpty() {
		assertTrue(this.testTileGroup.tiles().isEmpty());
	}

	@Test
	public void shouldHaveOneTileInTileGroup() throws EmptyTileBagException {

		Tile testTile = this.testTileBag.drawTile();
		this.testTileGroup.append(testTile);
		assertFalse(this.testTileGroup.tiles().isEmpty());
		assertEquals(this.testTileGroup.tiles().get(0), testTile);
		assertEquals(this.testTileGroup.tiles().size(), 1);
	}

	@Test
	public void shouldHaveManyTilesInTileGroup() throws EmptyTileBagException {

		Tile testTileOne = this.testTileBag.drawTile();
		Tile testTileTwo = this.testTileBag.drawTile();
		Tile testTileThree = this.testTileBag.drawTile();
		Tile testTileFour = this.testTileBag.drawTile();

		this.testTileGroup.append(testTileOne);
		this.testTileGroup.append(testTileTwo);
		this.testTileGroup.append(testTileThree);
		this.testTileGroup.append(testTileFour);

		assertFalse(this.testTileGroup.tiles().isEmpty());
		assertEquals(this.testTileGroup.tiles().get(0), testTileOne);
		assertEquals(this.testTileGroup.tiles().get(1), testTileTwo);
		assertEquals(this.testTileGroup.tiles().get(2), testTileThree);
		assertEquals(this.testTileGroup.tiles().get(3), testTileFour);
		assertEquals(this.testTileGroup.tiles().size(), 4);
	}

	@Test
	public void shouldHaveManyTilesIncludingDuplicatesInTileGroup() throws EmptyTileBagException {

		Tile testTileOne = new Tile('A');
		Tile testTileTwo = new Tile('A');

		Tile testTileThree = this.testTileBag.drawTile();
		Tile testTileFour = this.testTileBag.drawTile();

		this.testTileGroup.append(testTileOne);
		this.testTileGroup.append(testTileTwo);
		this.testTileGroup.append(testTileThree);
		this.testTileGroup.append(testTileFour);

		assertFalse(this.testTileGroup.tiles().isEmpty());
		assertEquals(this.testTileGroup.tiles().get(0), testTileOne);
		assertEquals(this.testTileGroup.tiles().get(1), testTileTwo);
		assertEquals(this.testTileGroup.tiles().get(2), testTileThree);
		assertEquals(this.testTileGroup.tiles().get(3), testTileFour);
		assertTrue(this.testTileGroup.tiles().size() == 4);
	}

	@Test
	public void canNotAddSameTileTwice() throws EmptyTileBagException {

		Tile testTileOne = new Tile('A');
		this.testTileGroup.append(testTileOne);

		assertThrows(IllegalArgumentException.class, () -> {
			this.testTileGroup.append(testTileOne);
		});

		assertEquals(this.testTileGroup.tiles().get(0), testTileOne);
		assertEquals(this.testTileGroup.tiles().size(), 1);

	}

	private class TileGroupClass extends TileGroup {

	}

}
