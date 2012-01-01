package se.kingharvest.infrastructure.data.types;

import java.io.Serializable;

public class PrimaryId extends Id implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6743594350335690487L;

	public PrimaryId(long id) {
		super(id);
	}

}
