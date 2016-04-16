DROP TABLE tblAssignment;
DROP TABLE tblAssignmentType;
DROP TABLE tblCourse;

CREATE TABLE tblCourse (
    cID INT NOT NULL GENERATED ALWAYS AS IDENTITY,
    cName varchar(50) NOT NULL,
    cOverall DOUBLE NOT NULL,
    PRIMARY KEY (cID)
);
CREATE TABLE tblAssignmentType (
    atID INT NOT NULL GENERATED ALWAYS AS IDENTITY,
    atName varchar(25) NOT NULL,
    atPercentage DOUBLE NOT NULL,
    atOverall DOUBLE NOT NULL,
    cID INT NOT NULL,
    PRIMARY KEY (atID),
    FOREIGN KEY (cID) REFERENCES tblCourse(cID)
);
CREATE TABLE tblAssignment (
    aID INT NOT NULL GENERATED ALWAYS AS IDENTITY,
    aName varchar(50) NOT NULL,
    aGrade DOUBLE NOT NULL,
    aDate varchar(10) NOT NULL,
    atID INT NOT NULL,
    PRIMARY KEY (aID),
    FOREIGN KEY (atID) REFERENCES tblAssignmentType(atID)
);
INSERT INTO tblCourse (cName, cOverall)
VALUES
    ('History', 100);

INSERT INTO tblAssignmentType (atName, atPercentage, atOverall, cID)
VALUES
    ('Homework', 50, 100, 1);

INSERT INTO tblAssignment (aName, aGrade, aDate, atID)
VALUES
    ('HW 1', 100, '01/11/16', 1),
    ('HW 2', 100, '02/12/16', 1),
    ('HW 3', 100, '03/13/16', 1),
    ('HW 4', 100, '04/14/16', 1);