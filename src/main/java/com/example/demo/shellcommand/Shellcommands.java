package com.example.demo.shellcommand;

import com.example.demo.component.Progresscounter;
import org.jline.terminal.Terminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.table.*;

@ShellComponent
public class Shellcommands {


    public String[] CONTINENTS = {"Europe", "North America", "South America", "Africa", "Asia", "Austraila and Oceania"};
    public String[] COUNTRIES1 = {"Germany", "USA", "Brasil", "Nigeria", "China", "Australia"};
    public String[] COUNTRIES2 = {"France", "Canada", "Argentina", "Egypt", "India", "New Zeeland"};

    @Autowired
    Progresscounter progressCounter;
    @Autowired
    @Lazy
    Terminal terminal;

    @ShellMethod("Say hello")
    public String hello(){
        return "hello nikunj";
    }

    @ShellMethod("Displays progress spinner")
    public void progressSpinner() throws InterruptedException {
        for (int i = 1; i <=20; i++) {
            progressCounter.display();
            Thread.sleep(100);
        }
        progressCounter.reset();
    }

    @ShellMethod("Display table")
    public void displayTable() throws InterruptedException{
        Object[][] sampleData = new String[][] {
                CONTINENTS,
                COUNTRIES1,
                COUNTRIES2
        };

        TableModel tableModel = new ArrayTableModel(sampleData);
TableBuilder tableBuilder = new TableBuilder(tableModel);
        tableBuilder.addInnerBorder(BorderStyle.fancy_light);
        tableBuilder.addHeaderBorder(BorderStyle.fancy_double);
        terminal.writer().println(tableBuilder.build().render(80));
        terminal.flush();

    }




}
