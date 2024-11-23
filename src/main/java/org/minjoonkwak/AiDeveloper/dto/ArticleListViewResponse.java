package org.minjoonkwak.AiDeveloper.dto;

import lombok.Getter;
import org.minjoonkwak.AiDeveloper.domain.Article;

@Getter
public class ArticleListViewResponse { //글 목록 뷰 구현하기

    private final Long id;
    private final String title;
    private final String content;

    public ArticleListViewResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}