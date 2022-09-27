package com.hongik.joinhere.dto.club;

import com.hongik.joinhere.entity.Announcement;
import com.hongik.joinhere.entity.Club;
import com.hongik.joinhere.entity.Comment;
import com.hongik.joinhere.entity.Qna;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ShowClubInfoResponse {

    private Club club;
    private Announcement announcement;
    private Comment comment;
    private Qna qna;

    public static ShowClubInfoResponse from(Club club, Announcement announcement, Comment comment, Qna qna) {
        return new ShowClubInfoResponse(club, announcement, comment, qna);
    }
}