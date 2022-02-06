package com.example.demo.component;

import org.jline.terminal.Terminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Progresscounter {

    @Autowired
    Terminal terminal;
    private static final String CUU = "\u001B[A";
boolean started = true;
    private char[] spinner = {'|', '/', '-', '\\'};

    private int spinCounter = 0;

    public void display() {
        if (!started) {
            terminal.writer().println();
            started = true;
        }
        terminal.writer().print(CUU + " " + getSpinnerChar());
        terminal.flush();
    }

    public void reset() {
        spinCounter = 0;
        started = false;
    }

    private char getSpinnerChar() {
        char spinChar = spinner[spinCounter];
        spinCounter++;
        if (spinCounter == spinner.length) {
            spinCounter = 0;
        }
        return spinChar;
    }

    //--- set / get methods ---------------------------------------------------

    public char[] getSpinner() {
        return spinner;
    }

    public void setSpinner(char[] spinner) {
        this.spinner = spinner;
    }


}
