package org.cms.entity;

public class Article extends BaseEntity {
    private  String title;
    private  String authorId;
    private  String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getAuthorId() {
        return authorId;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Article(String title, String content, String authorId) {
        super();
        this.title = title;
        this.authorId = authorId;
        this.content = content;
    }


}
