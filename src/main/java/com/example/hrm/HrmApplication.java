package com.example.hrm;

import com.example.hrm.entity.Employee;
import com.example.hrm.entity.User;
import com.example.hrm.repository.EmployeeRepository;
import com.example.hrm.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class HrmApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrmApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(UserRepository userRepository,
                                      EmployeeRepository employeeRepository,
                                      PasswordEncoder passwordEncoder) {
        return args -> {
            // User đăng nhập
            if (userRepository.findByUsername("huongcaoha").isEmpty()) {
                User user = User.builder()
                        .username("huongcaoha")
                        .password(passwordEncoder.encode("huongcaoha123"))
                        .fullName("Nguyễn Công Hưởng")
                        .role("USER")
                        .enabled(true)
                        .build();
                userRepository.save(user);
            }

            // Nhân viên mẫu ban đầu có ID = 1
            if (employeeRepository.count() == 0) {
                Employee employee = Employee.builder()
                        .fullName("Nguyễn Công Hưởng")
                        .email("huongcaoha@gmail.com")
                        .department("Đào Tạo")
                        .avatarUrl("https://res.cloudinary.com/dudtrotnp/image/upload/v1770293152/anh_goc.png")
                        .salary(6000.0)
                        .build();
                employeeRepository.save(employee);
            }
        };
    }
}