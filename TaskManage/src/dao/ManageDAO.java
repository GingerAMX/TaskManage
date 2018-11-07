package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.TaskIndex;

public class ManageDAO {
	//配布されている課題一覧
	public static ArrayList<TaskIndex> taskIndex(String classs) {
		ArrayList<TaskIndex> resultList = new ArrayList<TaskIndex>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/TaskManageDB?useSSL=false",
					"Abe",
					"Dai");

			String sql = "SELECT Task.taskName, Task.deadline, Teacher.tName "
					+ "FROM Task "
					+ "JOIN Teacher "
					+ "ON Task.tID = Teacher.tID "
					+ "AND Task.cID = 1 ";

			pstmt = con.prepareStatement(sql);

			int Class = Integer.parseInt(classs);
			//pstmt.setInt(1, Class);

			rs = pstmt.executeQuery();

			while(rs.next() == true) {
				String taskName = rs.getString("taskName");
				String tName = rs.getString("tName");
				int deadline = rs.getInt("deadline");
				resultList.add(new TaskIndex(taskName,tName,deadline));
			}

		} catch (ClassNotFoundException e) {
			System.out.println("JDBCドライバが見つかりません。");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				System.out.println("DBアクセス時にエラーが発生しました。");
				e.printStackTrace();
			}
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				System.out.println("DBアクセス時にエラーが発生しました。");
				e.printStackTrace();
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				System.out.println("DBアクセス時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
		return resultList;
	}


}
