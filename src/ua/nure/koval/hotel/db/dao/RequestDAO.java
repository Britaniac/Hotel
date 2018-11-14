package ua.nure.koval.hotel.db.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.nure.koval.hotel.db.DBManager;
import ua.nure.koval.hotel.entity.Request;
import ua.nure.koval.hotel.entity.enums.RoomClass;

public class RequestDAO implements DAO<Request> {
	private static final String SQL_FIND_BY_ID = "SELECT * FROM requests where ID=?";
	private static final String SQL_FIND_ALL = "SELECT * FROM requests";
	private static final String SQL_INSERT_REQUEST = "INSERT INTO requests VALUES(DEFAULT, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE_REQUEST = "UPDATE requests SET capacity=?, room_class=?, room_id=?, created=?, date_from=?, date_to=?, invoice_id=? where ID=?";
	private static final String SQL_DELETE_REQUEST = "DELETE FROM requests WHERE ID=?";
	private static final String SQL_FIND_UNPAID = "SELECT requests.* FROM requests, invoices WHERE requests.invoice_id=invoices.ID AND invoices.paid=FALSE";
	private static final String SQL_FIND_NEW = "SELECT * FROM requests WHERE invoice_id IS NULL";
	
	private static Connection con;
	
	public RequestDAO() {
		try {
			con = DBManager.getInstance().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Request getById(Long id) {
		Request req = new Request();
		try {
			PreparedStatement pstmt = con.prepareStatement(SQL_FIND_BY_ID);
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				RequestMapper mapper = new RequestMapper();
				req = mapper.mapRow(rs);
				return req;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Request> getAll() {
		List<Request> reqs = new ArrayList<>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL_FIND_ALL);
			RequestMapper mapper = new RequestMapper();
			while (rs.next()) {
				Request req = mapper.mapRow(rs);
				reqs.add(req);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reqs;
	}

	@Override
	public boolean save(Request req) {
		try {
			PreparedStatement pstmt = con.prepareStatement(SQL_INSERT_REQUEST, Statement.RETURN_GENERATED_KEYS);
			int k = 1;
			pstmt.setInt(k++, req.getCapacity());
			if(req.getrClass() != null) {
				pstmt.setString(k++, req.getrClass().getName());
			} else {
				pstmt.setString(k++, null);
			}
			if (req.getRoomId() != null) {
				pstmt.setLong(k++, req.getRoomId());
			} else {
				pstmt.setNull(k++, java.sql.Types.INTEGER);
			}
			pstmt.setDate(k++, Date.valueOf(req.getCreated()));
			pstmt.setDate(k++, Date.valueOf(req.getFrom()));
			pstmt.setDate(k++, Date.valueOf(req.getTo()));
			if(req.getInvoiceId() != null) {
				pstmt.setLong(k++, req.getInvoiceId());
			} else {
				pstmt.setNull(k++, java.sql.Types.INTEGER);
			}
			if (pstmt.executeUpdate() > 0) {
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					req.setId(rs.getLong(1));
					return true;
				}
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Request req) {
		try {
			PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_REQUEST);
			int k = 1;
			pstmt.setInt(k++, req.getCapacity());
			if(req.getrClass() != null) {
				pstmt.setString(k++, req.getrClass().getName());
			} else {
				pstmt.setString(k++, null);
			}
			if (req.getRoomId() != null) {
				pstmt.setLong(k++, req.getRoomId());
			} else {
				pstmt.setNull(k++, java.sql.Types.INTEGER);
			}
			pstmt.setDate(k++, Date.valueOf(req.getCreated()));
			pstmt.setDate(k++, Date.valueOf(req.getFrom()));
			pstmt.setDate(k++, Date.valueOf(req.getTo()));
			if(req.getInvoiceId() != null) {
				pstmt.setLong(k++, req.getInvoiceId());
			} else {
				pstmt.setNull(k++, java.sql.Types.INTEGER);
			}
			pstmt.setLong(k++, req.getId());
			if (pstmt.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Request req) {
		try {
			PreparedStatement pstmt = con.prepareStatement(SQL_DELETE_REQUEST);
			int k = 1;
			pstmt.setLong(k++, req.getId());
			if (pstmt.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private static class RequestMapper implements EntityMapper<Request> {

        @Override
        public Request mapRow(ResultSet rs) {
        	Request req = new Request();
    		int k = 1;
    		try {
    			req.setId(rs.getLong(k++));
    			req.setCapacity(rs.getInt(k++));
    			req.setrClass(RoomClass.fromString(rs.getString(k++)));
    			req.setRoomId(rs.getLong(k++));
    			req.setCreated(rs.getDate(k++).toLocalDate());
    			req.setFrom(rs.getDate(k++).toLocalDate());
    			req.setTo(rs.getDate(k++).toLocalDate());
    			req.setInvoiceId(rs.getLong(k++));
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}		
    		return req;
        }
    }

	public List<Request> getUnpaid() {
		List<Request> reqs = new ArrayList<>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL_FIND_UNPAID);
			RequestMapper mapper = new RequestMapper();
			while (rs.next()) {
				Request req = mapper.mapRow(rs);
				reqs.add(req);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reqs;
	}
	
	public List<Request> getNew() {
		List<Request> reqs = new ArrayList<>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL_FIND_NEW);
			RequestMapper mapper = new RequestMapper();
			while (rs.next()) {
				Request req = mapper.mapRow(rs);
				reqs.add(req);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reqs;
	}
}
