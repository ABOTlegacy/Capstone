package com.implementations;

import com.common.DBConnection;
import com.model.DisplayText;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DisplayTextImpl {

    private static final String FIND_BY_DISPLAY_TEXT_ID = "SELECT * FROM display_text_legacy WHERE display_text_id = ?;";
    private static final String FIND_NEXT_ID = "SELECT TOP 1 display_text_id FROM display_text_legacy ORDER BY display_text_id DESC;";
    private static final String CREATE = "INSERT INTO display_text_legacy (display_text_id) VALUES (?);";
    private static final String DELETE_BY_DISPLAY_TEXT_ID = "DELETE FROM display_text_legacy WHERE display_text_id = ?;";
    
    
    
    /***** Find Methods *****/
    public static DisplayText findByDisplayTextId(int displayTextId) {
        return DisplayTextImpl.findByDisplayTextId(displayTextId, null);
    }
    public static DisplayText findByDisplayTextId(int displayTextId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        DisplayText displayText = new DisplayText();
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get List By Id
            ps = con.prepareStatement(DisplayTextImpl.FIND_BY_DISPLAY_TEXT_ID);
            ps.setInt(1, displayTextId);
            rs = ps.executeQuery();
            while(rs.next()) {
                displayText = new DisplayText(rs.getInt("display_text_id"), DisplayTextTranslationImpl.findByDisplayTextId(displayTextId, con));
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
        return displayText;
    }
    public static List<DisplayText> findByDisplayTextIdAndCodeId(int displayTextId, int codeId) {
        return DisplayTextImpl.findByDisplayTextIdAndCodeId(displayTextId, codeId, null);
    }
    public static List<DisplayText> findByDisplayTextIdAndCodeId(int displayTextId, int codeId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        List<DisplayText> displayTexts = new ArrayList<DisplayText>();

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get List By Id
            ps = con.prepareStatement(DisplayTextImpl.FIND_BY_DISPLAY_TEXT_ID);
            ps.setInt(1, displayTextId);
            rs = ps.executeQuery();
            while(rs.next()) {
                displayTexts.add(new DisplayText(rs.getInt("display_text_id"), DisplayTextTranslationImpl.findByDisplayTextIdAndCodeId(displayTextId, codeId, con)));
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
        return displayTexts;
    }
    public static int findNextId() {
        return DisplayTextImpl.findNextId(null);
    }
    public static int findNextId(Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        int displayTextId = 1;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get All the Code Types
            ps = con.prepareStatement(DisplayTextImpl.FIND_NEXT_ID);
            rs = ps.executeQuery();
            while(rs.next()) {
                displayTextId = rs.getInt("display_text_id") + 1;
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
        return displayTextId;
    }
    
    
    
    /***** Create Methods *****/
    public static DisplayText create(DisplayText displayText) {
        return DisplayTextImpl.create(displayText, null);
    }
    public static DisplayText create(DisplayText displayText, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;
        
        // Return if null
        if(displayText == null) {
            return null;
        }

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Set Primary Key
            displayText.setDisplayTextId(DisplayTextImpl.findNextId(con));
            
            // Store Other translations
            DisplayTextTranslationImpl.create(displayText.getDisplayTextId(), displayText.getDisplayTextTranslation(), con);
            
            // Create New Entry
            ps = con.prepareStatement(DisplayTextImpl.CREATE);
            ps.setInt(1, displayText.getDisplayTextId());
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
        return displayText;
    }
    
    
    
    /***** Delete Methods *****/
    public static void deleteByDisplayTextId(int displayTextId) {
        DisplayTextImpl.deleteByDisplayTextId(displayTextId, null);
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
            
            // Delete Dependencies
            DisplayTextTranslationImpl.deleteByDisplayTextId(displayTextId, con);
            
            // Delete Existing Entry
            ps = con.prepareStatement(DisplayTextImpl.DELETE_BY_DISPLAY_TEXT_ID);
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