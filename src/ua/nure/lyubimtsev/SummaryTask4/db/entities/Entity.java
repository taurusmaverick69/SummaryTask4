package ua.nure.lyubimtsev.SummaryTask4.db.entities;

import java.io.Serializable;

/**
 * Root of all entities which have identifier field.
 * 
 * @author V.Lyubimtsev
 * 
 */
public abstract class Entity implements Serializable {

	protected int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
