package service;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.apache.poi.sl.usermodel.Slide;
import org.apache.poi.sl.usermodel.SlideShow;
import org.apache.poi.xslf.usermodel.XMLSlideShow;

import java.io.InputStream;
import java.util.List;

/**
 * @ClassName PptxOperate
 * @Description TODO
 * @Author Baird Li
 * @Date 2019-09-03 15:17
 **/
public class PptxOperate extends ApachePoiHandle {

    public PptxOperate(InputStream inputStream)throws Exception{
        super(new XMLSlideShow(inputStream));
    }

}
