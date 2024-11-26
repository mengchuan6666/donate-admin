package com.mengadmin.common.core.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Jwt载体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtSubject implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 账号
     */
    private String username;

}
