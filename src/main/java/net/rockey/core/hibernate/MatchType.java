package net.rockey.core.hibernate;

public enum MatchType {
	/** equals. */
	EQ,
	
	/** like. */
	LIKE,
	
	/** less than. */
	LT,
	
	/** greater than. */
	GT,
	
	/** less equals. */
	LE,
	
	/** greater equals. */
	GE,
	
	/** in. */
	IN,
	
	/** unknown. */
	UNKNOWN;
}
