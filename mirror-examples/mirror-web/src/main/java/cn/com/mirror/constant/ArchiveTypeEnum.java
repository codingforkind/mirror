package cn.com.mirror.constant;

import cn.com.mirror.exceptions.UnitException;
import lombok.Getter;

@Getter
public enum ArchiveTypeEnum {

    _ZIP(1, "zip", "postfix is .zip");

    private Integer id;
    private String postfix;
    private String desc;

    ArchiveTypeEnum(Integer id, String postfix, String desc) {
        this.id = id;
        this.postfix = postfix;
        this.desc = desc;
    }

    public static final ArchiveTypeEnum checkAchvType(String postfix) {
        for (ArchiveTypeEnum archiveTypeEnum : ArchiveTypeEnum.values()) {
            if (archiveTypeEnum.getPostfix().equals(postfix)) {
                return archiveTypeEnum;
            }
        }

        throw new UnitException(postfix + " type of archive is not supported.");
    }
}
