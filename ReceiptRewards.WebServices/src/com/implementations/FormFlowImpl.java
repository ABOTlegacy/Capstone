package com.implementations;

import com.common.DBConnection;
import com.model.FormFlow;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FormFlowImpl {

    private static final String FIND_BY_FORM_FLOW_ID = "SELECT * FROM form_flow_legacy WHERE form_flow_id = ?;";
    private static final String FIND_BY_FORM_ID = "SELECT * FROM form_flow_legacy WHERE form_id = ?;";
    private static final String FIND_NEXT_ID = "SELECT TOP 1 form_flow_id FROM form_flow_legacy ORDER BY form_flow_id DESC;";
    private static final String CREATE = "INSERT INTO form_flow_legacy (form_flow_id, form_id, question_id, value) VALUES (?, ?, ?, ?);";
    private static final String DELETE_BY_FORM_FLOW_ID = "DELETE FROM form_flow_legacy WHERE form_flow_id = ?;";
    private static final String DELETE_BY_FORM_ID = "DELETE FROM form_flow_legacy WHERE form_id = ?;";
    
    
    
    /***** Find Methods *****/
    public static FormFlow findByFormFlowId(int formFlowId) {
        return FormFlowImpl.findByFormFlowId(formFlowId, 100);
    }
    public static FormFlow findByFormFlowId(int formFlowId, Connection con) {
        return FormFlowImpl.findByFormFlowId(formFlowId, 100, con);
    }
    public static FormFlow findByFormFlowId(int formFlowId, int recursiveLevel) {
        return FormFlowImpl.findByFormFlowId(formFlowId, recursiveLevel, null);
    }
    public static FormFlow findByFormFlowId(int formFlowId, int recursiveLevel, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        FormFlow formFlow = new FormFlow();

        // Verify
        if(formFlowId <= 0 || recursiveLevel <= 0) {
            return null;
        }
        
        try { // Make Connection
            if(con != null && conBool) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get List By Id
            ps = con.prepareStatement(FormFlowImpl.FIND_BY_FORM_FLOW_ID);
            ps.setInt(1, formFlowId);
            rs = ps.executeQuery();
            while(rs.next()) {
                formFlow = new FormFlow(rs.getInt("form_flow_id"), rs.getInt("form_id"), QuestionImpl.findByQuestionId(rs.getInt("question_id"), (recursiveLevel - 1), con), rs.getString("value"));
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
        return formFlow;
    }
    public static List<FormFlow> findByFormId(int formId) {
        return FormFlowImpl.findByFormId(formId, null);
    }
    public static List<FormFlow> findByFormId(int formId, Connection con) {
        return FormFlowImpl.findByFormId(formId, 1000, con);
    }
    public static List<FormFlow> findByFormId(int formId, int recursiveLevel) {
        return FormFlowImpl.findByFormId(formId, recursiveLevel, null);
    }
    public static List<FormFlow> findByFormId(int formId, int recursiveLevel, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        List<FormFlow> formFlows = new ArrayList<FormFlow>();
        
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
            ps = con.prepareStatement(FormFlowImpl.FIND_BY_FORM_ID);
            ps.setInt(1, formId);
            rs = ps.executeQuery();
            while(rs.next()) {
                formFlows.add(new FormFlow(rs.getInt("form_flow_id"), rs.getInt("form_id"), QuestionImpl.findByQuestionId(rs.getInt("question_id"), (recursiveLevel - 1), con), rs.getString("value")));
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
        return formFlows;
    }
    public static int findNextId() {
        return FormFlowImpl.findNextId(null);
    }
    public static int findNextId(Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        int formFlowId = 1;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get All the Code Types
            ps = con.prepareStatement(FormFlowImpl.FIND_NEXT_ID);
            rs = ps.executeQuery();
            while(rs.next()) {
                formFlowId = rs.getInt("form_flow_id") + 1;
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
        return formFlowId;
    }
    
    
    
    /***** Create Methods *****/
    public static FormFlow create(FormFlow formFlow) {
        return FormFlowImpl.create(formFlow, null);
    }
    public static FormFlow create(FormFlow formFlow, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;
        
        // Return if null
        if(formFlow == null) {
            return null;
        }

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Set Primary Key
            formFlow.setFormFlowId(FormFlowImpl.findNextId(con));
            
            // Create New Entry
            ps = con.prepareStatement(FormFlowImpl.CREATE);
            ps.setInt(1, formFlow.getFormFlowId());
            ps.setInt(2, formFlow.getFormId());
            ps.setInt(3, formFlow.getQuestion().getQuestionId());
            ps.setString(4, formFlow.getValue());
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
        return formFlow;
    }
    
    
    
    /***** Delete Methods *****/
    public static void deleteByFormFlowId(int formFlowId) {
        FormFlowImpl.deleteByFormFlowId(formFlowId, null);
    }
    public static void deleteByFormFlowId(int formFlowId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Delete Existing Entry
            ps = con.prepareStatement(FormFlowImpl.DELETE_BY_FORM_FLOW_ID);
            ps.setInt(1, formFlowId);
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
    public static void deleteByFormId(int formId) {
        FormFlowImpl.deleteByFormId(formId, null);
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
            ps = con.prepareStatement(FormFlowImpl.DELETE_BY_FORM_ID);
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
}