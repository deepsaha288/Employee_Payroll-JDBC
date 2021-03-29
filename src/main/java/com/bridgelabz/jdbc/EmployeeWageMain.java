package com.bridgelabz.jdbc;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class EmployeeWageMain {
    private static final String URL = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
    private static final String user = "root";
    private static final String password = "root123";


    private Connection establishConnection() throws EmployeeWageException {
        Connection connection;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver found!");
        } catch (ClassNotFoundException e) {
            throw new EmployeeWageException("Cannot find the JDBC Driver!!", EmployeeWageException.ExceptionType.CANNOT_LOAD_DRIVER);
        }
        listDrivers();
        try {
            System.out.println("Connecting to database: " + URL);
            connection = DriverManager.getConnection(URL, user, password);
            System.out.println("Connection established with: " + connection);
        } catch (SQLException e) {
            throw new EmployeeWageException("Cannot connect to the JDBC Driver!!",
                    EmployeeWageException.ExceptionType.WRONG_CREDENTIALS);
        }
        return connection;
    }

    private void listDrivers() {
        Enumeration<Driver> driverList = DriverManager.getDrivers();
        while (driverList.hasMoreElements()) {
            Driver driverClass = driverList.nextElement();
            System.out.println("Driver: " + driverClass.getClass().getName());
        }
    }


    public List<EmployeePayrollData> retrieveAllData() throws SQLException, EmployeeWageException {
        String sql = "SELECT * FROM employee_details";
        List<EmployeePayrollData> employeePayrollData = new ArrayList<>();
        try (Connection connection = this.establishConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                LocalDate start = resultSet.getDate("start").toLocalDate();
                employeePayrollData.add(new EmployeePayrollData(id, name, start));
            }
        }
        return employeePayrollData;
    }

    public static void main(String[] args) throws EmployeeWageException, SQLException {
        EmployeeWageMain employeePayroll = new EmployeeWageMain();
        employeePayroll.establishConnection();
        employeePayroll.retrieveAllData();
    }
}


