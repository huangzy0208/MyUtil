package com.sdeport.common;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜单dao
 *
 * @author
 */
@Repository
public interface UploadFileDao {

    @Select(
        "SELECT FILE_ID,FILE_NAME,FILE_PATH,TRUE_FILE_NAME,TO_CHAR(UPLOAD_TIME,'YYYY-MM-DD HH24:MI:SS') AS " +
        "UPLOAD_TIME " +
        "FROM UPLOAD_FILE WHERE 1=1")
    List<UploadFile> queryAllFiles();

    @Select(
        "SELECT COUNT(1) " +
        "FROM UPLOAD_FILE WHERE 1=1")
    long queryAllCount();

    @Select(
        "SELECT FILE_ID,FILE_NAME,FILE_PATH,TRUE_FILE_NAME,TO_CHAR(UPLOAD_TIME,'YYYY-MM-DD HH24:MI:SS') AS " +
        "UPLOAD_TIME " +
        "FROM UPLOAD_FILE WHERE FILE_ID=${fileId}")
    List<UploadFile> queryFilesById(@Param("fileId") String fileId);

    @Select("SELECT to_char(SEQ_FILEID.NEXTVAL) FROM DUAL")
    String getSeqFileId();

    @Insert("INSERT INTO UPLOAD_FILE(FILE_ID,FILE_PATH,FILE_NAME,TRUE_FILE_NAME,UPLOAD_TIME) VALUES (#{fileId}," +
            "#{filePath},#{fileName}," +
            "#{trueFileName},SYSDATE)")
    long insertFile(@Param("fileId") String fileId, @Param("filePath") String filePath, @Param("fileName") String
        fileName, @Param("trueFileName") String trueFileName);

    @Delete("DELETE FROM UPLOAD_FILE WHERE FILE_ID=${fileId}")
    void deleteFile(@Param("fileId") long fileId);

    @Select("SELECT FILE_ID,FILE_NAME,FILE_PATH,TRUE_FILE_NAME,TO_CHAR(UPLOAD_TIME,'YYYY-MM-DD HH24:MI:SS') AS " +
            "UPLOAD_TIME " +
            "FROM VEHICLE.UPLOAD_FILE WHERE FILE_ID IN(SELECT FILE_ID FROM VEHICLE.ATTACHMENT_LIST WHERE LIST_ID=#{attachId})")
    List<UploadFile> queryAllByAttach(@Param("attachId") String attachId);
}
