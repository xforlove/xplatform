package net.rockey.core.util;

public enum SequencePrefix {

	/**************************************************
	 * ORG_ : 				1000
	 * ORG_DEPARTMENT : 	1001
	 * ORG_EMPLOYEE : 		1002
	 * ORG_POSITION : 		1003
	 * ORG_TEAM : 			1004
	 ************************************************/
	SEQ_FISRT_POSITION_FOR_ORG_DEPARTMENT("1001"),
	SEQ_FISRT_POSITION_FOR_ORG_EMPLOYEE("1002"),
	SEQ_FISRT_POSITION_FOR_ORG_POSITION("1003"),
	SEQ_FISRT_POSITION_FOR_ORG_TEAM("1004"),


	/**************************************************
	 * AUTH_ : 				1100
	 * AUTH_ROLE : 			1101
	 * AUTH_USER : 			1102
	 * AUTH_PARTYRES : 		1103
	 ************************************************/
	SEQ_FISRT_POSITION_FOR_AUTH_ROLE("1101"),
	SEQ_FISRT_POSITION_FOR_AUTH_USER("1102"),
	SEQ_FISRT_POSITION_FOR_AUTH_PARTYRES("1103"),
	
	
	/**************************************************
	 * APP_ : 				1200
	 * APP_FUNCGROUP : 		1201
	 * APP_FUNCTION : 		1202
	 * APP_MENU : 			1203
	 ************************************************/
	SEQ_FISRT_POSITION_FOR_APP_FUNCGROUP("1201"),
	SEQ_FISRT_POSITION_FOR_APP_FUNCTION("1202"),
	SEQ_FISRT_POSITION_FOR_APP_MENU("1203");

	
	// ~ ================================================
	
	private final String name;

	private SequencePrefix(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
