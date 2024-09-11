package com.yhkim.domain.product.entity;

import com.yhkim.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "products")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Product extends BaseEntity {
    @Comment("상품명")
    @Column(unique = true, nullable = false)
    private String name;
    
    @Comment("상품코드")
    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductCode code;
}
