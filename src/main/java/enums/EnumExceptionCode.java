package enums;

/**
 * @ClassName EnumExceptionCode
 * @Description TODO
 * @Author Baird Li
 * @Date 2019-09-02 17:30
 **/
public enum EnumExceptionCode {


    PPT_ADDRESS_IS_NOT_EXIST(3000001,"PPT本地路径不存在！"),
    PPT_ADDRESS_IS_NOT_RIGHT(3000002,"PPT信息不正确！"),
    PPT_SUFFIX_IS_NOT_RIGHT(300003,"PPT扩展名不正确！"),
    WRITE_PIC_FAILED(300004,"PPT图片回写失败！"),
    TARGET_ADDRESS_IS_NOT_RIGHT(300005,"目标存储路径不正确！");

    private Integer code;

    private String desc;

    private EnumExceptionCode(Integer code, String desc){
        this.code = code;
        this.desc = desc;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
