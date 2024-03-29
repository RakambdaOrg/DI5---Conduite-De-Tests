package fr.mrcraftcod.conduitedetests.inputstrategy;

import fr.mrcraftcod.conduitedetests.InvalidInput;
import fr.mrcraftcod.conduitedetests.Pile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 2018-12-21.
 *
 * @author Thomas Couchoud
 * @since 2018-12-21
 */
class KbdInputPileTest{
	private KbdInputPile input;
	private Pile pile;
	
	@BeforeEach
	void setUp(){
		pile = new Pile();
		input = new KbdInputPile(pile);
	}
	
	@ParameterizedTest
	@ValueSource(ints = {
			1,
			2,
			3,
			15,
			25,
			50,
			100,
			1000
	})
	void actionCommandeInputPush(int toAdd) throws InvalidInput{
		input.actionCommandeInput(String.format("push %d", toAdd));
		assertEquals(1, pile.getSizeList());
		assertEquals(toAdd, pile.pop());
	}
	
	@Test
	void actionCommandeInputInvalidPush(){
		assertThrows(InvalidInput.class, () -> input.actionCommandeInput("push hello"));
		assertThrows(InvalidInput.class, () -> input.actionCommandeInput("push"));
	}
	
	@ParameterizedTest
	@ValueSource(ints = {
			1,
			2,
			3,
			15,
			25,
			50,
			100,
			1000
	})
	void actionCommandeInputPop(int toAdd) throws InvalidInput{
		pile.push(toAdd);
		assertEquals((Integer) toAdd, input.actionCommandeInput("pop"));
	}
	
	@ParameterizedTest
	@ValueSource(ints = {
			1,
			2,
			3,
			15,
			25,
			50,
			100,
			1000
	})
	void actionCommandeInputClear(int toAdd) throws InvalidInput{
		for(int i = 0; i < toAdd; i++){
			pile.push(i);
		}
		assertEquals((Integer) toAdd, input.actionCommandeInput("clear"));
		assertTrue(pile.isEmpty());
	}
	
	@Test
	void actionCommandeInputInvalidClear(){
		assertThrows(InvalidInput.class, () -> input.actionCommandeInput("clear hello"));
	}
	
	@Test
	void actionCommandeInputInvalid(){
		assertThrows(InvalidInput.class, () -> input.actionCommandeInput(null));
	}
	
	@Test
	void actionCommandeInputInvalidPop(){
		pile.push(10);
		assertThrows(InvalidInput.class, () -> input.actionCommandeInput("pop hello"));
	}
	
	@Test
	void actionCommandeInputInvalidEmptyPop(){
		assertThrows(InvalidInput.class, () -> input.actionCommandeInput("pop hello"));
	}
	
	@Test
	void actionCommandeInputPopEmpty(){
		assertThrows(ArrayIndexOutOfBoundsException.class, () -> input.actionCommandeInput("pop"));
	}
	
	@Test
	void actionCommandeUnknownCommand(){
		assertThrows(InvalidInput.class, () -> input.actionCommandeInput("i'm invalid"));
	}
}