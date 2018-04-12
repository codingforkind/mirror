package cn.com.cx.ps.mirror.java.variable;

import java.util.Objects;

import org.eclipse.jdt.core.dom.ASTNode;

import lombok.Data;

/**
 * The type Variable. A variable which defined and used in the project is
 * unique.
 */
@Data
public class Variable {
	private String name;
	private VariableType variableType;

	private boolean isField;
	private String file; // if this variable is not a field then it must in the file which got it in the
							// first place.

	private int lineNum; // the location of a variable first appears, like the definition point of the variable  
	private ASTNode astNode;

	@Override
	public int hashCode() {
		return Objects.hash(name, variableType, isField, file);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		Variable that = (Variable) obj;
		return isField == that.isField() && 
				Objects.equals(name, that.getName()) && 
				Objects.equals(variableType, that.getVariableType()) && 
				Objects.equals(file, that.getFile());
	}

}