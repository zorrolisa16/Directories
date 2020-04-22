package model.dao;

import model.ConnectionPool;
import model.exception.*;

/**
 * @author Polina Astashko
 */
abstract class DAO {

	private ConnectionPool dbc;

	protected ConnectionPool getDBConnector() {
		System.out.println("requested to db connector");
		return dbc;
	}

	protected DAO() throws DAOException {
		try {
			dbc = ConnectionPool.getInstance();
			System.out.println("Connection to database from dao inited");
		} catch (DBConnectionException e) {
			throw new DAOException("Can't create DBConnectorPool ", e);
		}
	}

}