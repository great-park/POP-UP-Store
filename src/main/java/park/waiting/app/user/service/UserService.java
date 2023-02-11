package park.waiting.app.user.service;

import park.waiting.app.user.dto.*;

public interface UserService {

    CustomerResponse signUp(CustomerRequest customerRequest);

    CustomerResponse signIn(String phoneNumber);

    CustomerResponse updateName(CustomerUpdateNameRequest updateNameRequest);

    ManagerResponse adminSignUp(ManagerRequest managerRequest);

    ManagerResponse adminSignIn(ManagerSignInRequest managerSignInRequest);
}
