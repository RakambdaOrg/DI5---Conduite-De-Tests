package fr.mrcraftcod.conduitedetests;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 2018-12-21.
 *
 * @author Thomas Couchoud
 * @since 2018-12-21
 */
public class InvalidInput extends Exception{
	public InvalidInput(String message){
		super(message);
	}
	
	public InvalidInput(String message, Throwable cause){
		super(message, cause);
	}
}
