package com.laioffer.tinnews.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.laioffer.tinnews.model.Article;
import com.laioffer.tinnews.model.NewsResponse;
import com.laioffer.tinnews.repository.NewsRepository;

public class HomeViewModel extends ViewModel {
    private final NewsRepository newsRepository;
    private final MutableLiveData<String> countryInput = new MutableLiveData<>();
    public HomeViewModel(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public void setCountryInput(String country) {
        countryInput.setValue(country);
    }

    public LiveData<NewsResponse> getTopHeadlines() {
        return Transformations.switchMap(countryInput, newsRepository::getTopHeadlines);
    }
    // line 18 - 24 == getTopHeadlines(String Country)
    // why break down to two part? -- 1. need to have country first then can getTopheadlines

    public void favoriteArticle(Article article) {
        newsRepository.favoriteArticle(article);
    }
}
