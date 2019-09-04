package service;

import org.apache.poi.hslf.usermodel.HSLFTextParagraph;
import org.apache.poi.hslf.usermodel.HSLFTextRun;
import org.apache.poi.hslf.usermodel.HSLFTextShape;
import org.apache.poi.sl.draw.DrawFactory;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.Slide;
import org.apache.poi.sl.usermodel.SlideShow;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName ApachePoiHandle
 * @Description Apache处理基类
 * @Author Baird Li
 * @Date 2019-09-03 12:55
 **/
public abstract class ApachePoiHandle {

    private SlideShow slideShow;

    private Integer width;

    private Integer height;

    private List<Slide> slideList;

    public ApachePoiHandle(SlideShow slideShow){
        this.slideShow = slideShow;
        Dimension pageSize = slideShow.getPageSize();
        this.width = (int)(pageSize.getWidth()*1);
        this.height = (int)(pageSize.getHeight()*1);
        this.slideList = slideShow.getSlides();
    }

    public List<ByteArrayOutputStream> gainPptpicStream(){
        List<ByteArrayOutputStream> picStreamList = gainPptPicStream(width, height, slideList);
        return picStreamList;
    }

    public List<ByteArrayOutputStream> gainPptPicStream(Integer width, Integer height, List<Slide> slideList){
        List<ByteArrayOutputStream> picStreamList = new ArrayList<>();
        for(Slide slide : slideList){
            handlePptPageDetail(slide.getShapes());
            ByteArrayOutputStream picStream = gainBufferdImageStream(width, height, slide);
            picStreamList.add(picStream);
        }
        return picStreamList;
    }

    public void handlePptPageDetail(List<Shape> shapeList){
        for(Shape shape : shapeList){
            if ( shape instanceof XSLFTextShape){
                XSLFTextShape txtshape = (XSLFTextShape)shape ;
                for ( XSLFTextParagraph textPara : txtshape.getTextParagraphs() ){
                    List<XSLFTextRun> textRunList = textPara.getTextRuns();
                    for(XSLFTextRun textRun: textRunList) {
                        textRun.setFontFamily("宋体");
                    }
                }
            }else if(shape instanceof HSLFTextShape){
                HSLFTextShape hslfTextShape = (HSLFTextShape) shape;
                for(HSLFTextParagraph textParagraph : hslfTextShape.getTextParagraphs()){
                    List<HSLFTextRun> textRuns = textParagraph.getTextRuns();
                    for(HSLFTextRun hslfTextRun : textRuns){
                        hslfTextRun.setFontFamily("宋体");
                    }
                }
            }
        }
    }

    public ByteArrayOutputStream gainBufferdImageStream(Integer width, Integer height, Slide slide){
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
        slide.draw(graphics);
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        try {
            ImageIO.write(img, "png", bs);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            graphics.dispose();
            img.flush();
        }
        return bs;
    }


}
