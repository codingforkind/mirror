package cn.com.cx.ps.mirror.java.variable;

import org.eclipse.jdt.core.dom.ASTNode;

import lombok.Data;

/**
 * The type Variable.
 * A variable which defined and used in the project is unique.
 */
@Data
public class Variable {
    private String name;
    private VariableType variableType;
    
    private boolean isField;
    private String file;    // if this variable is not a field then it must in the file which got it in the first place.

    private int lineNum;
    private ASTNode astNode;

    @Override
    public int hashCode() {
        int prime = 37;
        int result = 1;
        result += isField ? 0 : 1;

        result += result * prime + ((name == null) ? 0 : name.hashCode());
        result += result * prime + ((variableType == null) ? 0 : variableType.hashCode());
        result += result * prime + ((file == null) ? 0 : file.hashCode());

        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        Variable tmV = (Variable) obj;
        if (null == name) {
            if (tmV.getName() != null) return false;
        } else {
            if (!name.equals(tmV.getName())) return false;
        }

        if (null == variableType) {
            if (tmV.getVariableType() != null) return false;
        } else {
            if (!variableType.equals(tmV.getVariableType())) return false;
        }

        if (isField) if (!tmV.isField()) return false;
        if (!isField) if (tmV.isField()) return false;

        if (null == file) {
            if (null != tmV.getFile()) return false;
        } else {
            if (!file.equals(tmV.getFile())) return false;
        }

        return true;
    }

}
