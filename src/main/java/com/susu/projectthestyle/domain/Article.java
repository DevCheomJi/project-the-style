package com.susu.projectthestyle.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString //쉽게 출력 관찰하기 위해
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy"),

} )
@Entity
public class Article extends AuditingFields{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //mysql 에서는 identity로
    private Long id;

    //다른 것들은 임의로 변경안되게끔 여기에다가 Setter건다.
    @Setter @Column(nullable = false) private String title; //제목
    @Setter @Column(nullable = false, length = 10000) private String content; //본문

    @Setter private String hashtag; //해시태그


    //운영 시 migration 이나 강결합등 이슈 때문에 CascadeType 안거는 경우가 많지만 스터디 목적으로 쓴다.
    //cf) 게시글 삭제해도 댓글 데이터 남기는 경우가 많다.
    @OrderBy("id")
    @ToString.Exclude // article<-> articlecomment 순환참조 발생할 수 있어서 ...
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();


    //hibernate쓸때 기본생성자 필수
    protected Article(){} //코드 밖에서 사용못하도록 protected

    //private 으로 막고 팩토리 메소드로 쓸 수 있게끔 -> 이걸로 사용가능하다는 것을 가이드해준다.
    private Article(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    public static Article of(String tite, String content, String hashtag){
        return new Article(tite, content, hashtag);
    }

    //equals hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(!(o instanceof Article article)) return false;
        return id!=null && id.equals(article.id); //영속화 되지 않은 엔티티는 id가 없으니(Null) false처리
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
