/**
 * 
 */
package com.lightbend.akka.tp3.message;

/**
 * 
 * @author Celine, Leane et Marine
 *
 */
public class Message {

	private int id;
	private String message;

	/**
	 * Message to be send
	 * 
	 * @param id      the id of the message
	 * @param message the message
	 */
	public Message(int id, String message) {
		this.id = id;
		this.message = message;
	}

	/**
	 * Get the id of the message
	 * 
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Get the message
	 * 
	 * @return the message
	 */
	public String getMessage() {
		return this.message;
	}

}
