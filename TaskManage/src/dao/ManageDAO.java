package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.DistributionIndex;
import dto.TaskContent;
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
	//配布されている課題一覧
	public static ArrayList<DistributionIndex> distributionIndex(String tID) {
		ArrayList<DistributionIndex> resultList = new ArrayList<DistributionIndex>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/TaskManageDB?useSSL=false",
					"Abe",
					"Dai");

			String sql = "SELECT Task.taskName, Task.deadline, Class.grade, Class.cName "
					+ "FROM Task "
					+ "JOIN Class "
					+ "ON Task.cID = Class.cID "
					+ "AND Task.tID = 00000001 ";

			pstmt = con.prepareStatement(sql);

			int Tid = Integer.parseInt(tID);
			//pstmt.setInt(1, Tid);

			rs = pstmt.executeQuery();

			while(rs.next() == true) {
				String taskName = rs.getString("taskName");
				int grade = rs.getInt("grade");
				int cName = rs.getInt("cName");
				int deadline = rs.getInt("deadline");
				resultList.add(new DistributionIndex(taskName,deadline,grade,cName));
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

	//課題内容詳細
	public static ArrayList<TaskContent> taskContent(String taskID) {
		ArrayList<TaskContent> resultList = new ArrayList<TaskContent>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/TaskManageDB?useSSL=false",
					"Abe",
					"Dai");

			String sql = "SELECT Task.taskName, Teacher.tName, Task.deadline, Task.text "
					+ "FROM Task "
					+ "JOIN Teacher "
					+ "ON Task.tID = Teacher.tID "
					+ "AND Task.taskID = 1";

			pstmt = con.prepareStatement(sql);

			int taskid = Integer.parseInt(taskID);
			//pstmt.setInt(1, taskid);

			rs = pstmt.executeQuery();

			while(rs.next() == true) {
				String taskName = rs.getString("taskName");
				String tName = rs.getString("tName");
				String text = rs.getString("text");
				int deadline = rs.getInt("deadline");
				resultList.add(new TaskContent(taskName,tName,deadline,text));
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

	//課題提出
	public static void submit(String taskID, String cID, String sID, String text) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/TaskManageDB?useSSL=false",
					"Abe",
					"Dai");

			String sql = "INSERT INTO TaskManage VALUES(null,?,?,?,now(),?)";

			pstmt = con.prepareStatement(sql);

			int SID = Integer.parseInt(sID);
			int CID = Integer.parseInt(cID);
			int TaskID = Integer.parseInt(taskID);

			pstmt.setInt(1, SID);
			pstmt.setString(2, cID);
			pstmt.setInt(3, TaskID);
			pstmt.setString(4, text);

			pstmt.executeUpdate();

		} catch(SQLException | ClassNotFoundException e){
			System.out.println("DBアクセスに失敗しました。");
			e.printStackTrace();
		} finally {
			try {
				if( pstmt != null){
					pstmt.close();
				}
			} catch(SQLException e){
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}

			try {
				if( con != null){
					con.close();
				}
			} catch (SQLException e){
			System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
	}

}
