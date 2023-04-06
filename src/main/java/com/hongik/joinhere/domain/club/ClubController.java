package com.hongik.joinhere.domain.club;

import com.hongik.joinhere.domain.club.dto.*;
import com.hongik.joinhere.domain.dto.club.ShowClubInfoResponse;
import com.hongik.joinhere.domain.dto.club.ShowClubResponse;
import com.hongik.joinhere.domain.dto.club.UpdateClubRequest;
import com.hongik.joinhere.global.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/clubs")
@RequiredArgsConstructor
public class ClubController {

    private final ClubService clubService;

    @GetMapping
    public ResponseEntity<List<ShowClubResponse>> showAll() {
        List<ShowClubResponse> responses = clubService.findClubs();
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    @PostMapping
    public CommonResponse<CreateClubResponse> create(@RequestPart(value = "request") CreateClubRequest request,
                                 @RequestPart(value = "image", required = false) MultipartFile multipartFile) {
        return CommonResponse.onSuccess(HttpStatus.CREATED.value(), clubService.register(request, multipartFile));
    }

    @PatchMapping
    public CommonResponse<?> update(@RequestPart(value = "request") UpdateClubRequest request,
                                         @RequestPart(value = "image", required = false) MultipartFile multipartFile) {
        clubService.updateClubInfo(request, multipartFile);
        return CommonResponse.onSuccess(HttpStatus.OK.value());
    }

    @GetMapping("/search")
    public List<ShowClubResponse> searchClubs(@RequestParam String query) {
        return clubService.findClubsByQuery(query);
    }

    @GetMapping("/{club-id}")
    public ResponseEntity<ShowClubInfoResponse> showClubInfo(@PathVariable("club-id") Long id) {
        ShowClubInfoResponse response = clubService.findClubInfo(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/categories/{category}")
    public ResponseEntity<List<ShowClubResponse>> showClubsByCategory(@PathVariable String category) {
        List<ShowClubResponse> responses = clubService.findClubsByCategory(category);
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }
}