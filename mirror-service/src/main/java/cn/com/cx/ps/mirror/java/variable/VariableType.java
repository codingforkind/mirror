package cn.com.cx.ps.mirror.java.variable;

import lombok.Data;

@Data
public class VariableType {
    public static enum TYPE {
        CLASS,INTERFACE,PRIMITIVE,ARRAY,NULL,ENUM,OTHER;
    }

    public static enum PRIME {
        INT, CHAR, DOUBLE, BOOLEAN, FLOAT, BYTE, SHORT, LONG
    }

    private TYPE type;

    private String classType;
    private String otherClass;
    private String interfaceType;

    private PRIME primeType;

    private VariableType arrayElementType;
    private String enumType;

    private String otherType;

    @Override
    public int hashCode() {
        int prime = 37;
        int result = 1;
//        CLASS,INTERFACE,PRIMITIVE,ARRAY,NULL,ENUM,OTHER;
//        if(this.getType().equals(TYPE.CLASS)) result += 1;
//        if(this.getType().equals(TYPE.INTERFACE)) result += 2;
//        if(this.getType().equals(TYPE.PRIMITIVE)) result += 3;
//        if(this.getType().equals(TYPE.ARRAY)) result += 4;
//        if(this.getType().equals(TYPE.NULL)) result += 5;
//        if(this.getType().equals(TYPE.ENUM)) result += 6;
//        if(this.getType().equals(TYPE.OTHER)) result += 7;

//        result += result * prime + ((name == null) ? 0 : name.hashCode());
        result += result * prime + ((type == null) ? 0 : type.hashCode());
        result += result * prime + ((classType == null) ? 0 : classType.hashCode());
        result += result * prime + ((otherClass == null) ? 0 : otherClass.hashCode());
        result += result * prime + ((interfaceType == null) ? 0 : interfaceType.hashCode());
        result += result * prime + ((primeType == null) ? 0 : primeType.hashCode());
        result += result * prime + ((arrayElementType == null) ? 0 : arrayElementType.hashCode());
        result += result * prime + ((enumType == null) ? 0 : enumType.hashCode());
        result += result * prime + ((otherType == null) ? 0 : otherType.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        VariableType tmV = (VariableType) obj;
        if (!tmV.getType().equals(this.getType())) return false;

        if (this.getClassType() == null) {
            if (tmV.getClassType() != null) {
                return false;
            }
        } else {
            if (!this.getClassType().equals(tmV.getClassType())) return false;
        }

        if (null == this.getOtherClass()) {
            if (tmV.getOtherClass() != null) return false;
        } else {
            if (!this.getOtherClass().equals(tmV.getOtherClass())) return false;
        }

        if (null == this.getInterfaceType()) {
            if (tmV.getInterfaceType() != null) return false;
        } else {
            if (!this.getInterfaceType().equals(tmV.getInterfaceType())) return false;
        }

        if (null == this.getPrimeType()) {
            if (tmV.getPrimeType() != null) return false;
        } else {
            if (!this.getPrimeType().equals(tmV.getPrimeType())) return false;
        }

        if (null == this.getArrayElementType()) {
            if (tmV.getArrayElementType() != null) return false;
        } else {
            if (!this.getArrayElementType().equals(tmV.getArrayElementType())) return false;
        }

        if (null == this.getEnumType()) {
            if (tmV.getEnumType() != null) return false;
        } else {
            if (!this.getEnumType().equals(tmV.getEnumType())) return false;
        }

        if (null == this.getOtherType()) {
            if (tmV.getOtherType() != null) return false;
        } else {
            if (!this.getOtherType().equals(tmV.getOtherType())) return false;
        }

        return true;
    }


}
