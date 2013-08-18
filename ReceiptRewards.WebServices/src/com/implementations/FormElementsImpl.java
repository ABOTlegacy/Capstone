package com.implementations;

import com.common.DBConnection;
import com.model.Form;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FormElementsImpl {

    //private static final String FIND_BY_AUTOMATION_ID = "SELECT * FROM commands WHERE automation_id = ?;";
    private static final String FIND_NEXT_ID = "SELECT TOP 1 form_elements_id FROM form_elements_legacy ORDER BY form_elements_id DESC;";
    private static final String FIND_NEXT_WEIGHT = "SELECT TOP 1 weight FROM form_elements_legacy WHERE form_id = ? ORDER BY weight DESC;";
    private static final String CREATE = "INSERT INTO form_elements_legacy (form_elements_id, form_id, form_element_id, weight, code_id) VALUES (?, ?, ?, ?, ?);";
    private static final String UPDATE_WEIGHT = "UPDATE form_elements_legacy SET weight = ? WHERE form_id = ? and form_element_id = ?;";
    private static final String DELETE_BY_FORM_ID = "DELETE FROM form_elements_legacy WHERE form_id = ?;";
    private static final String DELETE_BY_FORM_ELEMENT_ID = "DELETE FROM form_elements_legacy WHERE form_element_id = ?;";
    
    
    
    /***** Find Methods *****/
    public static int findNextId() {
        return FormElementsImpl.findNextId(null);
    }
    public static int findNextId(Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;    
        int formElementId = 1;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get All the Code Types
            ps = con.prepareStatement(FormElementsImpl.FIND_NEXT_ID);
            rs = ps.executeQuery();
            while(rs.next()) {
                formElementId = rs.getInt("form_elements_id") + 1;
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
        return formElementId;
    }
    public static int findNextWeight(int formId) {
        return FormElementsImpl.findNextWeight(formId, null);
    }
    public static int findNextWeight(int formId, Connection con) {
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
            ps = con.prepareStatement(FormElementsImpl.FIND_NEXT_WEIGHT);
            ps.setInt(1, formId);
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
    public static void create(int formId, int formElementId, int codeId) {
        FormElementsImpl.create(formId, formElementId, codeId, FormElementsImpl.findNextWeight(formId), null);
    }
    public static void create(int formId, int formElementId, int codeId, Connection con) {
        FormElementsImpl.create(formId, formElementId, codeId, FormElementsImpl.findNextWeight(formId), con);
    }
    public static void create(int formId, int formElementId, int codeId, int weight) {
        FormElementsImpl.create(formId, formElementId, codeId, weight, null);
    }
    public static void create(int formId, int formElementId, int codeId, int weight, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Create New Entry
            ps = con.prepareStatement(FormElementsImpl.CREATE);
            ps.setInt(1, FormElementsImpl.findNextId(con));
            ps.setInt(2, formId);
            ps.setInt(3, formElementId);
            ps.setInt(4, weight);
            ps.setInt(5, codeId);
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
    public static void updateOrder(Form form) {
        FormElementsImpl.updateOrder(form, null);
    }
    public static void updateOrder(Form form, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Create New Entry
            for(int i = 0; i < form.getFormElements().size(); i++) {
                ps = con.prepareStatement(FormElementsImpl.UPDATE_WEIGHT);
                ps.setInt(1, i);
                ps.setInt(2, form.getFormId());
                ps.setInt(3, form.getFormElements().get(i).getFormElementId());
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
    public static void deleteByFormId(int formId) {
        FormElementsImpl.deleteByFormId(formId, null);
    }
    public static void deleteByFormId(int formId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Delete Existing Entry
            ps = con.prepareStatement(FormElementsImpl.DELETE_BY_FORM_ID);
            ps.setInt(1, formId);
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
        FormElementsImpl.deleteByFormElementId(formElementId, null);
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
            ps = con.prepareStatement(FormElementsImpl.DELETE_BY_FORM_ELEMENT_ID);
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