# JavaFXList_DragDropReorder

## General info
Example on reordering a list in JavaFX by dragging and dropping the list cells 
	
## Technologies
Project is created with:
* Java Version: 11.0.11
* JavaFX Version: 17.0.1
* IDE: IntelliJ 
* Build: Maven
	
## Changing Cell Content Types
1. Change the type of the ListView<String> on the main controller
2. In the DraggableCell class change every instance of the cell String to your required type
3. In the updateItem() method of the draggable cell you'll need to get the cellString from your new type. ex(file): (getIndex.getName());
