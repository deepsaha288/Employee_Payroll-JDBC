package com.bridgelabz.jdbc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.util.List;

public class EmployeeWageTesting {

    @Test
    public void givenEmployeePayrollDB_WhenRetrieved_ShouldMatchEmployeeCount() throws SQLException, EmployeeWageException {
        EmployeeWageMain employeeWage = new EmployeeWageMain();
        List<EmployeePayrollData> employeePayrollDataList = employeeWage.retrieveAllData();
        System.out.println(employeePayrollDataList.get(0));
        System.out.println(employeePayrollDataList.get(1));
        System.out.println(employeePayrollDataList.get(2));
        Assertions.assertEquals(3, employeePayrollDataList.size());
    }
}
