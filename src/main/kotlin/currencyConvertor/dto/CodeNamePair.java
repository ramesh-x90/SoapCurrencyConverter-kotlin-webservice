package currencyConvertor.dto;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class CodeNamePair{
    private final String code;
    private final String name;

    public CodeNamePair(String code , String name) {
        this.code = code;
        this.name = name;
    }

}