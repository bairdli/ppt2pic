# ppt2pic
- 基于apache.poi Api开发，用于将PPT转换成图片的工具类。

- 1.0.0版本
    - 快速入门：
        ``````
        // 1.创建实例
        Ppt2PicClient ppt2PicClient = Ppt2PicClient.getInstance();
         
        // 2.将目标地址PPT转换图片，并且以字节数组输出流的形式返回
        List<ByteArrayOutputStream> picByteArrayStreamList = ppt2PicClient.gainPicStreamByPptAddress("xxxx xxx");
        
        // 3.将目标链接PPT转换图片，并且以字节数组输出流的形式返回
        List<ByteArrayOutputStream> picByteArrayStreamList = ppt2PicClient.gainPicStreamByPptUrl("xxx xxx");
        
        // 4.将目标地址PPT转换图片，并且将图片存储到指定路径
        ppt2PicClient.writePicToLocalByAddress("xxx xxx","xxx xxx");
        
        // 5.将目标链接PPT转换图片，并且将图片存储到指定路径
        ppt2PicClient.writePicToLocalByUrl("xxx xxx","xxx xxx");
        ``````
    - 异常报错：
        ``````
        PPT_ADDRESS_IS_NOT_EXIST(3000001,"PPT本地路径不存在！"),
        PPT_ADDRESS_IS_NOT_RIGHT(3000002,"PPT信息不正确！"),
        PPT_SUFFIX_IS_NOT_RIGHT(300003,"PPT扩展名不正确！"),
        WRITE_PIC_FAILED(300004,"PPT图片回写失败！"),
        TARGET_ADDRESS_IS_NOT_RIGHT(300005,"目标存储路径不正确！");
        ``````
