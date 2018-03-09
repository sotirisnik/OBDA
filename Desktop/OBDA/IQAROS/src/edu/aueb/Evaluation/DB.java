//package it.unibas.spicy.persistence;
//
//import com.ibatis.common.jdbc.ScriptRunner;
//import it.unibas.spicy.persistence.relational.IConnectionFactory;
//import it.unibas.spicy.persistence.relational.SimpleDbConnectionFactory;
//import it.unibas.spicy.utility.SpicyEngineConstants;
//import java.io.StringReader;
//import java.sql.Connection;
//
//public class DAOHandleDB {
//    
//    public void createNewDatabase(int no) throws DAOException{
//        handleDatabase(no, true);
//    }
//    
//    public void dropDatabase(int no) throws DAOException{
//        handleDatabase(no, false);
//    }
//    
//    private void handleDatabase(int no, boolean create) throws DAOException{
//        IConnectionFactory connectionFactory = null;
//        Connection connection = null; 
//        
//        AccessConfiguration accessConfiguration = new AccessConfiguration();
//        accessConfiguration.setDriver(SpicyEngineConstants.ACCESS_CONFIGURATION_DRIVER);
//        accessConfiguration.setUri(SpicyEngineConstants.ACCESS_CONFIGURATION_URI);
//        accessConfiguration.setLogin(SpicyEngineConstants.ACCESS_CONFIGURATION_LOGIN);
//        accessConfiguration.setPassword(SpicyEngineConstants.ACCESS_CONFIGURATION_PASS);
//        try  {
//            connectionFactory = new SimpleDbConnectionFactory();
//            connection = connectionFactory.getConnection(accessConfiguration);
//        
//            ScriptRunner scriptRunner = new ScriptRunner(connection, true, true);
//            scriptRunner.setLogWriter(null);
//            StringBuilder createSchemasQuery = new StringBuilder(); 
//            createSchemasQuery.append("drop database if exists "+SpicyEngineConstants.MAPPING_TASK_DB_NAME+no+";\n");
//            if (create){
//                createSchemasQuery.append("create database "+SpicyEngineConstants.MAPPING_TASK_DB_NAME+no+";\n");
//            }
//            scriptRunner.runScript(new StringReader(createSchemasQuery.toString()));
//        }
//        catch (Exception ex) {
//            throw new DAOException(ex);
//        }
//        finally
//        {   
//            connectionFactory.close(connection);
//        }
//    }
//}
package edu.aueb.Evaluation;

public class DB{}