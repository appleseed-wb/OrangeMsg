package com.orange.msg.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

/**
 * 消息模版
 */
@Document(collection = "mms_template")
public class Template {

    @Id
    private String uuid;

    /**
     * 模版分类
     */
    private String type;

    /**
     * 模板标题
     */
    private String title;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 模版内容
     */
    private String content;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 模版填充
     * @param params
     * @return
     */
    public String fill(Map<String,Object> params){
        String content = this.content;
        for (String key : params.keySet()) {
            content.replaceAll("${"+key+"}",params.get(key).toString());
        }
        return content;
    }
}
