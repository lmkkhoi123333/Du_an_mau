/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.EduSys.Dao;

import java.util.List;

/**
 *
 * @author Lê Minh Khôi
 */
public abstract class EduSysDao<EntityType,KeyType> {
    public abstract void insert(EntityType entity);
    public abstract void update(EntityType entity);
    public abstract void delete(KeyType id);
    public abstract List<EntityType> selectAll();
    public abstract EntityType selectByid(KeyType id);
    public abstract List<EntityType> selectBysql(String sql,Object...agrs);
    
    
}
