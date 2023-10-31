/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.EduSys.Dao;

import com.EduSys.entity.KhoaHoc;
import com.EduSys.entity.NguoiHoc;
import com.EduSys.uitils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lê Minh Khôi
 */
public class NguoiHocDAO extends EduSysDao<NguoiHoc, String>{
    final String INSERT_SQL = "INSERT INTO NGUOIHOC (MANH, HOTEN, GIOITINH, NGAYSINH, DIENTHOAI, EMAIL, GHICHU, MANV, NGAYDK) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE NGUOIHOC SET HOTEN = ?,GIOITINH = ?,NGAYSINH = ?, DIENTHOAI = ?, EMAIL = ?, GHICHU =?, MANV = ?, NGAYDK = ? WHERE MANH = ?";
    final String DELETE_SQL ="DELETE FROM NGUOIHOC WHERE MANH = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM NGUOIHOC";
    final String SELECT_BY_ID_SQL = "SELECT * FROM NGUOIHOC WHERE MANH = ?";
    @Override
    public void insert(NguoiHoc entity) {
        JdbcHelper.update(INSERT_SQL, entity.getMaNH(),entity.getHoTen(),entity.isGioiTinh(),entity.getNgaySinh(),
                entity.getDienThoai(),entity.getEmail(),entity.getGhiChu(),entity.getMaNV(),entity.getNgayDK());
    }

    @Override
    public void update(NguoiHoc entity) {
        JdbcHelper.update(UPDATE_SQL,entity.getHoTen(),entity.isGioiTinh(),entity.getNgaySinh(),
                entity.getDienThoai(),entity.getEmail(),entity.getGhiChu(),entity.getMaNV(),entity.getNgayDK(), entity.getMaNH());      
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<NguoiHoc> selectAll() {
        return selectBysql(SELECT_ALL_SQL);
    }

    @Override
    public NguoiHoc selectByid(String id) {
          List<NguoiHoc> list = selectBysql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<NguoiHoc> selectBysql(String sql, Object... agrs) {
        List<NguoiHoc> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, agrs);
            while (rs.next()) {                
                NguoiHoc entity = new NguoiHoc();
                entity.setMaNH(rs.getString("MANH"));
                entity.setHoTen(rs.getString("HOTEN"));
                entity.setGioiTinh(rs.getBoolean("GIOITINH"));
                entity.setNgaySinh(rs.getDate("NGAYSINH"));
                entity.setDienThoai(rs.getString("DIENTHOAI"));
                entity.setEmail(rs.getString("EMAIL"));
                entity.setGhiChu(rs.getString("GHICHU"));
                entity.setMaNV(rs.getString("MANV"));
                entity.setNgayDK(rs.getDate("NGAYDK"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public List<NguoiHoc> selectNotlnCourse(int maKH,String keyword){
        String sql = "SELECT * FROM NGUOIHOC WHERE HOTEN LIKE ? AND MANH NOT IN (SELECT MANH FROM HOCVIEN WHERE MAKH = ?)";
        return this.selectBysql(sql, "%"+keyword+"%",maKH);
    }
    
}
