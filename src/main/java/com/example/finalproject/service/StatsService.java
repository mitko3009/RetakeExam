package com.example.finalproject.service;

import com.example.finalproject.model.view.StatsView;

public interface StatsService {
  void onRequest();
  StatsView getStats();
}
