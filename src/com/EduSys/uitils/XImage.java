/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.EduSys.uitils;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.nio.file.*;
import javax.swing.ImageIcon;

/**
 *
 * @author Lê Minh Khôi
 */
public class XImage {
    public static Image getAppIcon(){
        File file = new File("D:\\FPT Polytechnic\\Ky_4\\BLOCK_1\\Bo_Icon\\fpt.png");
        return new ImageIcon(file.getAbsolutePath()).getImage();
    }
    public static boolean save(File src){
        File dst = new File("D:\\FPT Polytechnic\\Ky_4\\BLOCK_1\\Du_an_mau\\EduSys\\src\\com\\EduSys\\logos",src.getName());
        if(!dst.getParentFile().exists()){
            dst.getParentFile().mkdirs(); //tao thu muc
        }
        try {
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(dst.getAbsolutePath());
            Files.copy(from, to,StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public static ImageIcon read(String fileName){
        File path = new File ("D:\\FPT Polytechnic\\Ky_4\\BLOCK_1\\Du_an_mau\\EduSys\\src\\com\\EduSys\\logos",fileName);
        return new ImageIcon(path.getAbsolutePath());
    }
}
