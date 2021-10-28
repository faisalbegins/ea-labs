package edu.miu.cs.cs544.exercise03_2.a;

import java.util.List;

import static edu.miu.cs.cs544.exercise03_2.HibernateUtils.withTx;

public class App {
    public static void main(String[] args) {
        withTx(session -> {
            // department with employee
            Department cse = Department.of("CSE");

            Employee faisal = Employee.of("E001", "Faisal");
            Employee ahmed = Employee.of("E002", "Ahmed");

            faisal.setDepartment(cse);
            ahmed.setDepartment(cse);

            cse.addEmployee(faisal);
            cse.addEmployee(ahmed);

            session.persist(cse);

            // only department
            Department business = Department.of("Business");
            session.persist(business);

            // only employee
            Employee john = Employee.of("E003", "John");
            session.persist(john);

            Employee e = session.get(Employee.class, "E003");
            e.setDepartment(business);
            session.persist(e);
        });

        withTx(session -> {
            List<Department> departments = session.createQuery("from Department", Department.class).list();
            departments.forEach(System.out::println);
        });

        System.exit(0);
    }
}
