/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.EduSys.Dao;

import com.EduSys.uitils.JdbcHelper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lê Minh Khôi
 */
public class ThongKeDAO {
    public List<Object[]>getListOfArray(String sql,String[] cols,Object...agrs){
        try {
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = JdbcHelper.query(sql, agrs);
            while (rs.next()) {                
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
    }
    public List<Object[]> getBangDiem(Integer MaKH) {
        String sql = "{CALL SP_BANGDIEM(?)}";
        String col[] = {"MANH", "HOTEN", "DIEM"};
        return getListOfArray(sql, col, MaKH);
    }

    public List<Object[]> getLuongNguoiHoc() {
        String sql = "{CALl SP_LUONGNGUOIHOC}";
        String col[] = {"NAM", "SOLUONG", "DAUTIEN", "CUOICUNG"};
        return getListOfArray(sql, col);
    }

    public List<Object[]> getDoanhThu(Integer Nam) {
        String sql = "{CALL SP_DOANHTHU(?)}";
        String col[] = {"CHUYENDE","SOKH", "SOHV", "DOANHTHU", "CAONHAT", "THAPNHAT", "TRUNGBINH"};

        return getListOfArray(sql, col, Nam);
    }

    public List<Object[]> getDiemChuyenDe() {
        String sql = "{CALL SP_DIEMCHUYENDE}";
        String col[] = {"CHUYENDE", "SOHV", "CAONHAT", "THAPNHAT","TRUNGBINH"};
        return getListOfArray(sql, col);
    }
  
}
 