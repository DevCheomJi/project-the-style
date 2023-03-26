package com.susu.projectthestyle.service;

import com.susu.projectthestyle.domain.ArticleComment;
import com.susu.projectthestyle.repository.ArticleCommentRepository;
import com.susu.projectthestyle.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ArticleCommentService {

    private final ArticleCommentRepository articleCommentRepository;
    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public List<ArticleComment> searchArticleComment(Long articleId) {
        return null;
    }
}
