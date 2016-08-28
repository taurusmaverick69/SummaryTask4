package ua.nure.lyubimtsev.SummaryTask4.db.dao;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import org.apache.log4j.Logger;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.daoimpl.*;
import ua.nure.lyubimtsev.SummaryTask4.db.dao.entitydao.*;
import ua.nure.lyubimtsev.SummaryTask4.exception.DBException;
import ua.nure.lyubimtsev.SummaryTask4.exception.Messages;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public class MySQLDAOFactory extends DAOFactory {


    private static final Logger LOG = Logger.getLogger(MySQLDAOFactory.class);

    private static Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/hospitaldb?autoReconnect=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    /**
     * Returns a DB connection from the Pool Connections. Before using this
     * method you must configure the Date Source and the Connections Pool in
     * your WEB_APP_ROOT/META-INF/context.xml file.
     *
     * @return DB connection.
     */
    public static Connection createConnection() throws DBException {
        try {
            if (connection == null || connection.isClosed()) {
                try {
                    Context ctx = new InitialContext();
                    DataSource dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/hospitalDB");
                    LOG.trace("Data source ==> " + dataSource);
                    connection = dataSource.getConnection();

                } catch (NamingException e) {
                    LOG.error(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, e);
                    DriverManager.registerDriver(new FabricMySQLDriver());
                    connection = DriverManager.getConnection(URL, USER, PASSWORD);
                }

            }
        } catch (SQLException e) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CONNECTION, e);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_CONNECTION, e);
        }
        LOG.trace("Connection ==> " + connection);
        return connection;
    }



    // //////////////////////////////////////////////////////////
    // DB util methods
    // //////////////////////////////////////////////////////////

    /**
     * Closes a connection.
     *
     * @param con
     * Connection to be closed.
     */
    public static void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                LOG.error(Messages.ERR_CANNOT_CLOSE_CONNECTION, ex);
            }
        }
    }

    /**
     * Closes a statement object.
     */
    public static void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                LOG.error(Messages.ERR_CANNOT_CLOSE_STATEMENT, ex);
            }
        }
    }

    /**
     * Closes a result set object.
     */
    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                LOG.error(Messages.ERR_CANNOT_CLOSE_RESULTSET, ex);
            }
        }
    }

    /**
     * Closes resources.
     */
    public static void close(Connection con, Statement stmt, ResultSet rs) {
        close(rs);
        close(stmt);
        close(con);
    }


    /**
     * Rollbacks a connection.
     *
     * @param con
     *            Connection to be rollbacked.
     */
    public static void rollback(Connection con) {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                LOG.error("Cannot rollback transaction", ex);
            }
        }
    }



    @Override
    public AdminDAO getAdminDAO() {
        return new AdminDAOImpl();
    }

    @Override
    public CategoryDAO getCategoryDAO() {
        return new CategoryDAOImpl();
    }

    @Override
    public AppointmentDAO getAppointmentDAO() {
        return new AppointmentDAOImpl();
    }

    @Override
    public DoctorDAO getDoctorDAO() {
        return new DoctorDAOImpl();
    }

    @Override
    public MedicalCardDAO getMedicalCardDAO() {
        return new MedicalCardDAOImpl();
    }

    @Override
    public PatientDAO getPatientDAO() {
        return new PatientDAOImpl();
    }

    @Override
    public StateDAO getStateDAO() {
        return new StateDAOImpl();
    }

    @Override
    public TypeDAO getTypeDAO() {
        return new TypeDAOImpl();
    }

}
