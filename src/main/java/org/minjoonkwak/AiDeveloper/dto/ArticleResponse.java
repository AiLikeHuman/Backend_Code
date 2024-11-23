package org.minjoonkwak.AiDeveloper.dto;

import lombok.Getter;
import org.minjoonkwak.AiDeveloper.domain.Article;

@Getter
public class ArticleResponse { //블로그 글 목록 조회를 위한 API

    private final String title;
    private final String content;

    public ArticleResponse(Article article) {
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}