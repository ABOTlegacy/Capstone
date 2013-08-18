package com.implementations;

import com.common.DBConnection;
import com.model.SubmissionAnswer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SubmissionAnswerImpl {

    private static final String FIND_BY_SUBMISSION_ANSWER_ID = "SELECT * FROM submission_answer_legacy WHERE submission_answer_id = ?;";
    private static final String FIND_BY_SUBMISSION_ID = "SELECT * FROM submission_answer_legacy WHERE submission_id = ?;";
    private static final String FIND_NEXT_ID = "SELECT TOP 1 submission_answer_id FROM submission_answer_legacy ORDER BY submission_answer_id DESC;";
    private static final String CREATE = "INSERT INTO submission_answer_legacy (submission_answer_id, submission_id, question_id, form_element_id, value) VALUES (?, ?, ?, ?, ?);";
    private static final String UPDATE = "UPDATE submission_answer_legacy SET value = ? WHERE submission_answer_id = ?;";
    private static final String DELETE_BY_SUBMISSION_ANSWER_ID = "DELETE FROM submission_answer_legacy WHERE submission_answer_id = ?;";
    private static final String DELETE_BY_SUBMISSION_ID = "DELETE FROM submission_answer_legacy WHERE submission_id = ?;";
    private static final String DELETE_BY_QUESTION_ID = "DELETE FROM submission_answer_legacy WHERE question_id = ?;";
    private static final String DELETE_BY_FORM_ELEMENT_ID = "DELETE FROM submission_answer_legacy WHERE form_element_id = ?;";
    
    
    
    /***** Find Methods *****/
    public static SubmissionAnswer findBySubmissionAnswerId(int submissionAnswerId) {
        return SubmissionAnswerImpl.findBySubmissionAnswerId(submissionAnswerId, null);
    }
    public static SubmissionAnswer findBySubmissionAnswerId(int submissionAnswerId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        SubmissionAnswer submissionAnswer = new SubmissionAnswer();

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get List By Id
            ps = con.prepareStatement(SubmissionAnswerImpl.FIND_BY_SUBMISSION_ANSWER_ID);
            ps.setInt(1, submissionAnswerId);
            rs = ps.executeQuery();
            while(rs.next()) {
                submissionAnswer = new SubmissionAnswer(rs.getInt("submission_answer_id"), rs.getInt("submission_id"), rs.getInt("question_id"), rs.getInt("form_element_id"), rs.getString("value"));
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
        return submissionAnswer;
    }
    public static List<SubmissionAnswer> findBySubmissionId(int submissionId) {
        return SubmissionAnswerImpl.findBySubmissionId(submissionId, null);
    }
    public static List<SubmissionAnswer> findBySubmissionId(int submissionId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        List<SubmissionAnswer> submissionAnswers = new ArrayList<SubmissionAnswer>();

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get List By Id
            ps = con.prepareStatement(SubmissionAnswerImpl.FIND_BY_SUBMISSION_ID);
            ps.setInt(1, submissionId);
            rs = ps.executeQuery();
            while(rs.next()) {
                submissionAnswers.add(new SubmissionAnswer(rs.getInt("submission_answer_id"), rs.getInt("submission_id"), rs.getInt("question_id"), rs.getInt("form_element_id"), rs.getString("value")));
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
        return submissionAnswers;
    }
    public static int findNextId() {
        return SubmissionAnswerImpl.findNextId(null);
    }
    public static int findNextId(Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        int submissionAnswerId = 1;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get All the Entities
            ps = con.prepareStatement(SubmissionAnswerImpl.FIND_NEXT_ID);
            rs = ps.executeQuery();
            while(rs.next()) {
                submissionAnswerId = rs.getInt("submission_answer_id") + 1;
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
        return submissionAnswerId;
    }
    
    
    
    /***** Create Methods *****/
    public static SubmissionAnswer create(SubmissionAnswer submissionAnswer) {
        return SubmissionAnswerImpl.create(submissionAnswer, null);
    }
    public static SubmissionAnswer create(SubmissionAnswer submissionAnswer, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        // Return if null
        if(submissionAnswer == null) {
            return null;
        }
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Set ID
            submissionAnswer.setSubmissionAnswerId(SubmissionAnswerImpl.findNextId(con));
            
            // Create New Entry
            ps = con.prepareStatement(SubmissionAnswerImpl.CREATE);
            ps.setInt(1, submissionAnswer.getSubmissionAnswerId());
            ps.setInt(2, submissionAnswer.getSubmissionId());
            ps.setInt(3, submissionAnswer.getQuestionId());
            ps.setInt(4, submissionAnswer.getFormElementId());
            ps.setString(5, submissionAnswer.getValue());
            ps.executeUpdate();
            ps.close();
        } catch(Exception ex) {
            System.out.println("Error: SubmissionAnswer.Create = " + ex.getMessage());
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
        return submissionAnswer;
    }
    
    
    
    /***** Update Methods *****/
    public static SubmissionAnswer update(SubmissionAnswer submissionAnswer) {
        return SubmissionAnswerImpl.update(submissionAnswer, null);
    }
    public static SubmissionAnswer update(SubmissionAnswer submissionAnswer, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        // Return if null
        if(submissionAnswer == null) {
            return null;
        }
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Update Existing Entry
            ps = con.prepareStatement(SubmissionAnswerImpl.UPDATE);
            ps.setString(1, submissionAnswer.getValue());
            ps.setInt(2, submissionAnswer.getSubmissionAnswerId());
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
        return submissionAnswer;
    }
    
    
    
    /***** Delete Methods *****/
    public static void deleteBySubmissionAnswerId(int submissionAnswerId) {
        SubmissionAnswerImpl.deleteBySubmissionAnswerId(submissionAnswerId, null);
    }
    public static void deleteBySubmissionAnswerId(int submissionAnswerId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Delete Existing Entry
            ps = con.prepareStatement(SubmissionAnswerImpl.DELETE_BY_SUBMISSION_ANSWER_ID);
            ps.setInt(1, submissionAnswerId);
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
    public static void deleteBySubmissionId(int submissionId) {
        SubmissionAnswerImpl.deleteBySubmissionId(submissionId, null);
    }
    public static void deleteBySubmissionId(int submissionId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Delete Existing Entry
            ps = con.prepareStatement(SubmissionAnswerImpl.DELETE_BY_SUBMISSION_ID);
            ps.setInt(1, submissionId);
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
        SubmissionAnswerImpl.deleteByQuestionId(questionId, null);
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
            ps = con.prepareStatement(SubmissionAnswerImpl.DELETE_BY_QUESTION_ID);
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
    public static void deleteByFormElementId(int formElementId) {
        SubmissionAnswerImpl.deleteByFormElementId(formElementId, null);
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
            ps = con.prepareStatement(SubmissionAnswerImpl.DELETE_BY_FORM_ELEMENT_ID);
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