package com.example.demo.src.user;


import com.example.demo.config.BaseException;
import com.example.demo.src.user.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

//Provider : Read의 비즈니스 로직 처리
@Service
public class UserProvider {

    private final UserDao userDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UserProvider(UserDao userDao, JwtService jwtService) {
        this.userDao = userDao;
        this.jwtService = jwtService;
    }


    public GetUserFeedRes retrieveUserFeed(int userIdxByJwt, int userIdx) throws BaseException{
        // userIdxByJwt: 로그인된 정보
        // userIdx: pathVariable 로 받은 값

        Boolean isMyFeed = true;
        if (checkUserExist(userIdx) == 0) {
            throw new BaseException(USERS_EMPTY_USER_ID);
        }
        try{
            if (userIdxByJwt != userIdx)
                isMyFeed = false;

            GetUserInfoRes getUserInfo = userDao.selectUserInfo(userIdx);
            List<GetUserPostsRes> getUserPosts = userDao.selectUserPosts(userIdx);
            GetUserFeedRes getUserFeedRes = new GetUserFeedRes(isMyFeed, getUserInfo, getUserPosts);
            return getUserFeedRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
                    }


    public GetUserRes getUsersByIdx(int userIdx) throws BaseException{
        try{
            GetUserRes getUsersRes = userDao.getUsersByIdx(userIdx);
            return getUsersRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public int checkEmail(String email) throws BaseException{
        try{
            return userDao.checkEmail(email);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
    public int checkUserExist(int userIdx) throws BaseException{
        try{
            return userDao.checkUserExist(userIdx);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public DeleteUserRes deleteUserByIdx(int userIdx) throws BaseException {
        try {
            return userDao.deleteUserByIdx(userIdx);
        } catch (Exception exception) {
            throw new BaseException(NO_USER_ERROR);
        }
    }


}
