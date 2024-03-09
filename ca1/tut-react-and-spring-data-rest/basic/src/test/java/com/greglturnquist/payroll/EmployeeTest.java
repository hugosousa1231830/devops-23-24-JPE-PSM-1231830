package com.greglturnquist.payroll;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
    @Test
    void constructor_Success() throws InstantiationException {
        String firstName = "Peter";
        String lastName = "Parker";
        String description = "The first employee";
        int jobYears = 5;
        String email = "asda@safsa";

        Employee employee = new Employee(firstName,lastName,description,jobYears,email);
        int result = employee.getJobYears();
        assertEquals(jobYears,result);
    }
    @Test
    void constructor_throwsInstantiationExceptionIfInvalidName(){
        String firstName = " ";
        String lastName = "Parker";
        String description = "The first employee";
        int jobYears = 5;
        String email = "asda@safsa";

        String expected = "Invalid parameters";
        Exception exception = assertThrows(InstantiationException.class, () ->
                new Employee(firstName,lastName,description,jobYears,email));
        String result = exception.getMessage();

        assertEquals(expected,result);
    }

    @Test
    void constructor_throwsInstantiationExceptionIfNullName(){
        String firstName = null;
        String lastName = "Parker";
        String description = "The first employee";
        int jobYears = 5;
        String email = "asda@safsa";

        String expected = "Invalid parameters";
        Exception exception = assertThrows(InstantiationException.class, () ->
                new Employee(firstName,lastName,description,jobYears,email));
        String result = exception.getMessage();

        assertEquals(expected,result);
    }

    @Test
    void constructor_throwsInstantiationExceptionIfLastNameEmpty(){
        String firstName = "Peter";
        String lastName = " ";
        String description = "The first employee";
        int jobYears = 5;
        String email = "asda@safsa";

        String expected = "Invalid parameters";
        Exception exception = assertThrows(InstantiationException.class, () ->
                new Employee(firstName,lastName,description,jobYears,email));
        String result = exception.getMessage();

        assertEquals(expected,result);
    }

    @Test
    void constructor_throwsInstantiationExceptionIfLastNameNull(){
        String firstName = "Peter";
        String lastName = null;
        String description = "The first employee";
        int jobYears = 5;
        String email = "asda@safsa";

        String expected = "Invalid parameters";
        Exception exception = assertThrows(InstantiationException.class, () ->
                new Employee(firstName,lastName,description,jobYears,email));
        String result = exception.getMessage();

        assertEquals(expected,result);
    }

    @Test
    void constructor_throwsInstantiationExceptionIfDescriptionEmpty(){
        String firstName = "Peter";
        String lastName = "Parker";
        String description = " ";
        int jobYears = 5;
        String email = "asda@safsa";

        String expected = "Invalid parameters";
        Exception exception = assertThrows(InstantiationException.class, () ->
                new Employee(firstName,lastName,description,jobYears,email));
        String result = exception.getMessage();

        assertEquals(expected,result);
    }

    @Test
    void constructor_throwsInstantiationExceptionIfDescriptionNull(){
        String firstName = "Peter";
        String lastName = "Parker";
        String description = null;
        int jobYears = 5;
        String email = "asda@safsa";
        String expected = "Invalid parameters";
        Exception exception = assertThrows(InstantiationException.class, () ->
                new Employee(firstName,lastName,description,jobYears,email));
        String result = exception.getMessage();

        assertEquals(expected,result);
    }

    @Test
    void constructor_throwsInstantiationExceptionIfJobYearsNegative(){
        String firstName = "Peter";
        String lastName = "Parker";
        String description = "The slimyest employee";
        int jobYears = -1;
        String email = "asda@safsa";

        String expected = "Invalid parameters";
        Exception exception = assertThrows(InstantiationException.class, () ->
                new Employee(firstName,lastName,description,jobYears,email));
        String result = exception.getMessage();

        assertEquals(expected,result);
    }

    @Test
    void getEmail_GivenAppropriateEmail_ReturnsTheEmail() throws InstantiationException {
        String firstName = "Peter";
        String lastName = "Parker";
        String description = "The slimyest employee";
        int jobYears = 1;
        String email = "asda@safsa";
        Employee employee = new Employee(firstName,lastName,description,jobYears,email);
        String result = employee.getEmail();
        assertEquals(email,result);
    }


}