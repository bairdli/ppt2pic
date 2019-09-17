import service.Ppt2PicClient;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * @ClassName TestPpt2Pic
 * @Description TODO
 * @Author Baird Li
 * @Date 2019-09-03 17:14
 **/
public class TestPpt2Pic {

    public static void main(String[] args){

        Ppt2PicClient ppt2PicClient = Ppt2PicClient.getInstance();
        try {
            //ppt2PicClient.writePicToLocalByAddress("/Users/lixiaoxiong/work/学习/并发编程/ppt2picTest.pptx","/Users/lixiaoxiong/ppt2pic/");


            List<ByteArrayOutputStream> picByteArrayStreamList1 = ppt2PicClient.gainPicStreamByPptAddress("");

            List<ByteArrayOutputStream> picByteArrayStreamList = ppt2PicClient.gainPicStreamByPptUrl("");

            ppt2PicClient.writePicToLocalByAddress("xxx xxx","xxx xxx");

            ppt2PicClient.writePicToLocalByUrl("xxx xxx","xxx xxx");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }



}
