package cn.com.mirror.project.service;

public interface MaxClientService {

    /**
     * Check the max client num is reached or not.
     */
    boolean maxCliNumReached();

    /**
     * Check the max client limitation is enabled.
     */
    boolean maxCliEnabled();


    /**
     * Check and using max client function.
     */
    void increaseClientCount();
}
