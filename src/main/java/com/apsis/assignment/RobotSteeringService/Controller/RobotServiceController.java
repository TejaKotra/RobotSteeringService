package com.apsis.assignment.RobotSteeringService.Controller;

import com.apsis.assignment.RobotSteeringService.Model.Movement;
import com.apsis.assignment.RobotSteeringService.Model.UserInputs;
import com.apsis.assignment.RobotSteeringService.Service.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller class to handle requests from the UI.
 */

@Controller
@RequestMapping("/robot-service")
public class RobotServiceController {

    @Autowired
    private RobotService robotService;

    /**
     *
     * This will be the first entry point.
     * @param model Represents model received from view
     * @return Redirects to RobotService.html
     */
    @GetMapping(value = "/steer")
    public String robotService(ModelMap model){
        model.addAttribute("userInputs",new UserInputs("","",""));
        return "RobotService";
    }

    /**
     *
     * @param model Represents model received from view
     * @param userInputs Form backing bean. Contains user entered valued in the form.
     * @param result To access validation errors
     * @return Redirects to RobotService.html
     */
  @PostMapping(value = "/steer")
  public String submit(ModelMap model,
                       @ModelAttribute @Valid UserInputs userInputs, BindingResult result) {
      model.addAttribute("matrixSize",userInputs.getMatrixSize());
      model.addAttribute("firstPosition",userInputs.getFirstPosition());
      model.addAttribute("walk",userInputs.getWalk());
      if(result.hasErrors()){

          return "RobotService";
      }
        List<Movement> movementList = robotService.move(userInputs);
      model.addAttribute("movements",movementList);
        return "RobotService";
  }

    /**
     * Will be called from form to clear session
     * @param model Model received from view.
     * @return redirects to /robot-service/steer
     */
  @GetMapping
  public String redirect(SessionStatus status, ModelMap model){
      model.clear();
      status.setComplete();
      return "redirect:/robot-service/steer";
  }

}
