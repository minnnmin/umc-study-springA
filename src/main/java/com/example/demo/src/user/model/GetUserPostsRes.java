package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetUserPostsRes {
    private int postIdx;
    private String postImgUrl;
}
// 이미지 밖에 안 보이니까 postImgUrl만 필요하다고 생각할 수 있지만
// postIdx를 알아야 그 이미지를 불러올 수 있으므로 이것 또한 필요
