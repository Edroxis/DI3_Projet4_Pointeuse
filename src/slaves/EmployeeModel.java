package slaves;

public class EmployeeModel {
	private int id;
	private String name;
	
	public EmployeeModel(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public String toString() {
		return name;
	}
}
