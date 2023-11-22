package com.example.lab6.Controller;

import com.example.lab6.Model.Employee;
import com.example.lab6.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor

public class EmployeeController {
    private final EmployeeService employeeService;
    @GetMapping("/get")
    public ResponseEntity getEmployees(){
        ArrayList<Employee>employees=employeeService.getEmployees();
        return ResponseEntity.status(HttpStatus.OK).body(employees) ;
    }
    @PostMapping("/add")
    public ResponseEntity addEmployee(@RequestBody @Valid Employee employee, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        employeeService.addEmployee(employee);
       return ResponseEntity.status(HttpStatus.OK).body("employee added!");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateEmployee(@PathVariable String id,@RequestBody@Valid Employee employee,Errors errors){
       if(errors.hasErrors()){
          String message=errors.getFieldError().getDefaultMessage();
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
       }
       boolean isFound=employeeService.updateEmployee(employee,id);
       if(isFound){
          return ResponseEntity.status(HttpStatus.OK).body("employee updated");
       }
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("employee not found");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteEmployee(@PathVariable String id){
        boolean isRemoved=employeeService.deleteEmployee(id);
        if(isRemoved){
        return ResponseEntity.status(HttpStatus.OK).body("employee deleted");
    }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong id,not found");
    }
    @GetMapping("/search/{position}")
    public ResponseEntity searchPosition(@PathVariable String position){
        ArrayList positions=employeeService.searchEmployee(position);
        if(positions==null)  //لانها نل قبلت ==
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("enter supervisor or coordinator");
        }
        return ResponseEntity.status(HttpStatus.OK).body(positions);
    }
    @GetMapping("/range/{min}/{max}")
    public ResponseEntity rangeAge(@PathVariable int min,@PathVariable int max){
        ArrayList range=employeeService.range(min, max);
        if(range==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("enter range upon 25 or more");
        }
        return ResponseEntity.status(HttpStatus.OK).body(range);
    }
    @PutMapping("/annual/{id}")
    public ResponseEntity annualLeave(@PathVariable String id){
        boolean a=employeeService.annualLeave(id);
        if(a)
        return ResponseEntity.status(HttpStatus.OK).body("done");
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("employee doesn't have annual leave or is on leave,or employee doesn't exist ");
    }
    @GetMapping("/noAnnual")
    public ResponseEntity noAnnualLeave(){
        ArrayList noAnnual=employeeService.noAnnualLeave();
       return ResponseEntity.status(HttpStatus.OK).body(noAnnual);
    }
    @PutMapping("/promote/{id1}/{id2}")
    public ResponseEntity changePosition(@PathVariable String id1,@PathVariable String id2){
    boolean isChange=employeeService.promote(id1, id2);
    if(isChange){
        return ResponseEntity.status(HttpStatus.OK).body("is changed");
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("enter correct information ");
    }
}
