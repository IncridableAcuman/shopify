package com.commerce.server.domain.product.entity.enums;

public enum SubCategory {
    T_SHIRTS(Category.MEN_CLOTHING),
    HOODIES_SWEATSHIRTS(Category.MEN_CLOTHING),
    SWEATERS_KNITWEAR(Category.MEN_CLOTHING),
    PANTS_CHINOS(Category.MEN_CLOTHING),
    JEANS(Category.MEN_CLOTHING),
    SHORTS(Category.MEN_CLOTHING),
    ACCESSORIES(Category.MEN_CLOTHING),

    SKIRTS(Category.WOMEN_CLOTHING),
    DRESSES(Category.WOMEN_CLOTHING),
    UNDERWEAR_SOCKS(Category.WOMEN_CLOTHING),
    SPORTSWEAR(Category.WOMEN_CLOTHING),
    SLEEPWEAR(Category.WOMEN_CLOTHING),

    ROMPERS_ONESIES(Category.KIDS_CLOTHING),
    SCHOOL_UNIFORM(Category.KIDS_CLOTHING),
    TRACKSUITS(Category.KIDS_CLOTHING),
    SETS_SUITS(Category.KIDS_CLOTHING),
    OVERALLS(Category.KIDS_CLOTHING),
    SWIMWEAR(Category.KIDS_CLOTHING);


    private final Category parentCategory;

    SubCategory(Category parentCategory){
        this.parentCategory=parentCategory;
    }
    public Category getParentCategory(){
        return this.parentCategory;
    }
}
