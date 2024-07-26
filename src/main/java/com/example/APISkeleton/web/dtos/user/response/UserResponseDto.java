package com.example.APISkeleton.web.dtos.user.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private Character gender;
    private List<UserRoleDto> userRoles;
    private List<String> namePlant;
    private LocalDateTime createdAt;




    // DTO para la entidad UserRole
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserRoleDto {
        private Long id;
        private RoleDto role;

        // DTO para la entidad Role
        @Getter
        @Setter
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class RoleDto {
            private Long id;
            private String name;
        }
    }
}
