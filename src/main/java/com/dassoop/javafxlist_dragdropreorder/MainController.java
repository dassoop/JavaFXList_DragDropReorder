package com.dassoop.javafxlist_dragdropreorder;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable
{
    @FXML
    ListView<String> list = new ListView<>();
    @FXML
    Label lblMode = new Label();

    static String mode = "reorder";

    //Constructor
    public MainController()
    {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        //Set list view to use custom DraggableCell class
        list.setCellFactory(param -> new DraggableCell());
        //Add dummy data to list
        list.getItems().setAll(populateDummyData(10));
    }

    //Method to add dummy data into list
    public List<String> populateDummyData(int length)
    {
        List<String> data = new LinkedList<>();

        for(int i = 1; i <= length; i++)
        {
            data.add("Data " + i);
        }

        return data;
    }

    //Method to check list was altered in back-end after reorder
    public List<String> returnNewList()
    {
        List<String> newList = list.getItems();
        System.out.println(newList.toString());
        return newList;
    }

    public void changeMode()
    {
        if(mode == "reorder")
        {
            mode = "swap";
        }
        else
        {
            mode = "reorder";
        }
        String modeStr = mode.substring(0, 1).toUpperCase() + mode.substring(1);
        lblMode.setText(modeStr);
    }
}