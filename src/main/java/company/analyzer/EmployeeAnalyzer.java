package company.analyzer;
import java.util.*;

public class EmployeeAnalyzer {
    private Company company;

    public EmployeeAnalyzer(Company company) {
        this.company = company;
    }

    public void analyzeSalaries() {
        Map<String, Employee> employees = company.getEmployees();
        Map<String, List<String>> managerToSubordinates = company.getManagerToSubordinates();

        for (Map.Entry<String, List<String>> entry : managerToSubordinates.entrySet()) {
            String managerId = entry.getKey();
            List<String> subordinates = entry.getValue();
            
            double totalSalary = 0;
            int count = subordinates.size();
            for (String subordinateId : subordinates) {
                totalSalary += employees.get(subordinateId).getSalary();
            }
            
            double averageSalary = count > 0 ? totalSalary / count : 0;
            Employee manager = employees.get(managerId);
            double managerSalary = manager.getSalary();
            
            if (managerSalary < averageSalary * 1.2) {
                System.out.println("Manager having Id "+manager.getId() +" earns less than they should and by "+
                                  (averageSalary * 1.2 - managerSalary));
            } else if (managerSalary > averageSalary * 1.5) {
                System.out.println("Manager having Id "+manager.getId() +" earns more than they should and by "+
                        (managerSalary - averageSalary * 1.5));
            }
        }
    }

    public void analyzeReportingLines() {
        // Implement logic to find employees with too long reporting lines
    	Map<String, Integer> depth = company.calculateEmployeeDepths();
    	for(Map.Entry<String, Integer> entry: depth.entrySet()){
    		if(entry.getValue() > 4){
    			System.out.println("Employee with Id "+entry.getKey()+" have a reporting line which is too long and by "+ (entry.getValue()-4));
    		}
    	}
    }
}
