/*
    Cole Howell, Manoj Bompada, Justin Le
    Ticket.java
    ITCS 4180
 */

package com.example.ticketreservationsystem;

import java.io.Serializable;

public class Ticket implements Serializable {
    String name;
    String Source;
    String Destination;
    String Departure;
    String Return;
    boolean Round;

    public Ticket(String name, String source, String destination, String departure, String returns, boolean round) {
        this.name = name;
        this.Source = source;
        this.Destination = destination;
        this.Departure = departure;
        this.Return = returns;
        this.Round = round;
    }

//    public Ticket(String name, String source, String destination) {
//        this.name = name;
//        this.Source = source;
//        this.Destination = destination;
//    }

    public String getName() {
        return name;
    }

    public String getSource() {
        return Source;
    }

    public String getDestination() {
        return Destination;
    }

    public String getDeparture() {
        return Departure;
    }

    public String getReturn() {
        return Return;
    }

    public boolean isRound() {
        return Round;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "name='" + name + '\'' +
                ", Source='" + Source + '\'' +
                ", Destination='" + Destination + '\'' +
                ", Departure='" + Departure + '\'' +
                ", Return='" + Return + '\'' +
                '}';
    }
}
