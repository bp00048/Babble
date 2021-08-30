/**
 * 
 */
package edu.westga.cs.babble.model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author windy
 *
 */
public class TestTileConstructor {
	Tile testTile;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {

	}

	@Test
	public void shouldNotAllowNonLetters() {
		assertThrows(IllegalArgumentException.class, () -> {
			this.testTile = new Tile('7');
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			this.testTile = new Tile('!');
		});
		


	}

	@Test
	public void shouldCreateOnePointTiles() {

		Tile testTileLowerE = new Tile('e');
		Tile testTileUpperE = new Tile('E');
		Tile testTileLowerA = new Tile('a');
		Tile testTileUpperA = new Tile('A');
		Tile testTileLowerI = new Tile('i');
		Tile testTileUpperI = new Tile('I');
		Tile testTileLowerO = new Tile('o');
		Tile testTileUpperO = new Tile('O');
		Tile testTileLowerN = new Tile('n');
		Tile testTileUpperN = new Tile('N');
		Tile testTileLowerR = new Tile('r');
		Tile testTileUpperR = new Tile('R');
		Tile testTileLowerT = new Tile('t');
		Tile testTileUpperT = new Tile('T');
		Tile testTileLowerL = new Tile('l');
		Tile testTileUpperL = new Tile('L');
		Tile testTileLowerS = new Tile('s');
		Tile testTileUpperS = new Tile('S');
		Tile testTileLowerU = new Tile('u');
		Tile testTileUpperU = new Tile('U');

		assertEquals(testTileLowerE.getPointValue(), 1);
		assertEquals(testTileUpperE.getPointValue(), 1);
		assertEquals(testTileLowerA.getPointValue(), 1);
		assertEquals(testTileUpperA.getPointValue(), 1);
		assertEquals(testTileLowerI.getPointValue(), 1);
		assertEquals(testTileUpperI.getPointValue(), 1);
		assertEquals(testTileLowerO.getPointValue(), 1);
		assertEquals(testTileUpperO.getPointValue(), 1);
		assertEquals(testTileLowerN.getPointValue(), 1);
		assertEquals(testTileUpperN.getPointValue(), 1);
		assertEquals(testTileLowerR.getPointValue(), 1);
		assertEquals(testTileUpperR.getPointValue(), 1);
		assertEquals(testTileLowerT.getPointValue(), 1);
		assertEquals(testTileUpperT.getPointValue(), 1);
		assertEquals(testTileLowerL.getPointValue(), 1);
		assertEquals(testTileUpperL.getPointValue(), 1);
		assertEquals(testTileLowerS.getPointValue(), 1);
		assertEquals(testTileUpperS.getPointValue(), 1);
		assertEquals(testTileLowerU.getPointValue(), 1);
		assertEquals(testTileUpperU.getPointValue(), 1);

	}

	@Test
	public void shouldCreateTwoPointTiles() {

		Tile testTileLowerD = new Tile('d');
		Tile testTileUpperD = new Tile('D');
		Tile testTileLowerG = new Tile('g');
		Tile testTileUpperG = new Tile('G');

		assertEquals(testTileLowerD.getPointValue(), 2);
		assertEquals(testTileUpperD.getPointValue(), 2);
		assertEquals(testTileLowerG.getPointValue(), 2);
		assertEquals(testTileUpperG.getPointValue(), 2);

	}

	@Test
	public void shouldCreateThreePointTiles() {

		Tile testTileLowerB = new Tile('b');
		Tile testTileUpperB = new Tile('B');
		Tile testTileLowerC = new Tile('c');
		Tile testTileUpperC = new Tile('C');
		Tile testTileLowerM = new Tile('m');
		Tile testTileUpperM = new Tile('M');
		Tile testTileLowerP = new Tile('p');
		Tile testTileUpperP = new Tile('P');

		assertEquals(testTileLowerB.getPointValue(), 3);
		assertEquals(testTileUpperB.getPointValue(), 3);
		assertEquals(testTileLowerC.getPointValue(), 3);
		assertEquals(testTileUpperC.getPointValue(), 3);
		assertEquals(testTileLowerM.getPointValue(), 3);
		assertEquals(testTileUpperM.getPointValue(), 3);
		assertEquals(testTileLowerP.getPointValue(), 3);
		assertEquals(testTileUpperP.getPointValue(), 3);

	}

	@Test
	public void shouldCreateFourPointTiles() {

		Tile testTileLowerF = new Tile('f');
		Tile testTileUpperF = new Tile('F');
		Tile testTileLowerH = new Tile('h');
		Tile testTileUpperH = new Tile('H');
		Tile testTileLowerV = new Tile('v');
		Tile testTileUpperV = new Tile('V');
		Tile testTileLowerW = new Tile('w');
		Tile testTileUpperW = new Tile('W');
		Tile testTileLowerY = new Tile('y');
		Tile testTileUpperY = new Tile('Y');

		assertEquals(testTileLowerF.getPointValue(), 4);
		assertEquals(testTileUpperF.getPointValue(), 4);
		assertEquals(testTileLowerH.getPointValue(), 4);
		assertEquals(testTileUpperH.getPointValue(), 4);
		assertEquals(testTileLowerV.getPointValue(), 4);
		assertEquals(testTileUpperV.getPointValue(), 4);
		assertEquals(testTileLowerW.getPointValue(), 4);
		assertEquals(testTileUpperW.getPointValue(), 4);
		assertEquals(testTileLowerY.getPointValue(), 4);
		assertEquals(testTileUpperY.getPointValue(), 4);

	}

	@Test
	public void shouldCreateFivePointTiles() {

		Tile testTileLowerK = new Tile('k');
		Tile testTileUpperK = new Tile('K');

		assertEquals(testTileLowerK.getPointValue(), 5);
		assertEquals(testTileUpperK.getPointValue(), 5);

	}

	@Test
	public void shouldCreateEightPointTiles() {

		Tile testTileLowerJ = new Tile('j');
		Tile testTileUpperJ = new Tile('J');
		Tile testTileLowerX = new Tile('x');
		Tile testTileUpperX = new Tile('X');

		assertEquals(testTileLowerJ.getPointValue(), 8);
		assertEquals(testTileUpperJ.getPointValue(), 8);
		assertEquals(testTileLowerX.getPointValue(), 8);
		assertEquals(testTileUpperX.getPointValue(), 8);

	}
	
	@Test
	public void shouldCreateTenPointTiles() {

		Tile testTileLowerQ = new Tile('q');
		Tile testTileUpperQ = new Tile('Q');
		Tile testTileLowerZ = new Tile('z');
		Tile testTileUpperZ = new Tile('Z');

		assertEquals(testTileLowerQ.getPointValue(), 10);
		assertEquals(testTileUpperQ.getPointValue(), 10);
		assertEquals(testTileLowerZ.getPointValue(), 10);
		assertEquals(testTileUpperZ.getPointValue(), 10);

	}

}
