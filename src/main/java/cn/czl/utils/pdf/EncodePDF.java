package cn.czl.utils.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.*;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

/**
 * @author RedRush
 * @date 2022/1/26 13:18
 * @description: 为目录下的PDF加密
 */
public class EncodePDF {

    private static String ReadPath = "D:\\Downloads\\pythonDownload\\新建文件夹\\";
//    private static String ReadPath = "D:\\work\\Download\\book\\人体模特摄影艺术（全三册） by 陈光 编著 (z-lib.org).pdf";
    private static String OutPath = "D:\\trans\\pdf\\out\\";
    private static String ReadPassWord = "";
    private static String WritePassWord = "qq654321";

    @Test
    public void TranPDF(){
        Map<String, Integer> map = new HashMap<>();
        File root = new File(ReadPath);
        if (root.isDirectory()){
            Queue<File> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()){
                File f = queue.poll();
                if (f.isDirectory()){
                    queue.addAll(Arrays.asList(f.listFiles()));
                }else{
                    pdfEncode(f.getAbsolutePath(), f.getName());
                }
            }
        }else {
//            pdf2Pic(root.getAbsolutePath(), root.getName().replaceAll(".pdf", ""));
            pdfEncode(root.getAbsolutePath(), root.getName());
        }
    }

    public static void pdfEncode(String pdfPath, String writePdf) {
        if (!pdfPath.endsWith(".pdf"))  return;
        System.out.println("pdf加密，pdf路径为："+pdfPath);
        File outDir = new File(OutPath);
        if(!outDir.exists()){//如果文件夹不存在
            System.out.println("创建文件夹：" + OutPath);
            outDir.mkdirs();//创建文件夹
        }
        String out = OutPath + writePdf;
        try {
            File readFile = new File(pdfPath);
            PDDocument load = PDDocument.load(readFile, ReadPassWord);
            AccessPermission permissions = new AccessPermission();
            permissions.setCanExtractContent(false);
            permissions.setCanModify(false);
            StandardProtectionPolicy p = new StandardProtectionPolicy(WritePassWord , WritePassWord, permissions);
            SecurityHandler sh = new StandardSecurityHandler(p);
            sh.prepareDocumentForEncryption(load);
            PDEncryption encryptionOptions= new PDEncryption();
            encryptionOptions.setSecurityHandler(sh);
            load.setEncryptionDictionary(encryptionOptions);
            load.save(out);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
