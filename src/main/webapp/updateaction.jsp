<%@page import="menus.MenuDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
   //메뉴 수정
   //수정할 정보를 넘겨 받음
   String menu_id = request.getParameter("menu_id");
   String menu_name = request.getParameter("menu_name");
   String in_menu_seq = request.getParameter("menu_seq");

   int menu_seq = Integer.parseInt(in_menu_seq);
   
   //DB 정보 수정
   MenuDao menuDao = new MenuDao(); //import ctrl+shift+m
   menuDao.updateMenu(menu_id, menu_name, menu_seq);
   
   //menulist.jsp으로 돌아감
   String loc = "menulist.jsp";
   response.sendRedirect(loc);
   
%>