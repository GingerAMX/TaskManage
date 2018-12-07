package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.DistributionIndex;
import dto.Download;
import dto.Submitted;
import dto.TaskContent;
import dto.TaskIndex;
import dto.UnSubmitted;
import dto.Zip;

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
					"user1",
					"pass1");

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
					+ "AND Task.taskID = 2";

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
	//テキストファイル格納場所のパスの取得
	public static ArrayList<Zip> download(String taskName, String grade, String cName) {
		ArrayList<Download> mid = new ArrayList<Download>();
		ArrayList<Zip> resultList = new ArrayList<Zip>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String result = null;


		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/TaskManageDB?useSSL=false",
					"Abe",
					"Dai");

			//クラスIDの取得
			String sql = "SELECT cID FROM class WHERE grade = ? AND cName = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, grade);
			pstmt.setString(2, cName);

			rs = pstmt.executeQuery();

			while(rs.next() == true) {
				int cID = rs.getInt("cID");
				mid.add(new Download(cID,0,null));
			}

			Download key = (Download)mid.get(0);
			int cID = key.getcID();

			//課題IDの取得
			sql = "SELECT taskID FROM Task WHERE taskName = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, taskName);

			rs = pstmt.executeQuery();

			while(rs.next() == true) {
				int taskID = rs.getInt("taskID");
				mid.add(new Download(0,taskID,null));
			}

			key = (Download)mid.get(1);
			int taskID = key.getTaskID();

			//ダウンロードファイルの取得
			sql = "SELECT text FROM TaskManage WHERE cID = ? AND taskID = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, cID);
			pstmt.setInt(2, taskID);

			rs = pstmt.executeQuery();

			while(rs.next() == true) {
				result = rs.getString("text");
				resultList.add(new Zip(result));
			}

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
		return resultList;
	}
	//課題配布
	public static void distribute(String taskName, String content, String grade, String cName, String deadline) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/TaskManageDB?useSSL=false",
					"Abe",
					"Dai");

			String sql = "INSERT INTO Task VALUES(0,?,?,?,?,?)";

			pstmt = con.prepareStatement(sql);

			int deadLine = Integer.parseInt(deadline);
			int Grade = Integer.parseInt(grade);
			int CName = Integer.parseInt(cName);

			pstmt.setString(1, taskName);
			pstmt.setString(2, content);
			pstmt.setInt(3, Grade);
			pstmt.setInt(4, CName);
			pstmt.setInt(5, deadLine);

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
	//提出者の一覧
	public static ArrayList<Submitted> submitted(String taskID) {
		ArrayList<Submitted> resultList = new ArrayList<Submitted>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/TaskManageDB?useSSL=false",
					"Abe",
					"Dai");

			String sql = "SELECT Students.sID, Students.sName, TaskManage.date "
					+ "FROM Students "
					+ "JOIN TaskManage "
					+ "ON Students.sID = TaskManage.sID "
					+ "AND TaskManage.taskID = ?";

			pstmt = con.prepareStatement(sql);

			int TaskID = Integer.parseInt(taskID);
			pstmt.setInt(1, TaskID);

			rs = pstmt.executeQuery();

			while(rs.next() == true) {
				int sID = rs.getInt("sID");
				String sName = rs.getString("sName");
				String mid = rs.getString("date");
				String a =mid.replace("-","");
				int date = Integer.parseInt(a);
				resultList.add(new Submitted(sID,sName,date));
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
	//未提出者の一覧
	public static ArrayList<UnSubmitted> unSubmitted(String cID,String taskID) {
		ArrayList<UnSubmitted> resultList = new ArrayList<UnSubmitted>();
		ArrayList<UnSubmitted> result = new ArrayList<UnSubmitted>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/TaskManageDB?useSSL=false",
					"Abe",
					"Dai");

			//学生の一覧の取得
			String sql = "SELECT DISTINCT Students.sID, Students.sName "
					+ "FROM Students "
					+ "WHERE Students.cID = ?";

			pstmt = con.prepareStatement(sql);

			int CID = Integer.parseInt(cID);
			pstmt.setInt(1, CID);

			rs = pstmt.executeQuery();

			while(rs.next() == true) {
				int sID = rs.getInt("sID");
				String sName = rs.getString("sName");
				resultList.add(new UnSubmitted(sID,sName));
			}

			//提出者の学籍番号の取得
			sql = "SELECT DISTINCT TaskManage.sID "
					+ "FROM TaskManage "
					+ "WHERE TaskManage.taskID = ?";

			pstmt = con.prepareStatement(sql);

			int TaskID = Integer.parseInt(taskID);
			pstmt.setInt(1, TaskID);

			rs = pstmt.executeQuery();

			while(rs.next() == true) {
				int sID = rs.getInt("sID");
				result.add(new UnSubmitted(sID,null));
			}

			//学生一覧から提出者を除去
			int i,j,num1,num2 = 0;
			for(i = 0; i < resultList.size(); i++){
				UnSubmitted list1 = (UnSubmitted)resultList.get(i);
				for(j = 0; j < result.size(); j++){
					UnSubmitted list2 = (UnSubmitted)result.get(j);
					num1 = list1.getsID();
					num2 = list2.getsID();
					if(num1 == num2){
						resultList.remove(i);
					}
				}
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
