package park.waiting.app.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import park.waiting.app.common.dto.ApiDataResponse;
import park.waiting.app.user.dto.CustomerRequest;
import park.waiting.app.user.dto.CustomerResponse;
import park.waiting.app.user.dto.ManagerRequest;
import park.waiting.app.user.dto.ManagerResponse;
import park.waiting.app.user.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-in")
    public ApiDataResponse<CustomerResponse> customerSignIn(
            @RequestBody CustomerRequest customerRequest,
            HttpServletRequest request
    ) {
        CustomerResponse customerResponse = userService.signIn(customerRequest.getPhoneNumber());

        HttpSession session = request.getSession();
        session.setAttribute("AUTH_CUSTOMER", customerResponse);

        return ApiDataResponse.of(customerResponse);
    }
}
