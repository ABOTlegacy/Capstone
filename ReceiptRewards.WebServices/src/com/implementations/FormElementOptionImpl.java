package com.implementations;

import com.common.DBConnection;
import com.model.FormElementOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FormElementOptionImpl {

    private static final String FIND_BY_FORM_ELEMENT_OPTION_ID = "SELECT * FROM form_element_option_legacy WHERE form_element_option_id = ?;";
    private static final String FIND_BY_FORM_ELEMENT_ID = "SELECT * FROM form_element_option_legacy WHERE form_element_id = ?;";
    private static final String FIND_NEXT_ID = "SELECT TOP 1 form_element_option_id FROM form_element_option_legacy ORDER BY form_element_option_id DESC;";
    private static final String CREATE = "INSERT INTO form_element_option_legacy (form_element_option_id, form_element_id, display_text_id, value) VALUES (?, ?, ?, ?);";
    private static final String UPDATE = "UPDATE form_element_option_legacy SET value = ?, display_text_id = ? WHERE form_element_option_id = ?;";
    private static final String DELETE_BY_FORM_ELEMENT_OPTION_ID = "DELETE FROM form_element_option_legacy WHERE form_element_option_id = ?;";
    private static final String DELETE_BY_FORM_ELEMENT_ID = "DELETE FROM form_element_option_legacy WHERE form_element_id = ?;";
    
    
    
    /***** Find Methods *****/
    public static FormElementOption findByFormElementOptionId(int formElementOptionId) {
        return FormElementOptionImpl.findByFormElementOptionId(formElementOptionId, null);
    }
    public static FormElementOption findByFormElementOptionId(int formElementOptionId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        FormElementOption formElementOption = new FormElementOption();

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get List By Id
            ps = con.prepareStatement(FormElementOptionImpl.FIND_BY_FORM_ELEMENT_OPTION_ID);
            ps.setInt(1, formElementOptionId);
            rs = ps.executeQuery();
            while(rs.next()) {
                formElementOption = new FormElementOption(rs.getInt("form_element_option_id"), rs.getInt("form_element_id"), DisplayTextImpl.findByDisplayTextId(rs.getInt("display_text_id"), con), rs.getString("value"));
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
        return formElementOption;
    }
    public static List<FormElementOption> findByFormElementId(int formElementId) {
        return FormElementOptionImpl.findByFormElementId(formElementId, null);
    }
    public static List<FormElementOption> findByFormElementId(int formElementId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        List<FormElementOption> formElementOptions = new ArrayList<FormElementOption>();
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get List By Id
            ps = con.prepareStatement(FormElementOptionImpl.FIND_BY_FORM_ELEMENT_ID);
            ps.setInt(1, formElementId);
            rs = ps.executeQuery();
            while(rs.next()) {
                formElementOptions.add(new FormElementOption(rs.getInt("form_element_option_id"), rs.getInt("form_element_id"), DisplayTextImpl.findByDisplayTextId(rs.getInt("display_text_id"), con), rs.getString("value")));
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
        return formElementOptions;
    }
    public static int findNextId() {
        return FormElementOptionImpl.findNextId(null);
    }
    public static int findNextId(Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        int formElementOptionId = 1;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get the Next Id
            ps = con.prepareStatement(FormElementOptionImpl.FIND_NEXT_ID);
            rs = ps.executeQuery();
            while(rs.next()) {
                formElementOptionId = rs.getInt("form_element_option_id") + 1;
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
        return formElementOptionId;
    }
    
    
    
    /***** Create Methods *****/
    public static FormElementOption create(FormElementOption formElementOption, int formElementId) {
        return FormElementOptionImpl.create(formElementOption, formElementId, null);
    }
    public static FormElementOption create(FormElementOption formElementOption, int formElementId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        // Return if null
        if(formElementOption == null || formElementId <= 0) {
            return null;
        }
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Save Dependencies
            DisplayTextImpl.create(formElementOption.getDisplayText(), con);
            
            // Set Primary Key
            formElementOption.setFormElementOptionId(FormElementOptionImpl.findNextId(con));
            
            // Create New Entry
            ps = con.prepareStatement(FormElementOptionImpl.CREATE);
            ps.setInt(1, formElementOption.getFormElementOptionId());
            ps.setInt(2, formElementId);
            ps.setInt(3, formElementOption.getDisplayText().getDisplayTextId());
            ps.setString(4, formElementOption.getValue());
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
        return formElementOption;
    }
    
    
    
    /***** Update Methods *****/
    public static FormElementOption update(FormElementOption formElementOption) {
        return FormElementOptionImpl.update(formElementOption, null);
    }
    public static FormElementOption update(FormElementOption formElementOption, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;
        
        // Return if null
        if(formElementOption == null) {
            return null;
        }

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Update the Display Text
            if(! formElementOption.getDisplayText().getDisplayTextTranslation().equals("")) {
                DisplayTextImpl.deleteByDisplayTextId(formElementOption.getDisplayText().getDisplayTextId(), con);
                DisplayTextImpl.create(formElementOption.getDisplayText(), con);
            }
            
            // Update Existing Entry
            ps = con.prepareStatement(FormElementOptionImpl.UPDATE);
            ps.setString(1, formElementOption.getValue());
            ps.setInt(2, formElementOption.getDisplayText().getDisplayTextId());
            ps.setInt(3, formElementOption.getFormElementOptionId());
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
        return formElementOption;
    }
    
    
    
    /***** Delete Methods *****/
    public static void deleteByFormElementOptionId(int formElementOptionId) {
        FormElementOptionImpl.deleteByFormElementOptionId(formElementOptionId, null);
    }
    public static void deleteByFormElementOptionId(int formElementOptionId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Delete Existing Entry
            ps = con.prepareStatement(FormElementOptionImpl.DELETE_BY_FORM_ELEMENT_OPTION_ID);
            ps.setInt(1, formElementOptionId);
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
        FormElementOptionImpl.deleteByFormElementId(formElementId, null);
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
            ps = con.prepareStatement(FormElementOptionImpl.DELETE_BY_FORM_ELEMENT_ID);
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