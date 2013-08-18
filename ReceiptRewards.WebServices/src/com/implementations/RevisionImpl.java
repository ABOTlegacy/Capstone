package com.implementations;

import com.common.DBConnection;
import com.model.Code;
import com.model.Form;
import com.model.Revision;
import com.model.Reward;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RevisionImpl {

    private static final String FIND_ALL = "SELECT * FROM revision_legacy;";
    private static final String FIND_BY_ACTIVE_SURVEY_ID = "SELECT * FROM revision_legacy WHERE survey_id = ? AND active = 'true';";
    private static final String FIND_BY_SURVEY_ID = "SELECT * FROM revision_legacy WHERE survey_id = ?;";
    private static final String FIND_BY_REVISION_ID = "SELECT * FROM revision_legacy WHERE revision_id = ?;";
    private static final String FIND_BY_QUESTION_ID = "SELECT * FROM revision_legacy WHERE question_id = ?;";
    private static final String FIND_NEXT_ID = "SELECT TOP 1 revision_id FROM revision_legacy ORDER BY revision_id DESC;";
    private static final String CREATE = "INSERT INTO revision_legacy (revision_id, survey_id, active, date_created, revision_number, question_id, reward_id) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String UPDATE = "UPDATE revision_legacy SET active = ?, revision_number = ? WHERE revision_id = ?;";
    private static final String UPDATE_MAKE_ALL_INACTIVE = "UPDATE revision_legacy SET active = 'false' WHERE survey_id = ?;";
    private static final String DELETE_BY_REVISION_ID = "DELETE FROM revision_legacy WHERE revision_id = ?;";
    private static final String DELETE_BY_SURVEY_ID = "DELETE FROM revision_legacy WHERE survey_id = ?;";
    
    
    
    /***** Find Methods *****/
    public static Revision findByRevisionId(int revisionId) {
        return RevisionImpl.findByRevisionId(revisionId, null);
    }
    public static Revision findByRevisionId(int revisionId, Connection con) {
        return RevisionImpl.findByRevisionId(revisionId, 1000, con);
    }
    public static Revision findByRevisionId(int revisionId, int recursiveLevel) {
        return RevisionImpl.findByRevisionId(revisionId, recursiveLevel, null);
    }
    public static Revision findByRevisionId(int revisionId, int recursiveLevel, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        Revision revision = new Revision();

        // Verify
        if(revisionId <= 0 || recursiveLevel <= 0) {
            return null;
        }
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get List of All
            ps = con.prepareStatement(RevisionImpl.FIND_BY_REVISION_ID);
            ps.setInt(1, revisionId);
            rs = ps.executeQuery();
            while(rs.next()) {
                revision = new Revision(rs.getInt("revision_id"), rs.getInt("survey_id"), (rs.getString("active").equals("true") ? true : false), rs.getDate("date_created"), rs.getString("revision_number"), QuestionImpl.findByQuestionId(rs.getInt("question_id"), (recursiveLevel - 1), con), RewardImpl.findByRewardId(rs.getInt("reward_id"), con));
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
        return revision;
    }
    public static List<Revision> findByActiveSurveyId(int surveyId) {
        return RevisionImpl.findByActiveSurveyId(surveyId, 100);
    }
    public static List<Revision> findByActiveSurveyId(int surveyId, Connection con) {
        return RevisionImpl.findByActiveSurveyId(surveyId, 100, con);
    }
    public static List<Revision> findByActiveSurveyId(int surveyId, int recursiveLevel) {
        return RevisionImpl.findByActiveSurveyId(surveyId, recursiveLevel, null);
    }
    public static List<Revision> findByActiveSurveyId(int surveyId, int recursiveLevel, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        List<Revision> revisions = new ArrayList<Revision>();

        // Verify
        if(surveyId <= 0 || recursiveLevel <= 0) {
            return null;
        }
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get List By Id
            ps = con.prepareStatement(RevisionImpl.FIND_BY_ACTIVE_SURVEY_ID);
            ps.setInt(1, surveyId);
            rs = ps.executeQuery();
            while(rs.next()) {
                revisions.add(new Revision(rs.getInt("revision_id"), rs.getInt("survey_id"), (rs.getString("active").equals("true") ? true : false), rs.getDate("date_created"), rs.getString("revision_number"), QuestionImpl.findByQuestionId(rs.getInt("question_id"), (recursiveLevel - 1), con), RewardImpl.findByRewardId(rs.getInt("reward_id"), con)));
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
        return revisions;
    }
    public static List<Revision> findBySurveyId(int surveyId) {
        return RevisionImpl.findBySurveyId(surveyId, 100);
    }
    public static List<Revision> findBySurveyId(int surveyId, Connection con) {
        return RevisionImpl.findBySurveyId(surveyId, 100, con);
    }
    public static List<Revision> findBySurveyId(int surveyId, int recursiveLevel) {
        return RevisionImpl.findBySurveyId(surveyId, recursiveLevel, null);
    }
    public static List<Revision> findBySurveyId(int surveyId, int recursiveLevel, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        List<Revision> revisions = new ArrayList<Revision>();

        // Verify
        if(surveyId <= 0 || recursiveLevel <= 0) {
            return null;
        }
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get List By Id
            ps = con.prepareStatement(RevisionImpl.FIND_BY_SURVEY_ID);
            ps.setInt(1, surveyId);
            rs = ps.executeQuery();
            while(rs.next()) {
                revisions.add(new Revision(rs.getInt("revision_id"), rs.getInt("survey_id"), (rs.getString("active").equals("true") ? true : false), rs.getDate("date_created"), rs.getString("revision_number"), QuestionImpl.findByQuestionId(rs.getInt("question_id"), (recursiveLevel - 1), con), RewardImpl.findByRewardId(rs.getInt("reward_id"), con)));
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
        return revisions;
    }
    public static List<Revision> findAll() {
        return RevisionImpl.findAll(null);
    }
    public static List<Revision> findAll(Connection con) {
        return RevisionImpl.findAll(1000, null);
    }
    public static List<Revision> findAll(int recursiveLevel) {
        return RevisionImpl.findAll(recursiveLevel, null);
    }
    public static List<Revision> findAll(int recursiveLevel, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        List<Revision> revisions = new ArrayList<Revision>();

        // Verify
        if(recursiveLevel <= 0) {
            return null;
        }
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get List By Id
            ps = con.prepareStatement(RevisionImpl.FIND_ALL);
            rs = ps.executeQuery();
            while(rs.next()) {
                revisions.add(new Revision(rs.getInt("revision_id"), rs.getInt("survey_id"), (rs.getString("active").equals("true") ? true : false), rs.getDate("date_created"), rs.getString("revision_number"), QuestionImpl.findByQuestionId(rs.getInt("question_id"), (recursiveLevel - 1), con), RewardImpl.findByRewardId(rs.getInt("reward_id"), con)));
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
        return revisions;
    }
    public static List<Revision> findByQuestionId(int questionId) {
        return RevisionImpl.findByQuestionId(questionId, null);
    }
    public static List<Revision> findByQuestionId(int questionId, Connection con) {
        return RevisionImpl.findByQuestionId(questionId, 1000, con);
    }
    public static List<Revision> findByQuestionId(int questionId, int recursiveLevel) {
        return RevisionImpl.findByQuestionId(questionId, recursiveLevel, null);
    }
    public static List<Revision> findByQuestionId(int questionId, int recursiveLevel, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        List<Revision> revisions = new ArrayList<Revision>();

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
            ps = con.prepareStatement(RevisionImpl.FIND_BY_QUESTION_ID);
            ps.setInt(1, questionId);
            rs = ps.executeQuery();
            while(rs.next()) {
                revisions.add(new Revision(rs.getInt("revision_id"), rs.getInt("survey_id"), (rs.getString("active").equals("true") ? true : false), rs.getDate("date_created"), rs.getString("revision_number"), QuestionImpl.findByQuestionId(rs.getInt("question_id"), (recursiveLevel - 1), con), RewardImpl.findByRewardId(rs.getInt("reward_id"), con)));
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
        return revisions;
    }
    public static int findNextId() {
        return RevisionImpl.findNextId(null);
    }
    public static int findNextId(Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        int revisionId = 1;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get All the Code Types
            ps = con.prepareStatement(RevisionImpl.FIND_NEXT_ID);
            rs = ps.executeQuery();
            while(rs.next()) {
                revisionId = rs.getInt("revision_id") + 1;
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
        return revisionId;
    }
    
    
    
    /***** Create Methods *****/
    public static Revision create(Revision revision) {
        return RevisionImpl.create(revision, null);
    }
    public static Revision create(Revision revision, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        // Return if null
        if(revision == null) {
            return null;
        }
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Set ID
            revision.setRevisionId(RevisionImpl.findNextId(con));
            
            // Create Question
            revision.getQuestion().setRevisionId(revision.getRevisionId());
            revision.setQuestion(QuestionImpl.create(revision.getQuestion(), con));
            
            // Create Reward
            revision.setReward(RewardImpl.create(new Reward(0, new Code(0), new Form(0, null, null)), con));
            
            // Update Dependencies
            if(revision.isActive()) {
                RevisionImpl.updateMakeAllInactive(revision.getSurveyId());
            }
            
            // Create New Entry
            ps = con.prepareStatement(RevisionImpl.CREATE);
            ps.setInt(1, revision.getRevisionId());
            ps.setInt(2, revision.getSurveyId());
            ps.setString(3, (revision.isActive() ? "true" : "false"));
            ps.setDate(4, new java.sql.Date(revision.getDateCreated().getTime()));
            ps.setString(5, revision.getRevisionNumber());
            ps.setInt(6, revision.getQuestion().getQuestionId());
            ps.setInt(7, revision.getReward().getRewardId());
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
        return revision;
    }
    
    
    
    /***** Update Methods *****/
    public static Revision update(Revision revision) {
        return RevisionImpl.update(revision, null);
    }
    public static Revision update(Revision revision, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;
        
        // Return if null
        if(revision == null) {
            return null;
        }
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Update Dependencies
            if(revision.isActive()) {
                RevisionImpl.updateMakeAllInactive(revision.getSurveyId(), con);
            }
            
            // Update Existing Entry
            ps = con.prepareStatement(RevisionImpl.UPDATE);
            ps.setString(1, (revision.isActive() ? "true" : "false"));
            ps.setString(2, revision.getRevisionNumber());
            ps.setInt(3, revision.getRevisionId());
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
        return revision;
    }
    public static void updateMakeAllInactive(int surveyId) {
        RevisionImpl.updateMakeAllInactive(surveyId, null);
    }
    public static void updateMakeAllInactive(int surveyId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Update Existing Entry
            ps = con.prepareStatement(RevisionImpl.UPDATE_MAKE_ALL_INACTIVE);
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
    
    
    
    /***** Delete Methods *****/
    public static void deleteByRevisionId(int revisionId) {
        RevisionImpl.deleteByRevisionId(revisionId, null);
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
            
            // Delete Automation ID
            AutomationImpl.deleteByRevisionId(revisionId, con);
            
            // Delete Existing Entry
            ps = con.prepareStatement(RevisionImpl.DELETE_BY_REVISION_ID);
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
    public static void deleteBySurveyId(int surveyId) {
        RevisionImpl.deleteBySurveyId(surveyId, null);
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
            
            // Delete Automation ID
            Revision revision = RevisionImpl.findBySurveyId(surveyId).get(0);
            AutomationImpl.deleteByRevisionId(revision.getRevisionId(), con);
            
            // Delete Existing Entry
            ps = con.prepareStatement(RevisionImpl.DELETE_BY_SURVEY_ID);
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
}