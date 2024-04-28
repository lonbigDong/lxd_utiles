package org.example.imageUtiles;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Date;
@Slf4j
public class ImageUtils {


    /**
     * 项目地址
     */
    private static final String baseProjectPath = System.getProperty("user.dir");

    /**
     * 文件处理后输出的文件夹
     */
    private static final String fileOutPutPath = baseProjectPath+"/newFileSource/";

    private static String getOutPutFilaName(File sourceFile){
        String fileName = sourceFile.getName().substring(0,sourceFile.getName().indexOf("."));
        String suffix = DateUtil.convertDate2String(new Date(),DateUtil.PAYMENT_SHOT_DATE_TIME_FORMAT);
        return fileOutPutPath+fileName+"-"+suffix;
    }
    /**
     * 图片格式转换
     * @param file 文件
     * @param fileType 类型
     * @param compression 精度
     */
    public static void changeFileFormat(File file, String fileType,Double compression){
        double scale = 1.0;
        String thumbnailImage =getOutPutFilaName(file);
        log.info("---------图片格式转换开始---------");
        try {
            switch (fileType){
                case "JPG":
                    Thumbnails.of(file).scale(scale)
                            .outputFormat("jpg")
                            .outputQuality(compression)
                            .toFile(thumbnailImage);
                    break;
                case "PNG":
                    Thumbnails.of(file).scale(scale)
                            .outputFormat("png")
                            .outputQuality(compression)
                            .toFile(thumbnailImage);
                    break;
                case "WEBP":
                    Thumbnails.of(file).scale(scale)
                            .imageType(ThumbnailParameter.DEFAULT_IMAGE_TYPE)
                            .outputFormat("webp")
                            .outputQuality(compression)
                            .toFile(thumbnailImage);
                    break;
                case "BMP":
                    Thumbnails.of(file).scale(scale)
                            .outputFormat("bmp")
                            .outputQuality(compression)
                            .toFile(thumbnailImage);
                    break;
                default:
                    Thumbnails.of(file).scale(scale)
                            .imageType(ThumbnailParameter.DEFAULT_IMAGE_TYPE)
                            .outputQuality(compression)
                            .toFile(thumbnailImage);
                    break;
            }

            log.info(String.format("---------图片格式转换成功，输出文件路径：{}---------%s", thumbnailImage));
        } catch (IOException e) {
            log.info("---------图片格式转换失败---------");
            throw new RuntimeException("图片格式转换失败！");
        }finally {
            log.info("---------图片格式转换结束---------");
        }

    }

    /**
     * 图片压缩
     * @param file 图片文件
     * @param scale 宽高百分比  0.0 ~ 1.0
     * @param outPutQuality 图片精度
     */
    public static void zipImageFile(File file,double scale,double outPutQuality){
        log.info("---------图片文件压缩开始---------");
        try {
            String outPutFilaName = getOutPutFilaName(file)+"-压缩";
            Thumbnails.of(file)
                    .scale(scale)
                    .outputQuality(outPutQuality)  //图片输出质量  0.0~1.0  1为最高质量
                    .toFile(outPutFilaName);
            log.info(String.format("---------图片文件压缩完成，输出文件路径：{}---------%s", outPutFilaName));

        } catch (IOException e) {
            log.info("---------文件压缩失败---------");
            throw new RuntimeException("文件压缩失败！");
        }finally {
            log.info("---------文件压缩结束---------");
        }
    }

    /**
     * 添加水印
     * @param file 目标图片文件
     * @param scale 宽高百分比  0.0 ~ 1.0
     * @param var1 水印位置   Positions.TOP_LEFT ，Positions.TOP_CENTER .....
     * @param watermarkFile 水印图片文件
     * @param transparency  水印透明度
     */
    public static void imageAddWatermark(File file, double scale, Positions var1 ,File watermarkFile , float transparency){
        try {
            log.info("---------图片文件添加水印开始---------");
            String outPutFilaName = getOutPutFilaName(file)+"-水印";
            Thumbnails.of(file)
                    .scale(scale)
                    .watermark(var1, ImageIO.read(watermarkFile),transparency)   // 设置需要添加的水印
                    .toFile(outPutFilaName);

            log.info(String.format("---------图片文件添加水印完成，输出文件路径：{}---------%s", outPutFilaName));

        } catch (IOException e) {
            log.info("---------水印添加失败!---------");
            throw new RuntimeException("水印添加失败！");
        }finally {
            log.info("---------水印添加结束---------");
        }
    }


    /**
     * 修改图片宽高比
     * @param file  目标图片文件
     * @param width K
     * @param height G
     */
    public static void modifyWidthOrHeight(File file,   int width, int height){
        try {
            log.info("---------图片文件宽高比修改开始---------");

            String outPutFilaName = getOutPutFilaName(file)+"-缩放";

            Thumbnails.of(file)
                    .size(width,height)
                    .toFile(outPutFilaName);
            log.info(String.format("---------图片文件宽高比修改完成，输出文件路径：{}---------%s", outPutFilaName));

        } catch (IOException e) {
            log.info("---------修改图片失败!---------");
            throw new RuntimeException("修改图片失败！");
        }finally {
            log.info("---------修改图片结束---------");
        }
    }

    /**
     * 百分比缩放
     * @param file 目标图片文件
     * @param scale scale 宽高百分比  0.0 ~ 1.0
     */
    public static void modifyPercentage(File file, double scale){
        try {
            log.info("---------图片百分比缩放开始---------");

            String outPutFilaName = getOutPutFilaName(file)+"-缩放";

            Thumbnails.of(file)
                    .scale(scale)
                    .toFile(outPutFilaName);
            log.info(String.format("---------图片百分比缩放完成，输出文件路径：{}---------%s", outPutFilaName));

        } catch (IOException e) {
            log.info("---------修改图片失败!---------");
            throw new RuntimeException("修改图片失败！");
        }finally {
            log.info("---------修改图片结束---------");
        }
    }



}
