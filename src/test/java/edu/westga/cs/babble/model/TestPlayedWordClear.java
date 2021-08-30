/**
 * 
 */
package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Blair Pattison
 *
 */
public class TestPlayedWordClear {
	PlayedWord testPlayedWord;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
		this.testPlayedWord = new PlayedWord();
	}

	@Test
	public void shouldClearEmptyWord() {
		this.testPlayedWord.clear();
		assertTrue(this.testPlayedWord.tiles().isEmpty());
	}

	@Test
	public void shouldClearWordWithOneTile() {
		Tile testTile = new Tile('T');
		this.testPlayedWord.append(testTile);
		assertFalse(this.testPlayedWord.tiles().isEmpty());

		this.testPlayedWord.clear();
		assertTrue(this.testPlayedWord.tiles().isEmpty());
	}

	@Test
	public void shouldClearWordWithManyTiles() {
		Tile testTileOne = new Tile('T');
		Tile testTileTwo = new Tile('E');
		Tile testTileThree = new Tile('S');
		Tile testTileFour = new Tile('T');
		this.testPlayedWord.append(testTileOne);
		this.testPlayedWord.append(testTileTwo);
		this.testPlayedWord.append(testTileThree);
		this.testPlayedWord.append(testTileFour);
		assertFalse(this.testPlayedWord.tiles().isEmpty());

		this.testPlayedWord.clear();
		assertTrue(this.testPlayedWord.tiles().isEmpty());
	}

}
