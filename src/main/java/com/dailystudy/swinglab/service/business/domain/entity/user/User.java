package com.dailystudy.swinglab.service.business.domain.entity.user;

import com.dailystudy.swinglab.service.framework.core.gen.entity.UserCore;
import jakarta.persistence.Transient;
import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name="tb_user")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends UserCore
{
    @Transient
    private String pwdChk;
}