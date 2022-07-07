package com.SpringDatabase.DemoDatabase.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SpringDatabase.DemoDatabase.Entity.Employee;

@Repository
public interface UserRepository extends JpaRepository<Employee, Long> {

}
