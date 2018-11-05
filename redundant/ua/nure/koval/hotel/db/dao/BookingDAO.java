package ua.nure.koval.hotel.db.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.nure.koval.hotel.db.DBManager;
import ua.nure.koval.hotel.entity.Booking;

public class BookingDAO implements DAO<Booking> {
	private static final String SQL_FIND_ALL = "SELECT * FROM bookings";

	@Override
	public Booking getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Booking> getAll() {
		List<Booking> bookings = new ArrayList<>();
		try {
			Connection con = DBManager.getInstance().getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL_FIND_ALL);
			BookingMapper mapper = new BookingMapper();
			while (rs.next()) {
				Booking b = mapper.mapRow(rs);
				bookings.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookings;
	}

	@Override
	public boolean save(Booking t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Booking t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Booking t) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private static class BookingMapper implements EntityMapper<Booking>{

		@Override
		public Booking mapRow(ResultSet rs) {
			Booking b = new Booking();
			int k = 1;
    		try {
    			b.setId(rs.getLong(k++));
    			b.setRoomId(rs.getLong(k++));
    			b.setUserId(rs.getLong(k++));
    			b.setCreated(rs.getDate(k++).toLocalDate());
    			b.setFrom(rs.getDate(k++).toLocalDate());
    			b.setTo(rs.getDate(k++).toLocalDate());
    			b.setPaid(rs.getBoolean(k++));
    			b.setInvoiceId(rs.getLong(k++));
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}		
			return b;
		}
	}
}
