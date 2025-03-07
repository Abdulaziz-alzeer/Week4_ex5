package com.example.bank.Controller;

import com.example.bank.Api.ApiResponse;
import com.example.bank.Model.Customers;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.aop.framework.AopInfrastructureBean;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/version1/bank")
public class ControllerCustomers {

    public ArrayList<Customers>listCustomers=new ArrayList<>();


    @GetMapping("/get")
    public ArrayList<Customers>getListCustomers(){
        return listCustomers;
    }

    @PostMapping("/add")
    public ApiResponse addCustomers(@RequestBody Customers customers){
        listCustomers.add(customers);
        return new ApiResponse("Customer add successfully");
    }
    @PutMapping("/update/{ID}")
    public ApiResponse updateCustomers(@PathVariable String ID, @RequestBody Customers customers){
        for(int i = 0; i < listCustomers.size(); i++) {
            if (listCustomers.get(i).getID().equals(ID)) {
                Customers updateCustomer = listCustomers.get(i);
                updateCustomer.setUserName(customers.getUserName());
                updateCustomer.setBalance(customers.getBalance());

                return new ApiResponse("Customer updated successfully");
            }
        }
        return new ApiResponse("customer not found ");
    }


    @DeleteMapping("/delete/{ID}")
    public ApiResponse deleteCustomers(@PathVariable String ID) {

        for (int i = 0; i < listCustomers.size(); i++) {
            if (listCustomers.get(i).getID().equals(ID)) {
                listCustomers.remove(i);
                return new ApiResponse("Customer delete successfully");
            }
        }
        return new ApiResponse("Customer not found");
    }

    @PutMapping("/deposit/{ID}")
    public ApiResponse depositCustomers(@PathVariable String ID,@RequestBody double money){
        for (int i = 0; i < listCustomers.size(); i++) {
            if (listCustomers.get(i).getID().equals(ID)){
                Customers addMonitCus =listCustomers.get(i);
                addMonitCus.setBalance(addMonitCus.getBalance()+money);
                return new ApiResponse("customer deposit successfully ");
            }
        }
        return new ApiResponse("customer not found");
    }

    @PutMapping("/withdraw/{ID}")
    public ApiResponse withdrawCustomers(@PathVariable String ID,@RequestBody double money ){
        if (money <= 0) {
            return new ApiResponse("The withdrawal amount must be greater than 0");
        }
        for (int i = 0; i < listCustomers.size(); i++) {
            if(listCustomers.get(i).getID().equals(ID)){
                Customers withdrawCustomer=listCustomers.get(i);
                if (withdrawCustomer.getBalance()>=money){
                    withdrawCustomer.setBalance(withdrawCustomer.getBalance()-money);
                    return new ApiResponse("Withdrawal successful. New balance: " + withdrawCustomer.getBalance());
                }else{
                    return new ApiResponse("your balance is down more that what you want");
                }
            }
        }
        return new ApiResponse("Customer not found");
    }




}
