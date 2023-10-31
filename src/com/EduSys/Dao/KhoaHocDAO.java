/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.EduSys.Dao;

import com.EduSys.entity.ChuyenDe;
import com.EduSys.entity.KhoaHoc;
import com.EduSys.uitils.JdbcHelper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lê Minh Khôi
 */
public class KhoaHocDAO extends EduSysDao<KhoaHoc, Integer>{
    final String INSERT_SQL = "INSERT INTO KHOAHOC (MACD, HOCPHI, THOILUONG, NGAYKG, GHICHU, MANV, NGAYDK) VALUES (?, ?, ?, ?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE KHOAHOC SET MACD = ?,HOCPHI = ?,THOILUONG = ?, NGAYKG = ?, GHICHU = ?, MANV = ?, NGAYDK = ? WHERE MAKH = ?";
    final String DELETE_SQL ="DELETE FROM KHOAHOC WHERE MAKH = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM KHOAHOC";
    final String SELECT_BY_ID_SQL = "SELECT * FROM KHOAHOC WHERE MAKH = ?";

    @Override
    public void insert(KhoaHoc entity) {
        JdbcHelper.update(INSERT_SQL, entity.getMaCD(), entity.getHocPhi(), entity.getThoiLuong(), entity.getNgayKG(), entity.getGhiChu(), entity.getMaNV(), entity.getNgayTao());
    }

    @Override
    public void update(KhoaHoc entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getMaCD(), entity.getHocPhi(), entity.getThoiLuong(), entity.getNgayKG(), entity.getGhiChu(), entity.getMaNV(), entity.getNgayTao(),entity.getMaKH());

    }

    @Override
    public void delete(Integer id) {
        JdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<KhoaHoc> selectAll() {
        return selectBysql(SELECT_ALL_SQL);
    }

    @Override
    public KhoaHoc selectByid(Integer id) {
        List<KhoaHoc> list = selectBysql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }


    @Override
    public List<KhoaHoc> selectBysql(String sql, Object... agrs) {
         List<KhoaHoc> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, agrs);
            while (rs.next()) {                
                KhoaHoc entity = new KhoaHoc();
                entity.setMaKH(rs.getInt("MAKH"));
                entity.setMaCD(rs.getString("MACD"));
                entity.setHocPhi(rs.getDouble("HOCPHI"));
                entity.setThoiLuong(rs.getInt("THOILUONG"));
                entity.setNgayKG(rs.getDate("NGAYKG"));
                entity.setGhiChu(rs.getString("GHICHU"));
                entity.setMaNV(rs.getString("MANV"));
                entity.setNgayTao(rs.getDate("NGAYDK"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public List<KhoaHoc> selectByChuyenDe(String maCD){
        String sql = "SELECT * FROM KHOAHOC WHERE MACD = ?";
        return this.selectBysql(sql, maCD);
    }

    public List<Integer>selectYears(){
        String sql = "SELECT DISTINCT YEAR(NGAYDK) FROM KHOAHOC ORDER BY YEAR(NGAYDK) DESC";
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql);
            while (rs.next()) {                
                list.add(rs.getInt(1));
            }
            rs.getStatement().getConnection().close();
             return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
   
    
}
