package com.apsis.assignment.RobotSteeringService.Model;

import javax.validation.constraints.Pattern;

/**
 * Entity for form backing bean
 */
public class UserInputs {
    /**
     * matrixSize : represents Matrix size entered by the user.
     * It should be of format 5 5. First number represents column and second number represents row.
     */
  @Pattern(regexp = "[0-9]*\\s[0-9]*", message = "Enter correct dimensions for the matrix. Eg:- 5 5")
  private String matrixSize;
    /**
     * firstPosition : represents First position entered by the user.
     * It should be of format 0 0 E. First character specifies column. Second character specifies row.
     * And last character specifies the direction it is facing.
     */
  @Pattern(regexp = "[0-9]*\\s[0-9]*\\s[EWNS]", message = "Position entered is invalid. Eg:- 0 0 E")
  private String firstPosition;
    /**
     * walk : represents commands entered by the user
     * It should be set of characters R or L or G. R and L changes direction to Right and Left respectively.
     * Where as G move the robot one step in forward direction.
     */
    @Pattern(regexp ="[RLG]*", message = "Invalid instructions. Only L, R, G are allowed")
    private String walk;

    public UserInputs(String matrixSize, String firstPosition, String walk) {
        this.matrixSize = matrixSize;
        this.firstPosition = firstPosition;
        this.walk = walk;
    }

    public String getMatrixSize() {
        return matrixSize;
    }

    public String getFirstPosition() {
        return firstPosition;
    }

    public String getWalk() {
        return walk;
    }

    public void setMatrixSize(String matrixSize) {
        this.matrixSize = matrixSize;
    }

    public void setFirstPosition(String firstPosition) {
        this.firstPosition = firstPosition;
    }

    public void setWalk(String walk) {
        this.walk = walk;
    }


    @Override
    public String toString() {
        return "UserInputs{" +
                "matrixSize='" + matrixSize + '\'' +
                ", firstPosition='" + firstPosition + '\'' +
                ", walk='" + walk + '\'' +
                '}';
    }
}
