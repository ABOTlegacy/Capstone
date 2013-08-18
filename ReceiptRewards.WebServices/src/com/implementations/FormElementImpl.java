package com.implementations;

import com.common.DBConnection;
import com.model.FormElement;
import com.model.FormElementAttribute;
import com.model.FormElementOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FormElementImpl {

    private static final String FIND_BY_FORM_ELEMENT_ID = "SELECT * FROM form_element_legacy WHERE form_element_id = ?;";
    private static final String FIND_BY_FORM_ID = "SELECT * FROM form_element_legacy fe JOIN form_elements_legacy fes ON fes.form_id = ? AND fes.form_element_id = fe.form_element_id ORDER BY fes.weight ASC;";
    private static final String FIND_NEXT_ID = "SELECT TOP 1 form_element_id FROM form_element_legacy ORDER BY form_element_id DESC;";
    private static final String CREATE = "INSERT INTO form_element_legacy (form_element_id, code_id) VALUES (?, ?);";
    private static final String UPDATE = "UPDATE form_element_legacy SET code_id = ? WHERE form_element_id = ?;";
    private static final String DELETE_BY_FORM_ELEMENT_ID = "DELETE FROM form_element_legacy WHERE form_element_id = ?;";
    
    
    
    /***** Find Methods *****/
    public static FormElement findByFormElementId(int formElementId) {
        return FormElementImpl.findByFormElementId(formElementId, null);
    }
    public static FormElement findByFormElementId(int formElementId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        FormElement formElement = new FormElement();

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get List By Id
            ps = con.prepareStatement(FormElementImpl.FIND_BY_FORM_ELEMENT_ID);
            ps.setInt(1, formElementId);
            rs = ps.executeQuery();
            while(rs.next()) {
                formElement = new FormElement(rs.getInt("form_element_id"), CodeImpl.findByCodeId(rs.getInt("code_id"), con), FormElementOptionImpl.findByFormElementId(rs.getInt("form_element_id"), con), FormElementAttributeImpl.findByFormElementId(rs.getInt("form_element_id"), con));
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
        return formElement;
    }
    public static List<FormElement> findByFormId(int formId) {
        return FormElementImpl.findByFormId(formId, null);
    }
    public static List<FormElement> findByFormId(int formId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        List<FormElement> formElements = new ArrayList<FormElement>();

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get List By Id
            ps = con.prepareStatement(FormElementImpl.FIND_BY_FORM_ID);
            ps.setInt(1, formId);
            rs = ps.executeQuery();
            while(rs.next()) {
                formElements.add(new FormElement(rs.getInt("form_element_id"), CodeImpl.findByCodeId(rs.getInt("code_id"), con), FormElementOptionImpl.findByFormElementId(rs.getInt("form_element_id"), con), FormElementAttributeImpl.findByFormElementId(rs.getInt("form_element_id"), con)));
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
        return formElements;
    }
    public static int findNextId() {
        return FormElementImpl.findNextId(null);
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
            ps = con.prepareStatement(FormElementImpl.FIND_NEXT_ID);
            rs = ps.executeQuery();
            while(rs.next()) {
                formElementId = rs.getInt("form_element_id") + 1;
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
    
    
    
    /***** Create Methods *****/
    public static FormElement create(FormElement formElement, int formId) {
        return FormElementImpl.create(formElement, formId, null);
    }
    public static FormElement create(FormElement formElement, int formId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;
        
        // Return if null
        if(formElement == null || formId <= 0) {
            return null;
        }

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Set ID
            formElement.setFormElementId(FormElementImpl.findNextId(con));
            
            // Create Form Option
            if(formElement.getFormElementOptions() == null) {
                formElement.setFormElementOptions(new ArrayList<FormElementOption>());
            }
            for(int i = 0; i < formElement.getFormElementOptions().size(); i++) {
                FormElementOptionImpl.create(formElement.getFormElementOptions().get(i), formElement.getFormElementId(), con);
            }
            
            // Create Form Attribute
            if(formElement.getFormElementAttributes() == null) {
                formElement.setFormElementAttributes(new ArrayList<FormElementAttribute>());
            }
            for(int i = 0; i < formElement.getFormElementAttributes().size(); i++) {
                FormElementAttributeImpl.create(formElement.getFormElementAttributes().get(i), formElement.getFormElementId(), con);
            }
            
            // Complete the Code Object
            if(formElement.getCode().getCodeId() <= 0) {
                formElement.setCode(CodeImpl.findByCode(formElement.getCode(), con));
            }
            
            // Set Up Dependent Entries
            FormElementsImpl.create(formId, formElement.getFormElementId(), 0, con);
            
            // Create New Entry
            ps = con.prepareStatement(FormElementImpl.CREATE);
            ps.setInt(1, formElement.getFormElementId());
            ps.setInt(2, formElement.getCode().getCodeId());
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
        return formElement;
    }
    public static FormElement createFromExisting(int formElementId, int formId) {
        return FormElementImpl.createFromExisting(formElementId, formId, null);
    }
    public static FormElement createFromExisting(int formElementId, int formId, Connection con) {
        // Instantiate Variables
        boolean conBool = false;
        
        // Return if null
        if(formElementId <= 0 || formId <= 0) {
            return null;
        }

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Set Up Dependent Entries
            FormElementsImpl.create(formId, formElementId, 0, con);
            
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
        return new FormElement(formElementId, null, null, null);
    }
    
    
    /***** Update Methods *****/
    public static FormElement update(FormElement formElement) {
        return FormElementImpl.update(formElement, null);
    }
    public static FormElement update(FormElement formElement, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        // Return if null
        if(formElement == null) {
            return null;
        }
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Update Existing Entry
            ps = con.prepareStatement(FormElementImpl.UPDATE);
            ps.setInt(1, formElement.getCode().getCodeId());
            ps.setInt(2, formElement.getFormElementId());
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
        return formElement;
    }
    
    
    
    /***** Delete Methods *****/
    public static void deleteByFormElementId(int formElementId) {
        FormElementImpl.deleteByFormElementId(formElementId, null);
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
            
            // Delete Dependant Entries
            FormElementOptionImpl.deleteByFormElementId(formElementId, con);
            FormElementAttributeImpl.deleteByFormElementId(formElementId, con);
            
            // Delete Existing Entry
            ps = con.prepareStatement(FormElementImpl.DELETE_BY_FORM_ELEMENT_ID);
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