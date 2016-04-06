/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXML;

import Entities.AssignmentType;
import Entities.Course;
import Entities.CustomButton;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;



/**
 *
 * @author Brad
 */
public class FXMLDocumentController implements Initializable {
    
    
    
    
    @FXML
    private Button btnAddCourse;
    @FXML
    private Button btnCreate;
    @FXML
    private VBox courseListVBox;
    @FXML
    private TextField txtCourseName;
    @FXML
    private Label lblResult;
    @FXML
    private StackPane contentPane;
    @FXML
    private AnchorPane addCoursePane;
    @FXML
    private ScrollPane courseContentSPane;
    @FXML 
    private AnchorPane courseContentPane;
    @FXML
    private Label lblCourseName;
    @FXML
    private VBox courseContentVBox;
    @FXML
    private VBox btnListVBox;
    @FXML
    private Button btnDeleteCourse;
    @FXML
    private ScrollPane settingsSPane;
    @FXML
    private VBox settingsVBox;

    
    /*
        Styling methods for buttons (Entered and Exited style change)
    */
    @FXML
    protected void btnAddEntered(MouseEvent event) {
        btnAddCourse.getStyleClass().remove("btn");
        btnAddCourse.getStyleClass().add("btnEntered");
    }
    @FXML
    protected void btnAddExited(MouseEvent event) {
        btnAddCourse.getStyleClass().remove("btnEntered");
        btnAddCourse.getStyleClass().add("btn");
    }
    @FXML
    protected void btnCreateEntered(MouseEvent event) {
        btnCreate.getStyleClass().remove("btn");
        btnCreate.getStyleClass().add("btnEntered");
    }
    @FXML
    protected void btnCreateExited(MouseEvent event) {
        btnCreate.getStyleClass().remove("btnEntered");
        btnCreate.getStyleClass().add("btn");
    }
    @FXML
    protected void btnDeleteEntered(MouseEvent event) {
        btnCreate.getStyleClass().remove("btn");
        btnCreate.getStyleClass().add("btnEntered");
    }
    @FXML
    protected void btnDeleteExited(MouseEvent event) {
        btnDeleteCourse.getStyleClass().remove("btnEntered");
        btnDeleteCourse.getStyleClass().add("btn");
    }
    
    
    /*
    Method:     btnAddCourse()
    Purpose:    remove courseContentPane and add addCoursePane to StackPane
    Param:      none
    Log:        Brad Walker 4/2/2016
    */
    @FXML
    private void addCoursePane() {
        
        contentPane.getChildren().clear();          
        contentPane.getChildren().add(addCoursePane);
        
    }
    
    
    @FXML 
    private void DeleteCourse() {
        
        
        
    }
    
    
    
    
    /*
    Method:     addCourse()
    Purpose:    Create a new course record in DB via user input
    Param:      none
    Log:        Brad Walker 4/2/2016
    */
    @FXML
    private void addCourse() {
        
        
        //Variables
        String cName;
        int result;
        
        
        //Create new Course object to add to DB
        Course course;
        
        
        //Get input from user
        cName = txtCourseName.getText();
        
        
        //Give 0.0 as initial overall grade
        course = new Course(cName, 0.0);
        
        
        if(txtCourseName.getText().isEmpty()){
            
            
            result = 0;
            
            //Blank textbox error
            lblResult.setText("Course name must not be blank.");
            
            
        } else {
            
            
            //Add Course object to DB
            result = Course.addRecord(course);
            
            //Reset the error lable and course textbox.
            lblResult.setText("");
            txtCourseName.setText("");
            
        }
                
        
        if(result == 1) {
            
            
            //Clear the VBox and then repopulate them
            courseListVBox.getChildren().clear();
            
            
            //List of courses in DB
            ArrayList<Course> courseList;
            courseList = Course.getCourse();
        
               
            //Loop through all courses and display them
            courseList.stream().forEach((aCourse) -> {
                displayCourse(aCourse);
            });

            
            //Display new button 
            boolean displayResult;
          
        }
    }
    
    
    
    /*
    Method:     displayCourse()
    Purpose:    Display a new course button in the vBox
    Param:      Course 
    Log:        Brad Walker 4/2/2016
    */
    private boolean displayCourse(Course aCourse){

        
        //Variables
        boolean result;
        
        
        //Create new custom button with course name and id
        CustomButton btn = new CustomButton(aCourse.getCourseName(), aCourse.getCourseID());          

        
        //Initially style the button            
        btn.getStyleClass().add("btn");

        
        //Set the button's width to fill the vBox
        btn.setMaxWidth(Double.MAX_VALUE);

        
        //Add extra styling to the button
        btn.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        
        //Create a MouseEntered and MouseExited event that changes the 
        //style of the button
        btn.setOnMouseEntered((MouseEvent event) -> {
            btn.getStyleClass().add("btnEntered");

            btn.getStyleClass().remove("btn");
        });
        btn.setOnMouseExited((MouseEvent event) -> {
            btn.getStyleClass().add("btn");
            btn.getStyleClass().remove("btnEntered");
        });
        
        
        //Create action event for button to change 
        btn.setOnAction((ActionEvent e) -> {
            boolean removeResult;
            contentPane.getChildren().clear();
            contentPane.getChildren().add(courseContentPane);

            
            /*
            Add data from this course to the UI
            */
            lblCourseName.setText(aCourse.getCourseName());
            
            courseContentVBox.getChildren().clear();
            btnListVBox.getChildren().clear();
            
            displayAssignmentTypes(aCourse.getCourseID()); 
        });
        
        
        //Result of the button add
        result = courseListVBox.getChildren().addAll(btn);
        
        
        return result;
    }
    

    
    /*
        Display AssignmentTypes (AT)
    */
    private void displayAssignmentTypes(int courseID) {
       
        //Get list of assignment types for current course.
        ArrayList<AssignmentType> atList = new ArrayList<>();
        atList = AssignmentType.getAssignmentType(courseID);
        
        
        //Loop through list to display in vbox
        atList.stream().forEach((assignmentType) ->{
           
            //Create and add a label to content vbox
            Label lblATName = new Label(assignmentType.getATName());
            courseContentVBox.getChildren().add(lblATName);
            
            
            
            
            //Create and add a table view to content vbox
            TableView tbvAssignments = new TableView();
            tbvAssignments.setEditable(true);
            
            TableColumn nameCol = new TableColumn("Name");
            TableColumn gradeCol = new TableColumn("Grade");
            
            tbvAssignments.getColumns().addAll(nameCol, gradeCol);
            
            courseContentVBox.getChildren().add(tbvAssignments);
            
            //Add assignments to the table view
            
            
            
        });
        
        
        //New CusstomButton for settings that uses an ID to determine the 
        //course to edit in the settings.
        CustomButton btnSettings = new CustomButton("Settings", courseID);
        
        
        //Style for the button
        btnSettings.getStyleClass().add("btn");
        
        
        //Create a MouseEntered and MouseExited event that changes the 
        //style of the button
        btnSettings.setOnMouseEntered((MouseEvent event) -> {
            btnSettings.getStyleClass().add("btnEntered");
            btnSettings.getStyleClass().remove("btn");
        });
        btnSettings.setOnMouseExited((MouseEvent event) -> {
            btnSettings.getStyleClass().add("btn");
            btnSettings.getStyleClass().remove("btnEntered");
        });
        
        
        //OnAction Event that changes panes and adds
        btnSettings.setOnAction((ActionEvent e) -> { 
            contentPane.getChildren().clear();
            contentPane.getChildren().add(settingsSPane);
            
            settingsVBox.getChildren().clear();
            
            CustomButton btnDeleteCourse = new CustomButton("Delete", courseID);
            
            btnDeleteCourse.setOnAction((ActionEvent event) -> {
                int result = Course.deleteRecord(btnDeleteCourse.getID());
                
                if(result == 1) {
                    System.out.println("Deleted");
                }
                
                courseListVBox.getChildren().clear();
                
                //List of courses in DB
                ArrayList<Course> courseList;
                courseList = Course.getCourse();
        
               
                //Loop through all courses and display them
                courseList.stream().forEach((aCourse) -> {
                    displayCourse(aCourse);
                });
                
                contentPane.getChildren().clear();
                contentPane.getChildren().add(addCoursePane);  
                
            });
            
            settingsVBox.getChildren().add(btnDeleteCourse);
            
        });
                        
        btnListVBox.getChildren().add(btnSettings);

        
    }
        
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        //List of courses in DB
        ArrayList<Course> courseList;
        courseList = Course.getCourse();
        
               
        //Loop through all courses and display them
        courseList.stream().forEach((aCourse) -> {
            displayCourse(aCourse);
        }); 
        
        //contentPane.getChildren().add(addCoursePane);
        
        //Remove all panes except for the addCoursePane. 
        //addCoursePane is the default Pane on startup
        contentPane.getChildren().clear();
        contentPane.getChildren().add(addCoursePane);

        
    }
}