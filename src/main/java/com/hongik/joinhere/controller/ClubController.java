package com.hongik.joinhere.controller;

import com.hongik.joinhere.dto.club.CreateClubRequest;
import com.hongik.joinhere.dto.club.CreateClubResponse;
import com.hongik.joinhere.dto.club.ShowClubInfoResponse;
import com.hongik.joinhere.dto.club.ShowClubResponse;
import com.hongik.joinhere.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/clubs")
public class ClubController {

    private final ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping
    public ResponseEntity<List<ShowClubResponse>> showAll() {
        List<ShowClubResponse> responses = clubService.findClubs();
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    @PostMapping
    public ResponseEntity<CreateClubResponse> create(@RequestBody CreateClubRequest request) {
        String memberId = request.getId();
        System.out.println(memberId);
        CreateClubResponse response = clubService.register(request, memberId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/categories/{category}")
    public ResponseEntity<List<ShowClubResponse>> showClubsByCategory(@PathVariable String category) {
        List<ShowClubResponse> responses = clubService.findClubsByCategory(category);
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    @GetMapping("/{club-id}")
    public ResponseEntity<ShowClubInfoResponse> showClubInfo(@PathVariable("club-id") Long id) {
        ShowClubInfoResponse response = clubService.findClubInfo(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}