package com.implementations;

import com.common.DBConnection;
import com.model.Automation;
import com.model.CommandElement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AutomationImpl {

    private static final String FIND_BY_AUTOMATION_ID = "SELECT * FROM automation_legacy WHERE automation_id = ?;";
    private static final String FIND_BY_QUESTION_ID = "SELECT * FROM automation_legacy a JOIN question_legacy q ON q.automation_id = a.automation_id WHERE q.question_id = ?;";
    private static final String FIND_BY_REVISION_ID = "SELECT * FROM automation_legacy a JOIN question_legacy q ON q.automation_id = a.automation_id JOIN revision_legacy r ON r.revision_id = q.revision_id WHERE r.revision_id = ?;";
    private static final String FIND_NEXT_ID = "SELECT TOP 1 automation_id FROM automation_legacy ORDER BY automation_id DESC;";
    private static final String CREATE = "INSERT INTO automation_legacy (automation_id) VALUES (?);";
    private static final String DELETE_BY_AUTOMATION_ID = "DELETE FROM automation_legacy WHERE automation_id = ?;";
    private static final String DELETE_BY_QUESTION_ID = "DELETE FROM automation_legacy a JOIN question_legacy q ON q.automation_id = a.automation_id WHERE q.question_id = ?;";
    private static final String DELETE_BY_REVISION_ID = "DELETE FROM automation_legacy a JOIN question_legacy q ON q.automation_id = a.automation_id JOIN revision_legacy r ON r.revision_id = q.revision_id WHERE r.revision_id = ?;";
    
    
    
    /***** Find Methods *****/
    public static Automation findByAutomationId(int automationId) {
        return AutomationImpl.findByAutomationId(automationId, null);
    }
    public static Automation findByAutomationId(int automationId, Connection con) {
        return AutomationImpl.findByAutomationId(automationId, 1000, con);
    }
    public static Automation findByAutomationId(int automationId, int recursiveLevel) {
        return AutomationImpl.findByAutomationId(automationId, recursiveLevel, null);
    }
    public static Automation findByAutomationId(int automationId, int recursiveLevel, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        Automation automation = new Automation();

        // Verify
        if(automationId <= 0 || recursiveLevel <= 0) {
            return null;
        }
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get List By Id
            ps = con.prepareStatement(AutomationImpl.FIND_BY_AUTOMATION_ID);
            ps.setInt(1, automationId);
            rs = ps.executeQuery();
            while(rs.next()) {
                automation = new Automation(rs.getInt("automation_id"), CommandElementImpl.findByAutomationId(rs.getInt("automation_id"), con), AutomationFlowImpl.findByAutomationId(rs.getInt("automation_id"), (recursiveLevel - 1), con));
            }
            rs.close();
            ps.close();
        } catch(Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            // @TODO: Handle Exception
        } finally {
            try {
                if(con != null && conBool) {
                    con.close();
                    con = null;
                }
            } catch(Exception ex){}
        }
        
        // Return
        return automation;
    }
    public static Automation findByQuestionId(int questionId) {
        return AutomationImpl.findByQuestionId(questionId, null);
    }
    public static Automation findByQuestionId(int questionId, Connection con) {
        return AutomationImpl.findByQuestionId(questionId, 1000, con);
    }
    public static Automation findByQuestionId(int questionId, int recursiveLevel) {
        return AutomationImpl.findByQuestionId(questionId, recursiveLevel, null);
    }
    public static Automation findByQuestionId(int questionId, int recursiveLevel, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        Automation automations = new Automation();

        // Verify
        if(questionId <= 0 || recursiveLevel <= 0) {
            return null;
        }
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get List By Id
            ps = con.prepareStatement(AutomationImpl.FIND_BY_QUESTION_ID);
            ps.setInt(1, questionId);
            rs = ps.executeQuery();
            while(rs.next()) {
                automations = new Automation(rs.getInt("automation_id"), CommandElementImpl.findByAutomationId(rs.getInt("automation_id"), con), AutomationFlowImpl.findByAutomationId(rs.getInt("automation_id"), (recursiveLevel - 1), con));
            }
            rs.close();
            ps.close();
        } catch(Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            // @TODO: Handle Exception
        } finally {
            try {
                if(con != null && conBool) {
                    con.close();
                    con = null;
                }
            } catch(Exception ex){}
        }
        
        // Return
        return automations;
    }
    public static Automation findByRevisionId(int revisionId) {
        return AutomationImpl.findByRevisionId(revisionId, null);
    }
    public static Automation findByRevisionId(int revisionId, Connection con) {
        return AutomationImpl.findByRevisionId(revisionId, 1000, con);
    }
    public static Automation findByRevisionId(int revisionId, int recursiveLevel) {
        return AutomationImpl.findByRevisionId(revisionId, recursiveLevel, null);
    }
    public static Automation findByRevisionId(int revisionId, int recursiveLevel, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        Automation automation = new Automation();

        // Verify
        if(revisionId <= 0 || recursiveLevel <= 0) {
            return null;
        }
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get List By Id
            ps = con.prepareStatement(AutomationImpl.FIND_BY_REVISION_ID);
            ps.setInt(1, revisionId);
            rs = ps.executeQuery();
            while(rs.next()) {
                automation = new Automation(rs.getInt("automation_id"), CommandElementImpl.findByAutomationId(rs.getInt("automation_id"), con), AutomationFlowImpl.findByAutomationId(rs.getInt("automation_id"), (recursiveLevel - 1), con));
            }
            rs.close();
            ps.close();
        } catch(Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            // @TODO: Handle Exception
        } finally {
            try {
                if(con != null && conBool) {
                    con.close();
                    con = null;
                }
            } catch(Exception ex){}
        }
        
        // Return
        return automation;
    }
    public static int findNextId() {
        return AutomationImpl.findNextId(null);
    }
    public static int findNextId(Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        int automationId = 1;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get All the Code Types
            ps = con.prepareStatement(AutomationImpl.FIND_NEXT_ID);
            rs = ps.executeQuery();
            while(rs.next()) {
                automationId = rs.getInt("automation_id") + 1;
            }
            rs.close();
            ps.close();
        } catch(Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            // @TODO: Handle Exception
        } finally {
            try {
                if(con != null && conBool) {
                    con.close();
                    con = null;
                }
            } catch(Exception ex){}
        }
        
        // Return
        return automationId;
    }
    
    
    
    /***** Create Methods *****/
    public static Automation create(Automation automation) {
        return AutomationImpl.create(automation, null);
    }
    public static Automation create(Automation automation, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;
        
        // Return if null
        if(automation == null) {
            return null;
        }
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Set ID
            automation.setAutomationId(AutomationImpl.findNextId(con));
            
            // Create Command Elements
            if(automation.getCommandElements() == null) {
                automation.setCommandElements(new ArrayList<CommandElement>());
            }
            for(int i = 0; i < automation.getCommandElements().size(); i++) {
                CommandElementImpl.create(automation.getCommandElements().get(i), automation.getAutomationId(), con);
            }
            
            // Create New Entry
            ps = con.prepareStatement(AutomationImpl.CREATE);
            ps.setInt(1, automation.getAutomationId());
            ps.executeUpdate();
            ps.close();
        } catch(Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            // @TODO: Handle Exception
        } finally {
            try {
                if(con != null && conBool) {
                    con.close();
                    con = null;
                }
            } catch(Exception ex){}
        }
        return automation;
    }
    
    
    
    /***** Delete Methods *****/
    public static void deleteByAutomationId(int automationId) {
        AutomationImpl.deleteByAutomationId(automationId, null);
    }
    public static void deleteByAutomationId(int automationId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Delete Depended Entries
            CommandElementsImpl.deleteByAutomationId(automationId, con);
            
            // Delete Existing Entry
            ps = con.prepareStatement(AutomationImpl.DELETE_BY_AUTOMATION_ID);
            ps.setInt(1, automationId);
            ps.executeUpdate();
            ps.close();
        } catch(Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            // @TODO: Handle Exception
        } finally {
            try {
                if(con != null && conBool) {
                    con.close();
                    con = null;
                }
            } catch(Exception ex){}
        }
    }
    public static void deleteByQuestionId(int questionId) {
        AutomationImpl.deleteByQuestionId(questionId, null);
    }
    public static void deleteByQuestionId(int questionId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Delete Existing Entry
            ps = con.prepareStatement(AutomationImpl.DELETE_BY_QUESTION_ID);
            ps.setInt(1, questionId);
            ps.executeUpdate();
            ps.close();
        } catch(Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            // @TODO: Handle Exception
        } finally {
            try {
                if(con != null && conBool) {
                    con.close();
                    con = null;
                }
            } catch(Exception ex){}
        }
    }
    public static void deleteByRevisionId(int revisionId) {
        AutomationImpl.deleteByRevisionId(revisionId, null);
    }
    public static void deleteByRevisionId(int revisionId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Delete Existing Entry
            ps = con.prepareStatement(AutomationImpl.DELETE_BY_REVISION_ID);
            ps.setInt(1, revisionId);
            ps.executeUpdate();
            ps.close();
        } catch(Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            // @TODO: Handle Exception
        } finally {
            try {
                if(con != null && conBool) {
                    con.close();
                    con = null;
                }
            } catch(Exception ex){}
        }
    }
}