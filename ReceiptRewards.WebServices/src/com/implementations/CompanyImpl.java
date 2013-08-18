package com.implementations;

import com.common.DBConnection;
import com.model.Company;
import com.model.Survey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CompanyImpl {

    private static final String FIND_ALL = "SELECT * FROM company_legacy;";
    private static final String FIND_BY_COMPANY_ID = "SELECT * FROM company_legacy WHERE company_id = ?;";
    private static final String FIND_NEXT_ID = "SELECT TOP 1 company_id FROM company_legacy ORDER BY company_id DESC;";
    private static final String CREATE = "INSERT INTO company_legacy (company_id, name) VALUES (?, ?);";
    private static final String UPDATE_BY_ID = "UPDATE company_legacy SET name = ? WHERE company_id = ?;";
    private static final String DELETE_BY_ID = "DELETE FROM company_legacy WHERE company_id = ?;";
    
    
    
    /***** Find Methods *****/
    public static List<Company> findAll() {
        return CompanyImpl.findAll(null);
    }
    public static List<Company> findAll(Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        List<Company> companies = new ArrayList<Company>();

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get List of All
            ps = con.prepareStatement(CompanyImpl.FIND_ALL);
            rs = ps.executeQuery();
            while(rs.next()) {
                companies.add(new Company(rs.getInt("company_id"), rs.getString("name"), null));
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
        return companies;
    }
    public static Company findByCompnayId(int companyId) {
        return CompanyImpl.findByCompnayId(companyId, null);
    }
    public static Company findByCompnayId(int companyId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        Company company = new Company();

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get List By Id
            ps = con.prepareStatement(CompanyImpl.FIND_BY_COMPANY_ID);
            ps.setInt(1, companyId);
            rs = ps.executeQuery();
            while(rs.next()) {
                company = new Company(rs.getInt("company_id"), rs.getString("name"), null);
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
        return company;
    }
    public static int findNextId() {
        return CompanyImpl.findNextId(null);
    }
    public static int findNextId(Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean conBool = false;
        int companyId = 1;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Get All the Code Types
            ps = con.prepareStatement(CompanyImpl.FIND_NEXT_ID);
            rs = ps.executeQuery();
            while(rs.next()) {
                companyId = rs.getInt("company_id") + 1;
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
        return companyId;
    }
    
    
    
    /***** Create Methods *****/
    public static Company create(Company company) {
        return CompanyImpl.create(company, null);
    }
    public static Company create(Company company, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;
        
        // Return if null
        if(company == null) {
            return null;
        }
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Set ID
            company.setCompanyId(CompanyImpl.findNextId(con));
            
            // Create Survey
            if(company.getSurveys() == null) {
                company.setSurveys(new ArrayList<Survey>());
            }
            for(int i = 0; i < company.getSurveys().size(); i++) {
                SurveyImpl.create(company.getSurveys().get(i), con);
            }
            
            // Create New Entry
            ps = con.prepareStatement(CompanyImpl.CREATE);
            ps.setInt(1, company.getCompanyId());
            ps.setString(2, company.getName());
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
            } catch(Exception ex){
                System.out.println("Error: " + ex.getMessage());
            }
        }
        // Return
        return company;
    }
    
    
    
    /***** Update Methods *****/
    public static Company update(Company company) {
        return CompanyImpl.update(company, null);
    }
    public static Company update(Company company, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        // Return if null
        if(company == null) {
            return null;
        }
        
        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Update Existing Entry
            ps = con.prepareStatement(CompanyImpl.UPDATE_BY_ID);
            ps.setString(1, company.getName());
            ps.setInt(2, company.getCompanyId());
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
        return company;
    }
    
    
    
    /***** Delete Methods *****/
    public static void deleteById(int companyId) {
        CompanyImpl.deleteById(companyId, null);
    }
    public static void deleteById(int companyId, Connection con) {
        // Instantiate Variables
        PreparedStatement ps = null;
        boolean conBool = false;

        try { // Make Connection
            if(con == null) {
                con = DBConnection.getConnection();
                conBool = true;
            }
            
            // Delete Survey
            SurveyImpl.deleteByCompanyId(companyId, con);
            
            // Delete Company
            ps = con.prepareStatement(CompanyImpl.DELETE_BY_ID);
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