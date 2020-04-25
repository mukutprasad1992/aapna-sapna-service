package com.aapnasapna.AapnaSapna.dao;

import java.util.List;

import com.aapnasapna.AapnaSapna.models.Article;

public interface IArticleDAO {
	void addArticle(Article article);

	boolean articleExists(String title, String category);

	void deleteArticle(int articleId);

	List<Article> getAllArticles();

	Article getArticleById(int articleId);

	void updateArticle(Article article);
}