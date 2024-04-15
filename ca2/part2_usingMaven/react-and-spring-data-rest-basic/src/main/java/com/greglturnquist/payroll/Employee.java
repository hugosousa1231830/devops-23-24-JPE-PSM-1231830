/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.greglturnquist.payroll;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

/**
 * @author Greg Turnquist
 */
// tag::code[]
@Entity // <1>
public class Employee {

	private @Id @GeneratedValue Long id; // <2>
	private String firstName;
	private String lastName;
	private String description;
	private int jobYears;
	private String email;

	public Employee() {}

	public Employee(String firstName, String lastName, String description, int jobYears, String email) throws InstantiationException {
		if (areParamsNullOrEmpty(firstName,lastName,description,email) || !isEmailValid(email) || !areJobYearsValid(jobYears)){
			throw new InstantiationException("Invalid parameters");
		}
		this.firstName = firstName;
		this.lastName = lastName;
		this.description = description;
		this.jobYears = jobYears;
		this.email = email;
	}

	private boolean areParamsNullOrEmpty(String... params){
		for (String param : params){
			if (param == null || param.trim().isEmpty()){
				return true;
			}
		}
		return false;
	}

	private boolean areJobYearsValid(int jobYears){
		return jobYears > 0;
	}

	// This method was created on v 1.3.0, but I am creating this simulate that this was done on 1.3.1.
	private boolean isEmailValid(String email){
		String[] words = email.split("@");
		return words.length<=2 && !areParamsNullOrEmpty(words[0],words[1]);
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Employee employee = (Employee) o;
		return Objects.equals(id, employee.id) &&
			Objects.equals(firstName, employee.firstName) &&
			Objects.equals(lastName, employee.lastName) &&
			Objects.equals(description, employee.description) &&
			Objects.equals(jobYears, employee.jobYears) &&
			Objects.equals(email, employee.email);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, firstName, lastName, description, jobYears);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getJobYears(){
		return this.jobYears;
	}
	public void setJobYears(int jobYears){
		this.jobYears = jobYears;
	}

	@Override
	public String toString() {
		return "Employee{" +
			"id=" + id +
			", firstName='" + firstName + '\'' +
			", lastName='" + lastName + '\'' +
			", description='" + description + '\'' +
			", jobYears='" + jobYears + '\'' +
				", email='" + jobYears +
			'}';
	}
}
// end::code[]
