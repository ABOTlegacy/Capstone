package com.implementations;

import com.common.DBConnection;
import com.model.FormElementAttribute;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FormElementAttributeImpl {

    private static final String FIND_BY_FORM_ELEMENT_ATTRIBUTE_ID = "SELECT * FROM form_element_attribute_legacy WHERE form_element_attribute_id = ?;";
    private static final String FIND_BY_FORM_ELEMENT_ID = "SELECT * FROM form_element_attribute_legacy WHERE form_element_id = ?;";
    private static final String FIND_NEXT_ID = "SELECT TOP 1 form_element_attribute_id FROM form_element_attribute_legacy ORDER BY form_element_attribute_id DESC;";
    private static final String CREATE = "INSERT INTO form_element_attribute_legacy (form_element_attribute_id, form_element_id, code_id, value) VALUES (?, ?, ?, ?);";
    private static final String UPDATE = "UPDATE form_element_attribute_legacy SET value = ? WHERE form_element_attribute_id = ?;";
    private static final String DELETE_BY_FORM_ELEMENT_ATTRIBUTE_ID = "DELETE FROM form_element_attribute_legacy WHERE form_element_attribute_id = ?;";
    private static final String DELETE_BY_FORM_ELEMENT_ID = "DELETE FROM form_element_attribute_legacy WHERE form_element_id = ?;";
    
    
    
    /***** Find Methods *****/
    public static FormElementAttribute findByFormElementAttributeId(int formElementAttributeId) {
        return FormElementAttributeImpl.findByFormElementAttributeId(formElementAttributeId, null);
    }
    public static FormElementAttribute findByFormElementAttributeId(int formElementAttributeId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        FormElementAttribute formElementAttribute = new FormElementAttribute();

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get List By Id
            ps = con.prepareStatement(FormElementAttributeImpl.FIND_BY_FORM_ELEMENT_ATTRIBUTE_ID);
            ps.setInt(1, formElementAttributeId);
            rs = ps.executeQuery();
            while(rs.next()) {
                formElementAttribute = new FormElementAttribute(rs.getInt("form_element_attribute_id"), rs.getInt("form_element_id"), CodeImpl.findByCodeId(rs.getInt("code_id"), con), rs.getString("value"));
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
        return formElementAttribute;
    }
    public static List<FormElementAttribute> findByFormElementId(int formElementId) {
        return FormElementAttributeImpl.findByFormElementId(formElementId, null);
    }
    public static List<FormElementAttribute> findByFormElementId(int formElementId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        List<FormElementAttribute> formElementAttributes = new ArrayList<FormElementAttribute>();

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get List By Id
            ps = con.prepareStatement(FormElementAttributeImpl.FIND_BY_FORM_ELEMENT_ID);
            ps.setInt(1, formElementId);
            rs = ps.executeQuery();
            while(rs.next()) {
                formElementAttributes.add(new FormElementAttribute(rs.getInt("form_element_attribute_id"), rs.getInt("form_element_id"), CodeImpl.findByCodeId(rs.getInt("code_id"), con), rs.getString("value")));
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
        return formElementAttributes;
    }
    public static int findNextId() {
        return FormElementAttributeImpl.findNextId(null);
    }
    public static int findNextId(Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        int formElementAttributeId = 1;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get the Next Id
            ps = con.prepareStatement(FormElementAttributeImpl.FIND_NEXT_ID);
            rs = ps.executeQuery();
            while(rs.next()) {
                formElementAttributeId = rs.getInt("form_element_attribute_id") + 1;
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
        return formElementAttributeId;
    }
    
    
    
    /***** Create Methods *****/
    public static FormElementAttribute create(FormElementAttribute formElementAttribute, int formElementId) {
        return FormElementAttributeImpl.create(formElementAttribute, formElementId, null);
    }
    public static FormElementAttribute create(FormElementAttribute formElementAttribute, int formElementId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        // Return if null
        if(formElementAttribute == null) {
            return null;
        }
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Set Primary Key
            formElementAttribute.setFormElementAttributeId(FormElementAttributeImpl.findNextId(con));
            
            // Complete the Code Object
            if(formElementAttribute.getCode().getCodeId() <= 0) {
                formElementAttribute.setCode(CodeImpl.findByCode(formElementAttribute.getCode(), con));
            }
            
            // Create New Entry
            ps = con.prepareStatement(FormElementAttributeImpl.CREATE);
            ps.setInt(1, formElementAttribute.getFormElementAttributeId());
            ps.setInt(2, formElementId);
            ps.setInt(3, formElementAttribute.getCode().getCodeId());
            ps.setString(4, formElementAttribute.getValue());
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
        return formElementAttribute;
    }
    
    
    
    /***** Update Methods *****/
    public static FormElementAttribute update(FormElementAttribute formElementAttribute) {
        return FormElementAttributeImpl.update(formElementAttribute, null);
    }
    public static FormElementAttribute update(FormElementAttribute formElementAttribute, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        // Return
        if(formElementAttribute == null) {
            return null;
        }
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Complete the Code Object
            if(formElementAttribute.getCode().getCodeId() <= 0) {
                formElementAttribute.setCode(CodeImpl.findByCode(formElementAttribute.getCode(), con));
            }
            
            // Update Existing Entry
            ps = con.prepareStatement(FormElementAttributeImpl.UPDATE);
            ps.setString(1, formElementAttribute.getValue());
            ps.setInt(2, formElementAttribute.getFormElementAttributeId());
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
        return formElementAttribute;
    }
    
    
    
    /***** Delete Methods *****/
    public static void deleteByFormElementAttributeId(int formElementAttributeId) {
        FormElementAttributeImpl.deleteByFormElementAttributeId(formElementAttributeId, null);
    }
    public static void deleteByFormElementAttributeId(int formElementAttributeId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Delete Existing Entry
            ps = con.prepareStatement(FormElementAttributeImpl.DELETE_BY_FORM_ELEMENT_ATTRIBUTE_ID);
            ps.setInt(1, formElementAttributeId);
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
    public static void deleteByFormElementId(int formElementAttributeId) {
        FormElementAttributeImpl.deleteByFormElementId(formElementAttributeId, null);
    }
    public static void deleteByFormElementId(int formElementAttributeId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Delete Existing Entry
            ps = con.prepareStatement(FormElementAttributeImpl.DELETE_BY_FORM_ELEMENT_ID);
            ps.setInt(1, formElementAttributeId);
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