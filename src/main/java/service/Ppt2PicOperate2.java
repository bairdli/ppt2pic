package service;

import enums.EnumPptSuffix;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.poi.hslf.usermodel.*;
import org.apache.poi.sl.draw.DrawFactory;
import org.apache.poi.xslf.usermodel.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Ppt2picOperate
 * @Description TODO
 * @Author LiXiaoxiong
 * @Date 2019-07-11 11:55
 **/
public class Ppt2PicOperate2 {


    public Pair<InputStream, EnumPptSuffix> checkFileIsPpt(String pptAddress)throws Exception{

        File file = new File(pptAddress);
        if(!file.exists()){

        }

        String fileExtension = FileUtils.getFileExtension(pptAddress);

        EnumPptSuffix enumPptSuffix = EnumPptSuffix.gainEnumBySuffix(fileExtension);

        FileInputStream fileInputStream = new FileInputStream(file);

        return Pair.of((InputStream) fileInputStream, enumPptSuffix);
    }



    public List<ByteArrayOutputStream> handlePpt(InputStream inputStream, EnumPptSuffix pptSuffix)throws Exception{
        List<ByteArrayOutputStream> pptPicStreamList = new ArrayList<>();
        switch (pptSuffix){
            case PPT:
                PptOperate pptOperate = new PptOperate(inputStream);
                pptPicStreamList = pptOperate.gainPptpicStream();
                break;
            case PPTX:
                PptxOperate pptxOperate = new PptxOperate(inputStream);
                pptPicStreamList = pptxOperate.gainPptpicStream();
                break;
            default:

        }

        return null;
    }


    public String ppt2png(String  pptUrl, String pptType)throws Exception{
        String pngUrl = null;
        URL url = new URL(pptUrl);
        URLConnection openConnection = url.openConnection();
        InputStream inputStream = openConnection.getInputStream();

        if(pptType.equalsIgnoreCase("ppt")){
            HSLFSlideShow ppt = new HSLFSlideShow(inputStream);
            Dimension pgsize = ppt.getPageSize();
            int width = (int) (pgsize.width * 1);
            int height = (int) (pgsize.height * 1);
            List<HSLFSlide> slides = ppt.getSlides();
            if(!slides.isEmpty()){
                HSLFSlide hslfSlide = slides.get(0);
                List<HSLFShape> shapes = hslfSlide.getShapes();
                for(HSLFShape hslfShape : shapes){
                    if(hslfShape instanceof HSLFTextShape){
                        HSLFTextShape hslfTextShape = (HSLFTextShape) hslfShape;
                        for(HSLFTextParagraph textParagraph : hslfTextShape.getTextParagraphs()){
                            List<HSLFTextRun> textRuns = textParagraph.getTextRuns();
                            for(HSLFTextRun hslfTextRun : textRuns){
                                hslfTextRun.setFontFamily("宋体");
                            }
                        }
                    }
                }
                BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                Graphics2D graphics = img.createGraphics();
                DrawFactory.getInstance(graphics).fixFonts(graphics);
                // default rendering options
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                graphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
                graphics.scale(1, 1);

                // draw stuff
                hslfSlide.draw(graphics);
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                ImageIO.write(img, "png", bs);
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bs.toByteArray());
                byteArrayInputStream.mark(Integer.MAX_VALUE);
                //String fileMd5 = Encodes.encodeHex(Digests.md5(byteArrayInputStream));
                byteArrayInputStream.reset();
                //pngUrl = aliyunOssService.upload("pptHomePage/"+fileMd5+".png", byteArrayInputStream);
                //System.out.println(ossUrl);
                graphics.dispose();
                img.flush();
                ppt.close();
            }
        }else if(pptType.equalsIgnoreCase("pptx")){
            XMLSlideShow ppt = new XMLSlideShow(inputStream);
            List<XSLFSlide> slides = ppt.getSlides();
            Dimension pgsize = ppt.getPageSize();
            int width = (int) (pgsize.width * 1);
            int height = (int) (pgsize.height * 1);
            if(!slides.isEmpty()){
                XSLFSlide xslfSlide = slides.get(0);

                List<XSLFShape> shapes = xslfSlide.getShapes();
                for(XSLFShape shape : shapes){
                    if ( shape instanceof XSLFTextShape){
                        XSLFTextShape txtshape = (XSLFTextShape)shape ;
                        for ( XSLFTextParagraph textPara : txtshape.getTextParagraphs() ){
                            List<XSLFTextRun> textRunList = textPara.getTextRuns();
                            for(XSLFTextRun textRun: textRunList) {
                                textRun.setFontFamily("宋体");
                            }
                        }
                    }
                }
                BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                Graphics2D graphics = img.createGraphics();
                DrawFactory.getInstance(graphics).fixFonts(graphics);
                // default rendering options
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                graphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
                graphics.scale(1, 1);

                // draw stuff
                xslfSlide.draw(graphics);
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                ImageIO.write(img, "png", bs);
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bs.toByteArray());
                byteArrayInputStream.mark(Integer.MAX_VALUE);
                //String fileMd5 = Encodes.encodeHex(Digests.md5(byteArrayInputStream));
                byteArrayInputStream.reset();
                //pngUrl = aliyunOssService.upload("pptHomePage/"+fileMd5+".png", byteArrayInputStream);
                //System.out.println(ossUrl);
                graphics.dispose();
                img.flush();
                ppt.close();
            }
        }
        inputStream.close();
        return pngUrl;
    }





}
