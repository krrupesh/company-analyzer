package company.analyzer;
import java.util.*;
import java.io.*;
import org.apache.commons.csv.*;

public class Company {
    private Map<String, Employee> employees = new HashMap<>();
    private Map<String, List<String>> managerToSubordinates = new HashMap<>();
    private String ceoId;

    public void loadEmployees(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {
            
            for (CSVRecord record : csvParser) {
                // Create a new Employee object with updated fields
                Employee employee = new Employee();
                employee.setId(record.get("Id"));
                employee.setFirstName(record.get("firstName"));
                employee.setLastName(record.get("lastName"));
                employee.setManagerId(record.get("managerId"));
                employee.setSalary(Double.parseDouble(record.get("salary")));
                
                employees.put(employee.getId(), employee);
                
                // Determine if the employee is the CEO
                if (employee.getManagerId().isEmpty()) {
                    ceoId = employee.getId();
                } else {
                    // Map manager to their list of subordinates
                    managerToSubordinates.computeIfAbsent(employee.getManagerId(), k -> new ArrayList<>()).add(employee.getId());
                }
            }
        }
    }
    
    public Map<String, Employee> getEmployees() {
        return employees;
    }
    
    public Map<String, List<String>> getManagerToSubordinates() {
        return managerToSubordinates;
    }

    public String getCeoId() {
        return ceoId;
    }
    
    public void setCeoId(String ceoId) {
         this.ceoId = ceoId;
    }
    
    public Map<String, Integer> calculateEmployeeDepths() {
        Map<String, Integer> depths = new HashMap<>();
        if (ceoId == null) return depths; // No CEO found
        
        Queue<String> queue = new LinkedList<>();
        queue.add(ceoId);
        depths.put(ceoId, 0);
        
        while (!queue.isEmpty()) {
            String managerId = queue.poll();
            int currentDepth = depths.get(managerId);
            
            List<String> subordinates = managerToSubordinates.get(managerId);
            if (subordinates != null) {
                for (String subordinateId : subordinates) {
                    if (!depths.containsKey(subordinateId)) { // Not visited
                        queue.add(subordinateId);
                        depths.put(subordinateId, currentDepth + 1);
                    }
                }
            }
        }
        
        return depths;
    }
}
