package cn.czl.utils.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
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

    private boolean Key = false;
    private boolean Rebase = false;
    private String UserKey = "qq654321";
    private String OwnerKey = "Qq654321";

    private String OutPath = "D:\\trans\\pdf\\";
//    private String OutPath = "O:\\阿里云盘\\pdf\\90、00后\\";
    private String ReadPath = "D:\\Downloads\\pythonDownload\\upload\\";
//    private String ReadPath = "D:\\Downloads\\pythonDownload\\upload\\user\\脚色\\";
//    private String ReadPath = "G:\\小草\\拳交\\";

    @Test
    public void TransDir(){
        String dir = "D:\\Downloads\\Picture\\壁纸\\";
        toPdf(dir, "Test2");
    }
    @Test
    public void autoTransChild(){
        String root = ReadPath;
        String out = OutPath;
        File[] childs = new File(root).listFiles();
        for(File f : childs){
            String curFileName = f.getName();
            if (curFileName.equals("user")){
                File[] userList = new File(root + "user").listFiles();
                for(File userFile : userList){
                    String userFileName = userFile.getName();
                    OutPath = out + curFileName + File.separator + userFileName + File.separator;
                    ReadPath = root + curFileName + File.separator + userFileName + File.separator;
                    autoTrans();
                }
                continue;
            }
            OutPath = out + curFileName + File.separator;
            ReadPath = root + curFileName + File.separator;
//            System.out.println(OutPath);
//            System.out.println(ReadPath);
            autoTrans();
        }
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
                if (file.isDirectory()){
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
            if(!Rebase && new File(pdfPath).exists()){
                System.err.println("跳过生成" + pdfPath);
                return;
            }else   System.out.println("===开始生成：" + pdfPath);
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
            if (Key){
                writer.setEncryption(UserKey.getBytes(), OwnerKey.getBytes(), PdfWriter.ALLOW_SCREENREADERS, PdfWriter.ENCRYPTION_AES_128);
            }
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
                    doc.setPageSize(new Rectangle(img.getWidth(), img.getHeight()));
//                    doc.setPageSize(new Rectangle(1240, 1754));//   1240×1754
//                    doc.setPageSize(new Rectangle(1200, 900));//   1240×1754
                    //5、创建图片对象,加入headerTable中,此处写入图片路径
                    // 实例化图片h
                    image = Image.getInstance(imagePath);
//                    image.scaleAbsolute(1240,1754);//自定义大小
                    // image.scalePercent(50);//缩放百分比 --- 测试不起作用
//                     image.scaleToFit(1200,900);//自定义大小--自适应

//                    PdfPCell acell = new PdfPCell(image,false);
//                    headerTable.addCell(acell);
                    //将主要的表格headerTable加入document文档对象中
//                    doc.add(headerTable);

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
