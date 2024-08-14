package company.analyzer;

import java.io.IOException;

public class App {
    public static void main( String[] args ){
        String filePath = "../company-analyzer/input.csv";
        
        Company company = new Company();
        try {
            company.loadEmployees(filePath);
            EmployeeAnalyzer analyzer = new EmployeeAnalyzer(company);
            analyzer.analyzeSalaries();
            analyzer.analyzeReportingLines();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
