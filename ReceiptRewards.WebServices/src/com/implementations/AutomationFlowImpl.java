package com.implementations;

import com.common.DBConnection;
import com.model.AutomationFlow;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AutomationFlowImpl {

    private static final String FIND_BY_AUTOMATION_FLOW_ID = "SELECT * FROM automation_flow_legacy WHERE automation_flow_id = ? ORDER BY weight ASC;";
    private static final String FIND_BY_AUTOMATION_ID = "SELECT * FROM automation_flow_legacy WHERE automation_id = ? ORDER BY weight ASC;";
    private static final String FIND_NEXT_ID = "SELECT TOP 1 automation_flow_id FROM automation_flow_legacy ORDER BY automation_flow_id DESC;";
    private static final String CREATE = "INSERT INTO automation_flow_legacy (automation_flow_id, automation_id, question_id, weight) VALUES (?, ?, ?, ?);";
    private static final String DELETE_BY_AUTOMATION_FLOW_ID = "DELETE FROM automation_flow_legacy WHERE automation_flow_id = ?;";
    private static final String DELETE_BY_AUTOMATION_ID = "DELETE FROM automation_flow_legacy WHERE automation_id = ?;";
    private static final String DELETE_BY_QUESTION_ID = "DELETE FROM automation_flow_legacy WHERE question_id = ?;";
    
    
    
    /***** Find Methods *****/
    public static AutomationFlow findByAutomationFlowId(int automationFlowId) {
        return AutomationFlowImpl.findByAutomationFlowId(automationFlowId, null);
    }
    public static AutomationFlow findByAutomationFlowId(int automationFlowId, Connection con) {
        return AutomationFlowImpl.findByAutomationFlowId(automationFlowId, 1000, con);
    }
    public static AutomationFlow findByAutomationFlowId(int automationFlowId, int recursiveLevel) {
        return AutomationFlowImpl.findByAutomationFlowId(automationFlowId, recursiveLevel, null);
    }
    public static AutomationFlow findByAutomationFlowId(int automationFlowId, int recursiveLevel, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        AutomationFlow automationFlow = new AutomationFlow();

        // Verify
        if(automationFlowId <= 0 || recursiveLevel <= 0) {
            return null;
        }
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get List By Id
            ps = con.prepareStatement(AutomationFlowImpl.FIND_BY_AUTOMATION_FLOW_ID);
            ps.setInt(1, automationFlowId);
            rs = ps.executeQuery();
            while(rs.next()) {
                automationFlow = new AutomationFlow(rs.getInt("automation_flow_id"), rs.getInt("automation_id"), QuestionImpl.findByQuestionId(rs.getInt("question_id"), (recursiveLevel - 1), con), rs.getInt("weight"));
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
        return automationFlow;
    }
    public static List<AutomationFlow> findByAutomationId(int automationId) {
        return AutomationFlowImpl.findByAutomationId(automationId, null);
    }
    public static List<AutomationFlow> findByAutomationId(int automationId, Connection con) {
        return AutomationFlowImpl.findByAutomationId(automationId, 1000, null);
    }
    public static List<AutomationFlow> findByAutomationId(int automationId, int recursiveLevel) {
        return AutomationFlowImpl.findByAutomationId(automationId, recursiveLevel, null);
    }
    public static List<AutomationFlow> findByAutomationId(int automationId, int recursiveLevel, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        List<AutomationFlow> automationFlows = new ArrayList<AutomationFlow>();

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
            ps = con.prepareStatement(AutomationFlowImpl.FIND_BY_AUTOMATION_ID);
            ps.setInt(1, automationId);
            rs = ps.executeQuery();
            while(rs.next()) {
                automationFlows.add(new AutomationFlow(rs.getInt("automation_flow_id"), rs.getInt("automation_id"), QuestionImpl.findByQuestionId(rs.getInt("question_id"), (recursiveLevel - 1), con), rs.getInt("weight")));
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
        return automationFlows;
    }
    public static int findNextId() {
        return AutomationFlowImpl.findNextId(null);
    }
    public static int findNextId(Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        int automationFlowId = 1;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get All the Code Types
            ps = con.prepareStatement(AutomationFlowImpl.FIND_NEXT_ID);
            rs = ps.executeQuery();
            while(rs.next()) {
                automationFlowId = rs.getInt("automation_flow_id") + 1;
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
        return automationFlowId;
    }
    
    
    
    /***** Create Methods *****/
    public static AutomationFlow create(AutomationFlow automationFlow) {
        return AutomationFlowImpl.create(automationFlow, null);
    }
    public static AutomationFlow create(AutomationFlow automationFlow, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;
        
        // Return if null
        if(automationFlow == null) {
            return null;
        }

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Set Primary Key
            automationFlow.setAutomationFlowId(AutomationFlowImpl.findNextId(con));
            
            // Create New Entry
            ps = con.prepareStatement(AutomationFlowImpl.CREATE);
            ps.setInt(1, automationFlow.getAutomationFlowId());
            ps.setInt(2, automationFlow.getAutomationId());
            ps.setInt(3, automationFlow.getQuestion().getQuestionId());
            ps.setInt(4, automationFlow.getWeight());
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
        
        // Return
        return automationFlow;
    }
    
    
    
    /***** Delete Methods *****/
    public static void deleteByAutomationFlowId(int automationFlowId) {
        AutomationFlowImpl.deleteByAutomationFlowId(automationFlowId, null);
    }
    public static void deleteByAutomationFlowId(int automationFlowId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Delete Existing Entry
            ps = con.prepareStatement(AutomationFlowImpl.DELETE_BY_AUTOMATION_FLOW_ID);
            ps.setInt(1, automationFlowId);
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
    public static void deleteByAutomationId(int automationId) {
        AutomationFlowImpl.deleteByAutomationId(automationId, null);
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
            
            // Delete Existing Entry
            ps = con.prepareStatement(AutomationFlowImpl.DELETE_BY_AUTOMATION_ID);
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
        AutomationFlowImpl.deleteByQuestionId(questionId, null);
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
            ps = con.prepareStatement(AutomationFlowImpl.DELETE_BY_QUESTION_ID);
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
}