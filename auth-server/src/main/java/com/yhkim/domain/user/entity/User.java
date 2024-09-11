package com.yhkim.domain.user.entity;


import com.yhkim.domain.user.dto.UserDetailResponse;
import com.yhkim.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User extends BaseEntity {
    
    @Comment("계정")
    @Column(unique = true, nullable = false, length = 20)
    private String username;
    
    @Comment("비밀번호")
    @Column(nullable = false)
    private String password;
    
    @Comment("이름")
    @Column(nullable = false, name = "fullname", length = 50)
    private String fullName;
    
    @Comment("전화번호")
    @Column(nullable = false, length = 15)
    private String phoneNumber;
    
    public UserDetailResponse getUserDetail() {
        return UserDetailResponse.builder()
                .userID(this.getId())
                .username(username)
                .fullName(fullName)
                .phoneNumber(phoneNumber)
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .build();
    }
    
    public void update(String username, String password, String fullName, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }
    
    public void delete() {
        this.setDeletedAt();
    }
}
