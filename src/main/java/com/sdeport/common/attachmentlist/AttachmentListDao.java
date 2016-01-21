package com.sdeport.common.attachmentlist;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 附件列表dao
 *
 * @author
 */
@Repository
public interface AttachmentListDao {

    @Select(
        "SELECT * FROM ATTACHMENT_LIST WHERE LIST_ID=#{listId}")
    List<AttachmentList> queryAttachmentsById(@Param("listId") String listId);

    @Select("SELECT to_char(SEQ_ATTACH_ID.NEXTVAL) FROM DUAL")
    String getSeqListId();

    @Insert("INSERT INTO ATTACHMENT_LIST(LIST_ID,FILE_ID,REMARK)values(#{listId},#{fileId},#{remark})")
    int insertAttachList(@Param("listId") String listId, @Param("fileId") String fileId, @Param("remark") String
        remark);

    @Delete("DELETE FROM ATTACHMENT_LIST WHERE LIST_ID=#{listId}")
    void deleteListById(@Param("listId") String listId);

    @Delete("DELETE FROM ATTACHMENT_LIST WHERE LIST_ID=#{listId} AND FILE_ID=#{fileId}")
    void deleteListByListId(@Param("listId")String listId, @Param("fileId")String fileId);
}
