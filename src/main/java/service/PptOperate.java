package service;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.poi.hslf.usermodel.HSLFSlide;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.apache.poi.sl.usermodel.Slide;
import org.apache.poi.sl.usermodel.SlideShow;

import java.awt.*;
import java.io.InputStream;
import java.util.List;

/**
 * @ClassName PptOperate
 * @Description TODO
 * @Author Baird Li
 * @Date 2019-09-03 13:51
 **/
public class PptOperate extends ApachePoiHandle {

    public PptOperate(InputStream inputStream)throws Exception{
        super(new HSLFSlideShow(inputStream));
    }

}
