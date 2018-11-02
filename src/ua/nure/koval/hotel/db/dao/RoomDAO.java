package ua.nure.koval.hotel.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.nure.koval.hotel.db.DBManager;
import ua.nure.koval.hotel.entity.Room;
import ua.nure.koval.hotel.entity.enums.RoomClass;
import ua.nure.koval.hotel.entity.enums.Status;

public class RoomDAO implements DAO<Room> {
	private static final String SQL_FIND_ALL_ROOMS = "SELECT * FROM rooms";
	private static final String SQL_INSERT_ROOM = "INSERT INTO rooms (capacity, cost, class, status) VALUES(?,?,?,?)";

	@Override
	public Room getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Room> getAll() {
		List<Room> rooms = new ArrayList<>();
		try {
			Connection con = DBManager.getInstance().getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL_FIND_ALL_ROOMS);
			RoomMapper mapper = new RoomMapper();
			while (rs.next()) {
				Room room = mapper.mapRow(rs);
				rooms.add(room);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rooms;
	}

	@Override
	public boolean save(Room room) {
		try {
			Connection con = DBManager.getInstance().getConnection();
			PreparedStatement pstmt = con.prepareStatement(SQL_INSERT_ROOM, Statement.RETURN_GENERATED_KEYS);
			int k = 1;
			pstmt.setInt(k++, room.getCapacity());
			pstmt.setDouble(k++, room.getCost());
			pstmt.setString(k++, room.getrClass().getName());
			pstmt.setString(k++, room.getStatus().getName());
			if (pstmt.executeUpdate() > 0) {
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					room.setId(rs.getLong(1));
					return true;
				}
			}			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Room room) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Room room) {
		// TODO Auto-generated method stub
		return false;
	}
	
	static class RoomMapper implements EntityMapper<Room>{

		@Override
		public Room mapRow(ResultSet rs) {
			Room room = new Room();
			int k = 1;
			try {
				room.setId(rs.getLong(k++));
				room.setCapacity(rs.getInt(k++));
				room.setCost(rs.getDouble(k++));
				room.setrClass(RoomClass.fromString(rs.getString(k++)));
				room.setStatus(Status.fromString(rs.getString(k++)));
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return room;
		}
		
	}

}
