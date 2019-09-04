package enums;

import exception.Ppt2PicException;

/**
 * @ClassName EnumPptFrom
 * @Description TODO
 * @Author Baird Li
 * @Date 2019-09-03 20:17
 **/
public enum EnumPptSuffix {

    PPT("ppt",""),
    PPTX("pptx","");

    private String code;

    private String desc;

    private EnumPptSuffix(String code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public static Boolean checkIsPpt(String fileSuffix){
        if(PPT.code.equalsIgnoreCase(fileSuffix) || PPTX.code.equalsIgnoreCase(fileSuffix)){
            return true;
        }
        return false;
    }

    public static EnumPptSuffix gainEnumBySuffix(String fileSuffix){
        if(PPT.code.equalsIgnoreCase(fileSuffix)){
            return PPT;
        }else if(PPTX.code.equalsIgnoreCase(fileSuffix)){
            return PPTX;
        }else {
            throw new Ppt2PicException(EnumExceptionCode.PPT_SUFFIX_IS_NOT_RIGHT);
        }

    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
