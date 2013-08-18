package com.implementations;

import com.common.DBConnection;
import com.model.Code;
import com.model.DisplayTextTranslation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DisplayTextTranslationImpl {

    private static final String FIND_BY_DISPLAY_TEXT_TRANSLATION_ID = "SELECT * FROM display_text_translation_legacy WHERE display_text_translation_id = ?;";
    private static final String FIND_BY_DISPLAY_TEXT_ID_AND_CODE_ID = "SELECT * FROM display_text_translation_legacy WHERE display_text_id = ? AND code_id = ?;";
    private static final String FIND_BY_DISPLAY_TEXT_ID = "SELECT * FROM display_text_translation_legacy WHERE display_text_id = ?;";
    private static final String FIND_NEXT_ID = "SELECT TOP 1 display_text_translation_id FROM display_text_translation_legacy ORDER BY display_text_translation_id DESC;";
    private static final String CREATE = "INSERT INTO display_text_translation_legacy (display_text_translation_id, display_text_id, code_id, value) VALUES (?, ?, ?, ?);";
    private static final String DELETE_BY_DISPLAY_TEXT_TRANSLATION_ID = "DELETE FROM display_text_translation_legacy WHERE display_text_translation_id = ?;";
    private static final String DELETE_BY_DISPLAY_TEXT_ID = "DELETE FROM display_text_translation_legacy WHERE display_text_id = ?;";
    
    
    
    /***** Find Methods *****/
    public static DisplayTextTranslation findByDisplayTextTranslationId(int displayTextTranslationId) {
        return DisplayTextTranslationImpl.findByDisplayTextTranslationId(displayTextTranslationId, null);
    }
    public static DisplayTextTranslation findByDisplayTextTranslationId(int displayTextTranslationId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        DisplayTextTranslation displayTextTranslation = new DisplayTextTranslation();

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get List By Id
            ps = con.prepareStatement(DisplayTextTranslationImpl.FIND_BY_DISPLAY_TEXT_TRANSLATION_ID);
            ps.setInt(1, displayTextTranslationId);
            rs = ps.executeQuery();
            while(rs.next()) {
                displayTextTranslation = new DisplayTextTranslation(rs.getInt("display_text_translation_id"), rs.getInt("display_text_id"), CodeImpl.findByCodeId(rs.getInt("code_id"), con), rs.getString("value"));
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
        return displayTextTranslation;
    }
    public static List<DisplayTextTranslation> findByDisplayTextIdAndCodeId(int displayTextId, int codeId) {
        return DisplayTextTranslationImpl.findByDisplayTextIdAndCodeId(displayTextId, codeId, null);
    }
    public static List<DisplayTextTranslation> findByDisplayTextIdAndCodeId(int displayTextId, int codeId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        List<DisplayTextTranslation> displayTextTranslations = new ArrayList<DisplayTextTranslation>();

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get List By Id
            ps = con.prepareStatement(DisplayTextTranslationImpl.FIND_BY_DISPLAY_TEXT_ID_AND_CODE_ID);
            ps.setInt(1, displayTextId);
            ps.setInt(2, codeId);
            rs = ps.executeQuery();
            while(rs.next()) {
                displayTextTranslations.add(new DisplayTextTranslation(rs.getInt("display_text_translation_id"), rs.getInt("display_text_id"), CodeImpl.findByCodeId(rs.getInt("code_id"), con), rs.getString("value")));
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
        return displayTextTranslations;
    }
    public static List<DisplayTextTranslation> findByDisplayTextId(int displayTextId) {
        return DisplayTextTranslationImpl.findByDisplayTextId(displayTextId, null);
    }
    public static List<DisplayTextTranslation> findByDisplayTextId(int displayTextId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        List<DisplayTextTranslation> displayTextTranslations = new ArrayList<DisplayTextTranslation>();

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get List By Id
            ps = con.prepareStatement(DisplayTextTranslationImpl.FIND_BY_DISPLAY_TEXT_ID);
            ps.setInt(1, displayTextId);
            rs = ps.executeQuery();
            while(rs.next()) {
                displayTextTranslations.add(new DisplayTextTranslation(rs.getInt("display_text_translation_id"), rs.getInt("display_text_id"), CodeImpl.findByCodeId(rs.getInt("code_id"), con), rs.getString("value")));
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
        return displayTextTranslations;
    }
    public static int findNextId() {
        return DisplayTextTranslationImpl.findNextId(null);
    }
    public static int findNextId(Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        int displayTextTranslationId = 1;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get All the Code Types
            ps = con.prepareStatement(DisplayTextTranslationImpl.FIND_NEXT_ID);
            rs = ps.executeQuery();
            while(rs.next()) {
                displayTextTranslationId = rs.getInt("display_text_translation_id") + 1;
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
        return displayTextTranslationId;
    }
    
    
    
    /***** Create Methods *****/
    public static DisplayTextTranslation create(int displayTextId, String value) {
        return DisplayTextTranslationImpl.create(new DisplayTextTranslation(0, displayTextId, new Code(), value), null);
    }
    public static DisplayTextTranslation create(int displayTextId, String value, Connection con) {
        return DisplayTextTranslationImpl.create(new DisplayTextTranslation(0, displayTextId, new Code(), value), con);
    }
    public static DisplayTextTranslation create(DisplayTextTranslation displayTextTranslation) {
        return DisplayTextTranslationImpl.create(displayTextTranslation, null);
    }
    public static DisplayTextTranslation create(DisplayTextTranslation displayTextTranslation, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;
        
        // Return if null
        if(displayTextTranslation == null) {
            return null;
        }
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Set Primary Key
            displayTextTranslation.setDisplayTextTranslationId(DisplayTextTranslationImpl.findNextId(con));
            
            // Complete the Code Object
            if(displayTextTranslation.getCode().getCodeId() <= 0) {
                displayTextTranslation.setCode(CodeImpl.findByCode(displayTextTranslation.getCode(), con));
            }
            
            // Create New Entry
            ps = con.prepareStatement(DisplayTextTranslationImpl.CREATE);
            ps.setInt(1, displayTextTranslation.getDisplayTextTranslationId());
            ps.setInt(2, displayTextTranslation.getDisplayTextId());
            ps.setInt(3, displayTextTranslation.getCode().getCodeId());
            ps.setString(4, displayTextTranslation.getValue());
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
        return displayTextTranslation;
    }
    
    
    
    /***** Delete Methods *****/
    public static void deleteByDisplayTextTranslationId(int displayTextTranslationId) {
        DisplayTextTranslationImpl.deleteByDisplayTextTranslationId(displayTextTranslationId, null);
    }
    public static void deleteByDisplayTextTranslationId(int displayTextTranslationId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Delete Existing Entry
            ps = con.prepareStatement(DisplayTextTranslationImpl.DELETE_BY_DISPLAY_TEXT_TRANSLATION_ID);
            ps.setInt(1, displayTextTranslationId);
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
    public static void deleteByDisplayTextId(int displayTextId) {
        DisplayTextTranslationImpl.deleteByDisplayTextId(displayTextId, null);
    }
    public static void deleteByDisplayTextId(int displayTextId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Delete Existing Entry
            ps = con.prepareStatement(DisplayTextTranslationImpl.DELETE_BY_DISPLAY_TEXT_ID);
            ps.setInt(1, displayTextId);
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