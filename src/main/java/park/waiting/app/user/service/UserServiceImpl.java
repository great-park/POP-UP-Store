package park.waiting.app.user.service;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import park.waiting.app.common.constant.ErrorCode;
import park.waiting.app.common.exception.GeneralException;
import park.waiting.app.user.dto.CustomerRequest;
import park.waiting.app.user.dto.CustomerResponse;
import park.waiting.app.user.dto.ManagerRequest;
import park.waiting.app.user.dto.ManagerResponse;
import park.waiting.app.user.entity.Customer;
import park.waiting.app.user.exception.SignUpException;
import park.waiting.app.user.repository.CustomerRepository;
import park.waiting.app.user.repository.ManagerRepository;

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
        return null;
    }

    @Transactional
    @Override
    public ManagerResponse adminSignIn(ManagerRequest managerRequest) {
        return null;
    }

    private void validatePhoneNumberIsDuplicated(String phoneNumber) {
        boolean isDuplicated = customerRepository.findByPhoneNumber(phoneNumber).isPresent();
        if (isDuplicated) {
            throw new SignUpException(ErrorCode.DUPLICATE_PHONE_NUMBER);
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
