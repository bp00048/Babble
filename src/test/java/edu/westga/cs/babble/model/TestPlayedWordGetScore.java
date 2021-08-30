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
public class TestPlayedWordGetScore {
	PlayedWord testPlayedWord;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
		this.testPlayedWord = new PlayedWord();
	}

	@Test
	public void emptyWordShouldHaveScoreOfZero() {
		assertEquals(this.testPlayedWord.getScore(), 0);
	}

	@Test
	public void scoreAOneTileWord() {
		Tile testTile = new Tile('E');
		this.testPlayedWord.append(testTile);
		assertEquals(this.testPlayedWord.getScore(), 1);
	}

	@Test
	public void scoreAWordWithMultipleDifferingTiles() {
		Tile testTileOne = new Tile('E');
		Tile testTileTwo = new Tile('X');
		this.testPlayedWord.append(testTileOne);
		this.testPlayedWord.append(testTileTwo);
		assertEquals(this.testPlayedWord.getScore(), 9);
	}

	@Test
	public void scoreAWordContainingDuplicateTiles() {
		Tile testTileOne = new Tile('E');
		Tile testTileTwo = new Tile('E');
		this.testPlayedWord.append(testTileOne);
		this.testPlayedWord.append(testTileTwo);
		assertEquals(this.testPlayedWord.getScore(), 2);
	}
}
