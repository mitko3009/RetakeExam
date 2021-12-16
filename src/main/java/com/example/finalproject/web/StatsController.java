package com.example.finalproject.web;

import com.example.finalproject.model.view.StatsView;
import com.example.finalproject.service.StatsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class StatsController {

  private final StatsService statsService;

  public StatsController(StatsService statsService) {
    this.statsService = statsService;
  }

  @GetMapping("/statistics")
  public ResponseEntity<StatsView> statistics() {
    return ResponseEntity.ok(statsService.getStats());
  }

}
