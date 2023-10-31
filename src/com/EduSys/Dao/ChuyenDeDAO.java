/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.EduSys.Dao;

import com.EduSys.entity.*;
import com.EduSys.uitils.JdbcHelper;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
/**
 *
 * @author Lê Minh Khôi
 */
public class ChuyenDeDAO extends EduSysDao<ChuyenDe, String>{
    final String INSERT_SQL = "INSERT INTO CHUYENDE (MACD, TENCD, HOCPHI, THOILUONG, HINH, MOTA) VALUES (?, ?, ?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE CHUYENDE SET TENCD = ?,HOCPHI = ?,THOILUONG = ?, HINH = ?, MOTA = ? WHERE MACD = ?";
    final String DELETE_SQL ="DELETE FROM CHUYENDE WHERE MACD = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM CHUYENDE";
    final String SELECT_BY_ID_SQL = "SELECT * FROM CHUYENDE WHERE MACD = ?";
    @Override
    public void insert(ChuyenDe entity) {
        JdbcHelper.update(INSERT_SQL, entity.getMaCD(),entity.getTenCD(),entity.getHocPhi(),entity.getThoiLuong(),entity.getHinh(),entity.getMoTa());
    }

    @Override
    public void update(ChuyenDe entity) {
        JdbcHelper.update(UPDATE_SQL,entity.getTenCD(),entity.getHocPhi(),entity.getThoiLuong(),entity.getHinh(),entity.getMoTa(), entity.getMaCD());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<ChuyenDe> selectAll() {
        return selectBysql(SELECT_ALL_SQL);
    }

    @Override
    public ChuyenDe selectByid(String id) {
         List<ChuyenDe> list = selectBysql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<ChuyenDe> selectBysql(String sql, Object... agrs) {
        List<ChuyenDe> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, agrs);
            while (rs.next()) {                
                ChuyenDe entity = new ChuyenDe();
                entity.setMaCD(rs.getString("MACD"));
                entity.setTenCD(rs.getString("TENCD"));
                entity.setHocPhi(rs.getDouble("HOCPHI"));
                entity.setThoiLuong(rs.getInt("THOILUONG"));
                entity.setHinh(rs.getString("HINH"));
                entity.setMoTa(rs.getString("MOTA"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void cboChuyenDeMouseClicked(MouseEvent evt) {
        // fillcomboBoxChuyenDe();
    }
    
}
