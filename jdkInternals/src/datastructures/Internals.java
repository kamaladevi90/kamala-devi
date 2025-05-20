package datastructures;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Internals {
	
    static class Employee {
        private String name;
        private String department;
        private int salary;

        public Employee(String name, String department, int salary) {
            this.name = name;
            this.department = department;
            this.salary = salary;
        }
	
        public String getName() {
            return name;
        }

        public String getDepartment() {
            return department;
        }
        
        public Integer getSalary() {
            return salary;
        }

        @Override
        public String toString() {
            return name + " (" + department + ")";
        }
    }
		
		public static void main(String[] args) {
	        // Sample list of employees
	        List<Employee> employees = Arrays.asList(
	            new Employee("Alice", "HR",10000),
	            new Employee("Bob", "Finance",20000),
	            new Employee("Charlie", "HR",30000),
	            new Employee("David", "Engineering",40000),
	            new Employee("Eve", "Finance",50000)
	        );

	        // Group employees by department
	        Map<String, List<Employee>> groupByDept = employees.stream()
	                .collect(Collectors.groupingBy(Employee::getDepartment));

	        // Print the grouped employees
	        groupByDept.forEach((department, empList) -> {
	            System.out.println(department + ":");
	            empList.forEach(e -> System.out.println("  " + e));
	        });
	        
	        //second highest salary
	        // Step 1: Get distinct salaries in descending order
	        List<Integer> distinctSalariesDesc = employees.stream()
	            .map(Employee::getSalary)
	            .distinct()
	            .sorted(Comparator.reverseOrder())
	            .collect(Collectors.toList());
	        
	        
	        //Priority Queue
	        //PriorityQueue does not maintain sorted order during iteration or stream traversal — only the head (first element) is guaranteed to be the lowest (or highest) based on the comparator.
	        PriorityQueue<Employee> pq = new PriorityQueue<>(Comparator.comparingInt(emp -> emp.salary));

	        pq.offer(new Employee("Alice","HR", 7000));
	        pq.offer(new Employee("Bob","Finance", 5000));
	        pq.offer(new Employee("Charlie","HR", 6000));

	        while (!pq.isEmpty()) {
	            System.out.println(pq.poll().getName());
	        }
	        
	        System.out.println("Test");
	        pq.stream().collect(Collectors.groupingBy(Employee :: getDepartment)).forEach((dept,emplist) -> {
	        	System.out.println(dept + ":");
	        	emplist.forEach(e -> System.out.println("  " + e));	
	        });
	        System.out.println("Test1");
	    }

}
