package pkgGame;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import pkgCore.Card;
import pkgCore.GamePlay;
import pkgCore.HandPoker;
import pkgCore.Player;
import pkgCore.Rule;
import pkgCore.Table;
import pkgEnum.eGame;
import pkgEnum.eRank;
import pkgEnum.eSuit;
import pkgException.DeckException;
import pkgException.HandException;
import pkgHelper.GamePlayHelper;
import pkgHelper.HandPokerHelper;

public class GamePlayTest {

	@Test
	public void GamePlay_Test1() {
		
		Table t = new Table("Table 1");
		
		Player p1 = new Player("Bert");
		Player p2 = new Player("Joe");
		
		t.AddPlayerToTable(p1);
		t.AddPlayerToTable(p2);

		Rule rle = new Rule(eGame.TexasHoldEm);
		GamePlay gp = new GamePlay(t, rle);
		
		
		try {
			gp.StartGame();
		} catch (DeckException e1) {
			
			e1.printStackTrace();
		} catch (HandException e1) {
			
			e1.printStackTrace();
		}
		
		
		HandPoker hp1 = gp.GetPlayersHand(p1);		
		HandPoker hp2 = gp.GetPlayersHand(p2);
		
		
		ArrayList<Card> p1Cards = new ArrayList<Card>();
		p1Cards.add(new Card(eSuit.HEARTS, eRank.ACE));
		p1Cards.add(new Card(eSuit.HEARTS, eRank.ACE));
		
		ArrayList<Card> p2Cards = new ArrayList<Card>();
		p2Cards.add(new Card(eSuit.CLUBS, eRank.ACE));
		p2Cards.add(new Card(eSuit.CLUBS, eRank.ACE));
		
		ArrayList<Card> commonCards = new ArrayList<Card>();
		commonCards.add(new Card(eSuit.HEARTS, eRank.THREE));
		commonCards.add(new Card(eSuit.HEARTS, eRank.FOUR));
		commonCards.add(new Card(eSuit.HEARTS, eRank.FIVE));
		commonCards.add(new Card(eSuit.CLUBS, eRank.TEN));	
		commonCards.add(new Card(eSuit.CLUBS, eRank.QUEEN));
		
		gp = GamePlayHelper.setCommonCards(gp,  commonCards);
		
		hp1 = HandPokerHelper.SetHand(p1Cards, hp1);
		hp2 = HandPokerHelper.SetHand(p2Cards, hp2);
		
		gp = GamePlayHelper.PutGamePlay(gp, p1.getPlayerID(), hp1);		
		gp = GamePlayHelper.PutGamePlay(gp, p2.getPlayerID(), hp2);
		
		try {
			gp.EvaluateGameHands();
		} catch (HandException e) {
			fail("Evaluate hands failed");
		}

		ArrayList<Player> pWinner = gp.GetGameWinners();

		
		assertTrue(pWinner.contains(p1));
		
		
		
	}
	
	
	
	
	
	
	@Test
	public void GamePlay_Test2() {
		
		Table t = new Table("Table 1");
		
		Player p1 = new Player("Bert");
		Player p2 = new Player("Joe");
		
		t.AddPlayerToTable(p1);
		t.AddPlayerToTable(p2);

		Rule rle = new Rule(eGame.TexasHoldEm);
		GamePlay gp = new GamePlay(t, rle);
		
		try {
			gp.StartGame();
		} catch (DeckException e1) {
			
			e1.printStackTrace();
		} catch (HandException e1) {
			
			e1.printStackTrace();
		}
		
		
		HandPoker hp1 = gp.GetPlayersHand(p1);		
		HandPoker hp2 = gp.GetPlayersHand(p2);
		
		
		ArrayList<Card> p1Cards = new ArrayList<Card>();
		p1Cards.add(new Card(eSuit.DIAMONDS, eRank.ACE));
		p1Cards.add(new Card(eSuit.DIAMONDS, eRank.TWO));
		
		ArrayList<Card> p2Cards = new ArrayList<Card>();
		p2Cards.add(new Card(eSuit.CLUBS, eRank.ACE));
		p2Cards.add(new Card(eSuit.CLUBS, eRank.TWO));
		
		ArrayList<Card> commonCards = new ArrayList<Card>();
		commonCards.add(new Card(eSuit.HEARTS, eRank.THREE));
		commonCards.add(new Card(eSuit.HEARTS, eRank.FOUR));
		commonCards.add(new Card(eSuit.HEARTS, eRank.FIVE));
		commonCards.add(new Card(eSuit.CLUBS, eRank.TEN));	
		commonCards.add(new Card(eSuit.CLUBS, eRank.QUEEN));	
		
		gp = GamePlayHelper.setCommonCards(gp,  commonCards);
	
		hp1 = HandPokerHelper.SetHand(p1Cards, hp1);
		hp2 = HandPokerHelper.SetHand(p2Cards, hp2);
		
		gp = GamePlayHelper.PutGamePlay(gp, p1.getPlayerID(), hp1);		
		gp = GamePlayHelper.PutGamePlay(gp, p2.getPlayerID(), hp2);
		
		
		try {
			gp.EvaluateGameHands();
		} catch (HandException e) {
			fail("Evaluate hands failed");
		}

		ArrayList<Player> pWinner = null;
		pWinner = gp.GetGameWinners();
		
		
		assertTrue(pWinner.contains(p1));
		assertTrue(pWinner.contains(p2));
		
	}
	
	
	

}
