package com.susu.projectthestyle.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy"),

} )
@Entity
public class ArticleComment  extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //mysql 에서는 identity로
    private Long id;
    @Setter @ManyToOne(optional = false) private Article article; // 댓글 삭제되더라도 게시글은 남아있어야 하니 cascade none(기본값) 설정
    @Setter @ManyToOne(optional = false) private UserAccount userAccount; // 유저 정보 (ID)
    @Setter @Column(nullable = false, length = 500) private String content; // 본문


    protected ArticleComment(){}

    private ArticleComment(Article article, UserAccount userAccount, String content) {
        this.article = article;
        this.userAccount = userAccount;
        this.content = content;
    }

    public static ArticleComment of(Article article, UserAccount userAccount, String content) {
        return new ArticleComment(article, userAccount, content);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(!(o instanceof ArticleComment that)) return false;
        return id!=null && id.equals(that.id); //영속화 되지 않은 엔티티는 id가 없으니(Null) false처리
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
