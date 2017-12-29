package cn.com.cx.ps.mirror.project.variable;

import org.eclipse.jdt.core.dom.ASTNode;

/**
 * The type Variable.
 * A variable which defined and used in the project is unique.
 */
public class Variable {
    private String name;
    private VariableType variableType;
    private boolean isField;
    private String file;    // if this variable is not a field then it must in the file which got it in the first place.

    private int lineNum;
    private ASTNode astNode;


    @Override
    public int hashCode() {
//        TODO override hashCode method
        int prime = 37;
        int result = 1;
//        result = prime * result + ((password == null) ? 0 : password.hashCode());
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VariableType getVariableType() {
        return variableType;
    }

    public void setVariableType(VariableType variableType) {
        this.variableType = variableType;
    }

    public boolean isField() {
        return isField;
    }

    public void setField(boolean field) {
        isField = field;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int getLineNum() {
        return lineNum;
    }

    public void setLineNum(int lineNum) {
        this.lineNum = lineNum;
    }

    public ASTNode getAstNode() {
        return astNode;
    }

    public void setAstNode(ASTNode astNode) {
        this.astNode = astNode;
    }
}
