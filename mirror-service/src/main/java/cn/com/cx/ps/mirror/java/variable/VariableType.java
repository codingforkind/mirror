package cn.com.cx.ps.mirror.java.variable;

import org.eclipse.jdt.core.dom.ITypeBinding;
import org.springframework.util.Assert;

import cn.com.cx.ps.mirror.common.exceptions.AnalysisException;
import lombok.Data;

@Data
public class VariableType {
	
/*
// TODO 增加类型
	if (typeBinding.isGenericType()) {
		log.info("\t GenericType: {}, {}, {}", typeBinding.getBinaryName(), typeBinding.getName(),
				typeBinding.getQualifiedName());

		return varType;
	}

	if (typeBinding.isIntersectionType()) {
		log.info("intersectionType: {}", typeBinding);
		return varType;
	}*/
	
	public static enum TYPE {
		 PRIME, CLASS, INTERFACE, ARRAY, ENUM, OTHER;
		public static TYPE judgeType(ITypeBinding typeBinding) {
			if (typeBinding.isPrimitive()) return TYPE.PRIME;
			if (typeBinding.isEnum()) return TYPE.ENUM;
			if (typeBinding.isClass()) return TYPE.CLASS;
			if (typeBinding.isArray()) return TYPE.ARRAY;
			if (typeBinding.isInterface()) return TYPE.INTERFACE;
			return TYPE.OTHER;
		}
	}

	private TYPE type;
	private PRIME prime;
	private String qualifiedName;

	private VariableType arrayEleType;
	
	public VariableType(TYPE type){
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

	public VariableType(TYPE type, PRIME prime){
		this(type);
		this.prime = prime;
		this.qualifiedName = prime.getCode();
	}

	@Override
	public int hashCode() {
		int prime = 37;
		int result = 1;
		result += result * prime + ((type == null) ? 0 : type.hashCode());
		result += result * prime + ((qualifiedName == null) ? 0 : qualifiedName.hashCode());
		result += result * prime + ((arrayEleType == null) ? 0 : arrayEleType.hashCode());
		result += result * prime + ((this.getPrime() == null) ? 0 : this.getPrime().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		VariableType tmV = (VariableType) obj;
		if (!tmV.getType().equals(this.getType()))
			return false;

		if (null == this.getPrime()) {
			if (tmV.getPrime() != null)
				return false;
		} else {
			if (!this.getPrime().equals(tmV.getPrime()))
				return false;
		}

		if (null == this.getArrayEleType()) {
			if (tmV.getArrayEleType() != null)
				return false;
		} else {
			if (!this.getArrayEleType().equals(tmV.getArrayEleType()))
				return false;
		}

		if (null == this.getQualifiedName()) {
			if (tmV.getQualifiedName() != null)
				return false;
		} else {
			if (!this.getQualifiedName().equals(tmV.getQualifiedName()))
				return false;
		}

		return true;
	}
	
	public static enum PRIME {
		INT("int"), CHAR("char"), DOUBLE("double"), BOOLEAN("boolean"), FLOAT("float"), BYTE("byte"), SHORT("short"), LONG("long");

		private String code;
		private PRIME(String code) {
			this.code = code;
		}
		public final static PRIME prime(String code) {
			Assert.notNull(code, "prime argument is NULL in " + PRIME.class);
			for (PRIME p : PRIME.values()) {
				if (code.equals(p.getCode())) {
					return p;
				}
			}
			throw new AnalysisException("No PRIME type found!");
		}
		public final static boolean isPRIME(String code) {
			Assert.notNull(code, "isPRIME argument  is NULL in " + PRIME.class);
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
