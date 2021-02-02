package com.apsis.assignment.RobotSteeringService.Service;

import com.apsis.assignment.RobotSteeringService.Model.Movement;
import com.apsis.assignment.RobotSteeringService.Model.UserInputs;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service to handle robot movement logic
 */
@Service
public class RobotService {

    private int currentRow;
    private int currentColumn;
    private int currentDirection;
    private int maxRowSize;
    private int maxColumnSize;
    private char currentCommand;
    private String commands;

    /**
     * Contains 4 direction. N E W S
     */
    private static ArrayList<Character> directions;

    /**
     * List to store all the robot movements
     */
    private ArrayList<Movement> robotMovement;

    static {
        directions = new ArrayList<Character>();
        directions.add('N');
        directions.add('E');
        directions.add('S');
        directions.add('W');
    }

    public RobotService() {
        robotMovement = new ArrayList<Movement>();
    }

    private void assignInputs(UserInputs userInputs) {
        this.currentColumn = Integer.parseInt(userInputs.getFirstPosition().substring(0,userInputs.getFirstPosition().indexOf(' ')));
        this.currentRow = Integer.parseInt(userInputs.getFirstPosition().substring(userInputs.getFirstPosition().indexOf(' ')+1,3));
        this.currentDirection = directions.indexOf(userInputs.getFirstPosition().substring(4).charAt(0));
        this.maxColumnSize=Integer.parseInt(userInputs.getMatrixSize().substring(0,userInputs.getMatrixSize().indexOf(' ')));
        this.maxRowSize=Integer.parseInt(userInputs.getMatrixSize().substring(userInputs.getMatrixSize().indexOf(' ')+1));
        this.commands=userInputs.getWalk();
        this.currentCommand=' ';

    }

    /**
     *
     * @param userInputs User inputs received from UI
     * @return Returns robot's movements resulted from user commands
     */
    public List<Movement> move(UserInputs userInputs){

        assignInputs(userInputs);
        addMovement();
        for (Character cmd:
                commands.toCharArray()) {
            currentCommand=cmd.charValue();
            switch (cmd){
                case 'R':
                    changeDirection('R');
                    addMovement();
                    break;
                case 'L':
                    changeDirection('L');
                    addMovement();
                    break;
                case 'G':
                    changePosition();
                    addMovement();
                    break;
            }


        }

        return robotMovement;

    }

    private void changeDirection(char direction){
        if(currentDirection==directions.size()-1 && direction=='R'){
            currentDirection=0;

        }else if(currentDirection==0 && direction == 'L'){
            currentDirection=directions.size()-1;
        }else if(direction=='R'){
            currentDirection+=1;
        }else if(direction=='L'){
            currentDirection-=1;
        }
    }

    private void changePosition(){
        switch (directions.get(currentDirection)){
            case 'N':
                if (currentRow!=0){
                    currentRow--;
                }
                break;
            case 'E':
                if (currentColumn!=maxColumnSize){
                    currentColumn++;
                }
                break;
            case 'S':
                if (currentRow!=maxRowSize){
                    currentRow++;
                }
                break;
            case 'W':
                if(currentColumn!=0){
                    currentColumn--;
                }
        }
    }

    private void addMovement(){
        robotMovement.add(new Movement(currentCommand,currentColumn,currentRow,directions.get(currentDirection)));
    }

}
