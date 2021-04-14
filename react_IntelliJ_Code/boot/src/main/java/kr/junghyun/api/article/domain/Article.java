package kr.junghyun.api.article.domain;

import javax.persistence.*;

@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue

    @Column(name = "article_id")
    private long articleId;
    @Column(name = "writer")
    private String writer;
    @Column(name = "email")
    private String email;
    @Column(name = "subject")
    private String subject;
    @Column(name = "content")
    private String content;
    @Column(name = "rdate")
    private String rdate;

}
