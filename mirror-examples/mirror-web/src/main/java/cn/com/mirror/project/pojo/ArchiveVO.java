package cn.com.mirror.project.pojo;

import lombok.Data;

@Data
public class ArchiveVO {

    /**
     * archive id
     */
    private String achvId;

    /**
     * archive zip type
     */
    private String type;

    /**
     * archive zip file location
     */
    private String url;

    /**
     * unziped archive location
     */
    private String dir;

}
