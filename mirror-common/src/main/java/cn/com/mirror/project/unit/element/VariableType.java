package cn.com.mirror.project.unit.element;

import cn.com.mirror.exceptions.AnalysisException;
import lombok.Data;
import org.eclipse.jdt.core.dom.ITypeBinding;

import java.util.Objects;

@Data
public class VariableType {

	public static enum TYPE {
		PRIME, CLASS, INTERFACE, ARRAY, ENUM, OTHER;
		public static TYPE judgeType(ITypeBinding typeBinding) {
			if (typeBinding.isPrimitive())
				return TYPE.PRIME;
			if (typeBinding.isEnum())
				return TYPE.ENUM;
			if (typeBinding.isClass())
				return TYPE.CLASS;
			if (typeBinding.isArray())
				return TYPE.ARRAY;
			if (typeBinding.isInterface())
				return TYPE.INTERFACE;
			return TYPE.OTHER;
		}
	}

	private TYPE type;
	private PRIME prime;
	private String qualifiedName;
	private VariableType arrayEleType;

	public VariableType(TYPE type) {
		this.type = type;
	}

	public VariableType(TYPE type, VariableType arrayEleType) {
		this(type);
		this.arrayEleType = arrayEleType;
		this.qualifiedName = arrayEleType.getQualifiedName();
	}

	public VariableType(TYPE type, String qualifiedName) {
		this(type);
		this.qualifiedName = qualifiedName;
	}

	public VariableType(TYPE type, PRIME prime) {
		this(type);
		this.prime = prime;
		this.qualifiedName = prime.getCode();
	}

	@Override
	public int hashCode() {
		if (null == qualifiedName) {
			return Objects.hash(type, prime, arrayEleType, null);
		}
		return Objects.hash(type, prime, arrayEleType, qualifiedName.hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		VariableType that = (VariableType) obj;
		return Objects.equals(type, that.getType()) &&
				Objects.equals(prime, that.getPrime()) &&
				Objects.equals(arrayEleType, that.getArrayEleType()) &&
				Objects.equals(qualifiedName, that.getQualifiedName());
	}

	public static enum PRIME {
		INT("int"), 
		CHAR("char"), 
		DOUBLE("double"), 
		BOOLEAN("boolean"), 
		FLOAT("float"), 
		BYTE("byte"), 
		SHORT("short"), 
		LONG("long");

		private String code;

		private PRIME(String code) {
			this.code = code;
		}

		public final static PRIME prime(String code) {
			for (PRIME p : PRIME.values()) {
				if (code.equals(p.getCode())) {
					return p;
				}
			}
			throw new AnalysisException("No PRIME type found!");
		}

		public final static boolean isPRIME(String code) {
			for (PRIME p : PRIME.values()) {
				if (code.equals(p.getCode())) {
					return true;
				}
			}
			return false;
		}

		public String getCode() {
			return this.code;
		}
	}

}
