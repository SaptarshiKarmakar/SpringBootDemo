package com.demo15.controller;

import com.demo15.payload.EmailService;
import com.demo15.service.EmployeeService;
import com.demo15.util.EmployeeDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee/api/v15/")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    private EmailService emailService;
    public EmployeeController(EmailService emailService){
        this.emailService = emailService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(@Valid @RequestBody EmployeeDto dto, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        EmployeeDto dto2 = employeeService.addEmployee(dto);
        emailService.sendMail(dto2.getEmail(), "Test Mail", "Welcome "+dto2.getName()+". Your Employee Id - "+dto2.getId()+", Department - "+dto2.getDept()+". This is a test mail, Please ignore this.");
        return new ResponseEntity<>(dto2, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete{id}")
    public ResponseEntity<String> deleteEmployee(@RequestParam Long id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable long id, @RequestBody EmployeeDto dto){
        EmployeeDto dto2 = employeeService.updateEmployee(id, dto);
        return new ResponseEntity<>(dto2, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<List<EmployeeDto>> listAllEmployee(
            @RequestParam(name = "pageNo", required = false, defaultValue = "0")int pageNo,
            @RequestParam(name = "pageSize", required = false, defaultValue = "5")int pageSize,
            @RequestParam(name = "sortBy", required = false, defaultValue = "name")String sortBy,
            @RequestParam(name = "sortByDsc", required = false, defaultValue = "asc")String sortByDsc
    ){
        List<EmployeeDto> lae = employeeService.listAllEmp(pageNo, pageSize, sortBy, sortByDsc);
        return new ResponseEntity<>(lae, HttpStatus.OK);
    }

    @GetMapping("/get/{empId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long empId){
        EmployeeDto dto = employeeService.findEmployeeById(empId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
