package core;

import java.io.FileNotFoundException;

import org.junit.Test;

import junit.framework.TestCase;

public class File_Test extends TestCase{
	@Test
    public void testFilePlay() {
        GameController game = new GameController();

        game.dealer = new AIDealer();
        game.human = new HumanPlayer();
        
        System.out.println("Testing File Input test for file1");
        
        String fileName = "src/test/resources/testFile1.txt";
        
        // test cases
        try {
			game.filePlay(fileName, game.gameDeck);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        assertEquals(game.dealerWin, true);
    }

    @Test
    public void testFilePlay2() {
        GameController game = new GameController();

        game.dealer = new AIDealer();
        game.human = new HumanPlayer();
        
        System.out.println("Testing File Input test for file2");
        
        String fileName = "src/test/resources/testFile2.txt";
        
        // test cases
        try {
			game.filePlay(fileName, game.gameDeck);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        assertEquals(game.dealerWin, false);
    }
    
    @Test
    public void testFilePlay3() {
        GameController game = new GameController();

        game.dealer = new AIDealer();
        game.human = new HumanPlayer();
        
        System.out.println("Testing File Input test for file3");
        
        String fileName = "src/test/resources/testFile3.txt";
        
        // test cases
        try {
			game.filePlay(fileName, game.gameDeck);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        assertEquals(game.dealerWin, false);
    }
}
