package projectmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.DefaultListModel;

public class DatabaseCalls {

    private Connection conn1, conn2;
    private Statement stmt1, stmt2;
    private ResultSet rs1, rs2;
    private final String database = "testdatabas", projectTable = "projects",
            employeeTable = "employees", connTable = "relations", hourTable = "hours",
            dbmsURL = "localhost", user = "frho9207", pwd = "frho9207";
    private String strSQL;
    private PreparedStatement ps1, ps2, ps3;

    //Öppnar, stänger och skapar databasen
    public void dataBaseStart() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String strConn = "jdbc:mysql://" + dbmsURL + "/" + database + "?user=" + user + "&password=" + pwd;
        conn1 = DriverManager.getConnection(strConn);
        stmt1 = conn1.createStatement();
    }

    public void dataBaseClose() throws SQLException {
        if (!(stmt1 == null)) {
            stmt1.close();
        }
        if (!(stmt2 == null)) {
            stmt2.close();
        }
        if (!(conn1 == null)) {
            conn1.close();
        }
        if (!(conn2 == null)) {
            conn2.close();
        }
        if (!(ps1 == null)) {
            ps1.close();
        }
        if (!(ps2 == null)) {
            ps2.close();
        }
        if (!(ps3 == null)) {
            ps3.close();
        }
        if (!(rs1 == null)) {
            rs1.close();
        }
        if (!(rs2 == null)) {
            rs2.close();
        }
    }

    public void createDatabase() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        String strConn = "jdbc:mysql://" + dbmsURL + "?user=" + user + "&password=" + pwd;
        conn1 = DriverManager.getConnection(strConn);
        stmt1 = conn1.createStatement();

        strSQL = "DROP DATABASE IF EXISTS " + database;
        stmt1.executeUpdate(strSQL);
        strSQL = "CREATE DATABASE " + database;
        stmt1.executeUpdate(strSQL);

        strConn = "jdbc:mysql://" + dbmsURL + "/" + database + "?user=" + user + "&password=" + pwd;
        conn2 = DriverManager.getConnection(strConn);
        stmt2 = conn2.createStatement();

        strSQL = "DROP TABLE IF EXISTS " + projectTable;
        stmt2.executeUpdate(strSQL);
        strSQL = "CREATE TABLE " + projectTable + " (\n"
                + "  projectid int(8) UNSIGNED NOT NULL AUTO_INCREMENT,\n"
                + "  customer varchar(30) NOT NULL,\n"
                + "  projectname varchar(30) NOT NULL,\n"
                + "  startdate date NOT NULL,\n"
                + "  enddate date NOT NULL,\n"
                + "  projectleader varchar(30) NOT NULL,\n"
                + "  status varchar(10) NOT NULL,\n"
                + "  description varchar(200) DEFAULT NULL,\n"
                + "  budgettedcost int(11) NOT NULL,\n"
                + "  PRIMARY KEY (projectid),\n"
                + "  UNIQUE KEY projectid_UNIQUE (projectid)\n"
                + ") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;";
        stmt2.executeUpdate(strSQL);

        strSQL = "DROP TABLE IF EXISTS " + employeeTable;
        stmt2.executeUpdate(strSQL);
        strSQL = "CREATE TABLE " + employeeTable + " (\n"
                + "  idemployees int(8) UNSIGNED NOT NULL AUTO_INCREMENT,\n"
                + "  name VARCHAR(30) NOT NULL,\n"
                + "  personnr VARCHAR(12) NOT NULL,\n"
                + "  username VARCHAR(20) NOT NULL,\n"
                + "  password VARCHAR(20) NOT NULL,\n"
                + "  PRIMARY KEY (idemployees),\n"
                + "  UNIQUE INDEX idemployees_UNIQUE (idemployees ASC)\n"
                + ") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;";
        stmt2.executeUpdate(strSQL);

        strSQL = "DROP TABLE IF EXISTS " + connTable;
        stmt2.executeUpdate(strSQL);
        strSQL = "CREATE TABLE " + connTable + " (\n"
                + "  idemployees int(8) unsigned NOT NULL,\n"
                + "  projectid int(8) unsigned NOT NULL,\n"
                + "  work varchar(16) NOT NULL,\n"
                + "  PRIMARY KEY (idemployees,projectid),\n"
                + "  KEY projectid_idx (projectid),\n"
                + "  CONSTRAINT idemployees FOREIGN KEY (idemployees) REFERENCES employees (idemployees) ON DELETE NO ACTION ON UPDATE NO ACTION,\n"
                + "  CONSTRAINT projectid FOREIGN KEY (projectid) REFERENCES projects (projectid) ON DELETE NO ACTION ON UPDATE NO ACTION\n"
                + ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
        stmt2.executeUpdate(strSQL);

        strSQL = "DROP TABLE IF EXISTS " + hourTable;
        stmt2.executeUpdate(strSQL);
        strSQL = "CREATE TABLE hours (\n"
                + "  hoursid int(8) unsigned NOT NULL AUTO_INCREMENT,\n"
                + "  idemployees int(8) unsigned NOT NULL,\n"
                + "  projectid int(8) unsigned NOT NULL,\n"
                + "  hours double unsigned NOT NULL,\n"
                + "  overtime double,\n"
                + "  PRIMARY KEY (hoursid),\n"
                + "  UNIQUE KEY hoursid_UNIQUE (hoursid),\n"
                + "  KEY projectidfk_idx (projectid),\n"
                + "  KEY idemployeesfk_idx (idemployees),\n"
                + "  CONSTRAINT idemployeesfk FOREIGN KEY (idemployees) REFERENCES employees (idemployees) ON DELETE NO ACTION ON UPDATE NO ACTION,\n"
                + "  CONSTRAINT projectidfk FOREIGN KEY (projectid) REFERENCES projects (projectid) ON DELETE NO ACTION ON UPDATE NO ACTION\n"
                + ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
        stmt2.executeUpdate(strSQL);

        populateDatabase();
    }

    private void populateDatabase() throws SQLException, ClassNotFoundException {
        strSQL = "INSERT INTO " + projectTable + " (customer, projectname, \n"
                + "startdate, enddate, projectleader, status, description, budgettedcost) \n"
                + "VALUES ('Kalle Svensson', 'Godisautomat', '2015-06-24', '2015-07-27', 'Fritjof Höst', 'Planerat',"
                + " 'Utveckling av en godisautomat som kan skapa godis ur tomma luften', 500000)";
        stmt2.executeUpdate(strSQL);
        strSQL = "INSERT INTO " + projectTable + " (customer, projectname, \n"
                + "startdate, enddate, projectleader, status, description, budgettedcost) \n"
                + "VALUES ('LiU', 'Lisam', '2013-11-24', '2030-04-19', 'Ivan Nilsson', 'Aktivt',"
                + " 'Utveckling av en ny webplattform för universitet', 100000)";
        stmt2.executeUpdate(strSQL);
        strSQL = "INSERT INTO " + projectTable + " (customer, projectname, \n"
                + "startdate, enddate, projectleader, status, description, budgettedcost) \n"
                + "VALUES ('Ivan Nilsson', 'Programmeringsprojekt', '2015-03-01', '2015-03-27', 'Fritjof Höst', 'Aktivt',"
                + " 'Utveckling av ett projekthanteringssystem', 300)";
        stmt2.executeUpdate(strSQL);
        strSQL = "INSERT INTO " + projectTable + " (customer, projectname, \n"
                + "startdate, enddate, projectleader, status, description, budgettedcost) \n"
                + "VALUES ('IEI', 'Grundskurs programmering', '2014-09-01', '2014-11-10', 'Ivan Nilsson', 'Avslutat',"
                + " 'En programmeringskurs för systemvetarstudenter', 80000)";
        stmt2.executeUpdate(strSQL);
        strSQL = "INSERT INTO " + employeeTable + " (name, personnr, username, password) "
                + "VALUES ('Fritjof Höst', '199207022519', 'friho608', 'friho608')";
        stmt2.executeUpdate(strSQL);
        strSQL = "INSERT INTO " + employeeTable + " (name, personnr, username, password) "
                + "VALUES ('Ivan Nilsson', '195404031111', 'IvanN', 'IvanN')";
        stmt2.executeUpdate(strSQL);
        strSQL = "INSERT INTO " + employeeTable + " (name, personnr, username, password) "
                + "VALUES ('Kalle Svensson', '198305252222', 'KalleS', 'KalleS')";
        stmt2.executeUpdate(strSQL);
        strSQL = "INSERT INTO " + employeeTable + " (name, personnr, username, password) "
                + "VALUES ('Palle Andersson', '197212243333', 'PalleA', 'PalleA')";
        stmt2.executeUpdate(strSQL);
        strSQL = "INSERT INTO " + employeeTable + " (name, personnr, username, password) "
                + "VALUES ('Jesus', '000000000000', 'Jesus', 'Jesus')";
        stmt2.executeUpdate(strSQL);
        strSQL = "INSERT INTO " + connTable + " (idemployees, projectid, work) "
                + "VALUES ('1', '1', 'Projektledare')";
        stmt2.executeUpdate(strSQL);
        strSQL = "INSERT INTO " + connTable + " (idemployees, projectid, work) "
                + "VALUES ('2', '2', 'Projektledare')";
        stmt2.executeUpdate(strSQL);
        strSQL = "INSERT INTO " + connTable + " (idemployees, projectid, work) "
                + "VALUES ('1', '3', 'Projektledare')";
        stmt2.executeUpdate(strSQL);
        strSQL = "INSERT INTO " + connTable + " (idemployees, projectid, work) "
                + "VALUES ('1', '4', 'Programmerare')";
        stmt2.executeUpdate(strSQL);
        strSQL = "INSERT INTO " + connTable + " (idemployees, projectid, work) "
                + "VALUES ('2', '4', 'Projektledare')";
        stmt2.executeUpdate(strSQL);
        strSQL = "INSERT INTO " + connTable + " (idemployees, projectid, work) "
                + "VALUES ('2', '1', 'Programmerare')";
        stmt2.executeUpdate(strSQL);
        strSQL = "INSERT INTO " + hourTable + " (idemployees, projectid, hours, overtime) "
                + "VALUES ('2', '2', '6', '5')";
        stmt2.executeUpdate(strSQL);
        strSQL = "INSERT INTO " + hourTable + " (idemployees, projectid, hours, overtime) "
                + "VALUES ('2', '2', '8', '5')";
        stmt2.executeUpdate(strSQL);
        strSQL = "INSERT INTO " + hourTable + " (idemployees, projectid, hours, overtime) "
                + "VALUES ('2', '2', '4', '5')";
        stmt2.executeUpdate(strSQL);
        strSQL = "INSERT INTO " + hourTable + " (idemployees, projectid, hours, overtime) "
                + "VALUES ('2', '2', '7', '5')";
        stmt2.executeUpdate(strSQL);
        strSQL = "INSERT INTO " + hourTable + " (idemployees, projectid, hours, overtime) "
                + "VALUES ('1', '3', '50', '12')";
        stmt2.executeUpdate(strSQL);
        strSQL = "INSERT INTO " + hourTable + " (idemployees, projectid, hours, overtime) "
                + "VALUES ('1', '4', '60', '20')";
        stmt2.executeUpdate(strSQL);
        strSQL = "INSERT INTO " + hourTable + " (idemployees, projectid, hours, overtime) "
                + "VALUES ('2', '4', '5', '1')";
        stmt2.executeUpdate(strSQL);
        strSQL = "INSERT INTO " + hourTable + " (idemployees, projectid, hours, overtime) "
                + "VALUES ('2', '4', '40', '5')";
        stmt2.executeUpdate(strSQL);
    }

    //Fyller listor med saker
    public DefaultListModel populateEmployeeJList() throws SQLException {
        DefaultListModel model = new DefaultListModel(); //create a new list model

        strSQL = "SELECT name personnr FROM " + database + "." + employeeTable;

        rs1 = stmt1.executeQuery(strSQL); //run your query

        while (rs1.next()) //go through each row that your query returns
        {
            String itemCode = rs1.getString("personnr"); //get the element in column
            model.addElement(itemCode); //add each item to the model
        }
        return model;
    }

    public DefaultListModel populateProjectJList(String projectLeader) throws SQLException {
        DefaultListModel model = new DefaultListModel(); //create a new list model

        strSQL = "SELECT projectname FROM " + projectTable + " WHERE projectleader =?";

        ps1 = conn1.prepareStatement(strSQL);
        ps1.setString(1, projectLeader);
        try {
            rs1 = ps1.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (rs1.next()) { //go through each row that your query returns
            String project = rs1.getString("projectname"); //get the element in column
            model.addElement(project); //add each item to the model
        }
        return model;
    }

    public DefaultListModel populateWorkJList(int idemployees) throws SQLException {
        DefaultListModel model = new DefaultListModel(); //create a new list model

        strSQL = "SELECT projectid FROM " + connTable + " WHERE idemployees =?";

        ps1 = conn1.prepareStatement(strSQL);
        ps1.setInt(1, idemployees);
        try {
            rs1 = ps1.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (rs1.next()) { //go through each row that your query returns
            int project = rs1.getInt("projectid"); //get the element in column

            strSQL = "SELECT projectname FROM " + projectTable + " WHERE projectid =?";

            ps1 = conn1.prepareStatement(strSQL);
            ps1.setInt(1, project);
            try {
                rs2 = ps1.executeQuery();
            } catch (Exception e) {
                e.printStackTrace();
            }
            while (rs2.next()) {
                String projects = rs2.getString("projectname");
                model.addElement(projects);//add each item to the model
            }
        }
        return model;
    }

    public DefaultListModel populateProjectMemberJList(int projectIndex) throws SQLException {
        DefaultListModel model = new DefaultListModel(); //create a new list model

        strSQL = "SELECT idemployees FROM " + connTable + " WHERE projectid =?";

        ps1 = conn1.prepareStatement(strSQL);
        ps1.setInt(1, projectIndex);
        try {
            rs1 = ps1.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (rs1.next()) { //go through each row that your query returns
            int employees = rs1.getInt("idemployees"); //get the element in column

            strSQL = "SELECT name FROM " + employeeTable + " WHERE idemployees =?";

            ps1 = conn1.prepareStatement(strSQL);
            ps1.setInt(1, employees);
            try {
                rs2 = ps1.executeQuery();
            } catch (Exception e) {
                e.printStackTrace();
            }
            while (rs2.next()) {
                String employee = rs2.getString("name");
                model.addElement(employee);//add each item to the model
            }
        }
        return model;
    }

    //Skriver in vid ny användare och nytt projekt, samt kopplar ihop de två
    public void insertProjectData(String customer, String projectName, Date startDate, Date endDate,
            String projectLeader, String status, String description, int budgettedCost) throws SQLException {
        String insertTableSQL = ("INSERT INTO " + projectTable + " (customer, projectname, "
                + "startdate, enddate, projectleader, status, description, budgettedcost) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

        try {
            ps1 = conn1.prepareStatement(insertTableSQL);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ps1.setString(1, customer);
        ps1.setString(2, projectName);
        ps1.setDate(3, (java.sql.Date) startDate);
        ps1.setDate(4, (java.sql.Date) endDate);
        ps1.setString(5, projectLeader);
        ps1.setString(6, status);
        ps1.setString(7, description);
        ps1.setInt(8, budgettedCost);
        try {
            ps1.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertUserData(String name, String personNR, String username, String password) throws SQLException {
        strSQL = "INSERT INTO " + employeeTable + " (name, personnr, username, password) "
                + "VALUES (?, ?, ?, ?)";

        ps1 = conn1.prepareStatement(strSQL);

        ps1.setString(1, name);
        ps1.setString(2, personNR);
        ps1.setString(3, username);
        ps1.setString(4, password);

        ps1.execute();
    }

    public void createRelation(int employeeIndex, int projectIndex, String work) throws SQLException {
        strSQL = "INSERT INTO " + connTable + " (idemployees, projectid, work) "
                + "VALUES (?, ?, ?)";

        ps1 = conn1.prepareStatement(strSQL);
        ps1.setInt(1, employeeIndex);
        ps1.setInt(2, projectIndex);
        ps1.setString(3, work);
        try {
            ps1.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //kontrollerar inloggning och att folk inte jobbar på samma projekt flera gånger
    public boolean loginUser(String username, String password) throws SQLException {
        boolean check = false;
        strSQL = "SELECT username, password FROM " + employeeTable + " WHERE username = ? AND password = ?";

        ps1 = conn1.prepareStatement(strSQL);
        ps1.setString(1, username);
        ps1.setString(2, password);
        try {
            rs1 = ps1.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (rs1.next()) {
            check = true;
        }
        return check;
    }

    public boolean loginOwner(String username, String password) throws SQLException {
        boolean check = false;
        strSQL = "SELECT customer, projectname FROM " + projectTable + " WHERE customer = ? AND projectname = ?";

        ps1 = conn1.prepareStatement(strSQL);
        ps1.setString(1, username);
        ps1.setString(2, password);
        try {
            rs1 = ps1.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (rs1.next()) {
            check = true;
        }
        return check;
    }

    public boolean relationControll(int employeeIndex, int projectIndex) throws SQLException {
        boolean check = true;
        strSQL = "SELECT idemployees, projectid FROM " + connTable + " WHERE idemployees = ? AND projectid = ?";

        ps1 = conn1.prepareStatement(strSQL);
        ps1.setInt(1, employeeIndex);
        ps1.setInt(2, projectIndex);
        try {
            rs1 = ps1.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (rs1.next()) {
            check = false;
        }
        return check;
    }

//Småhämtningar
    public int getUserid(String username, String password) throws SQLException {
        int id = 0;
        strSQL = "SELECT idemployees FROM " + employeeTable + " WHERE username = ? AND password = ?";
        ps1 = conn1.prepareStatement(strSQL);
        ps1.setString(1, username);
        ps1.setString(2, password);

        try {
            rs1 = ps1.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (rs1.next()) {
            id = rs1.getInt("idemployees");
        }
        return id;
    }

    public int getProjectid(String projectName) throws SQLException {
        int id = 0;
        strSQL = "SELECT projectid FROM " + projectTable + " WHERE projectname =?";
        ps1 = conn1.prepareStatement(strSQL);
        ps1.setString(1, projectName);

        try {
            rs1 = ps1.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (rs1.next()) {
            id = rs1.getInt("projectid");
        }
        return id;
    }

    public String getProjectName(int projectid) throws SQLException {
        String name = "";
        strSQL = "SELECT projectname FROM " + projectTable + " WHERE projectid =?";
        ps1 = conn1.prepareStatement(strSQL);
        ps1.setInt(1, projectid);

        try {
            rs1 = ps1.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (rs1.next()) {
            name = rs1.getString("projectname");
        }
        return name;
    }
    
    public String getUser(int employeeid) throws SQLException {
        String name = "";
        strSQL = "SELECT name FROM " + employeeTable + " WHERE idemployees = ?";
        ps1 = conn1.prepareStatement(strSQL);
        ps1.setInt(1, employeeid);

        try {
            rs1 = ps1.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (rs1.next()) {
            name = rs1.getString("name");
        }
        return name;
    }

    public int getLastProject() throws SQLException {
        int lastIndex = 0;
        strSQL = "SELECT projectid FROM " + projectTable + " ORDER BY projectid DESC LIMIT 1";

        try {
            rs1 = stmt1.executeQuery(strSQL);
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (rs1.next()) {
            lastIndex = rs1.getInt("projectid");
        }
        return lastIndex;
    }

    public int getBudget(int projectIndex) throws SQLException {
        int budget = 0;
        strSQL = "SELECT budgettedcost FROM " + projectTable + " WHERE projectid = ?";
        ps1 = conn1.prepareStatement(strSQL);
        ps1.setInt(1, projectIndex);

        try {
            rs1 = ps1.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (rs1.next()) {
            budget = rs1.getInt("budgettedcost");
        }
        return budget;
    }

    public java.util.Date getEndDate(int projectIndex) throws SQLException {
        Date endDate = new Date();
        strSQL = "SELECT enddate FROM " + projectTable + " WHERE projectid = ?";
        ps1 = conn1.prepareStatement(strSQL);
        ps1.setInt(1, projectIndex);

        try {
            rs1 = ps1.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (rs1.next()) {
            endDate = rs1.getDate("enddate");
        }
        return endDate;
    }

    public java.util.Date getStartDate(int projectIndex) throws SQLException {
        Date startDate = new Date();
        strSQL = "SELECT startdate FROM " + projectTable + " WHERE projectid = ?";
        ps1 = conn1.prepareStatement(strSQL);
        ps1.setInt(1, projectIndex);

        try {
            rs1 = ps1.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (rs1.next()) {
            startDate = rs1.getDate("startdate");
        }
        return startDate;
    }

    public String getStatus(int projectIndex) throws SQLException {
        String status = "";
        strSQL = "SELECT status FROM " + projectTable + " WHERE projectid = ?";
        ps1 = conn1.prepareStatement(strSQL);
        ps1.setInt(1, projectIndex);

        try {
            rs1 = ps1.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (rs1.next()) {
            status = rs1.getString("status");
        }
        return status;
    }

    public String getProjectLeader(int projectIndex) throws SQLException {
        String projectLeader = "";
        strSQL = "SELECT idemployees FROM " + connTable + " WHERE projectid = ? AND work = 'Projektledare'";
        ps1 = conn1.prepareStatement(strSQL);
        ps1.setInt(1, projectIndex);
        try {
            rs1 = ps1.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (rs1.next()) {
            int userid = rs1.getInt("idemployees");
            projectLeader = getUser(userid);
        }
        return projectLeader;
    }

    public String getDescription(int projectIndex) throws SQLException {
        String description = "";
        strSQL = "SELECT description FROM " + projectTable + " WHERE projectid = ?";
        ps1 = conn1.prepareStatement(strSQL);
        ps1.setInt(1, projectIndex);
        try {
            rs1 = ps1.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (rs1.next()) {
            description = rs1.getString("description");
        }
        return description;
    }

    public String getHours(int projectIndex) throws SQLException {
        double hoursDouble = 0;
        strSQL = "SELECT hours, overtime FROM " + hourTable + " WHERE projectid = ?";
        ps1 = conn1.prepareStatement(strSQL);
        ps1.setInt(1, projectIndex);
        try {
            rs1 = ps1.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (rs1.next()) {
            hoursDouble = hoursDouble + rs1.getDouble("hours");
            hoursDouble = hoursDouble + rs1.getDouble("overtime");
        }
        String hoursString = hoursDouble + "";
        return hoursString;
    }
 
    //timrapportering
    public void timeReport(int userid, double hours, double overtime, int projectid) throws SQLException {
        strSQL = "INSERT INTO " + hourTable + " (idemployees, projectid, hours, overtime) "
                + "VALUES (?,?,?,?)";

        ps1 = conn1.prepareStatement(strSQL);
        ps1.setInt(1, userid);
        ps1.setInt(2, projectid);
        ps1.setDouble(3, hours);
        ps1.setDouble(4, overtime);

        try {
            ps1.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Kostnadsuträkning och stödmetod
    public double getCost(int projectIndex) throws SQLException {
        int userid;
        double cost = 0, hours, overtime;
        String work = "";
        strSQL = "SELECT hours, overtime, idemployees FROM " + hourTable + " WHERE projectid = ?";
        ps1 = conn1.prepareStatement(strSQL);
        ps1.setInt(1, projectIndex);
        try {
            rs1 = ps1.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (rs1.next()) {
            hours = rs1.getDouble("hours");
            overtime = rs1.getDouble("overtime");
            userid = rs1.getInt("idemployees");

            strSQL = "SELECT work FROM " + connTable + " WHERE projectid = ? AND idemployees = ?";
            ps1 = conn1.prepareStatement(strSQL);
            ps1.setInt(1, projectIndex);
            ps1.setInt(2, userid);
            try {
                rs2 = ps1.executeQuery();
            } catch (Exception e) {
                e.printStackTrace();
            }
            while (rs2.next()) {
                work = rs2.getString("work");
            }

            switch (work.toLowerCase()) {
                case "programmerare":
                    cost = cost + (500 * hours) + (500 * overtime);
                    break;
                case "systemutvecklare":
                    cost = cost + (450 * hours) + (450 * overtime) * 1.2;
                    break;
                case "projektledare":
                    cost = cost + (650 * hours);
                    if (memberCount(projectIndex)) {
                        cost = cost * 1.1;
                    }
                    break;
            }
        }
        cost = (double) (Math.round(cost * 10) / 10);
        return cost;
    }

    private boolean memberCount(int projectIndex) throws SQLException {
        boolean ok = false;
        int n = 0;
        strSQL = "SELECT idemployees FROM " + connTable + " WHERE projectid = ?";
        ps1 = conn1.prepareStatement(strSQL);
        ps1.setInt(1, projectIndex);
        try {
            rs2 = ps1.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (rs2.next()) {
            n++;
        }
        if (n >= 3) {
            ok = true;
        }
        return ok;
    }

}
