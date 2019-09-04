package service;

import enums.EnumExceptionCode;
import enums.EnumPptFrom;
import enums.EnumPptSuffix;
import exception.Ppt2PicException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName Ppt2PicHandle
 * @Description TODO
 * @Author Baird Li
 * @Date 2019-09-03 15:32
 **/
public class Ppt2PicHandle {


    private String fontStyle;

    private String picFont;

    /**
     * 1.将PPT转换成Pic
     *   ※ 区分PPT来源：本地路径、网络链接，获取PPT输入流
     *   ※ 读取PPT信息，将PPT转换成图片输出流
     * @param enumPptFrom ppt来源：本地路径、网络链接
     * @return List<字节数组输出流>
     * @throws Exception
     */
    public List<ByteArrayOutputStream> handlePpt2Pic(EnumPptFrom enumPptFrom)throws Exception{

        Pair<InputStream, EnumPptSuffix> pptDetailPair = null;
        switch (enumPptFrom){
            case ADDRESS:
                String pptAddress = enumPptFrom.getPptFromDetail();
                pptDetailPair = checkAddressIsPpt(pptAddress);
                break;
            case URL:
                String pptUrl = enumPptFrom.getPptFromDetail();
                pptDetailPair = checkUrlIsPpt(pptUrl);
                break;
                default:
        }

        List<ByteArrayOutputStream> picStreamList = handlePpt2Pic(pptDetailPair);

        return picStreamList;
    }

    /**
     * 1.1.1 校验本地路径是否是PPT
     * @param pptAddress PPT本地路径
     * @return Pair<PPT输入流，PPT扩展名>
     * @throws Exception
     */
    public Pair<InputStream, EnumPptSuffix> checkAddressIsPpt(String pptAddress)throws Exception{

        File file = new File(pptAddress);
        if(!file.exists()){
            throw new Ppt2PicException(EnumExceptionCode.PPT_ADDRESS_IS_NOT_EXIST);
        }

        EnumPptSuffix enumPptSuffix = EnumPptSuffix.gainEnumBySuffix(getExtension(pptAddress));

        return Pair.of((InputStream) new FileInputStream(file), enumPptSuffix);
    }

    /**
     * 1.1.2 校验网络链接是否是PPT
     * @param pptUrl PPT网络链接
     * @return Pair<PPT输入流，PPT扩展名>
     * @throws Exception
     */
    public Pair<InputStream, EnumPptSuffix> checkUrlIsPpt(String pptUrl)throws Exception{

        EnumPptSuffix enumPptSuffix = EnumPptSuffix.gainEnumBySuffix(getExtension(pptUrl));

        URL url = new URL(pptUrl);
        URLConnection openConnection = url.openConnection();
        InputStream inputStream = openConnection.getInputStream();

        return Pair.of(inputStream, enumPptSuffix);
    }

    /**
     * 1.2 PPT输入流处理成Pic输出流
     *   ※ 区分PPT版本：.ppt、.pptx
     *   ※ 通过Apache工具类处理PPT，转换成Pic输出流
     * @param pptDetailPair Pair<PPT输入流、PPT扩展名>
     * @return List<字节数组输出流>
     * @throws Exception
     */
    public List<ByteArrayOutputStream> handlePpt2Pic(Pair<InputStream, EnumPptSuffix> pptDetailPair)throws Exception{

        InputStream inputStream = pptDetailPair.getLeft();
        EnumPptSuffix pptSuffix = pptDetailPair.getRight();

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

        return pptPicStreamList;
    }

    /**
     * 2.将PPT转换成Pic字节数组输出流，并且将图片写到对应地址中
     *   ※ 校验目标地址是否正确
     *   ※ 将PPT转换成Pic输出流
     *   ※ 将图片写到对应地址中
     * @param targetAddress 目标存储路径
     * @param enumPptFrom ppt来源：本地路径、网络链接
     * @throws Exception
     */
    public void writeStreamToTargetAddress(String targetAddress, EnumPptFrom enumPptFrom)throws Exception{

        checkTargetAddress(targetAddress);

        List<ByteArrayOutputStream> picStreamList = handlePpt2Pic(enumPptFrom);

        for(ByteArrayOutputStream picStream : picStreamList){
            BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(picStream.toByteArray()));
            try{
                File picFile = new File(targetAddress + System.currentTimeMillis() + ".png");
                ImageIO.write(bufferedImage, "png", picFile);
            }catch (Exception e){
                throw new Ppt2PicException(EnumExceptionCode.WRITE_PIC_FAILED);
            }finally {
                bufferedImage.flush();
            }
        }
    }

    /**
     * 2.1 校验目标路径是否正确，是否存在
     * @param targetAddress 目标路径
     */
    public void checkTargetAddress(String targetAddress){
        if (!targetAddress.endsWith(File.separator)) {
            targetAddress = targetAddress + File.separator;
        }
        File descDir = new File(targetAddress);
        if (!descDir.exists()) {
            boolean mkdirs = descDir.mkdirs();
            if(mkdirs){
                throw new Ppt2PicException(EnumExceptionCode.TARGET_ADDRESS_IS_NOT_RIGHT);
            }
        }
    }

    /**
     * basic: 获取本地路径、网络链接的文件扩展名
     * @param fileName
     * @return
     */
    public String getExtension(String fileName) {
        if ((fileName == null) || (fileName.lastIndexOf(".") == -1) || (fileName.lastIndexOf(".") == fileName.length() - 1)) {
            //return null;
            throw new Ppt2PicException(EnumExceptionCode.PPT_ADDRESS_IS_NOT_RIGHT);
        }
        return StringUtils.lowerCase(fileName.substring(fileName.lastIndexOf(".") + 1));
    }






}
