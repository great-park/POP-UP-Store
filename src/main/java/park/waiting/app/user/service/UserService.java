package park.waiting.app.user.service;

import park.waiting.app.user.dto.CustomerRequest;
import park.waiting.app.user.dto.CustomerResponse;
import park.waiting.app.user.dto.ManagerRequest;
import park.waiting.app.user.dto.ManagerResponse;

public interface UserService {

    CustomerResponse signup(CustomerRequest customerRequest);

    CustomerResponse signIn(String phoneNumber);

    CustomerResponse updateName(CustomerRequest customerRequest);

    ManagerResponse adminSignup(ManagerRequest managerRequest);

    ManagerResponse adminSignIn(ManagerRequest managerRequest);
}
