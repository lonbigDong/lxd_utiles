package org.example;


import net.coobird.thumbnailator.geometry.Positions;
import org.example.imageUtiles.ImageUtils;
import org.example.model.ImageType;

import java.io.File;

/**
 * Hello world!
 *
 */
public class App{
    public static void main( String[] args ){
        String filePath = "C:\\Users\\BigDong\\Pictures\\Saved Pictures\\2.jpg";
        System.out.println("文件格式转换");
        File file = new File(filePath);
        //文件格式转换
//        ImageUtils.changeFileFormat(file, ImageType.PNG, ImageType.Max_OutPut_Quality);

//        ImageUtils.imageAddWatermark(file,0.5, Positions.BOTTOM_CENTER,new File("C:\\Users\\BigDong\\Pictures\\Saved Pictures/水印.png"),0.5f);
//        ImageUtils.modifyPercentage(file,0.5);
        ImageUtils.modifyWidthOrHeight(file,1920,1080);
    }
}
