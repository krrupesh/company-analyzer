package company.analyzer;
public class Employee {
    private String id;
    private String firstName;
    private String lastName;
    private String managerId;
    private double salary;
    
    
    
    
	public Employee(String id, String firstName, String lastName, String managerId, double salary) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.managerId = managerId;
		this.salary = salary;
	}
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

    // Constructors, getters, and setters
    
    
}
