package com.aapnasapna.AapnaSapna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aapnasapna.AapnaSapna.dao.IArticleDAO;
import com.aapnasapna.AapnaSapna.models.Article;

@Service
public class ArticleService implements IArticleService {
	@Autowired
	private IArticleDAO articleDAO;

	@Override
	public synchronized boolean addArticle(Article article) {
		if (articleDAO.articleExists(article.getTitle(), article.getCategory())) {
			return false;
		} else {
			articleDAO.addArticle(article);
			return true;
		}
	}

	@Override
	public void deleteArticle(int articleId) {
		articleDAO.deleteArticle(articleId);
	}

	@Override
	public List<Article> getAllArticles() {
		return articleDAO.getAllArticles();
	}

	@Override
	public Article getArticleById(int articleId) {
		Article obj = articleDAO.getArticleById(articleId);
		return obj;
	}

	@Override
	public void updateArticle(Article article) {
		articleDAO.updateArticle(article);
	}
}