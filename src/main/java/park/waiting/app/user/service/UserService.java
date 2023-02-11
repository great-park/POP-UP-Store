package park.waiting.app.user.service;

import park.waiting.app.user.dto.*;

public interface UserService {

    CustomerResponse signup(CustomerRequest customerRequest);

    CustomerResponse signIn(String phoneNumber);

    CustomerResponse updateName(CustomerRequest customerRequest);

    ManagerResponse adminSignup(ManagerRequest managerRequest);

    ManagerResponse adminSignIn(ManagerSignInRequest managerSignInRequest);
}
