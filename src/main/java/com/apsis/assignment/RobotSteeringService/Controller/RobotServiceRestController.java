package com.apsis.assignment.RobotSteeringService.Controller;

import com.apsis.assignment.RobotSteeringService.Model.FaultMessage;
import com.apsis.assignment.RobotSteeringService.Model.Movement;
import com.apsis.assignment.RobotSteeringService.Model.UserInputs;
import com.apsis.assignment.RobotSteeringService.Service.RobotService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RobotServiceRestController {

    @Autowired
    private RobotService robotService;

    @PostMapping(value = "/robot-service-rest/steer",produces = "applicaton/json")
    public ResponseEntity<Object> move(@RequestBody @Valid UserInputs userInputs, BindingResult result) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        if(result.hasErrors()){
            ArrayList<FaultMessage> faults = new ArrayList<FaultMessage>();
            for(ObjectError obj : result.getAllErrors()){
                faults.add(new FaultMessage(obj.getDefaultMessage()));
            }
            return ResponseEntity.badRequest().body(mapper.writer().withRootName("faults").writeValueAsString(faults));
        }else{
            List<Movement> movementList = robotService.move(userInputs);
            return ResponseEntity.ok().body(mapper.writer().withRootName("movements").writeValueAsString(movementList));
        }
    }
}
