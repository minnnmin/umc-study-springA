package com.example.demo.src.user.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetUserFeedRes {
    private boolean _isMyFeed; // 내 피드와 다른 사람의 피드를 볼 때 화면 구성 다르므로 구분 필요
    private GetUserInfoRes getUserInfo;
    private List<GetUserPostsRes> getUserPosts;

}
