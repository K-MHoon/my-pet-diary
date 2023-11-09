package com.kmhoon.common.model.entity.log;


import com.kmhoon.common.enums.LoginStatus;
import com.kmhoon.common.model.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tb_login_log")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class LoginLog extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loginEmail;

    private LoginStatus loginStatus;

    @Builder
    private LoginLog(String loginEmail, LoginStatus loginStatus) {
        this.loginEmail = loginEmail;
        this.loginStatus = loginStatus;
    }
}
