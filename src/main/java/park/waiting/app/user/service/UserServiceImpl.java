package park.waiting.app.user.service;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import park.waiting.app.common.constant.ErrorCode;
import park.waiting.app.common.exception.GeneralException;
import park.waiting.app.user.dto.*;
import park.waiting.app.user.entity.Customer;
import park.waiting.app.user.entity.Manager;
import park.waiting.app.user.exception.AuthException;
import park.waiting.app.user.repository.CustomerRepository;
import park.waiting.app.user.repository.ManagerRepository;
import park.waiting.app.util.CustomPasswordEncoder;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final CustomerRepository customerRepository;

    private final ManagerRepository managerRepository;

    @Transactional
    @Override
    public CustomerResponse signup(CustomerRequest customerRequest) {
        validatePhoneNumberIsDuplicated(customerRequest.getPhoneNumber());
        Customer customer = Customer.builder()
                .name(customerRequest.getName())
                .phoneNumber(customerRequest.getPhoneNumber())
                .build();
        Customer createdCustomer = customerRepository.save(customer);

        return createdCustomer.toResponse();
    }

    @Transactional
    @Override
    public CustomerResponse signIn(String phoneNumber) {
        Optional<Customer> customer = customerRepository.findByPhoneNumber(phoneNumber);

        // 자동 회원가입 - 랜덤 닉네임 부여
        if (customer.isEmpty()) {
            CustomerRequest createRequest = CustomerRequest.builder()
                    .name(createRandomName())
                    .phoneNumber(phoneNumber)
                    .build();

            return signup(createRequest);
        }

        return customer.get().toResponse();
    }

    @Transactional
    @Override
    public CustomerResponse updateName(CustomerRequest customerRequest) {
        Customer customer = customerRepository.findByPhoneNumber(customerRequest.getPhoneNumber())
                .orElseThrow(() -> new GeneralException(ErrorCode.DATA_ACCESS_ERROR));
        customer.setName(customerRequest.getName());

        return customer.toResponse();
    }

    @Transactional
    @Override
    public ManagerResponse adminSignup(ManagerRequest managerRequest) {
        validateEmailIsDuplicated(managerRequest.getEmail());

        Manager manager = Manager.builder()
                .email(managerRequest.getEmail())
                .hashed_password(CustomPasswordEncoder.hashPassword(managerRequest.getPassword()))
                .name(managerRequest.getName())
                .phoneNumber(managerRequest.getPhoneNumber())
                .build();
        Manager createdManager = managerRepository.save(manager);

        return createdManager.toResponse();
    }

    @Transactional
    @Override
    public ManagerResponse adminSignIn(ManagerSignInRequest managerSignInRequest) {
        Manager manager = managerRepository.findByEmail(managerSignInRequest.getEmail())
                .orElseThrow(() -> new AuthException(ErrorCode.MANAGER_NOT_FOUND));
        validatePassword(managerSignInRequest.getPassword(), manager);

        return manager.toResponse();
    }

    private void validatePassword(String password, Manager manager) {
        boolean isMatched = CustomPasswordEncoder.isMatched(
                password,
                manager.getHashed_password());
        if (!isMatched) {
            throw new AuthException(ErrorCode.PASSWORD_MISMATCHED);
        }
    }

    private void validatePhoneNumberIsDuplicated(String phoneNumber) {
        boolean isDuplicated = customerRepository.findByPhoneNumber(phoneNumber).isPresent();
        if (isDuplicated) {
            throw new AuthException(ErrorCode.DUPLICATE_PHONE_NUMBER);
        }
    }

    private void validateEmailIsDuplicated(String email) {
        boolean isDuplicated = managerRepository.findByEmail(email).isPresent();
        if (isDuplicated) {
            throw new AuthException(ErrorCode.DUPLICATE_EMAIL);
        }
    }

    private String createRandomName() {
        String name = RandomString.make(6);
        boolean isDuplicated = true;
        while (isDuplicated) {
            name = RandomString.make(6);
            isDuplicated = customerRepository.findByName(name).isPresent();
        }
        return name;
    }
}
