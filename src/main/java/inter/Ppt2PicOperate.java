package inter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * @ClassName Ppt2Pic
 * @Description TODO
 * @Author Baird Li
 * @Date 2019-09-02 18:03
 **/
public interface Ppt2PicOperate {


    /**
     * 1.通过PPT本地存储路径，获取图片字节数组输出流
     *   ※ 通过存储路径获取PPT的文件输入流
     *   ※ 根据扩展名区分ppt和pptx，通过Apache工具类解析PPT
     *   ※ 将每一页PPT转换成图片，通过字节数组输出流输出
     * @param pptAddress PPT本地存储路径
     * @return List<图片字节数组输出流>
     * @throws Exception
     */
    List<ByteArrayOutputStream> gainPicStreamByPptAddress(String pptAddress) throws Exception;

    /**
     * 2.通过PPT网络存储链接，获取图片字节数组输出流
     *   ※ 通过存储链接获取PPT的文件输入流
     *   ※ 根据扩展名区分ppt和pptx，通过Apache工具类解析PPT
     *   ※ 将每一页PPT转换成图片，通过字节数组输出流输出
     * @param pptUrl PPT网络存储链接
     * @return List<图片字节数组输出流>
     * @throws Exception
     */
    List<ByteArrayOutputStream> gainPicStreamByPptUrl(String pptUrl) throws Exception;

    /**
     * 3.将PPT转换成图片，并且存储到目标路径
     *   ※ ......
     *   ※ 将图片字节数组输出流写到对应的目标路径中
     * @param pptAddress PPT本地存储路径
     * @param targetAddress 图片目标存储路径
     * @throws Exception
     */
    void writePicToLocalByAddress(String pptAddress, String targetAddress)throws Exception;

    /**
     * 4.将PPT转换成图片，并且存储到目标路径
     *   ※ ......
     *   ※ 将图片字节数组输出流写到对应的目标路径中
     * @param pptUrl PPT网络存储链接
     * @param targetAddress 图片目标存储路径
     * @throws Exception
     */
    void writePicToLocalByUrl(String pptUrl, String targetAddress)throws Exception;





}
