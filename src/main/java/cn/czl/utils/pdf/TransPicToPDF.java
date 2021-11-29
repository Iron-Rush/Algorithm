package cn.czl.utils.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.util.*;


/**
 * @author RedRush
 * @date 2021/11/26 22:31
 * @description:    将图片存入pdf中
 */
public class TransPicToPDF {

    private String UserKey = "qq654321";
    private String OwnerKey = "Qq654321";

    private String OutPath = "D:\\trans\\pdf\\前女友\\";
    private String ReadPath = "D:\\Downloads\\pythonDownload\\upload\\写真\\";
//    private String ReadPath = "G:\\小草\\紧\\";

    @Test
    public void TransDir(){
        String dir = "D:\\trans\\前女友系列\\";
        toPdf(dir, "前女友系列");
    }
    @Test
    public void autoTrans(){
        File root = new File(ReadPath);
        if (root.isDirectory()){
            File[] all = root.listFiles();
            Deque<File> queue = new LinkedList<>();
            queue.addAll(Arrays.asList(all));
            while (!queue.isEmpty()){
                File file = queue.poll();
                if (file.isDirectory() && !file.getName().contains("动图")){
                    File[] childs = file.listFiles();
                    for(File c : childs){
                        if (c.isFile()){
                            toPdf(file.getAbsolutePath(), file.getName());
                            break;
                        }else{
                            queue.add(file);
                        }
                    }
                }else{
//                    File parentFile = file.getParentFile();
//                    String name = parentFile.getName();
//                    String source = parentFile.getAbsolutePath();
//                    System.out.println(name);
//                    System.out.println(source);
                }
            }
        }
    }

    public void toPdf(String imageFolderPath, String fileName) {
        try {
            // 图片文件夹地址
//            String imageFolderPath = "D:\\Downloads\\pythonDownload\\小草分类图\\user\\";
            // 图片地址
            String imagePath;
            // PDF文件保存地址
//            String pdfPath = "d:/test.pdf";
            String pdfPath = OutPath + fileName + ".pdf";
            File rootDir = new File(OutPath);
            if(!rootDir.exists()){//如果文件夹不存在
                rootDir.mkdir();//创建文件夹
            }
            // 输入流
            FileOutputStream fos = new FileOutputStream(pdfPath);
            // 创建文档
            Document doc = new Document(null, 0, 0, 0, 0);
            //doc.open();
            // 写入PDF文档
            PdfWriter writer = PdfWriter.getInstance(doc, fos);
            writer.setEncryption(UserKey.getBytes(), OwnerKey.getBytes(), PdfWriter.ALLOW_SCREENREADERS, PdfWriter.ENCRYPTION_AES_128);
            // 读取图片流
            BufferedImage img;
            // 实例化图片
            Image image;
            // 获取图片文件夹对象
            File file = new File(imageFolderPath);
            File[] files = file.listFiles();
            // 循环获取图片文件夹内的图片
            for (File file1 : files) {
                if (file1.getName().endsWith(".png")
                        || file1.getName().endsWith(".jpg")
                        || file1.getName().endsWith(".gif")
                        || file1.getName().endsWith(".jpeg")
                        || file1.getName().endsWith(".tif")) {
                    imagePath = imageFolderPath + File.separator + file1.getName();
                    //(file1.getName());
                    // 读取图片流
                    img = ImageIO.read(new File(imagePath));
//                    System.err.println("宽度" + img.getWidth());
//                    System.err.println("高度" + img.getHeight());
                    System.out.println(imagePath);
                    System.err.println("W:" + img.getWidth() + ",H:" + img.getHeight());
                    // 根据图片大小设置文档大小
                    doc.setPageSize(new Rectangle(img.getWidth(), img
                            .getHeight()));
                    // 实例化图片h
                    image = Image.getInstance(imagePath);
                    // 添加图片到文档
                    doc.newPage();
                    doc.open();
                    doc.add(image);
                }
            }
            // 关闭文档
            doc.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BadElementException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    private void setPDFPassWord(String path){
        if (!path.endsWith(".pdf"))     return;
        try {
            //实现A4纸页面 并且横向显示（不设置则为纵向）
            Document document = new Document(PageSize.A4.rotate());
            PdfWriter pdfWriter = PdfWriter.getInstance(document,new FileOutputStream(path));
            // 设置用户密码, 所有者密码,用户权限,所有者权限
//            pdfWriter.setEncryption("userpassword".getBytes(), "ownerPassword".getBytes(),  PdfWriter.ALLOW_COPY, PdfWriter.ENCRYPTION_AES_128);
            pdfWriter.setEncryption(UserKey.getBytes(), OwnerKey.getBytes(), PdfWriter.ALLOW_SCREENREADERS, PdfWriter.ENCRYPTION_AES_128);
//            // 打开文档
//            document.open();
//            // 创建第一页（如果只有一页的话，这一步可以省略）
//            document.newPage();
//            // 加入文档内容
//            document.add(new Paragraph("my first pdf demo"));
            // 关闭文档
            pdfWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    */
    @Test
    public void setPwd(){
        File root = new File("D:\\trans\\pdf\\前女友系列\\Java基础面试题.pdf");
        if (root.isDirectory()){
            Queue<File> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()){
                File f = queue.poll();
                if (f.isDirectory()){
                    queue.addAll(Arrays.asList(f.listFiles()));
                }else{
//                    setPDFPassWord(f.getAbsolutePath());
                    String path = f.getAbsolutePath();
                    if (!path.endsWith(".pdf"))     continue;
                    System.out.println(path);
                }
            }
        }else {
//            setPDFPassWord(root.getAbsolutePath());
        }
    }


}
