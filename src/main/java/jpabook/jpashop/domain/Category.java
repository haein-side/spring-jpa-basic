package jpabook.jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {

    @Id @GeneratedValue
    @Column(name = "CATEGORY_ID")
    private Long id;

    @ManyToOne // 자식입장에서 부모 여러 개 (Self Join)
    @JoinColumn(name = "PARENT_ID")
    private Category parent;

    @OneToMany(mappedBy = "parent") // child (Self Join 양방향)
    private List<Category> child = new ArrayList<>();

    @OneToMany(mappedBy = "category")
    private List<ItemCategory> itemCategories = new ArrayList<>();

    @Column(name = "NAME")
    private String name;
}
