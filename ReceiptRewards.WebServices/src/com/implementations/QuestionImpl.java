package com.implementations;

import com.common.DBConnection;
import com.model.Question;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QuestionImpl {

    private static final String FIND_BY_QUESTION_ID = "SELECT * FROM question_legacy WHERE question_id = ?;";
    private static final String FIND_BY_REVISION_ID = "SELECT * FROM question_legacy WHERE revision_id = ?;";
    private static final String FIND_BY_FORM_ID = "SELECT * FROM question_legacy WHERE form_id = ?;";
    private static final String FIND_BY_AUTOMATION_ID = "SELECT * FROM question_legacy WHERE automation_id = ?;";
    private static final String FIND_NEXT_ID = "SELECT TOP 1 question_id FROM question_legacy ORDER BY question_id DESC;";
    private static final String CREATE = "INSERT INTO question_legacy (question_id, revision_id, name, form_id, automation_id) VALUES (?, ?, ?, ?, ?);";
    private static final String UPDATE = "UPDATE question_legacy SET name = ?, form_id = ?, automation_id WHERE question_id = ?;";
    private static final String DELETE_BY_QUESTION_ID = "DELETE FROM question_legacy WHERE question_id = ?;";
    private static final String DELETE_BY_FORM_ID = "DELETE FROM question_legacy WHERE form_id = ?;";
    private static final String DELETE_BY_AUTOMATION_ID = "DELETE FROM question_legacy WHERE automation_id = ?;";
    
    
    
    /***** Find Methods *****/
    public static Question findByQuestionId(int questionId) {
        return QuestionImpl.findByQuestionId(questionId, null);
    }
    public static Question findByQuestionId(int questionId, Connection con) {
        return QuestionImpl.findByQuestionId(questionId, 1000, con);
    }
    public static Question findByQuestionId(int questionId, int recursiveLevel) {
        return QuestionImpl.findByQuestionId(questionId, recursiveLevel, null);
    }
    public static Question findByQuestionId(int questionId, int recursiveLevel, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        Question question = new Question(questionId);

        // Verify
        if(questionId <= 0 || recursiveLevel <= 0) {
            return question;
        }
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get List By Id
            ps = con.prepareStatement(QuestionImpl.FIND_BY_QUESTION_ID);
            ps.setInt(1, questionId);
            rs = ps.executeQuery();
            while(rs.next()) {
                question = new Question(rs.getInt("question_id"), rs.getInt("revision_id"), rs.getString("name"), FormImpl.findByFormId(rs.getInt("form_id"), (recursiveLevel - 1), con), AutomationImpl.findByAutomationId(rs.getInt("automation_id"), (recursiveLevel - 1), con));
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
        return question;
    }
    public static List<Question> findByRevisionId(int revisionId) {
        return QuestionImpl.findByRevisionId(revisionId, null);
    }
    public static List<Question> findByRevisionId(int revisionId, Connection con) {
        return QuestionImpl.findByRevisionId(revisionId, 1000, con);
    }
    public static List<Question> findByRevisionId(int revisionId, int recursiveLevel) {
        return QuestionImpl.findByRevisionId(revisionId, recursiveLevel, null);
    }
    public static List<Question> findByRevisionId(int revisionId, int recursiveLevel, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        List<Question> questions = new ArrayList<Question>();

        // Verify
        if(revisionId <= 0 || recursiveLevel <= 0) {
            questions.add(new Question(0, revisionId));
            return questions;
        }
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get List By Id
            ps = con.prepareStatement(QuestionImpl.FIND_BY_REVISION_ID);
            ps.setInt(1, revisionId);
            rs = ps.executeQuery();
            while(rs.next()) {
                questions.add(new Question(rs.getInt("question_id"), rs.getInt("revision_id"), rs.getString("name"), FormImpl.findByFormId(rs.getInt("form_id"), (recursiveLevel - 1), con), AutomationImpl.findByAutomationId(rs.getInt("automation_id"), (recursiveLevel - 1), con)));
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
        return questions;
    }
    public static List<Question> findByFormId(int formId) {
        return QuestionImpl.findByFormId(formId, 100, null);
    }
    public static List<Question> findByFormId(int formId, Connection con) {
        return QuestionImpl.findByFormId(formId, 100, con);
    }
    public static List<Question> findByFormId(int formId, int recursiveLevel) {
        return QuestionImpl.findByFormId(formId, recursiveLevel, null);
    }
    public static List<Question> findByFormId(int formId, int recursiveLevel, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        List<Question> questions = new ArrayList<Question>();

        // Verify
        if(formId <= 0 || recursiveLevel <= 0) {
            return null;
        }
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            //Get List By Id
            ps = con.prepareStatement(QuestionImpl.FIND_BY_FORM_ID);
            ps.setInt(1, formId);
            rs = ps.executeQuery();
            while(rs.next()) {
                questions.add(new Question(rs.getInt("question_id"), rs.getInt("revision_id"), rs.getString("name"), FormImpl.findByFormId(rs.getInt("form_id"), (recursiveLevel - 1), con), AutomationImpl.findByAutomationId(rs.getInt("automation_id"), (recursiveLevel - 1), con)));
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
        return questions;
    }
    public static List<Question> findByAutomationId(int automationId) {
        return QuestionImpl.findByAutomationId(automationId, null);
    }
    public static List<Question> findByAutomationId(int automationId, Connection con) {
        return QuestionImpl.findByAutomationId(automationId, 100, con);
    }
    public static List<Question> findByAutomationId(int automationId, int recursiveLevel) {
        return QuestionImpl.findByAutomationId(automationId, recursiveLevel, null);
    }
    public static List<Question> findByAutomationId(int automationId, int recursiveLevel, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        List<Question> questions = new ArrayList<Question>();

        // Verify
        if(automationId <= 0 || recursiveLevel <= 0) {
            return null;
        }
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get List By Id
            ps = con.prepareStatement(QuestionImpl.FIND_BY_AUTOMATION_ID);
            ps.setInt(1, automationId);
            rs = ps.executeQuery();
            while(rs.next()) {
                questions.add(new Question(rs.getInt("question_id"), rs.getInt("revision_id"), rs.getString("name"), FormImpl.findByFormId(rs.getInt("form_id"), (recursiveLevel - 1), con), AutomationImpl.findByAutomationId(rs.getInt("automation_id"), (recursiveLevel - 1), con)));
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
        return questions;
    }
    public static int findNextId() {
        return QuestionImpl.findNextId(null);
    }
    public static int findNextId(Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        int questionId = 1;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get All the Code Types
            ps = con.prepareStatement(QuestionImpl.FIND_NEXT_ID);
            rs = ps.executeQuery();
            while(rs.next()) {
                questionId = rs.getInt("question_id") + 1;
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
        return questionId;
    }
    
    
    
    /***** Create Methods *****/
    public static Question create(Question question) {
        return QuestionImpl.create(question, null);
    }
    public static Question create(Question question, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;
        
        // Return if null
        if(question == null) {
            return null;
        }

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Set ID
            question.setQuestionId(QuestionImpl.findNextId(con));
            
            // Create Form
            FormImpl.create(question.getForm());
            
            // Create Automation
            AutomationImpl.create(question.getAutomation());
            
            // Create New Entry
            ps = con.prepareStatement(QuestionImpl.CREATE);
            ps.setInt(1, question.getQuestionId());
            ps.setInt(2, question.getRevisionId());
            ps.setString(3, question.getName());
            ps.setInt(4, question.getForm().getFormId());
            ps.setInt(5, question.getAutomation().getAutomationId());
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
        return question;
    }
    
    
    
    /***** Update Methods *****/
    public static Question update(Question question) {
        return QuestionImpl.update(question, null);
    }
    public static Question update(Question question, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;
        
        // Return if null
        if(question == null) {
            return null;
        }

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Update Existing Entry
            ps = con.prepareStatement(QuestionImpl.UPDATE);
            ps.setString(1, question.getName());
            ps.setInt(2, question.getForm().getFormId());
            ps.setInt(3, question.getAutomation().getAutomationId());
            ps.setInt(4, question.getQuestionId());
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
        return question;
    }
    
    
    
    /***** Delete Methods *****/
    public static void deleteByQuestionId(int questionId) {
        QuestionImpl.deleteByQuestionId(questionId, null);
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
            ps = con.prepareStatement(QuestionImpl.DELETE_BY_QUESTION_ID);
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
    public static void deleteByFormId(int formId) {
        QuestionImpl.deleteByFormId(formId, null);
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
            ps = con.prepareStatement(QuestionImpl.DELETE_BY_FORM_ID);
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
    public static void deleteByAutomationId(int automationId) {
        QuestionImpl.deleteByAutomationId(automationId, null);
    }
    public static void deleteByAutomationId(int automationId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Delete Existing Entry
            ps = con.prepareStatement(QuestionImpl.DELETE_BY_AUTOMATION_ID);
            ps.setInt(1, automationId);
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