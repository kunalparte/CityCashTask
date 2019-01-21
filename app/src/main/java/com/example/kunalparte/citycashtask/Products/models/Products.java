package com.example.kunalparte.citycashtask.Products.models;

public class Products {

    /**
     * id : 1
     * category_id : CA125
     * name : Shirt
     * category_name : PartyWear
     * description : The finest quality linen in town
     * price : 1500
     * qty : 8
     * image : https://www.google.com/url?sa=i&source=images&cd=&cad=rja&uact=8&ved=2ahUKEwjTwfOl4OLfAhVGf30KHROfC78QjRx6BAgBEAU&url=http%3A%2F%2Fbrandstore.vistaprint.in%2Fproduct%2Fcambridge-fabien-mens-formal-shirt%2FPVAG-0Q4K507W3K1Y&psig=AOvVaw20cR9we7SpRw1eT04AJ9dO&ust=1547193826392830
     */

    private String id;
    private String category_id;
    private String name;
    private String category_name;
    private String description;
    private String price;
    private String qty;
    private String image;
    private String size;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }


    public SortProps getSort_props() {
        return sort_props;
    }

    public void setSort_props(SortProps sort_props) {
        this.sort_props = sort_props;
    }

    private SortProps sort_props;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
