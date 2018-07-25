package cn.com.mirror.variable;

import java.io.Serializable;
import java.util.Objects;

import org.eclipse.jdt.core.dom.TypeDeclaration;

import lombok.Data;

@Data
public class Class implements Serializable{
	private static final long serialVersionUID = 1L;
	private String file;
	private String name;
	private String qualifiedName;
	private String packageName;
	private TypeDeclaration typeDeclaration;
	private boolean isInterface;
	private boolean isPublic;
	private boolean isProtected;
	private boolean isPrivate;
	private boolean isDefault;
	private boolean isFinal;

	@Override
	public int hashCode() {
		return Objects.hash(typeDeclaration, file);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Class that = (Class) obj;
		return Objects.equals(typeDeclaration, that.getTypeDeclaration()) &&
				Objects.equals(file, that.getFile());
	}

}
