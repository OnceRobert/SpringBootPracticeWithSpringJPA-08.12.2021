package com.thoughtworks.springbootemployee.Integration;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.thoughtworks.springbootemployee.Repository.EmployeesRepo;
import com.thoughtworks.springbootemployee.model.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;



@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeesRepo employeesRepo;

    @AfterEach
    void tearDown(){
        employeesRepo.deleteAll();
    }

    @Test
    void should_return_all_employees_when_call_get_employees_api() throws Exception {
        //given
        final Employee employee = new Employee(1,"Momo", 24, "female",9999, 1);
        final Employee secondemployee = new Employee(2,"Mina", 24, "female",9999, 1);
        employeesRepo.save(employee);
        employeesRepo.save(secondemployee);

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].name").value("Momo"))
                .andExpect(jsonPath("$[0].age").value(24))
                .andExpect(jsonPath("$[0].gender").value("female"))
                .andExpect(jsonPath("$[0].salary").value(9999))
                .andExpect(jsonPath("$[1].name").value("Mina"));
    }
    
    @Test
    void should_return_specific_employee_when_call_get_employee_api_given_employee_id() throws Exception {
        final Employee employee = new Employee(1,"Momo", 24, "female",9999, 1);
        final Employee savedEmployee = employeesRepo.save(employee);

        //when
        //then
        int id = savedEmployee.getId();
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/{id}",id))
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(employee)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Momo"))
                .andExpect(jsonPath("$.age").value(24))
                .andExpect(jsonPath("$.gender").value("female"))
                .andExpect(jsonPath("$.salary").value(9999));
    }

    @Test
    void should_return_correct_pagination_when_call_get_employee_given_index_and_size() throws Exception
    {
        //given
        final Employee employee = new Employee(1,"Momo", 24, "female",9999, 1);
        final Employee secondemployee = new Employee(2,"Mina", 24, "female",9999, 1);
        final Employee thirdemployee = new Employee(3,"Sana", 24, "female",9999, 1);
        employeesRepo.save(employee);
        employeesRepo.save(secondemployee);
        employeesRepo.save(thirdemployee);

        //when
        //then
        int index = 1 ,size = 2;
        mockMvc.perform(MockMvcRequestBuilders.get("/employees?index={index}&size={size}",index,size))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].name").value("Momo"))
                .andExpect(jsonPath("$[0].age").value(24))
                .andExpect(jsonPath("$[0].gender").value("female"))
                .andExpect(jsonPath("$[0].salary").value(9999))
                .andExpect(jsonPath("$[1].id").isNumber())
                .andExpect(jsonPath("$[1].name").value("Mina"))
                .andExpect(jsonPath("$[1].age").value(24))
                .andExpect(jsonPath("$[1].gender").value("female"))
                .andExpect(jsonPath("$[1].salary").value(9999))
                .andExpect(jsonPath("$[2].salary").doesNotExist());
    }

    @Test
    void should_return_all_male_employees_when_call_get_employees_given_gender_equals_male() throws Exception
    {
        //given
        final Employee employee = new Employee(1,"JYP Oppar", 24, "male",9999, 1);
        final Employee secondemployee = new Employee(2,"Ralston", 24, "male",9999, 1);
        final Employee thirdemployee = new Employee(3,"Sana", 24, "female",9999, 1);
        employeesRepo.save(employee);
        employeesRepo.save(secondemployee);
        employeesRepo.save(thirdemployee);

        //when
        //then
        String gender = "male";
        mockMvc.perform(MockMvcRequestBuilders.get("/employees?gender={gender}",gender))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].gender").value("male"))
                .andExpect(jsonPath("$[1].gender").value("male"))
                .andExpect(jsonPath("$[2].salary").doesNotExist());
    }
    
    @Test
    void should_create_employee_when_call_create_employee_api() throws Exception
    {
        //given
        String employee = "{\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"Park Jinyoung\",\n" +
                "        \"age\": 49,\n" +
                "        \"gender\": \"male\",\n" +
                "        \"salary\": 2011233,\n" +
                "        \"companyId\": 1\n" +
                "    }";
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
        .contentType(MediaType.APPLICATION_JSON)
        .content(employee))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Park Jinyoung"))
                .andExpect(jsonPath("$.age").value(49))
                .andExpect(jsonPath("$.gender").value("male"));

    }
    
    
}
