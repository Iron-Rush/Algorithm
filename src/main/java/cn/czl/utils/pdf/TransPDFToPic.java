package cn.czl.utils.pdf;

import com.sun.javafx.iio.ImageStorage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.junit.jupiter.api.Test;

import java.util.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;


/**
 * @author RedRush
 * @date 2021/11/29 14:44
 * @description:
 */
public class TransPDFToPic {

    private static String ReadPath = "D:\\trans\\pdf\\前女友系列\\";
    private static String OutPath = "D:\\trans\\pdf\\前女友系列\\out\\";
    private static String PassWord = "qq654321";

    @Test
    public void AutoTransPDF(){
        File root = new File(ReadPath);
        if (root.isDirectory()){
            Queue<File> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()){
                File f = queue.poll();
                if (f.isDirectory()){
                    queue.addAll(Arrays.asList(f.listFiles()));
                }else{
                    pdf2Pic(f.getAbsolutePath(), f.getName().replaceAll(".pdf", ""));
                }
            }
        }else {
            pdf2Pic(root.getAbsolutePath(), root.getName().replaceAll(".pdf", ""));
        }
    }

    @Test
    public void TransPDF() {
        try {
            pdf2Pic(ReadPath, "a");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void pdf2Pic(String pdfPath, String fileName) {
        if (!pdfPath.endsWith(".pdf"))  return;
        System.out.println("将多页pdf转化为图片，pdf路径为："+pdfPath);
        try {
            File pdfFile = new File(pdfPath);
            PDDocument pdDocument = PDDocument.load(pdfFile, PassWord);
            int pageCount = pdDocument.getNumberOfPages();
            PDFRenderer pdfRenderer = new PDFRenderer(pdDocument);
            List<String> imagePathList=new ArrayList<>();
            String out = OutPath + fileName;
            File outDir = new File(out);
            if(!outDir.exists()){//如果文件夹不存在
                System.out.println("创建文件夹：" + out);
                outDir.mkdirs();//创建文件夹
            }
            for (int pageIndex=0; pageIndex<pageCount; pageIndex++) {
                String imgPath = out + File.separator + pageIndex+".png";
                try {
//                  BufferedImage image = pdfRenderer.renderImageWithDPI(pageIndex, 105, ImageType.RGB);
                    BufferedImage image = pdfRenderer.renderImageWithDPI(pageIndex, 72F, ImageType.RGB);
                    ImageIO.write(image, "png", new File(imgPath));
                    imagePathList.add(imgPath);
                    System.out.println("生成图片：" + imgPath);
                }catch (Exception e){
                    System.err.println("生成图片异常：" + imgPath);
                    e.printStackTrace();
                }
            }
            pdDocument.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        String filePath = ReadPath;
        pdf2Pic(filePath, "demo_");

    }
}