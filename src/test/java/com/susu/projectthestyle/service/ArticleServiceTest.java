package com.susu.projectthestyle.service;

import com.susu.projectthestyle.domain.Article;
import com.susu.projectthestyle.domain.type.SearchType;
import com.susu.projectthestyle.dto.ArticleDto;
import com.susu.projectthestyle.dto.ArticleUpdateDto;
import com.susu.projectthestyle.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.time.LocalDateTime;
import java.util.List;

@DisplayName("비즈니스 로직 - 게시글")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @InjectMocks private ArticleService sut; //테스트 대상

    @Mock private ArticleRepository articleRepository; //테스트 중간에 주입 필요하여

    @DisplayName("게시글을 검색하면, 게시글 리스트를 반환한다.")
    @Test
    void givenSearchParameters_whenSearchingArticles_thenReturnArticleList(){

        //given

        //when
        //List<ArticleDto> articles = sut.searchArticles(SearchType.TITLE, "search keyword"); // 제목, 본문, ID, 닉네임, 헤시태그
        Page<ArticleDto> articles = sut.searchArticles(SearchType.TITLE, "search keyword"); // 제목, 본문, ID, 닉네임, 헤시태그

        //then
        assertThat(articles).isNotNull();
    }

    @DisplayName("게시글을 조회하면, 게시글 반환한다.")
    @Test
    void givenArticleId_whenSearchingArticles_thenReturnArticle(){

        //given

        //when
        ArticleDto article = sut.searchArticle(1L);
        //then
        assertThat(article).isNotNull();
    }

    @DisplayName("게시글 정보를 입력하면, 게시글을 생성한다.")
    @Test
    void givenArticleInfo_whenSavingArticle_thenSavesArticle(){

        //Given
        //ArticleDto dto = ArticleDto.of(LocalDateTime.now(), "Uno", "title", "content", "#java");

        //코드에 무슨일이 일어날지 명시적으로 표현한것에 불과하다.
        //값 넣은 것을 실제 데이터베이스를 통해 검사하거나 그러진 않을 것이다.
        given(articleRepository.save(any(Article.class))).willReturn(null);

        // When
        sut.saveArticle(ArticleDto.of(LocalDateTime.now(), "Uno", "title", "content", "#java"));

        //Then
        then(articleRepository).should().save(any(Article.class)); //호출되었는지 검사

    }

    @DisplayName("게시글 ID와 수정 정보를 입력하면, 게시글을 수정한다.")
    @Test
    void givenModifiedArticleInfo_whenUpdatingArticle_thenUpdatesArticle(){

        //Given
        //ArticleDto dto = ArticleDto.of(LocalDateTime.now(), "Uno", "title", "content", "#java");

        //코드에 무슨일이 일어날지 명시적으로 표현한것에 불과하다.
        //값 넣은 것을 실제 데이터베이스를 통해 검사하거나 그러진 않을 것이다.
        given(articleRepository.save(any(Article.class))).willReturn(null);

        // When
        sut.updateArticle(1L, ArticleUpdateDto.of("title", "content", "#java"));

        //Then
        then(articleRepository).should().save(any(Article.class)); //호출되었는지 검사

    }

    @DisplayName("게시글 ID를 입력하면, 게시글을 삭제한다.")
    @Test
    void givenArticleId_whenDeletingArticle_thenDeletesArticle(){

        willDoNothing().given(articleRepository).delete(any(Article.class));

        // When
        sut.deleteArticle(1L);

        //Then
        then(articleRepository).should().delete(any(Article.class)); //호출되었는지 검사

    }
}