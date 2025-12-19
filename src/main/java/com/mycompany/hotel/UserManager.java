package com.mycompany.hotel;
import java.io.*;
import java.util.HashMap;


public class UserManager {
    private HashMap<Integer, Employee> employees = new HashMap<>();
    private HashMap<Integer, Customers> customers = new HashMap<>();
    // Use project-relative paths that match the actual layout: src/main/data/...
    private final String customersData = "src/main/java/data/customerdata.txt";
    private final String employeesData = "src/main/java/data/employeesdata.txt";

    public UserManager() {
        // Load existing data if files are present
        loadEmployeesFromFile();
        loadCustomersFromFile();
    }
    public void AddEmployee (Employee employee){
        employees.put(employee.getId(),employee);
        saveEmployeesToFile();
    }

    public void UpdateEmployee (int Id ,Employee employee){
        if(employees.containsKey(Id)){
            employees.put(Id, employee);
            saveEmployeesToFile();
        }


    }

    public void DeleteEmployee(int Id){
        employees.remove(Id);
        saveEmployeesToFile();
    }

    public HashMap<Integer, Employee> getEmployees() {
        return employees;
    }

    public void AddCustomers( Customers customer){
        customers.put(customer.getPassID(), customer);
        saveCustomersToFile();
    }

   public void UpdateCustomer (int passID, Customers customer){
        if (customers.containsKey(passID)){
            customers.put(passID,customer);
            saveCustomersToFile();
        }


   }

    public void DeleteCustomer (int passID){
        customers.remove(passID);
        saveCustomersToFile();
    }

    public HashMap<Integer, Customers> getCustomers() {
        return customers;
    }

    public void loadEmployeesFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(employeesData))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Skip empty or whitespace-only lines
                if (line.trim().isEmpty()) continue;

                String[] part = line.split(";");
                // Expect exactly 4 parts: id;name;phone;email
                if (part.length != 4) continue;

                try {
                    int id = Integer.parseInt(part[0].trim());
                    String name = part[1].trim();
                    String phone = part[2].trim();
                    String email = part[3].trim();

                    Employee employee = new Employee(id, name, phone, email);
                    employees.put(id, employee);
                } catch (NumberFormatException ignore) {
                    // Skip malformed line
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    public void saveEmployeesToFile (){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(employeesData))){
            for (Employee employee : employees.values()){
                String line = employee.toString();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void loadCustomersFromFile (){
        try (BufferedReader reader = new BufferedReader(new FileReader(customersData))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Skip empty or whitespace-only lines
                if (line.trim().isEmpty()) continue;

                String[] part = line.split(";");
                // Expect exactly 4 parts: passId;name;phone;email
                if (part.length != 4) continue;

                try {
                    int passid = Integer.parseInt(part[0].trim());
                    String name = part[1].trim();
                    String phone = part[2].trim();
                    String email = part[3].trim();

                    Customers customer = new Customers(passid, name, phone, email);
                    customers.put(passid, customer);
                } catch (NumberFormatException ignore) {
                    // Skip malformed line
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public  void saveCustomersToFile() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(customersData))){
            for (Customers customer : customers.values()){
                String line = customer.toString();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}