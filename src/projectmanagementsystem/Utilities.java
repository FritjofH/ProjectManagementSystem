package projectmanagementsystem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;

public class Utilities {

    public DefaultListModel populateEmployeeJList() throws IOException, FileNotFoundException, ClassNotFoundException, SQLException {
        DatabaseCalls dbc = new DatabaseCalls();
        dbc.dataBaseStart();
        DefaultListModel importedList = dbc.populateEmployeeJList();
        dbc.dataBaseClose();
        return importedList;
    }

    public DefaultListModel populateProjectJList(int userid) throws IOException, FileNotFoundException, ClassNotFoundException, SQLException {
        DatabaseCalls dbc = new DatabaseCalls();
        dbc.dataBaseStart();
        String projectLeader = dbc.getUser(userid);
        DefaultListModel importedList = dbc.populateProjectJList(projectLeader);
        dbc.dataBaseClose();
        return importedList;
    }

    public DefaultListModel populateWorkJList(int userid) throws IOException, FileNotFoundException, ClassNotFoundException, SQLException {
        DatabaseCalls dbc = new DatabaseCalls();
        dbc.dataBaseStart();
        DefaultListModel importedList = dbc.populateWorkJList(userid);
        dbc.dataBaseClose();
        return importedList;
    }

    public ListModel projectMembersJList(int projectIndex) throws ClassNotFoundException, SQLException {
        DatabaseCalls dbc = new DatabaseCalls();
        dbc.dataBaseStart();
        DefaultListModel importedList = dbc.populateProjectMemberJList(projectIndex);
        dbc.dataBaseClose();
        return importedList;
    }

    public void insertProjectData(String owner, String projectName, String startDateString,
            String endDateString, int userid, String status,
            String budgettedCostString, String description) throws SQLException, ClassNotFoundException {
        Date startDate = Date.valueOf(startDateString);
        Date endDate = Date.valueOf(endDateString);
        int budgettedCost = Integer.parseInt(budgettedCostString);
        DatabaseCalls dbc = new DatabaseCalls();
        dbc.dataBaseStart();
        String projectLeader = dbc.getUser(userid);
        dbc.insertProjectData(owner, projectName, startDate, endDate, projectLeader, status, description, budgettedCost);
        int projectid = dbc.getLastProject();
        dbc.createRelation(userid, projectid, "Projektledare");
        dbc.dataBaseClose();
    }

    public void insertUserData(String name, String personNR, String username, String password) throws ClassNotFoundException, SQLException {
        DatabaseCalls dbc = new DatabaseCalls();
        dbc.dataBaseStart();
        dbc.insertUserData(name, personNR, username, password);
        dbc.dataBaseClose();
    }

    public void createDBRelation(int employeeIndex, String projectName, int work) throws ClassNotFoundException, SQLException {
        DatabaseCalls dbc = new DatabaseCalls();
        dbc.dataBaseStart();
        String workStr = "";

        if (work == 0) {
            workStr = "Programmerare";
        } else if (work == 1) {
            workStr = "Systemutvecklare";
        }

        int projectIndex = dbc.getProjectid(projectName);

        dbc.createRelation(employeeIndex, projectIndex, workStr);
        dbc.dataBaseClose();
    }

    public boolean nullChecker(String text) {
        boolean ok = true;

        if (text.equals("")) {
            ok = false;
        }
        return ok;
    }

    public boolean relationControll(int employeeIndex, String projectname) throws SQLException, ClassNotFoundException {
        DatabaseCalls dbc = new DatabaseCalls();
        dbc.dataBaseStart();
        int projectIndex = dbc.getProjectid(projectname);
        boolean check = dbc.relationControll(employeeIndex, projectIndex);
        dbc.dataBaseClose();
        return check;
    }

    public boolean loginUser(String username, String password) throws SQLException, ClassNotFoundException {
        DatabaseCalls dbc = new DatabaseCalls();
        dbc.dataBaseStart();
        boolean check = dbc.loginUser(username, password);
        dbc.dataBaseClose();
        return check;
    }

    public boolean loginOwner(String username, String password) throws ClassNotFoundException, SQLException {
        DatabaseCalls dbc = new DatabaseCalls();
        dbc.dataBaseStart();
        boolean check = dbc.loginOwner(username, password);
        dbc.dataBaseClose();
        return check;
    }

    public int getUserid(String username, String password) throws ClassNotFoundException, SQLException {
        DatabaseCalls dbc = new DatabaseCalls();
        dbc.dataBaseStart();
        int userid = dbc.getUserid(username, password);
        dbc.dataBaseClose();
        return userid;
    }

    public int getProjectid(String projectName) throws SQLException, ClassNotFoundException {
        DatabaseCalls dbc = new DatabaseCalls();
        dbc.dataBaseStart();
        int id = dbc.getProjectid(projectName);
        dbc.dataBaseClose();
        return id;
    }

    public String getBudget(int projectIndex) throws ClassNotFoundException, SQLException {
        DatabaseCalls dbc = new DatabaseCalls();
        dbc.dataBaseStart();
        String budget = Integer.toString(dbc.getBudget(projectIndex));
        dbc.dataBaseClose();
        return budget;
    }

    public String getCost(int projectIndex) throws ClassNotFoundException, SQLException {
        DatabaseCalls dbc = new DatabaseCalls();
        dbc.dataBaseStart();
        String cost = Double.toString(dbc.getCost(projectIndex));
        dbc.dataBaseClose();
        return cost;
    }

    public String getEndDate(int projectIndex) throws ClassNotFoundException, SQLException {
        DatabaseCalls dbc = new DatabaseCalls();
        dbc.dataBaseStart();
        String endDate = dbc.getEndDate(projectIndex).toString();
        dbc.dataBaseClose();
        return endDate;
    }

    public String getStartDate(int projectIndex) throws ClassNotFoundException, SQLException {
        DatabaseCalls dbc = new DatabaseCalls();
        dbc.dataBaseStart();
        String startDate = dbc.getStartDate(projectIndex).toString();
        dbc.dataBaseClose();
        return startDate;
    }

    public String getStatus(int projectIndex) throws ClassNotFoundException, SQLException {
        DatabaseCalls dbc = new DatabaseCalls();
        dbc.dataBaseStart();
        String status = dbc.getStatus(projectIndex).toLowerCase();
        dbc.dataBaseClose();
        return status;
    }

    public void timeReport(int userid, double hours, double overtime, String projectName) throws SQLException, ClassNotFoundException {
        DatabaseCalls dbc = new DatabaseCalls();
        dbc.dataBaseStart();
        int projectid = getProjectid(projectName);
        dbc.timeReport(userid, hours, overtime, projectid);
        dbc.dataBaseClose();
    }

    public String getProjectLeader(int projectIndex) throws ClassNotFoundException, SQLException {
        DatabaseCalls dbc = new DatabaseCalls();
        dbc.dataBaseStart();
        String projectLeader = dbc.getProjectLeader(projectIndex);
        dbc.dataBaseClose();
        return projectLeader;
    }

    public String getProjectName(int projectIndex) throws ClassNotFoundException, SQLException {
        DatabaseCalls dbc = new DatabaseCalls();
        dbc.dataBaseStart();
        String projectName = dbc.getProjectName(projectIndex);
        dbc.dataBaseClose();
        return projectName;
    }

    public String getDescription(int projectIndex) throws ClassNotFoundException, SQLException {
        DatabaseCalls dbc = new DatabaseCalls();
        dbc.dataBaseStart();
        String description = dbc.getDescription(projectIndex);
        dbc.dataBaseClose();
        return description;
    }

    public String getHours(int projectIndex) throws ClassNotFoundException, SQLException {
        DatabaseCalls dbc = new DatabaseCalls();
        dbc.dataBaseStart();
        String hours = dbc.getHours(projectIndex);
        dbc.dataBaseClose();
        return hours;
    }

}
