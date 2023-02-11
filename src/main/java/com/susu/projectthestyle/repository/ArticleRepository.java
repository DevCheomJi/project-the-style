package com.susu.projectthestyle.repository;

import com.susu.projectthestyle.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}