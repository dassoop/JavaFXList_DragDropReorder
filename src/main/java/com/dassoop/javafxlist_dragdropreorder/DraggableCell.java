package com.dassoop.javafxlist_dragdropreorder;


import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

public class DraggableCell extends ListCell<String>
{
    //Constructor
    public DraggableCell()
    {
        ListCell<String> thisCell = this;

        setOnDragDetected(event ->
        {
            if(getItem() == null) return;

            Dragboard dragboard = startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString(getItem());
            dragboard.setDragView(null);
            dragboard.setContent(content);

            event.consume();
        });

        setOnMouseClicked(event ->
        {
            if(getItem() == null) return;
            event.consume();
        });

        //Set opacity lower when dragging mouse over other cells
        setOnDragEntered(event ->
        {
            if(event.getGestureSource() != thisCell && event.getDragboard().hasString())
            {
                setOpacity(0.3);
            }
        });

        //Set opacity higher when finished dragging mouse off cell
        setOnDragExited(event ->
        {
            if(event.getGestureSource() != thisCell && event.getDragboard().hasString())
            {
                setOpacity(1);
            }
        });

        //Set the cell to drop on when released
        setOnDragOver(event ->
        {
            if (event.getGestureSource() != thisCell && event.getDragboard().hasString())
            {
                event.acceptTransferModes(TransferMode.MOVE);
            }

            event.consume();
        });

        //On drop event
        setOnDragDropped(event ->
        {
            if(getItem() == null) return;

            Dragboard db = event.getDragboard();
            boolean success = false;

            if(db.hasString())
            {
                ObservableList<String> items = getListView().getItems();
                int origin = items.indexOf(db.getString());
                int destination = items.indexOf(getItem());

                //swap origin and destination values
                if(MainController.mode == "swap")
                {
                    items.set(origin, getItem());
                    items.set(destination, db.getString());
                }
                //move origin value and reorder list
                if(MainController.mode == "reorder")
                {
                    items.remove(origin);
                    items.add(destination, db.getString());
                }
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });

        setOnDragDone(DragEvent::consume);
    }


    @Override
    protected void updateItem(String item, boolean empty)
    {
        //run the super class update
        super.updateItem(item, empty);

        //get list from list view
        ObservableList<String> items = getListView().getItems();

        if(item != null)
        {
            String cellString = items.get(getIndex());
            setText(cellString);
        }
    }
}

