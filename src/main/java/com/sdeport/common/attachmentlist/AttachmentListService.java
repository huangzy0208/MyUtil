package com.sdeport.common.attachmentlist;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttachmentListService {

    @Autowired
    private AttachmentListDao attachmentListDao;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 删除指定id的附件列表
     *
     * @param listId
     * @return
     */
    public void deleteAttchListById(String listId) {
        attachmentListDao.deleteListById(listId);
    }

    /**
     * 新增附件列表
     *
     * @return
     */
    public String insertAttchList(List<AttachmentList> listAttch) {
        String attachId = "";
        if (!CollectionUtils.isEmpty(listAttch) && StringUtils.isEmpty(listAttch.get(0).getListId())) {
            attachId = this.attachmentListDao.getSeqListId();
        }
        for (AttachmentList attachment : listAttch) {
            if (StringUtils.isEmpty(attachment.getListId())) {
                attachment.setListId(attachId);
            }
            if (StringUtils.isEmpty(attachment.getRemark())) {
                attachment.setRemark("");
            }
            this.attachmentListDao.insertAttachList(attachment.getListId(), attachment.getFileId(),
                                                    attachment.getRemark());
        }
        return attachId;
    }

    /**
     * 生成附件列表
     *
     * @param fileNames
     * @return
     */
    public List<AttachmentList> generateListAttach(final String[] fileNames) {
        List<AttachmentList> listAttch = new ArrayList<AttachmentList>();
        if (null != fileNames) {
            for (String fileName : fileNames) {
                listAttch.add(new AttachmentList(fileName));
            }
        }
        return listAttch;
    }

    /**
     * 删除附件列表
     *
     * @param listId
     * @param fileId
     */
    public void deleteAttchListById(final String listId, final long fileId) {
        attachmentListDao.deleteListByListId(listId, String.valueOf(fileId));
    }

    /**
     * 生成附件列表
     *
     * @param fileId
     * @return
     */
    public List<AttachmentList> generateListAttach(final long fileId) {
        List<AttachmentList> listAttch = new ArrayList<AttachmentList>();
        listAttch.add(new AttachmentList(String.valueOf(fileId)));
        return listAttch;
    }

    /**
     * 生成附件列表
     *
     * @param qryList
     * @param fileId
     * @return
     */
    public List<AttachmentList> generateListAttach(final String qryList, final long fileId) {
        List<AttachmentList> listAttch = new ArrayList<AttachmentList>();
        listAttch.add(new AttachmentList(qryList, String.valueOf(fileId)));
        return listAttch;
    }

    /**
     * 复制一个附件列表并返回复制后的列表id
     *
     * @param listId
     * @return
     */
    public String copyAttachmentList(String listId) {
        String copyId = attachmentListDao.getSeqListId();
        if (StringUtils.isNotEmpty(listId)) {
            List<AttachmentList> listAttachs = attachmentListDao.queryAttachmentsById(listId);
            if (!CollectionUtils.isEmpty(listAttachs)) {
                for (AttachmentList attach : listAttachs) {
                    attachmentListDao.insertAttachList(copyId, attach.getFileId(), "");
                }
            }
        }
        return copyId;
    }
}
