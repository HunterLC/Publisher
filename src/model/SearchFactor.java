package model;

public class SearchFactor {
	private int id;
	private String logicaloperator;
	private String field;
	private String operator;
	private String value;

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public String getLogicaloperator() {
		return logicaloperator;
	}

	public void setLogicaloperator(String logicaloperator) {
		this.logicaloperator = logicaloperator;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public SearchFactor() {

	}
}
