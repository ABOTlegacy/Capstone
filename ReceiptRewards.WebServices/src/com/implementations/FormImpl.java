package com.implementations;

import com.common.DBConnection;
import com.model.Form;
import com.model.FormElement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class FormImpl {

    private static final String FIND_BY_FORM_ID = "SELECT * FROM form_legacy WHERE form_id = ?;";
    private static final String FIND_BY_QUESTION_ID = "SELECT * FROM form_legacy f JOIN question_legacy q ON q.form_id = f.form_id WHERE q.question_id = ?;";
    private static final String FIND_BY_REVISION_ID = "SELECT * FROM form_legacy f JOIN question_legacy q ON q.form_id = f.form_id JOIN revision_legacy r ON r.revision_id = q.revision_id WHERE r.revision_id = ?;";
    private static final String FIND_NEXT_ID = "SELECT TOP 1 form_id FROM form_legacy ORDER BY form_id DESC;";
    private static final String CREATE = "INSERT INTO form_legacy (form_id) VALUES (?);";
    private static final String DELETE_BY_FORM_ID = "DELETE FROM form_legacy WHERE form_id = ?;";
    private static final String DELETE_BY_QUESTION_ID = "DELETE FROM form_legacy f JOIN question_legacy q ON q.form_id = f.form_id WHERE q.question_id = ?;";
    private static final String DELETE_BY_REVISION_ID = "DELETE FROM form_legacy f JOIN question_legacy q ON q.form_id = f.form_id JOIN revision_legacy r ON r.revision_id = q.revsion_id WHERE r.revision_id = ?;";
    
    
    
    /***** Find Methods *****/
    public static Form findByFormId(int formId) {
        return FormImpl.findByFormId(formId, null);
    }
    public static Form findByFormId(int formId, Connection con) {
        return FormImpl.findByFormId(formId, 1000, con);
    }
    public static Form findByFormId(int formId, int recursiveLevel) {
        return FormImpl.findByFormId(formId, recursiveLevel, null);
    }
    public static Form findByFormId(int formId, int recursiveLevel, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        Form form = new Form();
        
        // Verify
        if(formId <= 0 || recursiveLevel <= 0) {
            return null;
        }
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get List By Id
            ps = con.prepareStatement(FormImpl.FIND_BY_FORM_ID);
            ps.setInt(1, formId);
            rs = ps.executeQuery();
            while(rs.next()) {
                form = new Form(rs.getInt("form_id"), FormElementImpl.findByFormId(rs.getInt("form_id"), con), FormFlowImpl.findByFormId(rs.getInt("form_id"), (recursiveLevel - 1), con));
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
        return form;
    }
    public static Form findByQuestionId(int questionId) {
        return FormImpl.findByQuestionId(questionId, 100);
    }
    public static Form findByQuestionId(int questionId, Connection con) {
        return FormImpl.findByQuestionId(questionId, 100, con);
    }
    public static Form findByQuestionId(int questionId, int recursiveLevel) {
        return FormImpl.findByQuestionId(questionId, recursiveLevel, null);
    }
    public static Form findByQuestionId(int questionId, int recursiveLevel, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        Form form = new Form();

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
            ps = con.prepareStatement(FormImpl.FIND_BY_QUESTION_ID);
            ps.setInt(1, questionId);
            rs = ps.executeQuery();
            while(rs.next()) {
                form = new Form(rs.getInt("form_id"), FormElementImpl.findByFormId(rs.getInt("form_id"), con), FormFlowImpl.findByFormId(rs.getInt("form_id"), (recursiveLevel - 1), con));
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
        return form;
    }
    public static Form findByRevisionId(int revisionId) {
        return FormImpl.findByRevisionId(revisionId, 100);
    }
    public static Form findByRevisionId(int revisionId, Connection con) {
        return FormImpl.findByRevisionId(revisionId, 100, con);
    }
    public static Form findByRevisionId(int revisionId, int recursiveLevel) {
        return FormImpl.findByRevisionId(revisionId, recursiveLevel, null);
    }
    public static Form findByRevisionId(int revisionId, int recursiveLevel, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        Form form = new Form();
        
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
            ps = con.prepareStatement(FormImpl.FIND_BY_REVISION_ID);
            ps.setInt(1, revisionId);
            rs = ps.executeQuery();
            while(rs.next()) {
                form = new Form(rs.getInt("form_id"), FormElementImpl.findByFormId(rs.getInt("form_id"), con), FormFlowImpl.findByFormId(rs.getInt("form_id"), (recursiveLevel - 1), con));
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
        return form;
    }
    public static int findNextId() {
        return FormImpl.findNextId(null);
    }
    public static int findNextId(Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        int formId = 1;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get All the Code Types
            ps = con.prepareStatement(FormImpl.FIND_NEXT_ID);
            rs = ps.executeQuery();
            while(rs.next()) {
                formId = rs.getInt("form_id") + 1;
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
        return formId;
    }
    
    
    
    /***** Create Methods *****/
    public static Form create(Form form) {
        return FormImpl.create(form, null);
    }
    public static Form create(Form form, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        // Return if null
        if(form == null) {
            return null;
        }
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Set ID
            form.setFormId(FormImpl.findNextId(con));
            
            // Create Form Elements
            if(form.getFormElements() == null) {
                form.setFormElements(new ArrayList<FormElement>());
            }
            for(int i = 0; i < form.getFormElements().size(); i++) {
                FormElementImpl.create(form.getFormElements().get(i), form.getFormId(), con);
            }
            
            // Create New Entry
            ps = con.prepareStatement(FormImpl.CREATE);
            ps.setInt(1, form.getFormId());
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
        return form;
    }
    
    
    
    /***** Delete Methods *****/
    public static void deleteByFormId(int formId) {
        FormImpl.deleteByFormId(formId, null);
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
            ps = con.prepareStatement(FormImpl.DELETE_BY_FORM_ID);
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
    public static void deleteByQuestionId(int questionId) {
        FormImpl.deleteByQuestionId(questionId, null);
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
            ps = con.prepareStatement(FormImpl.DELETE_BY_QUESTION_ID);
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
        FormImpl.deleteByRevisionId(revisionId, null);
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
            ps = con.prepareStatement(FormImpl.DELETE_BY_REVISION_ID);
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