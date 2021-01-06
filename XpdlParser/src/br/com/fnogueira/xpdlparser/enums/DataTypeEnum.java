package br.com.fnogueira.xpdlparser.enums;

public enum DataTypeEnum {
	
	CHAR("C","char"),
	TEXT("T","String"),
	DECIMAL("D","float"),
	INTEGER("I","int"),
	BOOLEAN("B","boolean");	
	
	private String key;
	private String value;
	
	
	public static String getDataType(String key){
		String result = "";
		boolean ok = false;
		for (DataTypeEnum item : DataTypeEnum.values()) {
			if(key.equalsIgnoreCase(item.getKey())){
				result = item.getValue();
				ok = true;
				break;
			}
		}
		if(ok)
			return result;
		else
			return key;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private DataTypeEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
}
