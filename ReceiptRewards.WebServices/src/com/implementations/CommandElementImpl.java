package com.implementations;

import com.common.DBConnection;
import com.model.CommandElement;
import com.model.CommandIdentifier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CommandElementImpl {

    private static final String FIND_BY_COMMAND_ELEMENT_ID = "SELECT * FROM command_element_legacy WHERE command_element_id = ?;";
    private static final String FIND_BY_AUTOMATION_ID = "SELECT * FROM command_element_legacy ce JOIN command_elements_legacy c ON c.automation_id = ? AND c.command_element_id = ce.command_element_id ORDER BY c.weight ASC;";
    private static final String FIND_NEXT_ID = "SELECT TOP 1 command_element_id FROM command_element_legacy ORDER BY command_element_id DESC;";
    private static final String CREATE = "INSERT INTO command_element_legacy (command_element_id, code_id, test_data) VALUES (?, ?, ?);";
    private static final String UPDATE = "UPDATE command_element_legacy SET code_id = ?, test_data = ? WHERE command_element_id = ?;";
    private static final String DELETE = "DELETE FROM command_element_legacy WHERE command_element_id = ?;";
    
    
    
    /***** Find Methods *****/
    public static CommandElement findByCommandElementId(int commandElementId) {
        return CommandElementImpl.findByCommandElementId(commandElementId, null);
    }
    public static CommandElement findByCommandElementId(int commandElementId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        CommandElement commandElement = new CommandElement();

        // Verify
        if(commandElementId <= 0) {
            return null;
        }
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get List By Id
            ps = con.prepareStatement(CommandElementImpl.FIND_BY_COMMAND_ELEMENT_ID);
            ps.setInt(1, commandElementId);
            rs = ps.executeQuery();
            while(rs.next()) {
                commandElement = new CommandElement(rs.getInt("command_element_id"), CodeImpl.findByCodeId(rs.getInt("code_id"), con), CommandIdentifierImpl.findByCommandElementId(rs.getInt("command_element_id"), con), CommandFormElementRelationImpl.findByCommandElementId(rs.getInt("command_element_id"), con), rs.getString("test_data"));
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
        return commandElement;
    }
    public static List<CommandElement> findByAutomationId(int automationId) {
        return CommandElementImpl.findByAutomationId(automationId, null);
    }
    public static List<CommandElement> findByAutomationId(int automationId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        List<CommandElement> commandElements = new ArrayList<CommandElement>();

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get List By Id
            ps = con.prepareStatement(CommandElementImpl.FIND_BY_AUTOMATION_ID);
            ps.setInt(1, automationId);
            rs = ps.executeQuery();
            while(rs.next()) {
                commandElements.add(new CommandElement(rs.getInt("command_element_id"), CodeImpl.findByCodeId(rs.getInt("code_id"), con), CommandIdentifierImpl.findByCommandElementId(rs.getInt("command_element_id"), con), CommandFormElementRelationImpl.findByCommandElementId(rs.getInt("command_element_id"), con), rs.getString("test_data")));
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
        return commandElements;
    }
    public static int findNextId() {
        return CommandElementImpl.findNextId(null);
    }
    public static int findNextId(Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        int commandElementId = 1;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get All the Code Types
            ps = con.prepareStatement(CommandElementImpl.FIND_NEXT_ID);
            rs = ps.executeQuery();
            while(rs.next()) {
                commandElementId = rs.getInt("command_element_id") + 1;
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
        return commandElementId;
    }
    
    
    
    /***** Create Methods *****/
    public static CommandElement create(CommandElement commandElement, int automationId) {
        return CommandElementImpl.create(commandElement, automationId, null);
    }
    public static CommandElement create(CommandElement commandElement, int automationId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;
        
        // Return if null
        if(commandElement == null || automationId <= 0 || commandElement.getCode() == null) {
            return null;
        }
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Set ID
            commandElement.setCommandElementId(CommandElementImpl.findNextId(con));
            
            // Create Identifiers
            if(commandElement.getCommandIdentifiers() == null) {
                commandElement.setCommandIdentifiers(new ArrayList<CommandIdentifier>());
            }
            for(int i = 0; i < commandElement.getCommandIdentifiers().size(); i++) {
                CommandIdentifierImpl.create(commandElement.getCommandIdentifiers().get(i), commandElement.getCommandElementId(), con);
            }
            
            // Complete the Code Object
            if(commandElement.getCode().getCodeId() <= 0) {
                commandElement.setCode(CodeImpl.findByCode(commandElement.getCode(), con));
            }
            
            // Create New Entry
            ps = con.prepareStatement(CommandElementImpl.CREATE);
            ps.setInt(1, commandElement.getCommandElementId());
            ps.setInt(2, commandElement.getCode().getCodeId());
            ps.setString(3, commandElement.getTestData());
            ps.executeUpdate();
            ps.close();
            
            // Set Up Dependent Entries
            CommandElementsImpl.create(automationId, commandElement.getCommandElementId(), con);
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
        return commandElement;
    }
    public static CommandElement createFromExisting(int commandElementId, int automationId) {
        return CommandElementImpl.createFromExisting(commandElementId, automationId, null);
    }
    public static CommandElement createFromExisting(int commandElementId, int automationId, Connection con) {
        // Instantiate Variables
        boolean conBool = false;
        
        // Return if null
        if(commandElementId <= 0 || automationId <= 0) {
            return null;
        }
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Set Up Dependent Entries
            CommandElementsImpl.create(automationId, commandElementId, con);
            
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
        return new CommandElement(commandElementId);
    }
    
    
    
    /***** Update Methods *****/
    public static CommandElement update(CommandElement commandElement) {
        return CommandElementImpl.update(commandElement, null);
    }
    public static CommandElement update(CommandElement commandElement, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;
        
        // Return if null
        if(commandElement == null) {
            return null;
        }

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Complete the Code Object
            if(commandElement.getCode().getCodeId() <= 0) {
                commandElement.setCode(CodeImpl.findByCode(commandElement.getCode(), con));
            }
            
            // Update Existing Entry
            ps = con.prepareStatement(CommandElementImpl.UPDATE);
            ps.setInt(1, commandElement.getCode().getCodeId());
            ps.setString(2, commandElement.getTestData());
            ps.setInt(3, commandElement.getCommandElementId());
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
        return commandElement;
    }
    
    
    
    /***** Delete Methods *****/
    public static void deleteByCommandElementId(int commandElementId) {
        CommandElementImpl.deleteByCommandElementId(commandElementId, null);
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
            
            // Delete Dependant Entries
            CommandIdentifierImpl.deleteByCommandElementId(commandElementId, con);
            CommandElementsImpl.deleteByCommandElementId(commandElementId, con);
            
            // Delete Existing Entry
            ps = con.prepareStatement(CommandElementImpl.DELETE);
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