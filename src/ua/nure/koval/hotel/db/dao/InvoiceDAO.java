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
import ua.nure.koval.hotel.entity.Invoice;

public class InvoiceDAO implements DAO<Invoice> {
	private static final String SQL_FIND_BY_ID = "SELECT * FROM invoices WHERE ID=?";
	private static final String SQL_FIND_ALL = "SELECT * FROM invoices";
	private static final String SQL_INSERT_INVOICE = "INSERT INTO invoices VALUES(DEFAULT, ?, ?, ?, ?)";
	private static final String SQL_UPDATE_INVOICE = "UPDATE invoices SET sum=?, created=?, paid=?, request_id=? WHERE ID=?";
	private static final String SQL_DELETE_INVOICE = "DELETE FROM invoices WHERE ID=?";
	private static final String SQL_FIND_BY_USER_ID = "SELECT invoices.* FROM requests, invoices WHERE requests.user_id=? AND invoices.request_id=requests.ID";

	@Override
	public Invoice getById(Long id) {
		Invoice inv = new Invoice();
		try (Connection con = DBManager.getInstance().getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL_FIND_BY_ID)){
			int k = 1;
			pstmt.setLong(k++, id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			InvoiceMapper mapper = new InvoiceMapper();
			inv = mapper.mapRow(rs);
/*			pstmt.close();
			con.close();*/
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inv;
	}

	@Override
	public List<Invoice> getAll() {
		List<Invoice> invoices = new ArrayList<>();
		try(Connection con = DBManager.getInstance().getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL_FIND_ALL)){
			ResultSet rs = pstmt.executeQuery();
			InvoiceMapper mapper = new InvoiceMapper();
			while (rs.next()) {
				Invoice inv = mapper.mapRow(rs);
				invoices.add(inv);
			}
		/*	pstmt.close();
			con.close();*/
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return invoices;
	}

	@Override
	public boolean save(Invoice inv) {
		try (Connection con = DBManager.getInstance().getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL_INSERT_INVOICE, Statement.RETURN_GENERATED_KEYS)){
			int k = 1;
			pstmt.setDouble(k++, inv.getSum());
			pstmt.setDate(k++, Date.valueOf(inv.getCreated()));
			pstmt.setBoolean(k++, inv.isPaid());
			pstmt.setLong(k++, inv.getRequestID());
			if (pstmt.executeUpdate() > 0) {
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					inv.setId(rs.getLong(1));
					return true;
				}
				rs.close();
			}	
		/*	pstmt.close();
			con.close();*/
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Invoice inv) {
		try (Connection con = DBManager.getInstance().getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_INVOICE)){
			int k = 1;
			pstmt.setDouble(k++, inv.getSum());
			pstmt.setDate(k++, Date.valueOf(inv.getCreated()));
			pstmt.setBoolean(k++, inv.isPaid());
			pstmt.setLong(k++, inv.getRequestID());
			pstmt.setLong(k++, inv.getId());
			if (pstmt.executeUpdate() > 0) {
				return true;
			}	
/*			pstmt.close();
			con.close();*/
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Invoice inv) {
		try (Connection con = DBManager.getInstance().getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL_DELETE_INVOICE)){
			int k = 1;
			pstmt.setLong(k++, inv.getId());
			if (pstmt.executeUpdate() > 0) {
				return true;
			}	
/*			pstmt.close();
			con.close();*/
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private static class InvoiceMapper implements EntityMapper<Invoice> {

        @Override
        public Invoice mapRow(ResultSet rs) {
        	Invoice inv = new Invoice();
    		int k = 1;
    		try {
    			inv.setId(rs.getLong(k++));
    			inv.setSum(rs.getDouble(k++));
    			inv.setCreated(rs.getDate(k++).toLocalDate());
    			inv.setPaid(rs.getBoolean(k++));
    			inv.setRequestID(rs.getLong(k++));
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}		
    		return inv;
        }
    }

	public List<Invoice> getByUserId(Long id) {
		List<Invoice> invoices = new ArrayList<>();
		try (Connection con = DBManager.getInstance().getConnection(); PreparedStatement pstmt = con.prepareStatement(SQL_FIND_BY_USER_ID)){
			int k = 1;
			pstmt.setLong(k++, id);
			ResultSet rs = pstmt.executeQuery();
			InvoiceMapper mapper = new InvoiceMapper();
			while (rs.next()) {
				Invoice inv = mapper.mapRow(rs);
				invoices.add(inv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return invoices;
	}
}
