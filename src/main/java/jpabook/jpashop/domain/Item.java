package jpabook.jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Item {
    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    @OneToMany(mappedBy = "item")
    private List<ItemCategory> itemCategories = new ArrayList<>();

    private String name;
    private Integer price;
    private Integer stockQuantity;
}
