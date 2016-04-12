/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXML;

import Entities.Assignment;
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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;


/**
 *
 * @author Brad
 */
public class FXMLDocumentController implements Initializable {
    
        
    /*
        Stack Pane
    */
    @FXML
    private StackPane contentPane;
    
    
    /*
        Create Course Pane
    */
    @FXML
    private AnchorPane addCoursePane;
    @FXML
    private Button btnCreate;
    @FXML
    private TextField txtCourseName;
    @FXML
    private Label lblResult;
    
    
    /*
        Left Side Bar
    */
    @FXML
    private Button btnAddCourse;
    @FXML
    private VBox courseListVBox;
        
            
    /*
        Course Content Pane
    */
    @FXML 
    private AnchorPane courseContentPane;
    @FXML
    private Label lblCourseName;
    @FXML
    private VBox courseContentVBox;
    @FXML
    private VBox overallVBox;
    
    
    /*
        Create Assignment Type Pane
    */
    @FXML
    private AnchorPane assignmentTypeAPane;
    @FXML
    private Button btnATCreate;
    @FXML
    private Button btnATBack;
    @FXML
    private HBox buttonHBox;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPercentage;
    @FXML
    private Label lblName;
    @FXML
    private Label lblPercentage;
    
    
    /*
        Settings Pane
    */
    @FXML
    private Button btnDeleteCourse;
    @FXML
    private ScrollPane settingsSPane;
    @FXML
    private VBox settingsVBox;

    
    /*
        Assignment Pane
    */
    @FXML
    private AnchorPane assignmentAPane;
    @FXML
    private Label lblAssignmentTypeName;
    @FXML
    private VBox assignmentListVBox;
    
    
    
    
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
        btnCreate.getStyleClass().remove("btn-Trans");
        btnCreate.getStyleClass().add("btn-TransEntered");
    }
    @FXML
    protected void btnCreateExited(MouseEvent event) {
        btnCreate.getStyleClass().remove("btn-TransEntered");
        btnCreate.getStyleClass().add("btn-Trans");
    }
    @FXML
    protected void btnDeleteExited(MouseEvent event) {
        btnDeleteCourse.getStyleClass().remove("btnEntered");
        btnDeleteCourse.getStyleClass().add("btn");
    }
    @FXML
    protected void btnATCreateEntered(MouseEvent event) {
        btnATCreate.getStyleClass().remove("btn-Trans");
        btnATCreate.getStyleClass().add("btn-TransEntered");
    }
    @FXML
    protected void btnATCreateExited(MouseEvent event) {
        btnATCreate.getStyleClass().remove("btn-TransEntered");
        btnATCreate.getStyleClass().add("btn-Trans");
    }
    @FXML
    protected void btnATBackEntered(MouseEvent event) {
        btnATBack.getStyleClass().remove("btn-Trans");
        btnATBack.getStyleClass().add("btn-TransEntered");
    }
    @FXML
    protected void btnATBackExited(MouseEvent event) {
        btnATBack.getStyleClass().remove("btn-TransEntered");
        btnATBack.getStyleClass().add("btn-Trans");
    }
    
    
    
    /*
    Method:     btnAddCourse()
    Purpose:    remove courseContentPane and add addCoursePane to StackPane
    Param:      none
    Log:        Brad Walker 4/2/2016
    */
    @FXML
    private void addCoursePane() {
        
        //Clear the Stack pane and add Add Course Pane
        contentPane.getChildren().clear();          
        contentPane.getChildren().add(addCoursePane);
    }
    
    
    
    /*
        Pass through the course ID to display corresponding ATs
    */
    @FXML 
    private void DisplayATPane() {
        
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
            lblResult.setText("Required Field.");
                       
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
        }
    }
    
    
    
    /*
    Method:     displayCourse()
    Purpose:    Display a new course button in the vBox
    Param:      Course 
    Log:        Brad Walker 4/2/2016
    */
    private void displayCourse(Course aCourse){
        
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
                
        //Change panes, display course information, display new pane componets
        btn.setOnAction((ActionEvent e) -> {
            boolean removeResult;
            contentPane.getChildren().clear();
            contentPane.getChildren().add(courseContentPane);
            
            /*
                Add this course data to the UI
            */
            lblCourseName.setText(aCourse.getCourseName());
            
            //Clear current vboxs before repopulating
            courseContentVBox.getChildren().clear();
            overallVBox.getChildren().clear();
            
            //Display the assignmentType to vbox as button
            //and display new componets
            displayAssignmentTypes(aCourse.getCourseID()); 
                        
        });
                
        //Result of the button add
        courseListVBox.getChildren().addAll(btn);
    }
    
    
    
    /*
        Display AssignmentTypes (AT)
    */
    private void displayAssignmentTypes(int courseID) {
       
        //Get list of assignment types for current course.
        ArrayList<AssignmentType> atList = new ArrayList<>();
        atList = AssignmentType.getAssignmentType(courseID);
        
        //Loop through list to display in vboxs
        for(int i = 0; i < atList.size(); i++){
           
            AssignmentType assignmentType;
            assignmentType = atList.get(i);
            
            /*
                New CusstomButton for settings that uses an ID to determine the 
                course to edit in the settings.
            */
            CustomButton btnAssignmentType = new CustomButton(assignmentType.getATName(), courseID);

            //Style for the button
            btnAssignmentType.getStyleClass().add("btn");

            //Create a MouseEntered and MouseExited event that changes the 
            //style of the button
            btnAssignmentType.setOnMouseEntered((MouseEvent event) -> {
                btnAssignmentType.getStyleClass().add("btnEntered");
                btnAssignmentType.getStyleClass().remove("btn");
            });
            btnAssignmentType.setOnMouseExited((MouseEvent event) -> {
                btnAssignmentType.getStyleClass().add("btn");
                btnAssignmentType.getStyleClass().remove("btnEntered");
            });

            //OnAction Event that changes panes and adds
            btnAssignmentType.setOnAction((ActionEvent e) -> { 
                
                displayAssignmentPane(assignmentType);
                //displaySettings(courseID);
                
            });

            btnAssignmentType.setMaxWidth(Double.MAX_VALUE);
            btnAssignmentType.setPrefHeight(50.0);
            
            /*
                Create a label to display overall Grade for corresponding Assingment Type
            */
            
            Double ATOverall = assignmentType.getATOverall();

            String string = String.valueOf(ATOverall);
                                    
            Label lblOverall = new Label(string);
            
            lblOverall.setWrapText(true);
            lblOverall.setAlignment(Pos.CENTER);
            lblOverall.setTextAlignment(TextAlignment.CENTER);
            lblOverall.getStyleClass().add("lbl-overall");
            lblOverall.setPrefHeight(50.0);
            lblOverall.setPrefWidth(60.0);
            
                    
            if(ATOverall >= 100.00) {
                lblOverall.getStyleClass().add("lbl-gold");
            } else if (ATOverall >= 80) {
                lblOverall.getStyleClass().add("lbl-green");
            } else if(ATOverall <= 69.99) {
                lblOverall.getStyleClass().add("lbl-red");
            }
            
            
            /*
                Add both the label and the button to their corresponding vBoxs
            */
            overallVBox.getChildren().add(lblOverall);
            courseContentVBox.getChildren().add(btnAssignmentType);
        }
        displayATComponets(courseID);
    }
        
    
    /*
        Create New Assignment Type
    */
    public boolean createAssignmentType(int courseID) {

        if(txtName.getText().isEmpty() == false) {
           
            if(txtPercentage.getText().isEmpty() == false) {
               
                String name;
                Double percentage;

                name = txtName.getText();
                percentage = Double.valueOf(txtPercentage.getText());

                //Create new Assignment Type
                AssignmentType assignmentType = new AssignmentType(name, percentage, 0.0, courseID); 

                //Add new Assignment Type to database
                int result = AssignmentType.addAssignmentType(assignmentType);

                if(result == 1 ) {
                    
                    Course course = Course.getACourse(courseID);

                    /*
                         Add data from this course to the UI
                    */
                    lblCourseName.setText(course.getCourseName());

                    //Clear current vboxs before repopulating
                    courseContentVBox.getChildren().clear();
                    overallVBox.getChildren().clear();

                    //Display the assignmentType to vbox as button
                    displayAssignmentTypes(course.getCourseID());
                    
                    contentPane.getChildren().clear();
                    contentPane.getChildren().add(courseContentPane);
                    
                    return true;
                }
            } else {
                lblPercentage.setText("Required Field.");
                return false;
            }
       } else {
            lblName.setText("Required Field.");
            return false;
       }
        return false;
    }
    
    
    
    /*
        Display other componets (other than Assignment Type Buttons) to Course Content Pane
    */
    public void displayATComponets(int courseID) {
          
        /*
            Create a button to create a new assignmentType
        */
        CustomButton btnCreateAssignmentType = new CustomButton("Create", courseID);
        
        //Set the style for the button
        btnCreateAssignmentType.getStyleClass().add("btn-Trans");
        
        //Create a MouseEntered and MouseExited event that changes the 
        //style of the button
        btnCreateAssignmentType.setOnMouseEntered((MouseEvent event) -> {
            btnCreateAssignmentType.getStyleClass().add("btn-TransEntered");
            btnCreateAssignmentType.getStyleClass().remove("btn-Trans");
        });
        btnCreateAssignmentType.setOnMouseExited((MouseEvent event) -> {
            btnCreateAssignmentType.getStyleClass().add("btn-Trans");
            btnCreateAssignmentType.getStyleClass().remove("btn-TransEntered");
        });
        btnCreateAssignmentType.setOnAction((ActionEvent e) -> {
           
            displayATCreateComponets(courseID);
                                   
        });
            
        //Add create button to the vbox after all assignmentType have been loaded
        courseContentVBox.getChildren().add(btnCreateAssignmentType);

    }
    
    
    /*
        Display componets for the Create Assignment Type Pane
    */
    public void displayATCreateComponets(int courseID) {

        //Clear StackPane and add Pane to create new assignmentType
        contentPane.getChildren().clear();
        contentPane.getChildren().add(assignmentTypeAPane);

        /*
            Back Button
        */
        CustomButton btnATBack = new CustomButton("<-", courseID);

        btnATBack.getStyleClass().add("btn-Trans");


        //Create a MouseEntered and MouseExited event that changes the 
        //style of the button
        btnATBack.setOnMouseEntered((MouseEvent event) -> {
            btnATBack.getStyleClass().add("btn-TransEntered");
            btnATBack.getStyleClass().remove("btn-Trans");
        });
        btnATBack.setOnMouseExited((MouseEvent event) -> {
            btnATBack.getStyleClass().add("btn-Trans");
            btnATBack.getStyleClass().remove("btn-TransEntered");
        });


        btnATBack.setOnAction((ActionEvent event) -> {

            contentPane.getChildren().clear();
            contentPane.getChildren().add(courseContentPane);

            Course course = Course.getACourse(courseID);

            /*
            Add data from this course to the UI
            */
            lblCourseName.setText(course.getCourseName());

            //Clear current vboxs before repopulating
            courseContentVBox.getChildren().clear();
            overallVBox.getChildren().clear();

            //Display the assignmentType in vbox as a button
            displayAssignmentTypes(course.getCourseID());

            //Clear result labels
            lblName.setText("");
            lblPercentage.setText("");

        });


        /*
            Create Button
        */
        CustomButton btnATCreate = new CustomButton("Create", courseID);

        //Set button style
        btnATCreate.getStyleClass().add("btn-Trans");

        //Create a MouseEntered and MouseExited event that changes the 
        //style of the button
        btnATCreate.setOnMouseEntered((MouseEvent event) -> {
            btnATCreate.getStyleClass().add("btn-TransEntered");
            btnATCreate.getStyleClass().remove("btn-Trans");
        });
        btnATCreate.setOnMouseExited((MouseEvent event) -> {
            btnATCreate.getStyleClass().add("btn-Trans");
            btnATCreate.getStyleClass().remove("btn-TransEntered");
        });

        /*
            Create Button Action Event
        */
        btnATCreate.setOnAction((ActionEvent event) -> {

            /*
                Call Function to create new assignmentType 
                and add it to the database
            */

            boolean result = createAssignmentType(courseID);

            //Check result of adding AT and clear screen if it was a success
            if(result == true) {

                //Clear result labels
                lblName.setText("");
                lblPercentage.setText("");

                //Clear textfields
                txtName.setText("");
                txtPercentage.setText("");
            }
        });

        //Clear HBox of any buttons
        buttonHBox.getChildren().clear();

        //Add the Back button then Create button to the HBox
        buttonHBox.getChildren().addAll(btnATBack, btnATCreate);

    }
    
    
    
    /*
        Assignment Pane
    */
    public void displayAssignmentPane(AssignmentType assignmentType) {
        
        //Switch panes
        contentPane.getChildren().clear();
        contentPane.getChildren().add(assignmentAPane);
        
        //Clear the list of assignments before repopulating
        assignmentListVBox.getChildren().clear();
        
        //Set the Assignment Type Name
        lblAssignmentTypeName.setText(assignmentType.getATName());
        
        //Loop through list of assignments
        //Get list of assignment types for current course.
        ArrayList<Assignment> aList = new ArrayList<>();
        aList = Assignment.getAssignment(assignmentType.getATID());
        
        aList.stream().forEach((assignment) ->{
        
            createAssignment(assignment);
        });
        
        /*
            Create Assignment Button
        */
        CustomButton btnCreateAssignment = new CustomButton("Create", assignmentType.getATID());
         
        btnCreateAssignment.getStyleClass().add("btn-Trans");
         
        //Create a MouseEntered and MouseExited event that changes the 
        //style of the button
        btnCreateAssignment.setOnMouseEntered((MouseEvent event) -> {
            btnCreateAssignment.getStyleClass().add("btn-TransEntered");
            btnCreateAssignment.getStyleClass().remove("btn-Trans");
        });
        btnCreateAssignment.setOnMouseExited((MouseEvent event) -> {
            btnCreateAssignment.getStyleClass().add("btn-Trans");
            btnCreateAssignment.getStyleClass().remove("btn-TransEntered");
        });
        
        btnCreateAssignment.setOnAction((ActionEvent e) ->{
            
            
            
        });
        
        //Add create assignment button to the VBox
        assignmentListVBox.getChildren().add(btnCreateAssignment);
        
    }
    
    
    /*
        Create Assignment HBox
    */
    public void createAssignment(Assignment assignment) {
 
        Label lblDate = new Label(assignment.getADate());
        Label lblAssignmentName = new Label(assignment.getAName());
        Label lblGrade = new Label(String.valueOf(assignment.getAGrade()) );
        
        lblDate.setAlignment(Pos.BOTTOM_LEFT);
        lblDate.setTextFill(Color.WHITE);
        
        lblAssignmentName.setAlignment(Pos.BOTTOM_LEFT);
        lblAssignmentName.setTextFill(Color.WHITE);
        
        lblGrade.setAlignment(Pos.BOTTOM_RIGHT);
        lblGrade.setTextFill(Color.WHITE);
        
        //Resize all labels
        lblDate.setMaxWidth(122);
        lblAssignmentName.setMaxWidth(230);
        lblGrade.setMaxWidth(122);
        
        HBox assignmentHBox = new HBox();
        
        
        
        assignmentHBox.setHgrow(lblDate, Priority.ALWAYS);
        assignmentHBox.setHgrow(lblAssignmentName, Priority.ALWAYS);
        assignmentHBox.setHgrow(lblGrade, Priority.ALWAYS);
        
        assignmentHBox.setAlignment(Pos.CENTER);
        assignmentHBox.getChildren().addAll(lblDate,lblAssignmentName,lblGrade);
        
        
        Separator spr = new Separator();
        spr.setPadding(new Insets(5,0,5,0));
        spr.setMaxWidth(Double.MAX_VALUE);
        
        assignmentListVBox.getChildren().addAll(assignmentHBox, spr);

    }
    
    
    
    
    /*
        Display the Settings Pane and componets for X Course
    */
    public void displaySettings(int courseID) {
                        
        contentPane.getChildren().clear();
        contentPane.getChildren().add(settingsSPane);

        settingsVBox.getChildren().clear();
            
        CustomButton btnDeleteCourse = new CustomButton("Delete", courseID);

        //Set Button Style
        btnDeleteCourse.getStyleClass().add("btn");

        //Create a MouseEntered and MouseExited event that changes the 
        //style of the button
        btnDeleteCourse.setOnMouseEntered((MouseEvent event) -> {
            btnDeleteCourse.getStyleClass().add("btnEntered");

            btnDeleteCourse.getStyleClass().remove("btn");
        });
        btnDeleteCourse.setOnMouseExited((MouseEvent event) -> {
            btnDeleteCourse.getStyleClass().add("btn");
            btnDeleteCourse.getStyleClass().remove("btnEntered");
        });

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