package com.implementations;

import com.common.DBConnection;
import com.model.Automation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CommandElementsImpl {

    //private static final String FIND_BY_AUTOMATION_ID = "SELECT * FROM commands WHERE automation_id = ?;";
    private static final String FIND_NEXT_ID = "SELECT TOP 1 command_elements_id FROM command_elements_legacy ORDER BY command_elements_id DESC;";
    private static final String FIND_NEXT_WEIGHT = "SELECT TOP 1 weight FROM command_elements_legacy WHERE automation_id = ? ORDER BY weight DESC;";
    private static final String CREATE = "INSERT INTO command_elements_legacy (command_elements_id, automation_id, command_element_id, weight) VALUES (?, ?, ?, ?);";
    private static final String UPDATE_WEIGHT = "UPDATE command_elements_legacy SET weight = ? WHERE automation_id = ? and command_element_id = ?;";
    private static final String DELETE_BY_AUTOMATION_ID = "DELETE FROM command_elements_legacy WHERE automation_id = ?;";
    private static final String DELETE_BY_COMMAND_ELEMENT_ID = "DELETE FROM command_elements_legacy WHERE command_element_id = ?;";
    
    
    
    /***** Find Methods *****/
    public static int findNextId() {
        return CommandElementsImpl.findNextId(null);
    }
    public static int findNextId(Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        int surveyId = 1;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get All the Code Types
            ps = con.prepareStatement(CommandElementsImpl.FIND_NEXT_ID);
            rs = ps.executeQuery();
            while(rs.next()) {
                surveyId = rs.getInt("command_elements_id") + 1;
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
        return surveyId;
    }
    public static int findNextWeight(int automationId) {
        return CommandElementsImpl.findNextWeight(automationId, null);
    }
    public static int findNextWeight(int automationId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        int weight = 1;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get All the Code Types
            ps = con.prepareStatement(CommandElementsImpl.FIND_NEXT_WEIGHT);
            ps.setInt(1, automationId);
            rs = ps.executeQuery();
            while(rs.next()) {
                weight = rs.getInt("weight") + 1;
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
        return weight;
    }
    
    
    
    /***** Create Methods *****/
    public static void create(int automationId, int commandElementId) {
        CommandElementsImpl.create(automationId, commandElementId, null);
    }
    public static void create(int automationId, int commandElementId, Connection con) {
        CommandElementsImpl.create(automationId, commandElementId, CommandElementsImpl.findNextWeight(automationId), con);
    }
    public static void create(int automationId, int commandElementId, int weight) {
        CommandElementsImpl.create(automationId, commandElementId, weight, null);
    }
    public static void create(int automationId, int commandElementId, int weight, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;
        
        // Return if null
        if(automationId <= 0 || commandElementId <= 0) {
            return;
        }

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Create New Entry
            ps = con.prepareStatement(CommandElementsImpl.CREATE);
            ps.setInt(1, CommandElementsImpl.findNextId(con));
            ps.setInt(2, automationId);
            ps.setInt(3, commandElementId);
            ps.setInt(4, weight);
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
    
    
    
    /***** Update Methods *****/
    public static void updateOrder(Automation automation) {
        CommandElementsImpl.updateOrder(automation, null);
    }
    public static void updateOrder(Automation automation, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Create New Entry
            for(int i = 0; i < automation.getCommandElements().size(); i++) {
                ps = con.prepareStatement(CommandElementsImpl.UPDATE_WEIGHT);
                ps.setInt(1, i);
                ps.setInt(2, automation.getAutomationId());
                ps.setInt(3, automation.getCommandElements().get(i).getCommandElementId());
                ps.executeUpdate();
                ps.close();
            }
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
    
    
    
    /***** Delete Methods *****/
    public static void deleteByAutomationId(int automationId) {
        CommandElementsImpl.deleteByAutomationId(automationId, null);
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
            ps = con.prepareStatement(CommandElementsImpl.DELETE_BY_AUTOMATION_ID);
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
    public static void deleteByCommandElementId(int commandElementId) {
        CommandElementsImpl.deleteByCommandElementId(commandElementId, null);
    }
    public static void deleteByCommandElementId(int commandElementId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Delete Existing Entry
            ps = con.prepareStatement(CommandElementsImpl.DELETE_BY_COMMAND_ELEMENT_ID);
            ps.setInt(1, commandElementId);
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
