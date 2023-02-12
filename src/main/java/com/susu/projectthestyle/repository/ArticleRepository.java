package com.susu.projectthestyle.repository;

import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.core.types.dsl.StringExpression;
import com.susu.projectthestyle.domain.Article;
import com.susu.projectthestyle.domain.QArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>
       ,QuerydslPredicateExecutor<Article> //Article 에 있는 모든 필드에 대한 검색 기능을 넣어준다.
       ,QuerydslBinderCustomizer<QArticle>
{
    @Override
    default void customize(QuerydslBindings bindings, QArticle root){
        bindings.excludeUnlistedProperties(true);//선택적인 필드에 대해서만 필터링 하기 위해 ..리스팅하지 않은 프로퍼티는 검색하지 않는 것을 true로
        bindings.including(root.title, root.content, root.hashtag, root.createdAt, root.createdBy);
        //bindings.bind(root.title).first(StringExpression::likeIgnoreCase); // like '${v}'
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase); // like '%${v}%'
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase); // like '%${v}%'
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase); // like '%${v}%'
        bindings.bind(root.createdAt).first(DateTimeExpression::eq); //
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase); // like '%${v}%'

    }
}