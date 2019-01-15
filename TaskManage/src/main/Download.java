package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ManageDAO;
import dto.Zip;

/**
 * Servlet implementation class Download
 */
@WebServlet("/Download")
public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Download() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String flg = request.getParameter("flg");

		request.setAttribute("flg", flg);

		String view = "/WEB-INF/view/Download.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String taskName = request.getParameter("taskName");
		String grade = request.getParameter("grade");
		String cName = request.getParameter("class");
		String flg = request.getParameter("flg");

		//圧縮するファイルのパスを取得
		ArrayList<Zip> path = ManageDAO.download(taskName,grade,cName);

		File result = archive(path);
		String zipPath = result.toString();

		request.setAttribute("zipPath", zipPath);
		request.setAttribute("flg", flg);

		String view = "/WEB-INF/view/Download.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

	//Zipファイルの作成
	public File archive(ArrayList<Zip> path){
        // 作成する ZipFile
        FileOutputStream fileOut = null;
        File parent = null;

        try{
            // 出力先ディレクトリを作っておく
        	Zip mid = (Zip)path.get(0);
        	String Path = mid.getPath();

        	File zipFile = new File(Path);
            parent = zipFile.getParentFile();
            System.out.println(parent);				//ディレクトリ
            if( parent != null ){
                parent.mkdirs();
            }

            // zipファイルを新規作成する
            fileOut = new FileOutputStream( zipFile );
            ZipOutputStream zipOut = new ZipOutputStream( fileOut );

            ZipEntry entry     = null;
            byte[]  textBytes = null;

            // txtを書き出す
            for(int i = 0; i < path.size(); i++) {
            	mid = (Zip)path.get(i);
            	String filePath = mid.toString();

            	entry = new ZipEntry(filePath);
            	zipOut.putNextEntry( entry );
            	textBytes = "This is test1".getBytes();
            	zipOut.write( textBytes );
            	zipOut.closeEntry();
            }

            // 終了
            zipOut.close();
            fileOut = null;

        }catch( Exception e){
            e.printStackTrace();

        } finally {
            //----------------------------
            // 後始末
            //----------------------------
            if( fileOut != null ){
                try{
                    fileOut.close();
                }catch( Exception e){}
            }
        }
		return parent;
    }

}
