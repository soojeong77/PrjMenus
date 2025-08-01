package menus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// Dao: Data Access Object
// CRUD 작업: Create(Insert), Read(Select), Update(Update) Delete(Delete)
public class MenuDao {
   // 데이터 추가
   public void addMenu(String menu_id, String menu_name, int menu_seq) {
      DBConn db = new DBConn();
      Connection conn = db.getConnection();
      String sql = "";
      sql += "INSERT INTO MENUS VALUES (?, ?, ?)";
      PreparedStatement pstmt = null;
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, menu_id);
         pstmt.setString(2, menu_name);
         pstmt.setInt(3, menu_seq);
         
         pstmt.executeUpdate();
         
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         try {
            if(pstmt != null) pstmt.close();
            if(conn != null) conn.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      
      //INSERT INTO MENUS VALUES ('MENU01', 'HTML', 1);
   }
   
   // 데이터 조회(목록)   
   public ArrayList<MenuDto> getMenuList() {
      DBConn db = new DBConn();
      Connection conn = db.getConnection();
      String sql = "";
      sql += "SELECT * FROM MENUS ";
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      
      ArrayList<MenuDto> menuList = new ArrayList<>();
      
      try {
         pstmt = conn.prepareStatement(sql);
         
         rs = pstmt.executeQuery(); //조회
         
         while(rs.next()) {
            String menu_id = rs.getString("menu_id");
            String menu_name = rs.getString("menu_name");
            int menu_seq = rs.getInt("menu_seq");
            
            MenuDto menuDto = new MenuDto();
            menuDto.setMenu_id(menu_id);
            menuDto.setMenu_name(menu_name);
            menuDto.setMenu_seq(menu_seq);
            
            menuList.add(menuDto);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         try {
            if(rs != null) rs.close();
            if(pstmt != null) pstmt.close();
            if(conn != null) conn.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      return menuList;
   }
   
   // 데이터 조회(메뉴 한 개)
   public MenuDto getMenu(String in_menu_id) {
      DBConn db = new DBConn();
      Connection conn = db.getConnection();
      String sql = "";
      sql += "SELECT MENU_ID, MENU_NAME, MENU_SEQ FROM MENUS ";
      sql += " WHERE MENU_ID = ?";
      
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      MenuDto menu = null;
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, in_menu_id);
         
         rs = pstmt.executeQuery();
         if(rs.next()) {
            String menu_id = rs.getString("menu_id");
            String menu_name = rs.getString("menu_name");
            int menu_seq = rs.getInt("menu_seq");
            
            menu = new MenuDto();
            menu.setMenu_id(menu_id);
            menu.setMenu_name(menu_name);
            menu.setMenu_seq(menu_seq);
         } else {
            
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         try {
            if(pstmt != null) pstmt.close();
            if(rs != null) rs.close();
            if(conn != null) conn.close();
         } catch (SQLException e) {
            
         }         
      }
      return menu;
   }
   
   // 데이터 수정
   public void updateMenu(String menu_id, String menu_name, int menu_seq) {
      DBConn db = new DBConn();
      Connection conn = db.getConnection();
      String sql = "";
      sql += "UPDATE MENUS ";
      sql += " SET "
            + " MENU_NAME = ? ,"
            + " MENU_SEQ = ? "
           + " WHERE "
         + " MENU_ID = ? ";
      PreparedStatement pstmt = null;
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, menu_name);
         pstmt.setInt(2, menu_seq);
         pstmt.setString(3, menu_id);
         
         pstmt.executeUpdate();
         
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         try {
            if(pstmt != null) pstmt.close();
            if(conn != null) conn.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
   }
   // 데이터 삭제
}







