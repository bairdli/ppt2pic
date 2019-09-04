package service;

import enums.EnumPptFrom;
import enums.EnumPptSuffix;
import inter.Ppt2PicOperate;
import org.apache.commons.lang3.tuple.Pair;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

/**
 * @ClassName Ppt2PicClient
 * @Description ppt转pic客户端
 * @Author Baird Li
 * @Date 2019-09-02 18:44
 **/
public class Ppt2PicClient extends Ppt2PicHandle implements Ppt2PicOperate {








    @Override
    public List<ByteArrayOutputStream> gainPicStreamByPptAddress(String pptAddress) throws Exception {

        return handlePpt2Pic(EnumPptFrom.gainAddress(pptAddress));
    }

    @Override
    public List<ByteArrayOutputStream> gainPicStreamByPptUrl(String pptUrl) throws Exception{

        return handlePpt2Pic(EnumPptFrom.gainUrl(pptUrl));
    }

    @Override
    public void writePicToLocalByAddress(String pptAddress, String targetAddress) throws Exception {

        writeStreamToTargetAddress(targetAddress, EnumPptFrom.gainAddress(pptAddress));
    }

    @Override
    public void writePicToLocalByUrl(String pptUrl, String targetAddress) throws Exception {

        writeStreamToTargetAddress(targetAddress, EnumPptFrom.gainUrl(pptUrl));
    }
}
