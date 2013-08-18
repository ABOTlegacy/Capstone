package com.implementations;

import com.common.DBConnection;
import com.model.CommandIdentifier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CommandIdentifierImpl {

    private static final String FIND_BY_COMMAND_ELEMENT_ID = "SELECT * FROM command_identifier_legacy WHERE command_element_id = ?;";
    private static final String FIND_BY_COMMAND_IDENTIFIER_ID = "SELECT * FROM command_identifier_legacy WHERE command_identifier_id = ?;";
    private static final String FIND_NEXT_ID = "SELECT TOP 1 command_identifier_id FROM command_identifier_legacy ORDER BY command_identifier_id DESC;";
    private static final String CREATE = "INSERT INTO command_identifier_legacy (command_identifier_id, command_element_id, code_id, value) VALUES (?, ?, ?, ?);";
    private static final String UPDATE = "UPDATE command_identifier_legacy SET code_id = ?, value = ? WHERE command_identifier_id = ?;";
    private static final String DELETE_BY_COMMAND_IDENTIFIER_ID = "DELETE FROM command_identifier_legacy WHERE command_identifier_id = ?;";
    private static final String DELETE_BY_COMMAND_ELEMENT_ID = "DELETE FROM command_identifier_legacy WHERE command_element_id = ?;";
    
    
    
    /***** Find Methods *****/
    public static List<CommandIdentifier> findByCommandElementId(int commandElementId) {
        return CommandIdentifierImpl.findByCommandElementId(commandElementId, null);
    }
    public static List<CommandIdentifier> findByCommandElementId(int commandElementId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        List<CommandIdentifier> commandIdentifiers = new ArrayList<CommandIdentifier>();

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get List By Id
            ps = con.prepareStatement(CommandIdentifierImpl.FIND_BY_COMMAND_ELEMENT_ID);
            ps.setInt(1, commandElementId);
            rs = ps.executeQuery();
            while(rs.next()) {
                commandIdentifiers.add(new CommandIdentifier(rs.getInt("command_identifier_id"), rs.getInt("command_element_id"), CodeImpl.findByCodeId(rs.getInt("code_id"), con), rs.getString("value")));
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
        return commandIdentifiers;
    }
    public static CommandIdentifier findByCommandIdentifierId(int commandIdentifierId) {
        return CommandIdentifierImpl.findByCommandIdentifierId(commandIdentifierId, null);
    }
    public static CommandIdentifier findByCommandIdentifierId(int commandIdentifierId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        CommandIdentifier commandIdentifier = new CommandIdentifier();

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get List By Id
            ps = con.prepareStatement(CommandIdentifierImpl.FIND_BY_COMMAND_IDENTIFIER_ID);
            ps.setInt(1, commandIdentifierId);
            rs = ps.executeQuery();
            while(rs.next()) {
                commandIdentifier = new CommandIdentifier(rs.getInt("command_identifier_id"), rs.getInt("command_element_id"), CodeImpl.findByCodeId(rs.getInt("code_id"), con), rs.getString("value"));
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
        return commandIdentifier;
    }
    public static int findNextId() {
        return CommandIdentifierImpl.findNextId(null);
    }
    public static int findNextId(Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        int commandIdentifierId = 1;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get Next Id
            ps = con.prepareStatement(CommandIdentifierImpl.FIND_NEXT_ID);
            rs = ps.executeQuery();
            while(rs.next()) {
                commandIdentifierId = rs.getInt("command_identifier_id") + 1;
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
        return commandIdentifierId;
    }
    
    
    
    /***** Create Methods *****/
    public static CommandIdentifier create(CommandIdentifier commandIdentifier, int commandElementId) {
        return CommandIdentifierImpl.create(commandIdentifier, commandElementId, null);
    }
    public static CommandIdentifier create(CommandIdentifier commandIdentifier, int commandElementId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;
        
        // Return if null
        if(commandIdentifier == null || commandElementId <= 0) {
            return null;
        }

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Set Primary Key
            commandIdentifier.setCommandIdentifierId(CommandIdentifierImpl.findNextId(con));
            
            // Set Object
            commandIdentifier.setCommandElementId(commandElementId);
            
            // Complete the Code Object
            if(commandIdentifier.getCode().getCodeId() <= 0) {
                commandIdentifier.setCode(CodeImpl.findByCode(commandIdentifier.getCode(), con));
            }
            
            // Create New Entry
            ps = con.prepareStatement(CommandIdentifierImpl.CREATE);
            ps.setInt(1, commandIdentifier.getCommandIdentifierId());
            ps.setInt(2, commandIdentifier.getCommandElementId());
            ps.setInt(3, commandIdentifier.getCode().getCodeId());
            ps.setString(4, commandIdentifier.getValue());
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
        return commandIdentifier;
    }
    
    
    
    /***** Update Methods *****/
    public static CommandIdentifier update(CommandIdentifier commandIdentifier) {
        return CommandIdentifierImpl.update(commandIdentifier, null);
    }
    public static CommandIdentifier update(CommandIdentifier commandIdentifier, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        // Return if null
        if(commandIdentifier == null) {
            return null;
        }
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Complete the Code Object
            if(commandIdentifier.getCode().getCodeId() <= 0) {
                commandIdentifier.setCode(CodeImpl.findByCode(commandIdentifier.getCode(), con));
            }
            
            // Update Existing Entry
            ps = con.prepareStatement(CommandIdentifierImpl.UPDATE);
            ps.setInt(1, commandIdentifier.getCode().getCodeId());
            ps.setString(2, commandIdentifier.getValue());
            ps.setInt(3, commandIdentifier.getCommandIdentifierId());
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
        return commandIdentifier;
    }
    
    
    
    /***** Delete Methods *****/
    public static void deleteByCommandIdentifierId(int commandIdentifierId) {
        CommandIdentifierImpl.deleteByCommandIdentifierId(commandIdentifierId, null);
    }
    public static void deleteByCommandIdentifierId(int commandIdentifierId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Delete Existing Entry
            ps = con.prepareStatement(CommandIdentifierImpl.DELETE_BY_COMMAND_IDENTIFIER_ID);
            ps.setInt(1, commandIdentifierId);
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
        CommandIdentifierImpl.deleteByCommandElementId(commandElementId, null);
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
            ps = con.prepareStatement(CommandIdentifierImpl.DELETE_BY_COMMAND_ELEMENT_ID);
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