package com.implementations;

import com.common.DBConnection;
import com.model.Revision;
import com.model.Survey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SurveyImpl {

    private static final String FIND_ALL = "SELECT * FROM survey_legacy;";
    private static final String FIND_BY_SURVEY_ID = "SELECT * FROM survey_legacy WHERE survey_id = ?;";
    private static final String FIND_BY_COMPANY_ID = "SELECT * FROM survey_legacy WHERE company_id = ?;";
    private static final String FIND_NEXT_ID = "SELECT TOP 1 survey_id FROM survey_legacy ORDER BY survey_id DESC;";
    private static final String CREATE = "INSERT INTO survey_legacy (survey_id, company_id, name) VALUES (?, ?, ?);";
    private static final String UPDATE = "UPDATE survey_legacy SET name = ? WHERE survey_id = ?;";
    private static final String DELETE_BY_SURVEY_ID = "DELETE FROM survey_legacy WHERE survey_id = ?;";
    private static final String DELETE_BY_COMPANY_ID = "DELETE FROM survey_legacy WHERE company_id = ?;";
    
    
    
    /***** Find Methods *****/
    public static List<Survey> findAll() {
        return SurveyImpl.findAll(null);
    }
    public static List<Survey> findAll(Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        List<Survey> surveys = new ArrayList<Survey>();

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get List of All
            ps = con.prepareStatement(SurveyImpl.FIND_ALL);
            rs = ps.executeQuery();
            while(rs.next()) {
                surveys.add(new Survey(rs.getInt("survey_id"), rs.getInt("company_id"), rs.getString("name"), null));
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
        return surveys;
    }
    public static List<Survey> findByCompanyId(int companyId) {
        return SurveyImpl.findByCompanyId(companyId, null);
    }
    public static List<Survey> findByCompanyId(int companyId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        List<Survey> surveys = new ArrayList<Survey>();

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get List By Id
            ps = con.prepareStatement(SurveyImpl.FIND_BY_COMPANY_ID);
            ps.setInt(1, companyId);
            rs = ps.executeQuery();
            while(rs.next()) {
                surveys.add(new Survey(rs.getInt("survey_id"), rs.getInt("company_id"), rs.getString("name"), null));
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
        return surveys;
    }
    public static Survey findBySurveyId(int surveyId) {
        return SurveyImpl.findBySurveyId(surveyId, null);
    }
    public static Survey findBySurveyId(int surveyId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        Survey survey = new Survey();

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get List By Id
            ps = con.prepareStatement(SurveyImpl.FIND_BY_SURVEY_ID);
            ps.setInt(1, surveyId);
            rs = ps.executeQuery();
            while(rs.next()) {
                survey = new Survey(rs.getInt("survey_id"), rs.getInt("company_id"), rs.getString("name"), null);
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
        return survey;
    }
    public static int findNextId() {
        return SurveyImpl.findNextId(null);
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
            ps = con.prepareStatement(SurveyImpl.FIND_NEXT_ID);
            rs = ps.executeQuery();
            while(rs.next()) {
                surveyId = rs.getInt("survey_id") + 1;
            }
            rs.close();
            ps.close();
        } catch(Exception ex) {
            System.out.println("Error");
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
    
    
    
    /***** Create Methods *****/
    public static Survey create(Survey survey) {
        return SurveyImpl.create(survey, null);
    }
    public static Survey create(Survey survey, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;
        
        // Return if null
        if(survey == null) {
            return null;
        }

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Set ID
            survey.setSurveyId(SurveyImpl.findNextId(con));
            
            // Create Revisions
            if(survey.getRevisions() == null) {
                survey.setRevisions(new ArrayList<Revision>());
            }
            for(int i = 0; i < survey.getRevisions().size(); i++) {
                RevisionImpl.create(survey.getRevisions().get(i), con);
            }
            
            // Create New Entry
            ps = con.prepareStatement(SurveyImpl.CREATE);
            ps.setInt(1, survey.getSurveyId());
            ps.setInt(2, survey.getCompanyId());
            ps.setString(3, survey.getName());
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
        return survey;
    }
    
    
    
    /***** Update Methods *****/
    public static Survey update(Survey survey) {
        return SurveyImpl.update(survey, null);
    }
    public static Survey update(Survey survey, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;
        
        // Verify
        if(survey == null) {
            return null;
        }
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Update Existing Entry
            ps = con.prepareStatement(SurveyImpl.UPDATE);
            ps.setString(1, survey.getName());
            ps.setInt(2, survey.getSurveyId());
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
        return survey;
    }
    
    
    
    /***** Delete Methods *****/
    public static void deleteBySurveyId(int surveyId) {
        SurveyImpl.deleteBySurveyId(surveyId, null);
    }
    public static void deleteBySurveyId(int surveyId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Delete Existing Entry
            ps = con.prepareStatement(SurveyImpl.DELETE_BY_SURVEY_ID);
            ps.setInt(1, surveyId);
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
    public static void deleteByCompanyId(int companyId) {
        SurveyImpl.deleteByCompanyId(companyId, null);
    }
    public static void deleteByCompanyId(int companyId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Delete Existing Entry
            ps = con.prepareStatement(SurveyImpl.DELETE_BY_COMPANY_ID);
            ps.setInt(1, companyId);
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