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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
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
    private Button btnCreateCourse;
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
    private HBox buttonATHBox;
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
        Create Assignment Pane
    */
    @FXML
    private AnchorPane createAssignmentAPane;
    @FXML
    private TextField txtAName;
    @FXML
    private TextField txtAGrade;
    @FXML
    private DatePicker dpDate;
    @FXML
    private HBox buttonAHBox;
    
    
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
        btnCreateCourse.getStyleClass().remove("btn-Trans");
        btnCreateCourse.getStyleClass().add("btn-TransEntered");
    }
    @FXML
    protected void btnCreateExited(MouseEvent event) {
        btnCreateCourse.getStyleClass().remove("btn-TransEntered");
        btnCreateCourse.getStyleClass().add("btn-Trans");
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
    
    
       
    
    /***************************************************************************
    
    Display Panes
     * @param assignmentType    
    ***************************************************************************/
    
 
    
    /*
        Assignment Pane
    */
    public void displayAssignmentPane(AssignmentType assignmentType) {
        
        //Variables
        int atID = assignmentType.getATID();
        String atName = assignmentType.getATName();
        
        //Switch panes
        switchPane(assignmentAPane);
        
        //Clear the list of assignments before repopulating
        assignmentListVBox.getChildren().clear();
        
        //Set the Assignment Type Name
        lblAssignmentTypeName.setText(atName);
        
        //Loop through list of assignments
        //Get list of assignment types for current course.
        ArrayList<Assignment> aList = new ArrayList<>();
        aList = Assignment.getAssignment(atID);
        
        
        //Loop through list of assignments
        aList.stream().forEach((assignment) ->{
        
            //Dsiplay Assignments
            createAssignmentRow(assignment);
        });
        
        /*
            Create Assignment Button
        */
        CustomButton btnCreateAssignment = createButton("Create", atID);
        
        
        btnCreateAssignment.setOnAction((ActionEvent e) ->{
            
            displayCreateAssignmentPane(assignmentType);
            
        });
        
        //Add create assignment button to the VBox
        assignmentListVBox.getChildren().add(btnCreateAssignment);
        
    }
    
    
    
    /*
        Display Create Assingment Pane
    */
    public void displayCreateAssignmentPane(AssignmentType assignmentType) {
        
        //Switch Panes
        switchPane(createAssignmentAPane);
        
        //Set default date to current date
        dpDate.setValue(LocalDate.now());
        
        //Variables
        int atID = assignmentType.getATID();
        
        /*
            New Create button
        */
        CustomButton btnCreate = createButton("Create", atID);
        
        //Action Event
        btnCreate.setOnAction((ActionEvent event) -> {
            
            //Create new Assignment and add to database
            createAssignment(assignmentType);
            
        });
        
        
        /*
            New Back Button
        */
        CustomButton btnBack = createButton("<-", atID);
        
        btnBack.setOnAction((ActionEvent event) -> {
        
            displayAssignmentPane(assignmentType);
            
        });
        
        //Clear and repopulate button HBox
        buttonAHBox.getChildren().clear();
        buttonAHBox.getChildren().addAll(btnBack, btnCreate);
        
    }
    

    
    /*
        Display the Settings Pane and components for X Course
    */
    public void displaySettingsPane(int id) {
               
        //Switch Panes
        switchPane(settingsSPane);

        settingsVBox.getChildren().clear();
            
        CustomButton btnDelete = createButton("Delete", id);

        btnDelete.setOnAction((ActionEvent event) -> {
            int result = Course.deleteRecord(btnDelete.getID());

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

            //Switch Panes
            switchPane(addCoursePane);  

        });
        
        settingsVBox.getChildren().add(btnDelete);
    }
    
    
    
    /***************************************************************************
     
    Display Data
    
    ***************************************************************************/
    
    
    
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
                
        //Change panes, display course information, display new pane components
        btn.setOnAction((ActionEvent e) -> {
            boolean removeResult;
            
            //Switch Panes
            switchPane(courseContentPane);
            
            /*
                Add this course data to the UI
            */
            lblCourseName.setText(aCourse.getCourseName());
            
            //Clear current vboxs before repopulating
            courseContentVBox.getChildren().clear();
            overallVBox.getChildren().clear();
            
            //Display the assignmentType to vbox as button
            //and display new components
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
        displayATComponents(courseID);
    }
    
    
    
    /*
        Create Assignment HBox
    */
    public void createAssignmentRow(Assignment assignment) {
 
        /*
            Create Labels to display assignment data
        */
        Label lblDate = new Label(assignment.getADate());
        Label lblAssignmentName = new Label(assignment.getAName());
        Label lblGrade = new Label(String.valueOf(assignment.getAGrade()) );
        
        //Align the text to bottom left of label and change font color to white        
        lblDate.setAlignment(Pos.BOTTOM_LEFT);
        lblDate.setTextFill(Color.WHITE);
        lblAssignmentName.setAlignment(Pos.BOTTOM_LEFT);
        lblAssignmentName.setTextFill(Color.WHITE);
        lblGrade.setAlignment(Pos.BOTTOM_RIGHT);
        lblGrade.setTextFill(Color.WHITE);
        
        //Resize labels to specified width 
        //(same as the headers above assignmentListVBox)
        lblDate.setMaxWidth(122);
        lblAssignmentName.setMaxWidth(230);
        lblGrade.setMaxWidth(122);
        
        //Create HBox to align assignment data
        HBox assignmentHBox = new HBox();
        
        //Allow the labels to grow to their max width
        HBox.setHgrow(lblDate, Priority.ALWAYS);
        HBox.setHgrow(lblAssignmentName, Priority.ALWAYS);
        HBox.setHgrow(lblGrade, Priority.ALWAYS);
        
        //Add labels to Hbox
        assignmentHBox.getChildren().addAll(lblDate,lblAssignmentName,lblGrade);
        
        //Create a separator, Add padding top and bottom
        //and set width to fill VBox
        Separator spr = new Separator();
        spr.setPadding(new Insets(5,0,5,0));
        spr.setMaxWidth(Double.MAX_VALUE);
        
        //Add HBox and Separator to the VBox
        assignmentListVBox.getChildren().addAll(assignmentHBox, spr);
    }
    
    
    
    /***************************************************************************
    
    Display Pane Components
    
    ***************************************************************************/
    
    
    
    /*
        Display other components (other than Assignment Type Buttons) to Course Content Pane
    */
    public void displayATComponents(int courseID) {
          
        /*
            Create a button to create a new assignmentType
        */
        CustomButton btnCreateAssignmentType = createButton("Create", courseID);
        btnCreateAssignmentType.setOnAction((ActionEvent e) -> {
           
            displayATCreateComponents(courseID);
                                   
        });
            
        //Add create button to the vbox after all assignmentType have been loaded
        courseContentVBox.getChildren().add(btnCreateAssignmentType);

    }
    
    
    /*
        Display componets for the Create Assignment Type Pane
    */
    public void displayATCreateComponents(int courseID) {

        
        //Switch Panes
        switchPane(assignmentTypeAPane);

        /*
            Back Button
        */
        CustomButton btnBack = createButton("<-", courseID);

        btnBack.setOnAction((ActionEvent event) -> {

            //Switch Panes
            switchPane(courseContentPane);

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
        CustomButton btnCreate = createButton("Create", courseID);

        //Add Action Event to button
        btnCreate.setOnAction((ActionEvent event) -> {

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
        buttonATHBox.getChildren().clear();

        //Add the Back button then Create button to the HBox
        buttonATHBox.getChildren().addAll(btnBack, btnCreate);

    }
    
    
    
    /***************************************************************************
    
    Create Data
    
    ***************************************************************************/
    
    
    
    /*
    Method:     createCourse()
    Purpose:    Create a new course record in DB via user input
    Param:      none
    Log:        Brad Walker 4/2/2016
    */
    @FXML
    private void createCourse() {
        
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
                    
                    //Switch Panes
                    switchPane(courseContentPane);
                    
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
        Create Assignment
    */
    public void createAssignment(AssignmentType assignmentType) {
        
        int atID = assignmentType.getATID();
        
        //User Data
        String aName = txtAName.getText();
        int aGrade = Integer.parseInt(txtAGrade.getText());
        String aDate = dpDate.getValue().toString();

        
        if(!(txtAName.getText().isEmpty())) {
            
            if(!(txtAGrade.getText().isEmpty())) {
                
                if(!(aDate.isEmpty())) {
                     
                    //Create new assignment
                    Assignment assignment = new Assignment(aName, aGrade, aDate, atID);
                    
                    //Add Assignment to DB
                    int result = Assignment.addAssignment(assignment);
                    
                    if(result == 1) {
                        
                        //Variables 
                        double overallATGrade;
                        
                        //Calculate Overall grade for the assignmentType
                        overallATGrade = calculateATOverall(atID);
                        
                        //Update AT overall grade
                        
                        //Switch panes
                        switchPane(assignmentAPane);
                        
                        //Display Pane content
                        displayAssignmentPane(assignmentType);
                        
                        //Clear Screen
                        txtAName.setText("");
                        txtAGrade.setText("");
                        dpDate.setValue(LocalDate.now());
                        
                    } else {
                        
                        System.out.println("Add failed.");
                        
                    }
                    
                } else {
                    System.out.println("Date is empty.");
                }
                
            } else {
                System.out.println("Grade is empty.");
            }
            
        } else {
            System.out.println("Name is empty.");
        }
    }
    
    
    /*
        Calculate overall grade for assignmentType
    */
    public double calculateATOverall (int atID) {
        
        //Variables
        ArrayList<Assignment> aList;
        Assignment assignment;
        double overallGrade = 0.0;
        int numOfAssignments = 0;
        
        //Get list of all assignments of assignmentType atID
        aList = Assignment.getAssignment(atID);
        
        for (int i = 0; i < aList.size(); i++) {
            
            numOfAssignments++;
            
            //Get assignment at index
            assignment = aList.get(i);
            
            //Get grade from assignment
            double grade = assignment.getAGrade();
            
            //Add grade to total overall grade
            overallGrade += grade;
            
        }
        
        //Divide overallGrade by total number of assignments to determine average
        overallGrade /= numOfAssignments;
        
        
        return overallGrade;
    }
    
    
    
    
   
    /***************************************************************************
    
    Other Methods
    
    ***************************************************************************/  
    
  
    
    /*
        Generate basic Custom Button
    */
    public CustomButton createButton(String name, int courseID) {
        
        CustomButton btn = new CustomButton(name, courseID);
        
        //Set initial Style
        btn.getStyleClass().add("btn-Trans");
         
        //Create a MouseEntered and MouseExited event that changes the 
        //style of the button
        btn.setOnMouseEntered((MouseEvent event) -> {
            btn.getStyleClass().add("btn-TransEntered");
            btn.getStyleClass().remove("btn-Trans");
        });
        btn.setOnMouseExited((MouseEvent event) -> {
            btn.getStyleClass().add("btn-Trans");
            btn.getStyleClass().remove("btn-TransEntered");
        });
        
        //Return Button
        return btn;
    }
    
    
    
    
    /*
        Switch Panes
    */
    public void switchPane(Node aPane) {
        contentPane.getChildren().clear();
        contentPane.getChildren().add(aPane);
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