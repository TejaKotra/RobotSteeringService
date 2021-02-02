package com.apsis.assignment.RobotSteeringService.Model;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Entity to describe robot's movement
 * command : Represents command driving current movement
 * column : Current column after the movement
 * row  :   Current row after the movement
 * direction : Current direction after the movement
 */
@JsonRootName("movement")
public class Movement {
    private char command;
    private int column;
    private int row;
    private char direction;

    public Movement(char command, int column, int row, char direction) {
        this.column = column;
        this.row = row;
        this.direction = direction;
        this.command=command;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public char getDirection() {
        return direction;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public char getCommand() {
        return command;
    }

    public void setCommand(char command) {
        this.command = command;
    }
}
