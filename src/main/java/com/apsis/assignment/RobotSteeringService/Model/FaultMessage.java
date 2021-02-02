package com.apsis.assignment.RobotSteeringService.Model;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("faults")
public class FaultMessage {
    private String faultMessage;

    public FaultMessage(String faultMessage) {
        this.faultMessage = faultMessage;
    }


    public String getFaultMessage() {
        return faultMessage;
    }


    public void setFaultMessage(String faultMessage) {
        this.faultMessage = faultMessage;
    }
}
