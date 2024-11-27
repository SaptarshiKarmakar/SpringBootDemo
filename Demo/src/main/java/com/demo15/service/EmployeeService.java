package com.demo15.service;

import com.demo15.entity.Employee;
import com.demo15.exception.GlobalException;
import com.demo15.repository.EmployeeRepo;
import com.demo15.util.EmployeeDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper modelMapper;

//    Employee mapToEntity(EmployeeDto dto){
//        Employee emp = new Employee();
//        emp.setId(dto.getId());
//        emp.setName(dto.getName());
//        emp.setEmail(dto.getEmail());
//        emp.setNumber(dto.getNumber());
//        emp.setDept(dto.getDept());
//        return emp;
//    }
//
//    EmployeeDto mapToDto(Employee employee){
//        EmployeeDto dto = new EmployeeDto();
//        dto.setId(employee.getId());
//        dto.setName(employee.getName());
//        dto.setEmail(employee.getEmail());
//        dto.setNumber(employee.getNumber());
//        dto.setDept(employee.getDept());
//        return dto;
//    }

    Employee mapToEntity(EmployeeDto dto){
        Employee emp = modelMapper.map(dto, Employee.class);
        return emp;
    }

    EmployeeDto mapToDto(Employee emp){
        EmployeeDto dto = modelMapper.map(emp, EmployeeDto.class);
        return dto;
    }

    public EmployeeDto addEmployee(EmployeeDto dto1){
        Employee emp1 = mapToEntity(dto1);
        Employee emp2 = employeeRepo.save(emp1);
        EmployeeDto dto2 = mapToDto(emp2);
        return dto2;
    }

    public void deleteEmployee(long id){
        employeeRepo.deleteById(id);
    }

    public EmployeeDto updateEmployee(long id, EmployeeDto dto1){
        Employee emp1 = mapToEntity(dto1);
        emp1.setId(id);
        Employee emp2 = employeeRepo.save(emp1);
        EmployeeDto dto2 = mapToDto(emp2);
        return dto2;
    }

    public List<EmployeeDto> listAllEmp(int pageNo, int pageSize, String sortBy, String sortByDsc){
        Sort sort = sortByDsc.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable page = PageRequest.of(pageNo, pageSize, sort);
        Page<Employee> list = employeeRepo.findAll(page);
        List<Employee> list1 = list.getContent();
        List<EmployeeDto> dto = list1.stream().map(e->mapToDto(e)).collect(Collectors.toList());
        return dto;
    }

    public EmployeeDto findEmployeeById(long empId){
        Employee employee = employeeRepo.findById(empId).orElseThrow(()-> new GlobalException("No Employee Found with this id "+empId));
        EmployeeDto dto = mapToDto(employee);
        return dto;
    }
}
