package service;

import enums.EnumPptFrom;
import inter.Ppt2PicOperate;
import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * @ClassName Ppt2PicClient
 * @Description ppt转pic客户端
 * @Author Baird Li
 * @Date 2019-09-02 18:44
 **/
public class Ppt2PicClient extends Ppt2PicHandle implements Ppt2PicOperate {


    private static Ppt2PicClient ppt2PicClient = new Ppt2PicClient();


    private Ppt2PicClient(){

    }

    public static Ppt2PicClient getInstance(){
        return ppt2PicClient;
    }

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
