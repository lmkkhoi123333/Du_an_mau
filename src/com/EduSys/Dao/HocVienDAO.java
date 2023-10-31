/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.EduSys.Dao;

import com.EduSys.entity.HocVien;
import com.EduSys.uitils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lê Minh Khôi
 */
public class HocVienDAO extends EduSysDao<HocVien, Integer>{
    final String INSERT_SQL = "INSERT INTO HOCVIEN (MAKH, MANH, DIEM) VALUES (?, ?, ?)";
    final String UPDATE_SQL = "UPDATE HOCVIEN SET MAKH = ?,MANH = ?,DIEM = ? WHERE MAHV = ?";
    final String DELETE_SQL ="DELETE FROM HOCVIEN WHERE MAHV = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM HOCVIEN";
    final String SELECT_BY_ID_SQL = "SELECT * FROM HOCVIEN WHERE MAHV = ?";
    final String SELECT_BY_ID_KH_SQL = "SELECT * FROM HOCVIEN WHERE MAKH = ?";

    @Override
    public void insert(HocVien entity) {
        JdbcHelper.update(INSERT_SQL, entity.getMaKH(),entity.getMaNH(),entity.getDiem());
    }

    @Override
    public void update(HocVien entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getMaKH(),entity.getMaNH(),entity.getDiem());
    }

    @Override
    public void delete(Integer id) {
        JdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<HocVien> selectAll() {
        return selectBysql(SELECT_ALL_SQL);
    }

    @Override
    public HocVien selectByid(Integer id) {
        List<HocVien> list = selectBysql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<HocVien> selectBysql(String sql, Object... agrs) {
        List<HocVien> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, agrs);
            while (rs.next()) {                
                HocVien entity = new HocVien();
                entity.setMaHV(rs.getInt("MAHV"));
                entity.setMaKH(rs.getInt("MAKH"));
                entity.setMaNH(rs.getString("MANH"));
                entity.setDiem(rs.getDouble("DIEM"));
                list.add(entity);   
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
     public List<HocVien> selectByKhoaHoc(int maKH) {
        return selectBysql(SELECT_BY_ID_KH_SQL,maKH);
    }
    
}
