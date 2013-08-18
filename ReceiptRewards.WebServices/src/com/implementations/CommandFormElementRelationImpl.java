package com.implementations;

import com.common.DBConnection;
import com.model.CommandFormElementRelation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CommandFormElementRelationImpl {

    private static final String FIND_BY_COMMAND_ELEMENT_ID = "SELECT * FROM command_form_element_relation_legacy WHERE command_element_id = ?;";
    private static final String FIND_BY_FORM_ELEMENT_ID = "SELECT * FROM command_form_element_relation_legacy WHERE form_element_id;";
    private static final String FIND_NEXT_ID = "SELECT TOP 1 command_form_element_relation_id FROM command_form_element_relation_legacy ORDER BY command_form_element_relation_id DESC;";
    private static final String CREATE = "INSERT INTO command_form_element_relation_legacy (command_form_element_relation_id, command_element_id, form_element_id) VALUES (?, ?, ?);";
    private static final String DELETE_BY_COMMAND_FORM_ELEMENT_RELATION_ID = "DELETE FROM command_form_element_relation_legacy WHERE command_form_element_relation_id = ?;";
    private static final String DELETE_BY_COMMAND_ELEMENT_ID = "DELETE FROM command_form_element_relation_legacy WHERE command_element_id = ?;";
    private static final String DELETE_BY_FORM_ELEMENT_ID = "DELETE FROM command_form_element_relation_legacy WHERE form_element_id = ?;";
    
    
    
    /***** Find Methods *****/
    public static List<CommandFormElementRelation> findByCommandElementId(int commandElementId) {
        return CommandFormElementRelationImpl.findByCommandElementId(commandElementId, null);
    }
    public static List<CommandFormElementRelation> findByCommandElementId(int commandElementId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        List<CommandFormElementRelation> commandFormElementRelations = new ArrayList<CommandFormElementRelation>();

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get List By Id
            ps = con.prepareStatement(CommandFormElementRelationImpl.FIND_BY_COMMAND_ELEMENT_ID);
            ps.setInt(1, commandElementId);
            rs = ps.executeQuery();
            while(rs.next()) {
                commandFormElementRelations.add(new CommandFormElementRelation(rs.getInt("command_form_element_relation_id"), rs.getInt("command_element_id"), rs.getInt("form_element_id")));
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
        return commandFormElementRelations;
    }
    public static List<CommandFormElementRelation> findByFormElementId(int formElementId) {
        return CommandFormElementRelationImpl.findByFormElementId(formElementId, null);
    }
    public static List<CommandFormElementRelation> findByFormElementId(int formElementId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        List<CommandFormElementRelation> commandFormElementRelations = new ArrayList<CommandFormElementRelation>();

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get List By Id
            ps = con.prepareStatement(CommandFormElementRelationImpl.FIND_BY_FORM_ELEMENT_ID);
            ps.setInt(1, formElementId);
            rs = ps.executeQuery();
            while(rs.next()) {
                commandFormElementRelations.add(new CommandFormElementRelation(rs.getInt("command_form_element_relation_id"), rs.getInt("command_element_id"), rs.getInt("form_element_id")));
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
        return commandFormElementRelations;
    }
    public static int findNextId() {
        return CommandFormElementRelationImpl.findNextId(null);
    }
    public static int findNextId(Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        int commandFormElementRelationId = 1;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get The Next Id
            ps = con.prepareStatement(CommandFormElementRelationImpl.FIND_NEXT_ID);
            rs = ps.executeQuery();
            while(rs.next()) {
                commandFormElementRelationId = rs.getInt("command_form_element_relation_id") + 1;
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
        return commandFormElementRelationId;
    }
    
    
    
    /***** Create Methods *****/
    public static CommandFormElementRelation create(CommandFormElementRelation commandFormElementRelation) {
        return CommandFormElementRelationImpl.create(commandFormElementRelation, null);
    }
    public static CommandFormElementRelation create(CommandFormElementRelation commandFormElementRelation, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        // Return
        if(commandFormElementRelation == null) {
            return null;
        }
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Set the next id
            commandFormElementRelation.setCommandFormElementRelationId(CommandFormElementRelationImpl.findNextId(con));
            
            // Create New Entry
            ps = con.prepareStatement(CommandFormElementRelationImpl.CREATE);
            ps.setInt(1, commandFormElementRelation.getCommandFormElementRelationId());
            ps.setInt(2, commandFormElementRelation.getCommandElementId());
            ps.setInt(3, commandFormElementRelation.getFormElementId());
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
        return commandFormElementRelation;
    }
    
    
    
    /***** Delete Methods *****/
    public static void deleteByCommandFormElementRelationId(int commandFormElementRelationId) {
        CommandFormElementRelationImpl.deleteByCommandFormElementRelationId(commandFormElementRelationId, null);
    }
    public static void deleteByCommandFormElementRelationId(int commandFormElementRelationId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Delete Existing Entry
            ps = con.prepareStatement(CommandFormElementRelationImpl.DELETE_BY_COMMAND_FORM_ELEMENT_RELATION_ID);
            ps.setInt(1, commandFormElementRelationId);
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
        CommandFormElementRelationImpl.deleteByCommandElementId(commandElementId, null);
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
            ps = con.prepareStatement(CommandFormElementRelationImpl.DELETE_BY_COMMAND_ELEMENT_ID);
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
    public static void deleteByFormElementId(int formElementId) {
        CommandFormElementRelationImpl.deleteByFormElementId(formElementId, null);
    }
    public static void deleteByFormElementId(int formElementId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Delete Existing Entry
            ps = con.prepareStatement(CommandFormElementRelationImpl.DELETE_BY_FORM_ELEMENT_ID);
            ps.setInt(1, formElementId);
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