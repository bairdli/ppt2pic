import service.Ppt2PicClient;

/**
 * @ClassName TestPpt2Pic
 * @Description TODO
 * @Author Baird Li
 * @Date 2019-09-03 17:14
 **/
public class TestPpt2Pic {

    public static void main(String[] args){

        Ppt2PicClient ppt2PicClient = new Ppt2PicClient();
        try {
            ppt2PicClient.writePicToLocalByAddress("/Users/lixiaoxiong/work/学习/并发编程/ppt2picTest.pptx","/Users/lixiaoxiong/ppt2pic/");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }



}
