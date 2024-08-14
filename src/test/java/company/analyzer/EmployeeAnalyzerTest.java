package company.analyzer;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

public class EmployeeAnalyzerTest {
    private Company company;
    private EmployeeAnalyzer analyzer;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    @Before
    public void setUp() {
        company = new Company();
        analyzer = new EmployeeAnalyzer(company);

        // Capture the output
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Load test data
        setupTestData();
    }

    @After
    public void tearDown() {
        // Restore the original System.out
        System.setOut(originalOut);
    }

    private void setupTestData() {
        // Create Employees
        Employee ceo = new Employee("1", "John", "Doe", "", 120000);
        Employee manager1 = new Employee("2", "Jane", "Smith", "1", 104000);
        Employee manager2 = new Employee("3", "Emily", "Jones", "1", 95000);
        Employee level1_1 = new Employee("4", "Michael", "Brown", "2", 80000);
        Employee level1_2 = new Employee("5", "Susan", "Wilson", "2", 93000);
        Employee level2_1 = new Employee("6", "David", "Lee", "4", 65000);
        Employee level2_2 = new Employee("7", "Laura", "Green", "5", 77000);
        Employee level3_1 = new Employee("8", "Chris", "White", "6", 52000);
        Employee level3_2 = new Employee("9", "Nina", "Black", "7", 63000);
        Employee level4_1 = new Employee("10", "Nina", "Black", "9", 50000);

        // Add Employees to the Company
        company.getEmployees().put(ceo.getId(), ceo);
        company.getEmployees().put(manager1.getId(), manager1);
        company.getEmployees().put(manager2.getId(), manager2);
        company.getEmployees().put(level1_1.getId(), level1_1);
        company.getEmployees().put(level1_2.getId(), level1_2);
        company.getEmployees().put(level2_1.getId(), level2_1);
        company.getEmployees().put(level2_2.getId(), level2_2);
        company.getEmployees().put(level3_1.getId(), level3_1);
        company.getEmployees().put(level3_2.getId(), level3_2);
        company.getEmployees().put(level4_1.getId(), level4_1);

        // Define manager to subordinates mapping
        company.getManagerToSubordinates().put(ceo.getId(), Arrays.asList(manager1.getId(), manager2.getId()));
        company.getManagerToSubordinates().put(manager1.getId(), Arrays.asList(level1_1.getId(), level1_2.getId()));
        company.getManagerToSubordinates().put(manager2.getId(), new ArrayList<>()); // No direct subordinates

        company.getManagerToSubordinates().put(level1_1.getId(), Arrays.asList(level2_1.getId()));
        company.getManagerToSubordinates().put(level1_2.getId(), Arrays.asList(level2_2.getId()));

        company.getManagerToSubordinates().put(level2_1.getId(), Arrays.asList(level3_1.getId()));
        company.getManagerToSubordinates().put(level2_2.getId(), Arrays.asList(level3_2.getId()));
        company.getManagerToSubordinates().put(level3_2.getId(), Arrays.asList(level4_1.getId()));
        company.setCeoId(ceo.getId());
    }

    @Test
    public void testAnalyzeSalaries() {
        // Call the method under test
        analyzer.analyzeSalaries();

        // Capture the output
        String output = outputStream.toString().trim();

        System.out.println(output);
        
        // Expected output
        String expectedOutput = "Manager having Id 3 earns more than they should and by 95000.0";

        // Check if the output matches the expected output
       assertEquals(expectedOutput, output);
    }
    
    @Test
    public void testAnalyzeReportingLines() {
        // Call the method under test
        analyzer.analyzeReportingLines();

        // Capture the output
        String output = outputStream.toString().trim();

        // Expected output
        String expectedOutput = "Employee with Id 10 have a reporting line which is too long and by 1";

        // Check if the output matches the expected output
        assertEquals(expectedOutput, output);
    }
}
