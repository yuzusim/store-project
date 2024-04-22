package com.example.storeproject.user;

import com.example.storeproject.errors.exception.Exception401;
import com.example.storeproject.util.ApiUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserJPARepository userJPARepo;
    private final UserService userService;

    private final HttpSession session;

    @PostMapping("/join")
    public String join(UserRequest.JoinDTO reqDTO) {
        //System.out.println(reqDTO);

        User sessionUser = userService.save(reqDTO.toEntity());
        session.setAttribute("sessionUser", sessionUser);
        return "redirect:/loginForm";


//        if (reqDTO.getUsername() == null) {
//            System.out.println("=========");
//            userService.save(reqDTO);
//            System.out.println("=========");
//        }
//        try {
//            User user = userService.findByUsername(reqDTO.getUsername());
//            if (reqDTO.getUsername() == null) {
//                userService.save(reqDTO);
//                //userRepository.userSave(reqDTO);
//                return "/user/loginForm";
//            }
//
//        } catch (DataIntegrityViolationException e) {
//            throw new Exception400("동일한 유저 네임이 존재합니다.");
////            return Script.back("아이디가 중복되었어요");
//        }
        //eturn "redirect:/user/loginForm";
        //return Script.href("/loginForm");
//        return "redirect:/user/loginForm";
    }

    @GetMapping("/api/username-same-check")
    public @ResponseBody ApiUtil<?> usernameSameCheck(@RequestParam("username") String username) {
        User user = userService.findByUsername(username);
        if (user == null) { // 회원가입 해도 된다.
            return new ApiUtil<>(true);
        } else { // 회원가입 하면 안된다.
            return new ApiUtil<>(false);
        }
    }


    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    // 왜 조회인데 post임? 민간함 정보는 body로 보낸다.
    // 로그인만 예외로 select인데 post 사용
    // select * from user_tb where username=? and password=?
    @PostMapping("/login")
    public String login(UserRequest.LoginDTO reqDTO) {
        System.out.println(reqDTO);

        if (reqDTO.getUsername().length() < 3) {
            // 유효하지 않은 경우 에러 페이지나 로그인 폼으로 리다이렉션
            return "redirect:/user/loginForm";
        }

        try {
            User user = userService.findByUsernameAndPassword(reqDTO);

            // 권한 체크
            Boolean isCheck = false;
            if (user.getRole() == 2) { // 1 -> admin, 2 -> user
                isCheck = true;
            }

            // 세션에 사용자 정보 및 권한 체크 결과 설정
            session.setAttribute("isCheck", isCheck);
            session.setAttribute("sessionUser", user); // 락카에 담음 (StateFul)

            return "redirect:/";

        } catch (EmptyResultDataAccessException e) {
            throw new Exception401("유저네임 혹은 비밀번호가 틀렸어요");
        }

    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @GetMapping("/user/updateForm")
    public String updateForm() {
        return "user/updateForm";
    }

    @GetMapping("/logout")
    public String logout() {
        // 세션(session)을 무효화(invalidate)하는 작업을 수행
        session.invalidate();
        return "redirect:/";
    }
}
