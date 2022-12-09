package ramesh.dto;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class CodeNamePair{
    private String code;
    private String name;

    public CodeNamePair(String code , String name) {
        this.code = code;
        this.name = name;
    }

}