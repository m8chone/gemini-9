package com.estcium.gemini9.api;

import com.estcium.gemini9.model.SciencePlan;
import com.estcium.gemini9.model.base.Message;
import com.estcium.gemini9.model.request.SciencePlanRequest;
import com.estcium.gemini9.service.SciencePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/scienceplan")
public class SciencePlanController {

    @Autowired
    SciencePlanService sciencePlanService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getSciencePlan(@PathVariable Integer id){
        return ResponseEntity.ok(sciencePlanService.getSciencePlan(id));
    }
    @GetMapping("/")
    public ResponseEntity<?> getSciencePlan(){
        return ResponseEntity.ok(sciencePlanService.getAllSciencePlan());
    }

    @RequestMapping(value="/add",method = RequestMethod.POST)
    public ResponseEntity<?> createSciencePlan(@RequestBody SciencePlanRequest req){
            return ResponseEntity.ok(sciencePlanService.createSciencePlan(req));
    }

    @RequestMapping(value="/update",method = RequestMethod.PUT)
    public ResponseEntity<?> updateSciencePlan(@RequestBody SciencePlan req){
        return ResponseEntity.ok(sciencePlanService.updateSciencePlan(req));
    }

    @RequestMapping(value="/submit",method = RequestMethod.GET)
    public ResponseEntity<?> submitSciencePlan(@RequestParam Integer id, @RequestParam Integer uid){
        try {
            return ResponseEntity.ok(sciencePlanService.submitSciencePlan(id, uid));
        }catch (Exception e){
            Message res = new Message(e.getMessage());
            return ResponseEntity.status(400).body(res);
        }
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public ResponseEntity<?> testSciencePlan(@RequestParam Integer id){
        try {
            return ResponseEntity.ok(sciencePlanService.testSciencePlan(id));
        }catch (Exception e){
            Message res = new Message(e.getMessage());
            return ResponseEntity.status(400).body(res);
        }
}

    @RequestMapping(value = "/validate",method = RequestMethod.GET)
    @PreAuthorize("hasRole('OBSERVER')")
    public ResponseEntity<?> validateSciencePlan(@RequestParam Integer id, @RequestParam Integer uid){
        try {
            //System.out.println(id+" "+uid);
            return ResponseEntity.ok(sciencePlanService.validateSciencePlan(id,uid));
        }catch (Exception e){
            Message res = new Message(e.getMessage());
            return ResponseEntity.status(400).body(res);
        }
    }

}
