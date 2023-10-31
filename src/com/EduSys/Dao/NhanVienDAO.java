/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.EduSys.Dao;

import com.EduSys.entity.NhanVien;
import com.EduSys.uitils.JdbcHelper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lê Minh Khôi
 */
public class NhanVienDAO extends EduSysDao<NhanVien, String>{

    final String INSERT_SQL = "INSERT INTO NHANVIEN (MANV, MATKHAU, HOTEN, VAITRO) VALUES (?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE NHANVIEN SET MATKHAU = ?,HOTEN = ?,VAITRO = ? WHERE MANV = ?";
    final String DELETE_SQL ="DELETE FROM NHANVIEN WHERE MANV = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM NHANVIEN";
    final String SELECT_BY_ID_SQL = "SELECT * FROM NHANVIEN WHERE MANV = ?";
    @Override
    public void insert(NhanVien entity) {
        JdbcHelper.update(INSERT_SQL, entity.getMaNV(),entity.getMatKhau(),entity.getHoTen(),entity.isVaiTro());
    }

    @Override
    public void update(NhanVien entity) {
        JdbcHelper.update(UPDATE_SQL,entity.getMatKhau(),entity.getHoTen(),entity.isVaiTro(), entity.getMaNV());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<NhanVien> selectAll() {
        return selectBysql(SELECT_ALL_SQL);
    }

    @Override
    public NhanVien selectByid(String id) {
        List<NhanVien> list = selectBysql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<NhanVien> selectBysql(String sql, Object... agrs) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, agrs);
            while (rs.next()) {                
                NhanVien entity = new NhanVien();
                entity.setMaNV(rs.getString("MANV"));
                entity.setMatKhau(rs.getString("MATKHAU"));
                entity.setHoTen(rs.getString("HOTEN"));
                entity.setVaiTro(rs.getBoolean("VAITRO"));
                list.add(entity);
            }
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
}
