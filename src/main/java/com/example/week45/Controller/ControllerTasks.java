package com.example.week45.Controller;

import com.example.week45.Api.ApiResponse;
import com.example.week45.Model.Tasks;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/version1/tasks")
public class ControllerTasks {
    ArrayList<Tasks> listTasks=new ArrayList<>();
    @GetMapping("/get")
    public ArrayList<Tasks>getTasks(){
        return listTasks;
    }

    @PostMapping("/add")
    public ApiResponse addTask(@RequestBody Tasks tasks ){
        listTasks.add(tasks);
        fixTasks.add(tasks);
        return new ApiResponse( "Tasks add successfully") ;
    }
    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteTask(@PathVariable int index){
        listTasks.remove(index);
        return new ApiResponse( "the deleted is done successfully ");
    }
    @PutMapping("/update/{index}")
    public ApiResponse updateTask(@PathVariable int index,@RequestBody Tasks tasks){
        listTasks.set(index,tasks);
        return new  ApiResponse( "the update successfully");
    }

    @GetMapping("/search")
    public ApiResponse searchListInfo(@RequestBody Task title) {
        for(Task use :listInfo){
            if (use.getTitle().equalsIgnoreCase(title.getTitle())) {
                return new ApiResponse("found");
            }
        }
        return new ApiResponse("not found ");
    }
     @PutMapping("/turn-status")
    public ApiResponse updateStatus(@RequestBody Task nStatus){
        for(Task use:listInfo){
            if(use.getTitle().equalsIgnoreCase(nStatus.getTitle())){
                use.setStatus(nStatus.isStatus());
                return new ApiResponse("turn status is done");
            }
        }

        return new ApiResponse("turn status not done");
    }


}
