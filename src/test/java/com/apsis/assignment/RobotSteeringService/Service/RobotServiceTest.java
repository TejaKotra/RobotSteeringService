package com.apsis.assignment.RobotSteeringService.Service;

import com.apsis.assignment.RobotSteeringService.Model.Movement;
import com.apsis.assignment.RobotSteeringService.Model.UserInputs;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class RobotServiceTest {


  @Test
  void moveRight() {
    RobotService robotService = new RobotService();
    List<Movement> list =robotService.move(new UserInputs("5 5","0 0 E","R"));
    assertEquals('S',list.get(1).getDirection());
  }

  @Test
  void moveLeft(){
    RobotService robotService = new RobotService();
    List<Movement> list =robotService.move(new UserInputs("5 5","0 0 E","L"));
    assertEquals('N',list.get(1).getDirection());
  }

  @Test
  void moveForward(){
    RobotService robotService = new RobotService();
    List<Movement> list =robotService.move(new UserInputs("5 5","0 0 E","G"));
    assertEquals(1,list.get(1).getColumn());
    assertEquals(0,list.get(1).getRow());
    assertEquals('E',list.get(1).getDirection());
  }

  @Test
  void moveWithCommands1(){
    RobotService robotService = new RobotService();
    List<Movement> list =robotService.move(new UserInputs("5 5","0 0 E","RGLGGLRG"));
    Movement movement = list.get(list.size()-1);
    assertEquals(3,movement.getColumn());
    assertEquals(1,movement.getRow());
    assertEquals('E',movement.getDirection());
  }

  @Test
  void moveWithCommands2(){
    RobotService robotService = new RobotService();
    List<Movement> list =robotService.move(new UserInputs("5 5","1 2 N","RGRGGRGRG"));
    Movement movement = list.get(list.size()-1);
    assertEquals(1,movement.getColumn());
    assertEquals(3,movement.getRow());
    assertEquals('N',movement.getDirection());
  }
}