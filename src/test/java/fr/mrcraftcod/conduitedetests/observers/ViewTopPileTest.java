package fr.mrcraftcod.conduitedetests.observers;

import fr.mrcraftcod.conduitedetests.Pile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 2018-12-21.
 *
 * @author Thomas Couchoud
 * @since 2018-12-21
 */
class ViewTopPileTest{
	private ViewTopPile top;
	private Pile pile;
	private ByteArrayOutputStream bos;
	
	@BeforeEach
	void setUp(){
		bos = new ByteArrayOutputStream();
		
		pile = new Pile();
		top = new ViewTopPile(2, bos);
		pile.addObserver(top);
	}
	
	@AfterEach
	void tearDown(){
		top.close();
	}
	
	@Test
	void pop(){
		pile.push(2);
		pile.push(2);
		bos.reset();
		pile.pop();
		assertEquals("popped 2, top stack is 2" + System.getProperty("line.separator"), bos.toString());
	}
	
	@Test
	void popOverLimit(){
		pile.push(2);
		pile.push(4);
		pile.push(6);
		pile.push(8);
		bos.reset();
		pile.pop();
		assertEquals("popped 8, top stack is 4, 6" + System.getProperty("line.separator"), bos.toString());
		bos.reset();
		pile.pop();
		assertEquals("popped 6, top stack is 2, 4" + System.getProperty("line.separator"), bos.toString());
		bos.reset();
		pile.pop();
		assertEquals("popped 4, top stack is 2" + System.getProperty("line.separator"), bos.toString());
	}
	
	@Test
	void popNothing(){
		pile.push(2);
		bos.reset();
		pile.pop();
		assertEquals("popped 2, top stack is empty" + System.getProperty("line.separator"), bos.toString());
	}
	
	@Test
	void unknownEvent(){
		top.update(pile, new Object());
		assertEquals("unknown event" + System.getProperty("line.separator"), bos.toString());
	}
}