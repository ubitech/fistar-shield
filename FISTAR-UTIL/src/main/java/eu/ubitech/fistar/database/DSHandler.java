package eu.ubitech.fistar.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Singleton pattern example using Java Enumeration
 */
public enum DSHandler {

    INSTANCE;

    private DataSource datasource = null;
    private Connection connection = null;

    private final String DEVELOPMENT_PROFILE = "development";
    private final String DEPLOYMENT_PROFILE = "deployment";
    private final Properties prop = new Properties();

    //All fields are readen from datasource.properties file
    private final String DSName;
    private final String DBName;
    private final String DBUser;
    private final String DBPass;
    private final String DBPort;
    private final String Host;
    private final String Profile;
    private final String DBURL;

    DSHandler() {
        try {
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("datasource.properties"));
        } catch (IOException ex) {
            Logger.getLogger(DSHandler.class.getName()).severe("Couldn't read properties file...");
        }
        this.DSName = prop.getProperty("DSName").trim();
        this.DBName = prop.getProperty("DBName").trim();
        this.DBUser = prop.getProperty("DBUser").trim();
        this.DBPass = prop.getProperty("DBPass").trim();
        this.DBPort = prop.getProperty("DBPort").trim();
        this.Profile = prop.getProperty("Profile").trim();

        //Set dynamically the url of mysql server based on the current profile
        if (Profile.equalsIgnoreCase(DEVELOPMENT_PROFILE)) {
            this.Host = prop.getProperty("host_development_ip").trim();
        } else if (Profile.equalsIgnoreCase(DEPLOYMENT_PROFILE)) {
            this.Host = prop.getProperty("host_deployment_ip").trim();
        } else {
            throw new UnsupportedOperationException("Unknown profile: " + this.Profile);
        }
        this.DBURL = "jdbc:mysql://" + this.Host + ":" + this.DBPort + "/" + this.DBName + "?user=" + this.DBUser + "&password=" + this.DBPass + "&useEncoding=true&characterEncoding=UTF-8&";
    }

    public Connection getDatasource() {

        if (datasource == null) {
            Logger.getLogger(DSHandler.class.getName()).log(Level.INFO, "Datasource: {0} is empty!", DSName);
            try {
                Context envContext = new InitialContext();
                datasource = (DataSource) envContext.lookup("java:jboss/datasources/" + DSName);
                Logger.getLogger(DSHandler.class.getName()).log(Level.INFO, "Created new  datasource!");
            } catch (NamingException ex) {
                Logger.getLogger(DSHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            //Quick Fix when requesting connection outside the app server
            if (null == datasource) {
                Logger.getLogger(DSHandler.class.getName()).warning("Fail to get a MySQL from AppServer - Datasource is null, requesting a native MySql Connection....");
                return getConnection();
            }
            return datasource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(DSHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public Connection getConnection() {
        try {

            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            } catch (InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(DSHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.connection = DriverManager.getConnection(this.DBURL);

                return this.connection;
            } catch (SQLException ex) {
                Logger.getLogger(DSHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DSHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.connection;
    }

    /**
     * Close all the resources used to access a Database
     *
     * @param connection A MySql Connection instance
     * @param preparedStatement A PreparedStatment stream
     * @param resultSet A ResultSet stream
     * @return
     */
    public boolean closeDBStreams(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        boolean status = true;
        if (null != resultSet) {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                Logger.getLogger(DSHandler.class.getName()).log(Level.SEVERE, null, ex);
                status = false;
            }
        }
        if (null != preparedStatement) {
            try {
                preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DSHandler.class.getName()).log(Level.SEVERE, null, ex);
                status = false;
            }
        }
        if (null != connection) {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(DSHandler.class.getName()).log(Level.SEVERE, null, ex);
                status = false;
            }
        }

        if (status) {
            //Logger.getLogger(OracleDBHandler.class.getName()).info("Successfuly closed all Database streams...");
        } else {
            Logger.getLogger(DSHandler.class.getName()).severe("Could not close all Database streams... This may lead to memory leak..");
        }

        return status;
    }

    public boolean rollback(Connection connection) {
        boolean isSuccess = false;
        if (null != connection) {
            try {
                connection.rollback();
                isSuccess = !isSuccess;
            } catch (SQLException ex) {
                Logger.getLogger(DSHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return isSuccess;
    }

    public static void main(String[] args) {

        System.out.println("Current Profile: " + DSHandler.INSTANCE.Profile + "\nDatabase URL: " + DSHandler.INSTANCE.DBURL);
        Connection ds = DSHandler.INSTANCE.getDatasource();
        PreparedStatement stm;
        try {
            stm = ds.prepareStatement("SELECT * from User");
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                System.out.println(rs.getString("username"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DSHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
